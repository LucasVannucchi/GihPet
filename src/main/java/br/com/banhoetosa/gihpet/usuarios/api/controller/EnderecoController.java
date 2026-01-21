package br.com.banhoetosa.gihpet.usuarios.api.controller;

import br.com.banhoetosa.gihpet.usuarios.domain.dto.endereco.EnderecoAtualizacaoRequest;
import br.com.banhoetosa.gihpet.usuarios.domain.dto.endereco.EnderecoRequest;
import br.com.banhoetosa.gihpet.usuarios.domain.dto.endereco.EnderecoResponse;
import br.com.banhoetosa.gihpet.usuarios.domain.entity.Endereco;
import br.com.banhoetosa.gihpet.usuarios.domain.mapper.EnderecoMapper;
import br.com.banhoetosa.gihpet.usuarios.domain.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/enderecos")
@Tag(name = "Endereco", description = "Gerenciamento de Endereço")
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

    @GetMapping("{id}")
    @Operation(summary = "Buscar cliente por ID")
    public ResponseEntity<EnderecoResponse> buscarPorId(@PathVariable("id") String id) {
        var idEndereco = UUID.fromString(id);
        Optional<Endereco> clienteOptional = enderecoService.buscarPorId(idEndereco);

        return enderecoService
                .buscarPorId(idEndereco)
                .map(endereco -> {
                    EnderecoResponse dto = enderecoMapper.toDTO(endereco);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("{id}")
    @Operation(summary = "Atualizar endereço")
    public ResponseEntity<EnderecoResponse> atualizarEndereco(
            @PathVariable ("id") String id,
            @RequestBody @Valid EnderecoAtualizacaoRequest dto
            ){
        UUID idEndereco = UUID.fromString(id);
        Optional<Endereco> enderecoOptional = enderecoService.buscarPorId(idEndereco);

        if (enderecoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Endereco endereco = enderecoMapper.toEntity(dto);
        endereco.setId(idEndereco);

        Endereco enderecoAtualizado = enderecoService.atualizarEndereco(endereco);
        EnderecoResponse response = enderecoMapper.toDTO(enderecoAtualizado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar endeço do db")
    public ResponseEntity<Void> deletarEndereco(@PathVariable("id") String id){
        var idEndereco = UUID.fromString(id);
        Optional<Endereco> enderecoOptional = enderecoService.buscarPorId(idEndereco);

        if (enderecoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        enderecoService.deletarEndereco(idEndereco);
        return ResponseEntity.noContent().build();
    }
}
