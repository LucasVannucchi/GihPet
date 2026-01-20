package br.com.banhoetosa.gihpet.pets.domain.service;

import br.com.banhoetosa.gihpet.users.domain.models.Cliente;
import br.com.banhoetosa.gihpet.pets.domain.entity.Pet;
import br.com.banhoetosa.gihpet.users.domain.models.Usuario;
import br.com.banhoetosa.gihpet.users.repository.ClienteRepository;
import br.com.banhoetosa.gihpet.pets.repository.PetRepository;
import br.com.banhoetosa.gihpet.common.security.SecurityService;
import br.com.banhoetosa.gihpet.pets.domain.validator.PetValidator;
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
