package br.com.banhoetosa.gihpet.users.domain.dto.usuario;

import br.com.banhoetosa.gihpet.users.domain.enums.TipoDeAcesso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UsuarioRequestDTO(

        @NotBlank(message = "Campo obrigatorio!")
        String login,

        @NotBlank(message = "Campo obrigatorio!")
        String senha,

        @NotEmpty(message = "Campo obrigatório!")
        TipoDeAcesso roles
) {
}
