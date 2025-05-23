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
    private JButton adoptarButton;
    private Mascota mascota;
    private int idMascota = 1;
    private ArrayList<Mascota> listaMascotas = new ArrayList<>();

    // Campos de texto para filtrar
    private JLabel razaFiltroLabel = new JLabel("Raza:");
    private JTextField razaFiltroTextField = new JTextField();
    private JLabel edadFiltroLabel = new JLabel("Edad:");
    private JTextField edadFiltroTextField = new JTextField();
    private JLabel generoFiltroLabel = new JLabel("Género:");
    private JTextField generoFiltroTextField = new JTextField();
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
        // Configuración básica de la ventana
        setTitle("Registro de Mascota");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(16, 2, 10, 10)); // Ajustar filas y columnas

        // Crear botones
        next = new JButton(">");
        next.addActionListener(this);
        JButton btnUsuarios = new JButton("Guardar Usuarios");
        btnUsuarios.setBounds(50, 50, 200, 30);
        btnUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuardarUsuariosFrame().setVisible(true);
            }
        });

        previous = new JButton("<");
        previous.addActionListener(this);

        buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(this);

        // Agregar componentes de búsqueda
        add(razaFiltroLabel);
        add(razaFiltroTextField);
        add(edadFiltroLabel);
        add(edadFiltroTextField);
        add(generoFiltroLabel);
        add(generoFiltroTextField);
        add(estadoSaludFiltroLabel);
        add(estadoSaludFiltroTextField);
        add(new JLabel()); // Espacio vacío
        add(buscarButton);

        // Línea separadora
        JSeparator separator = new JSeparator();
        add(separator);
        add(new JLabel()); // Espacio vacío para mantener el diseño

        // Agregar componentes de información de la mascota
        add(nombreLabel);
        add(nombreField);
        add(animalLabel);
        add(animalField);
        add(edadLabel);
        add(edadField);
        add(razaLabel);
        add(razaField);
        add(idLabel);
        add(idField);
        add(estadoSaludLabel);
        add(estadoSaludField);
        add(descripcionLabel);
        add(descripcionField);
        add(urlFotoLabel);
        add(urlFotoField);
        add(generoLabel);
        add(generoField);
        add(previous);
        add(next);

        // Cargar la primera mascota

        // Hacer visible la ventana
        setVisible(true);
    }

    public void recargarMascota() {
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
        if (idMascota < listaMascotas.size() -1 ) { // Verificar que no se exceda el tamaño de la lista
            idMascota++;
            recargarMascota();
        } else {
            JOptionPane.showMessageDialog(this, "No hay más mascotas.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    } else if (e.getSource() == previous) {
        if (idMascota > 0) { // Verificar que no sea menor que 0
            idMascota--;
            this.mascota = listaMascotas.get(idMascota);
            recargarMascota();
        } else {
            JOptionPane.showMessageDialog(this, "No hay mascotas anteriores.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    else if (e.getSource() == buscarButton) {
        buscarMascotasClickButton();
    }
}

    private void buscarMascotasClickButton() {
        idMascota = 0;
        try {
            // Validar y convertir la edad
            int edad = -1; // Valor por defecto si no se especifica la edad
            if (!edadFiltroTextField.getText().trim().isEmpty()) {
                edad = Integer.parseInt(edadFiltroTextField.getText().trim());
            }
    
            // Cargar el atributo listaMascotas con los datos de las mascotas
            listaMascotas = Mascota.cargarMascotasPorCriterios(
                razaFiltroTextField.getText().trim(),
                edad,
                generoFiltroTextField.getText().trim(),
                estadoSaludFiltroTextField.getText().trim()
            );
    
            if (listaMascotas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron mascotas con los criterios de búsqueda.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                this.mascota = null;
                recargarMascota(); // Limpiar los campos si no hay resultados
            } else {
                // Reiniciar el índice al primer elemento de la lista
                idMascota = 0;
                this.mascota = listaMascotas.get(idMascota);
                recargarMascota(); // Mostrar la primera mascota de la lista
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para la edad.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public Mascota getMascotaActual() {
        return this.mascota;
    }
    
}
