package br.com.banhoetosa.gihpet.repository;

import br.com.banhoetosa.gihpet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID> {
}
