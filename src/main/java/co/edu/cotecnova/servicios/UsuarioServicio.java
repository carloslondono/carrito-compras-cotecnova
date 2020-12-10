package co.edu.cotecnova.servicios;

import co.edu.cotecnova.modelos.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicio {
    private List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> obtenerUsuarios(){
        return usuarios;
    }

    public Usuario obtenerUsuarioPorUsername(String username){
        return usuarios.stream().filter(u -> u.getUsername().equals(username)).findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("username %s no encontrado", username)));
    }

    public Usuario crearUsuario(Usuario usuario){
        if(usuarios.stream().anyMatch(u -> u.getUsername().equals(usuario.getUsername()))){
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("El usuario %s ya existe", usuario.getUsername()));
        }
        usuarios.add(usuario);
        return usuario;
    }

    public Usuario actualizarUsuario(Usuario usuario, String username){
        Usuario usuarioAActualizar = obtenerUsuarioPorUsername(username);
        usuarioAActualizar.setNombre(usuario.getNombre());
        usuarioAActualizar.setApellido(usuario.getApellido());
        usuarioAActualizar.setUsername(usuario.getUsername());
        usuarioAActualizar.setContrasena(usuario.getContrasena());
        return usuarioAActualizar;
    }

    public void eliminarUsuario(String username){
        Usuario usuarioAEliminar = obtenerUsuarioPorUsername(username);
        usuarios.remove(usuarioAEliminar);
    }
}
