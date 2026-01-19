package br.com.banhoetosa.gihpet.dto.pet;

import br.com.banhoetosa.gihpet.enums.pet.PorteAnimal;
import br.com.banhoetosa.gihpet.enums.pet.RacaDoAnimal;
import br.com.banhoetosa.gihpet.enums.pet.TipoAnimal;

public record PetRequest(
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
