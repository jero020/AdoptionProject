package ui;
import javax.swing.*;
import java.awt.event.*;
import models.Adoptante; // Asegúrate de que la ruta del paquete sea correcta



public class AdopcionesUi extends JFrame {
    private JTextField cedulaField;
    private JButton buscarButton;
    private JLabel resultadoLabel;

    public AdopcionesUi() {
        setTitle("Buscar Adoptante por Cédula");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel cedulaLabel = new JLabel("Ingrese su cédula:");
        cedulaLabel.setBounds(30, 20, 120, 25);
        add(cedulaLabel);

        cedulaField = new JTextField();
        cedulaField.setBounds(160, 20, 180, 25);
        add(cedulaField);

        buscarButton = new JButton("Buscar");
        buscarButton.setBounds(140, 60, 100, 30);
        add(buscarButton);

        resultadoLabel = new JLabel("");
        resultadoLabel.setBounds(30, 100, 320, 25);
        add(resultadoLabel);

        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cedula = cedulaField.getText();
                // Supón que el método buscarPorCedula retorna un objeto Adoptante o null
                Object adoptante = Adoptante.buscarPorCedula(Integer.parseInt(cedula));
                if (adoptante != null) {
                    resultadoLabel.setText("Adoptante encontrado: " + adoptante.toString());
                } else {
                    resultadoLabel.setText("No se encontró adoptante con esa cédula.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdopcionesUi().setVisible(true);
        });
    }
}