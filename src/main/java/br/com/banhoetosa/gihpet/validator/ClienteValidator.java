package br.com.banhoetosa.gihpet.validator;

import br.com.banhoetosa.gihpet.entity.Cliente;
import br.com.banhoetosa.gihpet.exceptions.RegistroDuplicadoException;
import br.com.banhoetosa.gihpet.repository.ClienteRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteValidator {

    private final ClienteRepository repository;

    public ClienteValidator(ClienteRepository repository) {
        this.repository = repository;
    }

    public void validar(Cliente cliente) {
        if (existeClienteCadastrado(cliente)) {
            throw new RegistroDuplicadoException("Cliente já cadastrado!");
        }
    }

    public boolean existeClienteCadastrado(Cliente cliente){
        Optional<Cliente> clienteEncontrado = repository.findByNomeAndRgAndCpf(cliente.getNome(), cliente.getRg(), cliente.getCpf());

        /**
         * Se estou cadastrando um novo cliente (id == null)
         */
        if (cliente.getId() == null){
            return clienteEncontrado.isPresent();
        }

        /**
         *  Valida se é o Cliente referente ao ID que estou querendo atualizar
         */
        if (clienteEncontrado.isPresent()){
            return !cliente.getId().equals(clienteEncontrado.get().getId()) && clienteEncontrado.isPresent();
        }
        return false;
    }
}