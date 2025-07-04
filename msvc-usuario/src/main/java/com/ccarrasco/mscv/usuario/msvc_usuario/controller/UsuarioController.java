package com.ccarrasco.mscv.usuario.msvc_usuario.controller;


import com.ccarrasco.mscv.usuario.msvc_usuario.model.Usuario;
import com.ccarrasco.mscv.usuario.msvc_usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/Listar")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PostMapping
    public Usuario agregarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @GetMapping("/id/{id}")
    public Usuario buscarPorID(@PathVariable Long id) {
        return usuarioService.buscarPorID(id);
    }

    @GetMapping("/correo/{correo}")
    public Usuario buscarPorCorreo(@PathVariable String correo) {
        return usuarioService.buscarPorCorreo(correo);
    }

    @PutMapping("/actualizar/{id}")
    public Usuario actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        //id se usara mas adelante
        return usuarioService.actualizar(usuario);
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        return usuarioService.eliminar(id);
    }

}
