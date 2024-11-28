package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import Util.ComponentFactory;
import Util.Constantes;
import Util.EstiloHover;
import Model.UsuarioModelo;
import Controller.UsuarioController;

public class VentanaRight1 extends JPanel {
    
    // Controlador
    private UsuarioController usuarioControlador;

    // Variables de instancia
    private int idUsuario;

    // Componentes principales
    private JLabel lbRegistrosUsuarios, nombreSubPanel1_1, nombreSubPanel1_2;
    private JPanel subPanel1;
    private JLabel lbNombres, lbApellidos, lbUsuario, lbTipoDeDocumento, lbNumeroDeContacto, lbContraseña, lbNumeroDeDocumento, lbCargo, lbEmail, lbBuscar;
    private JTextField txtNombres, txtApellidos, txtUsuario, txtNumeroDeContacto, txtContraseña, txtNumeroDeDocumento, txtEmail, txtBuscar;
    private JComboBox<String> cbTipoDeDocumento, cbCargo;
    private JButton btnAgregar, btnModificar, btnEliminar, btnExportarExcel;
    private JTable tablaUsuarios;
    private DefaultTableModel modeloUsuario;

    // Datos
    private ArrayList<UsuarioModelo> listaUsuarios;

    public VentanaRight1(UsuarioController usuarioControlador) {
        this.usuarioControlador = usuarioControlador;

        setLayout(null);
        setBackground(Constantes.COLOR_FONDO_PANEL);

        inicializarComponentes();
        agregarEventos();
        listarUsuario();
        
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Filtrar(txtBuscar.getText()); // Llama al método de filtro
            }
        });
        
        

    }

    private void inicializarComponentes() {
        // Título
        lbRegistrosUsuarios = ComponentFactory.crearEtiqueta("REGISTRO DE USUARIOS", 50, 30, 630, 52, Constantes.FUENTE_TITULO, Constantes.COLOR_TEXTO_NEGRO);
        add(lbRegistrosUsuarios);

        // Subtítulo
        nombreSubPanel1_1 = ComponentFactory.crearEtiqueta("DATOS DEL REGISTRO", 100, 110, 500, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        nombreSubPanel1_1.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        nombreSubPanel1_1.setOpaque(true);
        nombreSubPanel1_1.setHorizontalAlignment(SwingConstants.CENTER);
        add(nombreSubPanel1_1);
        
        // Subpanel para datos del registro
        subPanel1 = new JPanel(null);
        subPanel1.setBounds(100, 180, 1090, 270);
        subPanel1.setBackground(Color.WHITE);
        subPanel1.setBorder(Constantes.BORDER_NEGRO);
        add(subPanel1);

        // Campos y etiquetas
        inicializarCamposRegistro();

        // Botones
        btnAgregar = ComponentFactory.crearBotonAccion("AGREGAR", 700, 110, 150, 50);
        btnModificar = ComponentFactory.crearBotonAccion("MODIFICAR", 870, 110, 150, 50);
        btnEliminar = ComponentFactory.crearBotonAccion("ELIMINAR", 1040, 110, 150, 50);

        add(btnAgregar);
        add(btnModificar);
        add(btnEliminar);

        // Subtítulo para la tabla
        nombreSubPanel1_2 = ComponentFactory.crearEtiqueta("LISTA DE USUARIOS REGISTRADOS", 100, 490, 570, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        nombreSubPanel1_2.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        nombreSubPanel1_2.setOpaque(true);
        nombreSubPanel1_2.setHorizontalAlignment(SwingConstants.CENTER);
        add(nombreSubPanel1_2);

        // Tabla de usuarios
        String[] columnasUsuario = {"Id", "Nombres", "Apellidos", "Tipo Doc.", "Nro. Doc.", "Número", "Cargo", "Usuario", "Contraseña", "Email"};
        modeloUsuario = new DefaultTableModel(columnasUsuario, 0);
        tablaUsuarios = ComponentFactory.crearTabla(columnasUsuario);
        tablaUsuarios.setModel(modeloUsuario);

        JScrollPane scrollTabla = ComponentFactory.crearScrollTabla(tablaUsuarios, 100, 560, 1090, 270);
        add(scrollTabla);
        
        // Implementación del Label y TextField Buscar
        lbBuscar = ComponentFactory.crearEtiqueta("Buscar: ", 700, 500, 100, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_TEXTO_NEGRO);
        add(lbBuscar);
        
        txtBuscar = ComponentFactory.crearCampoTexto(810, 500, 300, 30, Constantes.BORDER_NEGRO);
        add(txtBuscar);
            
        btnExportarExcel = ComponentFactory.crearBotonReporteExcel("EXPORTAR A EXCEL", 940, 40, 250, 50);
        add(btnExportarExcel);
        
        btnExportarExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioModelo.cargarBD_Excel();
                JOptionPane.showMessageDialog(null, "Datos exportados a Excel correctamente 🐧!!", "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        });
               
    }

    private void inicializarCamposRegistro() {
        // Etiquetas y campos de texto
        lbNombres = ComponentFactory.crearEtiqueta("Nombres", 30, 20, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbNombres);
        txtNombres = ComponentFactory.crearCampoTexto(30, 50, 300, 30, Constantes.BORDER_HOVER);
        subPanel1.add(txtNombres);

        lbApellidos = ComponentFactory.crearEtiqueta("Apellidos", 30, 100, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbApellidos);
        txtApellidos = ComponentFactory.crearCampoTexto(30, 130, 300, 30, Constantes.BORDER_HOVER);
        subPanel1.add(txtApellidos);

        lbUsuario = ComponentFactory.crearEtiqueta("Usuario", 30, 180, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbUsuario);
        txtUsuario = ComponentFactory.crearCampoTexto(30, 210, 300, 30, Constantes.BORDER_HOVER);
        subPanel1.add(txtUsuario);

        lbTipoDeDocumento = ComponentFactory.crearEtiqueta("Tipo de Documento", 435, 20, 210, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbTipoDeDocumento);
        cbTipoDeDocumento = ComponentFactory.crearComboBoxString(new String[]{"", "DNI", "PASAPORTE"}, 435, 50, 210, 30, Constantes.BORDER_HOVER);
        subPanel1.add(cbTipoDeDocumento);

        lbNumeroDeContacto = ComponentFactory.crearEtiqueta("Nro. de Contacto", 435, 100, 210, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbNumeroDeContacto);
        txtNumeroDeContacto = ComponentFactory.crearCampoTexto(435, 130, 210, 30, Constantes.BORDER_HOVER);
        subPanel1.add(txtNumeroDeContacto);

        lbContraseña = ComponentFactory.crearEtiqueta("Contraseña", 435, 180, 210, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbContraseña);
        txtContraseña = ComponentFactory.crearCampoTexto(435, 210, 210, 30, Constantes.BORDER_HOVER);
        subPanel1.add(txtContraseña);

        lbNumeroDeDocumento = ComponentFactory.crearEtiqueta("Nro. de Documento", 730, 20, 250, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbNumeroDeDocumento);
        txtNumeroDeDocumento = ComponentFactory.crearCampoTexto(730, 50, 300, 30, Constantes.BORDER_HOVER);
        subPanel1.add(txtNumeroDeDocumento);

        lbCargo = ComponentFactory.crearEtiqueta("Cargo", 730, 100, 250, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbCargo);
        cbCargo = ComponentFactory.crearComboBoxString(new String[]{"", "ADMINISTRADOR", "DOCENTE"}, 730, 130, 300, 30, Constantes.BORDER_HOVER);
        subPanel1.add(cbCargo);

        lbEmail = ComponentFactory.crearEtiqueta("Email", 730, 180, 250, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbEmail);
        txtEmail = ComponentFactory.crearCampoTexto(730, 210, 300, 30, Constantes.BORDER_HOVER);
        subPanel1.add(txtEmail);   
    }

    private void agregarEventos() {
        // Eventos para botones
        btnAgregar.addActionListener(e -> manejarAgregarUsuario());
        btnModificar.addActionListener(e -> manejarModificarUsuario());
        btnEliminar.addActionListener(e -> manejarEliminarUsuario());

        // Eventos de hover en botones
        btnAgregar.addMouseListener(new EstiloHover.HoverAccionBoton(btnAgregar));
        btnModificar.addMouseListener(new EstiloHover.HoverAccionBoton(btnModificar));
        btnEliminar.addMouseListener(new EstiloHover.HoverAccionBoton(btnEliminar));
        btnExportarExcel.addMouseListener(new EstiloHover.HoverAccionBotonExcel(btnExportarExcel));
        
        // Evento de selección en la tabla
        tablaUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                llenarCamposDesdeTabla();
            }
        });
    }

    private void manejarAgregarUsuario() {
        if (seLlenaronTodosLosCampos()) {
            UsuarioModelo usuarioModelo = new UsuarioModelo();

            usuarioModelo.setNombres(txtNombres.getText());
            usuarioModelo.setApellidos(txtApellidos.getText());
            usuarioModelo.setNombreUsuario(txtUsuario.getText());
            usuarioModelo.setTipoDocumento(cbTipoDeDocumento.getSelectedItem().toString());
            usuarioModelo.setNumero(txtNumeroDeContacto.getText());
            usuarioModelo.setContrasena(txtContraseña.getText());
            usuarioModelo.setNroDocumento(txtNumeroDeDocumento.getText());
            usuarioModelo.setCargo(cbCargo.getSelectedItem().toString());
            usuarioModelo.setEmail(txtEmail.getText());
            usuarioModelo.setIdUsuario(usuarioControlador.ultimoIdController() + 1);

            int estado = usuarioControlador.insertarUsuarioController(usuarioModelo);

            mostrarMensaje(estado, "Registro Insertado 🐧!!", "Registro no Insertado 🐧!!");

            listarUsuario();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Llena todos los campos 🐧!!");
        }
    }

    private void manejarModificarUsuario() {
        UsuarioModelo usuarioModelo = new UsuarioModelo();

        usuarioModelo.setNombres(txtNombres.getText());
        usuarioModelo.setApellidos(txtApellidos.getText());
        usuarioModelo.setNombreUsuario(txtUsuario.getText());
        usuarioModelo.setTipoDocumento(cbTipoDeDocumento.getSelectedItem().toString());
        usuarioModelo.setNumero(txtNumeroDeContacto.getText());
        usuarioModelo.setContrasena(txtContraseña.getText());
        usuarioModelo.setNroDocumento(txtNumeroDeDocumento.getText());
        usuarioModelo.setCargo(cbCargo.getSelectedItem().toString());
        usuarioModelo.setEmail(txtEmail.getText());
        usuarioModelo.setIdUsuario(idUsuario);

        int estado = usuarioControlador.modificarUsuarioController(usuarioModelo);

        mostrarMensaje(estado, "Registro Modificado 🐧!!", "Registro no Modificado 🐧!!");

        listarUsuario();
        limpiarCampos();
    }

    private void manejarEliminarUsuario() {
        UsuarioModelo usuarioModelo = new UsuarioModelo();
        usuarioModelo.setIdUsuario(idUsuario);

        int estado = usuarioControlador.eliminarUsuarioController(usuarioModelo);

        mostrarMensaje(estado, "Registro Eliminado 🐧!!", "Registro no Eliminado 🐧!!");

        listarUsuario();
        limpiarCampos();
    }

    private void mostrarMensaje(int estado, String mensajeExito, String mensajeError) {
        if (estado == 1) {
            JOptionPane.showMessageDialog(null, mensajeExito);
        } else {
            JOptionPane.showMessageDialog(null, mensajeError);
        }
    }

    private boolean seLlenaronTodosLosCampos() {
        String[] campos = {
            txtNombres.getText().trim(),
            txtApellidos.getText().trim(),
            txtUsuario.getText().trim(),
            cbTipoDeDocumento.getSelectedItem().toString(),
            txtNumeroDeContacto.getText().trim(),
            txtContraseña.getText().trim(),
            txtNumeroDeDocumento.getText().trim(),
            cbCargo.getSelectedItem().toString(),
            txtEmail.getText().trim()
        };

        for (String campo : campos) {
            if (campo.isEmpty()) {
                return false;
            }
        }
        return true;
    }

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
    
    public void Filtrar(String buscar) {
        listaUsuarios = usuarioControlador.buscarResgistroUsuarios(buscar); // Llama al método Buscar del DAO
        modeloUsuario.setRowCount(0); // Limpia la tabla

        for (UsuarioModelo obj : listaUsuarios) {
            Object[] fila = {
                obj.getIdUsuario(),
                obj.getNombres(),
                obj.getApellidos(),
                obj.getNombreUsuario(),
                obj.getTipoDocumento(),
                obj.getNroDocumento(),
                obj.getNumero(),
                obj.getCargo(),
                obj.getContrasena(),
                obj.getEmail()
                
            };
            modeloUsuario.addRow(fila); // Agrega cada registro filtrado a la tabla
        }
    }
    
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
}