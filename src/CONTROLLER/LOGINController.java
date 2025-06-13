package CONTROLLER;

import MODEL.Navegacion;
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
import MODEL.USUARIO;

public class LOGINController implements Initializable {

    @FXML
    private Label lblerror;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;

    private static List<USUARIO> usuariosRegistrados = new ArrayList<>();

    static {
        usuariosRegistrados.add(new USUARIO("MAT", "1234"));
        usuariosRegistrados.add(new USUARIO("AND", "abc"));
        usuariosRegistrados.add(new USUARIO("BURN", "pass"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // No se requiere inicialización por ahora
    }

    @FXML
    private void handleIngresar() {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        boolean credencialesValidas = usuariosRegistrados.stream()
                .anyMatch(user -> user.validar(usuario, contrasena));

        if (credencialesValidas) {
            lblerror.setVisible(false);
            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            Navegacion.cambiarVista("CATALOGO", "JSHOP - Catálogo de Productos", stage);
        } else {
            lblerror.setVisible(true);
            txtUsuario.clear();
            txtContrasena.clear();
        }
    }

    @FXML
    private void irACrearCuenta() {
        Stage stage = (Stage) txtUsuario.getScene().getWindow();
        // Se corrige el nombre del archivo FXML
        Navegacion.cambiarVista("CREARUSUARIO", "JSHOP - Crear Cuenta", stage);
    }

    public static void agregarUsuario(USUARIO nuevoUsuario) {
        usuariosRegistrados.add(nuevoUsuario);
    }
}
