package br.ufg.inf.pitanga.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SistemaController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
