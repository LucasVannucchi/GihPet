package br.com.banhoetosa.gihpet.usuarios.domain.validator;

import br.com.banhoetosa.gihpet.usuarios.domain.models.Cliente;
import br.com.banhoetosa.gihpet.usuarios.domain.enums.StatusCliente;
import br.com.banhoetosa.gihpet.common.exceptions.RegistroDuplicadoException;
import br.com.banhoetosa.gihpet.usuarios.repository.ClienteRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteValidator {

    private final ClienteRepository clienteRepository;

    public ClienteValidator(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void validarNovoCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo!");
        }

        aplicarValoresPadrao(cliente);

        if (existeClienteCadastrado(cliente)) {
            throw new RegistroDuplicadoException("Cliente já cadastrado!");
        }
    }

    public void validarAtualizacao(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo!");
        }
        if (cliente.getId() == null) {
            throw new IllegalArgumentException("ID do cliente é obrigatório para atualização!");
        }
        if (existeClienteCadastrado(cliente)) {
            throw new RegistroDuplicadoException("Outro cliente já possui estes dados!");
        }
    }

    private void aplicarValoresPadrao(Cliente cliente) {
        if (cliente.getStatusCliente() == null) {
            cliente.setStatusCliente(StatusCliente.ATIVO);
        }
    }

    private boolean existeClienteCadastrado(Cliente cliente) {
        Optional<Cliente> clienteEncontrado =
                clienteRepository.findByNomeAndRgAndCpf(cliente.getNome(), cliente.getRg(), cliente.getCpf());
        if (cliente.getId() == null) {
            return clienteEncontrado.isPresent();
        }

        return clienteEncontrado.isPresent()
                && !cliente.getId().equals(clienteEncontrado.get().getId());
    }
}