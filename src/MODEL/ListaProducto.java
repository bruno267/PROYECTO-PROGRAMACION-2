package MODELO;

public class ListaProducto {
    private NodoProducto cabeza;

    public ListaProducto() {
        this.cabeza = null;
    }

    public void agregar(PRODUCTO producto) {
        NodoProducto nuevo = new NodoProducto(producto);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoProducto temp = cabeza;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevo);
        }
    }

    public NodoProducto getCabeza() {
        return cabeza;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }
}

