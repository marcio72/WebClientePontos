package br.com.locaweb.relatorioclientes.controller;

import br.com.locaweb.relatorioclientes.DTO.InstalacaoRequestDTO;
import br.com.locaweb.relatorioclientes.model.SolicitacaoManutencao;
import br.com.locaweb.relatorioclientes.service.InstalacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instalacoes")
public class InstalacaoController {

    @Autowired
    private InstalacaoService instalacaoService;

    /**
     * Endpoint para cadastrar um novo cliente e criar uma solicitação de instalação
     * em uma única operação.
     */
    @PostMapping("/novo-cliente")
    public ResponseEntity<SolicitacaoManutencao> solicitarInstalacao(@RequestBody InstalacaoRequestDTO dto) {
        try {
            SolicitacaoManutencao solicitacao = instalacaoService.solicitarInstalacaoNovoCliente(dto);
            return ResponseEntity.ok(solicitacao);
        } catch (Exception e) {
            // Em um caso real, seria bom logar o erro e retornar uma mensagem mais específica
            return ResponseEntity.badRequest().body(null);
        }
    }
}
