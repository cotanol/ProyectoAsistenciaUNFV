package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.*;
import Util.ComponentFactory;
import Util.Constantes;
import Util.EstiloHover;
import Model.*;
import Controller.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class VentanaRight2 extends JPanel {

    // Controladores
    private HorarioLaboratorioController horarioLaboratorioController;
    private LaboratorioController laboratorioController;
    private AsistenciaController asistenciaController;

    // Componentes principales
    private JLabel lblControlAsistencia, lblDatosLab, lblConfigAvanz, lblNroLab, lblHor, lblAsigs;
    public static JComboBox<String> cboNroLab;
    private JComboBox<String> cboHorario;
    private JComboBox<String> cboAsignatura;
    private JComboBox<String> cboCodigoHorario;
    private JLabel lblCodigoHorario;
    private JButton btnBuscar, btnActuDatos, btnReporteGen;
    private JTable tablaLaboratorios;
    private DefaultTableModel modeloLaboratorio;

    // Componentes adicionales
    private JLabel lblTituloAsistencia, lblSubTitulo, lblInfoClase, lblTitulo;
    private JTable tablaAsistencia;
    private JButton btnGuardar, btnRegresar;
    private JTextField txtNumeroLab, txtCapacidad;

    // Datos
    private ArrayList<HorarioLaboratorioModelo> listaHorarioLaboratorio;
    

    // Layout y paneles
    private CardLayout cardLayout;
    private JPanel panelDerecho;
    private JPanel panelAsistencia;
    private JPanel panelActualizarLaboratorio;
    
    private VentanaRight3 ventanaRight3;
    private VentanaRight1 ventanaRight1;
    private VentanaRight4 ventanaRight4;

    public VentanaRight2(LaboratorioController laboratorioController, AsistenciaController asistenciaController, HorarioLaboratorioController horarioLaboratorioController) {
        this.laboratorioController = laboratorioController;
        this.asistenciaController = asistenciaController;
        this.horarioLaboratorioController = horarioLaboratorioController;
        
        
        
        setLayout(new CardLayout());
        setBackground(Constantes.COLOR_FONDO_PANEL);
        
        inicializarComponentes();
        agregarEventos();
    }

    private void inicializarComponentes() {
        // Configuración del CardLayout y panel principal
        cardLayout = (CardLayout) getLayout();
        panelDerecho = this;

        // Panel principal
        JPanel panelRight2 = new JPanel(null);
        panelRight2.setBackground(Constantes.COLOR_FONDO_PANEL);
        panelDerecho.add(panelRight2, "ControlAsistencia");

        // Título
        lblControlAsistencia = ComponentFactory.crearEtiqueta("CONTROL DE ASISTENCIA", 50, 30, 670, 52, Constantes.FUENTE_TITULO, Constantes.COLOR_TEXTO_NEGRO);
        panelRight2.add(lblControlAsistencia);

        // Subtítulo y panel de datos del laboratorio
        inicializarPanelDatosLaboratorio(panelRight2);

        // Subtítulo y panel de configuración avanzada
        inicializarPanelConfiguracionAvanzada(panelRight2);
        
    }

    private void inicializarPanelDatosLaboratorio(JPanel panel) {
        listaHorarioLaboratorio = horarioLaboratorioController.enlistarHorarioLaboratorioController();
        
        // Subtítulo
        lblDatosLab = ComponentFactory.crearEtiqueta("DATOS DEL LABORATORIO", 100, 110, 500, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        lblDatosLab.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        lblDatosLab.setOpaque(true);
        lblDatosLab.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblDatosLab);

        // Panel para los datos
        JPanel subPanelDatosLab = new JPanel(null);
        subPanelDatosLab.setBounds(100, 180, 1090, 100);
        subPanelDatosLab.setBackground(Color.WHITE);
        subPanelDatosLab.setBorder(Constantes.BORDER_NEGRO);
        panel.add(subPanelDatosLab);

        // Componentes dentro del panel de datos
        inicializarComponentesDatosLaboratorio(subPanelDatosLab);
    }

    private void inicializarComponentesDatosLaboratorio(JPanel subPanel) {
        // Etiqueta y ComboBox para Número de Laboratorio
        //lblNroLab = ComponentFactory.crearEtiqueta("Nro. Laboratorio", 50, 20, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        //subPanel.add(lblNroLab);

        //cboNroLab = ComponentFactory.crearComboBoxString(new String[]{}, 50, 50, 200, 30, Constantes.BORDER_HOVER);
        //subPanel.add(cboNroLab);
        
        // Etiqueta para el ComboBox de Código de Horario
        lblCodigoHorario = ComponentFactory.crearEtiqueta("Código de Horario:", 50, 20, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel.add(lblCodigoHorario);
        
        // Único ComboBox largo para Código de Horario
        cboCodigoHorario = ComponentFactory.crearComboBoxString(new String[]{}, 50, 50, 700, 30, Constantes.BORDER_HOVER);
        subPanel.add(cboCodigoHorario);
        cargarComboCodigoHorario(); // Método nuevo para llenar el cboCodigoHorario

        // Etiqueta y ComboBox para Horario
        //lblHor = ComponentFactory.crearEtiqueta("Horario", 300, 20, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        //subPanel.add(lblHor);

        //cboHorario = ComponentFactory.crearComboBoxString(new String[]{}, 300, 50, 200, 30, Constantes.BORDER_HOVER);
        //subPanel.add(cboHorario);
        cargarComboHorario();

        // Etiqueta y ComboBox para Asignatura
        //lblAsigs = ComponentFactory.crearEtiqueta("Asignatura", 550, 20, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        //subPanel.add(lblAsigs);

        //cboAsignatura = ComponentFactory.crearComboBoxString(new String[]{}, 550, 50, 200, 30, Constantes.BORDER_HOVER);
        //subPanel.add(cboAsignatura);
        cargarComboAsignatura();

        // Botón de Buscar
        btnBuscar = ComponentFactory.crearBotonAccion("BUSCAR", 850, 35, 150, 50);
        subPanel.add(btnBuscar);
    }

    private void cargarComboNroLab() {
        
    }

    private void cargarComboHorario() {
        
    }

    private void cargarComboAsignatura() {
        
    }
    
    public void cargarComboCodigoHorario() {
        cboCodigoHorario.removeAllItems();
        cboCodigoHorario.addItem(null);

        Set<String> codigosAgregados = new HashSet<>();
        for (HorarioLaboratorioModelo horaLab : horarioLaboratorioController.enlistarHorarioLaboratorioController()) {
            // Ahora usamos directamente el código de horario del objeto
            String codigoHorario = horaLab.getCodigoHorario();

            if (codigosAgregados.add(codigoHorario)) {
                cboCodigoHorario.addItem(codigoHorario);
            }
        }
    }

    private void inicializarPanelConfiguracionAvanzada(JPanel panel) {
        // Subtítulo
        lblConfigAvanz = ComponentFactory.crearEtiqueta("CONFIGURACIÓN AVANZADA", 400, 400, 500, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        lblConfigAvanz.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        lblConfigAvanz.setOpaque(true);
        lblConfigAvanz.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblConfigAvanz);

        // Panel de configuración
        JPanel subPanelConfigAvanz = new JPanel(null);
        subPanelConfigAvanz.setBounds(400, 470, 500, 200);
        subPanelConfigAvanz.setBackground(Color.WHITE);
        subPanelConfigAvanz.setBorder(Constantes.BORDER_NEGRO);
        panel.add(subPanelConfigAvanz);

        // Componentes dentro del panel de configuración
        inicializarComponentesConfiguracionAvanzada(subPanelConfigAvanz);
    }

    private void inicializarComponentesConfiguracionAvanzada(JPanel subPanel) {
        // Botón para Actualizar Datos
        btnActuDatos = ComponentFactory.crearBotonAccion("**REGISTRO ALUMNOS**", 50, 30, 400, 50);
        subPanel.add(btnActuDatos);

        // Botón para Generar Reporte
        btnReporteGen = ComponentFactory.crearBotonAccion("REPORTE GENERAL POR AÑO-MESES", 50, 110, 400, 50);
        subPanel.add(btnReporteGen);
    }

    private void agregarEventos() {
        // Eventos de hover
        btnBuscar.addMouseListener(new EstiloHover.HoverAccionBoton(btnBuscar));
        btnActuDatos.addMouseListener(new EstiloHover.HoverAccionBoton(btnActuDatos));
        btnReporteGen.addMouseListener(new EstiloHover.HoverAccionBoton(btnReporteGen));

        // Eventos de acción
        btnBuscar.addActionListener(e -> manejarBuscarHorario());
        btnActuDatos.addActionListener(e -> manejarCambioAPanelActualizarLaboratorio());
    }

    private void manejarBuscarHorario() {
        String codigoHorario = (String) cboCodigoHorario.getSelectedItem();

        if (codigoHorario == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un código de horario antes de buscar.");
            return;
        }

        try {
            // Remover el panel anterior si existe
            if (panelAsistencia != null) {
                panelDerecho.remove(panelAsistencia);
            }
            
            manejarCambioAPanelAsistencia(codigoHorario);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al buscar los alumnos: " + e.getMessage());
        }
    }

    private void manejarCambioAPanelAsistencia(String codigoHorario) {
        // Buscar el horario seleccionado
        HorarioLaboratorioModelo horarioSeleccionado = null;
        for (HorarioLaboratorioModelo horario : horarioLaboratorioController.enlistarHorarioLaboratorioController()) {
            if (horario.getCodigoHorario().equals(codigoHorario)) {
                horarioSeleccionado = horario;
                break;
            }
        }

        if (horarioSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el horario seleccionado.");
            return;
        }

        panelAsistencia = new JPanel(null);
        panelAsistencia.setBackground(Constantes.COLOR_FONDO_PANEL);

        // Título
        lblTituloAsistencia = ComponentFactory.crearEtiqueta("CONTROL DE ASISTENCIA", 50, 30, 590, 52, Constantes.FUENTE_TITULO, Constantes.COLOR_TEXTO_NEGRO);
        panelAsistencia.add(lblTituloAsistencia);

        // Subtítulo
        lblSubTitulo = ComponentFactory.crearEtiqueta("REGISTRO DE LA CLASE", 100, 110, 500, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        lblSubTitulo.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        lblSubTitulo.setOpaque(true);
        lblSubTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelAsistencia.add(lblSubTitulo);

        String nombreLab = horarioLaboratorioController.obtenerNumeroLabPorIdController(horarioSeleccionado.getIdLaboratorio());
        String nombreAsig = horarioLaboratorioController.obtenerNombreAsignaturaPorIdController(horarioSeleccionado.getIdAsignatura());
        String Hora = horarioSeleccionado.getHorarioInicio();
        String Docente = horarioLaboratorioController.obtenerNombreUsuarioPorIdController(horarioSeleccionado.getIdUsuario());

        // Información del horario
        lblInfoClase = new JLabel(String.format(
                "<html>Nro. Laboratorio: %s<br>Asignatura: %s<br>Horario: %s<br>Docente: %s<br></html>",
                nombreLab, nombreAsig, Hora, Docente));
        lblInfoClase.setBounds(100, 180, 600, 100);
        lblInfoClase.setFont(Constantes.FUENTE_LABEL);
        panelAsistencia.add(lblInfoClase);

        // Inicializar tabla de asistencia con idHorario
        int idHorario = horarioSeleccionado.getIdHorario();
        inicializarTablaAsistencia(idHorario);

        // Botones
        btnGuardar = ComponentFactory.crearBotonAccion("GUARDAR", 1000, 750, 150, 50);
        btnRegresar = ComponentFactory.crearBotonAccion("REGRESAR", 800, 750, 150, 50);

        btnGuardar.addMouseListener(new EstiloHover.HoverAccionBoton(btnGuardar));
        btnRegresar.addMouseListener(new EstiloHover.HoverAccionBoton(btnRegresar));

        btnGuardar.addActionListener(e -> manejarGuardarAsistencia(tablaAsistencia, idHorario));
        btnRegresar.addActionListener(e -> cardLayout.show(panelDerecho, "ControlAsistencia"));

        panelAsistencia.add(btnGuardar);
        panelAsistencia.add(btnRegresar);

        // Añadir panel al CardLayout
        panelDerecho.add(panelAsistencia, "RegistroAsistencia");
        cardLayout.show(panelDerecho, "RegistroAsistencia");
    }

    private void inicializarTablaAsistencia(int idHorario) {
        ArrayList<AlumnoModelo> alumnos = asistenciaController.obtenerAlumnosPorHorario(idHorario);
        if (alumnos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay alumnos para este horario.");
            return;
        }

        int numeroSemanas = 16;

        // Obtener el horario seleccionado
        HorarioLaboratorioModelo horarioSeleccionado = null;
        for (HorarioLaboratorioModelo horario : listaHorarioLaboratorio) {
            if (horario.getIdHorario() == idHorario) {
                horarioSeleccionado = horario;
                break;
            }
        }

        if (horarioSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el horario seleccionado para inicializar la tabla.");
            return;
        }

        // Configurar las fechas
        String fechaInicioStr = horarioSeleccionado.getFechaInicio();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaInicioDate = null;
        try {
            fechaInicioDate = sdf.parse(fechaInicioStr);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al parsear la fecha de inicio: " + fechaInicioStr);
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicioDate);

        // Configurar columnas
        int totalColumnas = numeroSemanas + 2;
        String[] columnNames = new String[totalColumnas];
        columnNames[0] = "Código Alumno";
        columnNames[1] = "Alumno";

        // Array para almacenar las fechas
        LocalDate[] fechas = new LocalDate[numeroSemanas];

        // Generar fechas para las columnas
        for (int i = 2; i < totalColumnas; i++) {
            String fechaCol = sdf.format(calendar.getTime());
            columnNames[i] = fechaCol;
            fechas[i - 2] = LocalDate.parse(fechaCol, java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            calendar.add(Calendar.DATE, 7);
        }

        // Crear modelo de tabla
        DefaultTableModel modeloTabla = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex > 1 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 1;
            }
        };

        // Crear mapa para almacenar las asistencias existentes
        Map<String, Map<LocalDate, Boolean>> asistenciasMap = new HashMap<>();
        ArrayList<AsistenciaModelo> asistenciasGuardadas = asistenciaController.obtenerAsistenciasPorHorarioController(idHorario);

        // Llenar el mapa de asistencias
        for (AsistenciaModelo asistencia : asistenciasGuardadas) {
            String codigoAlumno = asistenciaController.obtenerCodigoAlumnoPorIdController(asistencia.getIdAlumno());
            asistenciasMap.putIfAbsent(codigoAlumno, new HashMap<>());
            asistenciasMap.get(codigoAlumno).put(asistencia.getFecha(), asistencia.getEstado().equals("PRESENTE"));
        }

        // Llenar la tabla
        for (AlumnoModelo alumno : alumnos) {
            Object[] rowData = new Object[totalColumnas];
            rowData[0] = alumno.getCodigoAlumno();
            rowData[1] = alumno.getNombres() + " " + alumno.getApellidos();

            // Obtener asistencias del alumno
            Map<LocalDate, Boolean> asistenciasAlumno = asistenciasMap.getOrDefault(alumno.getCodigoAlumno(), new HashMap<>());

            // Llenar las columnas de asistencia
            for (int i = 0; i < numeroSemanas; i++) {
                LocalDate fecha = fechas[i];
                rowData[i + 2] = asistenciasAlumno.getOrDefault(fecha, Boolean.FALSE);
            }

            modeloTabla.addRow(rowData);
        }

        // Configurar la tabla con alternancia de colores y mantener los checkboxes
        tablaAsistencia = new JTable(modeloTabla) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(245, 245, 245)); // Gris claro
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                } else {
                    c.setBackground(getSelectionBackground());
                }
                return c;
            }
        };
        tablaAsistencia.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Deshabilitar auto-resize

        // Personalizar encabezados
        JTableHeader header = tablaAsistencia.getTableHeader();
        header.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        header.setForeground(Constantes.COLOR_TEXTO_BLANCO);
        header.setFont(Constantes.FUENTE_LABEL.deriveFont(Font.BOLD, 16f)); // Fuente más grande y en negrita

        // Alinear texto al centro en las primeras dos columnas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tablaAsistencia.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tablaAsistencia.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        // Configurar renderizador y editor para las columnas de asistencia (Boolean)
        for (int i = 2; i < tablaAsistencia.getColumnCount(); i++) {
            tablaAsistencia.getColumnModel().getColumn(i).setCellRenderer(tablaAsistencia.getDefaultRenderer(Boolean.class));
            tablaAsistencia.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        }

        // Bordes y rejilla
        tablaAsistencia.setGridColor(Color.LIGHT_GRAY);
        tablaAsistencia.setShowGrid(true);
        tablaAsistencia.setIntercellSpacing(new Dimension(1, 1));

        // Ajustar ancho de columnas
        tablaAsistencia.getColumnModel().getColumn(0).setPreferredWidth(150); // Código Alumno
        tablaAsistencia.getColumnModel().getColumn(1).setPreferredWidth(200); // Alumno
        for (int i = 2; i < tablaAsistencia.getColumnCount(); i++) {
            tablaAsistencia.getColumnModel().getColumn(i).setPreferredWidth(100); // Fechas
        }

        // Establecer selección múltiple si lo deseas
        tablaAsistencia.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Agregar la tabla al panel
        JScrollPane scrollTabla = ComponentFactory.crearScrollTabla(tablaAsistencia, 100, 300, 1100, 400);
        // Opcional: Puedes cambiar las políticas de scroll para que aparezcan solo cuando sea necesario
        scrollTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollTabla.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelAsistencia.add(scrollTabla);
    }


    private void manejarGuardarAsistencia(JTable tabla, int idHorario) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        int totalColumnas = modelo.getColumnCount();
        // Las primeras 2 columnas no son fechas, las siguientes sí
        int columnasFechas = totalColumnas - 2;

        // Obtener las fechas de las columnas
        LocalDate[] fechas = new LocalDate[columnasFechas];
        for (int i = 2; i < totalColumnas; i++) {
            String fechaColStr = (String) modelo.getColumnName(i);
            fechas[i-2] = LocalDate.parse(fechaColStr, java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }

        for (int i = 0; i < modelo.getRowCount(); i++) {
            String codigoAlumno = modelo.getValueAt(i, 0).toString();
            int idAlumno = asistenciaController.obtenerIdAlumnoPorCodigoController(codigoAlumno);
            if (idAlumno == -1) {
                JOptionPane.showMessageDialog(null, "No se encontró el alumno con código: " + codigoAlumno);
                continue;
            }

            // Por cada fecha, guardamos el valor
            for (int c = 0; c < columnasFechas; c++) {
                boolean presente = (boolean) modelo.getValueAt(i, c+2);
                String estado = presente ? "PRESENTE" : "AUSENTE";
                LocalDate fecha = fechas[c];

                // Verificar si ya existe el registro
                boolean existe = asistenciaController.existeAsistenciaController(idAlumno, idHorario, fecha);

                // Crear objeto asistencia
                AsistenciaModelo asistencia = new AsistenciaModelo();
                asistencia.setFecha(fecha);
                asistencia.setEstado(estado);
                asistencia.setIdAlumno(idAlumno);
                asistencia.setIdHorario(idHorario);

                int resultado;
                if (existe) {
                    // Modificar asistencia
                    resultado = asistenciaController.modificarAsistenciaController(asistencia);
                } else {
                    // Insertar asistencia
                    resultado = asistenciaController.insertarAsistenciaController(asistencia);
                }

                if (resultado == 0) {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar la asistencia del alumno: " + codigoAlumno + " en la fecha: " + fecha);
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Asistencia guardada exitosamente.");
    }


    private void manejarCambioAPanelActualizarLaboratorio() {
        panelActualizarLaboratorio = new JPanel(null);
        panelActualizarLaboratorio.setBackground(Constantes.COLOR_FONDO_PANEL);

        // Título
        lblTitulo = ComponentFactory.crearEtiqueta("CONTROL DE ASISTENCIA", 50, 30, 1200, 52, Constantes.FUENTE_TITULO, Constantes.COLOR_TEXTO_NEGRO);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelActualizarLaboratorio.add(lblTitulo);

        // Subtítulo
        lblSubTitulo = ComponentFactory.crearEtiqueta("DATOS DEL LABORATORIO", 150, 110, 1000, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        lblSubTitulo.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        lblSubTitulo.setOpaque(true);
        lblSubTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelActualizarLaboratorio.add(lblSubTitulo);

        // Panel de datos
        JPanel subPanelDatos = new JPanel(null);
        subPanelDatos.setBounds(100, 180, 1100, 120);
        subPanelDatos.setBackground(Color.WHITE);
        subPanelDatos.setBorder(Constantes.BORDER_NEGRO);
        panelActualizarLaboratorio.add(subPanelDatos);

        // Componentes dentro del panel de datos
        inicializarComponentesDatosLaboratorioGestion(subPanelDatos);

        // Botones de acción
        JButton btnAgregarLab = ComponentFactory.crearBotonAccion("Agregar", 300, 350, 180, 60);
        JButton btnModificarLab = ComponentFactory.crearBotonAccion("Modificar", 500, 350, 180, 60);
        JButton btnEliminarLab = ComponentFactory.crearBotonAccion("Eliminar", 700, 350, 180, 60);

        btnAgregarLab.addMouseListener(new EstiloHover.HoverAccionBoton(btnAgregarLab));
        btnModificarLab.addMouseListener(new EstiloHover.HoverAccionBoton(btnModificarLab));
        btnEliminarLab.addMouseListener(new EstiloHover.HoverAccionBoton(btnEliminarLab));

        btnAgregarLab.addActionListener(e -> manejarAgregarLaboratorio());
        btnModificarLab.addActionListener(e -> manejarModificarLaboratorio());
        btnEliminarLab.addActionListener(e -> manejarEliminarLaboratorio());

        panelActualizarLaboratorio.add(btnAgregarLab);
        panelActualizarLaboratorio.add(btnModificarLab);
        panelActualizarLaboratorio.add(btnEliminarLab);

        // Botón Regresar
        JButton btnRegresar = ComponentFactory.crearBotonAccion("REGRESAR", 900, 350, 180, 60);
        btnRegresar.addMouseListener(new EstiloHover.HoverAccionBoton(btnRegresar));
        btnRegresar.addActionListener(e -> cardLayout.show(panelDerecho, "ControlAsistencia"));
        panelActualizarLaboratorio.add(btnRegresar);

        // Imagen
        JLabel lblPinguino = new JLabel();
        lblPinguino.setBounds(100, 450, 350, 300);
        lblPinguino.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon iconoPinguino = new ImageIcon("Images/pinguinoLab.png");
        Image imageEscalada = iconoPinguino.getImage().getScaledInstance(-1, 300, Image.SCALE_SMOOTH);
        lblPinguino.setIcon(new ImageIcon(imageEscalada));
        panelActualizarLaboratorio.add(lblPinguino);

        // Tabla de laboratorios
        inicializarTablaLaboratorios(panelActualizarLaboratorio);

        // Añadir panel al CardLayout
        panelDerecho.add(panelActualizarLaboratorio, "ActualizarLaboratorio");
        cardLayout.show(panelDerecho, "ActualizarLaboratorio");
    }

    private void inicializarComponentesDatosLaboratorioGestion(JPanel subPanel) {
        JLabel lblNroLaboratorio = ComponentFactory.crearEtiqueta("Nro. Laboratorio", 200, 30, 250, 35, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel.add(lblNroLaboratorio);

        txtNumeroLab = ComponentFactory.crearCampoTexto(200, 70, 250, 35, Constantes.BORDER_HOVER);
        subPanel.add(txtNumeroLab);

        JLabel lblCapacidad = ComponentFactory.crearEtiqueta("Capacidad", 530, 30, 250, 35, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel.add(lblCapacidad);

        txtCapacidad = ComponentFactory.crearCampoTexto(530, 70, 250, 35, Constantes.BORDER_HOVER);
        subPanel.add(txtCapacidad);
    }

    private void inicializarTablaLaboratorios(JPanel panel) {
        JPanel subPanelTabla = new JPanel(null);
        subPanelTabla.setBounds(500, 450, 700, 300);
        subPanelTabla.setBackground(Color.WHITE);
        subPanelTabla.setBorder(Constantes.BORDER_NEGRO);
        panel.add(subPanelTabla);

        String[] columnas = {"LAB", "CAPACIDAD"};
        modeloLaboratorio = new DefaultTableModel(columnas, 0);

        tablaLaboratorios = ComponentFactory.crearTabla(columnas);
        tablaLaboratorios.setModel(modeloLaboratorio);

        JScrollPane scrollTabla = ComponentFactory.crearScrollTabla(tablaLaboratorios, 10, 10, 680, 280);
        subPanelTabla.add(scrollTabla);

        tablaLaboratorios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                llenarCamposDesdeTablaLaboratorio();
            }
        });

        listarLaboratorios();
    }

    // Métodos para el manejo de laboratorios

    private void manejarAgregarLaboratorio() {
        if (seLlenaronTodosLosCamposLaboratorio()) {
            LaboratorioModelo laboratorioModelo = new LaboratorioModelo();
            int capacidad = Integer.parseInt(txtCapacidad.getText());
            laboratorioModelo.setCapacidad(capacidad);
            laboratorioModelo.setNumeroLab(txtNumeroLab.getText());

            int estado = laboratorioController.insertarLaboratorioController(laboratorioModelo);
            mostrarMensaje(estado, "Laboratorio Insertado 🧪!!", "Laboratorio no Insertado 🧪!!");
            if (estado == 1) {
                
                actualizarCombosHorarioAsignatura();
                ventanaRight3.cargarComboNroLab();
                ventanaRight4.cargarComboNroLab();
            }
            
            
            
            listarLaboratorios();
            limpiarCamposLaboratorio();
        } else {
            Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO");
        }
    }

    private void manejarModificarLaboratorio() {
        if (seLlenaronTodosLosCamposLaboratorio()) {
            LaboratorioModelo laboratorioModelo = new LaboratorioModelo();
            String numeroLab = txtNumeroLab.getText();
            int capacidad = Integer.parseInt(txtCapacidad.getText());
            laboratorioModelo.setNumeroLab(numeroLab);
            laboratorioModelo.setCapacidad(capacidad);

            int estado = laboratorioController.modificarLaboratorioController(laboratorioModelo);
            mostrarMensaje(estado, "Laboratorio Modificado 🧪!!", "Laboratorio no Modificado 🧪!!");
            if (estado == 1) {
                actualizarCombosHorarioAsignatura();
                ventanaRight3.cargarComboNroLab();
                ventanaRight4.cargarComboNroLab();
            }

            listarLaboratorios();
            limpiarCamposLaboratorio();
        } else {
            Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO");
        }
    }

    private void manejarEliminarLaboratorio() {
        int filaSeleccionada = tablaLaboratorios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un laboratorio para eliminar 🧪!!");
            return;
        }

        try {
            String numeroLabTexto = txtNumeroLab.getText().trim();
            if (numeroLabTexto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo 'Número de Laboratorio' está vacío.");
                return;
            }

            
            LaboratorioModelo laboratorioModelo = new LaboratorioModelo();
            laboratorioModelo.setNumeroLab(numeroLabTexto);

            int estado = laboratorioController.eliminarLaboratorioController(laboratorioModelo);
            mostrarMensaje(estado, "Laboratorio Eliminado 🧪!!", "Laboratorio no Eliminado 🧪!!");
            if (estado == 1) {
                actualizarCombosHorarioAsignatura();
                ventanaRight3.cargarComboNroLab();
                ventanaRight4.cargarComboNroLab();
            }

            listarLaboratorios();
            limpiarCamposLaboratorio();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El número de laboratorio es inválido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error: " + e.getMessage());
        }
    }

    private void mostrarMensaje(int estado, String mensajeExito, String mensajeError) {
        if (estado == 1) {
            JOptionPane.showMessageDialog(null, mensajeExito);
        } else {
            JOptionPane.showMessageDialog(null, mensajeError);
        }
    }

    private void actualizarCombosHorarioAsignatura() {
        listaHorarioLaboratorio = horarioLaboratorioController.enlistarHorarioLaboratorioController();
        // Limpiar y recargar los combos
        cargarComboNroLab();
        cargarComboHorario();
        cargarComboAsignatura();
    }

    private void llenarCamposDesdeTablaLaboratorio() {
        int filaSeleccionada = tablaLaboratorios.getSelectedRow();
        if (filaSeleccionada != -1) {
            txtNumeroLab.setText(modeloLaboratorio.getValueAt(filaSeleccionada, 0).toString());
            txtCapacidad.setText(modeloLaboratorio.getValueAt(filaSeleccionada, 1).toString());
        }
    }

    private void listarLaboratorios() {
        modeloLaboratorio.setRowCount(0);
        ArrayList<LaboratorioModelo> listaLaboratorios = laboratorioController.enlistarLaboratorioController();

        for (LaboratorioModelo laboratorio : listaLaboratorios) {
            modeloLaboratorio.addRow(new Object[]{laboratorio.getNumeroLab(), laboratorio.getCapacidad()});
        }
    }

    private void limpiarCamposLaboratorio() {
        txtNumeroLab.setText("");
        txtCapacidad.setText("");
    }

    private boolean seLlenaronTodosLosCamposLaboratorio() {
        return !txtCapacidad.getText().equals("");
    }
    
    public void sincronizarVentanas(VentanaRight1 ventanaRight1,VentanaRight3 ventanaRight3, VentanaRight4 ventanaRight4) {
        this.ventanaRight3 = ventanaRight3;
        this.ventanaRight1 = ventanaRight1;
        this.ventanaRight4 = ventanaRight4;
    }
    
    
}