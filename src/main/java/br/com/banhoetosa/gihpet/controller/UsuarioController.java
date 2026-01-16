package br.com.banhoetosa.gihpet.controller;

import br.com.banhoetosa.gihpet.dto.usuario.UsuarioRequestDTO;
import br.com.banhoetosa.gihpet.mapper.UsuarioMapper;
import br.com.banhoetosa.gihpet.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/usuarios")
@Tag(name = "Usuario")
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
