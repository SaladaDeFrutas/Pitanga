package br.ufg.inf.pitanga.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class FuncionarioControllerTest {

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
    public void deveRedirecionarParaPaginaIndexFuncionarios() throws Exception {
        mockMvc.perform(get("/indexFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaCadastrarFilmeFuncionarios() throws Exception {
        mockMvc.perform(get("/cadastrarFilmeFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaCadastrarPecaFuncionarios() throws Exception {
        mockMvc.perform(get("/cadastrarPecaFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaGerenciarAtracoesFuncionarios() throws Exception {
        mockMvc.perform(get("/gerenciarAtracoesFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaGerenciarSessoesFuncionarios() throws Exception {
        mockMvc.perform(get("/gerenciarSessoesFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaGerenciarSalasFuncionarios() throws Exception {
        mockMvc.perform(get("/gerenciarSalasFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaCadastroAtracoesFuncionarios() throws Exception {
        mockMvc.perform(get("/cadastroAtracoesFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaCadastroFilmeFuncionarios() throws Exception {
        mockMvc.perform(get("/cadastroFilmeFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaCadastroPecaFuncionarios() throws Exception {
        mockMvc.perform(get("/cadastroPecaFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaCadastroSessoesFuncionarios() throws Exception {
        mockMvc.perform(get("/cadastroSessoesFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaCadastroSalasFuncionarios() throws Exception {
        mockMvc.perform(get("/cadastroSalasFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaGerenciarTipoIngressoFuncionarios() throws Exception {
        mockMvc.perform(get("/gerenciarTipoIngressoFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaCadastroTipoIngressoFuncionarios() throws Exception {
        mockMvc.perform(get("/cadastroTipoIngressoFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaMostrarAtracoesFuncionarios() throws Exception {
        mockMvc.perform(get("/mostrarAtracoesFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaMostrarSessoesFuncionarios() throws Exception {
        mockMvc.perform(get("/mostrarSessoesFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaCadastroSessoesFilmeFuncionarios() throws Exception {
        mockMvc.perform(get("/cadastroSessoesFilmeFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaCadastroSessoesPecaFuncionarios() throws Exception {
        mockMvc.perform(get("/cadastroSessoesPecaFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaAlteracaoTipoIngressoFuncionarios() throws Exception {
        mockMvc.perform(get("/alteracaoTipoIngressoFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaAlterarPecaFuncionarios() throws Exception {
        mockMvc.perform(get("/alterarPecaFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaAlterarTipoIngressoFuncionarios() throws Exception {
        mockMvc.perform(get("/alterarTipoIngressoFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaMostrarTipoIngressoFuncionarios() throws Exception {
        mockMvc.perform(get("/mostrarTipoIngressoFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaCadastrarTipoIngressoFuncionarios() throws Exception {
        mockMvc.perform(get("/cadastrarTipoIngressoFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaDimensoesSalaFuncionarios() throws Exception {
        mockMvc.perform(get("/dimensoesSalaFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaMostrarSalasFuncionarios() throws Exception {
        mockMvc.perform(get("/mostrarSalasFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaCadastrarFuncionarios() throws Exception {
        mockMvc.perform(get("/cadastrarFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaMostrarFuncionarios() throws Exception {
        mockMvc.perform(get("/mostrarFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaGerenciarFuncionarios() throws Exception {
        mockMvc.perform(get("/gerenciarFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaAlteracaoFuncionarios() throws Exception {
        mockMvc.perform(get("/alteracaoFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "pitanga", password = "pitanga")
    public void deveRedirecionarParaPaginaAlterarFuncionarios() throws Exception {
        mockMvc.perform(get("/alterarFuncionarios")
            .accept(MediaType.ALL))
            .andExpect(status().isOk())
            .andDo(print());
    }
}
