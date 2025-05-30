package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JFrame frame;

    public Menu() {
        frame = new JFrame("Adoption Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(null);

        JButton btnUsuarios = new JButton("Guardar Usuarios");
        btnUsuarios.setBounds(50, 50, 200, 30);
        btnUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuardarUsuariosFrame().setVisible(true);
            }
        });

        JButton btnMascotas = new JButton("Guardar Mascotas");
        btnMascotas.setBounds(50, 100, 200, 30);
        btnMascotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuardarMascotaFrame().setVisible(true);
            }
        });

        JButton btnConsultarMascotas = new JButton("Consultar Mascotas");
        btnConsultarMascotas.setBounds(50, 150, 200, 30);
        btnConsultarMascotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarMascotaFrame().setVisible(true);
            }
        });

        JButton btnSolicitudAdopciones = new JButton("Solicitud de Adopciones");
        btnSolicitudAdopciones.setBounds(50, 200, 200, 30);
        btnSolicitudAdopciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPasswordField pwd = new JPasswordField();
                int option = JOptionPane.showConfirmDialog(
                        frame,
                        pwd,
                        "Ingrese la contraseña",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
                if (option == JOptionPane.OK_OPTION) {
                    String password = new String(pwd.getPassword());
                    if ("12345".equals(password)) {
                        new SolicitudAdopcionesFrame().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        frame.add(btnConsultarMascotas);
        frame.add(btnUsuarios);
        frame.add(btnMascotas);
        frame.add(btnSolicitudAdopciones);
        setVisible(true);
    }

    public void setVisible(boolean b) {
        this.frame.setVisible(b);
    }
}