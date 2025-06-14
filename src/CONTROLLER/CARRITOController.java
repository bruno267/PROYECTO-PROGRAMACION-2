package CONTROLLER;

import MODEL.Navegacion;
import MODEL.Sesion;
import MODEL.USUARIO;
import MODEL.Carrito;
import MODEL.PRODUCTO;
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
import MODEL.Carrito.NodoCarrito;
import java.io.IOException;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;


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
    USUARIO usuario = Sesion.getUsuario();
    if (usuario == null) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Error");
        alerta.setHeaderText("Usuario no identificado");
        alerta.setContentText("No se ha iniciado sesión.");
        alerta.show();
        return;
    }

    StringBuilder factura = new StringBuilder();
    factura.append("===== FACTURA JSHOP =====\n");
    factura.append("Tienda: JSHOP\n");
    factura.append("Usuario: ").append(usuario.getUsername()).append("\n\n");
    factura.append("Productos:\n");

    Carrito carrito = Carrito.getInstance();
    Carrito.NodoCarrito actual = carrito.getCabeza();

    while (actual != null) {
        PRODUCTO p = actual.producto;
        factura.append("- ").append(p.getNombre()).append(" | Precio: $")
               .append(p.getPrecio()).append("\n");
        actual = actual.siguiente;
    }

    factura.append("\nTotal: $").append(String.format("%.2f", carrito.calcularTotal())).append("\n");
    factura.append("==========================");

    // Guardar factura como archivo .txt en una ruta conocida
    String nombreArchivo = "factura_" + usuario.getUsername() + ".txt";
    String rutaCompleta = System.getProperty("user.home") + java.io.File.separator + nombreArchivo;

    try {
        java.nio.file.Files.write(
            java.nio.file.Paths.get(rutaCompleta),
            factura.toString().getBytes()
        );
    } catch (IOException e) {
        e.printStackTrace();
        Alert errorArchivo = new Alert(Alert.AlertType.ERROR);
        errorArchivo.setTitle("Error al guardar");
        errorArchivo.setHeaderText("No se pudo guardar la factura");
        errorArchivo.setContentText(e.getMessage());
        errorArchivo.show();
        return;
    }

    // Mostrar factura completa con la ruta del archivo
    TextArea areaFactura = new TextArea(factura.toString() + "\n\nGuardado en: " + rutaCompleta);
    areaFactura.setEditable(false);
    areaFactura.setWrapText(true);
    areaFactura.setPrefWidth(450);
    areaFactura.setPrefHeight(350);

    Dialog<String> dialogoFactura = new Dialog<>();
    dialogoFactura.setTitle("Factura generada");
    dialogoFactura.setHeaderText("¡Gracias por tu compra, " + usuario.getUsername() + "!");
    dialogoFactura.getDialogPane().setContent(areaFactura);
    dialogoFactura.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialogoFactura.showAndWait();

    carrito.vaciarCarrito();
    actualizarCarrito();
}


    @FXML
    private void irACatalogo() {
        Stage stage = (Stage) itemsCarrito.getScene().getWindow();
        Navegacion.cambiarVista("CATALOGO", "JSHOP - Catálogo de Productos", stage);
    }

    @FXML
    private void irAFavoritos() {
        Stage stage = (Stage) itemsCarrito.getScene().getWindow();
        Navegacion.cambiarVista("FAVORITOS", "JSHOP - Favoritos", stage);
    }

    @FXML
    private void cerrarSesion() {
        Stage stage = (Stage) itemsCarrito.getScene().getWindow();
        stage.close();
        Navegacion.cargarVista("LOGIN", "JSHOP - Inicio de Sesión");
    }

}
