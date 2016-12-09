package br.ufg.inf.pitanga.controllers;

import br.ufg.inf.pitanga.entidades.*;
import br.ufg.inf.pitanga.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Transactional
@Controller
public class FuncionarioController {

    private static final String ATRIBUTO_MODAL_FILME = "filme";
    private static final String ATRIBUTO_MODAL_FILMES = "filmes";
    private static final String ATRIBUTO_MODAL_PECA = "peca";
    private static final String ATRIBUTO_MODAL_PECAS = "pecas";
    private static final String ATRIBUTO_MODAL_SALA = "sala";
    private static final String ATRIBUTO_MODAL_SALAS = "salas";
    private static final String ATRIBUTO_MODAL_SESSAO = "sessao";
    private static final String ATRIBUTO_MODAL_SESSOES = "sessoes";
    private static final String ATRIBUTO_MODAL_FUNCIONARIO = "funcionario";
    private static final String ATRIBUTO_MODAL_FUNCIONARIOS = "funcionarios";
    private static final String ATRIBUTO_MODAL_TIPO_INGRESSO = "tipoIngresso";
    private static final String ATRIBUTO_MODAL_TIPOS_INGRESSO = "tiposIngresso";

    private static final String ARGUMENTO_URL_ID_ATRACAO = "idAtracao=";
    private static final String COMPLEMENTO_PAGINA_REDIRECT = "redirect:";

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private AtracaoRepository atracaoRepository;

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private TipoIngressoRepository tipoIngressoRepository;

    @RequestMapping(Paginas.INDEX_FUNCIONARIOS)
    public String retornaPaginaIndex() {
        return Paginas.INDEX_FUNCIONARIOS;
    }

    @RequestMapping(Paginas.CADASTRAR_FILME_FUNCIONARIOS)
    public String cadastrarFilme(@Valid Filme umFilme, BindingResult result) {
        if (result.hasErrors()) {
            return Paginas.CADASTRO_FILME;
        }
        atracaoRepository.save(umFilme);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping(Paginas.CADASTRAR_PECA_FUNCIONARIOS)
    public String cadastrarPeca(@Valid Peca umaPeca, BindingResult result) {
        if (result.hasErrors()) {
            return Paginas.CADASTRO_PECA;
        }
        atracaoRepository.save(umaPeca);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping(Paginas.GERENCIAR_ATRACOES_FUNCIONARIOS)
    public String retornaPaginaGerenciaAtracoes() {
        return Paginas.GERENCIAR_ATRACOES;
    }

    @RequestMapping(Paginas.GERENCIAR_SESSOES_FUNCIONARIOS)
    public String retornaPaginaGerenciaSessoes() {
        return Paginas.GERENCIAR_SESSOES;
    }

    @RequestMapping(Paginas.GERENCIAR_SALAS_FUNCIONARIOS)
    public String retornaPaginaGerenciaSalas() {
        return Paginas.GERENCIAR_SALAS;
    }

    @RequestMapping(Paginas.CADASTRO_ATRACOES_FUNCIONARIOS)
    public String retornaPaginaCadastroAtracoes() {
        return Paginas.CADASTRO_ATRACOES;
    }

    @RequestMapping(Paginas.CADASTRO_FILME_FUNCIONARIOS)
    public String retornaPaginaCadastroFilme() {
        return Paginas.CADASTRO_FILME;
    }

    @RequestMapping(Paginas.CADASTRO_PECA_FUNCIONARIOS)
    public String retornaPaginaCadastroPeca() {
        return Paginas.CADASTRO_PECA;
    }

    @RequestMapping(Paginas.CADASTRO_SESSOES_FUNCIONARIOS)
    public String retornaPaginaCadastroSessoes() {
        return Paginas.CADASTRO_SESSOES;
    }

    @RequestMapping(Paginas.CADASTRO_SALAS_FUNCIONARIOS)
    public String retornaPaginaCadastroSalas() {
        return Paginas.CADASTRO_SALAS;
    }

    @RequestMapping(Paginas.GERENCIAR_TIPO_INGRESSO_FUNCIONARIOS)
    public String retornaPaginaGerenciaTipoIngresso() {
        return Paginas.GERENCIAR_TIPO_INGRESSO;
    }

    @RequestMapping(Paginas.CADASTRO_TIPO_INGRESSO_FUNCIONARIOS)
    public String retornaPaginaCadastroTipoIngresso() {
        return Paginas.CADASTRO_TIPO_INGRESSO;
    }

    /**
     * @param model adiciona atributos para a pagina JSP que sera retornada
     * @return pagina JSP de atracoes com botoes de alterar e excluir
     */
    @RequestMapping(Paginas.MOSTRAR_ATRACOES_FUNCIONARIOS)
    public String retornaPaginaAtracoes(Model model) {
        Iterable<Atracao> atracoes = atracaoRepository.findAll();

        model.addAttribute(ATRIBUTO_MODAL_FILMES, atracoes);
        return Paginas.MOSTRAR_ATRACOES_FUNCIONARIOS;
    }

    /**
     * @param model adiciona atributos para a pagina JSP que sera retornada
     * @return pagina JSP de atracoes para serem selecionadas e mostradas suas respectivas sessoes
     */
    @RequestMapping(Paginas.MOSTRAR_SESSOES_FUNCIONARIOS)
    public String retornaPaginaAtracoesSessoes(Model model) {
        Iterable<Atracao> pecasBuscadas = atracaoRepository.findAll();

        model.addAttribute(ATRIBUTO_MODAL_PECAS, pecasBuscadas);
        return Paginas.ATRACOES_SESSOES_FUNCIONARIOS;
    }

    @RequestMapping(Paginas.CADASTRO_SESSOES_FILME_FUNCIONARIOS)
    public String retornaPaginaCadastroSessaoFilme(Model model) {
        Iterable<Atracao> filmesBuscados = atracaoRepository.findAll();
        Iterable<Sala> salasBuscadas = salaRepository.findAll();

        model.addAttribute(ATRIBUTO_MODAL_FILMES, filmesBuscados);
        model.addAttribute(ATRIBUTO_MODAL_SALAS, salasBuscadas);
        return Paginas.CADASTRO_SESSAO_FILME;
    }

    @RequestMapping(Paginas.CADASTRO_SESSOES_PECA_FUNCIONARIOS)
    public String retornaPaginaCadastroSessaoPeca(Model model) {
        Iterable<Atracao> pecas = atracaoRepository.findAll();

        model.addAttribute(ATRIBUTO_MODAL_PECAS, pecas);
        return Paginas.CADASTRO_SESSAO_PECA;
    }

    @RequestMapping(Paginas.SESSOES_FILME_FUNCIONARIOS)
    public String mostrarSessoesFilme(Filme filme, Model model) {
        List<Sessao> sessoesBuscadas = sessaoRepository.findByAtracao(filme);
        model.addAttribute(ATRIBUTO_MODAL_SESSOES, sessoesBuscadas);
        Atracao filmeBuscado = atracaoRepository.findOne(filme.getId());
        model.addAttribute(ATRIBUTO_MODAL_FILME, filmeBuscado);
        return Paginas.MOSTRAR_SESSOES_FILME_FUNCIONARIOS;
    }

    @RequestMapping(Paginas.SESSOES_PECA_FUNCIONARIOS)
    public String mostrarSessoesPeca(Peca peca, Model model) {
        List<Sessao> sessoesBuscadas = sessaoRepository.findByAtracao(peca);
        model.addAttribute(ATRIBUTO_MODAL_SESSOES, sessoesBuscadas);
        Atracao pecaBuscada = atracaoRepository.findOne(peca.getId());
        model.addAttribute(ATRIBUTO_MODAL_PECA, pecaBuscada);
        return Paginas.MOSTRAR_SESSOES_PECA_FUNCIONARIOS;
    }

    @RequestMapping(Paginas.ALTERACAO_FILME_FUNCIONARIOS)
    public String alterarDadosFilme(Filme filme, Model model) {
        Atracao filmeBuscado = atracaoRepository.findOne(filme.getId());
        model.addAttribute(ATRIBUTO_MODAL_FILME, filmeBuscado);
        return Paginas.ALTERACAO_FILME;
    }

    @RequestMapping(Paginas.ALTERAR_FILME_FUNCIONARIOS)
    public String alterarFilme(@Valid Filme umFilme, BindingResult result) {
        if (result.hasErrors()) {
            return Paginas.ALTERACAO_FILME;
        }
        atracaoRepository.save(umFilme);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping(Paginas.EXCLUSAO_FILME_FUNCIONARIOS)
    public String excluirDadosFilme(Filme umFilme) {
        atracaoRepository.delete(umFilme);
        return COMPLEMENTO_PAGINA_REDIRECT + Paginas.MOSTRAR_ATRACOES_FUNCIONARIOS;
    }

    @RequestMapping(Paginas.ALTERACAO_PECA_FUNCIONARIOS)
    public String alterarDadosPeca(Peca peca, Model model) {
        Atracao pecaBuscada = atracaoRepository.findOne(peca.getId());
        model.addAttribute(ATRIBUTO_MODAL_PECA, pecaBuscada);
        return Paginas.ALTERACAO_PECA;
    }

    @RequestMapping(Paginas.ALTERACAO_SESSAO_FILME_FUNCIONARIOS)
    public String alterarDadosSessaoFilme(Sessao sessao, Model model) {
        Sessao sessaoBuscada = sessaoRepository.findOne(sessao.getIdSessao());

        Iterable<Atracao> filmes = atracaoRepository.findAll();
        Iterable<Sala> salas = salaRepository.findAll();

        model.addAttribute(ATRIBUTO_MODAL_FILMES, filmes);
        model.addAttribute(ATRIBUTO_MODAL_SALAS, salas);
        model.addAttribute(ATRIBUTO_MODAL_SESSAO, sessaoBuscada);

        return Paginas.ALTERACAO_SESSAO_FILME;
    }

    @RequestMapping(Paginas.ALTERACAO_SESSAO_PECA_FUNCIONARIOS)
    public String alterarDadosSessaoPeca(Sessao sessao, Model model) {
        Sessao sessaoBuscada = sessaoRepository.findOne(sessao.getIdSessao());

        Iterable<Atracao> pecas = atracaoRepository.findAll();
        Iterable<Sala> salas = salaRepository.findAll();

        model.addAttribute(ATRIBUTO_MODAL_PECAS, pecas);
        model.addAttribute(ATRIBUTO_MODAL_SALAS, salas);
        model.addAttribute(ATRIBUTO_MODAL_SESSAO, sessaoBuscada);

        return Paginas.ALTERACAO_SESSAO_PECA;
    }

    @RequestMapping(Paginas.ALTERACAO_TIPO_INGRESSO_FUNCIONARIOS)
    public String alterarDadosPeca(TipoIngresso umTipoIngresso, Model model) {
        TipoIngresso tipoIngressoBuscado = tipoIngressoRepository.findByNome(umTipoIngresso.getNome());
        model.addAttribute(ATRIBUTO_MODAL_TIPO_INGRESSO, tipoIngressoBuscado);
        return Paginas.ALTERACAO_TIPO_INGRESSO;
    }

    @RequestMapping(Paginas.CADASTRAR_SESSAO_PECA_FUNCIONARIOS)
    public String cadastrarSessaoPeca(@Valid Sessao sessao,
                                      BindingResult result, Peca peca) {
        //criado para adicionar um objeto sem id
        Sessao umaSessao = new Sessao();

        umaSessao.setSala(salaRepository.findOne(sessao.getSala().getId()));
        umaSessao.setAtracao(atracaoRepository.findOne(peca.getId()));
        umaSessao.setData(sessao.getData());

        if (result.hasFieldErrors("data")) {
            return COMPLEMENTO_PAGINA_REDIRECT + Paginas.CADASTRO_SESSOES_PECA_FUNCIONARIOS;
        }

        Iterable<Sessao> sessoes = sessaoRepository.findAll();

        /* checa se ja existe uma sessao cadastrada com o mesmo horario 
         * para a mesma atracao e para a mesma sala*/
        for (Sessao se : sessoes) {
            if (se.getData().compareTo(umaSessao.getData()) == 0
                && umaSessao.getSala().getId().equals(se.getSala().getId())) {
                return COMPLEMENTO_PAGINA_REDIRECT + Paginas.SESSOES_PECA_FUNCIONARIOS + "?" + ARGUMENTO_URL_ID_ATRACAO
                    + umaSessao.getAtracao().getId();
            }

        }

        sessaoRepository.save(umaSessao);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping(Paginas.CADASTRAR_SESSAO_FILME_FUNCIONARIOS)
    public String cadastrarSessaoFilme(@Valid Sessao sessao,
                                       BindingResult result, Filme filme) {
        //criado para adicionar um objeto sem id
        Sessao umaSessao = new Sessao();

        umaSessao.setSala(salaRepository.findOne(sessao.getSala().getId()));
        umaSessao.setAtracao(atracaoRepository.findOne(filme.getId()));
        umaSessao.setData(sessao.getData());

        if (result.hasFieldErrors("data")) {
            return COMPLEMENTO_PAGINA_REDIRECT + Paginas.CADASTRO_SESSOES_FILME_FUNCIONARIOS;
        }

        Iterable<Sessao> sessoes = sessaoRepository.findAll();

        /* checa se ja existe uma sessao cadastrada com o mesmo horario 
         * para a mesma atracao e para a mesma sala*/
        for (Sessao se : sessoes) {
            if (se.getData().compareTo(umaSessao.getData()) == 0
                && umaSessao.getSala().getId().equals(se.getSala().getId())) {
                return COMPLEMENTO_PAGINA_REDIRECT + Paginas.SESSOES_FILME_FUNCIONARIOS + "?" + ARGUMENTO_URL_ID_ATRACAO
                    + umaSessao.getAtracao().getId();
            }

        }
        sessaoRepository.save(umaSessao);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping(Paginas.ALTERAR_PECA_FUNCIONARIOS)
    public String alterarPeca(@Valid Peca umaPeca, BindingResult result) {
        if (result.hasErrors()) {
            return Paginas.ALTERACAO_PECA;
        }
        atracaoRepository.save(umaPeca);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping(Paginas.ALTERAR_SESSAO_FILME_FUNCIONARIOS)
    public String alterarSessaoFilme(@Valid Sessao sessao, BindingResult result,
                                     Filme filme) {
        //criado para adicionar um objeto sem id
        Sessao sessaoBuscada = sessaoRepository.findOne(sessao.getIdSessao());

        Iterable<Sessao> sessoes = sessaoRepository.findAll();

        // checa se ja existe uma sessao cadastrada com o mesmo horario para a mesma atracao e para a mesma sala
        for (Sessao se : sessoes) {
            if (se.getData().compareTo(sessaoBuscada.getData()) == 0
                && sessaoBuscada.getSala().getId().equals(se.getSala().getId())) {
                return COMPLEMENTO_PAGINA_REDIRECT + Paginas.SESSOES_FILME_FUNCIONARIOS + "?" + ARGUMENTO_URL_ID_ATRACAO
                    + sessaoBuscada.getAtracao().getId();
            }

        }

        sessaoBuscada.setSala(salaRepository.findOne(sessao.getSala().getId()));
        sessaoBuscada.setAtracao(atracaoRepository.findOne(filme.getId()));

        sessaoBuscada.setData(sessao.getData());

        if (result.hasFieldErrors("data")) {
            return COMPLEMENTO_PAGINA_REDIRECT + Paginas.ALTERACAO_SESSAO_FILME_FUNCIONARIOS;
        }

        sessaoRepository.save(sessaoBuscada);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping(Paginas.ALTERAR_SESSAO_PECA_FUNCIONARIOS)
    public String alterarSessaoPeca(@Valid Sessao sessao, BindingResult result,
                                    Peca peca) {
        //criado para adicionar um objeto sem id
        Sessao sessaoBuscada = sessaoRepository.findOne(sessao.getIdSessao());
        Iterable<Sessao> sessoes = sessaoRepository.findAll();

        // checa se ja existe uma sessao cadastrada com o mesmo horario para a mesma atracao e para a mesma sala
        for (Sessao se : sessoes) {
            if (se.getData().compareTo(sessaoBuscada.getData()) == 0
                && sessaoBuscada.getSala().getId().equals(se.getSala().getId())) {
                return COMPLEMENTO_PAGINA_REDIRECT + Paginas.SESSOES_PECA_FUNCIONARIOS + "?" + ARGUMENTO_URL_ID_ATRACAO
                    + sessaoBuscada.getAtracao().getId();
            }

        }

        sessaoBuscada.setSala(salaRepository.findOne(sessao.getSala().getId()));

        sessaoBuscada.setAtracao(atracaoRepository.findOne(peca.getId()));

        sessaoBuscada.setData(sessao.getData());

        if (result.hasFieldErrors("data")) {
            return COMPLEMENTO_PAGINA_REDIRECT + Paginas.ALTERACAO_SESSAO_PECA_FUNCIONARIOS;
        }

        sessaoRepository.save(sessaoBuscada);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping(Paginas.ALTERAR_TIPO_INGRESSO_FUNCIONARIOS)
    public String alterarTipoIngresso(@Valid TipoIngresso umTipoIngresso, BindingResult result) {
        if (result.hasErrors()) {
            return Paginas.ALTERACAO_TIPO_INGRESSO;
        }
        tipoIngressoRepository.save(umTipoIngresso);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping(Paginas.EXCLUSAO_SESSAO_FUNCIONARIOS)
    public String excluirDadosSessao(Sessao sessao) {
        sessaoRepository.delete(sessao);
        return COMPLEMENTO_PAGINA_REDIRECT + Paginas.GERENCIAR_SESSOES_FUNCIONARIOS;
    }

    @RequestMapping(Paginas.EXCLUSAO_TIPO_INGRESSO_FUNCIONARIOS)
    public String excluirDadosTipoIngresso(TipoIngresso umTipoIngresso) {
        tipoIngressoRepository.delete(umTipoIngresso);
        return COMPLEMENTO_PAGINA_REDIRECT + Paginas.MOSTRAR_TIPO_INGRESSO_FUNCIONARIOS;
    }

    @RequestMapping(Paginas.EXCLUSAO_PECA_FUNCIONARIOS)
    public String excluirDadosPeca(Peca umaPeca) {
        atracaoRepository.delete(umaPeca);
        return COMPLEMENTO_PAGINA_REDIRECT + Paginas.MOSTRAR_ATRACOES_FUNCIONARIOS;
    }

    @RequestMapping(Paginas.MOSTRAR_TIPO_INGRESSO_FUNCIONARIOS)
    public String retornaPaginaTipoIngresso(Model model) {
        Iterable<TipoIngresso> tiposIngresso = tipoIngressoRepository.findAll();
        model.addAttribute(ATRIBUTO_MODAL_TIPOS_INGRESSO, tiposIngresso);
        return Paginas.MOSTRAR_TIPO_INGRESSO_FUNCIONARIOS;
    }

    @RequestMapping(Paginas.CADASTRAR_TIPO_INGRESSO_FUNCIONARIOS)
    public String cadastrarTipoIngresso(@Valid TipoIngresso umTipoIngresso, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return Paginas.CADASTRO_TIPO_INGRESSO;
        }
        tipoIngressoRepository.save(umTipoIngresso);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping(Paginas.DIMENSOES_SALA_FUNCIONARIOS)
    public String cadastroSala(@Valid Sala sala, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return Paginas.CADASTRO_SALAS;
        }
        model.addAttribute(ATRIBUTO_MODAL_SALA, sala);
        return Paginas.FORMATAR_SALA;
    }

    @RequestMapping(Paginas.CADASTRAR_SALA_FUNCIONARIOS)
    public String cadastrarSalaFuncionarios(Sala sala,
                                            @RequestParam List<String> assentos) {
        salaRepository.save(sala);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping(Paginas.MOSTRAR_SALAS_FUNCIONARIOS)
    public String retornaPaginaSalas(Model model) {
        Iterable<Sala> salas = salaRepository.findAll();
        model.addAttribute(ATRIBUTO_MODAL_SALAS, salas);
        return Paginas.MOSTRAR_SALAS_FUNCIONARIOS;
    }

    @RequestMapping(Paginas.ALTERACAO_SALA_FUNCIONARIOS)
    public String alterarDadosSala(Sala sala, Model model) {
        Sala salaBuscada = salaRepository.findOne(sala.getId());
        model.addAttribute(ATRIBUTO_MODAL_SALA, salaBuscada);
        return Paginas.ALTERACAO_SALA;
    }

    @RequestMapping(Paginas.ALTERAR_SALA_FUNCIONARIOS)
    public String alterarSala(@Valid Sala sala, BindingResult result,
                              @RequestParam List<String> assentos) {
        if (result.hasErrors()) {
            return Paginas.ALTERACAO_SALA;
        }
        salaRepository.save(sala);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping(Paginas.EXCLUSAO_SALA_FUNCIONARIOS)
    public String excluirSala(Sala sala) {
        salaRepository.delete(sala);
        return COMPLEMENTO_PAGINA_REDIRECT + Paginas.MOSTRAR_SALAS_FUNCIONARIOS;
    }

    // Ao usar @Valid, nao usar redirect
    @RequestMapping(Paginas.CADASTRAR_FUNCIONARIOS)
    public String cadastrarFuncionario(@Valid Funcionario umFuncionario,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return Paginas.CADASTRO_FUNCIONARIO;
        }
        funcionarioRepository.save(umFuncionario);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    /**
     * @return pagina de cadastro de funcionarios
     */
    @RequestMapping(Paginas.CADASTRO_FUNCIONARIOS)
    public String retornaPaginaCadastroFuncionarios() {
        return Paginas.CADASTRO_FUNCIONARIO;
    }

    @RequestMapping(Paginas.MOSTRAR_FUNCIONARIOS)
    public String retornaPaginaFuncionarios(Model model) {
        //TODO: Remover admin da listagem
        Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
        model.addAttribute(ATRIBUTO_MODAL_FUNCIONARIOS, funcionarios);
        return Paginas.MOSTRAR_FUNCIONARIOS;
    }

    @RequestMapping(Paginas.GERENCIAR_FUNCIONARIOS)
    public String retornaPaginaGerenciaFuncionarios() {
        return Paginas.GERENCIAR_FUNCIONARIOS;
    }

    @RequestMapping(Paginas.ALTERACAO_FUNCIONARIOS)
    public String alterarFuncionarios(Funcionario funcionario, Model model) {
        Funcionario funcionarioBuscado = funcionarioRepository.findByEmail(funcionario.getEmail());
        model.addAttribute(ATRIBUTO_MODAL_FUNCIONARIO, funcionarioBuscado);
        return Paginas.ALTERACAO_FUNCIONARIO;
    }

    @RequestMapping(Paginas.ALTERAR_FUNCIONARIOS)
    public String alterarDadosFuncionarios(@Valid Funcionario funcionario, BindingResult result) {
        if (result.hasErrors()) {
            return Paginas.ALTERACAO_FUNCIONARIO;
        }
        funcionarioRepository.save(funcionario);
        return Paginas.CADASTRO_RESTRITO_SUCESSO;
    }

    @RequestMapping(Paginas.EXCLUSAO_FUNCIONARIOS)
    public String excluirFuncionario(Funcionario funcionario) {
        funcionarioRepository.delete(funcionario);
        return COMPLEMENTO_PAGINA_REDIRECT + Paginas.MOSTRAR_FUNCIONARIOS;
    }
}
