package br.com.banhoetosa.gihpet.dto.servico;

import br.com.banhoetosa.gihpet.dto.dataAgendamento.DataAgendamentoRequest;

public record ServicoComAgendamentoRequest(
        ServicoRequest servico,
        DataAgendamentoRequest agendamento
) {}
