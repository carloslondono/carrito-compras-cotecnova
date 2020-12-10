package co.edu.cotecnova.controladores;

import co.edu.cotecnova.modelos.Usuario;
import co.edu.cotecnova.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
    //private UsuarioServicio usuarioServicio = new UsuarioServicio();

    @Autowired
    private UsuarioServicio usuarioServicio;

    /*public UsuarioControlador(UsuarioServicio usuarioServicio){
        this.usuarioServicio = usuarioServicio;
    }*/

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios(){
        return new ResponseEntity<List<Usuario>>(usuarioServicio.obtenerUsuarios(), HttpStatus.OK);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<Usuario> obtenerUsuarioPorUsername(@PathVariable("username") String username){
        return new ResponseEntity<Usuario>(usuarioServicio.obtenerUsuarioPorUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        return new ResponseEntity<Usuario>(usuarioServicio.crearUsuario(usuario), HttpStatus.CREATED);
    }

}
