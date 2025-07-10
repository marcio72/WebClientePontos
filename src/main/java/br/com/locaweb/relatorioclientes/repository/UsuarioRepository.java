package br.com.locaweb.relatorioclientes.repository;

import br.com.locaweb.relatorioclientes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
	Usuario findByUsernameAndSenha(String username, String senha);
}