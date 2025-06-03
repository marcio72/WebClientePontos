package br.com.locaweb.relatorioclientes.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.locaweb.relatorioclientes.model.ExecucaoManutencao;
import br.com.locaweb.relatorioclientes.model.SolicitacaoManutencao;
import br.com.locaweb.relatorioclientes.repository.ExecucaoManutencaoRepository;
import br.com.locaweb.relatorioclientes.repository.SolicitacaoManutencaoRepository;
//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;



@Controller
@RequestMapping("/execucao")
public class ExecucaoManutencaoController {
	
	    @Autowired
	    private ExecucaoManutencaoRepository execucaoRepo;

	    @Autowired
	    private SolicitacaoManutencaoRepository solicitacaoRepo;

	   /* @GetMapping("/nova/{idSolicitacao}")
	    public String novaExecucao1(@PathVariable Long idSolicitacao, Model model) {
	        SolicitacaoManutencao solicitacao = solicitacaoRepo.findById(idSolicitacao).orElseThrow();
	        ExecucaoManutencao execucao = new ExecucaoManutencao();
	        execucao.setSolicitacao(solicitacao);
	        model.addAttribute("execucao", execucao);
	        return "form_execucao";
	    }*/

	    @PostMapping("/salvar")
	    public String salvar(@ModelAttribute ExecucaoManutencao execucao) {
	        execucao.setDataExecucao(LocalDate.now());

	        // Atualiza status da solicitação para 'false' (resolvida)
	        SolicitacaoManutencao solicitacao = execucao.getSolicitacao();
	        solicitacao.setStatus(false);
	        solicitacaoRepo.save(solicitacao);

	        execucaoRepo.save(execucao);
	        return "redirect:/solicitacoes"; // ou outra tela de confirmação
	    }
	    
	    @GetMapping("/nova/{idSolicitacao}")
	    public String novaExecucao(@PathVariable Long idSolicitacao, Model model) {
	        SolicitacaoManutencao solicitacao = solicitacaoRepo.findById(idSolicitacao).orElseThrow();

	        // Força o carregamento do cliente
	        solicitacao.getCliente().getNomCliente();

	        ExecucaoManutencao execucao = new ExecucaoManutencao();
	        execucao.setSolicitacao(solicitacao);

	        model.addAttribute("execucao", execucao);
	        return "form_execucao";
	    }


	    
	}

