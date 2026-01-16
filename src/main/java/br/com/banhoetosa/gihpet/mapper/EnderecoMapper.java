package br.com.banhoetosa.gihpet.mapper;

import br.com.banhoetosa.gihpet.dto.endereco.EnderecoRequest;
import br.com.banhoetosa.gihpet.dto.endereco.EnderecoResponse;
import br.com.banhoetosa.gihpet.entity.Endereco;
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

    public EnderecoResponse toDTO(Endereco entity) {
        if (entity == null) {
            return null;
        }

        return new EnderecoResponse(
                entity.getId(),
                entity.getLogradouro(),
                entity.getNumero(),
                entity.getComplemento(),
                entity.getBairro(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getCep()
        );
    }
}