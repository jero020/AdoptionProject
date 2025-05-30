package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import models.Mascota;
import models.Adoptante;
import models.Adopciones;

public class SolicitudAdopcionesFrame extends JFrame {
    Mascota mascota;
    Adoptante adoptante;
    int indice = 0;
    ArrayList<Adopciones> listaAdopciones = new ArrayList<>();

    // Campos de Mascota
    JTextField nombreField = new JTextField();
    JTextField animalField = new JTextField();
    JTextField edadField = new JTextField();
    JTextField razaField = new JTextField();
    JTextField idField = new JTextField();
    JTextField estadoSaludField = new JTextField();
    JTextField descripcionField = new JTextField();
    JTextField urlFotoField = new JTextField();
    JTextField generoField = new JTextField();

    // Campos de Adoptante
    JTextField nombreAdoptanteField = new JTextField();
    JTextField apellidoField = new JTextField();
    JTextField edadAdoptanteField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField cedulaField = new JTextField();

    public SolicitudAdopcionesFrame() {
        setTitle("Solicitud de Adopción");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        listaAdopciones = Adopciones.obtenerTodas();

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Botones de navegación
        JPanel navegacionPanel = new JPanel();
        JButton beforeBtn = new JButton("Anterior");
        JButton nextBtn = new JButton("Siguiente");
        navegacionPanel.add(beforeBtn);
        navegacionPanel.add(nextBtn);
        mainPanel.add(navegacionPanel, BorderLayout.NORTH);

        // Panel Mascota
        JPanel mascotaPanel = new JPanel(new GridLayout(9, 2));
        mascotaPanel.setBorder(BorderFactory.createTitledBorder("Información de la Mascota"));
        mascotaPanel.add(new JLabel("Nombre:")); mascotaPanel.add(nombreField);
        mascotaPanel.add(new JLabel("Animal:")); mascotaPanel.add(animalField);
        mascotaPanel.add(new JLabel("Edad:")); mascotaPanel.add(edadField);
        mascotaPanel.add(new JLabel("Raza:")); mascotaPanel.add(razaField);
        mascotaPanel.add(new JLabel("ID:")); mascotaPanel.add(idField);
        mascotaPanel.add(new JLabel("Estado de Salud:")); mascotaPanel.add(estadoSaludField);
        mascotaPanel.add(new JLabel("Descripción:")); mascotaPanel.add(descripcionField);
        mascotaPanel.add(new JLabel("URL Foto:")); mascotaPanel.add(urlFotoField);
        mascotaPanel.add(new JLabel("Género:")); mascotaPanel.add(generoField);

        // Panel Adoptante
        JPanel adoptantePanel = new JPanel(new GridLayout(5, 2));
        adoptantePanel.setBorder(BorderFactory.createTitledBorder("Información del Adoptante"));
        adoptantePanel.add(new JLabel("Nombre:")); adoptantePanel.add(nombreAdoptanteField);
        adoptantePanel.add(new JLabel("Apellido:")); adoptantePanel.add(apellidoField);
        adoptantePanel.add(new JLabel("Edad:")); adoptantePanel.add(edadAdoptanteField);
        adoptantePanel.add(new JLabel("Email:")); adoptantePanel.add(emailField);
        adoptantePanel.add(new JLabel("Cédula:")); adoptantePanel.add(cedulaField);

        // Desactivar edición
        for (JTextField field : new JTextField[]{nombreField, animalField, edadField, razaField, idField, estadoSaludField, descripcionField, urlFotoField, generoField, nombreAdoptanteField, apellidoField, edadAdoptanteField, emailField, cedulaField}) {
            field.setEditable(false);
        }

        // Panel Central
        JPanel centroPanel = new JPanel(new GridLayout(2, 1));
        centroPanel.add(mascotaPanel);
        centroPanel.add(adoptantePanel);
        mainPanel.add(centroPanel, BorderLayout.CENTER);

        // Botones aceptar/rechazar
        JPanel accionesPanel = new JPanel();
        JButton aceptarBtn = new JButton("Aceptar");
        JButton rechazarBtn = new JButton("Rechazar");
        accionesPanel.add(aceptarBtn);
        accionesPanel.add(rechazarBtn);
        mainPanel.add(accionesPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        Runnable actualizarPantalla = () -> {
            if (listaAdopciones.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay solicitudes.");
                return;
            }

            Adopciones adopcionActual = listaAdopciones.get(indice);
            mascota = Mascota.buscarPorId(adopcionActual.getIdMascota());
            adoptante = Adoptante.buscarPorCedula(adopcionActual.getCedulaAdoptante());

            if (mascota != null) {
                nombreField.setText(mascota.getNombre());
                animalField.setText(mascota.getTipo());
                edadField.setText(String.valueOf(mascota.getEdad()));
                razaField.setText(mascota.getRaza());
                idField.setText(String.valueOf(mascota.getId()));
                estadoSaludField.setText(mascota.getEstadoSalud());
                descripcionField.setText(mascota.getDescripcion());
                urlFotoField.setText(mascota.getUrlFoto());
                generoField.setText(mascota.getGenero());
            }

            if (adoptante != null) {
                nombreAdoptanteField.setText(adoptante.getNombre());
                apellidoField.setText(adoptante.getApellido());
                edadAdoptanteField.setText(String.valueOf(adoptante.getEdad()));
                emailField.setText(adoptante.getEmail());
                cedulaField.setText(String.valueOf(adoptante.getCedula()));
            }
        };

        // Acciones de los botones
        beforeBtn.addActionListener(e -> {
            if (indice > 0) {
                indice--;
                actualizarPantalla.run();
            } else {
                JOptionPane.showMessageDialog(this, "No hay adopciones anteriores.");
            }
        });

        nextBtn.addActionListener(e -> {
            if (indice < listaAdopciones.size() - 1) {
                indice++;
                actualizarPantalla.run();
            } else {
                JOptionPane.showMessageDialog(this, "No hay más adopciones.");
            }
        });

        aceptarBtn.addActionListener(e -> {
            Adopciones adopcionActual = listaAdopciones.get(indice);
            adopcionActual.actualizarEstadoAdopcion("Aceptada");
            JOptionPane.showMessageDialog(this, "Solicitud aceptada.");
        });

        rechazarBtn.addActionListener(e -> {
            Adopciones adopcionActual = listaAdopciones.get(indice);
            adopcionActual.actualizarEstadoAdopcion("Rechazada");
            JOptionPane.showMessageDialog(this, "Solicitud rechazada.");
        });

        actualizarPantalla.run();
        setVisible(true);
    }
}
