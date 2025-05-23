package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Adoptante;
public class GuardarUsuariosFrame extends JFrame {

    public GuardarUsuariosFrame() {
        // Configuración básica de la ventana
        setTitle("Registro de Usuario");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10)); // 5 filas, 2 columnas

        // Crear etiquetas y campos de texto
        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();

        JLabel edadLabel = new JLabel("Edad:");
        JTextField edadField = new JTextField();

        JLabel generoLabel = new JLabel("Género:");
        JTextField generoField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel cedulaLabel = new JLabel("Cedula:");
        JTextField cedulaField = new JTextField();

        // Botón para guardar
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores ingresados
                int cedula=Integer.parseInt(cedulaField.getText());
                String nombre = nombreField.getText();
                int edad = Integer.parseInt(edadField.getText());
                String genero = generoField.getText();
                String email = emailField.getText();
                Adoptante adoptante=new Adoptante(cedula,nombre, genero, edad, email);
                // Aquí puedes guardar los datos en un modelo o base de datos
                adoptante.guardar();
                // Mostrar mensaje de confirmación
                JOptionPane.showMessageDialog(null, "Datos del usuario guardados correctamente.");
            }
        });

        // Agregar componentes al JFrame
        add(cedulaLabel);
        add(cedulaField);
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