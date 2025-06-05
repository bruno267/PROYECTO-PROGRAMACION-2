package CONTROLLER;

import MODELO.PRODUCTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class TarjetaProductoController {

    @FXML
    private ImageView imgProducto;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblPrecio;

    @FXML
    private Label lblRating;

    @FXML
    private Button btnCarrito;

    @FXML
    private Button btnFavorito;

    private String idProducto; 
  
  public void setProducto(PRODUCTO producto) {
    this.idProducto = producto.getId();
    lblNombre.setText(producto.getNombre());
    lblPrecio.setText(producto.getPrecioFormateado());
    lblRating.setText(producto.getRatingFormateado());
    imgProducto.setImage(producto.getImagen());
}


    @FXML
    private void agregarCarrito() {
        System.out.println("Agregado al carrito: " + lblNombre.getText());
        // Aquí puedes llamar a tu lógica de carrito, por ejemplo:
        // Carrito.agregar(idProducto);
    }

    @FXML
    private void agregarFavorito() {
        System.out.println("Agregado a favoritos: " + lblNombre.getText());
        // Aquí puedes llamar a tu lógica de favoritos
        // Favoritos.agregar(idProducto);
    }
}
