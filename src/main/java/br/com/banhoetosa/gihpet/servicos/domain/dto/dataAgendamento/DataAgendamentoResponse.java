package br.com.banhoetosa.gihpet.servicos.domain.dto.dataAgendamento;

import br.com.banhoetosa.gihpet.servicos.domain.enums.servico.StatusAgendamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record DataAgendamentoResponse (
        UUID id,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
        LocalDateTime dataHora,
        StatusAgendamento statusAgendamento,
        UUID idPet,
        String nomePet,
        UUID idCliente,
        String nomeCliente

) {
}