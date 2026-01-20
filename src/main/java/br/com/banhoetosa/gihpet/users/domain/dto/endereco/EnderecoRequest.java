package br.com.banhoetosa.gihpet.users.domain.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoRequest(

        @NotBlank(message = "Logradouro é obrigatório.")
        @Size(max = 100, message = "O logradouro deve ter no máximo 100 caracteres.")
        String logradouro,

        @Size(max = 10, message = "O número deve ter no máximo 10 caracteres.")
        String numero,

        @Size(max = 50, message = "O complemento deve ter no máximo 50 caracteres.")
        String complemento,

        @NotBlank(message = "Bairro é obrigatório.")
        @Size(max = 60, message = "O bairro deve ter no máximo 60 caracteres.")
        String bairro,

        @NotBlank(message = "Cidade é obrigatória.")
        @Size(max = 60, message = "A cidade deve ter no máximo 60 caracteres.")
        String cidade,

        @NotBlank(message = "Estado é obrigatório.")
        @Size(min = 2, max = 2, message = "O estado deve conter exatamente 2 caracteres (UF).")
        String estado,

        @NotBlank(message = "CEP é obrigatório.")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido. Exemplo: 12345-678.")
        String cep
) {}