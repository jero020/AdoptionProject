package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuardarUsuariosFrame extends JFrame {
    public class GuardarUsuariosPanel extends JFrame {
        private JTextField nombreField;
        private JTextField apellidoField;
        private JTextField edadField;
        private JTextField emailField;
        private JButton guardarButton;

        public GuardarUsuariosPanel() {
            setTitle("Guardar Usuario");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(5, 2));

            // Labels and text fields
            add(new JLabel("Nombre:"));
            nombreField = new JTextField();
            add(nombreField);

            add(new JLabel("Apellido:"));
            apellidoField = new JTextField();
            add(apellidoField);

            add(new JLabel("Edad:"));
            edadField = new JTextField();
            add(edadField);

            add(new JLabel("Email:"));
            emailField = new JTextField();
            add(emailField);

            // Save button
            guardarButton = new JButton("Guardar");
            add(guardarButton);

            guardarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guardarUsuario();
                }
            });

            setVisible(true);
        }

        private void guardarUsuario() {
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            String edad = edadField.getText();
            String email = emailField.getText();

            // Logic to save user information
            JOptionPane.showMessageDialog(this, "Usuario guardado:\n" +
                    "Nombre: " + nombre + "\n" +
                    "Apellido: " + apellido + "\n" +
                    "Edad: " + edad + "\n" +
                    "Email: " + email);
        }
    }
}
