package br.com.banhoetosa.gihpet.usuarios.domain.dto.usuario;

import br.com.banhoetosa.gihpet.usuarios.domain.enums.TipoDeAcesso;

import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String login,
        TipoDeAcesso roles
) {
}
