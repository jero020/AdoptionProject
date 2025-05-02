package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuardarUsuariosFrame extends JFrame {

    public GuardarUsuariosFrame() {
        // Configuración básica de la ventana
        setTitle("Registro de Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10)); // 5 filas, 2 columnas

        // Crear etiquetas y campos de texto
        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();

        JLabel edadLabel = new JLabel("Edad:");
        JTextField edadField = new JTextField();

        JLabel generoLabel = new JLabel("Género:");
        JTextField generoField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        // Botón para guardar
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores ingresados
                String nombre = nombreField.getText();
                int edad = Integer.parseInt(edadField.getText());
                String genero = generoField.getText();
                String email = emailField.getText();

                // Aquí puedes guardar los datos en un modelo o base de datos
                System.out.println("Datos del usuario:");
                System.out.println("Nombre: " + nombre);
                System.out.println("Edad: " + edad);
                System.out.println("Género: " + genero);
                System.out.println("Email: " + email);

                // Mostrar mensaje de confirmación
                JOptionPane.showMessageDialog(null, "Datos del usuario guardados correctamente.");
            }
        });

        // Agregar componentes al JFrame
        add(nombreLabel);
        add(nombreField);
        add(edadLabel);
        add(edadField);
        add(generoLabel);
        add(generoField);
        add(emailLabel);
        add(emailField);
        add(new JLabel()); // Espacio vacío
        add(guardarButton);

        // Hacer visible la ventana
        setVisible(true);
    }
}