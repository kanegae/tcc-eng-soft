package br.com.kanegae.tccengsoft.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.kanegae.tccengsoft.model.Tarefa;

@SpringBootTest
class TarefaServiceTest {
	
	@Autowired
	TarefaService tarefaService;
	
	@Test
	void deveRetornarPorCodigo() {
		Tarefa tarefa = tarefaService.findById(1L);
		assertEquals(tarefa.getCodigo(), 1L);
	}
	
	@Test
	void naoDeveRetornarPorCodigo() {
		try{ 
			tarefaService.findById(0L);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "No value present");
		}
	}

}
