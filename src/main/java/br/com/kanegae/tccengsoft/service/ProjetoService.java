package br.com.kanegae.tccengsoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kanegae.tccengsoft.model.Projeto;
import br.com.kanegae.tccengsoft.model.Usuario;
import br.com.kanegae.tccengsoft.repository.ProjetoRepository;

@Service
public class ProjetoService {
	private ProjetoRepository repository;

	@Autowired
	public ProjetoService(ProjetoRepository repository) {
		this.repository = repository;
	}

	public List<Projeto> listar() {
		return repository.findAll();
	}
	
	public List<Projeto> listarPorUsuario(Usuario usuario) {
		return repository.findAllByDono(usuario);
	}

	public void gravar(Projeto projeto) {
		repository.save(projeto);
	}
	
	public void excluir(Long codigo) {
		repository.deleteById(codigo);
	}
	
	public Projeto findById(Long codigo) {
		Optional<Projeto> projeto = repository.findById(codigo);
		return projeto.get();
	}
}
