package br.com.banhoetosa.gihpet.services.domain.enums.servico;

import java.math.BigDecimal;

public enum TipoServico {

    BANHO(new BigDecimal("50.00")),
    TOSA(new BigDecimal("40.00")),
    BANHO_E_TOSA(new BigDecimal("80.00")),
    HIGIENIZACAO(new BigDecimal("30.00"));

    private final BigDecimal preco;

    TipoServico(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public static TipoServico getTipoPadrao() {
        return BANHO;
    }

    public static BigDecimal getPrecoPadrao() {
        return getTipoPadrao().getPreco();
    }
}