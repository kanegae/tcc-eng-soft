package br.com.kanegae.tccengsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.kanegae.tccengsoft.model.Usuario;
import br.com.kanegae.tccengsoft.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioService service;

	@Autowired
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	@GetMapping
	public ModelAndView listar(Model model) {
		ModelAndView modelAndView = new ModelAndView("usuario/lista");

		List<Usuario> usuarios = service.listar();
		model.addAttribute("usuarios", usuarios);

		return modelAndView;
	}

	@GetMapping("/formulario")
	public ModelAndView formulario(Model model) {
		ModelAndView modelAndView = new ModelAndView("usuario/formulario");
		
		model.addAttribute("usuario", new Usuario());
		
		return modelAndView;
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastro(Model model) {
		ModelAndView modelAndView = new ModelAndView("usuario/cadastro");
		
		model.addAttribute("usuario", new Usuario());
		
		return modelAndView;
	}

	@PostMapping
	public ModelAndView gravar(@ModelAttribute("usuario") Usuario usuario) {
		service.gravar(usuario);

		// TODO verificar redirect
		ModelAndView modelAndView = new ModelAndView("redirect:usuario");
		return modelAndView;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrar(@ModelAttribute("usuario") Usuario usuario) {
		service.gravar(usuario);

		// TODO verificar redirect
		ModelAndView modelAndView = new ModelAndView("redirect:/login");
		return modelAndView;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView exibir(@PathVariable("codigo") Long codigo, Model model) {
		ModelAndView modelAndView = new ModelAndView("usuario/formulario");
		
		Usuario usuario = service.findById(codigo);
		
		model.addAttribute("usuario", usuario);
		
		modelAndView.addObject("usuario", usuario);
		return modelAndView;
	}
	
	// TODO revisar método: requisições DELETE não funcionam via HTML
	@DeleteMapping("/{codigo}")
	public ModelAndView excluirOLD(@PathVariable("codigo") Long codigo) {
		service.excluir(codigo);

		// TODO verificar redirect
		ModelAndView modelAndView = new ModelAndView("redirect:/usuario");
		return modelAndView;
	}
	
	// TODO revisar método
	@PostMapping("/{codigo}/excluir")
	public ModelAndView excluir(@PathVariable("codigo") Long codigo) {
		service.excluir(codigo);

		// TODO verificar redirect
		ModelAndView modelAndView = new ModelAndView("redirect:/usuario");
		return modelAndView;
	}
}
