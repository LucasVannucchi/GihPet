package br.com.banhoetosa.gihpet.service;

import br.com.banhoetosa.gihpet.entity.Cliente;
import br.com.banhoetosa.gihpet.entity.Endereco;
import br.com.banhoetosa.gihpet.entity.Usuario;
import br.com.banhoetosa.gihpet.repository.ClienteRepository;
import br.com.banhoetosa.gihpet.repository.EnderecoRepository;
import br.com.banhoetosa.gihpet.repository.PetRepository;
import br.com.banhoetosa.gihpet.security.SecurityService;
import br.com.banhoetosa.gihpet.validator.ClienteValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.banhoetosa.gihpet.repository.specs.ClienteSpecs.*;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final PetRepository petRepository;
    private final ClienteValidator validator;
    private final SecurityService securityService;

    public ClienteService(
            ClienteRepository clienteRepository,
            EnderecoRepository enderecoRepository,
            PetRepository petRepository,
            ClienteValidator validator,
            SecurityService securityService
    ) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.petRepository = petRepository;
        this.validator = validator;
        this.securityService = securityService;
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        validator.validar(cliente);
        Usuario usuario = securityService.obterUsuarioLogado();
        cliente.setUsuario(usuario);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        if (cliente.getEnderecos() != null && !cliente.getEnderecos().isEmpty()) {
            for (Endereco endereco : cliente.getEnderecos()) {
                endereco.setCliente(clienteSalvo);
                enderecoRepository.save(endereco);
            }
        }
        return clienteSalvo;
    }

    public Optional<Cliente> buscarPorId(UUID id){
        return clienteRepository.findById(id);
    }

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
}