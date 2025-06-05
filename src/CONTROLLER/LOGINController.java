package CONTROLLER;

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
            mostrarAlerta("Éxito", "Inicio de sesión exitoso", Alert.AlertType.INFORMATION);
          
        } else {
            lblerror.setVisible(true);
            txtUsuario.clear();
            txtContrasena.clear();
        }
    }

    @FXML 
    private void irACrearCuenta() {
        
        try {
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VIEW/CREARUSUARIO.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Crear Nueva Cuenta");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // Hace que la ventana de login se bloquee hasta cerrar registro
            stage.showAndWait(); // Espera a que se cierre la ventana de registro

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la ventana", "No se pudo cargar la ventana de creación de usuario. Verifique la ruta del FXML y la existencia del archivo. Detalle: " + e.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception e) { 
            e.printStackTrace();
            mostrarAlerta("Error inesperado", "Ocurrió un error inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Mé
    public static void agregarUsuario(USUARIO nuevoUsuario) {
        usuariosRegistrados.add(nuevoUsuario);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}