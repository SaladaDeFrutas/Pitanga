package br.ufg.inf.pitanga.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * @return a pagina de login do sistema
     */
    @RequestMapping(Paginas.LOGIN)
    public String retornaPaginaLogin() {
        log.debug("Página de login.");
        return Paginas.LOGIN;
    }

}
