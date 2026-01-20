package br.com.banhoetosa.gihpet.services.domain.dto.dataAgendamento;

import br.com.banhoetosa.gihpet.services.domain.enums.servico.StatusAgendamento;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDateTime;

public record DataAgendamentoRequest(
        @FutureOrPresent
        LocalDateTime dataHora,
        StatusAgendamento statusAgendamento
) {
}
