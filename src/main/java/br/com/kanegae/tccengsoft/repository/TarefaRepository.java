package br.com.kanegae.tccengsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kanegae.tccengsoft.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
