package br.com.banhoetosa.gihpet.validator;

import br.com.banhoetosa.gihpet.entity.DataAgendamento;
import br.com.banhoetosa.gihpet.repository.DataAgendamentoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DataAgendamentoValidator {

    private final DataAgendamentoRepository repository;

    public DataAgendamentoValidator(DataAgendamentoRepository repository) {
        this.repository = repository;
    }

    public void validarAgendamento(DataAgendamento agendamento) {
        if (agendamento == null) {
            throw new IllegalArgumentException("Agendamento não pode ser nulo.");
        }

        if (agendamento.getDataHora() == null) {
            throw new IllegalArgumentException("Data e hora do agendamento são obrigatórias.");
        }

        if (agendamento.getDataHora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data e hora do agendamento devem ser futuras.");
        }

        if (agendamento.getPet() == null) {
            throw new IllegalArgumentException("O pet deve ser informado no agendamento.");
        }

        if (agendamento.getCliente() == null) {
            throw new IllegalArgumentException("O cliente deve ser informado no agendamento.");
        }

        UUID petId = agendamento.getPet().getId();
        LocalDateTime dataHora = agendamento.getDataHora();

        boolean mesmoPetHorarioOcupado = repository.existsByPetIdAndDataHora(petId, dataHora);
        if (mesmoPetHorarioOcupado) {
            throw new IllegalArgumentException("Este pet já possui um agendamento neste horário.");
        }

        boolean outroPetMesmoHorario = repository.existsByDataHora(dataHora);
        if (outroPetMesmoHorario) {
            throw new IllegalArgumentException("Já existe outro pet agendado neste mesmo horário.");
        }
    }

    public void validarAtualizacao(DataAgendamento existente, DataAgendamento novosDados) {
        if (novosDados == null) return;

        if (novosDados.getDataHora() != null)
            existente.setDataHora(novosDados.getDataHora());

        if (novosDados.getStatus() != null)
            existente.setStatus(novosDados.getStatus());

        validarAgendamento(existente);
    }
}