package ui;

import javax.swing.*;
import models.Mascota;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class ConsultarMascotaFrame extends JFrame implements ActionListener {
    private JButton next;
    private JButton previous;
    private JButton buscarButton;
    private Mascota mascota;
    private int idMascota = 0;
    private ArrayList<Mascota> listaMascotas = new ArrayList<>();

    private JLabel razaFiltroLabel = new JLabel("Raza:");
    private JTextField razaFiltroTextField = new JTextField();
    private JTextField edadFiltroTextField = new JTextField();
    private JLabel generoFiltroLabel = new JLabel("Género:");
    private JComboBox<String> generoFiltroComboBox = new JComboBox<>();
    {
        generoFiltroComboBox.addItem("Macho");
        generoFiltroComboBox.addItem("Hembra");
    }

    private JLabel estadoSaludFiltroLabel = new JLabel("Estado de Salud:");
    private JTextField estadoSaludFiltroTextField = new JTextField();

    private JLabel nombreLabel = new JLabel("Nombre:");
    private JTextField nombreField = new JTextField("");

    private JLabel animalLabel = new JLabel("Tipo de animal:");
    private JTextField animalField = new JTextField("");

    private JLabel edadLabel = new JLabel("Edad:");
    private JTextField edadField = new JTextField("");

    private JLabel razaLabel = new JLabel("Raza:");
    private JTextField razaField = new JTextField("");

    private JLabel idLabel = new JLabel("ID:");
    private JTextField idField = new JTextField("");

    private JLabel estadoSaludLabel = new JLabel("Estado de Salud:");
    private JTextField estadoSaludField = new JTextField("");

    private JLabel descripcionLabel = new JLabel("Descripción:");
    private JTextField descripcionField = new JTextField("");

    private JLabel urlFotoLabel = new JLabel("Foto:");
    private JLabel fotoLabel = new JLabel();

    private JLabel generoLabel = new JLabel("Género:");
    private JTextField generoField = new JTextField("");

    public ConsultarMascotaFrame() {
        setTitle("Registro de Mascota");
        setSize(500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel filtrosPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        filtrosPanel.setBorder(BorderFactory.createTitledBorder("Buscar mascotas"));
        filtrosPanel.add(razaFiltroLabel);
        filtrosPanel.add(razaFiltroTextField);
        filtrosPanel.add(generoFiltroLabel);
        filtrosPanel.add(generoFiltroComboBox);
        filtrosPanel.add(estadoSaludFiltroLabel);
        filtrosPanel.add(estadoSaludFiltroTextField);
        filtrosPanel.add(new JLabel());
        buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(this);
        filtrosPanel.add(buscarButton);

        // Dentro del constructor (reemplaza el panel infoPanel y su contenido por esto)
        JPanel infoCardPanel = new JPanel();
        infoCardPanel.setLayout(new BorderLayout(10, 10));
        infoCardPanel.setBorder(BorderFactory.createTitledBorder("Información de la mascota"));

        // Imagen centrada arriba
        JPanel imagePanel = new JPanel();
        fotoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fotoLabel.setVerticalAlignment(SwingConstants.CENTER);
        fotoLabel.setPreferredSize(new Dimension(250, 250));
        imagePanel.add(fotoLabel);
        infoCardPanel.add(imagePanel, BorderLayout.NORTH);

        // Datos debajo de la imagen
        JPanel dataPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        dataPanel.add(nombreLabel);
        dataPanel.add(nombreField);
        dataPanel.add(animalLabel);
        dataPanel.add(animalField);
        dataPanel.add(edadLabel);
        dataPanel.add(edadField);
        dataPanel.add(razaLabel);
        dataPanel.add(razaField);
        dataPanel.add(idLabel);
        dataPanel.add(idField);
        dataPanel.add(estadoSaludLabel);
        dataPanel.add(estadoSaludField);
        dataPanel.add(descripcionLabel);
        dataPanel.add(descripcionField);
        dataPanel.add(generoLabel);
        dataPanel.add(generoField);

        infoCardPanel.add(dataPanel, BorderLayout.CENTER);

        // Agrega infoCardPanel en lugar de infoPanel
        mainPanel.add(infoCardPanel);

        JPanel navPanel = new JPanel();
        previous = new JButton("<");
        previous.addActionListener(this);
        next = new JButton(">");
        next.addActionListener(this);
        JButton btnAdoptar = new JButton("Adoptar Mascota");
        btnAdoptar.addActionListener(e -> new AdopcionesUi().setVisible(true));
        navPanel.add(previous);
        navPanel.add(next);
        navPanel.add(btnAdoptar);

        mainPanel.add(filtrosPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(new JSeparator());
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(infoCardPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(navPanel);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public void recargarMascota() {
        if (listaMascotas.isEmpty() || idMascota < 0 || idMascota >= listaMascotas.size()) {
            nombreField.setText("");
            animalField.setText("");
            edadField.setText("");
            razaField.setText("");
            idField.setText("");
            estadoSaludField.setText("");
            descripcionField.setText("");
            generoField.setText("");
            fotoLabel.setIcon(null);
            fotoLabel.setText("Sin imagen");
            return;
        }
        this.mascota = listaMascotas.get(idMascota);
        if (this.mascota != null) {
            nombreField.setText(this.mascota.getNombre());
            animalField.setText(this.mascota.getTipo());
            edadField.setText(String.valueOf(this.mascota.getEdad()));
            razaField.setText(this.mascota.getRaza());
            idField.setText(String.valueOf(this.mascota.getId()));
            estadoSaludField.setText(this.mascota.getEstadoSalud());
            descripcionField.setText(this.mascota.getDescripcion());
            generoField.setText(this.mascota.getGenero());
            try {
                URL url = this.mascota.getUrlFoto();
                if (url != null) {
                    BufferedImage image = ImageIO.read(url);
                    if (image != null) {
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                        fotoLabel.setIcon(icon);
                        fotoLabel.setText("");
                    } else {
                        fotoLabel.setIcon(null);
                        fotoLabel.setText("Imagen no disponible");
                    }
                }
            } catch (Exception e) {
                fotoLabel.setIcon(null);
                fotoLabel.setText("Error al cargar imagen");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            if (idMascota < listaMascotas.size() - 1) {
                idMascota++;
                recargarMascota();
            } else {
                JOptionPane.showMessageDialog(this, "No hay más mascotas.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == previous) {
            if (idMascota > 0) {
                idMascota--;
                recargarMascota();
            } else {
                JOptionPane.showMessageDialog(this, "No hay mascotas anteriores.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == buscarButton) {
            buscarMascotasClickButton();
        }
    }

    private void buscarMascotasClickButton() {
        idMascota = 0;
        try {
            int edad = -1;
            if (!edadFiltroTextField.getText().trim().isEmpty()) {
                edad = Integer.parseInt(edadFiltroTextField.getText().trim());
            }
            listaMascotas = Mascota.cargarMascotasPorCriterios(
                (String)generoFiltroComboBox.getSelectedItem(),
                edad,
                razaFiltroTextField.getText().trim(),
                estadoSaludFiltroTextField.getText().trim()
            );
            if (listaMascotas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron mascotas con los criterios de búsqueda.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                this.mascota = null;
                recargarMascota();
            } else {
                idMascota = 0;
                this.mascota = listaMascotas.get(idMascota);
                recargarMascota();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para la edad.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Mascota getMascotaActual() {
        return this.mascota;
    }
}
