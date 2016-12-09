package br.ufg.inf.pitanga.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class FuncionarioControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private FuncionarioController funcionarioController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(funcionarioController).build();
    }

    @Test
    public void testRetornaPaginaIndex() throws Exception {
        mockMvc.perform(get(Paginas.INDEX_FUNCIONARIOS))
            .andExpect(unauthenticated());
    }
}
