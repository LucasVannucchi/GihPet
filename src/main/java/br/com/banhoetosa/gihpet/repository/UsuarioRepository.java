package br.com.banhoetosa.gihpet.repository;

import br.com.banhoetosa.gihpet.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByLogin(String login);
}
