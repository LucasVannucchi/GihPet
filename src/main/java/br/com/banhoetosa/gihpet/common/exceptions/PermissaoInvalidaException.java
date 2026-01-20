package br.com.banhoetosa.gihpet.common.exceptions;

import lombok.Getter;

public class PermissaoInvalidaException extends RuntimeException{
    @Getter
    private String campo;

    public PermissaoInvalidaException(String roles, String mensagem){
        super(mensagem);
        this.campo = campo;
    }
}
