package CONTROLLER;

import MODEL.Compra;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TarjetaHistorialController {

    @FXML
    private ImageView imgProducto;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblPrecio;
    @FXML
    private Label lblFecha;

    public void setCompra(Compra compra) {
        lblNombre.setText(compra.getProducto().getNombre());
        lblPrecio.setText("Precio: $" + compra.getProducto().getPrecio());
        lblFecha.setText("Fecha: " + compra.getFecha().toString());
        imgProducto.setImage(compra.getProducto().getImagen());
    }
}
