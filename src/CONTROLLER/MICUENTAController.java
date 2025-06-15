/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLLER;

/**
 *
 * @author PUBLICO
 */
import MODEL.Navegacion;
import MODEL.Sesion;
import MODEL.USUARIO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javafx.stage.Stage;

public class MICUENTAController {

    @FXML
    private Label lblUsername;
    @FXML
    private Label lblFechaRegistro;
    @FXML
    private ListView<String> listaHistorial;

    @FXML
    public void initialize() {
        USUARIO usuario = Sesion.getUsuario();
        if (usuario != null) {
            lblUsername.setText("Usuario: " + usuario.getUsername());
            lblFechaRegistro.setText("Registrado desde: " + usuario.getFechaRegistro());
            cargarHistorial(usuario.getUsername());
        }
    }

    private void cargarHistorial(String username) {
        String rutaArchivo = System.getProperty("user.home") + "/factura_" + username + ".txt";
        try {
            List<String> lineas = Files.readAllLines(Paths.get(rutaArchivo));
            listaHistorial.getItems().addAll(lineas);
        } catch (IOException e) {
            listaHistorial.getItems().add("No hay historial disponible.");
        }
    }

    @FXML
    private void irACatalogo() {
        Stage stage = (Stage) lblUsername.getScene().getWindow();
        Navegacion.cambiarVista("CATALOGO", "JSHOP - Catálogo", stage);
    }

    @FXML
    private void irACarrito() {
        Stage stage = (Stage) lblUsername.getScene().getWindow();
        Navegacion.cambiarVista("CARRITO", "JSHOP - Carrito", stage);
    }

    @FXML
    private void cerrarSesion() {
        Stage stage = (Stage) lblUsername.getScene().getWindow();
        Navegacion.cambiarVista("LOGIN", "JSHOP - Iniciar Sesión", stage);
    }
}
