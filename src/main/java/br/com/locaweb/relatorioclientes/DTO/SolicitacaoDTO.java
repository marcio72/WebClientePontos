package br.com.locaweb.relatorioclientes.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SolicitacaoDTO {
    private Long cliente;
    
    private LocalDateTime dataSolicitacao;
    
    private Boolean status;
    
    private List<ProblemaDTO> problemas;
}
