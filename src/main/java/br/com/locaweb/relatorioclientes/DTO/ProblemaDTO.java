package br.com.locaweb.relatorioclientes.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemaDTO {
	 private Long idProblema; // novo campo!
    private Long numeroMaquina;
    private String maquina;
    private String descricao;
}

