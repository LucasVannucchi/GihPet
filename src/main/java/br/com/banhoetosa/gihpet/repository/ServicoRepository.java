package br.com.banhoetosa.gihpet.repository;

import br.com.banhoetosa.gihpet.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface ServicoRepository extends JpaRepository<Servico, UUID>, JpaSpecificationExecutor<Servico> {

    Optional<Servico> findByPetIdAndDataAgendamentoDataHora(UUID petId, LocalDateTime dataHora);
}
