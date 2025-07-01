package br.com.locaweb.relatorioclientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.locaweb.relatorioclientes.model.ExecucaoManutencao;
import br.com.locaweb.relatorioclientes.model.SolicitacaoManutencao;
import br.com.locaweb.relatorioclientes.repository.ExecucaoRepository;
import br.com.locaweb.relatorioclientes.repository.SolicitacaoRepository;


@Controller
public class SolicitacaoWebController {
	@Autowired
    private SolicitacaoRepository solicitacaoRepository;
    @Autowired
    private ExecucaoRepository execucaoRepository;
    
    
    @GetMapping("/solicitacoes_abertas")
    public String listarSolicitacoesAbertas(Model model) {
        List<SolicitacaoManutencao> abertas = solicitacaoRepository.findByStatusTrueOrderByIdDesc();
        model.addAttribute("solicitacoes", abertas);
        return "solicitacoes_abertas";
    }

   /* @GetMapping("/relatorio_execucoes")
    public String listarSolicitacoesComExecucao(Model model) {
        List<ExecucaoManutencao> execucoes = (List<ExecucaoManutencao>) execucaoRepository.findAll();
        model.addAttribute("execucoes", execucoes);
        return "relatorio_execucoes";
    }*/
}
