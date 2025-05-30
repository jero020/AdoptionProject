package ui;

import javax.swing.*;
import java.awt.*;
import models.Mascota;
import models.Adoptante;
import java.util.ArrayList;
import models.Adopciones;

public class SolicitudAdopcionesFrame extends JFrame {
    Mascota mascota;
    Adoptante adoptante;
    ArrayList<Adopciones> listaAdopciones = new ArrayList<>();
    public SolicitudAdopcionesFrame() {
        setTitle("Solicitud de Adopción");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        listaAdopciones=Adopciones.obtenerTodas();
        if (!listaAdopciones.isEmpty()) {
            Adopciones adopcion = listaAdopciones.get(0);
            mascota = Mascota.buscarPorId(adopcion.getIdMascota());
            adoptante = Adoptante.buscarPorCedula(adopcion.getCedulaAdoptante());
        }
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel datosPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        // Panel de Mascota
        JPanel mascotaPanel = new JPanel(new GridLayout(10, 1));
        mascotaPanel.setBorder(BorderFactory.createTitledBorder("Información de la mascota"));

        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();
        JLabel animalLabel = new JLabel("Animal:");
        JTextField animalField = new JTextField();
        JLabel edadLabel = new JLabel("Edad:");
        JTextField edadField = new JTextField();
        JLabel razaLabel = new JLabel("Raza:");
        JTextField razaField = new JTextField();
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel estadoSaludLabel = new JLabel("Estado de Salud:");
        JTextField estadoSaludField = new JTextField();
        JLabel descripcionLabel = new JLabel("Descripción:");
        JTextField descripcionField = new JTextField();
        JLabel urlFotoLabel = new JLabel("URL Foto:");
        JTextField urlFotoField = new JTextField();
        JLabel generoLabel = new JLabel("Género:");
        JTextField generoField = new JTextField();

        mascotaPanel.add(nombreLabel);
        mascotaPanel.add(nombreField);
        mascotaPanel.add(animalLabel);
        mascotaPanel.add(animalField);
        mascotaPanel.add(edadLabel);
        mascotaPanel.add(edadField);
        mascotaPanel.add(razaLabel);
        mascotaPanel.add(razaField);
        mascotaPanel.add(idLabel);
        mascotaPanel.add(idField);
        mascotaPanel.add(estadoSaludLabel);
        mascotaPanel.add(estadoSaludField);
        mascotaPanel.add(descripcionLabel);
        mascotaPanel.add(descripcionField);
        mascotaPanel.add(urlFotoLabel);
        mascotaPanel.add(urlFotoField);
        mascotaPanel.add(generoLabel);
        mascotaPanel.add(generoField);

        // Desactivar edición de los campos de información
        nombreField.setEditable(false);
        animalField.setEditable(false);
        edadField.setEditable(false);
        razaField.setEditable(false);
        idField.setEditable(false);
        estadoSaludField.setEditable(false);
        descripcionField.setEditable(false);
        urlFotoField.setEditable(false);
        generoField.setEditable(false);

        // Si tienes una instancia de Mascota, puedes poblar los campos así:
        if (mascota != null) {
            nombreField.setText(mascota.getNombre());
            edadField.setText(String.valueOf(mascota.getEdad()));
            razaField.setText(mascota.getRaza());
            idField.setText(String.valueOf(mascota.getId()));
            estadoSaludField.setText(mascota.getEstadoSalud());
            descripcionField.setText(mascota.getDescripcion());
            urlFotoField.setText(mascota.getUrlFoto());
            generoField.setText(mascota.getGenero());
        }
        // Panel de Adoptante
        JPanel adoptantePanel = new JPanel(new GridLayout(10, 1));
        adoptantePanel.setBorder(BorderFactory.createTitledBorder("Información del adoptante"));

        JLabel nombreAdoptanteLabel = new JLabel("Nombre:");
        JTextField nombreAdoptanteField = new JTextField();
        JLabel apellidoLabel = new JLabel("Apellido:");
        JTextField apellidoField = new JTextField();
        JLabel edadAdoptanteLabel = new JLabel("Edad:");
        JTextField edadAdoptanteField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel cedulaLabel = new JLabel("Cédula:");
        JTextField cedulaField = new JTextField();

        adoptantePanel.setLayout(new GridLayout(10, 1));
        adoptantePanel.add(nombreAdoptanteLabel);
        adoptantePanel.add(nombreAdoptanteField);
        adoptantePanel.add(apellidoLabel);
        adoptantePanel.add(apellidoField);
        adoptantePanel.add(edadAdoptanteLabel);
        adoptantePanel.add(edadAdoptanteField);
        adoptantePanel.add(emailLabel);
        adoptantePanel.add(emailField);
        adoptantePanel.add(cedulaLabel);
        adoptantePanel.add(cedulaField);

        nombreAdoptanteField.setEditable(false);
        apellidoField.setEditable(false);
        edadAdoptanteField.setEditable(false);
        emailField.setEditable(false);
        cedulaField.setEditable(false);

        if (adoptante != null) {
            nombreAdoptanteField.setText(adoptante.getNombre());
            apellidoField.setText(adoptante.getApellido());
            edadAdoptanteField.setText(String.valueOf(adoptante.getEdad()));
            emailField.setText(adoptante.getEmail());
            cedulaField.setText(String.valueOf(adoptante.getCedula()));
        }

        datosPanel.add(mascotaPanel);
        datosPanel.add(adoptantePanel);

        // Panel de botones
        JPanel botonesPanel = new JPanel();
        JButton aceptarBtn = new JButton("Aceptar");
        JButton rechazarBtn = new JButton("Rechazar");
        botonesPanel.add(aceptarBtn);
        botonesPanel.add(rechazarBtn);

        mainPanel.add(datosPanel, BorderLayout.CENTER);
        mainPanel.add(botonesPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Ejemplo de acciones para los botones
        aceptarBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Solicitud aceptada"));
        rechazarBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Solicitud rechazada"));
        setVisible(true);
    }
}
