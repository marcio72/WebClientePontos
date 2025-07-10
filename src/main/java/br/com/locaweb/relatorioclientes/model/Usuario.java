package br.com.locaweb.relatorioclientes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "usuario")

public class Usuario {

	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String username;
	    private String senha;

	    private Integer leiturista;
	    
	    @Column(name = "id_pontos")
	    private Integer idPontos;

	    // getters e setters
	}




