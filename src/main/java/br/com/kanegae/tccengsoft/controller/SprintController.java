package br.com.kanegae.tccengsoft.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.kanegae.tccengsoft.model.Projeto;
import br.com.kanegae.tccengsoft.model.Sprint;
import br.com.kanegae.tccengsoft.model.Status;
import br.com.kanegae.tccengsoft.model.Tarefa;
import br.com.kanegae.tccengsoft.model.Usuario;
import br.com.kanegae.tccengsoft.service.SprintService;

@Controller
@RequestMapping("/sprint")
public class SprintController {

	private SprintService service;

	@Autowired
	public SprintController(SprintService service) {
		this.service = service;
	}
	
	private Usuario getUsuarioAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (Usuario) authentication.getPrincipal();
	}

	@GetMapping
	public ModelAndView listar(Model model, @RequestParam(name = "projeto", defaultValue = "0", required = false) String projeto) {
		ModelAndView modelAndView = new ModelAndView("sprint/lista");

		Long projetoSelecionado = Long.parseLong(projeto);
		model.addAttribute("projetoSelecionado", projetoSelecionado);
		
		List<Sprint> sprints = service.listar(projetoSelecionado, getUsuarioAutenticado());
		model.addAttribute("sprints", sprints);
		
		List<Projeto> projetos = service.listarProjetosDoUsuario(getUsuarioAutenticado());
		model.addAttribute("projetos", projetos);

		return modelAndView;
	}

	@GetMapping("/formulario")
	public ModelAndView formulario(Model model) {
		ModelAndView modelAndView = new ModelAndView("sprint/formulario");
		
		model.addAttribute("sprint", new Sprint());
		
		List<Projeto> projetos = service.listarProjetosDoUsuario(getUsuarioAutenticado());
		model.addAttribute("projetos", projetos);
		
		return modelAndView;
	}

	@PostMapping
	public ModelAndView gravar(@ModelAttribute("sprint") Sprint sprint) {
		service.gravar(sprint);

		// TODO verificar redirect
		ModelAndView modelAndView = new ModelAndView("redirect:sprint");
		return modelAndView;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView exibir(@PathVariable("codigo") Long codigo, Model model) {
		ModelAndView modelAndView = new ModelAndView("sprint/formulario");
		
		List<Projeto> projetos = service.listarProjetosDoUsuario(getUsuarioAutenticado());
		model.addAttribute("projetos", projetos);
		
		Sprint sprint = service.findById(codigo);
		
		model.addAttribute("sprint", sprint);
		
		modelAndView.addObject("sprint", sprint);
		return modelAndView;
	}
	
	@GetMapping("/{codigo}/tarefas")
	public ModelAndView listarTarefas(@PathVariable("codigo") Long codigo, @RequestParam(name = "projeto", defaultValue = "0", required = false) String projeto, Model model) {
		ModelAndView modelAndView = new ModelAndView("sprint/tarefas");
		
		Long projetoSelecionado = Long.parseLong(projeto);
		model.addAttribute("projetoSelecionado", projetoSelecionado);
		
		Sprint sprint = service.findById(codigo);
		model.addAttribute("sprint", sprint);
		
		List<Tarefa> tarefas = service.listarTarefasDaSprint(projetoSelecionado, codigo);
		model.addAttribute("tarefas", tarefas);
		
		List<Projeto> projetos = service.listarProjetosDoUsuario(getUsuarioAutenticado());
		model.addAttribute("projetos", projetos);
		
		Calendar dataInicial = sprint.getDataInicial();
		Calendar dataFinal = sprint.getDataFinal();
		Calendar dataAtual = Calendar.getInstance();
		
		// somente sprints com datas definidas
		if(dataInicial != null && dataFinal != null) {
		
			int diasTotal = getDiferencaEmDias(dataInicial, dataFinal);
			model.addAttribute("diasTotal", diasTotal);
			
			// sprint em andamento
			int diasPassados = getDiferencaEmDias(dataInicial, dataAtual);
			// sprint encerrada
			if(dataAtual.getTime().after(dataFinal.getTime())) {
				diasPassados = diasTotal;
			}
			// sprint não iniciada
			if(dataAtual.getTime().before(dataInicial.getTime())) {
				diasPassados = 0;
			}
			model.addAttribute("diasPassados", diasPassados);
			
			int diasPassadosPorcentagem = (int) (((float)diasPassados/diasTotal)*100);
			model.addAttribute("diasPassadosPorcentagem", diasPassadosPorcentagem);
			
			int tarefasTotal = tarefas.size();
			model.addAttribute("tarefasTotal", tarefasTotal);
			
			int tarefasConcluidas = (int) tarefas.stream()
		        .filter(tarefa -> (tarefa.getStatus() == Status.CONCLUIDO))
		        .count();
			model.addAttribute("tarefasConcluidas", tarefasConcluidas);
			
			int tarefasConcluidasPorcentagem = (int) (((float)tarefasConcluidas/tarefasTotal)*100);
			// sprint com nenhuma tarefa
			if(tarefas.size() == 0) {
				tarefasConcluidasPorcentagem = 100;
			}
			model.addAttribute("tarefasConcluidasPorcentagem", tarefasConcluidasPorcentagem);
		
		} else {
			
			model.addAttribute("diasTotal", "0");
			model.addAttribute("diasPassados", "0");
			model.addAttribute("diasPassadosPorcentagem", "0");
			
			model.addAttribute("tarefasTotal", "0");
			model.addAttribute("tarefasConcluidas", "0");
			model.addAttribute("tarefasConcluidasPorcentagem", "0");
			
		}

		return modelAndView;
	}
	
	public int getDiferencaEmDias(Calendar dataInicial, Calendar dataFinal) {
		long duracao = (dataFinal.getTime().getTime() - dataInicial.getTime().getTime()) + 3600000;      
		int dias = (int) (duracao / 86400000L);
		
		return dias;
	}
	
	// TODO revisar método: requisições DELETE não funcionam via HTML
	@DeleteMapping("/{codigo}")
	public ModelAndView excluirOLD(@PathVariable("codigo") Long codigo) {
		service.excluir(codigo);

		// TODO verificar redirect
		ModelAndView modelAndView = new ModelAndView("redirect:/sprint");
		return modelAndView;
	}
	
	// TODO revisar método
	@PostMapping("/{codigo}/excluir")
	public ModelAndView excluir(@PathVariable("codigo") Long codigo) {
		service.excluir(codigo);

		// TODO verificar redirect
		ModelAndView modelAndView = new ModelAndView("redirect:/sprint");
		return modelAndView;
	}
}
