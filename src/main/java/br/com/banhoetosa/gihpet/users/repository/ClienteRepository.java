package br.com.banhoetosa.gihpet.users.repository;

import br.com.banhoetosa.gihpet.users.domain.models.Cliente;
import br.com.banhoetosa.gihpet.users.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>, JpaSpecificationExecutor<Cliente> {
    List<Cliente> findByNome(String nome);
    List<Cliente> findByNacionalidade(String nacionalidade);
    List<Cliente> findByEnderecos(Endereco endereco);
    Optional<Cliente> findByNomeAndRgAndCpf(String nome, String rg, String cpf);
}
