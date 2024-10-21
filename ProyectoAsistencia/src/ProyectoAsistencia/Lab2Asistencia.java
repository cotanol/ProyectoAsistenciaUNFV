package ProyectoAsistencia;

import javax.swing.*;
import java.awt.event.*;

public class Lab2Asistencia extends JFrame implements ActionListener {

    JButton btnProgAplicadaB, btnSeguridadRedesA, btnInteligenciaNegociosA, btnSeguridadRedesC, btnVolver;

    public Lab2Asistencia() {
        setTitle("Control de Asistencia - Laboratorio 2");
        setSize(400, 400);  // Ajusta el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("CONTROL DE ASISTENCIA - LABORATORIO 2", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 300, 30);  // Ajusta la posición del título
        add(lblTitulo);

        // Botón Programación Aplicada I (B)
        btnProgAplicadaB = new JButton("PROGRAMACIÓN APLICADA I (B)");
        btnProgAplicadaB.setBounds(100, 70, 200, 40);
        btnProgAplicadaB.addActionListener(this);
        add(btnProgAplicadaB);

        // Botón Seguridad de Redes y Sistemas de Información (A)
        btnSeguridadRedesA = new JButton("SEGURIDAD DE REDES Y SISTEMAS DE INFORMACION (A)");
        btnSeguridadRedesA.setBounds(100, 120, 200, 40);
        btnSeguridadRedesA.addActionListener(this);
        add(btnSeguridadRedesA);

        // Botón Inteligencia de Negocios (A)
        btnInteligenciaNegociosA = new JButton("INTELIGENCIA DE NEGOCIOS (A)");
        btnInteligenciaNegociosA.setBounds(100, 170, 200, 40);
        btnInteligenciaNegociosA.addActionListener(this);
        add(btnInteligenciaNegociosA);

        // Botón Seguridad de Redes y Sistemas de Información (C)
        btnSeguridadRedesC = new JButton("SEGURIDAD DE REDES Y SISTEMAS DE INFORMACION (C)");
        btnSeguridadRedesC.setBounds(100, 220, 200, 40);
        btnSeguridadRedesC.addActionListener(this);
        add(btnSeguridadRedesC);

        // Botón Volver
        btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(140, 280, 120, 40);
        btnVolver.addActionListener(this);
        add(btnVolver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnProgAplicadaB) {
            this.dispose();  // Cierra la ventana actual
            RegistroProgApliB registro = new RegistroProgApliB();  // Abre la ventana de Registro de Asistencia
            registro.setVisible(true);
        } else if (e.getSource() == btnSeguridadRedesA) {
            JOptionPane.showMessageDialog(this, "Seguridad de Redes y Sistemas de Información (A) seleccionado");
        } else if (e.getSource() == btnInteligenciaNegociosA) {
            JOptionPane.showMessageDialog(this, "Inteligencia de Negocios (A) seleccionado");
        } else if (e.getSource() == btnSeguridadRedesC) {
            JOptionPane.showMessageDialog(this, "Seguridad de Redes y Sistemas de Información (C) seleccionado");
        } else if (e.getSource() == btnVolver) {
            // Volver al menú de laboratorios
            this.dispose();  // Cierra la ventana actual
            MenuAsistencia menuAsistencia = new MenuAsistencia();  // Regresa al menú de laboratorios
            menuAsistencia.setVisible(true);
        }
    }

    public static void main(String[] args) {
        Lab2Asistencia lab2Asistencia = new Lab2Asistencia();
        lab2Asistencia.setVisible(true);
    }
}
