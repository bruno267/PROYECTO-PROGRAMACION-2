package MODEL;

import javafx.scene.image.Image;

public class PRODUCTO {

    private String id;
    private String nombre;
    private double precio;
    private double rating;
    private Image imagen;

    public PRODUCTO(String id, String nombre, double precio, double rating, Image imagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.rating = rating;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public double getRating() {
        return rating;
    }

    public Image getImagen() {
        return imagen;
    }

    public String getPrecioFormateado() {
        return String.format("$ %.2f", precio);
    }

    public String getRatingFormateado() {
        return String.format("★ %.1f", rating);
    }
}
