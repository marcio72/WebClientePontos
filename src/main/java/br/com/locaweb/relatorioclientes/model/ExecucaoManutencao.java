package br.com.locaweb.relatorioclientes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter


@Entity
@Table(name = "execucao_manutencao")
public class ExecucaoManutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tecnico;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @Column(name = "data_execucao")
    private LocalDate dataExecucao;

    @ManyToOne
    @JoinColumn(name = "solicitacao_id")
    private SolicitacaoManutencao solicitacao;

}

