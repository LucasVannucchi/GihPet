package br.com.banhoetosa.gihpet.security;

import br.com.banhoetosa.gihpet.entity.Usuario;
import br.com.banhoetosa.gihpet.service.UsuarioService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService service;

    public CustomUserDetailsService(UsuarioService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = service.obterPorLogin(login);

        if (usuario == null){
            throw new UsernameNotFoundException("Usuario não encontrado!");
        }
        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(usuario.getRoles().toString())
                .build();
    }
}