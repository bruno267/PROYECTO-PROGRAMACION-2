package CONTROLLER;

import MODEL.Navegacion;
import MODEL.Sesion;
import MODEL.USUARIO;
import MODEL.Carrito;
import MODEL.PRODUCTO;
import MODEL.ListaProducto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import MODEL.Carrito.NodoCarrito;
import MODEL.Compra;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CARRITOController {

    @FXML
    private VBox itemsCarrito;
    @FXML
    private Label lblCantidad;
    @FXML
    private Label lblTotal;
    @FXML
    private VBox resumenDetalle;

    private Carrito carrito;

    @FXML
    public void initialize() {
        carrito = Carrito.getInstance();
        actualizarCarrito();
    }

    private void actualizarCarrito() {
        itemsCarrito.getChildren().clear();
        resumenDetalle.getChildren().clear();

        NodoCarrito actual = carrito.getCabeza();
        while (actual != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/VIEW/ItemCarrito.fxml"));
                VBox item = loader.load();
                ItemCarritoController controller = loader.getController();
                controller.setProducto(actual.producto, this);
                itemsCarrito.getChildren().add(item);

                Label resumenItem = new Label(actual.producto.getNombre() + " - $" + actual.producto.getPrecio());
                resumenItem.setStyle("-fx-font-size: 12px;");
                resumenDetalle.getChildren().add(resumenItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
            actual = actual.siguiente;
        }

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
            new Alert(Alert.AlertType.WARNING, "No se ha iniciado sesión.").show();
            return;
        }

        StringBuilder factura = new StringBuilder();
        NodoCarrito actual = carrito.getCabeza();
        String fechaCompra = java.time.LocalDate.now().toString();

        while (actual != null) {
            PRODUCTO p = actual.producto;

            // Agrega una nueva compra al historial con fecha
            Sesion.agregarCompra(new Compra(p, java.time.LocalDate.now()));

            factura.append(p.getId()).append(";")
                    .append(p.getNombre()).append(";")
                    .append(p.getPrecio()).append(";")
                    .append(p.getRating()).append(";")
                    .append(p.getImagen().getUrl()).append(";")
                    .append(fechaCompra).append("\n");

            actual = actual.siguiente;
        }

        String nombreArchivo = "factura_" + usuario.getUsername() + ".txt";
        String rutaCompleta = System.getProperty("user.home") + java.io.File.separator + nombreArchivo;

        try {
            Files.write(Paths.get(rutaCompleta), factura.toString().getBytes());
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Error al guardar factura: " + e.getMessage()).show();
            return;
        }

        TextArea areaFactura = new TextArea("=== Factura generada ===\n\n" + factura + "\nGuardado en: " + rutaCompleta);
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
    private void irAMiCuenta() {
        Stage stage = (Stage) itemsCarrito.getScene().getWindow();
        Navegacion.cambiarVista("MICUENTA", "JSHOP - Mi Cuenta", stage);
    }

    @FXML
    private void cerrarSesion() {
        Stage stage = (Stage) itemsCarrito.getScene().getWindow();
        stage.close();
        Navegacion.cargarVista("LOGIN", "JSHOP - Inicio de Sesión");
    }
}
