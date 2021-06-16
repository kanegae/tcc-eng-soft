package br.com.kanegae.tccengsoft.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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

import br.com.kanegae.tccengsoft.model.Projeto;
import br.com.kanegae.tccengsoft.model.Usuario;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjetoRepositoryTest {

	@Autowired
	ProjetoRepository projetoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	Usuario dono;
	Projeto projetoParaTeste;
	Long projetoId;
	
	@BeforeAll
	void setup() {
		dono = usuarioRepository.getOne(1L);
	}
	
	@Test
	@Order(1)
	void testCreate() {
		Projeto projeto = new  Projeto();
		projeto.setTitulo("PROJETO PARA TESTE");
		projeto.setDescricao("DESCRIÇÃO DO PROJETO PARA TESTE");
		projeto.setDono(dono);
		projetoParaTeste = projetoRepository.save(projeto);
		projetoId = projetoParaTeste.getCodigo();
	}
	
	@Test
	@Order(2)
	void testFindById() {
		Projeto projeto = projetoRepository.findById(projetoId).get();
		assertEquals(projeto.getCodigo(), projetoId);
	}
	
	@Test
	@Order(3)
	void testFindAllByDono() {
		List<Projeto> projetos = projetoRepository.findAllByDono(dono);
		for(Projeto projeto : projetos) {
			assertEquals(projeto.getDono().getCodigo(), dono.getCodigo());
		}
	}
	
	@Test
	@Order(4)
	void testUpdate() {
		String novoTitulo = "PROJETO PARA TESTE - UPDATE";
		projetoParaTeste.setTitulo(novoTitulo);
		Projeto projeto = projetoRepository.save(projetoParaTeste);
		assertEquals(projeto.getTitulo(), novoTitulo);
	}
	
	@Test
	@AfterAll
	void testDelete() {
		projetoRepository.delete(projetoParaTeste);
	}

}
