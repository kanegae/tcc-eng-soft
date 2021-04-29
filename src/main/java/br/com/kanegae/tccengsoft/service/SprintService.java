package br.com.kanegae.tccengsoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kanegae.tccengsoft.model.Projeto;
import br.com.kanegae.tccengsoft.model.Sprint;
import br.com.kanegae.tccengsoft.model.Usuario;
import br.com.kanegae.tccengsoft.repository.ProjetoRepository;
import br.com.kanegae.tccengsoft.repository.SprintRepository;

@Service
public class SprintService {
	private SprintRepository sprintRepository;
	private ProjetoRepository projetoRepository;

	@Autowired
	public SprintService(SprintRepository sprintRepository, ProjetoRepository projetoRepository) {
		this.sprintRepository = sprintRepository;
		this.projetoRepository = projetoRepository;
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
	
	public List<Projeto> listarProjetosDoUsuario(Usuario usuarioAutenticado) {
		return projetoRepository.findAllByDono(usuarioAutenticado);
	}

	public void gravar(Sprint sprint) {
		sprintRepository.save(sprint);
	}
	
	public void excluir(Long codigo) {
		sprintRepository.deleteById(codigo);
	}
	
	// TODO verificar Optional
	public Sprint findById(Long codigo) {
		Optional<Sprint> sprint = sprintRepository.findById(codigo);
		return sprint.get();
	}
}
