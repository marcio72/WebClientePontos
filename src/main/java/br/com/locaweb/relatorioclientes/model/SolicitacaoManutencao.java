package br.com.locaweb.relatorioclientes.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "solicitacao_manutencao")
@Getter
@Setter
public class SolicitacaoManutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
    

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_solicitacao")
    private LocalDate dataSolicitacao;

    @Column(name = "status")
    private Boolean status;

    
    
    @OneToMany(mappedBy = "solicitacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProblemaMaquina> problemas = new ArrayList<>();
}

