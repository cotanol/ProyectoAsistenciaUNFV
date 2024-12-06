package View;

import Controller.UsuarioController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Util.ComponentFactory;
import Util.Constantes;
import Util.EstiloHover;
import Model.UsuarioModelo;
import java.util.ArrayList;

public class Login extends JFrame {
    
    // Componentes de la interfaz
    private JPanel panelHeader, panelMenu, panelImg;
    private JLabel imageLabel, lbTitulo, lbUsuario, lbPassword;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnEntrar, btnSalir;

    public Login() {
        // Foco transparente de seleccion
        UIManager.put("Button.select", new Color(0, 0, 0, 0)); 
        // Configuración de la ventana principal
        
        setSize(1000, 600);
        setTitle("Login del Gestionador");
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar componentes
        inicializarComponentes();
        agregarEventos();
    }

    private void inicializarComponentes() {
        // Panel Header
        panelHeader = new JPanel(null);
        panelHeader.setBounds(0, 0, 700, 130);
        panelHeader.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        add(panelHeader);

        // Logo de la universidad
        ImageIcon logo = new ImageIcon(getClass().getResource("/Imagenes/logo_villarreal.png"));
        Image scaledLogoImage = logo.getImage().getScaledInstance(240, 100, Image.SCALE_SMOOTH);
        imageLabel = new JLabel(new ImageIcon(scaledLogoImage));
        imageLabel.setBounds(30, 15, 240, 100);
        panelHeader.add(imageLabel);

        // Panel de menú
        panelMenu = new JPanel(null);
        panelMenu.setBounds(0, 130, 700, 570);
        panelMenu.setBackground(Constantes.COLOR_FONDO_PANEL);
        add(panelMenu);

        // Etiqueta de título
        lbTitulo = ComponentFactory.crearEtiqueta("INICIAR SESIÓN", 50, 40, 300, 45, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_NEGRO);
        panelMenu.add(lbTitulo);

        // Etiqueta y campo de texto para usuario
        lbUsuario = ComponentFactory.crearEtiqueta("USUARIO", 50, 95, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_TEXTO_NEGRO);
        panelMenu.add(lbUsuario);

        txtUsuario = ComponentFactory.crearCampoTexto(50, 130, 300, 35, Constantes.BORDER_NEGRO);
        panelMenu.add(txtUsuario);

        // Etiqueta y campo de texto para contraseña
        lbPassword = ComponentFactory.crearEtiqueta("CONTRASEÑA", 50, 180, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_TEXTO_NEGRO);
        panelMenu.add(lbPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 215, 300, 35);
        txtPassword.setFont(Constantes.FUENTE_TEXTFIELD);
        txtPassword.setBorder(Constantes.BORDER_NEGRO);
        panelMenu.add(txtPassword);

        // Botones de acción
        btnEntrar = ComponentFactory.crearBotonAccion("ENTRAR", 50, 280, 150, 60);
        panelMenu.add(btnEntrar);

        btnSalir = ComponentFactory.crearBotonAccion("SALIR", 220, 280, 150, 60);
        panelMenu.add(btnSalir);

        // Panel de imagen
        panelImg = new JPanel(null);
        panelImg.setBounds(700, 0, 300, 700); // Ajustamos la altura a 700
        add(panelImg);

        // Imagen de la universidad
        ImageIcon imageIcon1 = new ImageIcon(getClass().getResource("/Imagenes/central_villarreal.jpg"));
        Image image = imageIcon1.getImage();
        Image scaledImage1 = image.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon1 = new ImageIcon(scaledImage1);

        JLabel imageLabel1 = new JLabel(scaledImageIcon1);
        imageLabel1.setBounds(0, 0, 300, 700);
        panelImg.add(imageLabel1);
    }

    private void agregarEventos() {
        // Eventos de hover
        btnEntrar.addMouseListener(new EstiloHover.HoverAccionBoton(btnEntrar));
        btnSalir.addMouseListener(new EstiloHover.HoverAccionBoton(btnSalir));

        // Eventos de acción
        btnEntrar.addActionListener(e -> Entrar());
        btnSalir.addActionListener(e -> {
            Util.WindowFactory.windowClose("Cirre de Programa","CIERRE_PROGRAMA");
        });
    }

    private void Entrar() {
        UsuarioController usuarioControlador = new UsuarioController();
        ArrayList<String> usu = new ArrayList<>();
        ArrayList<String> pass = new ArrayList<>();

        for (UsuarioModelo usuario : usuarioControlador.enlistarUsuarioController()) {
            usu.add(usuario.getNombreUsuario());
            pass.add(usuario.getContrasena());
        }

        // Agregar usuario administrador por defecto
        usu.add("admin");
        pass.add("123");

        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtPassword.getPassword()).trim();

        if (usuario.isEmpty() || clave.isEmpty()) {
            Util.WindowFactory.errorLogin(" del login: Campos en Blanco","CAMPOS_EN_BLANCO");
            return;
        }

        boolean autenticado = false;
        for (int i = 0; i < usu.size(); i++) {
            if (usu.get(i).equals(usuario) && pass.get(i).equals(clave)) {
                autenticado = true;
                break;
            }
        }

        if (autenticado) {
            this.dispose();
            VentanaPrincipal vtn = new VentanaPrincipal();
            vtn.setUser(usuario);
            vtn.setVisible(true);
        } else {
            Util.WindowFactory.errorLogin(" del login: Usuario/Contraseña Incorrectos","USUARIO_CONTRASEÑA_INCORRECTOS");
        }
    }

    public static void main(String[] args) {
        Login ventanaLogin = new Login();
        ventanaLogin.setVisible(true);
    }
}
