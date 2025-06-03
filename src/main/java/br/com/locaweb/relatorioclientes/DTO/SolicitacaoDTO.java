package br.com.locaweb.relatorioclientes.DTO;

import java.time.LocalDate;
import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SolicitacaoDTO {
    private Long cliente;
    private LocalDate dataSolicitacao;
    private Boolean status;
    private List<ProblemaDTO> problemas;
}
