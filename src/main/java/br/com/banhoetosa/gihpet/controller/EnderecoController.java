package br.com.banhoetosa.gihpet.controller;

import br.com.banhoetosa.gihpet.dto.endereco.EnderecoRequest;
import br.com.banhoetosa.gihpet.dto.endereco.EnderecoResponse;
import br.com.banhoetosa.gihpet.entity.Endereco;
import br.com.banhoetosa.gihpet.mapper.EnderecoMapper;
import br.com.banhoetosa.gihpet.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/enderecos")
@Tag(name = "Endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final EnderecoMapper enderecoMapper;

    public EnderecoController(EnderecoService enderecoService, EnderecoMapper enderecoMapper) {
        this.enderecoService = enderecoService;
        this.enderecoMapper = enderecoMapper;
    }

    @PostMapping("{idCliente}")
    @Operation(summary = "Salvar novo endereço vinculado a um cliente")
    public ResponseEntity<EnderecoResponse> salvar(
            @PathVariable UUID idCliente,
            @RequestBody EnderecoRequest dto) {

        Endereco endereco = enderecoMapper.toEntity(dto);
        Endereco salvo = enderecoService.salvar(idCliente, endereco);
        EnderecoResponse response = enderecoMapper.toDTO(salvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
