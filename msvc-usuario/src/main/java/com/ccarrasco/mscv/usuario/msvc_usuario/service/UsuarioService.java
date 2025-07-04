package com.ccarrasco.mscv.usuario.msvc_usuario.service;

import com.ccarrasco.mscv.usuario.msvc_usuario.model.Usuario;
import com.ccarrasco.mscv.usuario.msvc_usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    // Aquí puedes agregar métodos para interactuar con el repositorio


    public List<Usuario> getUsuarios() {
        return usuarioRepository.obtenerUsuarios();
    }

    // Buscar Usuario por su id
    public Usuario buscarPorID(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Error: El ID no puede ser nulo.");
        }
        return usuarioRepository.buscarPorID(id);
    }

    // Buscar usuario por su correo
    public Usuario buscarPorCorreo(String correo) {

        if (correo == null || correo.isEmpty()) {
            throw new IllegalArgumentException("Error: El correo no puede estar vacío o nulo.");
        }
        return usuarioRepository.buscarPorGmail(correo);

    }

    // Guardar usuario
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.guardar(usuario);
    }

    // Actualizar usuario
    public Usuario actualizar(Usuario usuario) {
        return usuarioRepository.actualizar(usuario);
    }

    // Eliminar usuario
    public String eliminar(int id) {
        usuarioRepository.eliminar(id);
        return "Usuario eliminado con éxito";
    }

}
