package br.com.banhoetosa.gihpet.services.domain.service;

import br.com.banhoetosa.gihpet.services.domain.dto.dataAgendamento.DataAgendamentoRequest;
import br.com.banhoetosa.gihpet.services.domain.dto.servico.ServicoRequest;
import br.com.banhoetosa.gihpet.services.domain.dto.servico.ServicoResponse;
import br.com.banhoetosa.gihpet.services.domain.entity.DataAgendamento;
import br.com.banhoetosa.gihpet.services.domain.entity.Servico;
import br.com.banhoetosa.gihpet.services.domain.enums.servico.StatusAgendamento;
import br.com.banhoetosa.gihpet.services.domain.enums.servico.StatusServico;
import br.com.banhoetosa.gihpet.services.domain.mapper.ServicoMapper;
import br.com.banhoetosa.gihpet.pets.domain.entity.Pet;
import br.com.banhoetosa.gihpet.pets.repository.PetRepository;
import br.com.banhoetosa.gihpet.common.security.SecurityService;
import br.com.banhoetosa.gihpet.services.repository.DataAgendamentoRepository;
import br.com.banhoetosa.gihpet.services.repository.ServicoRepository;
import br.com.banhoetosa.gihpet.users.domain.models.Cliente;
import br.com.banhoetosa.gihpet.users.domain.models.Usuario;
import br.com.banhoetosa.gihpet.users.repository.ClienteRepository;
import br.com.banhoetosa.gihpet.services.domain.validator.DataAgendamentoValidator;
import br.com.banhoetosa.gihpet.services.domain.validator.ServicoValidator;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static br.com.banhoetosa.gihpet.services.repository.specs.ServicoSpecs.*;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final DataAgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final PetRepository petRepository;
    private final ServicoMapper servicoMapper;
    private final ServicoValidator servicoValidator;
    private final DataAgendamentoValidator agendamentoValidator;
    private final SecurityService securityService;

    public ServicoService(
            ServicoRepository servicoRepository,
            DataAgendamentoRepository agendamentoRepository,
            ClienteRepository clienteRepository,
            PetRepository petRepository,
            ServicoMapper servicoMapper,
            ServicoValidator servicoValidator,
            DataAgendamentoValidator agendamentoValidator,
            SecurityService securityService
    ) {
        this.servicoRepository = servicoRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.petRepository = petRepository;
        this.servicoMapper = servicoMapper;
        this.servicoValidator = servicoValidator;
        this.agendamentoValidator = agendamentoValidator;
        this.securityService = securityService;
    }

    @Transactional
    public ServicoResponse criarServico(UUID idCliente, UUID idPet,
                                        ServicoRequest servicoDTO,
                                        DataAgendamentoRequest agendamentoDTO) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        Pet pet = petRepository.findById(idPet)
                .orElseThrow(() -> new IllegalArgumentException("Pet não encontrado"));

        Servico servico = servicoMapper.toEntity(servicoDTO, pet, cliente);

        agendamentoValidator.validarAgendamento(servico.getDataAgendamento());
        DataAgendamento agendamentoSalvo = agendamentoRepository.save(servico.getDataAgendamento());
        servico.setDataAgendamento(agendamentoSalvo);

        Usuario usuario = securityService.obterUsuarioLogado();
        servico.setUsuario(usuario);

        servicoValidator.validar(servico);

        Servico salvo = servicoRepository.save(servico);
        return servicoMapper.toDTO(salvo);
    }

    public Optional<Servico> buscarPorId(UUID id){
        return servicoRepository.findById(id);
    }

    public Page<Servico> buscar(
            Integer pagina,
            Integer tamanhoPagina,
            String tipoServico,
            String statusServico,
            String taxiDog,
            String horarioTaxiDog,
            String statusAgendamento
    ){
        Specification<Servico> specs = (root, query, cb) -> cb.conjunction();

        if (tipoServico != null){
            specs = specs.and(tipoServicoLike(tipoServico));
        }
        if (statusServico != null){
            specs = specs.and(statusServicoLike(statusServico));
        }
        if (taxiDog != null) {
            Boolean taxiDogBoolean = converterTaxidog(taxiDog);
            if (taxiDogBoolean != null) {
                specs = specs.and(taxiDogEqual(taxiDogBoolean));
            }
        }
        if (horarioTaxiDog != null){
            specs = specs.and(horarioTaxiDogLike(horarioTaxiDog));
        }
        if (statusAgendamento != null){
            specs = specs.and(statusAgendamentoLike(statusAgendamento));
        }

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return servicoRepository.findAll(specs, pageRequest);
    }

    @Transactional
    public void cancelarServico(UUID id) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado!"));

        DataAgendamento dataAgendamento = servico.getDataAgendamento();
        if (dataAgendamento != null){
            dataAgendamento.setStatus(StatusAgendamento.CANCELADO);
        }

        servico.setStatusServico(StatusServico.CANCELADO);
        servicoRepository.save(servico);
    }

    /** ================================================================
     *  Conversão de "sim"/"não"/"1"/"true" -> Boolean
     *  ================================================================ */
    private Boolean converterTaxidog(String valor) {
        if (valor == null) return null;
        return switch (valor.trim().toLowerCase()) {
            case "sim", "true", "1", "yes", "y" -> true;
            case "não", "nao", "false", "0", "no", "n" -> false;
            default -> null;
        };
    }
}