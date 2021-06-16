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

import br.com.kanegae.tccengsoft.model.Sprint;
import br.com.kanegae.tccengsoft.model.Usuario;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SprintRepositoryTest {

	@Autowired
	SprintRepository sprintRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	Usuario dono;
	Sprint sprintParaTeste;
	Long sprintId;
	
	@BeforeAll
	void setup() {
		dono = usuarioRepository.getOne(1L);
	}
	
	@Test
	@Order(1)
	void testCreate() {
		Sprint sprint = new  Sprint();
		sprint.setObjetivo("SPRINT PARA TESTE");
		sprint.setDescricao("DESCRIÇÃO DA SPRINT PARA TESTE");
		sprint.setDono(dono);
		sprintParaTeste = sprintRepository.save(sprint);
		sprintId = sprintParaTeste.getCodigo();
	}
	
	@Test
	@Order(2)
	void testFindById() {
		Sprint sprint = sprintRepository.findById(sprintId).get();
		assertEquals(sprint.getCodigo(), sprintId);
	}
	
	@Test
	@Order(3)
	void testFindAllByDono() {
		List<Sprint> sprints = sprintRepository.findAllByDono(dono);
		for(Sprint sprint : sprints) {
			assertEquals(sprint.getDono().getCodigo(), dono.getCodigo());
		}
	}
	
	@Test
	@Order(4)
	void testUpdate() {
		String novoObjetivo = "SPRINT PARA TESTE - UPDATE";
		sprintParaTeste.setObjetivo(novoObjetivo);
		Sprint sprint = sprintRepository.save(sprintParaTeste);
		assertEquals(sprint.getObjetivo(), novoObjetivo);
	}
	
	@Test
	@AfterAll
	void testDelete() {
		sprintRepository.delete(sprintParaTeste);
	}

}
