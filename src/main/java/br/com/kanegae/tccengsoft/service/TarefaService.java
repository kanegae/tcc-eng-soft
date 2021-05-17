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
public class TarefaService {
	private TarefaRepository tarefaRepository;
	private ProjetoRepository projetoRepository;
	private SprintRepository sprintRepository;

	@Autowired
	public TarefaService(TarefaRepository tarefaRepository, ProjetoRepository projetoRepository, SprintRepository sprintRepository) {
		this.tarefaRepository = tarefaRepository;
		this.projetoRepository = projetoRepository;
		this.sprintRepository = sprintRepository;
	}

	public List<Tarefa> listar(Long projetoSelecionado, Usuario usuarioAutenticado) {
		if(projetoSelecionado != 0) {
			return tarefaRepository.findAllByProjetoCodigo(projetoSelecionado);
		} else {
			return tarefaRepository.findAllByProjetoDono(usuarioAutenticado);
		}
	}
	
	public List<Projeto> listarProjetosDoUsuario(Usuario usuarioAutenticado) {
		return projetoRepository.findAllByDono(usuarioAutenticado);
	}
	
	public List<Sprint> listarSprintsDoUsuario(Usuario usuarioAutenticado) {
		return sprintRepository.findAllByDono(usuarioAutenticado);
	}

	public void gravar(Tarefa tarefa) {
		tarefaRepository.save(tarefa);
	}
	
	public void excluir(Long codigo) {
		tarefaRepository.deleteById(codigo);
	}
	
	public Tarefa findById(Long codigo) {
		Optional<Tarefa> tarefa = tarefaRepository.findById(codigo);
		return tarefa.get();
	}
}
