package br.com.banhoetosa.gihpet.users.domain.mapper;

import br.com.banhoetosa.gihpet.users.domain.dto.endereco.EnderecoAtualizacaoRequest;
import br.com.banhoetosa.gihpet.users.domain.dto.endereco.EnderecoRequest;
import br.com.banhoetosa.gihpet.users.domain.dto.endereco.EnderecoResponse;
import br.com.banhoetosa.gihpet.users.domain.entity.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public Endereco toEntity(EnderecoRequest dto) {
        if (dto == null) {
            return null;
        }

        Endereco endereco = new Endereco();
        endereco.setLogradouro(dto.logradouro());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
        endereco.setCep(dto.cep());

        return endereco;
    }

    public Endereco toEntity(EnderecoAtualizacaoRequest dto){
        Endereco endereco = new Endereco();
        endereco.setLogradouro(dto.logradouro());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
        endereco.setCep(dto.cep());
        return endereco;
    }

    public EnderecoResponse toDTO(Endereco endereco) {
        if (endereco == null) {
            return null;
        }

        return new EnderecoResponse(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }
}