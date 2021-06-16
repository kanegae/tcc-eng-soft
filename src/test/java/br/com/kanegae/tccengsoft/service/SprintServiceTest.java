package br.com.kanegae.tccengsoft.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.kanegae.tccengsoft.model.Sprint;

@SpringBootTest
class SprintServiceTest {
	
	@Autowired
	SprintService sprintService;
	
	@Test
	void deveRetornarPorCodigo() {
		Sprint sprint = sprintService.findById(1L);
		assertEquals(sprint.getCodigo(), 1L);
	}
	
	@Test
	void naoDeveRetornarPorCodigo() {
		try{ 
			sprintService.findById(0L);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "No value present");
		}
	}

}
