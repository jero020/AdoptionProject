package ui;

import javax.swing.*;
import models.Mascota;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarMascotaFrame extends JFrame implements ActionListener {
    private JButton next;
    private JButton previous;
    private Mascota mascota;
    private int idMascota = 1;

    // Campos de texto para mostrar la información de la mascota
    private JLabel nombreLabel = new JLabel("Nombre:");
    private JTextField nombreField = new JTextField();

    private JLabel animalLabel = new JLabel("Tipo de animal:");
    private JTextField animalField = new JTextField();

    private JLabel edadLabel = new JLabel("Edad:");
    private JTextField edadField = new JTextField();

    private JLabel razaLabel = new JLabel("Raza:");
    private JTextField razaField = new JTextField();

    private JLabel idLabel = new JLabel("ID:");
    private JTextField idField = new JTextField();

    private JLabel estadoSaludLabel = new JLabel("Estado de Salud:");
    private JTextField estadoSaludField = new JTextField();

    private JLabel descripcionLabel = new JLabel("Descripción:");
    private JTextField descripcionField = new JTextField();

    private JLabel urlFotoLabel = new JLabel("URL de Foto:");
    private JTextField urlFotoField = new JTextField();

    private JLabel generoLabel = new JLabel("Género:");
    private JTextField generoField = new JTextField();

    public ConsultarMascotaFrame() {
        // Configuración básica de la ventana
        setTitle("Registro de Mascota");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(12, 2, 10, 10)); // 12 filas, 2 columnas

        // Crear botones
        next = new JButton(">");
        next.addActionListener(this);

        previous = new JButton("<");
        previous.addActionListener(this);

        // Agregar componentes al JFrame
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
        this.mascota = Mascota.buscarPorId(idMascota);
        recargarMascota();

        // Hacer visible la ventana
        setVisible(true);
    }

    public void recargarMascota() {
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
        } else {
            // Limpiar los campos si no hay mascota
            nombreField.setText("");
            animalField.setText("");
            edadField.setText("");
            razaField.setText("");
            idField.setText("");
            estadoSaludField.setText("");
            descripcionField.setText("");
            urlFotoField.setText("");
            generoField.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            idMascota++; // Incrementar el ID para la siguiente mascota
        } else if (e.getSource() == previous) {
            if (idMascota > 1) { // Evitar que el ID sea menor que 1
                idMascota--;
            } else {
                JOptionPane.showMessageDialog(this, "No hay mascotas anteriores.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        // Buscar la mascota por el nuevo ID
        this.mascota = Mascota.buscarPorId(idMascota);

        if (this.mascota != null) {
            recargarMascota(); // Recargar los datos de la mascota en los campos
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró una mascota con el ID: " + idMascota, "Aviso", JOptionPane.INFORMATION_MESSAGE);
            if (e.getSource() == next) {
                idMascota--; // Revertir el cambio si no se encuentra la mascota
            } else if (e.getSource() == previous) {
                idMascota++;
            }
        }
    }
}
