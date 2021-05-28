package br.com.zupedu.ot5.stitchsecurity101.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping
    public ResponseEntity<?> listaUsuarios() {
        HashMap<Object, Object> usuarios = new HashMap<>();
        usuarios.put("nome", "Pluto");
        return ResponseEntity.ok(usuarios);
    }
}
