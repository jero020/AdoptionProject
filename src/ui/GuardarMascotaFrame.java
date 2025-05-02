package ui;

import javax.swing.*;
import models.Mascota;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuardarMascotaFrame extends JFrame {

    public GuardarMascotaFrame() {
        // Configuración básica de la ventana
        setTitle("Registro de Mascota");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(11, 2, 10, 10)); // 11 filas, 2 columnas

        // Crear etiquetas y campos de texto
        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();

        JLabel animalLabel = new JLabel("Tipo de animal:");
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

        JLabel urlFotoLabel = new JLabel("URL de Foto:");
        JTextField urlFotoField = new JTextField();

        JLabel generoLabel = new JLabel("Género:");
        JTextField generoField = new JTextField();

        // Botón para guardar
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores ingresados
                String nombre = nombreField.getText();
                String animal = animalField.getText();
                String edad = edadField.getText();
                String raza = razaField.getText();
                int id =Integer.parseInt(idField.getText());
                String estadoSalud = estadoSaludField.getText();
                String descripcion = descripcionField.getText();
                String urlFoto = urlFotoField.getText();
                String genero = generoField.getText();

                Mascota mascota = new Mascota(nombre, animal, Integer.parseInt(edad), raza, id, estadoSalud, descripcion, urlFoto, genero); 
                mascota.guardar();
                // Mostrar mensaje de confirmación
                JOptionPane.showMessageDialog(null, "Datos guardados correctamente.");
            }
        });

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
        add(new JLabel()); // Espacio vacío
        add(guardarButton);

        // Hacer visible la ventana
        setVisible(true);
    }
    
}