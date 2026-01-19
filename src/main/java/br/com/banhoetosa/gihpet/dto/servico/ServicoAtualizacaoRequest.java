package br.com.banhoetosa.gihpet.dto.servico;

import br.com.banhoetosa.gihpet.dto.dataAgendamento.DataAgendamentoRequest;
import br.com.banhoetosa.gihpet.enums.pagamento.StatusPagamento;
import br.com.banhoetosa.gihpet.enums.pagamento.TipoPagamento;
import br.com.banhoetosa.gihpet.enums.servico.HorarioTaxiDog;
import br.com.banhoetosa.gihpet.enums.servico.StatusServico;
import br.com.banhoetosa.gihpet.enums.servico.TipoServico;

import java.math.BigDecimal;
import java.util.UUID;

public record ServicoAtualizacaoRequest(
        TipoServico tipoServico,
        TipoPagamento tipoPagamento,
        StatusPagamento statusPagamento,
        String taxiDog,
        HorarioTaxiDog horarioTaxiDog,
        String pacote,
        StatusServico statusServico,
        String observacoes,
        DataAgendamentoRequest dataAgendamento
) {}
