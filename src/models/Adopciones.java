package models;

public class Adopciones {
    private String EstadoAdopcion;
    private int cedulaAdoptante;
    private int idMascota;

    public Adopciones(){}

    public Adopciones(int cedulaAdoptante, int idMascota,String estadoAdopcion) {
        this.cedulaAdoptante = cedulaAdoptante;
        this.idMascota = idMascota;
        this.EstadoAdopcion = estadoAdopcion;
    }
    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }
    public int getIdMascota() {
        return idMascota;
    }
    public void setCedulaAdoptante(int cedulaAdoptante) {
        this.cedulaAdoptante = cedulaAdoptante;
    }
    public int getCedulaAdoptante() {
        return cedulaAdoptante;
    }
    public String getEstadoAdopcion() {
        return EstadoAdopcion;
    }
    public void setEstadoAdopcion(String estadoAdopcion) {
        EstadoAdopcion = estadoAdopcion;
    }

    @Override
    public String toString() {
        return cedulaAdoptante + "," + idMascota + "," + EstadoAdopcion;
    }

    public void guardar() {
        try (java.io.FileWriter writer = new java.io.FileWriter("data/adopciones.txt", true)) {
            writer.write(this.toString() + System.lineSeparator());
        } catch (java.io.IOException e) {
            System.err.println("Error al guardar la mascota: " + e.getMessage());
        }
    }

    public static java.util.ArrayList<Adopciones> obtenerTodas() {
        java.util.ArrayList<Adopciones> lista = new java.util.ArrayList<>();
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("data/adopciones.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    int cedulaAdoptante = Integer.parseInt(partes[0]);
                    int idMascota = Integer.parseInt(partes[1]);
                    String estadoAdopcion = partes[2];
                    lista.add(new Adopciones(cedulaAdoptante, idMascota, estadoAdopcion));
                }
            }
        } catch (java.io.IOException e) {
            System.err.println("Error al leer las adopciones: " + e.getMessage());
        }
        return lista;
    }
    
    public void actualizarEstadoAdopcion(String nuevoEstado) {
        java.util.ArrayList<Adopciones> lista = obtenerTodas();
        for (Adopciones adopcion : lista) {
            if (adopcion.getCedulaAdoptante() == this.cedulaAdoptante && adopcion.getIdMascota() == this.idMascota) {
                adopcion.setEstadoAdopcion(nuevoEstado);
            }
        }
        try (java.io.FileWriter writer = new java.io.FileWriter("data/adopciones.txt", false)) {
            for (Adopciones adopcion : lista) {
                writer.write(adopcion.toString() + System.lineSeparator());
            }
        } catch (java.io.IOException e) {
            System.err.println("Error al actualizar el estado de adopción: " + e.getMessage());
        }
        this.EstadoAdopcion = nuevoEstado;
    }
}
