package ui;
import javax.swing.*;
import java.awt.event.*;
import models.Adopciones;

public class AdopcionesUi extends JFrame {
    private JTextField cedulaField;
    private JButton adoptarButton;
    private JLabel resultadoLabel;
    private JTextField mascotaIdField;

    public AdopcionesUi() {
        setTitle("Buscar Adoptante por Cédula");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel cedulaLabel = new JLabel("Ingrese su cédula:");
        cedulaLabel.setBounds(30, 20, 120, 25);
        add(cedulaLabel);

        cedulaField = new JTextField();
        cedulaField.setBounds(160, 20, 180, 25);
        add(cedulaField);

        adoptarButton = new JButton("Adoptar");
        adoptarButton.setBounds(140, 75, 100, 30);
        add(adoptarButton);

        resultadoLabel = new JLabel("");
        resultadoLabel.setBounds(30, 100, 320, 25);
        add(resultadoLabel);

        JLabel mascotaIdLabel = new JLabel("ID Mascota:");
        mascotaIdLabel.setBounds(30, 50, 120, 25);
        add(mascotaIdLabel);

        mascotaIdField = new JTextField();
        mascotaIdField.setBounds(160, 50, 180, 25);
        add(mascotaIdField);
         
        adoptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cedula = cedulaField.getText();
                String mascotaId = mascotaIdField.getText();

                if (cedula == null || cedula.trim().isEmpty()) {
                    resultadoLabel.setText("Por favor ingrese su cédula.");
                    return;
                }
                if (mascotaId == null || mascotaId.trim().isEmpty()) {
                    resultadoLabel.setText("Por favor ingrese el ID de la mascota.");
                    return;
                }

                // Aquí solo se guardan los valores, no los objetos
                try {
                    int cedulaInt = Integer.parseInt(cedula);
                    int mascotaIdInt = Integer.parseInt(mascotaId);

                    // Suponiendo que Adopciones tiene este constructor y método
                    Adopciones adopcion = new Adopciones(cedulaInt, mascotaIdInt,"Sin validar");
                    adopcion.guardar();

                    resultadoLabel.setText("Adopción registrada correctamente.");
                } catch (NumberFormatException ex) {
                    resultadoLabel.setText("Cédula o ID de mascota inválidos.");
                }
            }
        });
    }
}
