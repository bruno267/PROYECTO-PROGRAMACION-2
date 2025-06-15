package CONTROLLER;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import MODEL.USUARIO;

public class CREARUSUARIOController implements Initializable {

    @FXML
    private TextField txtNuevoUsuario;
    @FXML
    private PasswordField txtNuevaContrasena;
    @FXML
    private PasswordField txtConfirmarContrasena;
    @FXML
    private Label lblErrorRegistro;
    @FXML
    private Label lblMensajeRegistro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblErrorRegistro.setVisible(false);
        lblMensajeRegistro.setVisible(false);
    }

    @FXML
    private void handleRegistrar() {
        lblErrorRegistro.setVisible(false);
        lblMensajeRegistro.setVisible(false);

        String nuevoUsuario = txtNuevoUsuario.getText();
        String nuevaContrasena = txtNuevaContrasena.getText();
        String confirmarContrasena = txtConfirmarContrasena.getText();

        if (nuevoUsuario.isEmpty() || nuevaContrasena.isEmpty() || confirmarContrasena.isEmpty()) {
            lblErrorRegistro.setText("Todos los campos son obligatorios.");
            lblErrorRegistro.setVisible(true);
            return;
        }

        if (!nuevaContrasena.equals(confirmarContrasena)) {
            lblErrorRegistro.setText("Las contraseñas no coinciden.");
            lblErrorRegistro.setVisible(true);
            txtNuevaContrasena.clear();
            txtConfirmarContrasena.clear();
            return;
        }

        USUARIO user = new USUARIO(nuevoUsuario, nuevaContrasena);
        LOGINController.agregarUsuario(user);

        lblMensajeRegistro.setText("¡Usuario '" + nuevoUsuario + "' creado con éxito!");
        lblMensajeRegistro.setVisible(true);

        Stage stage = (Stage) txtNuevoUsuario.getScene().getWindow();
        stage.close();
    }
}
