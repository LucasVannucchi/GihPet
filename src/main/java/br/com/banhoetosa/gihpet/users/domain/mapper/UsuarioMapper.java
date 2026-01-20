package br.com.banhoetosa.gihpet.users.domain.mapper;

import br.com.banhoetosa.gihpet.users.domain.dto.usuario.UsuarioRequestDTO;
import br.com.banhoetosa.gihpet.users.domain.dto.usuario.UsuarioResponseDTO;
import br.com.banhoetosa.gihpet.users.domain.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setSenha(dto.senha());
        usuario.setRoles(dto.roles());
        return usuario;
    }

    public UsuarioResponseDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getRoles()
        );
    }
}
