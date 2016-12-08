package br.ufg.inf.pitanga.controller;

import br.ufg.inf.pitanga.entidades.Filme;
import br.ufg.inf.pitanga.entidades.Peca;
import br.ufg.inf.pitanga.entidades.Sessao;
import br.ufg.inf.pitanga.interfaces.dao.InterfaceFilmeDao;
import br.ufg.inf.pitanga.interfaces.dao.InterfacePecaDao;
import br.ufg.inf.pitanga.interfaces.dao.InterfaceSessaoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class AtracaoController {

    @Autowired
    private InterfaceFilmeDao filmeDao;

    @Autowired
    private InterfacePecaDao pecaDao;

    @Autowired
    private InterfaceSessaoDao sessaoDao;

    @RequestMapping("/mostrarFilme")
    public String mostrarFilme(Filme umFilme, Model model) {
        Filme filmeEscolhido = filmeDao.buscarPorId(umFilme.getId());
        model.addAttribute("filme", filmeEscolhido);
        return "informacoesFilme";
    }

    @RequestMapping("mostrarAtracoes")
    public String retornaPaginaAtracoes(Model model) {
        List<Filme> filmes = filmeDao.listarFilmes();
        List<Peca> pecas = pecaDao.listarPecas();

        model.addAttribute("filmes", filmes);
        model.addAttribute("pecas", pecas);
        return "mostrarAtracoes";
    }

    @RequestMapping("mostrarSessoesFilme")
    public String mostrarSessoesFilme(Filme umFilme, Model model) {
        List<Sessao> sessoes = sessaoDao.buscarPorAtracao(umFilme);
        model.addAttribute("sessoes", sessoes);
        model.addAttribute("filme", umFilme);
        return "mostrarSessoesFilme";
    }

    @RequestMapping("mostrarSessoesPeca")
    public String mostrarSessoesPeca(Peca umaPeca, Model model) {
        List<Sessao> sessoes = sessaoDao.buscarPorAtracao(umaPeca);
        model.addAttribute("sessoes", sessoes);
        model.addAttribute("peca", umaPeca);
        return "mostrarSessoesPeca";
    }
}
