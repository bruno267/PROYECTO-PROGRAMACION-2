/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author PUBLICO
 */

import java.time.LocalDate;

public class Compra {
    private PRODUCTO producto;
    private LocalDate fecha;

    public Compra(PRODUCTO producto, LocalDate fecha) {
        this.producto = producto;
        this.fecha = fecha;
    }

    public PRODUCTO getProducto() {
        return producto;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
