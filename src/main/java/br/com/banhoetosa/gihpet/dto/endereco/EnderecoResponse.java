package br.com.banhoetosa.gihpet.dto.endereco;

import java.time.LocalDateTime;
import java.util.UUID;

public record EnderecoResponse(
        UUID id,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep,
        LocalDateTime dataCadastro,
        LocalDateTime dataAtualizacao
) {}