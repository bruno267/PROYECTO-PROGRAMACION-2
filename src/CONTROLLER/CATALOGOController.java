package CONTROLLER;
import MODEL.Navegacion;
import MODELO.PRODUCTO;
import MODELO.Carrito; // <--- Importa la clase Carrito
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label; // <--- Importa Label
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;

public class CATALOGOController {

    @FXML
    private FlowPane contenedorProductos;
    @FXML
    private Label lblContadorCarrito; // <--- Nuevo FXML para el contador

    private Carrito carrito; // <--- Instancia del carrito

    @FXML
    public void initialize() {
        carrito = Carrito.getInstance(); // Obtiene la instancia Singleton del carrito
        List<PRODUCTO> productos = cargarProductos();
        mostrarProductos(productos);
        actualizarContadorCarrito(); // Llama a este método al inicializar
    }

    private List<PRODUCTO> cargarProductos() {
        List<PRODUCTO> lista = new ArrayList<>();

        lista.add(new PRODUCTO("P001", "Diademas Beats Solo Pro", 333200, 4.9,
                new Image(getClass().getResourceAsStream("/IMAGENES/beats.png"))));

        lista.add(new PRODUCTO("P002", "Audífonos Bluetooth STAU", 90900, 4.7,
                new Image(getClass().getResourceAsStream("/IMAGENES/audifonos.png"))));

        lista.add(new PRODUCTO("P003", "Cargador GaN USB C PD", 119900, 4.8,
                new Image(getClass().getResourceAsStream("/IMAGENES/cargador.png"))));

        lista.add(new PRODUCTO("P004", "Apple Watch Serie 9 45mm", 1533200, 5.0,
                new Image(getClass().getResourceAsStream("/IMAGENES/apple_watch.png"))));

        lista.add(new PRODUCTO("P005", "Cable adaptador Lightning", 34320, 4.6,
                new Image(getClass().getResourceAsStream("/IMAGENES/cable.png"))));

        lista.add(new PRODUCTO("P006", "Parlante JBL GO4", 193920, 4.9,
                new Image(getClass().getResourceAsStream("/IMAGENES/jbl.png"))));

        lista.add(new PRODUCTO("P007", "Xiaomi Note 14 Pro 256GB", 949990, 4.5,
                new Image(getClass().getResourceAsStream("/IMAGENES/xiaomi.png"))));

        lista.add(new PRODUCTO("P008", "Nokia 105 4G", 98700, 4.7,
                new Image(getClass().getResourceAsStream("/IMAGENES/nokia.png"))));

        return lista;
    }

    private void mostrarProductos(List<PRODUCTO> productos) {
        contenedorProductos.getChildren().clear();

        for (PRODUCTO producto : productos) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/VIEW/TarjetaProducto.fxml"));
                Pane tarjeta = loader.load();

                TarjetaProductoController controller = loader.getController();
                controller.setProducto(producto);
                // Pasar una referencia al CATALAGOController para que pueda actualizar el contador
                controller.setCatalogoController(this); // <--- Nuevo método en TarjetaProductoController
                contenedorProductos.getChildren().add(tarjeta);
            } catch (IOException e) {
                System.err.println("Error al cargar la tarjeta de producto: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void irACarrito() {
        Stage stage = (Stage) contenedorProductos.getScene().getWindow();
        Navegacion.cambiarVista("/VIEW/CARRITO.fxml", "JSHOP - Carrito de Compras", stage);
    }

    // Método para actualizar el contador del carrito
    public void actualizarContadorCarrito() {
        if (lblContadorCarrito != null) {
            lblContadorCarrito.setText(String.valueOf(carrito.getProductos().size()));
        }
    }
}