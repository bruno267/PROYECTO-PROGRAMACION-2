package MODELO;

public class NodoProducto {
    private PRODUCTO producto;
    private NodoProducto siguiente;

    public NodoProducto(PRODUCTO producto) {
        this.producto = producto;
        this.siguiente = null;
    }

    public PRODUCTO getProducto() {
        return producto;
    }

    public NodoProducto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoProducto siguiente) {
        this.siguiente = siguiente;
    }
}
