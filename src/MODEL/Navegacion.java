package MODEL;
//realmente alguien lee los comentarios? bueno si acaso, esta clase consiste en poder navegar entre ventanas

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navegacion {

    public static void cargarVista(String fxmlPath, String title) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Navegacion.class.getResource(fxmlPath));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void cambiarVista(String fxmlPath, String title, Stage stageActual) {
        try {
            FXMLLoader loader = new FXMLLoader(Navegacion.class.getResource(fxmlPath));
            Parent root = loader.load();
            
            Stage stage = (Stage) stageActual.getScene().getWindow();
            stage.setTitle(title);
            stage.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}