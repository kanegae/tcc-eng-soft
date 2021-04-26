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

import br.com.kanegae.tccengsoft.model.Tarefa;
import br.com.kanegae.tccengsoft.service.TarefaService;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {

	private TarefaService service;

	@Autowired
	public TarefaController(TarefaService service) {
		this.service = service;
	}

	@GetMapping
	public ModelAndView listar(Model model) {
		ModelAndView modelAndView = new ModelAndView("tarefa/lista");

		List<Tarefa> tarefas = service.listar();
		model.addAttribute("tarefas", tarefas);

		return modelAndView;
	}

	@GetMapping("/formulario")
	public ModelAndView formulario(Model model) {
		ModelAndView modelAndView = new ModelAndView("tarefa/formulario");
		
		model.addAttribute("tarefa", new Tarefa());
		
		return modelAndView;
	}

	@PostMapping
	public ModelAndView gravar(@ModelAttribute("tarefa") Tarefa tarefa) {
		service.gravar(tarefa);

		// TODO verificar redirect
		ModelAndView modelAndView = new ModelAndView("redirect:tarefa");
		return modelAndView;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView exibir(@PathVariable("codigo") Long codigo, Model model) {
		ModelAndView modelAndView = new ModelAndView("tarefa/formulario");
		
		Tarefa tarefa = service.findById(codigo);
		
		model.addAttribute("tarefa", tarefa);
		
		modelAndView.addObject("tarefa", tarefa);
		return modelAndView;
	}
	
	// TODO revisar método: requisições DELETE não funcionam via HTML
	@DeleteMapping("/{codigo}")
	public ModelAndView excluirOLD(@PathVariable("codigo") Long codigo) {
		service.excluir(codigo);

		// TODO verificar redirect
		ModelAndView modelAndView = new ModelAndView("redirect:/tarefa");
		return modelAndView;
	}
	
	// TODO revisar método
	@PostMapping("/{codigo}/excluir")
	public ModelAndView excluir(@PathVariable("codigo") Long codigo) {
		service.excluir(codigo);

		// TODO verificar redirect
		ModelAndView modelAndView = new ModelAndView("redirect:/tarefa");
		return modelAndView;
	}
}
