package ProyectoAsistencia;

import javax.swing.*;
import java.awt.event.*;

public class MenuAsistencia extends JFrame implements ActionListener {

    JButton btnLab1, btnLab2, btnLab3, btnLab4, btnLab5, btnLab6, btnVolver;

    public MenuAsistencia() {
        setTitle("Control de Asistencia");
        setSize(400, 400);  // Ajusta el tamaño si es necesario
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("CONTROL DE ASISTENCIA", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 300, 30);
        add(lblTitulo);

        // Botones para los 6 laboratorios (renombrados como LAB 1, LAB 2, etc.)
        btnLab1 = new JButton("LAB 1");
        btnLab1.setBounds(50, 70, 130, 40);
        btnLab1.addActionListener(this);
        add(btnLab1);

        btnLab2 = new JButton("LAB 2");
        btnLab2.setBounds(210, 70, 130, 40);
        btnLab2.addActionListener(this);
        add(btnLab2);

        btnLab3 = new JButton("LAB 3");
        btnLab3.setBounds(50, 120, 130, 40);
        btnLab3.addActionListener(this);
        add(btnLab3);

        btnLab4 = new JButton("LAB 4");
        btnLab4.setBounds(210, 120, 130, 40);
        btnLab4.addActionListener(this);
        add(btnLab4);

        btnLab5 = new JButton("LAB 5");
        btnLab5.setBounds(50, 170, 130, 40);
        btnLab5.addActionListener(this);
        add(btnLab5);

        btnLab6 = new JButton("LAB 6");
        btnLab6.setBounds(210, 170, 130, 40);
        btnLab6.addActionListener(this);
        add(btnLab6);

        // Botón Volver
        btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(140, 240, 120, 40);
        btnVolver.addActionListener(this);
        add(btnVolver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLab1) {
            // Abrir la ventana de Lab1Asistencia
            this.dispose();  // Cierra la ventana actual
            Lab1Asistencia lab1 = new Lab1Asistencia();  // Abre la ventana de Lab1Asistencia
            lab1.setVisible(true);
        } else if (e.getSource() == btnLab2) {
            this.dispose();  // Cierra la ventana actual
            Lab2Asistencia lab2 = new Lab2Asistencia();  // Abre la nueva ventana de Lab2Asistencia
            lab2.setVisible(true);
        } else if (e.getSource() == btnLab3) {
            JOptionPane.showMessageDialog(this, "LAB 3 seleccionado");
        } else if (e.getSource() == btnLab4) {
            JOptionPane.showMessageDialog(this, "LAB 4 seleccionado");
        } else if (e.getSource() == btnLab5) {
            JOptionPane.showMessageDialog(this, "LAB 5 seleccionado");
        } else if (e.getSource() == btnLab6) {
            JOptionPane.showMessageDialog(this, "LAB 6 seleccionado");
        } else if (e.getSource() == btnVolver) {
            // Volver al menú de opciones
            this.dispose();  // Cierra la ventana actual
            MenuDeOpciones menu = new MenuDeOpciones();  // Regresa al menú de opciones
            menu.setVisible(true);
        }
    }

    public static void main(String[] args) {
        MenuAsistencia asistencia = new MenuAsistencia();
        asistencia.setVisible(true);
    }
}
