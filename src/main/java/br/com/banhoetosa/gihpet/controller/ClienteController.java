package br.com.banhoetosa.gihpet.controller;

import br.com.banhoetosa.gihpet.dto.cliente.ClienteAtualizacaoRequest;
import br.com.banhoetosa.gihpet.dto.cliente.ClienteRequest;
import br.com.banhoetosa.gihpet.dto.cliente.ClienteResponse;
import br.com.banhoetosa.gihpet.entity.Cliente;
import br.com.banhoetosa.gihpet.mapper.ClienteMapper;
import br.com.banhoetosa.gihpet.service.ClienteService;
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
@RequestMapping("/clientes")
@Tag(name = "Clientes")
public class ClienteController implements GenericController {

    private final ClienteService service;
    private final ClienteMapper mapper;

    public ClienteController(ClienteService service, ClienteMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @Operation(summary = "Criar novo cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Erro de validação!"),
            @ApiResponse(responseCode = "409", description = "Cliente já cadastrado!")
    })
    public ResponseEntity<ClienteResponse> salvar(@RequestBody @Valid ClienteRequest dto) {
        Cliente cliente = mapper.toEntity(dto);
        Cliente salvo = service.salvar(cliente);

        ClienteResponse response = mapper.toDTO(salvo);

        URI location = gerarHeaderLocation(salvo.getId());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar cliente por ID")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable("id") String id) {
        var idCliente = UUID.fromString(id);
        Optional<Cliente> clienteOptional = service.buscarPorId(idCliente);

        return service
                .buscarPorId(idCliente)
                .map(cliente -> {
                    ClienteResponse dto = mapper.toDTO(cliente);
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
        Page<Cliente> paginaResultado = service.buscar(
                nome, logradouro, bairro, cidade, nomePet, pagina, tamanhoPagina);

        Page<ClienteResponse> resultado = paginaResultado.map(mapper::toDTO);

        return ResponseEntity.ok(resultado);
    }

    @PatchMapping("{id}")
    @Operation(summary = "Atualiza cliente")
    public ResponseEntity<ClienteResponse> atualizarCliente(
            @PathVariable("id") String id,
            @RequestBody @Valid ClienteAtualizacaoRequest dto) {

        UUID idCliente = UUID.fromString(id);
        Optional<Cliente> clienteOptional = service.buscarPorId(idCliente);

        if (clienteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cliente cliente = mapper.toEntity(dto);
        cliente.setId(idCliente);

        Cliente clienteAtualizado = service.atualizarCliente(cliente);
        ClienteResponse response = mapper.toDTO(clienteAtualizado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Excluir cliente")
    public  ResponseEntity<Void> excluirCliente(@PathVariable("id") String id){
        var idCliente = UUID.fromString(id);
        Optional<Cliente> clienteOptional = service.buscarPorId(idCliente);

        if (clienteOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.deletarCliente(idCliente);
        return ResponseEntity.noContent().build();
    }
}