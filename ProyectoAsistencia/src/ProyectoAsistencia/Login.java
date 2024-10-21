package ProyectoAsistencia;  // Ajusta el nombre del paquete si es necesario

import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    // Componentes de la interfaz
    JTextField txtUsuario;
    JPasswordField txtPassword;
    JButton btnIngresar, btnSalir;
    JLabel lblMensaje;

    public Login() {
        setTitle("Login - Gestión de Laboratorios");
        setSize(350, 300);  // Tamaño ajustado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("LOGIN - GESTIÓN DE LABORATORIOS", SwingConstants.CENTER);
        lblTitulo.setBounds(20, 20, 300, 30);
        add(lblTitulo);

        // Campo de Usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 70, 100, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 70, 150, 25);
        add(txtUsuario);

        // Campo de Contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 110, 100, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 110, 150, 25);
        add(txtPassword);

        // Botón Ingresar
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(70, 160, 100, 30);
        btnIngresar.addActionListener(this);
        add(btnIngresar);

        // Botón Salir
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(180, 160, 100, 30);
        btnSalir.addActionListener(this);
        add(btnSalir);

        // Mensaje de error
        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setBounds(20, 200, 300, 30);
        add(lblMensaje);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIngresar) {
            verificarCredenciales();
        } else if (e.getSource() == btnSalir) {
            System.exit(0);
        }
    }

    // Método para verificar credenciales
    private void verificarCredenciales() {
        String usuario = txtUsuario.getText();
        String contraseña = new String(txtPassword.getPassword());

        if (usuario.equals("admin") && contraseña.equals("123")) {
            lblMensaje.setText("¡Acceso concedido!");
            lblMensaje.setForeground(java.awt.Color.BLACK);
            this.dispose();  // Cierra la ventana de Login
            MenuDeOpciones menu = new MenuDeOpciones();  // Abre la ventana del menú de opciones
            menu.setVisible(true);
        } else {
            lblMensaje.setText("¡¡ERROR!! Usuario o Contraseña incorrecta");
            lblMensaje.setForeground(java.awt.Color.RED);
        }
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
    }
}
