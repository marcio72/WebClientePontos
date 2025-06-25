package br.com.locaweb.relatorioclientes.DTO;

import java.time.LocalDate;

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
    private LocalDate DataExecucao;
    private String tecnico;
         
}
