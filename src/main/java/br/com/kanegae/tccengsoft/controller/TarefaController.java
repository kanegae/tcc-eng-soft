package br.com.kanegae.tccengsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.kanegae.tccengsoft.model.Projeto;
import br.com.kanegae.tccengsoft.model.Sprint;
import br.com.kanegae.tccengsoft.model.Tarefa;
import br.com.kanegae.tccengsoft.model.Usuario;
import br.com.kanegae.tccengsoft.service.TarefaService;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {

	private TarefaService service;

	@Autowired
	public TarefaController(TarefaService service) {
		this.service = service;
	}

	private Usuario getUsuarioAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (Usuario) authentication.getPrincipal();
	}
	
	@GetMapping
	public ModelAndView listar(Model model, @RequestParam(name = "projeto", defaultValue = "0", required = false) String projeto) {
		ModelAndView modelAndView = new ModelAndView("tarefa/lista");
		
		Long projetoSelecionado = Long.parseLong(projeto);
		model.addAttribute("projetoSelecionado", projetoSelecionado);
		
		List<Tarefa> tarefas = service.listar(projetoSelecionado, getUsuarioAutenticado());
		model.addAttribute("tarefas", tarefas);

		List<Projeto> projetos = service.listarProjetosDoUsuario(getUsuarioAutenticado());
		model.addAttribute("projetos", projetos);
		
		return modelAndView;
	}

	@GetMapping("/formulario")
	public ModelAndView formulario(Model model) {
		ModelAndView modelAndView = new ModelAndView("tarefa/formulario");
		
		model.addAttribute("tarefa", new Tarefa());
		
		List<Projeto> projetos = service.listarProjetosDoUsuario(getUsuarioAutenticado());
		model.addAttribute("projetos", projetos);
		
		List<Sprint> sprints = service.listarSprintsDoUsuario(getUsuarioAutenticado());
		model.addAttribute("sprints", sprints);
		
		return modelAndView;
	}

	@PostMapping
	public ModelAndView gravar(@ModelAttribute("tarefa") Tarefa tarefa) {
		service.gravar(tarefa);
		
		ModelAndView modelAndView = new ModelAndView("redirect:tarefa");
		return modelAndView;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView exibir(@PathVariable("codigo") Long codigo, Model model) {
		ModelAndView modelAndView = new ModelAndView("tarefa/formulario");
		
		List<Projeto> projetos = service.listarProjetosDoUsuario(getUsuarioAutenticado());
		model.addAttribute("projetos", projetos);
		
		List<Sprint> sprints = service.listarSprintsDoUsuario(getUsuarioAutenticado());
		model.addAttribute("sprints", sprints);
		
		Tarefa tarefa = service.findById(codigo);
		
		model.addAttribute("tarefa", tarefa);
		
		modelAndView.addObject("tarefa", tarefa);
		return modelAndView;
	}
	
	@PostMapping("/{codigo}/excluir")
	public ModelAndView excluir(@PathVariable("codigo") Long codigo) {
		service.excluir(codigo);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/tarefa");
		return modelAndView;
	}
}
