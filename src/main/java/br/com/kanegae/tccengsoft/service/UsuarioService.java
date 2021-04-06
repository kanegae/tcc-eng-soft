package br.com.kanegae.tccengsoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kanegae.tccengsoft.model.Usuario;
import br.com.kanegae.tccengsoft.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private UsuarioRepository repository;

	@Autowired
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	public List<Usuario> listar() {
		return repository.findAll();
	}

	public void gravar(Usuario usuario) {
		repository.save(usuario);
	}
	
	public void excluir(Long codigo) {
		repository.deleteById(codigo);
	}
	
	// TODO verificar Optional
	public Usuario findById(Long codigo) {
		Optional<Usuario> usuario = repository.findById(codigo);
		return usuario.get();
	}
}
