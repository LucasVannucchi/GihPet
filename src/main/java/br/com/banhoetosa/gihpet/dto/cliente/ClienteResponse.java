package br.com.banhoetosa.gihpet.dto.cliente;

import br.com.banhoetosa.gihpet.dto.endereco.EnderecoResponse;
import br.com.banhoetosa.gihpet.enums.StatusCliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ClienteResponse(
        UUID id,
        String nome,
        String cpf,
        String rg,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        String nacionalidade,
        String telefone,
        String email,
        StatusCliente statusCliente,
        List<EnderecoResponse> enderecos
) {}