package br.com.banhoetosa.gihpet.service;

import br.com.banhoetosa.gihpet.entity.Cliente;
import br.com.banhoetosa.gihpet.entity.Pet;
import br.com.banhoetosa.gihpet.entity.Usuario;
import br.com.banhoetosa.gihpet.repository.ClienteRepository;
import br.com.banhoetosa.gihpet.repository.PetRepository;
import br.com.banhoetosa.gihpet.security.SecurityService;
import br.com.banhoetosa.gihpet.validator.PetValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final ClienteRepository clienteRepository;
    private final PetValidator petValidator;
    private final SecurityService securityService;

    public PetService(PetRepository petRepository, ClienteRepository clienteRepository, PetValidator petValidator, SecurityService securityService) {
        this.petRepository = petRepository;
        this.clienteRepository = clienteRepository;
        this.petValidator = petValidator;
        this.securityService = securityService;
    }

    @Transactional
    public Pet salvar(UUID idCliente, Pet pet) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));

        Usuario usuario = securityService.obterUsuarioLogado();
        pet.setCliente(cliente);
        pet.setUsuario(usuario);

        petValidator.validarNovoPet(pet);

        return petRepository.save(pet);
    }
}
