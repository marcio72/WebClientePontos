package br.com.locaweb.relatorioclientes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public String exibirMenu() {
        return "menu"; // Isso renderiza o arquivo src/main/resources/templates/menu.html
    }
}
