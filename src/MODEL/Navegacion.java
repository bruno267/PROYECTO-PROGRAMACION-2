package MODEL;
//realmente alguien lee los comentarios? bueno si acaso, esta clase consiste en poder navegar entre ventanas

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Navegacion {

    public static void cargarVista(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Navegacion.class.getResource("/VIEW/" + fxmlPath + ".fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.err.println("Error al cargar vista: " + fxmlPath);
            e.printStackTrace();
            mostrarError("Error de navegación", "No se pudo cargar: " + fxmlPath);
        }
    }

    public static void cambiarVista(String fxmlPath, String title, Stage stageActual) {
        try {
            FXMLLoader loader = new FXMLLoader(Navegacion.class.getResource("/VIEW/" + fxmlPath + ".fxml"));
            Parent root = loader.load();

            stageActual.setTitle(title);
            stageActual.getScene().setRoot(root);
        } catch (Exception e) {
            System.err.println("Error al cambiar vista: " + fxmlPath);
            e.printStackTrace();
            mostrarError("Error de navegación", "No se pudo cambiar a: " + fxmlPath);
        }
    }

    private static void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
