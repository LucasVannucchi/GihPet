package br.com.banhoetosa.gihpet.service;

import br.com.banhoetosa.gihpet.entity.Cliente;
import br.com.banhoetosa.gihpet.entity.Endereco;
import br.com.banhoetosa.gihpet.entity.Usuario;
import br.com.banhoetosa.gihpet.repository.ClienteRepository;
import br.com.banhoetosa.gihpet.repository.EnderecoRepository;
import br.com.banhoetosa.gihpet.security.SecurityService;
import br.com.banhoetosa.gihpet.validator.EnderecoValidator;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoValidator enderecoValidator;
    private final SecurityService securityService;

    public EnderecoService(EnderecoRepository enderecoRepository, ClienteRepository clienteRepository, EnderecoValidator enderecoValidator, SecurityService securityService) {
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
        this.enderecoValidator = enderecoValidator;
        this.securityService = securityService;
    }

    @Transactional
    public Endereco salvar(UUID idCliente, Endereco endereco) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));
        enderecoValidator.validarNovoEndereco(endereco);
        Usuario usuario = securityService.obterUsuarioLogado();
        endereco.setCliente(cliente);
        endereco.setUsuario(usuario);
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public Endereco atualizarEndereco(Endereco endereco) {
        if (endereco.getId() == null) {
            throw new IllegalArgumentException("Para atualizar é necessário que o endereço esteja cadastrado no sistema!");
        }

        Endereco enderecoExistente = enderecoRepository.findById(endereco.getId())
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado!"));

        if (endereco.getLogradouro() != null)
            enderecoExistente.setLogradouro(endereco.getLogradouro());
        if (endereco.getNumero() != null)
            enderecoExistente.setNumero(endereco.getNumero());
        if (endereco.getComplemento() != null)
            enderecoExistente.setComplemento(endereco.getComplemento());
        if (enderecoExistente.getBairro() != null)
            enderecoExistente.setBairro(endereco.getBairro());
        if (enderecoExistente.getCidade() != null)
            enderecoExistente.setCidade(endereco.getCidade());
        if (enderecoExistente.getEstado() != null)
            enderecoExistente.setEstado(endereco.getEstado());
        if (enderecoExistente.getCep() != null)
            enderecoExistente.setCep(endereco.getCep());

        enderecoValidator.validarAtualizacao(enderecoExistente);

        return enderecoRepository.save(enderecoExistente);
    }

    @Transactional
    public void deletarEndereco(UUID id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado!"));

        enderecoRepository.delete(endereco);
    }
}
