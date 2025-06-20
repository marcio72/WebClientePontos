package br.com.locaweb.relatorioclientes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaExecucaoController {

    @GetMapping("/form_execucao")
    public String mostrarPaginaExecucao() {
        return "form_execucao";
    }
}
