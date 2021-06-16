package br.com.kanegae.tccengsoft.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.kanegae.tccengsoft.model.Prioridade;
import br.com.kanegae.tccengsoft.model.Projeto;
import br.com.kanegae.tccengsoft.model.Status;
import br.com.kanegae.tccengsoft.model.Tarefa;
import br.com.kanegae.tccengsoft.model.Usuario;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TarefaRepositoryTest {

	@Autowired
	TarefaRepository tarefaRepository;
	@Autowired
	ProjetoRepository projetoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	Usuario dono;
	Projeto projeto;
	Tarefa tarefaParaTeste;
	Long tarefaId;
	
	@BeforeAll
	void setup() {
		dono = usuarioRepository.getOne(1L);
		projeto = projetoRepository.getOne(1L);
	}
	
	@Test
	@Order(1)
	void testCreate() {
		Tarefa tarefa = new  Tarefa();
		tarefa.setTitulo("TAREFA PARA TESTE");
		tarefa.setDescricao("DESCRIÇÃO DA TAREFA PARA TESTE");
		tarefa.setProjeto(projeto);
		tarefa.setPrioridade(Prioridade.INDEFINIDA);
		tarefa.setStatus(Status.NAO_INICIADO);
		tarefa.setSprint(null);
		tarefaParaTeste = tarefaRepository.save(tarefa);
		tarefaId = tarefaParaTeste.getCodigo();
	}
	
	@Test
	@Order(2)
	void testFindById() {
		Tarefa tarefa = tarefaRepository.findById(tarefaId).get();
		assertEquals(tarefa.getCodigo(), tarefaId);
	}
	
	@Test
	@Order(3)
	void testFindAllByDono() {
		List<Tarefa> tarefas = tarefaRepository.findAllByProjetoDono(dono);
		for(Tarefa tarefa : tarefas) {
			assertEquals(tarefa.getProjeto().getDono().getCodigo(), dono.getCodigo());
		}
	}
	
	@Test
	@Order(4)
	void testUpdate() {
		String novoTitulo = "TAREFA PARA TESTE - UPDATE";
		tarefaParaTeste.setTitulo(novoTitulo);
		Tarefa tarefa = tarefaRepository.save(tarefaParaTeste);
		assertEquals(tarefa.getTitulo(), novoTitulo);
	}
	
	@Test
	@AfterAll
	void testDelete() {
		tarefaRepository.delete(tarefaParaTeste);
	}

}
