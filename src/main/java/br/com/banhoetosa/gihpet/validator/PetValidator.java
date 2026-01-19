package br.com.banhoetosa.gihpet.validator;

import br.com.banhoetosa.gihpet.entity.Pet;
import br.com.banhoetosa.gihpet.exceptions.RegistroDuplicadoException;
import br.com.banhoetosa.gihpet.repository.PetRepository;
import org.springframework.stereotype.Component;

@Component
public class PetValidator {

    private final PetRepository petRepository;

    public PetValidator(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void validarNovoPet(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("Pet não pode ser nulo!");
        }

        aplicarValoresPadrao(pet);

        if (existePetCadastrado(pet)) {
            throw new RegistroDuplicadoException("Pet já cadastrado!");
        }
    }

    public void aplicarValoresPadrao(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("Pet não pode ser nulo!");
        }
        if (pet.getTipo() == null) {
            pet.setTipo(pet.getTipo().getTipoPadrao());
        }
        if (pet.getRaca() == null) {
            pet.setRaca(pet.getRaca().getRacaPadrao());
        }
        if (pet.getPorte() == null) {
            pet.setPorte(pet.getPorte().getPortePadrao());
        }
        if (pet.getPossuiAlergia() == null) {
            pet.setPossuiAlergia(Boolean.FALSE);
        }
        if (pet.getObservacoes() == null){
            pet.setObservacoes(pet.getObservacoes());
        }
    }

    private boolean existePetCadastrado(Pet pet) {
        String logradouro = null;

        if (pet.getCliente() != null
                && pet.getCliente().getEnderecos() != null
                && !pet.getCliente().getEnderecos().isEmpty()) {
            logradouro = pet.getCliente().getEnderecos().get(0).getLogradouro();
        }

        return petRepository.findByNomeClienteAndLogradouro(
                pet.getNomePet(),
                pet.getCliente().getNome(),
                logradouro
        ).isPresent();
    }

}
