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
	
	public List<Sprint> listar(Usuario usuarioAutenticado) {
		return sprintRepository.findAllByDono(usuarioAutenticado);
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
	
	public Tarefa findTarefaById(Long codigo) {
		Optional<Tarefa> tarefa = tarefaRepository.findById(codigo);
		return tarefa.get();
	}
	
	public void gravarTarefa(Tarefa tarefa) {
		tarefaRepository.save(tarefa);
	}
	
	public void excluirTarefa(Tarefa tarefa) {
		tarefaRepository.delete(tarefa);
	}
	
	public List<Sprint> listarSprintsDoUsuario(Usuario usuarioAutenticado) {
		return sprintRepository.findAllByDono(usuarioAutenticado);
	}
}
