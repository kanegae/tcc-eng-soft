package br.com.kanegae.tccengsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanegae.tccengsoft.model.Tarefa;
import br.com.kanegae.tccengsoft.model.Usuario;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	
	public List<Tarefa> findAllByProjetoDono(Usuario usuario);
	
	public List<Tarefa> findAllByProjetoCodigo(Long codigo);
	
	public List<Tarefa> findAllBySprintCodigo(Long codigo);
	
	public List<Tarefa> findAllBySprintCodigoAndProjetoCodigo(Long codigoDaSprint, Long codigoDoProjeto);
	
}
