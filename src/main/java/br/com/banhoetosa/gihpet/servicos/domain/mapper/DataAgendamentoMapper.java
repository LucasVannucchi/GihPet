package br.com.banhoetosa.gihpet.servicos.domain.mapper;

import br.com.banhoetosa.gihpet.servicos.domain.dto.dataAgendamento.DataAgendamentoRequest;
import br.com.banhoetosa.gihpet.servicos.domain.dto.dataAgendamento.DataAgendamentoResponse;
import br.com.banhoetosa.gihpet.usuarios.domain.models.Cliente;
import br.com.banhoetosa.gihpet.servicos.domain.entity.DataAgendamento;
import br.com.banhoetosa.gihpet.pets.domain.entity.Pet;
import org.springframework.stereotype.Component;

@Component
public class DataAgendamentoMapper {

    public DataAgendamento toEntity(DataAgendamentoRequest dto, Pet pet, Cliente cliente) {
        if (dto == null) {
            return null;
        }

        DataAgendamento agendamento = new DataAgendamento();
        agendamento.setDataHora(dto.dataHora());
        agendamento.setStatus(dto.statusAgendamento());
        agendamento.setPet(pet);
        agendamento.setCliente(cliente);
        return agendamento;
    }

    public DataAgendamentoResponse toDTO(DataAgendamento agendamento) {
        if (agendamento == null) {
            return null;
        }

        return new DataAgendamentoResponse(
                agendamento.getId(),
                agendamento.getDataHora(),
                agendamento.getStatus(),
                agendamento.getPet() != null ? agendamento.getPet().getId() : null,
                agendamento.getPet() != null ? agendamento.getPet().getNomePet() : null,
                agendamento.getCliente() != null ? agendamento.getCliente().getId() : null,
                agendamento.getCliente() != null ? agendamento.getCliente().getNome() : null
        );
    }
}