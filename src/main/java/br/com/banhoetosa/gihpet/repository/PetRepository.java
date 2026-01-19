package br.com.banhoetosa.gihpet.repository;

import br.com.banhoetosa.gihpet.entity.Endereco;
import br.com.banhoetosa.gihpet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {

    @Query("""
        SELECT p FROM Pet p
        JOIN p.cliente c
        JOIN c.enderecos e
        WHERE p.nomePet = :nomePet
          AND c.nome = :nomeCliente
          AND e.logradouro = :logradouro
        """)
    Optional<Pet> findByNomeClienteAndLogradouro(
            @Param("nomePet") String nomePet,
            @Param("nomeCliente") String nomeCliente,
            @Param("logradouro") String logradouro);
}
