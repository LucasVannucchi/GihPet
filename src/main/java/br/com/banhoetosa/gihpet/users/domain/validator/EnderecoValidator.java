package br.com.banhoetosa.gihpet.users.domain.validator;

import br.com.banhoetosa.gihpet.users.domain.entity.Endereco;
import br.com.banhoetosa.gihpet.users.repository.EnderecoRepository;
import org.springframework.stereotype.Component;

@Component
public class EnderecoValidator {

    private final EnderecoRepository enderecoRepository;

    public EnderecoValidator(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public void validarNovoEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo!");
        }
        if (endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty()) {
            throw new IllegalArgumentException("Logradouro é obrigatório!");
        }
        if (endereco.getCep() == null || endereco.getCep().isEmpty()) {
            throw new IllegalArgumentException("CEP é obrigatório!");
        }
    }

    public void validarAtualizacao(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo!");
        }
        if (endereco.getId() == null) {
            throw new IllegalArgumentException("ID do endereço é obrigatório para atualização!");
        }
    }
}