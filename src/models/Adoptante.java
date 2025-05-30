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
    
    public static Adoptante buscarPorCedula(int cedulaBuscada) {
        try (java.util.Scanner scanner = new java.util.Scanner(new java.io.File("data/adoptantes.txt"))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                if (partes.length == 5) {
                    int cedula = Integer.parseInt(partes[0]);
                    if (cedula == cedulaBuscada) {
                        String nombre = partes[1];
                        String apellido = partes[2];
                        int edad = Integer.parseInt(partes[3]);
                        String email = partes[4];
                        return new Adoptante(cedula, nombre, apellido, edad, email);
                    }
                }
            }
        } catch (java.io.IOException | NumberFormatException e) {
            System.err.println("Error al buscar adoptante: " + e.getMessage());
        }
        return null;
    }
}
