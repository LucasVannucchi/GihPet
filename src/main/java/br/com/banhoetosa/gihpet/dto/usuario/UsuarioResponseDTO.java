package br.com.banhoetosa.gihpet.dto.usuario;

import br.com.banhoetosa.gihpet.enums.TipoDeAcesso;

import java.util.List;
import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String login,
        TipoDeAcesso roles
) {
}
