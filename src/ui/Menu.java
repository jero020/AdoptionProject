package ui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JFrame frame;
    
    public Menu() {
        frame = new JFrame("Adoption Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
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

        frame.add(btnUsuarios);
        frame.add(btnMascotas);
        setVisible(true);

    }
    public void setVisible(boolean b) {
      this.frame.setVisible(b);
    }
}