package br.com.kanegae.tccengsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanegae.tccengsoft.model.Projeto;
import br.com.kanegae.tccengsoft.model.Usuario;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

	public List<Projeto> findAllByDono(Usuario usuario);
	
}
