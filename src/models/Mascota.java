package models;

public class Mascota {
    private String nombre;
    private String animal; // Ejemplo: perro, gato, etc.
    private int edad;
    private String raza;
    private int id;
    private String estadoSalud;
    private String descripcion;
    private String urlFoto;
    private String genero; // Ejemplo: macho, hembra
    // Constructor
    public Mascota(String nombre, String animal, int edad, String raza, int id, String estadoSalud, String descripcion, String urlFoto, String genero) {
        this.nombre = nombre;
        this.animal= animal;
        this.edad = edad;
        this.raza = raza;
        this.id = id;
        this.estadoSalud = estadoSalud;
        this.descripcion = descripcion;
        this.urlFoto = urlFoto;
        this.genero = genero;
    }


    
    // Getters y Setters para los nuevos atributos
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return animal;
    }

    public void setTipo(String tipo) {
        this.animal = tipo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    // Método para mostrar información de la mascota
    @Override
    public String toString() {
        return id + "," + nombre + "," + raza + "," + edad + "," + genero + "," + estadoSalud + "," + descripcion + "," + urlFoto;
    }
    // Método para crear una mascota a partir de una línea de texto 
    public static Mascota fromString(String linea) { 
        String[] partes = linea.split(",", 8);
        return new Mascota(
            partes[1],
            partes[2],
            Integer.parseInt(partes[3]), // Convert 'edad' to int
            partes[4],
            Integer.parseInt(partes[0]), // Convert 'id' to int
            partes[5],
            partes[6],
            partes[7],
            partes[4] // Assuming the last parameter is 'genero'
        );
    }
    
    public void guardar() {
        try (java.io.FileWriter writer = new java.io.FileWriter("data/mascotas.txt", true)) {
            writer.write(this.toString() + System.lineSeparator());
        } catch (java.io.IOException e) {
            System.err.println("Error al guardar la mascota: " + e.getMessage());
        }
        
    
        System.out.println(this);
    }
    public static Mascota buscarPorId(int idBuscado) {
        try (java.util.Scanner scanner = new java.util.Scanner(new java.io.File("data/mascotas.txt"))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Mascota mascota = Mascota.fromString(linea);
                if (mascota.getId()==(idBuscado)) {
                    return mascota;
                }
            }
        } catch (java.io.IOException e) {
            System.err.println("Error al buscar la mascota: " + e.getMessage());
        }
        return null; // Retorna null si no se encuentra la mascota
    }
}
