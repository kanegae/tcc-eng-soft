package br.com.kanegae.tccengsoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kanegae.tccengsoft.model.Tarefa;
import br.com.kanegae.tccengsoft.repository.TarefaRepository;

@Service
public class TarefaService {
	private TarefaRepository repository;

	@Autowired
	public TarefaService(TarefaRepository repository) {
		this.repository = repository;
	}

	public List<Tarefa> listar() {
		return repository.findAll();
	}

	public void gravar(Tarefa tarefa) {
		repository.save(tarefa);
	}
	
	public void excluir(Long codigo) {
		repository.deleteById(codigo);
	}
	
	// TODO verificar Optional
	public Tarefa findById(Long codigo) {
		Optional<Tarefa> tarefa = repository.findById(codigo);
		return tarefa.get();
	}
}
