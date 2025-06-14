package CONTROLLER;

import MODEL.Navegacion;
import MODEL.Sesion;
import MODEL.USUARIO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LOGINController implements Initializable {

    @FXML
    private Label lblerror;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;

    private static List<USUARIO> usuariosRegistrados = new ArrayList<>();

    // Usuarios predeterminados
    static {
        usuariosRegistrados.add(new USUARIO("MAT", "1234"));
        usuariosRegistrados.add(new USUARIO("AND", "abc"));
        usuariosRegistrados.add(new USUARIO("BURN", "pass"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblerror.setVisible(false);
    }

    @FXML
    private void handleIngresar() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = txtContrasena.getText().trim();

        for (USUARIO user : usuariosRegistrados) {
            if (user.validar(usuario, contrasena)) {
                // Guardar el usuario en sesión
                Sesion.setUsuario(user);

                // Cambiar a la vista del catálogo
                Stage stage = (Stage) txtUsuario.getScene().getWindow();
                Navegacion.cambiarVista("CATALOGO", "JSHOP - Catálogo de Productos", stage);
                return; // Éxito, salimos del método
            }
        }

        // Si llega aquí, credenciales incorrectas
        lblerror.setText("Credenciales incorrectas. Inténtalo de nuevo.");
        lblerror.setVisible(true);
        txtUsuario.clear();
        txtContrasena.clear();
    }

    @FXML
    private void irACrearCuenta() {
        Stage stage = (Stage) txtUsuario.getScene().getWindow();
        Navegacion.cargarVista("CREARUSUARIO", "JSHOP - Crear Nueva Cuenta");
    }

    public static void agregarUsuario(USUARIO nuevoUsuario) {
        usuariosRegistrados.add(nuevoUsuario);
    }
}
