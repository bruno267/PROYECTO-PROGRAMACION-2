package MODEL;

public class Sesion {
    private static USUARIO usuarioActual;

    public static void setUsuario(USUARIO usuario) {
        Sesion.usuarioActual = usuario;
    }

    public static USUARIO getUsuario() {
        return usuarioActual;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }
}
