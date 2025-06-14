
package MODEL;

public class Favoritos {
    private static Favoritos instance;
    private NodoFavorito cabeza;
    private NodoFavorito ultimo;
    private int cantidad;

    private Favoritos() {
        cabeza = null;
        ultimo = null;
        cantidad = 0;
    }

    public static Favoritos getInstance() {
        if (instance == null) {
            instance = new Favoritos();
        }
        return instance;
    }

    public void agregarProducto(PRODUCTO producto) {
        NodoFavorito nuevo = new NodoFavorito(producto);
        if (cabeza == null) {
            cabeza = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
        cantidad++;
    }

    public void eliminarProducto(String id) {
        if (cabeza == null) return;

        if (cabeza.producto.getId().equals(id)) {
            cabeza = cabeza.siguiente;
            if (cabeza == null) ultimo = null;
            cantidad--;
            return;
        }

        NodoFavorito actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.producto.getId().equals(id)) {
                actual.siguiente = actual.siguiente.siguiente;
                if (actual.siguiente == null) ultimo = actual;
                cantidad--;
                return;
            }
            actual = actual.siguiente;
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public NodoFavorito getCabeza() {
        return cabeza;
    }

    public static class NodoFavorito {
        public PRODUCTO producto;
        public NodoFavorito siguiente;

        public NodoFavorito(PRODUCTO producto) {
            this.producto = producto;
            this.siguiente = null;
        }
    }
}