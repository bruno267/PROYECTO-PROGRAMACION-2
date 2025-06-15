package CONTROLLER;

import MODEL.Navegacion;
import MODEL.PRODUCTO;
import MODEL.Carrito;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import MODEL.ListaProducto;
import MODEL.NodoProducto;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class CATALOGOController {

    @FXML
    private FlowPane contenedorProductos;
    @FXML
    private Label lblContadorCarrito;

    private Carrito carrito;

    @FXML
    public void initialize() {
        this.carrito = Carrito.getInstance();
        ListaProducto productos = cargarProductos();
        mostrarProductos(productos);
        actualizarContadorCarrito();
    }

    private ListaProducto cargarProductos() {
        ListaProducto lista = new ListaProducto();

        lista.agregar(new PRODUCTO("P001", "Diademas Beats Solo Pro", 333200, 4.9,
                new Image(getClass().getResourceAsStream("/IMAGENES/beats.png"))));

        lista.agregar(new PRODUCTO("P002", "Audífonos Bluetooth STAU", 90900, 4.7,
                new Image(getClass().getResourceAsStream("/IMAGENES/audifonos.png"))));

        lista.agregar(new PRODUCTO("P003", "Cargador GaN USB C PD", 119900, 4.8,
                new Image(getClass().getResourceAsStream("/IMAGENES/cargador.png"))));

        lista.agregar(new PRODUCTO("P004", "Apple Watch Serie 9 45mm", 1533200, 5.0,
                new Image(getClass().getResourceAsStream("/IMAGENES/apple_watch.png"))));

        lista.agregar(new PRODUCTO("P005", "Cable adaptador Lightning", 34320, 4.6,
                new Image(getClass().getResourceAsStream("/IMAGENES/cable.png"))));

        lista.agregar(new PRODUCTO("P006", "Parlante JBL GO4", 193920, 4.9,
                new Image(getClass().getResourceAsStream("/IMAGENES/jbl.png"))));

        lista.agregar(new PRODUCTO("P007", "Xiaomi Note 14 Pro 256GB", 949990, 4.5,
                new Image(getClass().getResourceAsStream("/IMAGENES/xiaomi.png"))));

        lista.agregar(new PRODUCTO("P008", "Nokia 105 4G", 98700, 4.7,
                new Image(getClass().getResourceAsStream("/IMAGENES/nokia.png"))));

        lista.agregar(new PRODUCTO("P009", "Control PLAYSTATION PS5", 254900, 4.9,
                new Image(getClass().getResourceAsStream("/IMAGENES/Control.png"))));

        lista.agregar(new PRODUCTO("P010", "Portátil LENOVO IdeaPad", 2399000, 4.7,
                new Image(getClass().getResourceAsStream("/IMAGENES/Lenovo.png"))));

        return lista;

    }

    private void mostrarProductos(ListaProducto lista) {
        contenedorProductos.getChildren().clear();

        NodoProducto actual = lista.getCabeza();
        while (actual != null) {
            PRODUCTO producto = actual.getProducto();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/VIEW/TarjetaProducto.fxml"));
                Pane tarjeta = loader.load();

                TarjetaProductoController controller = loader.getController();
                controller.setProducto(producto);
                controller.setCatalogoController(this);

                contenedorProductos.getChildren().add(tarjeta);
            } catch (IOException e) {
                System.err.println("Error al cargar la tarjeta de producto: " + e.getMessage());
                e.printStackTrace();
            }

            actual = actual.getSiguiente();
        }
    }

    @FXML
    private void irACarrito() {
        Stage stage = (Stage) contenedorProductos.getScene().getWindow();
        Navegacion.cambiarVista("CARRITO", "JSHOP - Carrito", stage);
    }

    // Método para actualizar el contador del carrito
    public void actualizarContadorCarrito() {
        if (lblContadorCarrito != null) {
            // Usar el método de cantidad del carrito
            lblContadorCarrito.setText(String.valueOf(carrito.getCantidadProductos()));
        }
    }

    @FXML
    private void cerrarSesion() {
        Stage currentStage = (Stage) contenedorProductos.getScene().getWindow();
        currentStage.close();
        Navegacion.cargarVista("LOGIN", "JSHOP - Inicio de Sesión");
    }

    @FXML
    private void irAFavoritos() {
        Stage stage = (Stage) contenedorProductos.getScene().getWindow();
        Navegacion.cambiarVista("FAVORITOS", "JSHOP - Favoritos", stage);
    }

    @FXML
    private void irAMiCuenta() {
        Stage stage = (Stage) contenedorProductos.getScene().getWindow();
        Navegacion.cambiarVista("MICUENTA", "JSHOP - Mi Cuenta", stage);
    }

}
