package br.ufg.inf.pitanga.controller;

<<<<<<< HEAD
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
=======
import br.ufg.inf.pitanga.entidades.Cliente;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
>>>>>>> da2c76ed0d4764133217b13b06d2d54cbb0dce17
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
<<<<<<< HEAD

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
=======
import java.security.InvalidParameterException;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
>>>>>>> da2c76ed0d4764133217b13b06d2d54cbb0dce17

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ClienteControllerTest {

    @Autowired
<<<<<<< HEAD
    private MockHttpSession session;
    @Autowired
=======
>>>>>>> da2c76ed0d4764133217b13b06d2d54cbb0dce17
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
<<<<<<< HEAD
=======

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

>>>>>>> da2c76ed0d4764133217b13b06d2d54cbb0dce17
}
