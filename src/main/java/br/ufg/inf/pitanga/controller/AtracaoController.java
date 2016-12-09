package br.ufg.inf.pitanga.controller;

import br.ufg.inf.pitanga.entidades.Filme;
import br.ufg.inf.pitanga.entidades.Peca;
import br.ufg.inf.pitanga.entidades.Sessao;
import br.ufg.inf.pitanga.servicos.AtracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.InvalidParameterException;
import java.util.List;

@Controller
public class AtracaoController {

    @Autowired
    private AtracaoService atracaoService;

    private static final String MOSTRAR_FILME = "mostrarFilme";

    private static final String MOSTRAR_ATRACAO = "mostrarAtracoes";

    private static final String MOSTRAR_SESSOES_FILME = "mostrarSessoesFilme";

    private static final String MOSTRAR_SESSOES_PECA = "mostrarSessoesPeca";

    @RequestMapping(MOSTRAR_FILME)
    public String mostrarFilme(Filme filme, Model model) {
        Filme filmeEscolhido = atracaoService.buscarFilmePorId(filme.getId());
        if (filme == null) {
            throw new InvalidParameterException("Filme invalido");
        }
        model.addAttribute("filme", filmeEscolhido);
        return "informacoesFilme";
    }

    @RequestMapping(MOSTRAR_ATRACAO)
    public String retornaPaginaAtracoes(Model model) {
        List<Filme> filmes = atracaoService.listarFilmes();
        List<Peca> pecas = atracaoService.listarPecas();
        if (filmes.isEmpty()){
            throw new InvalidParameterException("Sem filmes na lista");
        }
        model.addAttribute("filmes", filmes);
        model.addAttribute("pecas", pecas);
        return "mostrarAtracoes";
    }

    @RequestMapping(MOSTRAR_SESSOES_FILME)
    public String mostrarSessoesFilme(Filme filme, Model model) {
        List<Sessao> sessoes = atracaoService.buscarPorAtracao(filme);
        if (sessoes.isEmpty()){
            throw new InvalidParameterException("Sem sessoes na lista");
        }
        model.addAttribute("sessoes", sessoes);
        model.addAttribute("filme", filme);
        return "mostrarSessoesFilme";
    }

    @RequestMapping(MOSTRAR_SESSOES_PECA)
    public String mostrarSessoesPeca(Peca peca, Model model) {
        List<Sessao> sessoes = atracaoService.buscarPorAtracao(peca);
        if (sessoes.isEmpty()){
            throw new InvalidParameterException("Sem sessoes na lista");
        }
        model.addAttribute("sessoes", sessoes);
        model.addAttribute("peca", peca);
        return "mostrarSessoesPeca";
    }
}
