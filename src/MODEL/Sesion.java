package MODEL;

import java.util.ArrayList;
import java.util.List;

public class Sesion {
    private static USUARIO usuario;
    private static List<Compra> historialCompras = new ArrayList<>();

    public static void setUsuario(USUARIO u) {
        usuario = u;
    }

    public static USUARIO getUsuario() {
        return usuario;
    }

    public static void cerrarSesion() {
        usuario = null;
        historialCompras.clear();
    }

    public static List<Compra> getHistorialCompras() {
        return historialCompras;
    }

    public static void agregarCompra(Compra compra) {
        historialCompras.add(compra);
    }
}
