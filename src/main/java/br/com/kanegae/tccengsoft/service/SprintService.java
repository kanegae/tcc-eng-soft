package br.com.kanegae.tccengsoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kanegae.tccengsoft.model.Projeto;
import br.com.kanegae.tccengsoft.model.Sprint;
import br.com.kanegae.tccengsoft.model.Tarefa;
import br.com.kanegae.tccengsoft.model.Usuario;
import br.com.kanegae.tccengsoft.repository.ProjetoRepository;
import br.com.kanegae.tccengsoft.repository.SprintRepository;
import br.com.kanegae.tccengsoft.repository.TarefaRepository;

@Service
public class SprintService {
	private SprintRepository sprintRepository;
	private ProjetoRepository projetoRepository;
	private TarefaRepository tarefaRepository;

	@Autowired
	public SprintService(SprintRepository sprintRepository, ProjetoRepository projetoRepository, TarefaRepository tarefaRepository) {
		this.sprintRepository = sprintRepository;
		this.projetoRepository = projetoRepository;
		this.tarefaRepository = tarefaRepository;
	}

	public List<Sprint> listar() {
		return sprintRepository.findAll();
	}
	
	public List<Sprint> listar(Long projetoSelecionado, Usuario usuarioAutenticado) {
		if(projetoSelecionado != 0) {
			return sprintRepository.findAllByProjetoCodigo(projetoSelecionado);
		} else {
			return sprintRepository.findAllByProjetoDono(usuarioAutenticado);
		}
	}
	
	public List<Tarefa> listarTarefasDaSprint(Long projetoSelecionado, Long sprintSelecionada) {
		if(projetoSelecionado != 0) {
			return tarefaRepository.findAllBySprintCodigoAndProjetoCodigo(sprintSelecionada, projetoSelecionado);
		} else {
			return tarefaRepository.findAllBySprintCodigo(sprintSelecionada);
		}
	}
	
	public List<Projeto> listarProjetosDoUsuario(Usuario usuarioAutenticado) {
		return projetoRepository.findAllByDono(usuarioAutenticado);
	}

	public void gravar(Sprint sprint) {
		sprintRepository.save(sprint);
	}
	
	public void excluir(Long codigo) {
		sprintRepository.deleteById(codigo);
	}
	
	public Sprint findById(Long codigo) {
		Optional<Sprint> sprint = sprintRepository.findById(codigo);
		return sprint.get();
	}
}
