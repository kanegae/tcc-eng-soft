package br.com.kanegae.tccengsoft.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.kanegae.tccengsoft.model.Usuario;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	Usuario usuarioParaTeste;
	Long usuarioId;
	
	@BeforeAll
	void setup() {
		
	}
	
	@Test
	@Order(1)
	void testCreate() {
		Usuario usuario = new  Usuario();
		usuario.setNome("USUARIO PARA TESTE");
		usuarioParaTeste = usuarioRepository.save(usuario);
		usuarioId = usuarioParaTeste.getCodigo();
	}
	
	@Test
	@Order(2)
	void testFindById() {
		Usuario usuario = usuarioRepository.findById(usuarioId).get();
		assertEquals(usuario.getCodigo(), usuarioId);
	}
	
	@Test
	@Order(3)
	void testUpdate() {
		String novoNome = "USUARIO PARA TESTE - UPDATE";
		usuarioParaTeste.setNome(novoNome);
		Usuario usuario = usuarioRepository.save(usuarioParaTeste);
		assertEquals(usuario.getNome(), novoNome);
	}
	
	@Test
	@AfterAll
	void testDelete() {
		usuarioRepository.delete(usuarioParaTeste);
	}

}
