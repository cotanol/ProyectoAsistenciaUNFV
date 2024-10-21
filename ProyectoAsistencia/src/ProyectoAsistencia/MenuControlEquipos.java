package ProyectoAsistencia;

import javax.swing.*;
import java.awt.event.*;

public class MenuControlEquipos extends JFrame implements ActionListener {

    JButton btnLab1, btnLab2, btnLab3, btnLab4, btnLab5, btnLab6, btnVolver;

    public MenuControlEquipos() {
        setTitle("Control de Equipos");
        setSize(400, 400);  // Ajusta el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("CONTROL DE EQUIPOS", SwingConstants.CENTER);
        lblTitulo.setBounds(100, 20, 200, 30);
        add(lblTitulo);

        // Botones para los laboratorios (renombrados como LAB 1, LAB 2, etc.)
        btnLab1 = new JButton("LAB 1");
        btnLab1.setBounds(50, 70, 120, 40);
        btnLab1.addActionListener(this);
        add(btnLab1);

        btnLab2 = new JButton("LAB 2");
        btnLab2.setBounds(220, 70, 120, 40);
        btnLab2.addActionListener(this);
        add(btnLab2);

        btnLab3 = new JButton("LAB 3");
        btnLab3.setBounds(50, 130, 120, 40);
        btnLab3.addActionListener(this);
        add(btnLab3);

        btnLab4 = new JButton("LAB 4");
        btnLab4.setBounds(220, 130, 120, 40);
        btnLab4.addActionListener(this);
        add(btnLab4);

        btnLab5 = new JButton("LAB 5");
        btnLab5.setBounds(50, 190, 120, 40);
        btnLab5.addActionListener(this);
        add(btnLab5);

        btnLab6 = new JButton("LAB 6");
        btnLab6.setBounds(220, 190, 120, 40);
        btnLab6.addActionListener(this);
        add(btnLab6);

        // Botón Volver
        btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(140, 260, 120, 40);
        btnVolver.addActionListener(this);
        add(btnVolver);
    }

     @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            this.dispose();  // Cierra la ventana actual
            MenuDeOpciones menu = new MenuDeOpciones();  // Vuelve al menú principal
            menu.setVisible(true);
        } else if (e.getSource() == btnLab1) {
            this.dispose();  // Cierra la ventana actual
            CEquiposLab1 lab1 = new CEquiposLab1();  // Abre la ventana de Control de Equipos para LAB 1
            lab1.setVisible(true);
        } else if (e.getSource() == btnLab2) {
            this.dispose();  // Cierra la ventana actual
            CEquiposLab2 lab2 = new CEquiposLab2();  // Abre la ventana de Control de Equipos para LAB 2
            lab2.setVisible(true);
        } else if (e.getSource() == btnLab3) {
            this.dispose();  // Cierra la ventana actual
            CEquiposLab3 lab3 = new CEquiposLab3();  // Abre la ventana de Control de Equipos para LAB 3
            lab3.setVisible(true);
        } else if (e.getSource() == btnLab4) {
            this.dispose();  // Cierra la ventana actual
            CEquiposLab4 lab4 = new CEquiposLab4();  // Abre la ventana de Control de Equipos para LAB 4
            lab4.setVisible(true);
        } else if (e.getSource() == btnLab5) {
            this.dispose();  // Cierra la ventana actual
            CEquiposLab5 lab5 = new CEquiposLab5();  // Abre la ventana de Control de Equipos para LAB 5
            lab5.setVisible(true);
        } else if (e.getSource() == btnLab6) {
            this.dispose();  // Cierra la ventana actual
            CEquiposLab6 lab6 = new CEquiposLab6();  // Abre la ventana de Control de Equipos para LAB 6
            lab6.setVisible(true);
        }
    }

    public static void main(String[] args) {
        MenuControlEquipos controlEquipos = new MenuControlEquipos();
        controlEquipos.setVisible(true);
    }
}
