package br.com.kanegae.tccengsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kanegae.tccengsoft.model.Usuario;

public interface UsuarioRepositoty extends JpaRepository<Usuario, Long> {

}
