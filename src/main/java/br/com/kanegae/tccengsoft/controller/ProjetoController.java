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
import org.springframework.web.servlet.ModelAndView;

import br.com.kanegae.tccengsoft.model.Projeto;
import br.com.kanegae.tccengsoft.model.Usuario;
import br.com.kanegae.tccengsoft.service.ProjetoService;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {

	private ProjetoService service;

	@Autowired
	public ProjetoController(ProjetoService service) {
		this.service = service;
	}
	
	private Usuario getUsuarioAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (Usuario) authentication.getPrincipal();
	}

	@GetMapping
	public ModelAndView listar(Model model) {
		ModelAndView modelAndView = new ModelAndView("projeto/lista");

		List<Projeto> projetos = service.listarPorUsuario(getUsuarioAutenticado());
		model.addAttribute("projetos", projetos);

		return modelAndView;
	}

	@GetMapping("/formulario")
	public ModelAndView formulario(Model model) {
		ModelAndView modelAndView = new ModelAndView("projeto/formulario");
		
		model.addAttribute("projeto", new Projeto());
		
		return modelAndView;
	}

	@PostMapping
	public ModelAndView gravar(@ModelAttribute("projeto") Projeto projeto) {
		projeto.setDono(getUsuarioAutenticado());
		service.gravar(projeto);

		ModelAndView modelAndView = new ModelAndView("redirect:projeto");
		return modelAndView;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView exibir(@PathVariable("codigo") Long codigo, Model model) {
		ModelAndView modelAndView = new ModelAndView("projeto/formulario");
		
		Projeto projeto = service.findById(codigo);
		
		model.addAttribute("projeto", projeto);
		
		modelAndView.addObject("projeto", projeto);
		return modelAndView;
	}
	
	@PostMapping("/{codigo}/excluir")
	public ModelAndView excluir(@PathVariable("codigo") Long codigo) {
		service.excluir(codigo);

		ModelAndView modelAndView = new ModelAndView("redirect:/projeto");
		return modelAndView;
	}
}
