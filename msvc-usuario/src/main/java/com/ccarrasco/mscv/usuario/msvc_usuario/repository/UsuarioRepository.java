package com.ccarrasco.mscv.usuario.msvc_usuario.repository;
import com.ccarrasco.mscv.usuario.msvc_usuario.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {
    private List<Usuario> listaUsuario = new ArrayList<>();

    public List<Usuario> obtenerUsuarios() {
        return listaUsuario;
    }

    //Buscar Usuario por su id
    public Usuario buscarPorID(int id) {
        for (Usuario usuario : listaUsuario) {
            if (usuario.getUsuarioID() == id) {
                return usuario;
            }
        }
        return null;
    }

    //Buscar usuario por su gmail
    public Usuario buscarPorGmail(String gmail) {
        for (Usuario usuario : listaUsuario) {
            if (usuario.getCorreo().equals(gmail)) {
                return usuario;
            }
        }
        return null;
    }
    //guardar usuario
    public Usuario guardar(Usuario usu) {
        listaUsuario.add(usu);
        return usu;

    }

    public Usuario actualizar(Usuario usu) {



        Usuario usuario1 = new Usuario();
        usuario1.setUsuarioID(usu.getUsuarioID());
        usuario1.setNombreUsuario(usu.getNombreUsuario());
        usuario1.setCorreo(usu.getCorreo());
        usuario1.setTipoCliente(usu.getTipoCliente());
        usuario1.setTotalDineroCliente(usuario1.getTotalDineroCliente());

        for (int i = 0; i < listaUsuario.size(); i++) {
            if (listaUsuario.get(i).getUsuarioID() == usuario1.getUsuarioID()) {
                listaUsuario.set(i, usuario1);
                break;
            }
        }
        // Actualizar el usuario en la lista
        return usuario1;

    }

    //Eliminar usuario por su id
    public void eliminar(int id) {
        listaUsuario.removeIf(usuario -> usuario.getUsuarioID() == id);
    }
}


