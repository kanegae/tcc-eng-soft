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
class SprintControllerTest {

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
	void deveRedirecionarParaSprints() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/sprint"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("sprints"))
				.andExpect(MockMvcResultMatchers.view().name("sprint/lista"));
	}
	
	@Test
	@WithUserDetails("admin@admin.com")
	void deveRedirecionarParaFormularioVazio() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/sprint/formulario"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("sprint"))
				.andExpect(MockMvcResultMatchers.view().name("sprint/formulario"));
	}
	
	@Test
	@WithUserDetails("admin@admin.com")
	void deveRedirecionarParaFormularioPreenchido() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/sprint/1"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("sprint"))
				.andExpect(MockMvcResultMatchers.model().attribute("sprint", hasProperty("codigo", is(1L))))
				.andExpect(MockMvcResultMatchers.view().name("sprint/formulario"));
	}

}
