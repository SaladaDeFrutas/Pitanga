package br.ufg.inf.pitanga.controller;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.servicos.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    private static final String cadastro = "cadastro";

    private static final String cadastroSucesso = "cadastroSucesso";


    /**
     * Requisição da página de cadastro.
     *
     * @return nome da página de cadastro
     */
    @RequestMapping(cadastro)
    public String retornaPaginaCadastro() {
        return cadastro;
    }

    /**
     * Cadastro de cliente.
     *
     * @param cliente Cliente a ser cadastrado.
     * @param result  Erros contidos da operação.
     * @return nome da página a ser redirecionado, tela de cadastro se caso houver erros na operação, tela de
     * sucesso de cadastro para cadastro realizado com sucesso.
     */
    @RequestMapping("cadastrarCliente")
    public String cadastrarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return cadastro;
        }

        if (clienteService.cadastrarCliente(cliente) == null) {
            return cadastro;
        }
        return cadastroSucesso;
    }

}
