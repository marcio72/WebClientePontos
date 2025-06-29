package br.com.locaweb.relatorioclientes.DTO;

import br.com.locaweb.relatorioclientes.model.Cliente;
import java.time.LocalDate;

public class InstalacaoRequestDTO {

    private Cliente cliente;
    private String descricaoInstalacao;
    private LocalDate dataSolicitacao;

    // Getters e Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescricaoInstalacao() {
        return descricaoInstalacao;
    }

    public void setDescricaoInstalacao(String descricaoInstalacao) {
        this.descricaoInstalacao = descricaoInstalacao;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
}
