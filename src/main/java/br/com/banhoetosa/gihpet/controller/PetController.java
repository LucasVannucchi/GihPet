package br.com.banhoetosa.gihpet.controller;

import br.com.banhoetosa.gihpet.dto.pet.PetRequest;
import br.com.banhoetosa.gihpet.dto.pet.PetResponse;
import br.com.banhoetosa.gihpet.entity.Pet;
import br.com.banhoetosa.gihpet.mapper.PetMapper;
import br.com.banhoetosa.gihpet.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/pets")
@Tag(name = "Pets", description = "Gerenciamento de Animais")
public class PetController {

    private final PetMapper petMapper;
    private final PetService petService;

    public PetController(PetMapper petMapper, PetService petService) {
        this.petMapper = petMapper;
        this.petService = petService;
    }

    @PostMapping("{idCliente}")
    @Operation(summary = "Salvar novo pet")
    public ResponseEntity<PetResponse> salvar(
            @PathVariable UUID idCliente,
            @RequestBody @Valid PetRequest dto){

        Pet pet = petMapper.toEntity(dto);
        Pet salvo = petService.salvar(idCliente, pet);
        PetResponse response = petMapper.toDTO(salvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
