package ProyectoAsistencia;

import javax.swing.*;
import java.awt.event.*;

public class CEquiposLab1 extends JFrame implements ActionListener {

    JButton btnMonitor, btnCpu, btnTeclado, btnPizarraDigital, btnVolver;

    public CEquiposLab1() {
        setTitle("Control de Equipos - LAB 1");
        setSize(400, 400);  // Ajusta el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("CONTROL DE EQUIPOS - LAB 1", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 300, 30);
        add(lblTitulo);

        // Botón Monitor
        btnMonitor = new JButton("MONITOR");
        btnMonitor.setBounds(100, 70, 200, 40);
        btnMonitor.addActionListener(this);
        add(btnMonitor);

        // Botón CPU
        btnCpu = new JButton("CPU");
        btnCpu.setBounds(100, 120, 200, 40);
        btnCpu.addActionListener(this);
        add(btnCpu);

        // Botón Teclado
        btnTeclado = new JButton("TECLADO");
        btnTeclado.setBounds(100, 170, 200, 40);
        btnTeclado.addActionListener(this);
        add(btnTeclado);

        // Botón Pizarra Digital
        btnPizarraDigital = new JButton("PIZARRA DIGITAL");
        btnPizarraDigital.setBounds(100, 220, 200, 40);
        btnPizarraDigital.addActionListener(this);
        add(btnPizarraDigital);

        // Botón Volver
        btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(140, 280, 120, 40);
        btnVolver.addActionListener(this);
        add(btnVolver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMonitor) {
            this.dispose();  // Cierra la ventana actual
            CEMonitorLab1 monitorLab1 = new CEMonitorLab1();  // Abre la ventana de Control de Monitores para LAB 1
            monitorLab1.setVisible(true);
        } else if (e.getSource() == btnCpu) {
            this.dispose();  // Cierra la ventana actual
            CECPULab1 cpuLab1 = new CECPULab1();  // Abre la ventana de Control de CPU para LAB 1
            cpuLab1.setVisible(true);
        } else if (e.getSource() == btnTeclado) {
            this.dispose();  // Cierra la ventana actual
            CETecladoLab1 tecladoLab1 = new CETecladoLab1();  // Abre la ventana de Control de CPU para LAB 1
            tecladoLab1.setVisible(true);
        } else if (e.getSource() == btnPizarraDigital) {
            this.dispose();  // Cierra la ventana actual
            CEPizarraLab1 pizarraLab1 = new CEPizarraLab1();  // Abre la ventana de Control de CPU para LAB 1
            pizarraLab1.setVisible(true);
        } else if (e.getSource() == btnVolver) {
            this.dispose();  // Cierra la ventana actual
            MenuControlEquipos menu = new MenuControlEquipos();  // Vuelve al menú de control de equipos
            menu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Opción aún no implementada.");
        }
    }

    public static void main(String[] args) {
        CEquiposLab1 lab1 = new CEquiposLab1();
        lab1.setVisible(true);
    }
}
