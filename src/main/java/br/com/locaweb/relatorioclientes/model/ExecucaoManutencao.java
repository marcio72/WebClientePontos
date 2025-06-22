package br.com.locaweb.relatorioclientes.model;

import jakarta.persistence.*;
import lombok.*;


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
    
 // Troque isso:
    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String descricao;

    // Por isso:
    //@Column(name = "observacoes", columnDefinition = "TEXT")
    //private String descricao;


    // Aqui você pode renomear para "descricao" se quiser padronizar com o DTO
    //@Column(name = "descricao", columnDefinition = "TEXT")
    //private String descricao;

    @Column(name = "data_execucao")
    private LocalDate dataExecucao;

    @ManyToOne
    @JoinColumn(name = "solicitacao_id")
    private SolicitacaoManutencao solicitacao;

    @OneToOne
    @JoinColumn(name = "problema_id")
    private ProblemaMaquina problema;
}
