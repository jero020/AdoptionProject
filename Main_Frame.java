import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import AdoptioProject.Mascota.*; // Importar la clase Mascota
public class Main_Frame extends JFrame {
    public static void main(String[] args) {
        Main_Frame frame = new Main_Frame();
        frame.inicializacion();
    }
    public void inicializacion() {
        // Configuración del marco principal
        this.setTitle("Bienvenida");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Crear un mensaje de bienvenida
        JLabel mensaje = new JLabel("¡Bienvenido a la aplicación!", SwingConstants.CENTER);
        this.add(mensaje, BorderLayout.CENTER);

        // Crear un panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        // Botón vacío
        JButton botonVacio = new JButton("Vacío");
        panelBotones.add(botonVacio);

        // Botón para cerrar el panel
        JButton botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cierra la aplicación
            }
        });
        panelBotones.add(botonCerrar);

        // Agregar el panel de botones al marco
        this.add(panelBotones, BorderLayout.SOUTH);

        // Hacer visible el marco
        this.setVisible(true);
    }
}