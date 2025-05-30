package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Adoptante;

public class GuardarUsuariosFrame extends JFrame {

    public GuardarUsuariosFrame() {
        setTitle("Registro de Usuario");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        setResizable(false);

        // Crear campos de entrada
        JTextField nombreField = new JTextField(20);
        JTextField edadField = new JTextField(20);
        JTextField generoField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField cedulaField = new JTextField(20);

        // Crear panel con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Método auxiliar para crear filas de entrada
        mainPanel.add(createInputRow("Cédula:", cedulaField));
        mainPanel.add(createInputRow("Nombre:", nombreField));
        mainPanel.add(createInputRow("Edad:", edadField));
        mainPanel.add(createInputRow("Género:", generoField));
        mainPanel.add(createInputRow("Email:", emailField));

        // Botón para guardar
        JButton guardarButton = new JButton("Guardar");
        guardarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = nombreField.getText().trim();
                    String genero = generoField.getText().trim();
                    String email = emailField.getText().trim();
                    int edad = Integer.parseInt(edadField.getText().trim());
                    int cedula = Integer.parseInt(cedulaField.getText().trim());

                    // Validaciones básicas
                    if (nombre.isEmpty() || genero.isEmpty() || email.isEmpty() || cedulaField.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Adoptante adoptante = new Adoptante(cedula, nombre, genero,edad, email);
                    adoptante.guardar();

                    JOptionPane.showMessageDialog(null, "Datos del usuario guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    // Limpiar campos después de guardar
                    cedulaField.setText("");
                    nombreField.setText("");
                    edadField.setText("");
                    generoField.setText("");
                    emailField.setText("");

                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(guardarButton);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private JPanel createInputRow(String label, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setMaximumSize(new Dimension(300, 40));
        panel.add(new JLabel(label), BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }
}
