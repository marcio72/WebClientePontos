package br.com.locaweb.relatorioclientes.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.locaweb.relatorioclientes.model.Cliente;
import lombok.*;

@Getter
@Setter
public class ExecucaoDTO {
    private Long id;
    private String nomeCliente;
    private String nomeMaquina; 
    private String descricaoProblema;
    private String descricao;
    private LocalDateTime DataExecucao;
    private Double valor;
    private String tecnico;
    private boolean pdfGerado;

         
}
