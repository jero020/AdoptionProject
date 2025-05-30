package models;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.URL;

public class Mascota {
    private String nombre;
    private String animal; // Ejemplo: perro, gato, etc.
    private int edad;
    private String raza;
    private int id;
    private String estadoSalud;
    private String descripcion;
    private URL urlFoto;
    private String genero; // Ejemplo: macho, hembra

    // Constructor vacío
    public Mascota() {
    }

    public Mascota(String nombre, String animal, int edad, String raza, int id, String estadoSalud, String descripcion, URL urlFoto, String genero) {
        this.nombre = nombre;
        this.animal = animal;
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

    public URL getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(URL urlFoto) {
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
        return id + "," + nombre + "," + raza + "," + edad + "," + genero + "," + estadoSalud + "," + descripcion + "," + (urlFoto != null ? urlFoto.toString() : "");
    }

    // Método para crear una mascota a partir de una línea de texto 
    public static Mascota fromString(String linea) {
        String[] partes = linea.split(",", 8);
        try {
            return new Mascota(
                partes[1],
                partes[2],
                Integer.parseInt(partes[3]), // edad
                partes[4],
                Integer.parseInt(partes[0]), // id
                partes[5],
                partes[6],
                partes[7].isEmpty() ? null : new URL(partes[7]),
                partes[4] // OJO: esto parece incorrecto, probablemente debería ser partes[5] o partes[8] si hay un campo más
            );
        } catch (Exception e) {
            System.err.println("Error al parsear la mascota: " + e.getMessage());
            return null;
        }
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
                if (mascota != null && mascota.getId() == idBuscado) {
                    return mascota;
                }
            }
        } catch (java.io.IOException e) {
            System.err.println("Error al buscar la mascota: " + e.getMessage());
        }
        return null;
    }

    public static ArrayList<Mascota> cargarMascotasPorCriterios(String raza, int edad, String genero, String estadoSalud) {
        ArrayList<Mascota> mascotasFiltradas = new ArrayList<>();
        try (Scanner scanner = new Scanner(new java.io.File("data/mascotas.txt"))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Mascota mascota = Mascota.fromString(linea);

                if (mascota != null &&
                    (raza.isEmpty() || mascota.getRaza().equalsIgnoreCase(raza)) &&
                    (edad == -1 || mascota.getEdad() == edad) &&
                    (genero.isEmpty() || mascota.getGenero().equalsIgnoreCase(genero)) &&
                    (estadoSalud.isEmpty() || mascota.getEstadoSalud().equalsIgnoreCase(estadoSalud))) {
                    mascotasFiltradas.add(mascota);
                } else if (mascota != null) {
                    System.out.println("La mascota " + mascota.getNombre() + " no cumple con los criterios de búsqueda.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar las mascotas: " + e.getMessage());
        }

        return mascotasFiltradas;
    }

    public static int obtenerUltimoId() {
        int ultimoId = -1;
        try (Scanner scanner = new Scanner(new java.io.File("data/mascotas.txt"))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",", 2);
                if (partes.length > 0) {
                    try {
                        int id = Integer.parseInt(partes[0]);
                        ultimoId = id;
                    } catch (NumberFormatException e) {
                        // Ignorar líneas mal formateadas
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener el último ID: " + e.getMessage());
        }
        return ultimoId;
    }
}
