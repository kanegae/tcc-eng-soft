package br.com.kanegae.tccengsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanegae.tccengsoft.model.Sprint;
import br.com.kanegae.tccengsoft.model.Usuario;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

	public List<Sprint> findAllByDono(Usuario usuario);
	
}
