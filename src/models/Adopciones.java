package models;

public class Adopciones {
    private Adoptante adoptante;
    private Mascota mascota;
    public Adopciones(){}
    public Adopciones(Adoptante adoptante, Mascota mascota){
        this.adoptante=adoptante;
        this.mascota=mascota;
    }
    public void setAdoptante(Adoptante adoptante){
        this.adoptante=adoptante;
    }
    public Adoptante getAdoptante(){
        return adoptante;
    }
    public void setMascota(Mascota mascota){
        this.mascota=mascota;
    }
    public Mascota getMascota(){
        return mascota;
    }
    public void guardar() {
        try (java.io.FileWriter writer = new java.io.FileWriter("data/adopciones.txt", true)) {
            writer.write(this.toString() + System.lineSeparator());
        } catch (java.io.IOException e) {
            System.err.println("Error al guardar la mascota: " + e.getMessage());
        }
    }
}
