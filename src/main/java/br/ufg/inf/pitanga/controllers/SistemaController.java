package br.ufg.inf.pitanga.controllers;

import br.ufg.inf.pitanga.entidades.Cliente;
import br.ufg.inf.pitanga.interfaces.dao.InterfaceClienteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Transactional
@Controller
public class SistemaController {

    @Autowired
    private InterfaceClienteDao clienteDao;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("cadastrarCliente")
    public String cadastrarCliente(@Valid Cliente umCliente,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "cadastro";
        }
        clienteDao.adicionarCliente(umCliente);
        return "cadastroSucesso";
    }

    @RequestMapping("cadastro")
    public String retornaPaginaCadastro() {
        return "cadastro";
    }

    /**
     * @return caso a requisicao n encontre pagina retorna uma pagina de 404
     * para o usuario
     */
    @RequestMapping("notFound")
    public String retornarPagina404() {
        return "notFound";
    }
}
