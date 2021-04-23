package br.com.kanegae.tccengsoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.kanegae.tccengsoft.model.Usuario;
import br.com.kanegae.tccengsoft.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	private UsuarioRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<Usuario> listar() {
		return repository.findAll();
	}

	public void gravar(Usuario usuario) {
		if(!usuario.getSenha().equals("")) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		}
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

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = repository.findByEmail(email);
		if (usuario == null) {
            throw new UsernameNotFoundException("Dados inválidos.");
		}
		return usuario;
	}
}
