package br.com.banhoetosa.gihpet.mapper;

import br.com.banhoetosa.gihpet.dto.cliente.ClienteAtualizacaoRequest;
import br.com.banhoetosa.gihpet.dto.cliente.ClienteRequest;
import br.com.banhoetosa.gihpet.dto.cliente.ClienteResponse;
import br.com.banhoetosa.gihpet.dto.endereco.EnderecoResponse;
import br.com.banhoetosa.gihpet.dto.pet.PetResponse;
import br.com.banhoetosa.gihpet.entity.Cliente;
import br.com.banhoetosa.gihpet.entity.Endereco;
import br.com.banhoetosa.gihpet.entity.Pet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    private final EnderecoMapper enderecoMapper;
    private final PetMapper petMapper;

    public ClienteMapper(EnderecoMapper enderecoMapper, PetMapper petMapper) {
        this.enderecoMapper = enderecoMapper;
        this.petMapper = petMapper;
    }

    public Cliente toEntity(ClienteRequest dto) {
        if (dto == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setRg(dto.rg());
        cliente.setCpf(dto.cpf());
        cliente.setDataNascimento(dto.dataNascimento());
        cliente.setNacionalidade(dto.nacionalidade());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());

        List<Endereco> enderecos = dto.enderecos()
                .stream()
                .map(enderecoMapper::toEntity)
                .collect(Collectors.toList());
        cliente.setEnderecos(enderecos);

        List<Pet> pets = dto.pets()
                .stream()
                .map(petMapper::toEntity)
                .collect(Collectors.toList());
        cliente.setPets(pets);

        return cliente;
    }

    public Cliente toEntity(ClienteAtualizacaoRequest dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setRg(dto.rg());
        cliente.setDataNascimento(dto.dataNascimento());
        cliente.setNacionalidade(dto.nacionalidade());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());
        return cliente;
    }


    public ClienteResponse toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        List<EnderecoResponse> enderecos = cliente.getEnderecos()
                .stream()
                .map(enderecoMapper::toDTO)
                .collect(Collectors.toList());

        List<PetResponse> pets = cliente.getPets()
                .stream()
                .map(petMapper::toDTO)
                .collect(Collectors.toList());

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getRg(),
                cliente.getDataNascimento(),
                cliente.getNacionalidade(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getStatusCliente(),
                cliente.getDataCadastro(),
                cliente.getDataAtualizacao(),
                enderecos,
                pets
        );
    }
}