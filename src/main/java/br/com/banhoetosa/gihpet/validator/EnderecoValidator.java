package br.com.banhoetosa.gihpet.validator;

import br.com.banhoetosa.gihpet.entity.Endereco;
import br.com.banhoetosa.gihpet.repository.EnderecoRepository;
import org.springframework.stereotype.Component;

@Component
public class EnderecoValidator {

    private final EnderecoRepository enderecoRepository;

    public EnderecoValidator(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public void validarNovoEndereco(Endereco endereco){
        if (endereco == null){
            throw new IllegalArgumentException("Endereço não pode ser nulo!");
        }
    }

    public void validarAtualizacao(Endereco endereco){
        if (endereco == null){
            throw new IllegalArgumentException("Endereço não pode ser nulo!");
        }
        if (endereco.getId() == null){
            throw new IllegalArgumentException("ID do endereço é obrigatório para atualização!");
        }
    }
}
