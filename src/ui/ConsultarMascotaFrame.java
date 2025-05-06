package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Mascota; // Asegúrate de que esta clase esté correctamente importada

public class ConsultarMascotaFrame extends JFrame {

    private JTextField idField;
    private JTextArea resultArea;
    private JButton searchButton;

    public ConsultarMascotaFrame() {
        setTitle("Consultar Mascota");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para la entrada del ID
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JLabel idLabel = new JLabel("ID de la Mascota:");
        idField = new JTextField(20);
        searchButton = new JButton("Buscar");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(searchButton);

        // Área de texto para mostrar resultados
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Agregar componentes al frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Acción del botón de búsqueda
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarMascotaPorId();
            }
        });
    }

    private void buscarMascotaPorId() {
        String id = idField.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Llamar al método buscarPorId de la clase Mascota
            Mascota resultado = Mascota.buscarPorId(id);

            if (resultado != null) {
                resultArea.setText(resultado.toString());
            } else {
                resultArea.setText("No se encontró ninguna mascota con el ID proporcionado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al buscar la mascota: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
