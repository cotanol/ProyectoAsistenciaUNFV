package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import Model.*;
import Controller.*;
import Util.ComponentFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import java.time.LocalTime;

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
    
    
    // Componentes de panelRight2 (Control de Asistencia)
    private JLabel lblControlAsistencia, lblDatosLab, lblConfigAvanz, lblNroLab, lblHor, lblAsigs;
    private JComboBox<Integer> cboNroLab;
    private JComboBox<LocalTime> cboHorario;
    private JComboBox<String> cboAsignatura;
    private JButton btnBuscar, btnActuDatos, btnReporteGen;
    
    private JLabel lblSubTitulo ,lblTituloAsistencia, lblInfoClase, lblDia, lblMes, lblAnio;
    private JTable tablaAsistencia;
    private JButton btnGuardar, btnRegresar;
    
    private JLabel lblTitulo;
    // Componentes para la gestión de laboratorios
    private JTextField txtNumeroLab;
    private JTextField txtCapacidad;
    private JTable tablaLaboratorios;
    private DefaultTableModel modeloLaboratorio;

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
    private ArrayList<HorarioLaboratorioModelo> listaHorarioLaboratorio;
    
    private UsuarioController usuarioControlador;
    private EquipoController equipoController;
    private HorarioLaboratorioController horarioLaboratorioController;
    private LaboratorioController laboratorioController;
    private AsistenciaController asistenciaController;
    
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
        equipoController = new EquipoController(this);
        horarioLaboratorioController = new HorarioLaboratorioController(this);
        laboratorioController = new LaboratorioController(this);
        asistenciaController = new AsistenciaController(this);

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
        listarLaboratorios();
        
        
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
        
        
        // Inicializar modelo de la tabla
        modeloLaboratorio = new DefaultTableModel(new String[]{"LAB", "CAPACIDAD"}, 0);

        // Inicializar tablaLaboratorios
        tablaLaboratorios = new JTable(modeloLaboratorio);

        // Configurar estilo de la tabla
        tablaLaboratorios.setFont(new Font("Poppins", Font.PLAIN, 18));
        tablaLaboratorios.getTableHeader().setFont(new Font("Poppins", Font.BOLD, 18));
        tablaLaboratorios.setRowHeight(35);
        tablaLaboratorios.setGridColor(Color.GRAY);
        tablaLaboratorios.setSelectionBackground(COLOR_HOVER_SELECCIONADO1);
        tablaLaboratorios.setSelectionForeground(Color.WHITE);
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
        configurarPanelRight2();
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
    
    private void configurarPanelRight2() {
        
        
        // Título
        lblControlAsistencia = ComponentFactory.crearEtiqueta("CONTROL DE ASISTENCIA", 50, 30, 590, 52, FUENTE_TITULO, COLOR_TEXTO_NEGRO);
        lblControlAsistencia.setBackground(Color.GREEN);
        lblControlAsistencia.setOpaque(true);
        panelRight2.add(lblControlAsistencia);
        
        listaHorarioLaboratorio = horarioLaboratorioController.enlistarHorarioLaboratorioController();

        // Subtítulo para Datos del Laboratorio
        lblDatosLab = ComponentFactory.crearEtiqueta("DATOS DEL LABORATORIO", 100, 110, 500, 50, FUENTE_SUBTITULO, COLOR_TEXTO_BLANCO);
        lblDatosLab.setBackground(COLOR_HOVER_SELECCIONADO1);
        lblDatosLab.setOpaque(true);
        lblDatosLab.setHorizontalAlignment(SwingConstants.CENTER);
        panelRight2.add(lblDatosLab);

        // Panel para los datos del laboratorio
        JPanel subPanelDatosLab = new JPanel(null);
        subPanelDatosLab.setBounds(100, 180, 1090, 100);
        subPanelDatosLab.setBackground(Color.WHITE);
        subPanelDatosLab.setBorder(border);
        panelRight2.add(subPanelDatosLab);

        // Etiqueta y ComboBox para Número de Laboratorio
        lblNroLab = ComponentFactory.crearEtiqueta("Nro. Laboratorio", 50, 20, 200, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanelDatosLab.add(lblNroLab);
        cboNroLab = new JComboBox<>();
        cboNroLab.setBounds(50, 50, 200, 30);
        cboNroLab.setBorder(border1);
        
        cboNroLab.addItem(null);
        Set<Integer> labsAgregados = new HashSet<>();
        for (HorarioLaboratorioModelo horaLab: listaHorarioLaboratorio) {
            if (!labsAgregados.contains(horaLab.getNumeroLab())) { // Verificar si ya se agregó
                cboNroLab.addItem(horaLab.getNumeroLab());
                labsAgregados.add(horaLab.getNumeroLab()); // Marcar como agregado
            }
        }
        
        subPanelDatosLab.add(cboNroLab);

        // Etiqueta y ComboBox para Horario
        lblHor = ComponentFactory.crearEtiqueta("Horario", 300, 20, 200, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanelDatosLab.add(lblHor);
        cboHorario = new JComboBox<>();
        cboHorario.setBounds(300, 50, 200, 30);
        cboHorario.setBorder(border1);
        
        cboHorario.addItem(null); // Agregar un elemento inicial vacío
        Set<LocalTime> horariosAgregados = new HashSet<>(); // Usar un conjunto para rastrear los horarios agregados

        for (HorarioLaboratorioModelo horaLab : listaHorarioLaboratorio) {
            if (!horariosAgregados.contains(horaLab.getHorarioInicio())) { // Verificar si ya se agregó
                cboHorario.addItem(horaLab.getHorarioInicio());
                horariosAgregados.add(horaLab.getHorarioInicio()); // Marcar como agregado
            }
        }

        subPanelDatosLab.add(cboHorario);

        // Etiqueta y ComboBox para Asignatura
        lblAsigs = ComponentFactory.crearEtiqueta("Asignatura", 550, 20, 200, 30, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanelDatosLab.add(lblAsigs);
        cboAsignatura = new JComboBox<>();
        cboAsignatura.setBounds(550, 50, 200, 30);
        cboAsignatura.setBorder(border1);
        
        cboAsignatura.addItem(""); // Agregar un elemento inicial vacío
        Set<String> asignaturasAgregadas = new HashSet<>(); // Usar un conjunto para rastrear las asignaturas agregadas

        for (HorarioLaboratorioModelo horaLab : listaHorarioLaboratorio) {
            if (!asignaturasAgregadas.contains(horaLab.getAsignatura())) { // Verificar si ya se agregó
                cboAsignatura.addItem(horaLab.getAsignatura());
                asignaturasAgregadas.add(horaLab.getAsignatura()); // Marcar como agregado
            }
        }

        subPanelDatosLab.add(cboAsignatura);

        // Botón de Buscar
        btnBuscar = ComponentFactory.crearBotonAccion("BUSCAR", 850, 35, 150, 50);
        subPanelDatosLab.add(btnBuscar);

        // Subtítulo para Configuración Avanzada (Mayor margen vertical)
        lblConfigAvanz = ComponentFactory.crearEtiqueta("CONFIGURACIÓN AVANZADA", 400, 400, 500, 50, FUENTE_SUBTITULO, COLOR_TEXTO_BLANCO);
        lblConfigAvanz.setBackground(COLOR_HOVER_SELECCIONADO1);
        lblConfigAvanz.setOpaque(true);
        lblConfigAvanz.setHorizontalAlignment(SwingConstants.CENTER);
        panelRight2.add(lblConfigAvanz);

        // Panel para Configuración Avanzada (Mayor margen vertical)
        JPanel subPanelConfigAvanz = new JPanel(null);
        subPanelConfigAvanz.setBounds(400, 470, 500, 200);
        subPanelConfigAvanz.setBackground(Color.WHITE);
        subPanelConfigAvanz.setBorder(border);
        panelRight2.add(subPanelConfigAvanz);

        // Botón para Actualizar Datos
        btnActuDatos = ComponentFactory.crearBotonAccion("ACTUALIZAR DATOS DEL LABORATORIO", 50, 30, 400, 50);
        subPanelConfigAvanz.add(btnActuDatos);

        // Botón para Generar Reporte
        btnReporteGen = ComponentFactory.crearBotonAccion("REPORTE GENERAL POR AÑO-MESES", 50, 110, 400, 50);
        subPanelConfigAvanz.add(btnReporteGen);
        
        // Agregar estilo hover y eventos a los botones
        btnBuscar.addMouseListener(new EstiloHoverAccionBoton(btnBuscar));
        btnActuDatos.addMouseListener(new EstiloHoverAccionBoton(btnActuDatos));
        btnReporteGen.addMouseListener(new EstiloHoverAccionBoton(btnReporteGen));

        // Asignar eventos de clic
        btnBuscar.addActionListener(e -> manejarBuscarHorario());
        btnActuDatos.addActionListener(e -> manejarCambioAPanelActualizarLaboratorio());

        
    }
    
    private void manejarCambioAPanelAsistencia(int numeroLab, String asignatura, LocalTime horarioInicio) {
        JPanel panelAsistencia = new JPanel(null);
        panelAsistencia.setBackground(COLOR_FONDO_PANEL);

        // Título
        lblTituloAsistencia = ComponentFactory.crearEtiqueta("CONTROL DE ASISTENCIA", 50, 30, 590, 52, FUENTE_TITULO, COLOR_TEXTO_NEGRO);
        lblTituloAsistencia.setBackground(Color.GREEN);
        lblTituloAsistencia.setOpaque(true);
        panelAsistencia.add(lblTituloAsistencia);

        // Subtítulo
        lblSubTitulo = ComponentFactory.crearEtiqueta("REGISTRO DE LA CLASE", 100, 110, 500, 50, FUENTE_SUBTITULO, COLOR_TEXTO_BLANCO);
        lblSubTitulo.setBackground(COLOR_HOVER_SELECCIONADO1);
        lblSubTitulo.setOpaque(true);
        lblSubTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelAsistencia.add(lblSubTitulo);

        // Información del horario seleccionado
        lblInfoClase = new JLabel(String.format(
            "<html>"
            + "Nro. Laboratorio: %d<br>"
            + "Asignatura: %s<br>"
            + "Horario: %s<br>"
            + "</html>",
            numeroLab,
            asignatura,
            horarioInicio.toString()
        ));
        lblInfoClase.setBounds(100, 180, 600, 100);
        lblInfoClase.setFont(FUENTE_LABEL);
        panelAsistencia.add(lblInfoClase);

        // Tabla para registrar la asistencia
        String[] columnasAsistencia = {"Código", "Nombres", "Apellidos", "Asistencia"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnasAsistencia, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 3 ? Boolean.class : String.class;
            }
        };

        // Consultar alumnos registrados con los criterios seleccionados
        ArrayList<AlumnoModelo> alumnos = asistenciaController.obtenerAlumnosPorLaboratorioYHorario(numeroLab, asignatura, horarioInicio);

        for (AlumnoModelo alumno : alumnos) {
            modeloTabla.addRow(new Object[]{
                alumno.getCodigoAlumno(),
                alumno.getNombres(),
                alumno.getApellidos(),
                false // Inicializar asistencia como "no presente"
            });
        }

        tablaAsistencia = new JTable(modeloTabla);
        tablaAsistencia.setRowHeight(30);
        tablaAsistencia.getTableHeader().setFont(FUENTE_LABEL);
        tablaAsistencia.setFont(FUENTE_TEXTFIELD);

        JScrollPane scrollTabla = new JScrollPane(tablaAsistencia);
        scrollTabla.setBounds(100, 300, 1100, 400);
        scrollTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panelAsistencia.add(scrollTabla);

        // Botones Guardar y Regresar
        btnGuardar = ComponentFactory.crearBotonAccion("GUARDAR", 1000, 750, 150, 50);
        btnRegresar = ComponentFactory.crearBotonAccion("REGRESAR", 800, 750, 150, 50);

        btnGuardar.addMouseListener(new EstiloHoverAccionBoton(btnGuardar));
        btnRegresar.addMouseListener(new EstiloHoverAccionBoton(btnRegresar));

        btnGuardar.addActionListener(e -> manejarGuardarAsistencia(tablaAsistencia));
        btnRegresar.addActionListener(e -> cardLayout.show(panelDerecho, "ControlAsistencia"));

        panelAsistencia.add(btnGuardar);
        panelAsistencia.add(btnRegresar);

        // Añadir el panel al CardLayout
        panelDerecho.add(panelAsistencia, "RegistroAsistencia");
        cardLayout.show(panelDerecho, "RegistroAsistencia");
    }

    private void manejarBuscarHorario() {
        // Obtener valores seleccionados de los JComboBox
        Integer numeroLab = (Integer) cboNroLab.getSelectedItem();
        String asignatura = (String) cboAsignatura.getSelectedItem();
        LocalTime horarioInicio = (LocalTime) cboHorario.getSelectedItem();

        if (numeroLab == null || asignatura == null || horarioInicio == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione todos los campos antes de buscar.");
            return;
        }

        try {
            // Consultar alumnos y cambiar al panel de asistencia
            manejarCambioAPanelAsistencia(numeroLab, asignatura, horarioInicio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al buscar los alumnos: " + e.getMessage());
        }
    }


    private void manejarGuardarAsistencia(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String codigo = modelo.getValueAt(i, 0).toString();
            String nombre = modelo.getValueAt(i, 1).toString();
            String apellido = modelo.getValueAt(i, 2).toString();
            boolean asistencia = (boolean) modelo.getValueAt(i, 3);

            // Aquí puedes guardar la asistencia en la base de datos o realizar otra acción
            System.out.println("Código: " + codigo + ", Nombre: " + nombre + ", Asistencia: " + asistencia);
        }
        JOptionPane.showMessageDialog(null, "Asistencia guardada exitosamente.");
    }
    
    
    private void manejarCambioAPanelActualizarLaboratorio() {
        // Crear el panel principal
        JPanel panelActualizarLaboratorio = new JPanel(null);
        panelActualizarLaboratorio.setBackground(COLOR_FONDO_PANEL);

        // Título
        lblTitulo = ComponentFactory.crearEtiqueta("CONTROL DE ASISTENCIA", 50, 30, 1200, 52, FUENTE_TITULO, COLOR_TEXTO_NEGRO);
        lblTitulo.setBackground(Color.GREEN);
        lblTitulo.setOpaque(true);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelActualizarLaboratorio.add(lblTitulo);

        // Subtítulo
        lblSubTitulo = ComponentFactory.crearEtiqueta("DATOS DEL LABORATORIO", 150, 110, 1000, 50, FUENTE_SUBTITULO, COLOR_TEXTO_BLANCO);
        lblSubTitulo.setBackground(COLOR_HOVER_SELECCIONADO1);
        lblSubTitulo.setOpaque(true);
        lblSubTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelActualizarLaboratorio.add(lblSubTitulo);

        // Panel para ingresar datos
        int panelAncho = 1100; 
        int panelX = (1300 - panelAncho) / 2; 
        JPanel subPanelDatos = new JPanel(null);
        subPanelDatos.setBounds(panelX, 180, panelAncho, 120);
        subPanelDatos.setBackground(Color.WHITE);
        subPanelDatos.setBorder(border);
        panelActualizarLaboratorio.add(subPanelDatos);

        // Etiquetas y Campos en una Sola Línea
        int campoAncho = 250; 
        int campoAlto = 35;
        int separacion = 80; 
        int totalAncho = (campoAncho * 2) + separacion; 
        int inicioX = (panelAncho - totalAncho) / 2;

        JLabel lblNroLaboratorio = ComponentFactory.crearEtiqueta("Nro. Laboratorio", inicioX, 30, campoAncho, campoAlto, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanelDatos.add(lblNroLaboratorio);
        txtNumeroLab = new JTextField();
        txtNumeroLab.setBounds(inicioX, 70, campoAncho, campoAlto);
        txtNumeroLab.setBorder(border1);
        subPanelDatos.add(txtNumeroLab);

        JLabel lblCapacidad = ComponentFactory.crearEtiqueta("Capacidad", inicioX + campoAncho + separacion, 30, campoAncho, campoAlto, FUENTE_LABEL, COLOR_HOVER_SELECCIONADO1);
        subPanelDatos.add(lblCapacidad);
        txtCapacidad = new JTextField();
        txtCapacidad.setBounds(inicioX + campoAncho + separacion, 70, campoAncho, campoAlto);
        txtCapacidad.setBorder(border1);
        subPanelDatos.add(txtCapacidad);

        // Botones de Acción
        int btnWidth = 180; 
        int btnHeight = 60; 
        int spacing = 60; 
        int totalWidth = (btnWidth * 3) + (spacing * 2); 
        int startX = (panelAncho - totalWidth) / 2;

        JButton btnAgregarLab = ComponentFactory.crearBotonAccion("Agregar", panelX + startX, 350, btnWidth, btnHeight);
        JButton btnModificarLab = ComponentFactory.crearBotonAccion("Modificar", panelX + startX + btnWidth + spacing, 350, btnWidth, btnHeight);
        JButton btnEliminarLab = ComponentFactory.crearBotonAccion("Eliminar", panelX + startX + (btnWidth + spacing) * 2, 350, btnWidth, btnHeight);

        btnAgregarLab.addMouseListener(new EstiloHoverAccionBoton(btnAgregarLab));
        btnModificarLab.addMouseListener(new EstiloHoverAccionBoton(btnModificarLab));
        btnEliminarLab.addMouseListener(new EstiloHoverAccionBoton(btnEliminarLab));

        btnAgregarLab.addActionListener(e -> manejarAgregarLaboratorio());
        btnModificarLab.addActionListener(e -> manejarModificarLaboratorio());
        btnEliminarLab.addActionListener(e -> manejarEliminarLaboratorio());

        panelActualizarLaboratorio.add(btnAgregarLab);
        panelActualizarLaboratorio.add(btnModificarLab);
        panelActualizarLaboratorio.add(btnEliminarLab);

        // Imagen del Pingüino
        JLabel lblPinguino = new JLabel();
        lblPinguino.setBounds(panelX, 450, (panelAncho / 3) - 20, 300); 
        lblPinguino.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon iconoPinguino = new ImageIcon("Images/pinguinoLab.png");
        Image imageEscalada = iconoPinguino.getImage().getScaledInstance(-1, 300, Image.SCALE_SMOOTH); 
        lblPinguino.setIcon(new ImageIcon(imageEscalada));
        panelActualizarLaboratorio.add(lblPinguino);

        // Tabla para mostrar los laboratorios
        JPanel subPanelTabla = new JPanel(null);
        subPanelTabla.setBounds(panelX + (panelAncho / 3), 450, (panelAncho * 2 / 3) - 20, 300); 
        subPanelTabla.setBackground(Color.WHITE);
        subPanelTabla.setBorder(border);
        panelActualizarLaboratorio.add(subPanelTabla);

        // Inicializar el modelo y la tabla
        modeloLaboratorio = new DefaultTableModel(new String[]{"LAB", "CAPACIDAD"}, 0);
        tablaLaboratorios = new JTable(modeloLaboratorio);

        // Mejorar Estética de la Tabla
        tablaLaboratorios.setFont(new Font("Poppins", Font.PLAIN, 18));
        tablaLaboratorios.getTableHeader().setFont(new Font("Poppins", Font.BOLD, 18));
        tablaLaboratorios.setRowHeight(35);
        tablaLaboratorios.setGridColor(Color.GRAY);
        tablaLaboratorios.setSelectionBackground(COLOR_HOVER_SELECCIONADO1);
        tablaLaboratorios.setSelectionForeground(Color.WHITE);

        JScrollPane scrollTabla = new JScrollPane(tablaLaboratorios);
        scrollTabla.setBounds(10, 10, (panelAncho * 2 / 3) - 40, 280);
        scrollTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        subPanelTabla.add(scrollTabla);

        // Eventos de la tabla
        tablaLaboratorios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                llenarCamposDesdeTablaLaboratorio();
            }
        });

        // Cargar datos iniciales en la tabla
        listarLaboratorios();

        // Añadir el panel al CardLayout
        panelDerecho.add(panelActualizarLaboratorio, "ActualizarLaboratorio");
        cardLayout.show(panelDerecho, "ActualizarLaboratorio");
    }
    
    private void actualizarCombosHorarioAsignatura() {
        // Limpiar los items actuales
        cboNroLab.removeAllItems();
        cboHorario.removeAllItems();
        cboAsignatura.removeAllItems();

        // Consultar la lista actualizada desde el controlador
        listaHorarioLaboratorio = horarioLaboratorioController.enlistarHorarioLaboratorioController();

        // Agregar elementos al combo de laboratorios
        Set<Integer> labsAgregados = new HashSet<>();
        cboNroLab.addItem(null); // Para permitir selección nula
        for (HorarioLaboratorioModelo horaLab : listaHorarioLaboratorio) {
            if (!labsAgregados.contains(horaLab.getNumeroLab())) {
                cboNroLab.addItem(horaLab.getNumeroLab());
                labsAgregados.add(horaLab.getNumeroLab());
            }
        }

        // Agregar elementos al combo de horarios
        Set<LocalTime> horariosAgregados = new HashSet<>();
        cboHorario.addItem(null); // Selección nula inicial
        for (HorarioLaboratorioModelo horaLab : listaHorarioLaboratorio) {
            if (!horariosAgregados.contains(horaLab.getHorarioInicio())) {
                cboHorario.addItem(horaLab.getHorarioInicio());
                horariosAgregados.add(horaLab.getHorarioInicio());
            }
        }

        // Agregar elementos al combo de asignaturas
        Set<String> asignaturasAgregadas = new HashSet<>();
        cboAsignatura.addItem(""); // Selección nula inicial
        for (HorarioLaboratorioModelo horaLab : listaHorarioLaboratorio) {
            if (!asignaturasAgregadas.contains(horaLab.getAsignatura())) {
                cboAsignatura.addItem(horaLab.getAsignatura());
                asignaturasAgregadas.add(horaLab.getAsignatura());
            }
        }
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
// METODOS PARA MANERJAR LABORATORIO ======================================================
    
    private void manejarAgregarLaboratorio() {
        if (seLlenaronTodosLosCamposLaboratorio()) {
            LaboratorioModelo laboratorioModelo = new LaboratorioModelo();

            int capacidad = Integer.parseInt(txtCapacidad.getText());

            laboratorioModelo.setCapacidad(capacidad);
            laboratorioModelo.setNumeroLab(laboratorioController.ultimoIdController() + 1);

            int estado = laboratorioController.insertarLaboratorioController(laboratorioModelo);

            if (estado == 1) {
                JOptionPane.showMessageDialog(null, "Laboratorio Insertado 🧪!!");
            } else {
                JOptionPane.showMessageDialog(null, "Laboratorio no Insertado 🧪!!");
            }

            listarLaboratorios();
            limpiarCamposLaboratorio();
        } else {
            JOptionPane.showMessageDialog(null, "Llena todos los campos 🧪!!");
        }
    }

    private void manejarModificarLaboratorio() {
        if (seLlenaronTodosLosCamposLaboratorio()) {
            LaboratorioModelo laboratorioModelo = new LaboratorioModelo();

            int numeroLab = Integer.parseInt(txtNumeroLab.getText());
            int capacidad = Integer.parseInt(txtCapacidad.getText());

            laboratorioModelo.setNumeroLab(numeroLab);
            laboratorioModelo.setCapacidad(capacidad);

            int estado = laboratorioController.modificarLaboratorioController(laboratorioModelo);

            if (estado == 1) {
                JOptionPane.showMessageDialog(null, "Laboratorio Modificado 🧪!!");
                actualizarCombosHorarioAsignatura();
            } else {
                JOptionPane.showMessageDialog(null, "Laboratorio no Modificado 🧪!!");
            }

            listarLaboratorios();
            limpiarCamposLaboratorio();
        } else {
            JOptionPane.showMessageDialog(null, "Llena todos los campos 🧪!!");
        }
    }

    private void manejarEliminarLaboratorio() {
        // Verificar si hay una fila seleccionada
        int filaSeleccionada = tablaLaboratorios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un laboratorio para eliminar 🧪!!");
            return;
        }

        try {
            // Obtener y validar el número de laboratorio desde el campo de texto
            String numeroLabTexto = txtNumeroLab.getText().trim();

            if (numeroLabTexto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo 'Número de Laboratorio' está vacío. Por favor, seleccione o ingrese un laboratorio.");
                return;
            }

            int numeroLab = Integer.parseInt(numeroLabTexto);

            // Crear el modelo del laboratorio
            LaboratorioModelo laboratorioModelo = new LaboratorioModelo();
            laboratorioModelo.setNumeroLab(numeroLab);

            // Llamar al controlador para eliminar el laboratorio
            int estado = laboratorioController.eliminarLaboratorioController(laboratorioModelo);

            if (estado == 1) {
                JOptionPane.showMessageDialog(null, "Laboratorio Eliminado 🧪!!");
                actualizarCombosHorarioAsignatura(); // Actualizar combos relacionados
            } else {
                JOptionPane.showMessageDialog(null, "Laboratorio no Eliminado 🧪!!");
            }

            // Actualizar la tabla y limpiar los campos
            listarLaboratorios();
            limpiarCamposLaboratorio();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El número de laboratorio es inválido. Por favor, ingrese un número válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    // Método para llenar campos desde la tabla
    private void llenarCamposDesdeTablaLaboratorio() {
        int filaSeleccionada = tablaLaboratorios.getSelectedRow();
        if (filaSeleccionada != -1) {
            txtNumeroLab.setText(modeloLaboratorio.getValueAt(filaSeleccionada, 0).toString());
            txtCapacidad.setText(modeloLaboratorio.getValueAt(filaSeleccionada, 1).toString());
        }
    }

    // Método para listar laboratorios
    public void listarLaboratorios() {
        modeloLaboratorio.setRowCount(0);
        ArrayList<LaboratorioModelo> listaLaboratorios = laboratorioController.enlistarLaboratorioController();

        for (LaboratorioModelo laboratorio : listaLaboratorios) {
            modeloLaboratorio.addRow(new Object[]{
                laboratorio.getNumeroLab(),
                laboratorio.getCapacidad()
            });
        }
    }

    // Método para limpiar campos
    private void limpiarCamposLaboratorio() {
        txtNumeroLab.setText("");
        txtCapacidad.setText("");
    }

    // Método para verificar que los campos no están vacíos
    private boolean seLlenaronTodosLosCamposLaboratorio() {
        return !txtCapacidad.getText().equals("");
    }

    // Método principal
    public static void main(String[] args) {
        Ventana01RegistrosDeUsuarios vtn = new Ventana01RegistrosDeUsuarios();
        vtn.setVisible(true);
    }
}
