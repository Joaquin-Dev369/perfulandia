package com.perfulandia.mscv.compras.msvc_compras.model;

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
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id")
    private Long productoId;

    private Integer cantidad;

    private Double total;

    private LocalDateTime fecha;

    @PrePersist
    public void asignarFecha() {this.fecha = LocalDateTime.now();}

}
