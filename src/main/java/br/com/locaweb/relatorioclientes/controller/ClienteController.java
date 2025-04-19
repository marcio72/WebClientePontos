package br.com.locaweb.relatorioclientes.controller;

import br.com.locaweb.relatorioclientes.model.Cliente;
import br.com.locaweb.relatorioclientes.service.ClienteService;
import br.com.locaweb.relatorioclientes.util.ConvertRegiao; // Adicionado
import br.com.locaweb.relatorioclientes.util.ConvertRegiao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/relatorio")
    public String mostrarRelatorio(Model model) {
        List<Cliente> clientes = clienteService.getClientesAtivos();

        // Contagem por bairro
        Map<String, Long> pontosPorBairro = clientes.stream()
                .collect(Collectors.groupingBy(Cliente::getBairro, Collectors.counting()));

        // Contagem por nome da região (usando ConvertRegiao)
        Map<String, Long> pontosPorRegiao = clientes.stream()
        	    .collect(Collectors.groupingBy(
        	        c -> ConvertRegiao.exibirNome(c.getRegiao()),
        	        Collectors.counting()
        	    ));

        model.addAttribute("clientes", clientes);
        model.addAttribute("pontosPorBairro", pontosPorBairro);
        model.addAttribute("pontosPorRegiao", pontosPorRegiao);
        model.addAttribute("total", clientes.size());

        return "relatorio";
    }
}
