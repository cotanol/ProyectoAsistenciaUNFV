package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import Util.ComponentFactory;
import Util.EstiloHover;
import Util.Constantes;
import Controller.*;
import Model.UsuarioModelo;

public class VentanaPrincipal extends JFrame {
    
    private UsuarioController usuarioControlador;
    
    // Paneles principales
    private JPanel panelLeft, panelDerecho;

    // CardLayout
    private CardLayout cardLayout;

    // Ventanas derechas
    private VentanaRight1 ventanaRight1;
    private VentanaRight2 ventanaRight2;
    private VentanaRight3 ventanaRight3;
    private VentanaRight4 ventanaRight4;
    // Botones del menú
    private JButton btnRegistroUsuarios, btnControlAsistencia, btnControlEquipos, btnHorariosLaboratorio, btnCerrarSesion;

    // Elementos del panel izquierdo
    private JLabel lbImagenLogo, lbImagenUser, lbNameUser;

    // Botón actualmente seleccionado
    private JButton botonSeleccionado;
    
    public VentanaPrincipal() {
        // Foco transparente de seleccion
        UIManager.put("Button.select", new Color(0, 0, 0, 0));         
        // Configuración de la ventana principal
        setSize(1600, 900);
        setTitle("GESTOR DE LABORATORIOS");
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar componentes
        inicializarComponentes();
        
        // Configurar el menú lateral
        configurarMenu();

        // Mostrar panel inicial
        botonSeleccionado = btnRegistroUsuarios; // Establecer el botón predeterminado
        actualizarEstadoBotones();
        cardLayout.show(panelDerecho, "RegistroUsuarios");
        usuarioControlador = new UsuarioController();
    }

    private void inicializarComponentes() {
        // Panel izquierdo
        panelLeft = new JPanel(null);
        panelLeft.setBounds(0, 0, 300, 900);
        panelLeft.setBackground(Constantes.COLOR_BASE_BOTONES);
        add(panelLeft);
        
        // Panel logo
        JPanel panelLogo = new JPanel(null);
        panelLogo.setBounds(0, 0, 300, 150);
        panelLogo.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        panelLeft.add(panelLogo);

        // Imagen del logo
        ImageIcon imagenLogo = new ImageIcon("Images/logo_villarreal.png");
        Image imagenEscaladaLogo = imagenLogo.getImage().getScaledInstance(250, 110, Image.SCALE_SMOOTH);
        lbImagenLogo = new JLabel(new ImageIcon(imagenEscaladaLogo), SwingConstants.CENTER);
        lbImagenLogo.setBounds(20, 10, 260, 130);
        panelLogo.add(lbImagenLogo);

        // Panel usuario
        JPanel panelUser = new JPanel(null);
        panelUser.setBounds(0, 150, 300, 290);
        panelUser.setOpaque(false);
        panelLeft.add(panelUser);

        // Imagen del usuario
        ImageIcon imagenUser = new ImageIcon("Images/user_penguin.png");
        Image imagenEscaladaUser = imagenUser.getImage().getScaledInstance(195, 195, Image.SCALE_SMOOTH);
        lbImagenUser = new JLabel(new ImageIcon(imagenEscaladaUser), SwingConstants.CENTER);
        lbImagenUser.setBounds(0, 7, 300, 205);
        panelUser.add(lbImagenUser);

        // Nombre del usuario
        lbNameUser = ComponentFactory.crearEtiqueta("Usuario", 0, 220, 300, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_TEXTO_BLANCO);
        lbNameUser.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        lbNameUser.setOpaque(true);
        lbNameUser.setHorizontalAlignment(SwingConstants.CENTER);
        panelUser.add(lbNameUser);
         
        // Panel derecho con CardLayout
        cardLayout = new CardLayout();
        panelDerecho = new JPanel(cardLayout);
        panelDerecho.setBounds(300, 0, 1300, 900);
        add(panelDerecho);

        // Crear las instancias de las ventanas derechas
        UsuarioController usuarioController = new UsuarioController(this);
        HorarioLaboratorioController horarioController = new HorarioLaboratorioController(this);
        AsistenciaController asistenciaController = new AsistenciaController(this);
        LaboratorioController laboratorioController = new LaboratorioController(this);
        EquipoController equipoController = new EquipoController(this);
        AsignaturaController asignaturaController = new AsignaturaController(this);
        HorariosAlumnoController horariosAlumnoController = new HorariosAlumnoController(this);
        AlumnoController alumnoController = new AlumnoController(this);
        
        ventanaRight1 = new VentanaRight1(usuarioController);
        ventanaRight3 = new VentanaRight3(equipoController, horarioController, laboratorioController);
        ventanaRight2 = new VentanaRight2(laboratorioController, asistenciaController, horarioController, horariosAlumnoController, alumnoController);
        ventanaRight4 = new VentanaRight4(horarioController, usuarioController, laboratorioController, asignaturaController);
        
        ventanaRight1.sincronizarVentanas(ventanaRight2,ventanaRight3,ventanaRight4);
        ventanaRight2.sincronizarVentanas(ventanaRight1,ventanaRight3,ventanaRight4);
        ventanaRight3.sincronizarVentanas(ventanaRight1,ventanaRight2,ventanaRight4);
        ventanaRight4.sincronizarVentanas(ventanaRight1,ventanaRight2,ventanaRight3);
        
        // Agregar ventanas al CardLayout
        panelDerecho.add(ventanaRight1, "RegistroUsuarios");
        panelDerecho.add(ventanaRight2, "ControlAsistencia");
        panelDerecho.add(ventanaRight3, "ControlEquipos");
        panelDerecho.add(ventanaRight4, "Horarios");    
    }

    private void configurarMenu() {
        // Crear y configurar botones del menú
        btnRegistroUsuarios = ComponentFactory.crearBotonMenu("Registro de Usuarios");
        btnControlAsistencia = ComponentFactory.crearBotonMenu("Control de Asistencia");
        btnControlEquipos = ComponentFactory.crearBotonMenu("Control de Equipos");
        btnHorariosLaboratorio = ComponentFactory.crearBotonMenu("Horarios de Laboratorio");
        btnCerrarSesion = ComponentFactory.crearBotonAccion("Cerrar Sesión", 50, 730, 200, 70);

        // Establecer posiciones
        btnRegistroUsuarios.setBounds(0, 410, 300, 70);
        btnControlAsistencia.setBounds(0, 480, 300, 70);
        btnControlEquipos.setBounds(0, 550, 300, 70);
        btnHorariosLaboratorio.setBounds(0, 620, 300, 70);

        // Añadir botones al panel izquierdo
        panelLeft.add(btnRegistroUsuarios);
        panelLeft.add(btnControlAsistencia);
        panelLeft.add(btnControlEquipos);
        panelLeft.add(btnHorariosLaboratorio);
        panelLeft.add(btnCerrarSesion);

        // Asignar eventos de hover y clic directamente
        JButton[] botones = {btnRegistroUsuarios, btnControlAsistencia, btnControlEquipos, btnHorariosLaboratorio};
        String[] nombresPaneles = {"RegistroUsuarios", "ControlAsistencia", "ControlEquipos", "Horarios"};
        for (int i = 0; i < botones.length; i++) {
            JButton boton = botones[i];
            String nombrePanel = nombresPaneles[i];

            // Asignar eventos de hover y clic
            boton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!boton.equals(botonSeleccionado)) {
                        boton.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
                        boton.setForeground(Constantes.COLOR_TEXTO_BLANCO);
                    }
                    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!boton.equals(botonSeleccionado)) {
                        boton.setBackground(Constantes.COLOR_BASE_BOTONES);
                        boton.setForeground(Constantes.COLOR_TEXTO_NEGRO);
                    }
                }
            });

            boton.addActionListener(e -> {
                botonSeleccionado = boton;
                actualizarEstadoBotones();
                cardLayout.show(panelDerecho, nombrePanel);
            });
        }

        // Configurar el botón de cerrar sesión
        btnCerrarSesion.addMouseListener(new EstiloHover.HoverAccionBoton(btnCerrarSesion));
        btnCerrarSesion.addActionListener(e -> {
            this.dispose();
            Util.WindowFactory.windowClose("Cirre de Sesión","CIERRE_SESION", lbNameUser.getText());
        });
    }

    private void actualizarEstadoBotones() {
        // Actualizar el estilo de los botones según cuál está seleccionado
        JButton[] botones = {btnRegistroUsuarios, btnControlAsistencia, btnControlEquipos, btnHorariosLaboratorio};
        for (JButton boton : botones) {
            if (boton.equals(botonSeleccionado)) {
                boton.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
                boton.setForeground(Constantes.COLOR_TEXTO_BLANCO);
            } else {
                boton.setBackground(Constantes.COLOR_BASE_BOTONES);
                boton.setForeground(Constantes.COLOR_TEXTO_NEGRO);
            }
        }
    }

    public void setUser(String user) {
        lbNameUser.setText(user);
    }
    
    public void permisosDocente(UsuarioModelo usuario) {
        if (usuario.getTipoUsuario().equals("DOCENTE")) {
            btnRegistroUsuarios.setEnabled(false);
            btnControlEquipos.setEnabled(false);
            btnHorariosLaboratorio.setEnabled(false);
            
            botonSeleccionado = btnControlAsistencia;
            actualizarEstadoBotones();
            cardLayout.show(panelDerecho, "ControlAsistencia");
        }
    }
    
    public UsuarioModelo obtenerElUsuario(String nombreUsuario) {
        return usuarioControlador.obtenerUsuarioPorNombreController(nombreUsuario);
    }


    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
    }
}
