package com.ccarrasco.mscv.usuario.msvc_usuario.repository;
import com.ccarrasco.mscv.usuario.msvc_usuario.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    public UsuarioRepository() {
        //UNO CON ID NULL

        listaUsuario.add(new Usuario(3L, "AntiPablito", "AntiPablito@duocuc.cl", 1.0, "Basico"));
        listaUsuario.add(new Usuario(20L, "Pablito", "Pablito@duocuc.cl", 1000.0, "Avanzado"));
        listaUsuario.add(new Usuario(2L, "Benja Aranda", "BenjaArandal@duocuc.cl", 200000.0, "Intermedio"));
        listaUsuario.add(new Usuario(1L, "Joaquin Robles", "JoaquinRobles@duocuc.cl", 100000.0, "Basico"));
    }

    private static long ContadorLista = 1;

    private String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    private List<Usuario> listaUsuario = new ArrayList<>();

    public List<Usuario> obtenerUsuarios() {
        return listaUsuario;
    }

    //Buscar Usuario por su id
    public Usuario buscarPorID(Long id) {
        for (Usuario usuario : listaUsuario) {
            if (usuario.getUsuarioID() != null && usuario.getUsuarioID().equals(id)) {
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

        List<String> tiposValidos = List.of("Basico", "Intermedio", "Avanzado");

        // Validación: correo duplicado
        for (Usuario existente : listaUsuario) {
            if (existente.getCorreo().equalsIgnoreCase(usu.getCorreo())) {
                throw new IllegalArgumentException("X El correo ya está en uso. Intenta con otro.");
            }
        }

        // Validación: nombre y correo no pueden estar vacíos o nulos
        if (usu.getNombreUsuario() == null || usu.getNombreUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("X El nombre de usuario es obligatorio.");
        }

        if (usu.getCorreo() == null || usu.getCorreo().trim().isEmpty()) {
            throw new IllegalArgumentException("X El correo es obligatorio.");
        }

        //  Validación de formato de correo
        if (!usu.getCorreo().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("X El correo no es válido. Usa formato ejemplo@dominio.com");
        }

        if (usu.getTipoCliente() == null || usu.getTipoCliente().trim().isEmpty()) {
            usu.setTipoCliente("Basico");
        } else if (!tiposValidos.contains(capitalizar(usu.getTipoCliente()))) {
            throw new IllegalArgumentException("X Tipo de cliente inválido. Los valores permitidos son: Basico, Intermedio, Avanzado.");
        } else {
            // normaliza el valor del tipoCliente (ej. "intermedio" → "Intermedio")
            usu.setTipoCliente(capitalizar(usu.getTipoCliente()));
        }

        // 5. Validación del dinero (null o inválido)
        if (usu.getTotalDineroCliente() == null) {
            usu.setTotalDineroCliente(0.0);
        } else if (usu.getTotalDineroCliente() < 0) {
            throw new IllegalArgumentException("X El dinero no puede ser negativo.");
        }

        // 4. Dinero por defecto si es null
        if (usu.getTotalDineroCliente() == null) {
            usu.setTotalDineroCliente(0.0);
        }

        if (usu.getUsuarioID() == null) {
            long nuevoID = ContadorLista;
            ContadorLista += 1L; // Incrementar el contador para el siguiente ID

            usu.setUsuarioID(nuevoID);
        }

        listaUsuario.add(usu);
        return usu;

    }

    public Usuario actualizar(Usuario usu) {
        if (usu.getUsuarioID() == null) {
            throw new IllegalArgumentException("❌ Debes especificar el ID del usuario a modificar.");
        }

        // Buscar si existe un usuario con ese ID
        Usuario usuarioExistente = null;
        int indice = -1;

        for (int i = 0; i < listaUsuario.size(); i++) {
            if (listaUsuario.get(i).getUsuarioID().equals(usu.getUsuarioID())) {
                usuarioExistente = listaUsuario.get(i);
                indice = i;
                break;
            }
        }

        if (usuarioExistente == null) {
            throw new IllegalArgumentException("❌ No existe un usuario con ID " + usu.getUsuarioID());
        }

        // Validaciones
        if (usu.getNombreUsuario() == null || usu.getNombreUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("❌ El nombre es obligatorio.");
        }

        if (usu.getCorreo() == null || usu.getCorreo().trim().isEmpty()) {
            throw new IllegalArgumentException("❌ El correo es obligatorio.");
        }

        if (!usu.getCorreo().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("❌ El correo no tiene un formato válido.");
        }

        for (Usuario u : listaUsuario) {
            if (!u.getUsuarioID().equals(usu.getUsuarioID()) && u.getCorreo().equalsIgnoreCase(usu.getCorreo())) {
                throw new IllegalArgumentException("❌ El correo ya está en uso por otro usuario.");
            }
        }

        List<String> tiposValidos = List.of("Basico", "Intermedio", "Avanzado");
        String tipo = usu.getTipoCliente();
        if (tipo == null || tipo.trim().isEmpty()) {
            tipo = "Basico";
        } else if (!tiposValidos.contains(capitalizar(tipo))) {
            throw new IllegalArgumentException("❌ Tipo de cliente inválido. Solo se permite: Basico, Intermedio, Avanzado.");
        }

        if (usu.getTotalDineroCliente() == null) {
            usu.setTotalDineroCliente(0.0);
        }

        // Reemplazar completamente el usuario en la lista
        Usuario nuevo = new Usuario();

        nuevo.setUsuarioID(usu.getUsuarioID());
        nuevo.setNombreUsuario(usu.getNombreUsuario());
        nuevo.setCorreo(usu.getCorreo());
        nuevo.setTipoCliente(capitalizar(tipo));
        nuevo.setTotalDineroCliente(usu.getTotalDineroCliente());

        listaUsuario.set(indice, nuevo);

        return nuevo;
    }



    //Eliminar usuario por su id
    public void eliminar(int id) {
        listaUsuario.removeIf(usuario -> usuario.getUsuarioID() == id);
    }
}


