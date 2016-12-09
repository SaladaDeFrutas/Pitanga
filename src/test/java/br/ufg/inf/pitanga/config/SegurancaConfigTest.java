package br.ufg.inf.pitanga.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SegurancaConfigTest {

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
    public void deveRedirecionarParaPaginaLogin() throws Exception {
        mockMvc.perform(get("/")
            .accept(MediaType.ALL))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("**/login"))
            .andExpect(unauthenticated());
    }

    @Test
    public void devePermitirVerArquivosPublicos() throws Exception {
        verificaSeNaoEstaLogado();
        mockMvc.perform(get("/css/bootstrap.css")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andExpect(unauthenticated());
        mockMvc.perform(get("/js/bootstrap.js")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andExpect(unauthenticated());
        mockMvc.perform(get("/jquery/index.html")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andExpect(unauthenticated());
    }

    @Test
    public void naoDeveConseguirRealizarLoginInvalido() throws Exception {
        verificaSeNaoEstaLogado();
        mockMvc.perform(login().user("noPitanga").password("pitanga"))
            .andExpect(unauthenticated())
            .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    public void deveConseguiRealizarLoginUsuarioPitanga() throws Exception {
        verificaSeNaoEstaLogado();
        mockMvc.perform(login().user("pitanga").password("pitanga"))
            .andExpect(authenticated())
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/"))
            .andDo(print());
    }

    private SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login() {
        return SecurityMockMvcRequestBuilders
            .formLogin("/login")
            .userParameter(SegurancaConfig.PARAMETRO_USUARIO)
            .passwordParam(SegurancaConfig.PARAMETRO_SENHA);
    }

    private void verificaSeNaoEstaLogado() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(redirectedUrlPattern("**/login"))
            .andExpect(unauthenticated());
    }

}
