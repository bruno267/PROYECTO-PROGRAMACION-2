package CONTROLLER;
import MODEL.Navegacion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import MODEL.USUARIO;

public class LOGINController implements Initializable {

    @FXML
    private Label lblerror;
    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private Label lblCrearCuenta;

    private static List<USUARIO> usuariosRegistrados = new ArrayList<>();

    static {
        usuariosRegistrados.add(new USUARIO("MAT", "1234"));
        usuariosRegistrados.add(new USUARIO("AND", "abc"));
        usuariosRegistrados.add(new USUARIO("BURN", "pass"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleIngresar() {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        boolean credencialesValidas = false;
        for (USUARIO user : usuariosRegistrados) {
            if (user.validar(usuario, contrasena)) {
                credencialesValidas = true;
                break;
            }
        }

        if (credencialesValidas) {
            lblerror.setVisible(false);

            {
                     Stage stage = (Stage) txtUsuario.getScene().getWindow();
        Navegacion.cambiarVista("/VIEW/CATALOGO.fxml", "JSHOP - Catálogo de Productos", stage);
    }

        } else {
            lblerror.setVisible(true);
            txtUsuario.clear();
            txtContrasena.clear();
        }
    }

    @FXML
    private void irACrearCuenta() {

       

     
        Stage stage = (Stage) txtUsuario.getScene().getWindow();
        Navegacion.cambiarVista("/VIEW/REGISTRO.fxml", "JSHOP - Crear Cuenta", stage);
    }

    

    // Mé
    public static void agregarUsuario(USUARIO nuevoUsuario) {
        usuariosRegistrados.add(nuevoUsuario);
    }

 
}
