package MODEL;

public class ListaProducto {

    private NodoProducto cabeza;
    private NodoProducto cola;

    public ListaProducto() {
        this.cabeza = null;
        this.cola = null;
    }

    public void agregar(PRODUCTO producto) {
        NodoProducto nuevo = new NodoProducto(producto);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }
    }

    public NodoProducto getCabeza() {
        return cabeza;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }
}
