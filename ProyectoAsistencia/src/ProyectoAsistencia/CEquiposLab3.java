package ProyectoAsistencia;

import javax.swing.*;
import java.awt.event.*;

public class CEquiposLab3 extends JFrame implements ActionListener {

    JButton btnMonitor, btnCpu, btnTeclado, btnPizarra, btnVolver;

    public CEquiposLab3() {
        setTitle("Control de Equipos - LAB 3");
        setSize(400, 450);  // Ajustamos el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("CONTROL DE EQUIPOS - LAB 3", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 300, 30);  // Ajusta la posición del título
        add(lblTitulo);

        // Botón Monitor
        btnMonitor = new JButton("MONITOR");
        btnMonitor.setBounds(120, 70, 160, 40);  // Se ajusta el ancho y la posición
        btnMonitor.addActionListener(this);
        add(btnMonitor);

        // Botón CPU
        btnCpu = new JButton("CPU");
        btnCpu.setBounds(120, 120, 160, 40);  // Se ajusta el ancho y la posición
        btnCpu.addActionListener(this);
        add(btnCpu);

        // Botón Teclado
        btnTeclado = new JButton("TECLADO");
        btnTeclado.setBounds(120, 170, 160, 40);  // Se ajusta el ancho y la posición
        btnTeclado.addActionListener(this);
        add(btnTeclado);

        // Botón Pizarra Digital
        btnPizarra = new JButton("PIZARRA DIGITAL");
        btnPizarra.setBounds(120, 220, 160, 40);  // Se ajusta el ancho y la posición
        btnPizarra.addActionListener(this);
        add(btnPizarra);

        // Botón Volver
        btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(140, 300, 120, 40);  // Se ajusta la posición del botón Volver
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
            JOptionPane.showMessageDialog(this, "Monitor seleccionado en LAB 3.");
        } else if (e.getSource() == btnCpu) {
            JOptionPane.showMessageDialog(this, "CPU seleccionado en LAB 3.");
        } else if (e.getSource() == btnTeclado) {
            JOptionPane.showMessageDialog(this, "Teclado seleccionado en LAB 3.");
        } else if (e.getSource() == btnPizarra) {
            JOptionPane.showMessageDialog(this, "Pizarra Digital seleccionada en LAB 3.");
        }
    }

    public static void main(String[] args) {
        CEquiposLab3 cEquiposLab3 = new CEquiposLab3();
        cEquiposLab3.setVisible(true);
    }
}
