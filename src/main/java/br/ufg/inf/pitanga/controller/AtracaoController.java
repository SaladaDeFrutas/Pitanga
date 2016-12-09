package br.ufg.inf.pitanga.controller;

import br.ufg.inf.pitanga.entidades.Atracao;
import br.ufg.inf.pitanga.entidades.Filme;
import br.ufg.inf.pitanga.entidades.Peca;
import br.ufg.inf.pitanga.entidades.Sessao;
import br.ufg.inf.pitanga.repository.AtracaoRepository;
import br.ufg.inf.pitanga.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class AtracaoController {

    @Autowired
    private AtracaoRepository atracaoRepository;

    @Autowired
    private SessaoRepository sessaoRepository;

    @RequestMapping("/mostrarFilme")
    public String mostrarFilme(Filme umFilme, Model model) {
        Atracao filmeEscolhido = atracaoRepository.findOne(umFilme.getId());
        model.addAttribute("filme", filmeEscolhido);
        return "informacoesFilme";
    }

    /**
     * @param model adiciona atributos para a pagina JSP que sera retornada
     * @return pagina JSP de atracoes
     */
    @RequestMapping("mostrarAtracoes")
    public String retornaPaginaAtracoes(Model model) {
        Iterable<Atracao> filmes = atracaoRepository.findAll();

        model.addAttribute("filmes", filmes);
        return "mostrarAtracoes";
    }

    /**
     * @param umFilme ID e titulo do filme
     * @param model   adiciona o ID e titulo do filme e a lista de sessoes
     * @return
     */
    @RequestMapping("mostrarSessoesFilme")
    public String mostrarSessoesFilme(Filme umFilme, Model model) {
        List<Sessao> sessoes = sessaoRepository.findByAtracao(umFilme);
        model.addAttribute("sessoes", sessoes);
        model.addAttribute("filme", umFilme);
        return "mostrarSessoesFilme";
    }

    @RequestMapping("mostrarSessoesPeca")
    public String mostrarSessoesPeca(Peca umaPeca, Model model) {
        List<Sessao> sessoes = sessaoRepository.findByAtracao(umaPeca);
        model.addAttribute("sessoes", sessoes);
        model.addAttribute("peca", umaPeca);
        return "mostrarSessoesPeca";
    }
}
