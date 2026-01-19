package br.com.banhoetosa.gihpet.repository;

import br.com.banhoetosa.gihpet.entity.DataAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface DataAgendamentoRepository extends JpaRepository<DataAgendamento, UUID> {

    boolean existsByPetIdAndDataHora(UUID petId, LocalDateTime dataHora);

    boolean existsByDataHora(LocalDateTime dataHora);
}
