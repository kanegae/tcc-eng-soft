package br.com.kanegae.tccengsoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kanegae.tccengsoft.model.Sprint;
import br.com.kanegae.tccengsoft.repository.SprintRepository;

@Service
public class SprintService {
	private SprintRepository repository;

	@Autowired
	public SprintService(SprintRepository repository) {
		this.repository = repository;
	}

	public List<Sprint> listar() {
		return repository.findAll();
	}

	public void gravar(Sprint sprint) {
		repository.save(sprint);
	}
	
	public void excluir(Long codigo) {
		repository.deleteById(codigo);
	}
	
	// TODO verificar Optional
	public Sprint findById(Long codigo) {
		Optional<Sprint> sprint = repository.findById(codigo);
		return sprint.get();
	}
}
