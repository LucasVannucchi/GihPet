package br.com.banhoetosa.gihpet.users.repository;

import br.com.banhoetosa.gihpet.users.domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByLogin(String login);
}
