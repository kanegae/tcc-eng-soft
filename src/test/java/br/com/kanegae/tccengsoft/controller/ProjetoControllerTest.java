package br.com.kanegae.tccengsoft.controller;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class ProjetoControllerTest {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders
						.webAppContextSetup(context)
						.apply(SecurityMockMvcConfigurers.springSecurity())
						.build();
	}
	
	@Test
	@WithUserDetails("admin@admin.com")
	void deveRedirecionarParaProjetos() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/projeto"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("projetos"))
				.andExpect(MockMvcResultMatchers.view().name("projeto/lista"));
	}
	
	@Test
	@WithUserDetails("admin@admin.com")
	void deveRedirecionarParaFormularioVazio() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/projeto/formulario"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("projeto"))
				.andExpect(MockMvcResultMatchers.view().name("projeto/formulario"));
	}
	
	@Test
	@WithUserDetails("admin@admin.com")
	void deveRedirecionarParaFormularioPreenchido() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/projeto/1"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("projeto"))
				.andExpect(MockMvcResultMatchers.model().attribute("projeto", hasProperty("codigo", is(1L))))
				.andExpect(MockMvcResultMatchers.view().name("projeto/formulario"));
	}

}
