package br.com.banhoetosa.gihpet.dto.usuario;

import br.com.banhoetosa.gihpet.enums.TipoDeAcesso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record UsuarioRequestDTO(

        @NotBlank(message = "Campo obrigatorio!")
        String login,

        @NotBlank(message = "Campo obrigatorio!")
        String senha,

        @NotEmpty(message = "Campo obrigatório!")
        TipoDeAcesso roles
) {
}
