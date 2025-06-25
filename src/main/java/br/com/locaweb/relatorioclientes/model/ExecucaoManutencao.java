package br.com.locaweb.relatorioclientes.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Getter
@Setter
@Table(name = "execucao_manutencao")


public class ExecucaoManutencao {

	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tecnico;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_execucao")
    private LocalDate dataExecucao;

    @OneToOne
    @JoinColumn(name = "solicitacao_id")
    
    private SolicitacaoManutencao solicitacaoManutencao;

    @ManyToOne
    @JoinColumn(name = "problema_id")
    @JsonBackReference
    private ProblemaMaquina problema;
}


