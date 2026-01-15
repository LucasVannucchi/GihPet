package br.com.banhoetosa.gihpet.dto.cliente;

import br.com.banhoetosa.gihpet.dto.endereco.EnderecoResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ClienteResponse(
        UUID id,
        String nome,
        String cpf,
        String rg,
        LocalDate dataNascimento,
        String nacionalidade,
        String telefone,
        String email,
        List<EnderecoResponse> enderecos
) {}