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

    //guardar usuario
    public Usuario guardar(Usuario usu) {
        listaUsuario.add(usu);
        return usu;

    }

    public Usuario actualizar(Usuario usu) {

        long id = 0;
        int idPosicion = 0;

        for(int i = 0; i < listaUsuario.size(); i++) {
            if (listaUsuario.get(i).getUsuarioID() == usu.getUsuarioID()) {

                id = usu.getUsuarioID();
                idPosicion = i;
            }
        }

        Usuario usuario1 = new Usuario();
        usuario1.setUsuarioID();
        usuario1.setNombreUsuario(usu.getNombreUsuario());

    }


}


