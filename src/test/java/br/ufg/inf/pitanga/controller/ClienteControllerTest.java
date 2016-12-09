package br.ufg.inf.pitanga.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import br.ufg.inf.pitanga.entidades.Cliente;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import java.security.InvalidParameterException;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ClienteControllerTest {

    @Autowired
    private MockHttpSession session;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .addFilters(springSecurityFilterChain)
            .apply(springSecurity())
            .build();
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaCadastro() throws Exception {
        mockMvc.perform(get("/cadastro")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test(expected = InvalidParameterException.class)
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveValidarClienteERedirecionarParaCadastro() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("");
        mockMvc.perform(get("/cadastrarCliente")
            .requestAttr("cliente", cliente)
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andExpect(authenticated())
            .andDo(print());
    }
}
