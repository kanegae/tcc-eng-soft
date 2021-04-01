package br.com.kanegae.tccengsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanegae.tccengsoft.model.Usuario;

@Repository
public interface UsuarioRepositoty extends JpaRepository<Usuario, Long> {

}
