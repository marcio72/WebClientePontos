package br.com.locaweb.relatorioclientes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Tbl_Cliente")
public class Cliente {

    @Id
    @Column(name = "cod_cliente")
    private Integer codCliente;

    @Column(name = "nom_cliente")
    private String nomCliente;
    
    @Column(name = "log")
    private String logradouro;

    @Column(name = "tel")
    private String telefone;

    @Column(name = "bai")
    private String bairro;

    @Column(name = "regiao")
    private Integer regiao;

    @Column(name = "Dt_Cadastro")
    private String dtCadastro;

    @Column(name = "Ativo")
    private Boolean ativo;


    public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
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

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getRegiao() {
		return regiao;
	}

	public void setRegiao(Integer regiao) {
		this.regiao = regiao;
	}

	public String getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(String dtCadastro) {
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
