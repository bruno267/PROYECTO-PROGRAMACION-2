package CONTROLLER;

import MODELO.PRODUCTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class CATALOGOController {

    @FXML
    private FlowPane contenedorProductos;

    @FXML
    private Label lblPagina;

    private List<PRODUCTO> productos;
    private final int productosPorPagina = 8;

    @FXML
    public void initialize() {
        productos = cargarProductos();
        mostrarProductos();
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

    private void mostrarProductos() {
        contenedorProductos.getChildren().clear();

        for (PRODUCTO producto : productos) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/VIEW/TarjetaProducto.fxml"));
                Pane tarjeta = loader.load();

                TarjetaProductoController controller = loader.getController();
                controller.setProducto(producto);

                contenedorProductos.getChildren().add(tarjeta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        lblPagina.setText("Página 1");
    }
}
