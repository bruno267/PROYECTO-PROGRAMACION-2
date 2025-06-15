package CONTROLLER;

import MODEL.PRODUCTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ItemCarritoController {

    @FXML
    private ImageView imgProducto;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblPrecio;

    @FXML
    private Button btnEliminar;

    private PRODUCTO producto;
    private CARRITOController carritoController;

    public void setProducto(PRODUCTO producto, CARRITOController carritoController) {
        this.producto = producto;
        this.carritoController = carritoController;

        lblNombre.setText(producto.getNombre());
        lblPrecio.setText(producto.getPrecioFormateado());
        imgProducto.setImage(producto.getImagen());
    }

    @FXML
    private void eliminarProducto() {
        carritoController.eliminarProducto(producto);
    }
}
