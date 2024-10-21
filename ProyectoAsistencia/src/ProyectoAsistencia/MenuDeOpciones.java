package ProyectoAsistencia;

import javax.swing.*;
import java.awt.event.*;

public class MenuDeOpciones extends JFrame implements ActionListener {

    JButton btnControlAsistencia, btnHorariosLaboratorio, btnControlEquipos, btnCerrarSesion;

    public MenuDeOpciones() {
        setTitle("Menú de Opciones");
        setSize(400, 400);  // Ajusta el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("MENÚ DE OPCIONES", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 300, 30);  // Ajusta la posición del título
        add(lblTitulo);

        // Botón Control de Asistencia
        btnControlAsistencia = new JButton("CONTROL DE ASISTENCIA");
        btnControlAsistencia.setBounds(100, 70, 200, 40);
        btnControlAsistencia.addActionListener(this);
        add(btnControlAsistencia);

        // Botón Horarios por Laboratorio
        btnHorariosLaboratorio = new JButton("HORARIOS POR LAB");
        btnHorariosLaboratorio.setBounds(100, 130, 200, 40);
        btnHorariosLaboratorio.addActionListener(this);
        add(btnHorariosLaboratorio);

        // Botón Control de Equipos
        btnControlEquipos = new JButton("CONTROL DE EQUIPOS");
        btnControlEquipos.setBounds(100, 190, 200, 40);
        btnControlEquipos.addActionListener(this);
        add(btnControlEquipos);

        // Botón Cerrar Sesión
        btnCerrarSesion = new JButton("CERRAR SESIÓN");
        btnCerrarSesion.setBounds(100, 250, 200, 40);
        btnCerrarSesion.addActionListener(this);
        add(btnCerrarSesion);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnControlAsistencia) {
            this.dispose();  // Cierra la ventana actual
            MenuAsistencia asistencia = new MenuAsistencia();  // Abre la ventana de MenuAsistencia
            asistencia.setVisible(true);
        } else if (e.getSource() == btnHorariosLaboratorio) {
            JOptionPane.showMessageDialog(this, "Horarios por Laboratorio seleccionado");
        } else if (e.getSource() == btnControlEquipos) {
            this.dispose();  // Cierra la ventana actual
            MenuControlEquipos controlEquipos = new MenuControlEquipos();  // Abre la ventana de Control de Equipos
            controlEquipos.setVisible(true);
        } else if (e.getSource() == btnCerrarSesion) {
            JOptionPane.showMessageDialog(this, "Cerrando sesión...");
            this.dispose();  // Cierra la ventana actual
            Login login = new Login();  // Vuelve a mostrar la pantalla de Login
            login.setVisible(true);
        }
    }

    public static void main(String[] args) {
        MenuDeOpciones menu = new MenuDeOpciones();
        menu.setVisible(true);
    }
}
