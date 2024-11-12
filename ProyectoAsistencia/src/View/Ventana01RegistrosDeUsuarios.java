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

public class Ventana01RegistrosDeUsuarios extends JFrame {

    // Variables de instancia
    private int idUsuario;
    private JPanel panelLeft, panelDerecho;
    private JPanel panelLogo, panelUser;
    private JButton btnRegistroUsuarios, btnControlAsistencia, btnControlEquipos, btnHorariosLaboratorio, btnCerrarSesion;
    private JLabel lbImagenUser, lbImagenLogo, lbNameUser;

    // Paneles derechos
    private JPanel panelRight1, panelRight2, panelRight3, panelRight4;

    // Componentes de panelRight1
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

    // Modelo
    private ArrayList<UsuarioModelo> listaUsuarios;
    private UsuarioController usuarioControlador;

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
        // Configuración de la ventana
        setSize(1600, 900);
        setTitle("GESTOR DE LABORATORIO DE LA UNIVERSIDAD NACIONAL FEDERICO VILLARREAL");
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        usuarioControlador = new UsuarioController(this);

        // Inicialización de componentes
        inicializarComponentes();

        // Configuración de paneles y componentes
        configurarPaneles();
        configurarMenu();
        configurarEventos();

        // Listar usuarios
        listarUsuario();
    }

    private void inicializarComponentes() {
        // Panel izquierdo
        panelLeft = new JPanel(null);
        panelLeft.setBounds(0, 0, 300, 900);
        panelLeft.setBackground(COLOR_BASE_BOTONES);

        // Panel derecho con CardLayout
        cardLayout = new CardLayout();
        panelDerecho = new JPanel(cardLayout);
        panelDerecho.setBounds(300, 0, 1300, 900);

        // Paneles derechos individuales
        panelRight1 = new JPanel(null);
        panelRight1.setBackground(COLOR_FONDO_PANEL);

        panelRight2 = new JPanel(null);
        panelRight2.setBackground(COLOR_FONDO_PANEL);

        panelRight3 = new JPanel(null);
        panelRight3.setBackground(COLOR_FONDO_PANEL);

        panelRight4 = new JPanel(null);
        panelRight4.setBackground(COLOR_FONDO_PANEL);

        // Agregar paneles derechos al CardLayout
        panelDerecho.add(panelRight1, "RegistroUsuarios");
        panelDerecho.add(panelRight2, "ControlAsistencia");
        panelDerecho.add(panelRight3, "ControlEquipos");
        panelDerecho.add(panelRight4, "HorariosLaboratorio");

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
        lbImagenLogo = new JLabel(imagenEscaladaLogo, SwingConstants.CENTER); // Centrado horizontal
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

        // Configurar panelRight1 (Registro de Usuarios)
        configurarPanelRight1();
    }

    private void configurarMenu() {
        // Asignar el primer botón como seleccionado por defecto
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
        btnAgregar.addActionListener(e -> manejarAgregarUsuario());
        btnModificar.addActionListener(e -> manejarModificarUsuario());
        btnEliminar.addActionListener(e -> manejarEliminarUsuario());

        btnAgregar.addMouseListener(new EstiloHoverAccionBoton(btnAgregar));
        btnModificar.addMouseListener(new EstiloHoverAccionBoton(btnModificar));
        btnEliminar.addMouseListener(new EstiloHoverAccionBoton(btnEliminar));

        // Evento de la tabla
        tablaUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                llenarCamposDesdeTabla();
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

    // Método principal
    public static void main(String[] args) {
        Ventana01RegistrosDeUsuarios vtn = new Ventana01RegistrosDeUsuarios();
        vtn.setVisible(true);
    }
}
