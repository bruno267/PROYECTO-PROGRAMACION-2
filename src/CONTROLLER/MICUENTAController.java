package CONTROLLER;

import MODEL.Compra;
import MODEL.Navegacion;
import MODEL.PRODUCTO;
import MODEL.Sesion;
import MODEL.USUARIO;
import MODEL.ListaProducto;
import MODEL.NodoProducto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MICUENTAController {

    @FXML
    private Label lblUsername;
    @FXML
    private Label lblFechaRegistro;
    @FXML
    private Label lblCorreo;
    @FXML
    private ListView<Pane> listaHistorial;

    @FXML
    public void initialize() {
        USUARIO usuario = Sesion.getUsuario();
        if (usuario != null) {
            lblUsername.setText("Usuario: " + usuario.getUsername());
            lblFechaRegistro.setText(usuario.getFechaRegistro());
            cargarHistorial();
        }
    }

    private void cargarHistorial() {
        for (Compra compra : Sesion.getHistorialCompras()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/VIEW/TarjetaHistorial.fxml"));
                HBox item = loader.load();
                TarjetaHistorialController controller = loader.getController();
                controller.setCompra(compra);
                listaHistorial.getItems().add(item);
            } catch (IOException e) {
                System.out.println("Error al cargar item del historial: " + e.getMessage());
            }
        }
    }

    public void agregarCompraAlHistorial(Compra compra) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VIEW/TarjetaHistorial.fxml"));
            VBox item = loader.load();
            TarjetaHistorialController controller = loader.getController();

            // Aquí usamos la compra recibida como parámetro
            controller.setCompra(compra);

            listaHistorial.getItems().add(item);
        } catch (IOException e) {
            e.printStackTrace();
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
        Sesion.cerrarSesion();
        Stage stage = (Stage) lblUsername.getScene().getWindow();
        Navegacion.cargarVista("LOGIN", "JSHOP - Iniciar Sesión");
    }
}
