package br.ufg.inf.pitanga.controller;

import br.ufg.inf.pitanga.entidades.*;
import br.ufg.inf.pitanga.entidades.enums.TipoFuncionario;
import br.ufg.inf.pitanga.interfaces.dao.*;
import br.ufg.inf.pitanga.servicos.StringAssento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Controller
public class FuncionarioController {
    
    private final String ATRIBUTO_FILMES = "filmes";
    private final String ATRIBUTO_PECAS = "pecas";
    private final String ATRIBUTO_SALAS = "salas";

    @Autowired
    private InterfaceFilmeDao filmeDao;

    @Autowired
    private InterfaceFuncionarioDao funcionarioDao;

    @Autowired
    private InterfacePecaDao pecaDao;

    @Autowired
    private InterfaceSessaoDao sessaoDao;

    @Autowired
    private InterfaceTipoIngressoDao tipoIngressoDao;

    @Autowired
    private InterfaceSalaDao salaDao;


    @RequestMapping("indexFuncionarios")
    public String retornaPaginaIndex() {
        return "indexFuncionarios";
    }

    @RequestMapping("cadastrarFilmeFuncionarios")
    public String cadastrarFilme(@Valid Filme umFilme, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastroFilme";
        }
        filmeDao.adicionarFilme(umFilme);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping("cadastrarPecaFuncionarios")
    public String cadastrarPeca(@Valid Peca umaPeca, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastroPeca";
        }
        pecaDao.adicionarPeca(umaPeca);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping("gerenciarAtracoesFuncionarios")
    public String retornaPaginaGerenciaAtracoes() {
        return "gerenciarAtracoes";
    }

    @RequestMapping("gerenciarSessoesFuncionarios")
    public String retornaPaginaGerenciaSessoes() {
        return "gerenciarSessoes";
    }

    @RequestMapping("gerenciarSalasFuncionarios")
    public String retornaPaginaGerenciaSalas() {
        return "gerenciarSalas";
    }


    @RequestMapping("cadastroAtracoesFuncionarios")
    public String retornaPaginaCadastroAtracoes() {
        return "cadastroAtracoes";
    }

    @RequestMapping("cadastroFilmeFuncionarios")
    public String retornaPaginaCadastroFilme() {
        return "cadastroFilme";
    }

    @RequestMapping("cadastroPecaFuncionarios")
    public String retornaPaginaCadastroPeca() {
        return "cadastroPeca";
    }

    @RequestMapping("cadastroSessoesFuncionarios")
    public String retornaPaginaCadastroSessoes() {
        return "cadastroSessoes";
    }

    @RequestMapping("cadastroSalasFuncionarios")
    public String retornaPaginaCadastroSalas() {
        return "cadastroSalas";
    }

    @RequestMapping("gerenciarTipoIngressoFuncionarios")
    public String retornaPaginaGerenciaTipoIngresso() {
        return "gerenciarTipoIngresso";
    }

    @RequestMapping("cadastroTipoIngressoFuncionarios")
    public String retornaPaginaCadastroTipoIngresso() {
        return "cadastroTipoIngresso";
    }

    /**
     * @param model adiciona atributos para a pagina JSP que sera retornada
     * @return pagina JSP de atracoes com botoes de alterar e excluir
     */
    @RequestMapping("mostrarAtracoesFuncionarios")
    public String retornaPaginaAtracoes(Model model) {
        List<Filme> filmes = filmeDao.listarFilmes();
        List<Peca> pecas = pecaDao.listarPecas();

        model.addAttribute(ATRIBUTO_FILMES, filmes);
        model.addAttribute(ATRIBUTO_PECAS, pecas);
        return "mostrarAtracoesFuncionarios";
    }

    /**
     * @param model adiciona atributos para a pagina JSP que sera retornada
     * @return pagina JSP de atracoes para serem selecionadas e mostradas
     * suas respectivas sessoes
     */
    @RequestMapping("mostrarSessoesFuncionarios")
    public String retornaPaginaAtracoesSessoes(Model model) {
        List<Filme> filmes = filmeDao.listarFilmes();
        List<Peca> pecas = pecaDao.listarPecas();

        model.addAttribute(ATRIBUTO_FILMES, filmes);
        model.addAttribute(ATRIBUTO_PECAS, pecas);
        return "atracoesSessoesFuncionarios";
    }

    @RequestMapping("cadastroSessoesFilmeFuncionarios")
    public String retornaPaginaCadastroSessaoFilme(Model model) {
        List<Filme> filmes = filmeDao.listarFilmes();
        List<Sala> salas = salaDao.listarSalas();

        model.addAttribute(ATRIBUTO_FILMES, filmes);
        model.addAttribute(ATRIBUTO_SALAS, salas);
        return "cadastroSessaoFilme";
    }

    @RequestMapping("cadastroSessoesPecaFuncionarios")
    public String retornaPaginaCadastroSessaoPeca(Model model) {
        List<Peca> pecas = pecaDao.listarPecas();
        List<Sala> salas = salaDao.listarSalas();

        model.addAttribute(ATRIBUTO_PECAS, pecas);
        model.addAttribute(ATRIBUTO_SALAS, salas);
        return "cadastroSessaoPeca";
    }

    @RequestMapping("sessoesFilmeFuncionarios")
    public String mostrarSessoesFilme(Filme umFilme, Model model) {
        List<Sessao> sessoes = sessaoDao.buscarPorAtracao(umFilme);
        model.addAttribute("sessoes", sessoes);
        Filme filmeBuscado = filmeDao.buscarPorId(umFilme.getId());
        model.addAttribute("filme", filmeBuscado);
        return "mostrarSessoesFilmeFuncionarios";
    }

    @RequestMapping("sessoesPecaFuncionarios")
    public String mostrarSessoesPeca(Peca umaPeca, Model model) {
        List<Sessao> sessoes = sessaoDao.buscarPorAtracao(umaPeca);
        model.addAttribute("sessoes", sessoes);
        Peca pecaBuscada = pecaDao.buscarPorId(umaPeca.getId());
        model.addAttribute("peca", pecaBuscada);
        return "mostrarSessoesPecaFuncionarios";
    }

    @RequestMapping("alteracaoFilmeFuncionarios")
    public String alterarDadosFilme(Filme umFilme, Model model) {
        Filme filme = filmeDao.buscarPorId(umFilme.getId());
        model.addAttribute("filme", filme);
        return "alteracaoFilme";
    }

    @RequestMapping("alterarFilmeFuncionarios")
    public String alterarFilme(@Valid Filme umFilme, BindingResult result) {
        if (result.hasErrors()) {
            return "alteracaoFilme";
        }
        filmeDao.alterarFilme(umFilme);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping("exclusaoFilmeFuncionarios")
    public String excluirDadosFilme(Filme umFilme) {
        filmeDao.removerFilme(umFilme);
        return "redirect:mostrarAtracoesFuncionarios";
    }

    @RequestMapping("alteracaoPecaFuncionarios")
    public String alterarDadosPeca(Peca umaPeca, Model model) {
        Peca peca = pecaDao.buscarPorId(umaPeca.getId());
        model.addAttribute("peca", peca);
        return "alteracaoPeca";
    }

    @RequestMapping("alteracaoSessaoFilmeFuncionarios")
    public String alterarDadosSessaoFilme(Sessao sessao, Model model) {
        sessao = sessaoDao.buscarPorId(sessao.getIdSessao());

        List<Filme> filmes = filmeDao.listarFilmes();
        List<Sala> salas = salaDao.listarSalas();

        model.addAttribute(ATRIBUTO_FILMES, filmes);
        model.addAttribute(ATRIBUTO_SALAS, salas);
        model.addAttribute("sessao", sessao);

        return "alteracaoSessaoFilme";
    }

    @RequestMapping("alteracaoSessaoPecaFuncionarios")
    public String alterarDadosSessaoPeca(Sessao sessao, Model model) {
        sessao = sessaoDao.buscarPorId(sessao.getIdSessao());

        List<Peca> pecas = pecaDao.listarPecas();
        List<Sala> salas = salaDao.listarSalas();

        model.addAttribute(ATRIBUTO_PECAS, pecas);
        model.addAttribute(ATRIBUTO_SALAS, salas);
        model.addAttribute("sessao", sessao);

        return "alteracaoSessaoPeca";
    }


    @RequestMapping("alteracaoTipoIngressoFuncionarios")
    public String alterarDadosPeca(TipoIngresso umTipoIngresso, Model model) {
        umTipoIngresso = tipoIngressoDao.buscarPorNome(umTipoIngresso.getNome());
        model.addAttribute("tipoIngresso", umTipoIngresso);
        return "alteracaoTipoIngresso";
    }

    @RequestMapping("cadastrarSessaoPecaFuncionarios")
    public String cadastrarSessaoPeca(@Valid Sessao sessao,
                                      BindingResult result, Peca peca) {
        //criado para adicionar um objeto sem id
        Sessao umaSessao = new Sessao();

        umaSessao.setSala(
            salaDao.buscarPorId(sessao.getSala().getId()));
        umaSessao.setAtracao(
            pecaDao.buscarPorId(peca.getId()));
        umaSessao.setData(sessao.getData());

        umaSessao.setAssentosOcupados(" ");

        if (result.hasFieldErrors("data")) {
            return "redirect:cadastroSessoesPecaFuncionarios";
        }

        List<Sessao> sessoes = sessaoDao.listarSessoes();

		/* checa se ja existe uma sessao cadastrada com o mesmo horario 
		 * para a mesma atracao e para a mesma sala*/
        for (Sessao se : sessoes) {
            if (se.getData().compareTo(umaSessao.getData()) == 0 
                    && umaSessao.getSala().getId() == se.getSala().getId()) {
                return "redirect:sessoesPecaFuncionarios?idAtracao=" + umaSessao.getAtracao().getId();
            }

        }

        sessaoDao.adicionarSessao(umaSessao);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping("cadastrarSessaoFilmeFuncionarios")
    public String cadastrarSessaoFilme(@Valid Sessao sessao,
                                       BindingResult result, Filme filme) {
        //criado para adicionar um objeto sem id
        Sessao umaSessao = new Sessao();

        umaSessao.setSala(
            salaDao.buscarPorId(sessao.getSala().getId()));
        umaSessao.setAtracao(
            filmeDao.buscarPorId(filme.getId()));
        umaSessao.setData(sessao.getData());
        umaSessao.setAssentosOcupados(" ");

        if (result.hasFieldErrors("data")) {
            return "redirect:cadastroSessoesFilmeFuncionarios";
        }

        List<Sessao> sessoes = sessaoDao.listarSessoes();
		
		/* checa se ja existe uma sessao cadastrada com o mesmo horario 
		 * para a mesma atracao e para a mesma sala*/
        for (Sessao se : sessoes) {
            if (se.getData().compareTo(umaSessao.getData()) == 0
                    && umaSessao.getSala().getId() == se.getSala().getId()) {
                return "redirect:sessoesFilmeFuncionarios?idAtracao=" + umaSessao.getAtracao().getId();
            }

        }
        sessaoDao.adicionarSessao(umaSessao);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping("alterarPecaFuncionarios")
    public String alterarPeca(@Valid Peca umaPeca, BindingResult result) {
        if (result.hasErrors()) {
            return "alteracaoPeca";
        }
        pecaDao.alterarPeca(umaPeca);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping("alterarSessaoFilmeFuncionarios")
    public String alterarSessaoFilme(@Valid Sessao sessao, BindingResult result,
                                     Filme filme) {
        //criado para adicionar um objeto sem id
        Sessao umaSessao = sessaoDao.buscarPorId(sessao.getIdSessao());

        List<Sessao> sessoes = sessaoDao.listarSessoes();
		
        // checa se ja existe uma sessao cadastrada com o mesmo horario para a mesma atracao e para a mesma sala
        for (Sessao se : sessoes) {
            if (se.getData().compareTo(umaSessao.getData()) == 0
                    && umaSessao.getSala().getId() == se.getSala().getId()) {
                return "redirect:sessoesFilmeFuncionarios?idAtracao=" + umaSessao.getAtracao().getId();
            }

        }

        umaSessao.setSala(
            salaDao.buscarPorId(sessao.getSala().getId()));

        umaSessao.setAtracao(
            filmeDao.buscarPorId(filme.getId()));

        umaSessao.setData(sessao.getData());

        if (result.hasFieldErrors("data")) {
            return "redirect:alteracaoSessaoFilmeFuncionarios";
        }

        sessaoDao.alterarSessao(umaSessao);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping("alterarSessaoPecaFuncionarios")
    public String alterarSessaoPeca(@Valid Sessao sessao, BindingResult result,
                                    Peca peca) {
        //criado para adicionar um objeto sem id
        Sessao umaSessao = sessaoDao.buscarPorId(sessao.getIdSessao());

        List<Sessao> sessoes = sessaoDao.listarSessoes();
		
        // checa se ja existe uma sessao cadastrada com o mesmo horario para a mesma atracao e para a mesma sala
        for (Sessao se : sessoes) {
            if (se.getData().compareTo(umaSessao.getData()) == 0 
                    && umaSessao.getSala().getId() == se.getSala().getId()) {
                return "redirect:sessoesPecaFuncionarios?idAtracao=" + umaSessao.getAtracao().getId();
            }

        }

        umaSessao.setSala(
            salaDao.buscarPorId(sessao.getSala().getId()));

        umaSessao.setAtracao(
            pecaDao.buscarPorId(peca.getId()));

        umaSessao.setData(sessao.getData());

        if (result.hasFieldErrors("data")) {
            return "redirect:alteracaoSessaoPecaFuncionarios";
        }

        sessaoDao.alterarSessao(umaSessao);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping("alterarTipoIngressoFuncionarios")
    public String alterarTipoIngresso(@Valid TipoIngresso umTipoIngresso, BindingResult result) {
        if (result.hasErrors()) {
            return "alteracaoTipoIngresso";
        }
        tipoIngressoDao.alterarTipoIngresso(umTipoIngresso);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping("exclusaoSessaoFuncionarios")
    public String excluirDadosSessao(Sessao sessao) {
        sessaoDao.removerSessao(sessao);
        return "redirect:gerenciarSessoesFuncionarios";
    }

    @RequestMapping("exclusaoTipoIngressoFuncionarios")
    public String excluirDadosTipoIngresso(TipoIngresso umTipoIngresso) {
        tipoIngressoDao.removerTipoIngresso(umTipoIngresso);
        return "redirect:mostrarTipoIngressoFuncionarios";
    }

    @RequestMapping("exclusaoPecaFuncionarios")
    public String excluirDadosPeca(Peca umaPeca) {
        pecaDao.removerPeca(umaPeca);
        return "redirect:mostrarAtracoesFuncionarios";
    }

    @RequestMapping("mostrarTipoIngressoFuncionarios")
    public String retornaPaginaTipoIngresso(Model model) {
        List<TipoIngresso> tiposIngresso = tipoIngressoDao.listarTipoIngresso();
        model.addAttribute("tiposIngresso", tiposIngresso);
        return "mostrarTipoIngressoFuncionarios";
    }

    @RequestMapping("cadastrarTipoIngressoFuncionarios")
    public String cadastrarTipoIngresso(@Valid TipoIngresso umTipoIngresso, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "cadastroTipoIngresso";
        }
        tipoIngressoDao.adicionarTipoIngresso(umTipoIngresso);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping("dimensoesSalaFuncionarios")
    public String cadastroSala(@Valid Sala sala, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastroSalas";
        }
        model.addAttribute("sala", sala);
        return "formatarSala";
    }

    @RequestMapping("cadastrarSalaFuncionarios")
    public String cadastrarSalaFuncionarios(Sala sala,
                                            @RequestParam ArrayList<String> assentos) {
        String assentosInvalidos;

        if (assentos != null) {
            StringAssento stringAssento = new StringAssento();
            ArrayList<Assento> arrayAssentosInvalidos = stringAssento.converterArrayStringParaArrayAssento(assentos);
            assentosInvalidos = stringAssento.converterAssentoParaString(arrayAssentosInvalidos);
        } else
            assentosInvalidos = " ";

        System.out.println("String assentos invalidos:" + assentosInvalidos);
        salaDao.adicionarSala(sala);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping("mostrarSalasFuncionarios")
    public String retornaPaginaSalas(Model model) {
        List<Sala> salas = salaDao.listarSalas();
        model.addAttribute(ATRIBUTO_SALAS, salas);
        return "mostrarSalasFuncionarios";
    }

    @RequestMapping("alteracaoSalaFuncionarios")
    public String alterarDadosSala(Sala sala, Model model) {
        sala = salaDao.buscarPorId(sala.getId());
        model.addAttribute("sala", sala);
        return "alteracaoSala";
    }

    @RequestMapping("alterarSalaFuncionarios")
    public String alterarSala(@Valid Sala sala, BindingResult result,
                              @RequestParam ArrayList<String> assentos) {
        if (result.hasErrors()) {
            return "alteracaoSala";
        }
        String assentosInvalidos;
        if (assentos != null) {
            StringAssento stringAssento = new StringAssento();
            ArrayList<Assento> arrayAssentosInvalidos = stringAssento.converterArrayStringParaArrayAssento(assentos);
            assentosInvalidos = stringAssento.converterAssentoParaString(arrayAssentosInvalidos);
        } else
            assentosInvalidos = " ";

        salaDao.alterarSala(sala);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping("exclusaoSalaFuncionarios")
    public String excluirSala(Sala sala) {
        salaDao.removerSala(sala);
        return "redirect:mostrarSalasFuncionarios";
    }

    // Ao usar @Valid, nao usar redirect
    @RequestMapping("cadastrarFuncionarios")
    public String cadastrarFuncionario(@Valid Funcionario umFuncionario,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return "cadastroFuncionario";
        }
        funcionarioDao.adicionarFuncionario(umFuncionario);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    /**
     * @param model Usado para passar os niveis de acesso esperados para a pagina
     *              JSP
     * @return pagina de cadastro de funcionarios
     */
    @RequestMapping("cadastroFuncionarios")
    public String retornaPaginaCadastroFuncionarios() {
        return "cadastroFuncionario";
    }

    @RequestMapping("mostrarFuncionarios")
    public String retornaPaginaFuncionarios(Model model) {
        List<Funcionario> funcionarios = funcionarioDao.listarFuncionario();
        Funcionario admin = null;
        // retira o admin da lista buscada do BD para exibir apenas os que nao sao admin
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNivelAcesso() == TipoFuncionario.ADMINISTRADOR) {
                admin = funcionario;
            }
        }
        funcionarios.remove(admin);

        model.addAttribute("funcionarios", funcionarios);
        return "mostrarFuncionarios";
    }

    @RequestMapping("gerenciarFuncionarios")
    public String retornaPaginaGerenciaFuncionarios() {
        return "gerenciarFuncionarios";
    }

    @RequestMapping("alteracaoFuncionarios")
    public String alterarFuncionarios(Funcionario funcionario, Model model) {
        funcionario = funcionarioDao.buscarPorId(funcionario.getEmail());
        model.addAttribute("funcionario", funcionario);
        return "alteracaoFuncionario";
    }

    @RequestMapping("alterarFuncionarios")
    public String alterarDadosFuncionarios(@Valid Funcionario funcionario, BindingResult result) {
        if (result.hasErrors()) {
            return "alteracaoFuncionario";
        }
        funcionarioDao.alterarFuncionario(funcionario);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping("exclusaoFuncionarios")
    public String excluirFuncionario(Funcionario funcionario) {
        funcionarioDao.removerFuncionario(funcionario);
        return "redirect:mostrarFuncionarios";
    }
}
