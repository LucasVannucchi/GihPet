package br.com.banhoetosa.gihpet.usuarios.api.controller;

import br.com.banhoetosa.gihpet.common.controller.GenericController;
import br.com.banhoetosa.gihpet.usuarios.domain.dto.cliente.ClienteAtualizacaoRequest;
import br.com.banhoetosa.gihpet.usuarios.domain.dto.cliente.ClienteRequest;
import br.com.banhoetosa.gihpet.usuarios.domain.dto.cliente.ClienteResponse;
import br.com.banhoetosa.gihpet.usuarios.domain.models.Cliente;
import br.com.banhoetosa.gihpet.usuarios.domain.mapper.ClienteMapper;
import br.com.banhoetosa.gihpet.usuarios.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/clientes")
@Tag(name = "Clientes", description = "Gerenciamento de Cliente")
public class ClienteController implements GenericController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @PostMapping
    @Operation(summary = "Criar novo cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Erro de validação!"),
            @ApiResponse(responseCode = "409", description = "Cliente já cadastrado!")
    })
    public ResponseEntity<ClienteResponse> salvar(@RequestBody @Valid ClienteRequest dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente salvo = clienteService.salvar(cliente);

        ClienteResponse response = clienteMapper.toDTO(salvo);

        URI location = gerarHeaderLocation(salvo.getId());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar cliente por ID")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable("id") String id) {
        var idCliente = UUID.fromString(id);
        Optional<Cliente> clienteOptional = clienteService.buscarPorId(idCliente);

        return clienteService
                .buscarPorId(idCliente)
                .map(cliente -> {
                    ClienteResponse dto = clienteMapper.toDTO(cliente);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Buscar todos clientes")
    public ResponseEntity<Page<ClienteResponse>> buscarClientes(
            @RequestParam(value = "nome", required = false)
            String nome,
            @RequestParam(value = "logradouro", required = false)
            String logradouro,
            @RequestParam(value = "bairro", required = false)
            String bairro,
            @RequestParam(value = "cidade", required = false)
            String cidade,
            @RequestParam(value = "nomePet", required = false)
            String nomePet,
            @RequestParam(value = "pagina", defaultValue = "0")
            Integer pagina,
            @RequestParam(value = "tamanhoPagina", defaultValue = "10")
            Integer tamanhoPagina
    ) {
        Page<Cliente> paginaResultado = clienteService.buscar(
                nome, logradouro, bairro, cidade, nomePet, pagina, tamanhoPagina);

        Page<ClienteResponse> resultado = paginaResultado.map(clienteMapper::toDTO);

        return ResponseEntity.ok(resultado);
    }

    @PatchMapping("{id}/atualizar")
    @Operation(summary = "Atualizar cliente")
    public ResponseEntity<ClienteResponse> atualizarCliente(
            @PathVariable("id") String id,
            @RequestBody @Valid ClienteAtualizacaoRequest dto) {

        UUID idCliente = UUID.fromString(id);
        Optional<Cliente> clienteOptional = clienteService.buscarPorId(idCliente);

        if (clienteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cliente cliente = clienteMapper.toEntity(dto);
        cliente.setId(idCliente);

        Cliente clienteAtualizado = clienteService.atualizarCliente(cliente);
        ClienteResponse response = clienteMapper.toDTO(clienteAtualizado);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}/reativar")
    @Operation(summary = "Reativar cliente")
    public ResponseEntity<Void> reativarCliente(@PathVariable("id") String id){
        var idCliente = UUID.fromString(id);
        Optional<Cliente> clienteOptional = clienteService.buscarPorId(idCliente);

        if (clienteOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        clienteService.reativarCliente(idCliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}/desativar")
    @Operation(summary = "Desativar cliente")
    public  ResponseEntity<Void> desativarCliente(@PathVariable("id") String id){
        var idCliente = UUID.fromString(id);
        Optional<Cliente> clienteOptional = clienteService.buscarPorId(idCliente);

        if (clienteOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        clienteService.desativarCliente(idCliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idCliente}/enderecos/{idEndereco}")
    @Operation(summary = "Remover vínculo de endereço com o cliente")
    public ResponseEntity<Void> excluirEnderecoVinculado(
            @PathVariable UUID idCliente,
            @PathVariable UUID idEndereco
    ) {
        clienteService.removerEnderecoDoCliente(idCliente, idEndereco);
        return ResponseEntity.noContent().build();
    }
}