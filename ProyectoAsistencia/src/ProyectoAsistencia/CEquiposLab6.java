package ProyectoAsistencia;

import javax.swing.*;
import java.awt.event.*;

public class CEquiposLab6 extends JFrame implements ActionListener {

    JButton btnMonitor, btnCpu, btnTeclado, btnVolver;

    public CEquiposLab6() {
        setTitle("Control de Equipos - LAB 6");
        setSize(300, 400);  // Tamaño reducido de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("CONTROL DE EQUIPOS - LAB 6", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 200, 30);  // Ajustamos el título
        add(lblTitulo);

        // Botón Monitor
        btnMonitor = new JButton("MONITOR");
        btnMonitor.setBounds(80, 70, 140, 40);  // Botón más pequeño
        btnMonitor.addActionListener(this);
        add(btnMonitor);

        // Botón CPU
        btnCpu = new JButton("CPU");
        btnCpu.setBounds(80, 120, 140, 40);  // Botón más pequeño
        btnCpu.addActionListener(this);
        add(btnCpu);

        // Botón Teclado
        btnTeclado = new JButton("TECLADO");
        btnTeclado.setBounds(80, 170, 140, 40);  // Botón más pequeño
        btnTeclado.addActionListener(this);
        add(btnTeclado);

        // Botón Volver (con menos separación)
        btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(90, 240, 120, 40);  // Botón centrado y más cerca de los anteriores
        btnVolver.addActionListener(this);
        add(btnVolver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            this.dispose();  // Cierra la ventana actual
            MenuControlEquipos menu = new MenuControlEquipos();  // Vuelve al menú de control de equipos
            menu.setVisible(true);
        } else if (e.getSource() == btnMonitor) {
            JOptionPane.showMessageDialog(this, "Monitor seleccionado en LAB 6.");
        } else if (e.getSource() == btnCpu) {
            JOptionPane.showMessageDialog(this, "CPU seleccionado en LAB 6.");
        } else if (e.getSource() == btnTeclado) {
            JOptionPane.showMessageDialog(this, "Teclado seleccionado en LAB 6.");
        }
    }

    public static void main(String[] args) {
        CEquiposLab6 cEquiposLab6 = new CEquiposLab6();
        cEquiposLab6.setVisible(true);
    }
}
