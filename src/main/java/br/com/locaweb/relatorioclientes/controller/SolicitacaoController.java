package br.com.locaweb.relatorioclientes.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.locaweb.relatorioclientes.DTO.SolicitacaoDTO;
import br.com.locaweb.relatorioclientes.DTO.SolicitacaoResponseDTO;
import br.com.locaweb.relatorioclientes.model.ProblemaMaquina;
import br.com.locaweb.relatorioclientes.model.SolicitacaoManutencao;



import br.com.locaweb.relatorioclientes.repository.SolicitacaoManutencaoRepository;
import br.com.locaweb.relatorioclientes.repository.ClienteRepository;
import br.com.locaweb.relatorioclientes.repository.MaquinaRepository;


@RestController
@RequestMapping("/api/solicitacao")
public class SolicitacaoController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MaquinaRepository maquinaRepository;

    @Autowired
    private SolicitacaoManutencaoRepository solicitacaoRepo;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody SolicitacaoDTO dto) {
        SolicitacaoManutencao solicitacao = new SolicitacaoManutencao();
        solicitacao.setCliente(clienteRepository.findById(dto.getCliente()).orElseThrow());
        solicitacao.setDataSolicitacao(dto.getDataSolicitacao());
        solicitacao.setStatus(dto.getStatus());

        List<ProblemaMaquina> problemas = dto.getProblemas().stream().map(p -> {
            ProblemaMaquina problema = new ProblemaMaquina();
            problema.setDescricao(p.getDescricao());
            problema.setMaquina(maquinaRepository.findById(p.getNumeroMaquina()).orElseThrow());
            problema.setSolicitacao(solicitacao); // vínculo reverso
            return problema;
        }).collect(Collectors.toList());

        solicitacao.setProblemas(problemas);

        solicitacaoRepo.save(solicitacao);

        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<List<SolicitacaoResponseDTO>> listarSolicitacoesComProblemas() {
        List<SolicitacaoResponseDTO> resultado = solicitacaoRepo.findAll().stream().map(s -> {
            SolicitacaoResponseDTO dto = new SolicitacaoResponseDTO();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dto.setCliente(s.getCliente().getNomCliente() + " - " + s.getDataSolicitacao().format(formatter) );

            List<SolicitacaoResponseDTO.ProblemaDTO> problemas = s.getProblemas().stream().map(p -> {
                SolicitacaoResponseDTO.ProblemaDTO problemaDTO = new SolicitacaoResponseDTO.ProblemaDTO();
                problemaDTO.setMaquina(p.getMaquina().getNom_maq() + " - " + p.getMaquina().getNom_jogo()); // Verifique se existe esse método em Maquina
                problemaDTO.setDescricao(p.getDescricao());
                
                return problemaDTO;
            }).collect(Collectors.toList());

            dto.setProblemas(problemas);
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }

    
}
