package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import Model.*;
import Controller.UsuarioController;
import Util.ComponentFactory;
import java.util.ArrayList;
import Controller.EquipoController;

public class Ventana01RegistrosDeUsuarios extends JFrame {

    // Variables de instancia
    private int idUsuario;
    private int numeroLaboratorio;

    private JPanel panelLeft, panelDerecho;
    private JPanel panelLogo, panelUser;
    private JButton btnRegistroUsuarios, btnControlAsistencia, btnControlEquipos, btnHorariosLaboratorio, btnCerrarSesion;
    private JLabel lbImagenUser, lbImagenLogo, lbNameUser;

    // Paneles derechos
    private JPanel panelRight1, panelRight2, panelRight3, panelRight4, panelRightConfig;

    // Componentes de panelRight1 (Registro de Usuarios)
    private JLabel lbRegistrosUsuarios;
    private JPanel subPanel1;
    private JLabel lbNombres, lbApellidos, lbUsuario, lbTipoDeDocumento, lbNumeroDeContacto, lbContraseña, lbNumeroDeDocumento, lbCargo, lbEmail;
    private JTextField txtNombres, txtApellidos, txtUsuario, txtNumeroDeContacto, txtContraseña, txtNumeroDeDocumento, txtEmail;
    private JComboBox<String> cbTipoDeDocumento, cbCargo;
    private JButton btnAgregar, btnModificar, btnEliminar;
    private JLabel nombreSubPanel1_1, nombreSubPanel1_2;
    private JScrollPane scrollSubPanel1_2;
    private JTable tablaUsuarios;
    private DefaultTableModel modeloUsuario;

    // Componentes de panelRight3 (Control de Equipos)
    private JLabel lbTituloPantalla1, lbEquiposDisponiblesLabel, lbTituloPantalla2, lbRegistroEquiposLabel, lbListaEquiposLabel, lbTipoEquipoLabel, lbLaboratorioLabel, lbEstadoLabel, lbNumeroSerieLabel, lbCodigoPatrimonialLabel;
    private JTextField txtNumeroSerie, txtCodigoPatrimonial;
    private JComboBox<String> cbTipoEquipo, cbLaboratorio, cbEstado;
    private JButton btnAgregarEquipo, btnModificarEquipo, btnEliminarEquipo, btnConfiguracion;
    private JTable tablaEquiposDisponibles, tablaEquiposRegistrados;
    private JScrollPane scrollTablaEquiposDisponibles, scrollTablaEquiposRegistrados;
    private DefaultTableModel modeloEquiposDisponibles, modeloEquiposRegistrados;

    // Modelos
    private ArrayList<UsuarioModelo> listaUsuarios;
    private ArrayList<EquipoModelo> listaEquipos;
    private UsuarioController usuarioControlador;
    private EquipoController equipoController;
    private EquipoModelo equipo;

    // Colores
    private static final Color COLOR_BASE_BOTONES = new Color(255, 152, 0);
    private static final Color COLOR_HOVER_SELECCIONADO1 = new Color(233, 113, 50);
    private static final Color COLOR_HOVER_SELECCIONADO2 = new Color(255, 198, 66);
    private static final Color COLOR_TEXTO_BLANCO = Color.WHITE;
    private static final Color COLOR_TEXTO_NEGRO = Color.BLACK;
    private static final Color COLOR_FONDO_PANEL = new Color(238, 238, 238);

    // Fuentes
    private static final Font FUENTE_TITULO = new Font("Poppins", Font.BOLD, 50);
    private static final Font FUENTE_SUBTITULO = new Font("Poppins", Font.BOLD, 30);
    private static final Font FUENTE_LABEL = new Font("Poppins", Font.BOLD, 20);
    private static final Font FUENTE_TEXTFIELD = new Font("Poppins", Font.PLAIN, 18);
    private static final Font FUENTE_BOTON = new Font("Poppins", Font.BOLD, 20);
    private static final Font FUENTE_MENU = new Font("Poppins", Font.PLAIN, 22);

    // Bordes
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Border border1 = BorderFactory.createLineBorder(COLOR_HOVER_SELECCIONADO1, 2);

    // Variable para el botón seleccionado actualmente
    private JButton botonSeleccionado;

    // CardLayout para manejar los paneles derechos
    private CardLayout cardLayout;

    public Ventana01RegistrosDeUsuarios() {
        usuarioControlador = new UsuarioController(this);
        equipoController = new EquipoController();

        // Configuración de la ventana
        setSize(1600, 900);
        setTitle("GESTOR DE LABORATORIO DE LA UNIVERSIDAD NACIONAL FEDERICO VILLARREAL");
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicialización de componentes
        inicializarComponentes();

        // Configuración de paneles y componentes
        configurarPaneles();
        configurarMenu();
        configurarEventos();

        // Listar usuarios y equipos
        listarUsuario();
        listarEquiposDisponibles();
        listarEquiposRegistrados();
    }

    private void inicializarComponentes() {
        // Inicialización común para ambos paneles
        cbEstado = new JComboBox<>(new String[]{"", "Operativo", "No Operativo"});

        // Panel izquierdo
        panelLeft = new JPanel(null);
        panelLeft.setBounds(0, 0, 300, 900);
        panelLeft.setBackground(COLOR_BASE_BOTONES);

        // Panel derecho con CardLayout
        cardLayout = new CardLayout();
        panelDerecho = new JPanel(cardLayout);
        panelDerecho.setBounds(300, 0, 1300, 900);

        // Paneles derechos individuales
        panelRight1 = new JPanel(null); // Registro de Usuarios
        panelRight2 = new JPanel(null); // Control de Asistencia
        panelRight3 = new JPanel(null); // Control de Equipos
        panelRight4 = new JPanel(null); // Horarios de Laboratorio
        panelRightConfig = new JPanel(null); // Configuración de Equipos

        panelRight1.setBackground(COLOR_FONDO_PANEL);
        panelRight2.setBackground(COLOR_FONDO_PANEL);
        panelRight3.setBackground(COLOR_FONDO_PANEL);
        panelRight4.setBackground(COLOR_FONDO_PANEL);
        panelRightConfig.setBackground(COLOR_FONDO_PANEL);

        // Agregar paneles derechos al CardLayout
        panelDerecho.add(panelRight1, "RegistroUsuarios");
        panelDerecho.add(panelRight2, "ControlAsistencia");
        panelDerecho.add(panelRight3, "ControlEquipos");
        panelDerecho.add(panelRight4, "HorariosLaboratorio");
        panelDerecho.add(panelRightConfig, "ConfigurarEquipos");

        // Botones del menú
        btnRegistroUsuarios = ComponentFactory.crearBotonMenu("Registro de Usuarios");
        btnControlAsistencia = ComponentFactory.crearBotonMenu("Control de Asistencia");
        btnControlEquipos = ComponentFactory.crearBotonMenu("Control de Equipos");
        btnHorariosLaboratorio = ComponentFactory.crearBotonMenu("Horarios de Laboratorio");
        btnCerrarSesion = ComponentFactory.crearBotonAccion("Cerrar Sesión", 50, 730, 200, 70);

        // Inicializar etiquetas e imágenes
        lbImagenLogo = new JLabel();
        lbImagenUser = new JLabel();
        lbNameUser = new JLabel("", SwingConstants.CENTER);

        cbTipoEquipo = new JComboBox<>(new String[]{"", "Teclado", "CPU", "Monitor", "PizarraDigital"});
        cbLaboratorio = new JComboBox<>(new String[]{"", "1", "2", "3", "4", "5", "6"});
    }

    private void configurarPaneles() {
        // Panel izquierdo
        add(panelLeft);

        // Panel logo
        panelLogo = new JPanel(null);
        panelLogo.setBounds(0, 0, 300, 150);
        panelLogo.setBackground(COLOR_HOVER_SELECCIONADO1);
        panelLeft.add(panelLogo);

        // Imagen del logo
        ImageIcon imagenLogo = new ImageIcon("Images/logo_villarreal.png");
        ImageIcon imagenEscaladaLogo = new ImageIcon(imagenLogo.getImage().getScaledInstance(250, 110, Image.SCALE_DEFAULT));
        lbImagenLogo = new JLabel(imagenEscaladaLogo, SwingConstants.CENTER);
        lbImagenLogo.setBounds(20, 10, 260, 130);
        panelLogo.add(lbImagenLogo);

        // Panel usuario
        panelUser = new JPanel(null);
        panelUser.setBounds(0, 150, 300, 290);
        panelUser.setOpaque(false);
        panelLeft.add(panelUser);

        // Imagen del usuario
        ImageIcon imagenUser = new ImageIcon("Images/user_penguin.png");
        ImageIcon imagenEscaladaUser = new ImageIcon(imagenUser.getImage().getScaledInstance(195, 195, Image.SCALE_DEFAULT));
        lbImagenUser = new JLabel(imagenEscaladaUser, SwingConstants.CENTER);
        lbImagenUser.setBounds(0, 7, 300, 205);
        panelUser.add(lbImagenUser);

        // Nombre del usuario
        lbNameUser.setForeground(Color.WHITE);
        lbNameUser.setBackground(COLOR_HOVER_SELECCIONADO1);
        lbNameUser.setOpaque(true);
        lbNameUser.setFont(new Font("Poppins", Font.BOLD, 22));
        lbNameUser.setBounds(0, 220, 300, 30);
        panelUser.add(lbNameUser);

        // Botones del menú
        btnRegistroUsuarios.setBounds(0, 410, 300, 70);
        btnControlAsistencia.setBounds(0, 480, 300, 70);
        btnControlEquipos.setBounds(0, 550, 300, 70);
        btnHorariosLaboratorio.setBounds(0, 620, 300, 70);

        panelLeft.add(btnRegistroUsuarios);
        panelLeft.add(btnControlAsistencia);
        panelLeft.add(btnControlEquipos);
        panelLeft.add(btnHorariosLaboratorio);
        panelLeft.add(btnCerrarSesion);

        // Añadir panel derecho
        add(panelDerecho);

        // Configurar paneles de la interfaz
        configurarPanelRight1();
        configurarPanelRight3();
        configurarPanelRightConfig();
    }

    private void configurarMenu() {
        botonSeleccionado = btnRegistroUsuarios;
        actualizarEstadoBotones();
        cardLayout.show(panelDerecho, "RegistroUsuarios");
    }

    private void configurarEventos() {
        // Asignar MouseListener y ActionListener a los botones del menú
        btnRegistroUsuarios.addMouseListener(new EstiloHoverMenuBoton(btnRegistroUsuarios));
        btnControlAsistencia.addMouseListener(new EstiloHoverMenuBoton(btnControlAsistencia));
        btnControlEquipos.addMouseListener(new EstiloHoverMenuBoton(btnControlEquipos));
        btnHorariosLaboratorio.addMouseListener(new EstiloHoverMenuBoton(btnHorariosLaboratorio));

        btnRegistroUsuarios.addActionListener(new ClickMenuBoton("RegistroUsuarios"));
        btnControlAsistencia.addActionListener(new ClickMenuBoton("ControlAsistencia"));
        btnControlEquipos.addActionListener(new ClickMenuBoton("ControlEquipos"));
        btnHorariosLaboratorio.addActionListener(new ClickMenuBoton("HorariosLaboratorio"));

        // Botón Cerrar Sesión
        btnCerrarSesion.addActionListener(e -> manejarCerrarSesion());
        btnCerrarSesion.addMouseListener(new EstiloHoverAccionBoton(btnCerrarSesion));

        // Botones de acción
        //======================================================================
        //      REGISTROS DE USUARIOS
        //======================================================================
        btnAgregar.addActionListener(e -> manejarAgregarUsuario());
        btnModificar.addActionListener(e -> manejarModificarUsuario());
        btnEliminar.addActionListener(e -> manejarEliminarUsuario());

        btnAgregar.addMouseListener(new EstiloHoverAccionBoton(btnAgregar));
        btnModificar.addMouseListener(new EstiloHoverAccionBoton(btnModificar));
        btnEliminar.addMouseListener(new EstiloHoverAccionBoton(btnEliminar));
        //======================================================================
        //     CONTROL DE EQUIPOS
        //======================================================================
        btnConfiguracion.addActionListener(e -> manejarConfigurarEquipo());
        btnAgregarEquipo.addActionListener(e -> manejarAgregarEquipo());
        btnModificarEquipo.addActionListener(e -> manejarModificarEquipo());
        btnEliminarEquipo.addActionListener(e -> manejarEliminarEquipo());
           
        btnConfiguracion.addMouseListener(new EstiloHoverAccionBoton(btnConfiguracion));
        btnAgregarEquipo.addMouseListener(new EstiloHoverAccionBoton(btnAgregarEquipo));
        btnModificarEquipo.addMouseListener(new EstiloHoverAccionBoton(btnModificarEquipo));
        btnEliminarEquipo.addMouseListener(new EstiloHoverAccionBoton(btnEliminarEquipo));
        
        
        // Evento de la tabla
        //======================================================================
        //      REGISTROS DE USUARIOS
        //======================================================================
        tablaUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                llenarCamposDesdeTabla();
            }
        });
        //======================================================================
        //     CONTROL DE EQUIPOS
        //======================================================================
        tablaEquiposRegistrados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                llenarCamposDesdeTablaEquipo();
            }
        });
        //======================================================================
    }

    private void configurarPanelRight1() {
        // Título
        lbRegistrosUsuarios = ComponentFactory.crearEtiqueta("REGISTRO DE USUARIOS", 50, 30, 590, 52, FUENTE_TITULO, COLOR_TEXTO_NEGRO);
        lbRegistrosUsuarios.setBackground(Color.GREEN);
        lbRegistrosUsuarios.setOpaque(true);
        panelRight1.add(lbRegistrosUsuarios);

        // Subtítulo
        nombreSubPanel1_1 = ComponentFactory.crearEtiqueta("DATOS DEL REGISTRO", 100, 110, 500, 50, FUENTE_SUBTITULO, COLOR_TEXTO_BLANCO);
        nombreSubPanel1_1.setBackground(COLOR_HOVER_SELECCIONADO1);
        nombreSubPanel1_1.setOpaque(true);
        nombreSubPanel1_1.setHorizontalAlignment(SwingConstants.CENTER);
        panelRight1.add(nombreSubPanel1_1);

        // Subpanel1
        subPanel1 = new JPanel(null);
        subPanel1.setBounds(100, 180, 1090, 270);
        subPanel1.setBackground(Color.WHITE);
        subPanel1.setBorder(border);
        panelRight1.add(subPanel1);

        // Etiquetas y campos de texto
        lbNombres = ComponentFactory.crearEtiqueta("Nombres", 30, 20, 200, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbNombres);

        txtNombres = ComponentFactory.crearCampoTexto(30, 50, 300, 30, border1);
        subPanel1.add(txtNombres);

        lbApellidos = ComponentFactory.crearEtiqueta("Apellidos", 30, 100, 200, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbApellidos);

        txtApellidos = ComponentFactory.crearCampoTexto(30, 130, 300, 30, border1);
        subPanel1.add(txtApellidos);

        lbUsuario = ComponentFactory.crearEtiqueta("Usuario", 30, 180, 200, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbUsuario);

        txtUsuario = ComponentFactory.crearCampoTexto(30, 210, 300, 30, border1);
        subPanel1.add(txtUsuario);

        lbTipoDeDocumento = ComponentFactory.crearEtiqueta("Tipo de Documento", 435, 20, 210, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbTipoDeDocumento);

        cbTipoDeDocumento = new JComboBox<>();
        cbTipoDeDocumento.setBounds(435, 50, 210, 30);
        cbTipoDeDocumento.setBorder(border1);
        cbTipoDeDocumento.addItem("");
        cbTipoDeDocumento.addItem("DNI");
        cbTipoDeDocumento.addItem("PASAPORTE");
        subPanel1.add(cbTipoDeDocumento);

        lbNumeroDeContacto = ComponentFactory.crearEtiqueta("Nro. de Contacto", 435, 100, 210, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbNumeroDeContacto);

        txtNumeroDeContacto = ComponentFactory.crearCampoTexto(435, 130, 210, 30, border1);
        subPanel1.add(txtNumeroDeContacto);

        lbContraseña = ComponentFactory.crearEtiqueta("Contraseña", 435, 180, 210, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbContraseña);

        txtContraseña = ComponentFactory.crearCampoTexto(435, 210, 210, 30, border1);
        subPanel1.add(txtContraseña);

        lbNumeroDeDocumento = ComponentFactory.crearEtiqueta("Nro. de Documento", 730, 20, 250, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbNumeroDeDocumento);

        txtNumeroDeDocumento = ComponentFactory.crearCampoTexto(730, 50, 300, 30, border1);
        subPanel1.add(txtNumeroDeDocumento);

        lbCargo = ComponentFactory.crearEtiqueta("Cargo", 730, 100, 250, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbCargo);

        cbCargo = new JComboBox<>();
        cbCargo.setBounds(730, 130, 300, 30);
        cbCargo.setBorder(border1);
        cbCargo.addItem("");
        cbCargo.addItem("ADMINISTRADOR");
        cbCargo.addItem("DOCENTE");
        subPanel1.add(cbCargo);

        lbEmail = ComponentFactory.crearEtiqueta("Email", 730, 180, 250, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbEmail);

        txtEmail = ComponentFactory.crearCampoTexto(730, 210, 300, 30, border1);
        subPanel1.add(txtEmail);

        // Botones de acción
        btnAgregar = ComponentFactory.crearBotonAccion("AGREGAR", 700, 110, 150, 50);
        panelRight1.add(btnAgregar);

        btnModificar = ComponentFactory.crearBotonAccion("MODIFICAR", 870, 110, 150, 50);
        panelRight1.add(btnModificar);

        btnEliminar = ComponentFactory.crearBotonAccion("ELIMINAR", 1040, 110, 150, 50);
        panelRight1.add(btnEliminar);

        // Subtítulo de la tabla
        nombreSubPanel1_2 = ComponentFactory.crearEtiqueta("LISTA DE USUARIOS REGISTRADOS", 100, 490, 570, 50, FUENTE_SUBTITULO, COLOR_TEXTO_BLANCO);
        nombreSubPanel1_2.setBackground(COLOR_HOVER_SELECCIONADO1);
        nombreSubPanel1_2.setOpaque(true);
        nombreSubPanel1_2.setHorizontalAlignment(SwingConstants.CENTER);
        panelRight1.add(nombreSubPanel1_2);

        // Tabla de usuarios
        String[] columnasUsuario = {"Id", "Nombres", "Apellidos", "Tipo Doc.", "Nro. Doc.", "Número", "Cargo", "Usuario", "Contraseña", "Email"};
        modeloUsuario = new DefaultTableModel(columnasUsuario, 0);
        tablaUsuarios = new JTable(modeloUsuario);
        scrollSubPanel1_2 = new JScrollPane(tablaUsuarios);
        scrollSubPanel1_2.setBounds(100, 560, 1090, 270);
        scrollSubPanel1_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollSubPanel1_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelRight1.add(scrollSubPanel1_2);
    }
    
    private void configurarPanelRight3(){
        //Tirulo
        // Título
        lbTituloPantalla1 = ComponentFactory.crearEtiqueta("CONTROL DE EQUIPOS", 50, 30, 590, 52, FUENTE_TITULO, COLOR_TEXTO_NEGRO);
        lbTituloPantalla1.setBackground(Color.GREEN);
        lbTituloPantalla1.setOpaque(true);
        panelRight3.add(lbTituloPantalla1);
            
        // Subtítulo
        lbEquiposDisponiblesLabel = ComponentFactory.crearEtiqueta("EQUIPOS DISPONIBLES", 100, 110, 500, 50, FUENTE_SUBTITULO, COLOR_TEXTO_BLANCO);
        lbEquiposDisponiblesLabel.setBackground(COLOR_HOVER_SELECCIONADO1);
        lbEquiposDisponiblesLabel.setOpaque(true);
        lbEquiposDisponiblesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelRight3.add(lbEquiposDisponiblesLabel);
        
        //Boton de Accion - Equipos Disponibles
        btnConfiguracion = ComponentFactory.crearBotonAccion("CONFIGURACION", 870, 110, 250, 50);
        panelRight3.add(btnConfiguracion);

        // Tabla de Equipos Disponibles
        String[] columnasEquipoDisponible = {"LAB", "TIPO", "Código Patrimonial", "Número de Serie", "Estado"};
        modeloEquiposDisponibles = new DefaultTableModel(columnasEquipoDisponible, 0);
        tablaEquiposDisponibles = new JTable(modeloEquiposDisponibles);
        scrollTablaEquiposDisponibles = new JScrollPane(tablaEquiposDisponibles);
        scrollTablaEquiposDisponibles.setBounds(50, 170, 1150, 600);
        scrollTablaEquiposDisponibles.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollTablaEquiposDisponibles.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelRight3.add(scrollTablaEquiposDisponibles);
        
    }
    
    private void configurarPanelRightConfig() {
        lbTituloPantalla2 = ComponentFactory.crearEtiqueta("CONFIGURACIÓN DE EQUIPOS", 50, 30, 765, 52, FUENTE_TITULO, COLOR_TEXTO_NEGRO);
        lbTituloPantalla2.setBackground(Color.GREEN);
        lbTituloPantalla2.setOpaque(true);
        panelRightConfig.add(lbTituloPantalla2);

        lbRegistroEquiposLabel = ComponentFactory.crearEtiqueta("REGISTRO DE EQUIPOS", 100, 110, 500, 50, FUENTE_SUBTITULO, COLOR_TEXTO_BLANCO);
        lbRegistroEquiposLabel.setBackground(COLOR_HOVER_SELECCIONADO1);
        lbRegistroEquiposLabel.setOpaque(true);
        lbRegistroEquiposLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelRightConfig.add(lbRegistroEquiposLabel);

        JPanel subPanelRegistro = new JPanel(null);
        subPanelRegistro.setBounds(100, 180, 1000, 170);
        subPanelRegistro.setBackground(Color.WHITE);
        subPanelRegistro.setBorder(border);
        panelRightConfig.add(subPanelRegistro);

        lbTipoEquipoLabel = ComponentFactory.crearEtiqueta("Tipo de Equipo", 30, 20, 200, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanelRegistro.add(lbTipoEquipoLabel);

        cbTipoEquipo = new JComboBox<>(new String[]{"", "Teclado", "CPU", "Monitor", "PizarraDigital"});
        cbTipoEquipo.setBounds(30, 50, 300, 30);
        cbTipoEquipo.setBorder(border1);
        subPanelRegistro.add(cbTipoEquipo);

        lbLaboratorioLabel = ComponentFactory.crearEtiqueta("Laboratorio", 350, 20, 200, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanelRegistro.add(lbLaboratorioLabel);

        cbLaboratorio = new JComboBox<>(new String[]{"", "1", "2", "3", "4", "5", "6"});
        cbLaboratorio.setBounds(350, 50, 300, 30);
        cbLaboratorio.setBorder(border1);
        subPanelRegistro.add(cbLaboratorio);

        lbEstadoLabel = ComponentFactory.crearEtiqueta("Estado", 670, 20, 200, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanelRegistro.add(lbEstadoLabel);

        cbEstado.setBounds(670, 50, 300, 30);
        cbEstado.setBorder(border1);
        subPanelRegistro.add(cbEstado);

        lbNumeroSerieLabel = ComponentFactory.crearEtiqueta("Número de Serie", 30, 90, 200, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanelRegistro.add(lbNumeroSerieLabel);

        txtNumeroSerie = new JTextField();
        txtNumeroSerie.setBounds(30, 120, 300, 30);
        txtNumeroSerie.setBorder(border1);
        subPanelRegistro.add(txtNumeroSerie);

        lbCodigoPatrimonialLabel = ComponentFactory.crearEtiqueta("Código Patrimonial", 350, 90, 200, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanelRegistro.add(lbCodigoPatrimonialLabel);

        txtCodigoPatrimonial = new JTextField();
        txtCodigoPatrimonial.setBounds(350, 120, 300, 30);
        txtCodigoPatrimonial.setBorder(border1);
        subPanelRegistro.add(txtCodigoPatrimonial);

        btnAgregarEquipo = ComponentFactory.crearBotonAccion("AGREGAR", 700, 110, 150, 50);
        btnModificarEquipo = ComponentFactory.crearBotonAccion("MODIFICAR", 870, 110, 150, 50);
        btnEliminarEquipo = ComponentFactory.crearBotonAccion("ELIMINAR", 1040, 110, 150, 50);

        panelRightConfig.add(btnAgregarEquipo);
        panelRightConfig.add(btnModificarEquipo);
        panelRightConfig.add(btnEliminarEquipo);

        lbListaEquiposLabel = ComponentFactory.crearEtiqueta("LISTA DE EQUIPOS REGISTRADOS", 100, 360, 560, 50, FUENTE_SUBTITULO, COLOR_TEXTO_BLANCO);
        lbListaEquiposLabel.setBackground(COLOR_HOVER_SELECCIONADO1);
        lbListaEquiposLabel.setOpaque(true);
        lbListaEquiposLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelRightConfig.add(lbListaEquiposLabel);

        String[] columnasEquiposRegistrados = {"LAB", "TIPO", "COD. PATRIMONIAL", "NÚMERO DE SERIE", "ESTADO"};
        modeloEquiposRegistrados = new DefaultTableModel(columnasEquiposRegistrados, 0);
        tablaEquiposRegistrados = new JTable(modeloEquiposRegistrados);
        scrollTablaEquiposRegistrados = new JScrollPane(tablaEquiposRegistrados);
        scrollTablaEquiposRegistrados.setBounds(50, 420, 1150, 400);
        scrollTablaEquiposRegistrados.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollTablaEquiposRegistrados.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelRightConfig.add(scrollTablaEquiposRegistrados);
    }
    
    
    // Clases internas para manejar eventos
    private class EstiloHoverMenuBoton extends MouseAdapter {
        private JButton boton;

        public EstiloHoverMenuBoton(JButton boton) {
            this.boton = boton;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (!boton.equals(botonSeleccionado)) {
                boton.setBackground(COLOR_HOVER_SELECCIONADO1);
                boton.setForeground(COLOR_TEXTO_BLANCO);
            }
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!boton.equals(botonSeleccionado)) {
                boton.setBackground(COLOR_BASE_BOTONES);
                boton.setForeground(COLOR_TEXTO_NEGRO);
            }
            boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    private class ClickMenuBoton implements ActionListener {
        private String nombrePanel;

        public ClickMenuBoton(String nombrePanel) {
            this.nombrePanel = nombrePanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            botonSeleccionado = (JButton) e.getSource();
            actualizarEstadoBotones();
            cardLayout.show(panelDerecho, nombrePanel);
        }
    }

    private class EstiloHoverAccionBoton  extends MouseAdapter {
        private JButton boton;

        public EstiloHoverAccionBoton (JButton boton) {
            this.boton = boton;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            boton.setBackground(COLOR_HOVER_SELECCIONADO2);
            boton.setForeground(COLOR_TEXTO_NEGRO);
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            boton.setBackground(COLOR_HOVER_SELECCIONADO1);
            boton.setForeground(COLOR_TEXTO_BLANCO);
            boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    // Método para actualizar el estado de los botones del menú
    private void actualizarEstadoBotones() {
        JButton[] botones = {btnRegistroUsuarios, btnControlAsistencia, btnControlEquipos, btnHorariosLaboratorio};
        for (JButton boton : botones) {
            if (boton.equals(botonSeleccionado)) {
                boton.setBackground(COLOR_HOVER_SELECCIONADO1);
                boton.setForeground(COLOR_TEXTO_BLANCO);
            } else {
                boton.setBackground(COLOR_BASE_BOTONES);
                boton.setForeground(COLOR_TEXTO_NEGRO);
            }
        }
    }

    // Métodos para manejar acciones
    private void manejarCerrarSesion() {
        QuieresCerrarSession window = new QuieresCerrarSession();
        window.setVisible(true);
        this.dispose();
    }

    private void manejarAgregarUsuario() {
        if (seLlenaronTodosLosCampos()) {
            UsuarioModelo usuarioModelo = new UsuarioModelo();

            String nombre = txtNombres.getText();
            String apellido = txtApellidos.getText();
            String nombUsuario = txtUsuario.getText();
            String tipoDoc = cbTipoDeDocumento.getSelectedItem().toString();
            String numero = txtNumeroDeContacto.getText();
            String contrasena = txtContraseña.getText();
            String nroDoc = txtNumeroDeDocumento.getText();
            String cargo = cbCargo.getSelectedItem().toString();
            String email = txtEmail.getText();

            usuarioModelo.setNombres(nombre);
            usuarioModelo.setApellidos(apellido);
            usuarioModelo.setNombreUsuario(nombUsuario);
            usuarioModelo.setTipoDocumento(tipoDoc);
            usuarioModelo.setNumero(numero);
            usuarioModelo.setContrasena(contrasena);
            usuarioModelo.setNroDocumento(nroDoc);
            usuarioModelo.setCargo(cargo);
            usuarioModelo.setEmail(email);
            usuarioModelo.setIdUsuario(usuarioControlador.ultimoIdController()+ 1);

            int estado = usuarioControlador.insertarUsuarioController(usuarioModelo);

            if (estado == 1) {
                JOptionPane.showMessageDialog(null, "Registro Insertado 🐧!!");
            } else {
                JOptionPane.showMessageDialog(null, "Registro no Insertado 🐧!!");
            }

            listarUsuario();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Llena todos los campos 🐧!!");
        }
        
    }
    
    private boolean seLlenaronTodosLosCampos() {
        String[] campos = {txtNombres.getText(), txtApellidos.getText(), txtUsuario.getText(),
                           cbTipoDeDocumento.getSelectedItem().toString(), txtNumeroDeContacto.getText(),
                           txtContraseña.getText(), txtNumeroDeDocumento.getText(), cbCargo.getSelectedItem().toString(),
                           txtEmail.getText()};
        for (String campo : campos) {
            if (campo.equals("")) {
                return false;
            }
        }
        return true;
    }

    private void manejarModificarUsuario() {
        UsuarioModelo usuarioModelo = new UsuarioModelo();

        String nombre = txtNombres.getText();
        String apellido = txtApellidos.getText();
        String nombUsuario = txtUsuario.getText();
        String tipoDoc = cbTipoDeDocumento.getSelectedItem().toString();
        String numero = txtNumeroDeContacto.getText();
        String contrasena = txtContraseña.getText();
        String nroDoc = txtNumeroDeDocumento.getText();
        String cargo = cbCargo.getSelectedItem().toString();
        String email = txtEmail.getText();

        usuarioModelo.setNombres(nombre);
        usuarioModelo.setApellidos(apellido);
        usuarioModelo.setNombreUsuario(nombUsuario);
        usuarioModelo.setTipoDocumento(tipoDoc);
        usuarioModelo.setNumero(numero);
        usuarioModelo.setContrasena(contrasena);
        usuarioModelo.setNroDocumento(nroDoc);
        usuarioModelo.setCargo(cargo);
        usuarioModelo.setEmail(email);
        usuarioModelo.setIdUsuario(idUsuario);

        int estado = usuarioControlador.modificarUsuarioController(usuarioModelo);

        if (estado == 1) {
            JOptionPane.showMessageDialog(null, "Registro Modificado 🐧!!");
        } else {
            JOptionPane.showMessageDialog(null, "Registro no Modificado 🐧!!");
        }

        listarUsuario();
        
        limpiarCampos();
    }

    private void manejarEliminarUsuario() {
        UsuarioModelo usuarioModelo = new UsuarioModelo();
        usuarioModelo.setIdUsuario(idUsuario);

        int estado = usuarioControlador.eliminarUsuarioController(usuarioModelo);

        if (estado == 1) {
            JOptionPane.showMessageDialog(null, "Registro Eliminado 🐧!!");
        } else {
            JOptionPane.showMessageDialog(null, "Registro no Eliminado 🐧!!");
        }

        listarUsuario();
        limpiarCampos();
    }

    // Método para llenar campos desde la tabla
    private void llenarCamposDesdeTabla() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (filaSeleccionada != -1) {
            idUsuario = Integer.parseInt(modeloUsuario.getValueAt(filaSeleccionada, 0).toString());
            txtNombres.setText(modeloUsuario.getValueAt(filaSeleccionada, 1).toString());
            txtApellidos.setText(modeloUsuario.getValueAt(filaSeleccionada, 2).toString());
            cbTipoDeDocumento.setSelectedItem(modeloUsuario.getValueAt(filaSeleccionada, 3).toString());
            txtNumeroDeDocumento.setText(modeloUsuario.getValueAt(filaSeleccionada, 4).toString());
            txtNumeroDeContacto.setText(modeloUsuario.getValueAt(filaSeleccionada, 5).toString());
            cbCargo.setSelectedItem(modeloUsuario.getValueAt(filaSeleccionada, 6).toString());
            txtUsuario.setText(modeloUsuario.getValueAt(filaSeleccionada, 7).toString());
            txtContraseña.setText(modeloUsuario.getValueAt(filaSeleccionada, 8).toString());
            txtEmail.setText(modeloUsuario.getValueAt(filaSeleccionada, 9).toString());
        }
    }

    // Método para listar usuarios
    public void listarUsuario() {
        modeloUsuario.setRowCount(0);
        listaUsuarios = usuarioControlador.enlistarUsuarioController();

        for (UsuarioModelo usuarioTa : listaUsuarios) {
            modeloUsuario.addRow(new Object[]{
                usuarioTa.getIdUsuario(),
                usuarioTa.getNombres(),
                usuarioTa.getApellidos(),
                usuarioTa.getTipoDocumento(),
                usuarioTa.getNroDocumento(),
                usuarioTa.getNumero(),
                usuarioTa.getCargo(),
                usuarioTa.getNombreUsuario(),
                usuarioTa.getContrasena(),
                usuarioTa.getEmail()
            });
        }
    }

    // Método para limpiar campos
    private void limpiarCampos() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtUsuario.setText("");
        cbTipoDeDocumento.setSelectedIndex(0);
        txtNumeroDeContacto.setText("");
        txtContraseña.setText("");
        txtNumeroDeDocumento.setText("");
        cbCargo.setSelectedIndex(0);
        txtEmail.setText("");
    }

    // Método para establecer el nombre de usuario en la interfaz
    public void setUser(String user) {
        lbNameUser.setText(user);
    }
    
    //==========================================================================
    // TODO ESTO ES DE CONTROL DE EQUIPOS - CAMOTE MORADO
    //==========================================================================
    
    private void manejarConfigurarEquipo(){
        
        // Cambia al panel de configuraciones de equipos
        cardLayout.show(panelDerecho, "ConfigurarEquipos"); // Muestra el panel de configuración

    } 
    
    private void manejarAgregarEquipo() {
    try {
        if (seLlenaronTodosLosCamposEquipo()) {
            equipo = new EquipoModelo();

            // Crear el objeto equipo y asignar los valores directamente
            equipo.setTipoEquipo(cbTipoEquipo.getSelectedItem().toString());
            equipo.setNumeroLab(Integer.parseInt(cbLaboratorio.getSelectedItem().toString()));
            equipo.setEstado(cbEstado.getSelectedItem().toString());
            equipo.setNumeroSerie(txtNumeroSerie.getText().trim());
            equipo.setCodPatrimonial(txtCodigoPatrimonial.getText().trim()); // ID único

            // Insertar el equipo y verificar el estado de la inserción
            int estadoRegistro = equipoController.insertarEquipoController(equipo);
            String mensaje = (estadoRegistro == 1) ? 
                "Equipo agregado exitosamente 🐧!!" : 
                "Error al agregar el equipo 🐧!!";
            JOptionPane.showMessageDialog(null, mensaje);

            // Verificar el estado del equipo para determinar en qué tabla mostrarlo
            if (estadoRegistro == 1) {
                if ("Operativo".equals(equipo.getEstado())) {
                    listarEquiposDisponibles(); // Solo para "Operativo"
                }
                listarEquiposRegistrados(); // Muestra todos los equipos
            }

            limpiarCamposEquipo(); // Limpiar los campos de entrada
        } else {
            JOptionPane.showMessageDialog(null, "Llena todos los campos 🐧!!");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El valor del laboratorio debe ser un número válido 🐧!!");
    }
}

    
    private boolean seLlenaronTodosLosCamposEquipo() {
        String[] camposTexto = {
            txtNumeroSerie.getText().trim(), 
            txtCodigoPatrimonial.getText().trim()
        };

        // Verifica que todos los campos de texto no estén vacíos
        for (String campo : camposTexto) {
            if (campo.isEmpty()) {
                return false;
            }
        }

        // Verifica que los JComboBox no estén en su primer índice (vacío)
        if (cbTipoEquipo.getSelectedIndex() == 0 ||
            cbLaboratorio.getSelectedIndex() == 0 ||
            cbEstado.getSelectedIndex() == 0) {
            return false;
        }

        return true;
    }
    
    private void manejarModificarEquipo() {
    try {
        // Verificar si se seleccionó una fila en la tabla
        int filaSeleccionada = tablaEquiposRegistrados.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un equipo para modificar 🐧!!");
            return;
        }

        // Verificar si todos los campos están llenos
        if (seLlenaronTodosLosCamposEquipo()) {
            equipo = new EquipoModelo();

            // Asignación de los valores directamente al objeto equipo
            equipo.setTipoEquipo(cbTipoEquipo.getSelectedItem().toString());
            equipo.setNumeroLab(Integer.parseInt(cbLaboratorio.getSelectedItem().toString()));
            equipo.setEstado(cbEstado.getSelectedItem().toString());
            equipo.setNumeroSerie(txtNumeroSerie.getText().trim());
            equipo.setCodPatrimonial(txtCodigoPatrimonial.getText().trim()); // ID único para modificar

            // Llamada al controlador para modificar el equipo
            if (equipoController != null) {
                int estado = equipoController.modificarEquipoController(equipo);

                // Verificar si la modificación fue exitosa y mostrar el mensaje correspondiente
                String mensaje = (estado == 1) ? 
                    "Equipo modificado correctamente 🐧!!" : 
                    "Error al modificar el equipo 🐧!!";
                JOptionPane.showMessageDialog(null, mensaje);

                // Actualizar las tablas y limpiar los campos de entrada
                listarEquiposDisponibles();
                listarEquiposRegistrados();
                limpiarCamposEquipo();
            } else {
                JOptionPane.showMessageDialog(null, "Controlador no inicializado. Verifique la inicialización del controlador 🐧!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Llena todos los campos 🐧!!");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El valor del laboratorio debe ser un número válido 🐧!!");
    }
}
    
    private void manejarEliminarEquipo() {
    // Verificar si hay un equipo seleccionado en alguna de las tablas
    int filaSeleccionadaDisponibles = tablaEquiposDisponibles.getSelectedRow();
    int filaSeleccionadaRegistrados = tablaEquiposRegistrados.getSelectedRow();
    
    // Identificar desde cuál tabla se intentará eliminar el equipo
    JTable tablaSeleccionada;
    DefaultTableModel modeloSeleccionado;
    int filaSeleccionada;

    if (filaSeleccionadaDisponibles != -1) {
        tablaSeleccionada = tablaEquiposDisponibles;
        modeloSeleccionado = modeloEquiposDisponibles;
        filaSeleccionada = filaSeleccionadaDisponibles;
    } else if (filaSeleccionadaRegistrados != -1) {
        tablaSeleccionada = tablaEquiposRegistrados;
        modeloSeleccionado = modeloEquiposRegistrados;
        filaSeleccionada = filaSeleccionadaRegistrados;
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione un equipo para eliminar 🐧!!");
        return;
    }

    // Obtener el código patrimonial del equipo seleccionado
    String codigoPatrimonial = modeloSeleccionado.getValueAt(filaSeleccionada, 2).toString();

    // Verificar si el código patrimonial es válido
    if (codigoPatrimonial != null && !codigoPatrimonial.isEmpty()) {
        // Solicitar confirmación al usuario antes de eliminar
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este equipo?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Crear el objeto equipo y asignar el código patrimonial
            equipo = new EquipoModelo();
            equipo.setCodPatrimonial(codigoPatrimonial);

            // Llamar al método eliminarEquipo de equipoController
            int estado = equipoController.eliminarEquipoController(equipo);

            // Comprobar si se eliminó correctamente
            if (estado == 1) {
                JOptionPane.showMessageDialog(null, "Equipo eliminado correctamente 🐧!!");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el equipo 🐧!!");
            }

            listarEquiposDisponibles(); // Actualizar tabla de equipos operativos
            listarEquiposRegistrados(); // Actualizar tabla de todos los equipos
            limpiarCamposEquipo(); // Limpiar los campos de entrada
        }
    } else {
        JOptionPane.showMessageDialog(null, "Código patrimonial inválido. No se puede eliminar el equipo 🐧!!");
    }
}
    // Método para llenar campos desde la tabla
    private void llenarCamposDesdeTablaEquipo() {
    int filaSeleccionada = tablaEquiposRegistrados.getSelectedRow();
    
    if (filaSeleccionada != -1) {
        cbLaboratorio.setSelectedItem(modeloEquiposRegistrados.getValueAt(filaSeleccionada, 0).toString());
        cbTipoEquipo.setSelectedItem(modeloEquiposRegistrados.getValueAt(filaSeleccionada, 1).toString());
        txtCodigoPatrimonial.setText(modeloEquiposRegistrados.getValueAt(filaSeleccionada, 2).toString());
        txtNumeroSerie.setText(modeloEquiposRegistrados.getValueAt(filaSeleccionada, 3).toString());
        cbEstado.setSelectedItem(modeloEquiposRegistrados.getValueAt(filaSeleccionada, 4).toString());
        }
    }  
    
    public void listarEquiposDisponibles() {
        modeloEquiposDisponibles.setRowCount(0); // Limpia la tabla antes de agregar nuevos datos
        listaEquipos = equipoController.enlistarEquipoPorEstadoController("Operativo"); // Obtiene solo equipos Operativo

        for (EquipoModelo equipoTa : listaEquipos) {
            modeloEquiposDisponibles.addRow(new Object[]{
                equipoTa.getNumeroLab(),
                equipoTa.getTipoEquipo(),
                equipoTa.getCodPatrimonial(),
                equipoTa.getNumeroSerie(),
                equipoTa.getEstado(),
            });
        }
    }
    
    public void listarEquiposRegistrados() {
        modeloEquiposRegistrados.setRowCount(0); // Limpia la tabla antes de agregar nuevos datos
        listaEquipos = equipoController.enlistarEquipoController(); // Obtiene todos los equipos

        for (EquipoModelo equipoTa : listaEquipos) {
            modeloEquiposRegistrados.addRow(new Object[]{
                equipoTa.getNumeroLab(),
                equipoTa.getTipoEquipo(),
                equipoTa.getCodPatrimonial(),
                equipoTa.getNumeroSerie(),
                equipoTa.getEstado(),
            });
        }
    }

    
    
    // Método para listar equipos
    public void listarEquipo() {
        modeloEquiposDisponibles.setRowCount(0); // Limpia la tabla antes de agregar nuevos datos
        listaEquipos = equipoController.enlistarEquipoController(); // Obtiene la lista de equipos desde la base de datos o lista local

        for (EquipoModelo equipoTa : listaEquipos) {
            modeloEquiposDisponibles.addRow(new Object[]{
                equipoTa.getNumeroLab(),
                equipoTa.getTipoEquipo(),
                equipoTa.getCodPatrimonial(),
                equipoTa.getNumeroSerie(),
                equipoTa.getEstado(),
            });
        }
    }

    // Método para limpiar campos de equipo
    private void limpiarCamposEquipo() {
        txtNumeroSerie.setText("");
        txtCodigoPatrimonial.setText("");
        cbTipoEquipo.setSelectedIndex(0);
        cbLaboratorio.setSelectedIndex(0);
        cbEstado.setSelectedIndex(0);
    }
    
    // Método para establecer el nombre del equipo o algún identificador en la interfaz
    public void setEquipoLabel(String equipo) {
        lbNameUser.setText(equipo); // Asigna el nombre o identificador del equipo al label
    }
    
//=========================================================================================
//=========================================================================================
    
    // Método principal
    public static void main(String[] args) {
        Ventana01RegistrosDeUsuarios vtn = new Ventana01RegistrosDeUsuarios();
        vtn.setVisible(true);
    }
}
