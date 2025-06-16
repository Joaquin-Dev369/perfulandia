package com.ccarrasco.mscv.usuario.msvc_usuario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioID;
    @Column(name = "usuario_ID")


    private String nombreUsuario;
    private String correo;

    private Double totalDineroCliente;

    private String tipoCliente;




}

