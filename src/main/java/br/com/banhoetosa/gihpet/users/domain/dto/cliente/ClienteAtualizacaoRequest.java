package br.com.banhoetosa.gihpet.users.domain.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ClienteAtualizacaoRequest(

        @Size(min = 2, max = 100, message = "Campo fora do padrão!")
        String nome,

        @CPF(message = "CPF inválido!")
        String cpf,

        @Size(min = 7, max = 11, message = "Campo fora do padrão!")
        String rg,

        @Past(message = "A data de nascimento deve ser anterior à data atual!")
        LocalDate dataNascimento,

        @Size(min = 2, max = 50, message = "Campo fora do padrão!")
        String nacionalidade,

        @Pattern(
                regexp = "^\\+?\\d{0,3}?\\s?\\(?\\d{2,4}\\)?[-.\\s]?\\d{4,5}[-.\\s]?\\d{4}$",
                message = "Telefone inválido!"
        )
        String telefone,

        @Email(message = "E-mail fora do padrão!")
        String email
) {
}
