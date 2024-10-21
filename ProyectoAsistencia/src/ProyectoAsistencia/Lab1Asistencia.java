package ProyectoAsistencia;

import javax.swing.*;
import java.awt.event.*;

public class Lab1Asistencia extends JFrame implements ActionListener {

    JButton btnProgAplicada, btnTopicosInternet, btnInteligenciaNegocios, btnTallerIntegracion, btnVolver;

    public Lab1Asistencia() {
        setTitle("Control de Asistencia - Laboratorio 1");
        setSize(400, 400);  // Ajusta el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("CONTROL DE ASISTENCIA - LABORATORIO 1", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 300, 30);  // Ajusta la posición del título
        add(lblTitulo);

        // Botón Programación Aplicada I
        btnProgAplicada = new JButton("PROGRAMACIÓN APLICADA I (A)");
        btnProgAplicada.setBounds(100, 70, 200, 40);
        btnProgAplicada.addActionListener(this);
        add(btnProgAplicada);

        // Botón Tópicos Especiales en Internet de las Cosas
        btnTopicosInternet = new JButton("TOPICOS ESPECIALES EN INTERNET DE LAS COSAS (A)");
        btnTopicosInternet.setBounds(100, 120, 200, 40);
        btnTopicosInternet.addActionListener(this);
        add(btnTopicosInternet);

        // Botón Inteligencia de Negocios
        btnInteligenciaNegocios = new JButton("INTELIGENCIA DE NEGOCIOS (C)");
        btnInteligenciaNegocios.setBounds(100, 170, 200, 40);
        btnInteligenciaNegocios.addActionListener(this);
        add(btnInteligenciaNegocios);

        // Botón Taller de Integración de Sistemas
        btnTallerIntegracion = new JButton("TALLER DE INTEGRACION DE SISTEMAS (A)");
        btnTallerIntegracion.setBounds(100, 220, 200, 40);
        btnTallerIntegracion.addActionListener(this);
        add(btnTallerIntegracion);

        // Botón Volver
        btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(140, 280, 120, 40);
        btnVolver.addActionListener(this);
        add(btnVolver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnProgAplicada) {
            JOptionPane.showMessageDialog(this, "Programación Aplicada I seleccionado");
        } else if (e.getSource() == btnTopicosInternet) {
            JOptionPane.showMessageDialog(this, "Tópicos Especiales en Internet de las Cosas seleccionado");
        } else if (e.getSource() == btnInteligenciaNegocios) {
            JOptionPane.showMessageDialog(this, "Inteligencia de Negocios seleccionado");
        } else if (e.getSource() == btnTallerIntegracion) {
            JOptionPane.showMessageDialog(this, "Taller de Integración de Sistemas seleccionado");
        } else if (e.getSource() == btnVolver) {
            // Volver al menú de laboratorios
            this.dispose();  // Cierra la ventana actual
            MenuAsistencia menuAsistencia = new MenuAsistencia();  // Regresa al menú de laboratorios
            menuAsistencia.setVisible(true);
        }
    }

    public static void main(String[] args) {
        Lab1Asistencia lab1Asistencia = new Lab1Asistencia();
        lab1Asistencia.setVisible(true);
    }
}
