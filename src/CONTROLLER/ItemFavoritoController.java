// ItemFavoritoController.java
package CONTROLLER;

import MODELO.Carrito;
import MODELO.PRODUCTO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ItemFavoritoController {

    @FXML private ImageView imgProducto;
    @FXML private Label lblNombre;
    @FXML private Label lblPrecio;
    @FXML private Button btnAgregarCarrito;
    @FXML private Button btnEliminar;

    private PRODUCTO producto;
    private FAVORITOSController favoritosController;

    public void setProducto(PRODUCTO producto, FAVORITOSController controller) {
        this.producto = producto;
        this.favoritosController = controller;
        
        lblNombre.setText(producto.getNombre());
        lblPrecio.setText(producto.getPrecioFormateado());
        imgProducto.setImage(producto.getImagen());
    }

    @FXML
    private void eliminarProducto() {
        favoritosController.eliminarProducto(producto);
    }

    @FXML
    private void agregarAlCarrito() {
        Carrito carrito = Carrito.getInstance();
        carrito.agregarProducto(producto);
        
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Producto Agregado");
        alerta.setHeaderText(null);
        alerta.setContentText(producto.getNombre() + " ha sido añadido al carrito.");
        alerta.showAndWait();
    }
}