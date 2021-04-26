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

import br.com.kanegae.tccengsoft.model.Sprint;
import br.com.kanegae.tccengsoft.service.SprintService;

@Controller
@RequestMapping("/sprint")
public class SprintController {

	private SprintService service;

	@Autowired
	public SprintController(SprintService service) {
		this.service = service;
	}

	@GetMapping
	public ModelAndView listar(Model model) {
		ModelAndView modelAndView = new ModelAndView("sprint/lista");

		List<Sprint> sprints = service.listar();
		model.addAttribute("sprints", sprints);

		return modelAndView;
	}

	@GetMapping("/formulario")
	public ModelAndView formulario(Model model) {
		ModelAndView modelAndView = new ModelAndView("sprint/formulario");
		
		model.addAttribute("sprint", new Sprint());
		
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
