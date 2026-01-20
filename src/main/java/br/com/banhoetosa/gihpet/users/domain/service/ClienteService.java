package br.com.banhoetosa.gihpet.users.domain.service;

import br.com.banhoetosa.gihpet.users.domain.models.Cliente;
import br.com.banhoetosa.gihpet.users.domain.entity.Endereco;
import br.com.banhoetosa.gihpet.pets.domain.entity.Pet;
import br.com.banhoetosa.gihpet.users.domain.models.Usuario;
import br.com.banhoetosa.gihpet.users.domain.enums.StatusCliente;
import br.com.banhoetosa.gihpet.users.repository.ClienteRepository;
import br.com.banhoetosa.gihpet.users.repository.EnderecoRepository;
import br.com.banhoetosa.gihpet.pets.repository.PetRepository;
import br.com.banhoetosa.gihpet.common.security.SecurityService;
import br.com.banhoetosa.gihpet.users.domain.validator.ClienteValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static br.com.banhoetosa.gihpet.users.repository.specs.ClienteSpecs.*;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final PetRepository petRepository;
    private final ClienteValidator clienteValidator;
    private final SecurityService securityService;

    public ClienteService(
            ClienteRepository clienteRepository,
            EnderecoRepository enderecoRepository, PetRepository petRepository,
            ClienteValidator clienteValidator,
            SecurityService securityService
    ) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.petRepository = petRepository;
        this.clienteValidator = clienteValidator;
        this.securityService = securityService;
    }

    /**
     * END-POINT para salvar cliente
     */
    @Transactional
    public Cliente salvar(Cliente cliente) {
        clienteValidator.validarNovoCliente(cliente);
        Usuario usuario = securityService.obterUsuarioLogado();
        cliente.setUsuario(usuario);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        if (cliente.getEnderecos() != null && !cliente.getEnderecos().isEmpty()) {
            for (Endereco endereco : cliente.getEnderecos()) {
                endereco.setCliente(clienteSalvo);
                enderecoRepository.save(endereco);
            }
        }

        if (cliente.getPets() != null && !cliente.getPets().isEmpty()){
            for (Pet pet : cliente.getPets()){
                pet.setCliente(clienteSalvo);
                petRepository.save(pet);
            }
        }
        return clienteSalvo;
    }

    /**
     * END-POINT para buscar cliente via ID
     */
    public Optional<Cliente> buscarPorId(UUID id){
        return clienteRepository.findById(id);
    }

    /**
     * END-POINT para buscar cliente em geral!
     * Podendo buscar com filtro.
     */
    public Page<Cliente> buscar(
            String nome,
            String logradouro,
            String bairro,
            String cidade,
            String nomePet,
            Integer pagina,
            Integer tamanhoPagina) {

        Specification<Cliente> specs =
                (root, query, cb) -> cb.conjunction();
        if (nome != null){
            specs = specs.and(nomeLike(nome));
        }
        if (logradouro != null){
            specs = specs.and(logradouroLike(logradouro));
        }
        if (bairro != null){
            specs = specs.and(bairroLike(bairro));
        }
        if (cidade != null){
            specs = specs.and(cidadeLike(cidade));
        }
        if (nomePet != null ){
            specs = specs.and(nomePetLike(nomePet));
        }
        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return clienteRepository.findAll(specs, pageRequest);
    }

    /**
     * END-POINT para atualizar informações do cliente
     */
    @Transactional
    public Cliente atualizarCliente(Cliente cliente) {
        if (cliente.getId() == null) {
            throw new IllegalArgumentException("Para atualizar é necessário que o cliente esteja cadastrado no sistema!");
        }

        Cliente clienteExistente = clienteRepository.findById(cliente.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));

        if (cliente.getNome() != null)
            clienteExistente.setNome(cliente.getNome());
        if (cliente.getCpf() != null)
            clienteExistente.setCpf(cliente.getCpf());
        if (cliente.getRg() != null)
            clienteExistente.setRg(cliente.getRg());
        if (cliente.getTelefone() != null)
            clienteExistente.setTelefone(cliente.getTelefone());
        if (cliente.getEmail() != null)
            clienteExistente.setEmail(cliente.getEmail());
        if (cliente.getDataNascimento() != null)
            clienteExistente.setDataNascimento(cliente.getDataNascimento());

        clienteValidator.validarAtualizacao(clienteExistente);

        return clienteRepository.save(clienteExistente);
    }

    /**
     * END-POINT para reativar cliente
     */
    @Transactional
    public void reativarCliente(UUID id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));

        cliente.setStatusCliente(StatusCliente.ATIVO);

        clienteRepository.save(cliente);
    }

    /**
     * END-POINT para desativar cliente
     */
    @Transactional
    public void desativarCliente(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));

        cliente.setStatusCliente(StatusCliente.INATIVO);

        clienteRepository.save(cliente);
    }

    /**
     * END-POINT para remover endereço do cliente
     */
    @Transactional
    public void removerEnderecoDoCliente(UUID idCliente, UUID idEndereco) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));

        Endereco endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado!"));

        cliente.getEnderecos().remove(endereco);

        endereco.setCliente(null);

        clienteRepository.save(cliente);
    }
}