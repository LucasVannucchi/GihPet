package br.com.banhoetosa.gihpet.services.domain.dto.servico;

import br.com.banhoetosa.gihpet.services.domain.dto.dataAgendamento.DataAgendamentoRequest;
import br.com.banhoetosa.gihpet.services.domain.enums.pagamento.StatusPagamento;
import br.com.banhoetosa.gihpet.services.domain.enums.pagamento.TipoPagamento;
import br.com.banhoetosa.gihpet.services.domain.enums.servico.HorarioTaxiDog;
import br.com.banhoetosa.gihpet.services.domain.enums.servico.StatusServico;
import br.com.banhoetosa.gihpet.services.domain.enums.servico.TipoServico;

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
