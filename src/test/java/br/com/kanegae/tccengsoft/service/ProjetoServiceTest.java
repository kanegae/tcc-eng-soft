package br.com.kanegae.tccengsoft.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.kanegae.tccengsoft.model.Projeto;

@SpringBootTest
class ProjetoServiceTest {
	
	@Autowired
	ProjetoService projetoService;
	
	@Test
	void deveRetornarPorCodigo() {
		Projeto projeto = projetoService.findById(1L);
		assertEquals(projeto.getCodigo(), 1L);
	}
	
	@Test
	void naoDeveRetornarPorCodigo() {
		try{ 
			projetoService.findById(0L);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "No value present");
		}
	}

}
