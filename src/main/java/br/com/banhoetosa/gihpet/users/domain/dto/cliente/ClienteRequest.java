package br.com.banhoetosa.gihpet.users.domain.dto.cliente;

import br.com.banhoetosa.gihpet.users.domain.dto.endereco.EnderecoRequest;
import br.com.banhoetosa.gihpet.pets.domain.dto.pet.PetRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

public record ClienteRequest(

        @NotBlank(message = "Campo obrigatório!")
        @Size(min = 2, max = 100, message = "Campo fora do padrão!")
        String nome,

        @CPF(message = "CPF inválido!")
        @NotBlank(message = "Campo obrigatório!")
        String cpf,

        @NotBlank(message = "Campo obrigatório!")
        @Size(min = 7, max = 11, message = "Campo fora do padrão!")
        String rg,

        @NotNull(message = "Campo obrigatório!")
        @Past(message = "A data de nascimento deve ser anterior à data atual!")
        LocalDate dataNascimento,

        @NotBlank(message = "Campo obrigatório!")
        @Size(min = 2, max = 50, message = "Campo fora do padrão!")
        String nacionalidade,

        @NotBlank(message = "Campo obrigatório!")
        @Pattern(
                regexp = "^\\+?\\d{0,3}?\\s?\\(?\\d{2,4}\\)?[-.\\s]?\\d{4,5}[-.\\s]?\\d{4}$",
                message = "Telefone inválido!"
        )
        String telefone,

        @Email(message = "E-mail fora do padrão!")
        @NotBlank(message = "Campo obrigatório!")
        String email,

        @NotEmpty(message = "É necessário informar ao menos um endereço!")
        @Valid
        List<EnderecoRequest> enderecos,

        @NotEmpty(message = "É necessário informar ao menos um Pet!")
        @Valid
        List<PetRequest> pets
) {}