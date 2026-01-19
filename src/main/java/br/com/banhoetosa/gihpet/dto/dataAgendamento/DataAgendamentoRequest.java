package br.com.banhoetosa.gihpet.dto.dataAgendamento;

import br.com.banhoetosa.gihpet.enums.StatusAgendamento;
import br.com.banhoetosa.gihpet.enums.servico.TipoServico;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDateTime;

public record DataAgendamentoRequest(
        @FutureOrPresent
        LocalDateTime dataHora,
        StatusAgendamento statusAgendamento
) {
}
