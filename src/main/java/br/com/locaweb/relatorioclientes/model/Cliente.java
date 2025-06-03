package br.com.locaweb.relatorioclientes.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Tbl_Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cliente")
    private Long codCliente;

    @Column(name = "nom_cliente")
    private String nomCliente;
    
    @Column(name = "log")
    private String logradouro;

    @Column(name = "tel")
    private String telefone;

    @Column(name = "bai")
    private String bairro;
    
    @Column(name = "cont")
    private String contato;

    

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Column(name = "regiao")
    private Integer regiao;

    @Column(name = "Dt_Cadastro")
    private LocalDateTime dtCadastro;

    @Column(name = "Ativo")
    private Boolean ativo;


    public Long getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

	public String getNomCliente() {
		return nomCliente;
	}

	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getBairro() {
		return bairro;
	}
	

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getRegiao() {
		return regiao;
	}

	public void setRegiao(Integer regiao) {
		this.regiao = regiao;
	}

	public LocalDateTime getDtCadastro() {
	    return dtCadastro;
	}

	public void setDtCadastro(LocalDateTime dtCadastro) {
	    this.dtCadastro = dtCadastro;
	}


	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	
    // Getters e setters
    // (Pode gerar automaticamente com a IDE)
}
