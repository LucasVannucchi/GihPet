package br.com.banhoetosa.gihpet.users.domain.service;

import br.com.banhoetosa.gihpet.users.domain.models.Cliente;
import br.com.banhoetosa.gihpet.users.domain.entity.Endereco;
import br.com.banhoetosa.gihpet.users.domain.models.Usuario;
import br.com.banhoetosa.gihpet.users.repository.ClienteRepository;
import br.com.banhoetosa.gihpet.users.repository.EnderecoRepository;
import br.com.banhoetosa.gihpet.common.security.SecurityService;
import br.com.banhoetosa.gihpet.users.domain.validator.EnderecoValidator;
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

    public Optional<Endereco> buscarPorId(UUID id){
        return enderecoRepository.findById(id);
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
        if (endereco.getBairro() != null)
            enderecoExistente.setBairro(endereco.getBairro());
        if (endereco.getCidade() != null)
            enderecoExistente.setCidade(endereco.getCidade());
        if (endereco.getEstado() != null)
            enderecoExistente.setEstado(endereco.getEstado());
        if (endereco.getCep() != null)
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
