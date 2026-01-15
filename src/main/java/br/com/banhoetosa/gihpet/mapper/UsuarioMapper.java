package br.com.banhoetosa.gihpet.mapper;

import br.com.banhoetosa.gihpet.dto.usuario.UsuarioRequestDTO;
import br.com.banhoetosa.gihpet.dto.usuario.UsuarioResponseDTO;
import br.com.banhoetosa.gihpet.entity.Usuario;
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
