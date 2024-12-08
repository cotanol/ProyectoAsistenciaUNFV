package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import Util.ComponentFactory;
import Util.Constantes;
import Util.EstiloHover;
import Model.EquipoModelo;
import Controller.EquipoController;
import Controller.HorarioLaboratorioController;
import Controller.LaboratorioController;
import Model.HorarioLaboratorioModelo;
import Model.LaboratorioModelo;
import java.util.HashSet;
import java.util.Set;

public class VentanaRight3 extends JPanel {
    
    
    // Controlador
    private EquipoController equipoController;
    private HorarioLaboratorioController horarioLaboratorioController;
    private LaboratorioController laboratorioController;

    // Componentes principales
    private JLabel lbTituloPantalla1, lbEquiposDisponiblesLabel;
    private JLabel lbTituloPantalla2, lbRegistroEquiposLabel, lbListaEquiposLabel;
    private JLabel lbTipoEquipoLabel, lbLaboratorioLabel, lbEstadoLabel, lbNumeroSerieLabel, lbCodigoPatrimonialLabel, lbBuscar;
    private JTextField txtNumeroSerie, txtCodigoPatrimonial, txtBuscar;
    private JComboBox<String> cbTipoEquipo, cbLaboratorio, cbEstado;
    private JButton btnAgregarEquipo, btnModificarEquipo, btnEliminarEquipo, btnConfiguracion, btnRegresar, btnExportarExcel;
    private JTable tablaEquiposDisponibles, tablaEquiposRegistrados;
    private DefaultTableModel modeloEquiposDisponibles, modeloEquiposRegistrados;
    
    // Componentes del nuevo panel de Exportación
    private JLabel lbTituloExportExcel;
    private JLabel lbBuscarExport;
    private JTextField txtBuscarExport;
    private JTable tablaEquiposExport;
    private DefaultTableModel modeloEquiposExport;
    private JButton btnRealExportarExcel, btnRegresarExport;

    // Datos
    private ArrayList<EquipoModelo> listaEquipos;
    private EquipoModelo equipo;

    // Layout y paneles
    private CardLayout cardLayout;
    
    private JPanel panelDerecho;
    private JPanel panelRight3;
    private JPanel panelRightConfig;
    private JPanel panelRightExportExcel;
    
    private VentanaRight1 ventanaRight1;
    private VentanaRight2 ventanaRight2;
    private VentanaRight4 ventanaRight4;
    

    public VentanaRight3(EquipoController equipoController, HorarioLaboratorioController horarioLaboratorioController, LaboratorioController laboratorioController) {
        this.equipoController = equipoController;
        this.horarioLaboratorioController = horarioLaboratorioController;
        this.laboratorioController = laboratorioController;
        
        

        setLayout(new CardLayout());
        setBackground(Constantes.COLOR_FONDO_PANEL);

        inicializarComponentes();
        agregarEventos();
        listarEquiposDisponibles();
        
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Filtrar(txtBuscar.getText()); 
            }
        });
    }

    private void inicializarComponentes() {
        // Configuración del CardLayout y panel principal
        cardLayout = (CardLayout) getLayout();
        panelDerecho = this;

        // Panel principal
        panelRight3 = new JPanel(null);
        panelRight3.setBackground(Constantes.COLOR_FONDO_PANEL);
        panelDerecho.add(panelRight3, "ControlEquipos");

        // Título
        lbTituloPantalla1 = ComponentFactory.crearEtiqueta("CONTROL DE EQUIPOS", 50, 30, 590, 52, Constantes.FUENTE_TITULO, Constantes.COLOR_TEXTO_NEGRO);
        panelRight3.add(lbTituloPantalla1);

        // Subtítulo y botón de configuración
        inicializarPanelEquiposDisponibles(panelRight3);

        // Panel de configuración
        configurarPanelRightConfig();
        
        configurarPanelRightExportExcel();
    }

    private void inicializarPanelEquiposDisponibles(JPanel panel) {
        // Subtítulo
        lbEquiposDisponiblesLabel = ComponentFactory.crearEtiqueta("EQUIPOS DISPONIBLES", 100, 110, 500, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        lbEquiposDisponiblesLabel.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        lbEquiposDisponiblesLabel.setOpaque(true);
        lbEquiposDisponiblesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lbEquiposDisponiblesLabel);

        // Botón de Configuración
        btnConfiguracion = ComponentFactory.crearBotonAccion("CONFIGURACIÓN", 970, 170, 250, 50);
        panel.add(btnConfiguracion);

        // Botón Exportar a Excel 
        //btnExportarExcel = ComponentFactory.crearBotonReporteExcel("EXPORTAR A EXCEL", 680, 170, 250, 50); 
        //panel.add(btnExportarExcel);
        
        /*
        btnExportarExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EquipoModelo.cargarBD_Excel();
                JOptionPane.showMessageDialog(null, "Datos exportados a Excel correctamente 🐧!!", "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        */
        
        // Este botón ahora solo cambia al panel de exportación en lugar de exportar directamente
        btnExportarExcel = ComponentFactory.crearBotonReporteExcel("EXPORTAR A EXCEL", 680, 170, 250, 50); 
        panelRight3.add(btnExportarExcel);
        
        // Tabla de Equipos Disponibles
        String[] columnasEquipoDisponible = {"LAB", "TIPO", "Código Patrimonial", "Número de Serie", "Estado"};
        modeloEquiposDisponibles = new DefaultTableModel(columnasEquipoDisponible, 0);
        tablaEquiposDisponibles = ComponentFactory.crearTabla(columnasEquipoDisponible);
        tablaEquiposDisponibles.setModel(modeloEquiposDisponibles);

        JScrollPane scrollTablaEquiposDisponibles = ComponentFactory.crearScrollTabla(tablaEquiposDisponibles, 70, 250, 1150, 550); // Ajustado para no solaparse
        panel.add(scrollTablaEquiposDisponibles);
    }


    private void configurarPanelRightConfig() {
        // Panel de configuración
        panelRightConfig = new JPanel(null);
        panelRightConfig.setBackground(Constantes.COLOR_FONDO_PANEL);
        panelDerecho.add(panelRightConfig, "ConfigurarEquipos");

        // Título
        lbTituloPantalla2 = ComponentFactory.crearEtiqueta("CONFIGURACIÓN DE EQUIPOS", 50, 30, 765, 52, Constantes.FUENTE_TITULO, Constantes.COLOR_TEXTO_NEGRO);
        panelRightConfig.add(lbTituloPantalla2);

        // Subtítulo y subpanel de registro
        inicializarPanelRegistroEquipos(panelRightConfig);

        // Botones de acción
        inicializarBotonesAccion(panelRightConfig);

        // Subtítulo y tabla de equipos registrados
        inicializarPanelListaEquipos(panelRightConfig);
    }

    private void inicializarPanelRegistroEquipos(JPanel panel) {
        // Subtítulo
        lbRegistroEquiposLabel = ComponentFactory.crearEtiqueta("REGISTRO DE EQUIPOS", 100, 110, 500, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        lbRegistroEquiposLabel.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        lbRegistroEquiposLabel.setOpaque(true);
        lbRegistroEquiposLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lbRegistroEquiposLabel);

        // Subpanel de Registro
        JPanel subPanelRegistro = new JPanel(null);
        subPanelRegistro.setBounds(100, 180, 1000, 170);
        subPanelRegistro.setBackground(Color.WHITE);
        subPanelRegistro.setBorder(Constantes.BORDER_NEGRO);
        panel.add(subPanelRegistro);
        
        lbBuscar = ComponentFactory.crearEtiqueta("Buscar: ", 700, 370, 100, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_TEXTO_NEGRO);
        panel.add(lbBuscar);
        
        txtBuscar = ComponentFactory.crearCampoTexto(810, 370, 300, 30, Constantes.BORDER_NEGRO);
        panel.add(txtBuscar);
        
        
        
        // Componentes dentro del subpanel
        inicializarComponentesRegistroEquipos(subPanelRegistro);
    }

    private void inicializarComponentesRegistroEquipos(JPanel subPanel) {
        // Etiqueta y ComboBox para Tipo de Equipo
        lbTipoEquipoLabel = ComponentFactory.crearEtiqueta("Tipo de Equipo", 30, 20, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel.add(lbTipoEquipoLabel);

        cbTipoEquipo = ComponentFactory.crearComboBoxString(new String[]{"", "Teclado", "CPU", "Monitor", "PizarraDigital"}, 30, 50, 300, 30, Constantes.BORDER_HOVER);
        subPanel.add(cbTipoEquipo);

        // Etiqueta y ComboBox para Laboratorio
        lbLaboratorioLabel = ComponentFactory.crearEtiqueta("Laboratorio", 350, 20, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel.add(lbLaboratorioLabel);

        cbLaboratorio = ComponentFactory.crearComboBoxString(new String[]{}, 350, 50, 300, 30, Constantes.BORDER_HOVER);
        subPanel.add(cbLaboratorio);
        cargarComboNroLab();

        // Etiqueta y ComboBox para Estado
        lbEstadoLabel = ComponentFactory.crearEtiqueta("Estado", 670, 20, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel.add(lbEstadoLabel);

        cbEstado = ComponentFactory.crearComboBoxString(new String[]{"OPERATIVO", "INOPERATIVO"}, 670, 50, 300, 30, Constantes.BORDER_HOVER);
        subPanel.add(cbEstado);

        // Etiqueta y Campo de Texto para Número de Serie
        lbNumeroSerieLabel = ComponentFactory.crearEtiqueta("Número de Serie", 30, 90, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel.add(lbNumeroSerieLabel);

        txtNumeroSerie = ComponentFactory.crearCampoTexto(30, 120, 300, 30, Constantes.BORDER_HOVER);
        subPanel.add(txtNumeroSerie);

        // Etiqueta y Campo de Texto para Código Patrimonial
        lbCodigoPatrimonialLabel = ComponentFactory.crearEtiqueta("Código Patrimonial", 350, 90, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel.add(lbCodigoPatrimonialLabel);

        txtCodigoPatrimonial = ComponentFactory.crearCampoTexto(350, 120, 300, 30, Constantes.BORDER_HOVER);
        subPanel.add(txtCodigoPatrimonial);
    }

    private void inicializarBotonesAccion(JPanel panel) {
        // Botones de acción
        btnAgregarEquipo = ComponentFactory.crearBotonAccion("AGREGAR", 700, 110, 150, 50);
        btnModificarEquipo = ComponentFactory.crearBotonAccion("MODIFICAR", 870, 110, 150, 50);
        btnEliminarEquipo = ComponentFactory.crearBotonAccion("ELIMINAR", 1040, 110, 150, 50);
        btnRegresar = ComponentFactory.crearBotonAccion("REGRESAR", 1040, 30, 150, 50);
        
        panel.add(btnAgregarEquipo);
        panel.add(btnModificarEquipo);
        panel.add(btnEliminarEquipo);
        panel.add(btnRegresar);
    }

    private void inicializarPanelListaEquipos(JPanel panel) {
        // Subtítulo
        lbListaEquiposLabel = ComponentFactory.crearEtiqueta("LISTA DE EQUIPOS REGISTRADOS", 100, 360, 560, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        lbListaEquiposLabel.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        lbListaEquiposLabel.setOpaque(true);
        lbListaEquiposLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lbListaEquiposLabel);

        // Tabla de Equipos Registrados
        String[] columnasEquiposRegistrados = {"LAB", "TIPO", "COD. PATRIMONIAL", "NÚMERO DE SERIE", "ESTADO"};
        modeloEquiposRegistrados = new DefaultTableModel(columnasEquiposRegistrados, 0);
        tablaEquiposRegistrados = ComponentFactory.crearTabla(columnasEquiposRegistrados);
        tablaEquiposRegistrados.setModel(modeloEquiposRegistrados);

        JScrollPane scrollTablaEquiposRegistrados = ComponentFactory.crearScrollTabla(tablaEquiposRegistrados, 50, 430, 1150, 400);
        panel.add(scrollTablaEquiposRegistrados);

        // Listar equipos registrados al iniciar
        listarEquiposRegistrados();
    }

    private void agregarEventos() {
        // Eventos de hover
        btnAgregarEquipo.addMouseListener(new EstiloHover.HoverAccionBoton(btnAgregarEquipo));
        btnModificarEquipo.addMouseListener(new EstiloHover.HoverAccionBoton(btnModificarEquipo));
        btnEliminarEquipo.addMouseListener(new EstiloHover.HoverAccionBoton(btnEliminarEquipo));
        btnConfiguracion.addMouseListener(new EstiloHover.HoverAccionBoton(btnConfiguracion));
        btnRegresar.addMouseListener(new EstiloHover.HoverAccionBoton(btnRegresar));
        btnExportarExcel.addMouseListener(new EstiloHover.HoverAccionBotonExcel(btnExportarExcel));
        btnRealExportarExcel.addMouseListener(new EstiloHover.HoverAccionBotonExcel(btnRealExportarExcel));
        btnRegresarExport.addMouseListener(new EstiloHover.HoverAccionBoton(btnRegresarExport));
        
        // Eventos de acción
        btnConfiguracion.addActionListener(e -> manejarConfigurarEquipo());
        btnAgregarEquipo.addActionListener(e -> {
            manejarAgregarEquipo();
            cargarComboNroLab();
        });
        btnModificarEquipo.addActionListener(e -> manejarModificarEquipo());
        btnEliminarEquipo.addActionListener(e -> manejarEliminarEquipo());
        btnRegresar.addActionListener(e -> cardLayout.show(panelDerecho, "ControlEquipos"));

        // Evento de selección en la tabla
        tablaEquiposRegistrados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                llenarCamposDesdeTablaEquipo();
            }
        });
    }
    //====================================================================================================================
    //====================================================================================================================
    //====================================================================================================================
    //====================================================================================================================
    //====================================================================================================================
    //====================================================================================================================
    
    private void configurarPanelRightExportExcel() {
        // Nuevo panel de exportación
        panelRightExportExcel = new JPanel(null);
        panelRightExportExcel.setLayout(null);
        panelRightExportExcel.setBackground(Constantes.COLOR_FONDO_PANEL);
        panelDerecho.add(panelRightExportExcel, "ExportExcelPanel");

        // Título del nuevo panel
        lbTituloExportExcel = ComponentFactory.crearEtiqueta("CONTROL DE EQUIPOS", 50, 30, 765, 52, Constantes.FUENTE_TITULO, Constantes.COLOR_TEXTO_NEGRO);
        panelRightExportExcel.add(lbTituloExportExcel);
        
        JLabel lbFiltracion = ComponentFactory.crearEtiqueta("FILTRACIÓN DE EXPORTACIÓN", 100, 110, 500, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        lbFiltracion.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        lbFiltracion.setOpaque(true);
        lbFiltracion.setHorizontalAlignment(SwingConstants.CENTER);
        panelRightExportExcel.add(lbFiltracion);
        
        // Campo de Búsqueda en el nuevo panel
        lbBuscarExport = ComponentFactory.crearEtiqueta("Buscar: ", 140, 190, 100, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_TEXTO_NEGRO);
        panelRightExportExcel.add(lbBuscarExport);

        txtBuscarExport = ComponentFactory.crearCampoTexto(270, 190, 300, 30, Constantes.BORDER_NEGRO);
        panelRightExportExcel.add(txtBuscarExport);

        // Tabla en el nuevo panel (idéntica a la de configuración)
        String[] columnasExport = {"LAB", "TIPO", "Código Patrimonial", "Número De Serie", "Estado"};
        modeloEquiposExport = new DefaultTableModel(columnasExport, 0);
        tablaEquiposExport = ComponentFactory.crearTabla(columnasExport);
        tablaEquiposExport.setModel(modeloEquiposExport);
        
        // Crear e añadir el scroll pane que contiene la tabla
        JScrollPane scrollTablaEquiposExport = ComponentFactory.crearScrollTabla(tablaEquiposExport, 70, 250, 1150, 550);
        panelRightExportExcel.add(scrollTablaEquiposExport);

        
        // Botón Regresar en el panel de Exportación
        btnRegresarExport = ComponentFactory.crearBotonAccion("REGRESAR", 1020, 170, 200, 50);
        panelRightExportExcel.add(btnRegresarExport);
        btnRegresarExport.addActionListener(e -> cardLayout.show(panelDerecho, "ControlEquipos"));

        // Botón verdadero de Exportar a Excel en este nuevo panel
        btnRealExportarExcel = ComponentFactory.crearBotonReporteExcel("EXPORTAR A EXCEL", 730, 170, 250, 50);
        panelRightExportExcel.add(btnRealExportarExcel);
        btnExportarExcel.addActionListener(e -> {
            cardLayout.show(panelDerecho, "ExportExcelPanel");
        });

        // Evento de filtrado en el nuevo panel
        txtBuscarExport.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FiltrarExport(txtBuscarExport.getText()); 
            }
        });

        // Evento para el botón de exportar real
        btnRealExportarExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EquipoModelo.cargarBD_Excel1();
                JOptionPane.showMessageDialog(null, "Datos exportados a Excel correctamente 🐧!!", "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Listar los equipos para exportar (puedes reutilizar el mismo método o crear uno nuevo)
        listarEquiposParaExportar();
    }
    
    public void cargarComboNroLab() {
        cbLaboratorio.removeAllItems();
        cbLaboratorio.addItem(null);
        Set<String> labsAgregados = new HashSet<>();
        for (LaboratorioModelo lab : laboratorioController.enlistarLaboratorioController()) {
            if (labsAgregados.add(lab.getNumeroLab())) {
                cbLaboratorio.addItem(lab.getNumeroLab());
            }
        }
    }

    // Métodos para el manejo de eventos y lógica

    private void manejarConfigurarEquipo() {
        // Cambia al panel de configuraciones de equipos
        cardLayout.show(panelDerecho, "ConfigurarEquipos");
    }

    private void manejarAgregarEquipo() {
        try {
            if (seLlenaronTodosLosCamposEquipo()) {
                equipo = new EquipoModelo();

                // Asignar valores al objeto equipo
                equipo.setTipoEquipo(cbTipoEquipo.getSelectedItem().toString());
                equipo.setIdLaboratorio(horarioLaboratorioController.obtenerIDLaboratorioPorNumeroController((String)(cbLaboratorio.getSelectedItem())));
                equipo.setEstado(cbEstado.getSelectedItem().toString());
                equipo.setNumeroSerie(txtNumeroSerie.getText().trim());
                equipo.setCodPatrimonial(txtCodigoPatrimonial.getText().trim());

                // Insertar el equipo
                int estado = equipoController.insertarEquipoController(equipo);

                if (estado == 1) {
                    Util.WindowFactory.confirmationWindowCRUD("Registro Insertado","INSERTADO");
                    cargarComboNroLab();
                    
                } else {
                    Util.WindowFactory.errorWindowCRUD("Registro No Insertado","INSERTADO");
                }

                // Actualizar tablas
                listarEquiposDisponibles();
                listarEquiposRegistrados();

                limpiarCamposEquipo();
            } else {
                Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor del laboratorio debe ser un número válido 🐧!!");
        }
    }

    private void manejarModificarEquipo() {
        try {
            int filaSeleccionada = tablaEquiposRegistrados.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un equipo para modificar 🐧!!");
                return;
            }

            if (seLlenaronTodosLosCamposEquipo()) {
                equipo = new EquipoModelo();

                // Asignar valores al objeto equipo
                equipo.setTipoEquipo(cbTipoEquipo.getSelectedItem().toString());
                equipo.setIdLaboratorio( horarioLaboratorioController.obtenerIDLaboratorioPorNumeroController((String) cbLaboratorio.getSelectedItem()));
                equipo.setEstado(cbEstado.getSelectedItem().toString());
                equipo.setNumeroSerie(txtNumeroSerie.getText().trim());
                equipo.setCodPatrimonial(txtCodigoPatrimonial.getText().trim());

                // Modificar el equipo
                int estado = equipoController.modificarEquipoController(equipo);

                if (estado == 1) {
                    Util.WindowFactory.confirmationWindowCRUD("Registro Modificado","MODIFICADO");
                } else {
                    Util.WindowFactory.errorWindowCRUD("Registro No Modificado","MODIFICADO");
                }

                // Actualizar tablas
                listarEquiposDisponibles();
                listarEquiposRegistrados();

                limpiarCamposEquipo();
            } else {
                Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor del laboratorio debe ser un número válido 🐧!!");
        }
    }

    private void manejarEliminarEquipo() {
        int filaSeleccionada = tablaEquiposRegistrados.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un equipo para eliminar 🐧!!");
            return;
        }

        String codigoPatrimonial = modeloEquiposRegistrados.getValueAt(filaSeleccionada, 2).toString();

        if (codigoPatrimonial != null && !codigoPatrimonial.isEmpty()) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este equipo?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                equipo = new EquipoModelo();
                equipo.setCodPatrimonial(codigoPatrimonial);

                int estado = equipoController.eliminarEquipoController(equipo);

                if (estado == 1) {
                    Util.WindowFactory.confirmationWindowCRUD("Registro Eliminado","ELIMINADO");
                } else {
                    Util.WindowFactory.errorWindowCRUD("Registro No Eliminado","ELIMINADO");
                }

                listarEquiposDisponibles();
                listarEquiposRegistrados();

                limpiarCamposEquipo();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Código patrimonial inválido. No se puede eliminar el equipo 🐧!!");
        }
    }
    
    public void Filtrar(String buscar) {
        listaEquipos = equipoController.buscarResgistroEquipos(buscar); // Llama al método Buscar del DAO
        modeloEquiposRegistrados.setRowCount(0); // Limpia la tabla

        for (EquipoModelo obj : listaEquipos) {
            Object[] fila = {
                equipoController.obtenerNumeroLabPorIdController(obj.getIdLaboratorio()),
                obj.getTipoEquipo(),
                obj.getCodPatrimonial(),
                obj.getNumeroSerie(),
                obj.getEstado()
                
            };
            modeloEquiposRegistrados.addRow(fila); // Agrega cada registro filtrado a la tabla
        }
    }
    
    public void FiltrarExport(String buscar) {
        modeloEquiposExport.setRowCount(0);
        listaEquipos = equipoController.buscarResgistroEquipos(buscar);
        for (EquipoModelo obj : listaEquipos) {
            Object[] fila = {
                equipoController.obtenerNumeroLabPorIdController(obj.getIdLaboratorio()),
                obj.getTipoEquipo(),
                obj.getCodPatrimonial(),
                obj.getNumeroSerie(),
                obj.getEstado()
            };
            modeloEquiposExport.addRow(fila);
        }
    }
    
    private boolean seLlenaronTodosLosCamposEquipo() {
        String[] camposTexto = {
            txtNumeroSerie.getText().trim(),
            txtCodigoPatrimonial.getText().trim()
        };

        for (String campo : camposTexto) {
            if (campo.isEmpty()) {
                return false;
            }
        }

        if (cbTipoEquipo.getSelectedIndex() == 0 ||
            cbLaboratorio.getSelectedIndex() == 0) {
            return false;
        }

        
        return true;
    }

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
    
    public void listarEquiposParaExportar() {
        modeloEquiposExport.setRowCount(0);
        listaEquipos = equipoController.enlistarEquipoController();
        for (EquipoModelo equipoTa : listaEquipos) {
            modeloEquiposExport.addRow(new Object[]{
                equipoController.obtenerNumeroLabPorIdController(equipoTa.getIdLaboratorio()),
                equipoTa.getTipoEquipo(),
                equipoTa.getCodPatrimonial(),
                equipoTa.getNumeroSerie(),
                equipoTa.getEstado()
            });
        }
    }

    public void listarEquiposDisponibles() {
        modeloEquiposDisponibles.setRowCount(0);
        listaEquipos = equipoController.enlistarEquipoPorEstadoController("Operativo");

        for (EquipoModelo equipoTa : listaEquipos) {
            modeloEquiposDisponibles.addRow(new Object[]{
                equipoController.obtenerNumeroLabPorIdController(equipoTa.getIdLaboratorio()),
                equipoTa.getTipoEquipo(),
                equipoTa.getCodPatrimonial(),
                equipoTa.getNumeroSerie(),
                equipoTa.getEstado(),
            });
        }
    }

    public void listarEquiposRegistrados() {
        modeloEquiposRegistrados.setRowCount(0);
        listaEquipos = equipoController.enlistarEquipoController();

        for (EquipoModelo equipoTa : listaEquipos) {
            modeloEquiposRegistrados.addRow(new Object[]{
                equipoController.obtenerNumeroLabPorIdController(equipoTa.getIdLaboratorio()),
                equipoTa.getTipoEquipo(),
                equipoTa.getCodPatrimonial(),
                equipoTa.getNumeroSerie(),
                equipoTa.getEstado(),
            });
        }
    }

    private void limpiarCamposEquipo() {
        txtNumeroSerie.setText("");
        txtCodigoPatrimonial.setText("");
        cbTipoEquipo.setSelectedIndex(0);
        cbLaboratorio.setSelectedIndex(0);
        cbEstado.setSelectedIndex(0);
    }
    
    public void sincronizarVentanas(VentanaRight1 ventanaRight1,VentanaRight2 ventanaRight2, VentanaRight4 ventanaRight4) {
        this.ventanaRight1 = ventanaRight1;
        this.ventanaRight2 = ventanaRight2;
        this.ventanaRight4 = ventanaRight4;
    }
}