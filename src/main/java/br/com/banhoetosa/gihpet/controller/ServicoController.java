package br.com.banhoetosa.gihpet.controller;

import br.com.banhoetosa.gihpet.dto.servico.ServicoRequest;
import br.com.banhoetosa.gihpet.dto.servico.ServicoResponse;
import br.com.banhoetosa.gihpet.entity.Servico;
import br.com.banhoetosa.gihpet.mapper.ServicoMapper;
import br.com.banhoetosa.gihpet.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/servicos")
@Tag(name = "Serviços", description = "Gerenciamento de serviços e agendamentos")
public class ServicoController {

    private final ServicoService servicoService;
    private final ServicoMapper servicoMapper;

    public ServicoController(ServicoService servicoService, ServicoMapper servicoMapper) {
        this.servicoService = servicoService;
        this.servicoMapper = servicoMapper;
    }

    @Operation(summary = "Cria um novo serviço com agendamento")
    @PostMapping("/{idCliente}/{idPet}")
    public ResponseEntity<ServicoResponse> criarServico(
            @PathVariable UUID idCliente,
            @PathVariable UUID idPet,
            @Valid @RequestBody ServicoRequest request
    ) {
        ServicoResponse response = servicoService.criarServico(
                idCliente,
                idPet,
                request,
                request.dataAgendamento()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Buscar todos os servicos!")
    public ResponseEntity<Page<ServicoResponse>> buscarServicos(
            @RequestParam (value = "pagina", defaultValue = "0")
            Integer pagina,
            @RequestParam(value = "tamanhoPagina", defaultValue = "10")
            Integer tamanhoPagina,
            @RequestParam(value = "tipoServico", required = false)
            String tipoServico,
            @RequestParam(value = "statusServico", required = false)
            String statusServico,
            @RequestParam(value = "taxiDog", required = false)
            String taxiDog,
            @RequestParam(value = "horarioTaxiDog", required = false)
            String horarioTaxiDog,
            @RequestParam(value = "statusAgendamento", required = false)
            String statusAgendamento
    ){
        Page<Servico> paginaResultado = servicoService.buscar(
                pagina, tamanhoPagina, tipoServico, statusServico, taxiDog, horarioTaxiDog, statusAgendamento);

        Page<ServicoResponse> resultado = paginaResultado.map(servicoMapper::toDTO);

        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir servico")
    public ResponseEntity<Void> deletarServico(@PathVariable("id") String id) {
        var idServico = UUID.fromString(id);
        Optional<Servico> servicoOptional = servicoService.buscarPorId(idServico);

        if (servicoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        servicoService.deletarServico(idServico);
        return ResponseEntity.noContent().build();
    }
}