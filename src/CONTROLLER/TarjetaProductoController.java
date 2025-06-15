package CONTROLLER;

import MODEL.PRODUCTO;
import MODEL.Carrito;
import MODEL.Favoritos;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;

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
    private Button btnAgregarCarrito;
    private PRODUCTO producto;
    private Carrito carrito;
    private CATALOGOController catalogoController;

    @FXML
    public void initialize() {
        carrito = Carrito.getInstance();
    }

    public void setProducto(PRODUCTO producto) {
        this.producto = producto;
        lblNombre.setText(producto.getNombre());
        lblPrecio.setText(producto.getPrecioFormateado());
        lblRating.setText(String.valueOf(producto.getRating()));
        imgProducto.setImage(producto.getImagen());
    }

    // Método para pasar la referencia del CATALOGOController
    public void setCatalogoController(CATALOGOController controller) {
        this.catalogoController = controller;
    }

    @FXML
    private void agregarAlCarrito() {
        carrito.agregarProducto(producto);
        mostrarAlerta("Producto Agregado", producto.getNombre() + " ha sido añadido al carrito.", Alert.AlertType.INFORMATION);

        // ¡Actualizar el contador del carrito en el CATALOGOController! si, 2 horas para un numerito solo porque se veia bonito (quedo algo feo....)
        if (catalogoController != null) {
            catalogoController.actualizarContadorCarrito();
        }
    }

    @FXML
    private void agregarFavorito() {
        Favoritos favoritos = Favoritos.getInstance();
        favoritos.agregarProducto(producto);

        mostrarAlerta("Producto Favorito",
                producto.getNombre() + " ha sido añadido a tus favoritos.",
                Alert.AlertType.INFORMATION);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
