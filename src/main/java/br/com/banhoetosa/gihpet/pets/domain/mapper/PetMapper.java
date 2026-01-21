package br.com.banhoetosa.gihpet.pets.domain.mapper;

import br.com.banhoetosa.gihpet.servicos.domain.mapper.ServicoMapper;
import br.com.banhoetosa.gihpet.pets.domain.dto.pet.PetAtualizacaoRequest;
import br.com.banhoetosa.gihpet.pets.domain.dto.pet.PetRequest;
import br.com.banhoetosa.gihpet.pets.domain.dto.pet.PetResponse;
import br.com.banhoetosa.gihpet.servicos.domain.dto.servico.ServicoResponse;
import br.com.banhoetosa.gihpet.pets.domain.entity.Pet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetMapper {

    private final ServicoMapper servicoMapper;

    public PetMapper(ServicoMapper servicoMapper) {
        this.servicoMapper = servicoMapper;
    }

    private Boolean converterSimNaoParaBoolean(String valor) {
        if (valor == null) return null;
        return "Sim".equalsIgnoreCase(valor.trim());
    }

    private String converterBooleanParaSimNao(Boolean valor) {
        if (valor == null) return "Não";
        return Boolean.TRUE.equals(valor) ? "Sim" : "Não";
    }

    public Pet toEntity(PetRequest dto) {
        if (dto == null) {
            return null;
        }

        Pet pet = new Pet();
        pet.setNomePet(dto.nomePet());
        pet.setTipo(dto.tipoAnimal());
        pet.setRaca(dto.racaDoAnimal());
        pet.setCor(dto.cor());
        pet.setPorte(dto.porteAnimal());
        pet.setPeso(dto.peso());
        pet.setPossuiAlergia(converterSimNaoParaBoolean(dto.possuiAlergia()));
        pet.setObservacoes(dto.observacoes());
        return pet;
    }

    public Pet toEntity(PetAtualizacaoRequest dto) {
        if (dto == null) {
            return null;
        }

        Pet pet = new Pet();
        pet.setNomePet(dto.nomePet());
        pet.setTipo(dto.tipoAnimal());
        pet.setRaca(dto.racaDoAnimal());
        pet.setCor(dto.cor());
        pet.setPorte(dto.porteAnimal());
        pet.setPeso(dto.peso());
        pet.setPossuiAlergia(converterSimNaoParaBoolean(dto.possuiAlergia()));
        pet.setObservacoes(dto.observacoes());
        return pet;
    }

    public PetResponse toDTO(Pet pet) {
        if (pet == null) {
            return null;
        }

        List<ServicoResponse> servicos = pet.getServicos()
                .stream()
                .map(servicoMapper::toDTO)
                .collect(Collectors.toList());

        return new PetResponse(
                pet.getId(),
                pet.getNomePet(),
                pet.getTipo(),
                pet.getRaca(),
                pet.getCor(),
                pet.getPorte(),
                pet.getPeso(),
                converterBooleanParaSimNao(pet.getPossuiAlergia()),
                pet.getObservacoes()
        );
    }
}