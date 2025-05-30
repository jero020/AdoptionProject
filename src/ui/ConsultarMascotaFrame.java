package ui;

import javax.swing.*;
import models.Mascota;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConsultarMascotaFrame extends JFrame implements ActionListener {
    private JButton next;
    private JButton previous;
    private JButton buscarButton;
    private Mascota mascota;
    private int idMascota = 0;
    private ArrayList<Mascota> listaMascotas = new ArrayList<>();

    // Campos de texto para filtrar
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

    // Campos de texto para mostrar la información de la mascota
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

    private JLabel urlFotoLabel = new JLabel("URL de Foto:");
    private JTextField urlFotoField = new JTextField("");

    private JLabel generoLabel = new JLabel("Género:");
    private JTextField generoField = new JTextField("");

    public ConsultarMascotaFrame() {
        setTitle("Registro de Mascota");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con BoxLayout vertical
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel de filtros
        JPanel filtrosPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        filtrosPanel.setBorder(BorderFactory.createTitledBorder("Buscar mascotas"));
        filtrosPanel.add(razaFiltroLabel);
        filtrosPanel.add(razaFiltroTextField);
        filtrosPanel.add(generoFiltroLabel);
        filtrosPanel.add(generoFiltroComboBox);
        filtrosPanel.add(generoFiltroLabel);
        filtrosPanel.add(generoFiltroComboBox);
        filtrosPanel.add(estadoSaludFiltroLabel);
        filtrosPanel.add(estadoSaludFiltroTextField);
        filtrosPanel.add(new JLabel());
        buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(this);
        filtrosPanel.add(buscarButton);

        // Panel de información de mascota
        JPanel infoPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Información de la mascota"));
        infoPanel.add(nombreLabel);
        infoPanel.add(nombreField);
        infoPanel.add(animalLabel);
        infoPanel.add(animalField);
        infoPanel.add(edadLabel);
        infoPanel.add(edadField);
        infoPanel.add(razaLabel);
        infoPanel.add(razaField);
        infoPanel.add(idLabel);
        infoPanel.add(idField);
        infoPanel.add(estadoSaludLabel);
        infoPanel.add(estadoSaludField);
        infoPanel.add(descripcionLabel);
        infoPanel.add(descripcionField);
        infoPanel.add(urlFotoLabel);
        infoPanel.add(urlFotoField);
        infoPanel.add(generoLabel);
        infoPanel.add(generoField);

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

        // Panel de navegación
        JPanel navPanel = new JPanel();
        previous = new JButton("<");
        previous.addActionListener(this);
        next = new JButton(">");
        next.addActionListener(this);
        JButton btnAdoptar = new JButton("Adoptar Mascota");
        btnAdoptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            new AdopcionesUi().setVisible(true);
            }
        });
        navPanel.add(previous);
        navPanel.add(next);
        navPanel.add(btnAdoptar);

        // Agregar todo al panel principal
        mainPanel.add(filtrosPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(new JSeparator());
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(infoPanel);
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
            urlFotoField.setText("");
            generoField.setText("");
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
            urlFotoField.setText(this.mascota.getUrlFoto());
            generoField.setText(this.mascota.getGenero());
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
