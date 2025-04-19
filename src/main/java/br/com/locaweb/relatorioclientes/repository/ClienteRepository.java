package br.com.locaweb.relatorioclientes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.locaweb.relatorioclientes.model.Cliente;



import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Método que encontra clientes ativos e ordena por cod_cliente e logradouro
    List<Cliente> findByAtivoOrderByCodClienteAscLogradouroAsc(Boolean ativo);
}
