package br.com.banhoetosa.gihpet.dto.servico;

import br.com.banhoetosa.gihpet.dto.dataAgendamento.DataAgendamentoResponse;
import br.com.banhoetosa.gihpet.enums.pagamento.StatusPagamento;
import br.com.banhoetosa.gihpet.enums.pagamento.TipoPagamento;
import br.com.banhoetosa.gihpet.enums.servico.HorarioTaxiDog;
import br.com.banhoetosa.gihpet.enums.servico.StatusServico;
import br.com.banhoetosa.gihpet.enums.servico.TipoServico;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ServicoResponse(
        UUID id,
        TipoServico tipoServico,
        BigDecimal preco,
        TipoPagamento tipoPagamento,
        StatusPagamento statusPagamento,
        String taxiDog,
        HorarioTaxiDog horarioTaxiDog,
        String pacote,
        StatusServico statusServico,
        String observacoes,
        DataAgendamentoResponse dataAgendamento,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
        LocalDateTime dataCadastro,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
        LocalDateTime dataAtualizacao
) {}