package br.com.banhoetosa.gihpet.pets.domain.dto.pet;

import br.com.banhoetosa.gihpet.pets.domain.enums.pet.PorteAnimal;
import br.com.banhoetosa.gihpet.pets.domain.enums.pet.RacaDoAnimal;
import br.com.banhoetosa.gihpet.pets.domain.enums.pet.TipoAnimal;

public record PetAtualizacaoRequest(
        String nomePet,
        TipoAnimal tipoAnimal,
        RacaDoAnimal racaDoAnimal,
        String cor,
        PorteAnimal porteAnimal,
        Double peso,
        String possuiAlergia,
        String observacoes
) {
}
