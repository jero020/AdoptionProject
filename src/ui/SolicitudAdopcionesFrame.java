package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import models.Mascota;
import models.Adoptante;
import models.Adopciones;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SolicitudAdopcionesFrame extends JFrame {
    Mascota mascota;
    Adoptante adoptante;
    int indice = 0;
    ArrayList<Adopciones> listaAdopciones = new ArrayList<>();

    // Campos de texto
    JLabel fotoLabel = new JLabel();
    JLabel nombreLabel = new JLabel();
    JLabel tipoLabel = new JLabel();
    JLabel edadLabel = new JLabel();
    JLabel razaLabel = new JLabel();
    JLabel idLabel = new JLabel();
    JLabel estadoSaludLabel = new JLabel();
    JLabel descripcionLabel = new JLabel();
    JLabel generoLabel = new JLabel();

    JTextField nombreAdoptanteField = new JTextField();
    JTextField apellidoField = new JTextField();
    JTextField edadAdoptanteField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField cedulaField = new JTextField();

    public SolicitudAdopcionesFrame() {
        setTitle("Solicitud de Adopción");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);

        listaAdopciones = Adopciones.obtenerTodas();

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Navegación
        JPanel navegacionPanel = new JPanel();
        JButton beforeBtn = new JButton("Anterior");
        JButton nextBtn = new JButton("Siguiente");
        navegacionPanel.add(beforeBtn);
        navegacionPanel.add(nextBtn);
        mainPanel.add(navegacionPanel, BorderLayout.NORTH);

        // Panel mascota tipo card
        JPanel mascotaCard = new JPanel();
        mascotaCard.setLayout(new BoxLayout(mascotaCard, BoxLayout.Y_AXIS));
        mascotaCard.setBorder(BorderFactory.createTitledBorder("Mascota"));

        fotoLabel.setPreferredSize(new Dimension(200, 200));
        fotoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mascotaCard.add(fotoLabel);
        mascotaCard.add(Box.createVerticalStrut(10));
        mascotaCard.add(nombreLabel);
        mascotaCard.add(tipoLabel);
        mascotaCard.add(edadLabel);
        mascotaCard.add(razaLabel);
        mascotaCard.add(idLabel);
        mascotaCard.add(estadoSaludLabel);
        mascotaCard.add(descripcionLabel);
        mascotaCard.add(generoLabel);

        // Panel Adoptante
        JPanel adoptantePanel = new JPanel(new GridLayout(5, 2));
        adoptantePanel.setBorder(BorderFactory.createTitledBorder("Información del Adoptante"));
        adoptantePanel.add(new JLabel("Nombre:")); adoptantePanel.add(nombreAdoptanteField);
        adoptantePanel.add(new JLabel("Apellido:")); adoptantePanel.add(apellidoField);
        adoptantePanel.add(new JLabel("Edad:")); adoptantePanel.add(edadAdoptanteField);
        adoptantePanel.add(new JLabel("Email:")); adoptantePanel.add(emailField);
        adoptantePanel.add(new JLabel("Cédula:")); adoptantePanel.add(cedulaField);

        for (JTextField f : new JTextField[]{nombreAdoptanteField, apellidoField, edadAdoptanteField, emailField, cedulaField}) {
            f.setEditable(false);
        }

        JPanel centroPanel = new JPanel(new BorderLayout());
        centroPanel.add(mascotaCard, BorderLayout.CENTER);
        centroPanel.add(adoptantePanel, BorderLayout.SOUTH);
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
                nombreLabel.setText("Nombre: " + mascota.getNombre());
                tipoLabel.setText("Tipo: " + mascota.getTipo());
                edadLabel.setText("Edad: " + mascota.getEdad());
                razaLabel.setText("Raza: " + mascota.getRaza());
                idLabel.setText("ID: " + mascota.getId());
                estadoSaludLabel.setText("Estado de salud: " + mascota.getEstadoSalud());
                descripcionLabel.setText("Descripción: " + mascota.getDescripcion());
                generoLabel.setText("Género: " + mascota.getGenero());

                try {
                    URL url = mascota.getUrlFoto();
                    BufferedImage image = ImageIO.read(url);
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                    fotoLabel.setIcon(icon);
                    fotoLabel.setText("");
                } catch (Exception ex) {
                    fotoLabel.setIcon(null);
                    fotoLabel.setText("Foto no disponible");
                }
            }

            if (adoptante != null) {
                nombreAdoptanteField.setText(adoptante.getNombre());
                apellidoField.setText(adoptante.getApellido());
                edadAdoptanteField.setText(String.valueOf(adoptante.getEdad()));
                emailField.setText(adoptante.getEmail());
                cedulaField.setText(String.valueOf(adoptante.getCedula()));
            }
        };

        // Eventos botones
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
