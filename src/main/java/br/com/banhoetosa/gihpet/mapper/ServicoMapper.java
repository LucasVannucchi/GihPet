package br.com.banhoetosa.gihpet.mapper;

import br.com.banhoetosa.gihpet.dto.dataAgendamento.DataAgendamentoResponse;
import br.com.banhoetosa.gihpet.dto.servico.ServicoAtualizacaoRequest;
import br.com.banhoetosa.gihpet.dto.servico.ServicoRequest;
import br.com.banhoetosa.gihpet.dto.servico.ServicoResponse;
import br.com.banhoetosa.gihpet.entity.Cliente;
import br.com.banhoetosa.gihpet.entity.DataAgendamento;
import br.com.banhoetosa.gihpet.entity.Pet;
import br.com.banhoetosa.gihpet.entity.Servico;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ServicoMapper {

    private final DataAgendamentoMapper dataAgendamentoMapper;

    public ServicoMapper(DataAgendamentoMapper dataAgendamentoMapper) {
        this.dataAgendamentoMapper = dataAgendamentoMapper;
    }

    private Boolean converterSimNaoParaBoolean(String valor) {
        if (valor == null) return null;
        return "Sim".equalsIgnoreCase(valor.trim());
    }

    private String converterBooleanParaSimNao(Boolean valor) {
        if (valor == null) return "Não";
        return Boolean.TRUE.equals(valor) ? "Sim" : "Não";
    }

    public Servico toEntity(ServicoRequest dto, Pet pet, Cliente cliente) {
        if (dto == null) {
            return null;
        }

        Servico servico = new Servico();
        servico.setTipoServico(dto.tipoServico());
        servico.setTipoPagamento(dto.tipoPagamento());
        servico.setStatusPagamento(dto.statusPagamento());
        servico.setTaxiDog(converterSimNaoParaBoolean(dto.taxiDog()));
        servico.setHorarioTaxiDog(dto.horarioTaxiDog());
        servico.setPacote(converterSimNaoParaBoolean(dto.pacote()));
        servico.setStatusServico(dto.statusServico());
        servico.setObservacoes(dto.observacoes());
        servico.setCliente(cliente);
        servico.setPet(pet);

        if (dto.dataAgendamento() != null) {
            servico.setDataAgendamento(
                    dataAgendamentoMapper.toEntity(dto.dataAgendamento(), pet, cliente)
            );
        }

        return servico;
    }

    public Servico toEntity(ServicoAtualizacaoRequest dto) {
        if (dto == null) {
            return null;
        }

        Servico servico = new Servico();
        servico.setTipoServico(dto.tipoServico());
        servico.setTipoPagamento(dto.tipoPagamento());
        servico.setStatusPagamento(dto.statusPagamento());
        servico.setTaxiDog(converterSimNaoParaBoolean(dto.taxiDog()));
        servico.setHorarioTaxiDog(dto.horarioTaxiDog());
        servico.setPacote(converterSimNaoParaBoolean(dto.pacote()));
        servico.setStatusServico(dto.statusServico());
        servico.setObservacoes(dto.observacoes());
        // ❗ Removido o setPreco(dto.preco()) pois o preço é definido pelo validador
        return servico;
    }

    public ServicoResponse toDTO(Servico servico) {
        if (servico == null) {
            return null;
        }

        DataAgendamentoResponse dataAgendamentoResponse = null;
        DataAgendamento ag = servico.getDataAgendamento();
        if (ag != null) {
            dataAgendamentoResponse = new DataAgendamentoResponse(
                    ag.getId(),
                    ag.getDataHora(),
                    ag.getStatus(),
                    ag.getPet() != null ? ag.getPet().getId() : null,
                    ag.getPet() != null ? ag.getPet().getNomePet() : null,
                    ag.getCliente() != null ? ag.getCliente().getId() : null,
                    ag.getCliente() != null ? ag.getCliente().getNome() : null
            );
        }

        return new ServicoResponse(
                servico.getId(),
                servico.getTipoServico(),
                servico.getPreco(),
                servico.getTipoPagamento(),
                servico.getStatusPagamento(),
                converterBooleanParaSimNao(servico.getTaxiDog()),
                servico.getHorarioTaxiDog(),
                converterBooleanParaSimNao(servico.getPacote()),
                servico.getStatusServico(),
                servico.getObservacoes(),
                dataAgendamentoResponse,
                servico.getDataCadastro(),
                servico.getDataAtualizacao()
        );
    }
}