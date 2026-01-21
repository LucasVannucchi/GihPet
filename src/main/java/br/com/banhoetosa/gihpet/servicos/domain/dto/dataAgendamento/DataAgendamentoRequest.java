package br.com.banhoetosa.gihpet.servicos.domain.dto.dataAgendamento;

import br.com.banhoetosa.gihpet.servicos.domain.enums.servico.StatusAgendamento;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDateTime;

public record DataAgendamentoRequest(
        @FutureOrPresent
        LocalDateTime dataHora,
        StatusAgendamento statusAgendamento
) {
}
