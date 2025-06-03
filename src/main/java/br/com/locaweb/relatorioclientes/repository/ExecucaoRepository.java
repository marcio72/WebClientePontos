package br.com.locaweb.relatorioclientes.repository;

import br.com.locaweb.relatorioclientes.model.ExecucaoManutencao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecucaoRepository extends JpaRepository<ExecucaoManutencao, Long> {
}
