package br.com.locaweb.relatorioclientes.DTO;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter

public class ExecucaoRequestDTO {
    private Long problemaId;
    private Long solicitacaoId;
    private LocalDate dataExecucao;
    private String tecnico;
    private String descricao;

    // Getters e setters
}

