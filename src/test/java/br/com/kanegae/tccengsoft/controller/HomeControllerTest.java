package br.com.kanegae.tccengsoft.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

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
	void deveRedirecionarParaHome() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.view().name("index"));
	}

}
