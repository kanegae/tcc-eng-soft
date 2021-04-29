package br.com.kanegae.tccengsoft.controller;

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
