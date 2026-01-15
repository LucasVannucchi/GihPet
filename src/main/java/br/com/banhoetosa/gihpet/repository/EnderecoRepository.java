package br.com.banhoetosa.gihpet.repository;

import br.com.banhoetosa.gihpet.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
}