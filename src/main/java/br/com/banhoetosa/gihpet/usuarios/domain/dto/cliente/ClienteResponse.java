package br.com.banhoetosa.gihpet.usuarios.domain.dto.cliente;

import br.com.banhoetosa.gihpet.usuarios.domain.dto.endereco.EnderecoResponse;
import br.com.banhoetosa.gihpet.pets.domain.dto.pet.PetResponse;
import br.com.banhoetosa.gihpet.usuarios.domain.enums.StatusCliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
        LocalDateTime dataCadastro,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
        LocalDateTime dataAtualizacao,
        List<EnderecoResponse> enderecos,
        List<PetResponse> pets
) {}