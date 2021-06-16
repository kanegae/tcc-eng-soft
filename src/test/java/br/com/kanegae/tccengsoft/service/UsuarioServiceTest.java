package br.com.kanegae.tccengsoft.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.kanegae.tccengsoft.model.Usuario;

@SpringBootTest
class UsuarioServiceTest {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Test
	void deveRetornarPorCodigo() {
		Usuario usuario = usuarioService.findById(1L);
		assertEquals(usuario.getCodigo(), 1L);
	}
	
	@Test
	void naoDeveRetornarPorCodigo() {
		try{ 
			usuarioService.findById(0L);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "No value present");
		}
	}

}
