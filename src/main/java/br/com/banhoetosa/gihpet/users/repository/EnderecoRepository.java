package br.com.banhoetosa.gihpet.users.repository;

import br.com.banhoetosa.gihpet.users.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    Optional<Endereco> findByLogradouroAndNumeroAndCepAndCidadeAndEstado(
            String logradouro,
            String numero,
            String cep,
            String cidade,
            String estado
    );
}