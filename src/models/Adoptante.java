package models;

public class Adoptante {
    private int cedula;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;

    public Adoptante(int cedula, String nombre, String apellido, int edad, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
        this.cedula=cedula;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
   

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return cedula+","+nombre+","+apellido+","+edad +","+email;
    }

    public void guardar() {
        try (java.io.FileWriter writer = new java.io.FileWriter("data/adoptantes.txt", true)) {
            writer.write(this.toString() + System.lineSeparator());
        } catch (java.io.IOException e) {
            System.err.println("Error al guardar adoptante " + e.getMessage()); 
        }
    
        System.out.println(this);
    }
}
