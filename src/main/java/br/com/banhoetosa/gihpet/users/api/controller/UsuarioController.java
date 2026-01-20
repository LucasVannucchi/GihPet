package br.com.banhoetosa.gihpet.users.api.controller;

import br.com.banhoetosa.gihpet.users.domain.dto.usuario.UsuarioRequestDTO;
import br.com.banhoetosa.gihpet.users.domain.mapper.UsuarioMapper;
import br.com.banhoetosa.gihpet.users.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/usuarios")
@Tag(name = "Usuario", description = "Gerenciamento de Usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    public UsuarioController(UsuarioService service, UsuarioMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody UsuarioRequestDTO dto){
        var usuario = mapper.toEntity(dto);
        service.salvar(usuario);
    }
}
