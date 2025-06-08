package MODELO;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private static Carrito instance;
    private List<PRODUCTO> productos = new ArrayList<>();

    private Carrito() {}

    public static Carrito getInstance() {
        if (instance == null) {
            instance = new Carrito();
        }
        return instance;
    }

    public void agregarProducto(PRODUCTO producto) {
        productos.add(producto);
    }

    public void eliminarProducto(String idProducto) {
        productos.removeIf(p -> p.getId().equals(idProducto));
    }

    public List<PRODUCTO> getProductos() {
        return productos;
    }

    public double calcularTotal() {
        return productos.stream().mapToDouble(PRODUCTO::getPrecio).sum();
    }

    public void vaciarCarrito() {
        productos.clear();
    }
}