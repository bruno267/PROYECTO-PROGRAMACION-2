
package CONTROLLER;

import MODEL.Navegacion;
import MODEL.Favoritos;
import MODEL.PRODUCTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.Parent;

public class FAVORITOSController {

    @FXML
    private VBox contenedorFavoritos;

    private Favoritos favoritos;

    @FXML
    public void initialize() {
        favoritos = Favoritos.getInstance();
        mostrarFavoritos();
    }

    private void mostrarFavoritos() {
        contenedorFavoritos.getChildren().clear();
        Favoritos.NodoFavorito actual = favoritos.getCabeza();
        
        while (actual != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/VIEW/ItemFavoritos.fxml"));
               Parent item = loader.load();
                
                ItemFavoritoController controller = loader.getController();
                controller.setProducto(actual.producto, this);
                
                contenedorFavoritos.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
            actual = actual.siguiente;
        }
    }

    public void eliminarProducto(PRODUCTO producto) {
        favoritos.eliminarProducto(producto.getId());
        mostrarFavoritos();
    }

    @FXML
    private void irACatalogo() {
        Stage stage = (Stage) contenedorFavoritos.getScene().getWindow();
        Navegacion.cambiarVista("CATALOGO", "JSHOP - Catálogo", stage);
    }

    @FXML
    private void irACarrito() {
        Stage stage = (Stage) contenedorFavoritos.getScene().getWindow();
        Navegacion.cambiarVista("CARRITO", "JSHOP - Carrito", stage);
    }
    
    @FXML
private void cerrarSesion() {
    Stage stage = (Stage) contenedorFavoritos.getScene().getWindow();
    stage.close();
    Navegacion.cargarVista("LOGIN", "JSHOP - Inicio de Sesión");
}
}
