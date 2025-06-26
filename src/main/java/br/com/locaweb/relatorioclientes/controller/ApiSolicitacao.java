package br.com.locaweb.relatorioclientes.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.locaweb.relatorioclientes.DTO.ExecucaoDTO;
import br.com.locaweb.relatorioclientes.model.ExecucaoManutencao;
import br.com.locaweb.relatorioclientes.repository.ExecucaoRepository;
import br.com.locaweb.relatorioclientes.repository.SolicitacaoManutencaoRepository;

@Controller
public class ApiSolicitacao {

    @Autowired
    private ExecucaoRepository execucaoRepository;

    @Autowired
    private SolicitacaoManutencaoRepository solicitacaoRepository;

    @GetMapping("/relatorioExecucoes")
    public String listarComPaginacao(Model model,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "7") int size,
                                     @RequestParam(required = false) String busca,
                                     @RequestParam(required = false) String pdf) {

        List<ExecucaoDTO> todos = execucaoRepository.findAllWithCliente()
            .stream()
            .sorted((a, b) -> Long.compare(b.getId(), a.getId()))
            .map(exec -> {
                ExecucaoDTO dto = new ExecucaoDTO();
                dto.setId(exec.getId());
                dto.setTecnico(exec.getTecnico());
                dto.setDescricao(exec.getDescricao());
                dto.setDataExecucao(exec.getDataExecucao());
                dto.setPdfGerado(exec.isPdfGerado());

                if (exec.getSolicitacaoManutencao() != null && exec.getSolicitacaoManutencao().getCliente() != null) {
                    dto.setNomeCliente(exec.getSolicitacaoManutencao().getCliente().getNomCliente());
                } else {
                    dto.setNomeCliente("Desconhecido");
                }

                if (exec.getProblema() != null) {
                    dto.setDescricaoProblema(exec.getProblema().getDescricao());
                    if (exec.getProblema().getMaquina() != null) {
                        dto.setNomeMaquina(exec.getProblema().getMaquina().getNom_maq());
                    } else {
                        dto.setNomeMaquina("Máquina desconhecida");
                    }
                } else {
                    dto.setDescricaoProblema("Sem problema registrado");
                    dto.setNomeMaquina("Máquina não informada");
                }

                return dto;
            })
            .collect(Collectors.toList());

        // Filtro por busca (cliente ou técnico)
        if (busca != null && !busca.trim().isEmpty()) {
            String termo = busca.trim().toLowerCase();
            todos = todos.stream()
                .filter(dto -> (dto.getNomeCliente() != null && dto.getNomeCliente().toLowerCase().contains(termo)) ||
                               (dto.getTecnico() != null && dto.getTecnico().toLowerCase().contains(termo)))
                .collect(Collectors.toList());
        }

        // Filtro por pdfGerado
        if ("true".equalsIgnoreCase(pdf)) {
            todos = todos.stream()
                .filter(ExecucaoDTO::isPdfGerado)
                .collect(Collectors.toList());
        } else if ("false".equalsIgnoreCase(pdf)) {
            todos = todos.stream()
                .filter(dto -> !dto.isPdfGerado())
                .collect(Collectors.toList());
        }

        int total = todos.size();
        int fromIndex = Math.min(page * size, total);
        int toIndex = Math.min(fromIndex + size, total);
        List<ExecucaoDTO> pagina = todos.subList(fromIndex, toIndex);

        model.addAttribute("execucoes", pagina);
        model.addAttribute("paginaAtual", page);
        model.addAttribute("totalPaginas", (int) Math.ceil((double) total / size));
        model.addAttribute("busca", busca);
        model.addAttribute("pdf", pdf);

        return "relatorioExecucoes";
    }
}
