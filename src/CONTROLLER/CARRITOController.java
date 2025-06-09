package CONTROLLER;
import MODEL.Navegacion;
import MODELO.Carrito;
import MODELO.PRODUCTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import MODELO.Carrito.NodoCarrito;
import java.io.IOException;

public class CARRITOController {

    @FXML
    private VBox itemsCarrito;

    @FXML
    private Label lblCantidad;

    @FXML
    private Label lblTotal;

    private Carrito carrito;

    @FXML
    public void initialize() {
        carrito = Carrito.getInstance();
        actualizarCarrito();
    }

      private void actualizarCarrito() {
        itemsCarrito.getChildren().clear();
        
       
        NodoCarrito actual = carrito.getCabeza();
        while (actual != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/VIEW/ItemCarrito.fxml"));
                VBox item = loader.load();
                ItemCarritoController controller = loader.getController();
                controller.setProducto(actual.producto, this);
                itemsCarrito.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
            actual = actual.siguiente;
        }
        
        // Usar el contador del carrito
        lblCantidad.setText(String.valueOf(carrito.getCantidadProductos()));
        lblTotal.setText(String.format("COP$ %.2f", carrito.calcularTotal()));
    }

    public void eliminarProducto(PRODUCTO producto) {
        carrito.eliminarProducto(producto.getId());
        actualizarCarrito();
    }

    @FXML
    private void realizarCompra() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Compra realizada");
        alert.setHeaderText("¡Gracias por tu compra!");
        alert.setContentText(String.format("Total: COP$ %.2f\nLos productos serán enviados pronto.", carrito.calcularTotal()));
        alert.showAndWait();
        
        carrito.vaciarCarrito();
        actualizarCarrito();
    }

     @FXML
    private void irACatalogo() {
        Stage stage = (Stage) itemsCarrito.getScene().getWindow();
        Navegacion.cambiarVista("/VIEW/CATALOGO.fxml", "JSHOP - Catálogo de Productos", stage);
    }
}