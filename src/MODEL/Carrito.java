package MODEL;

public class Carrito {
    private static Carrito instance;
    private NodoCarrito cabeza;
    private NodoCarrito cola;  // 
    private int cantidad;

    private Carrito() {
        cabeza = null;
        cola = null;
        cantidad = 0;
    }

    public static Carrito getInstance() {
        if (instance == null) {
            instance = new Carrito();
        }
        return instance;
    }

    public void agregarProducto(PRODUCTO producto) {
        NodoCarrito nuevoNodo = new NodoCarrito(producto);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }
        cantidad++;
    }

    public void eliminarProducto(String id) {
        if (cabeza == null) return;

        if (cabeza.producto.getId().equals(id)) {
            cabeza = cabeza.siguiente;
            if (cabeza == null) cola = null;  // actualiza cola si quedó vacía
            cantidad--;
            return;
        }

        NodoCarrito actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.producto.getId().equals(id)) {
                if (actual.siguiente == cola) {
                    cola = actual;  // actualiza cola si se borra el último
                }
                actual.siguiente = actual.siguiente.siguiente;
                cantidad--;
                return;
            }
            actual = actual.siguiente;
        }
    }

    public double calcularTotal() {
        double total = 0;
        NodoCarrito actual = cabeza;
        while (actual != null) {
            total += actual.producto.getPrecio();
            actual = actual.siguiente;
        }
        return total;
    }

    public void vaciarCarrito() {
        cabeza = null;
        cola = null;
        cantidad = 0;
    }

    public int getCantidadProductos() {
        return cantidad;
    }

    public NodoCarrito getCabeza() {
        return cabeza;
    }

    // Clase interna para nodos del carrito
    public static class NodoCarrito {
        public PRODUCTO producto;
        public NodoCarrito siguiente;

        public NodoCarrito(PRODUCTO producto) {
            this.producto = producto;
            this.siguiente = null;
        }
    }
}
