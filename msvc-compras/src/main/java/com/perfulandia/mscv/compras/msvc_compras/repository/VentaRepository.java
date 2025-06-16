package com.perfulandia.mscv.compras.msvc_compras.repository;

import com.perfulandia.mscv.compras.msvc_compras.model.Venta;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VentaRepository {
    private List<Venta> listaVenta = new ArrayList<>();

    public List<Venta> obtenerCompras() {
        return listaVenta;
    }

    //Buscar compra por su id de compra
    public Venta buscarPorID(String id) {
        for (Venta venta : listaVenta) {
            if (venta.getId() == id) {
                return

            }

        }

    }


}


