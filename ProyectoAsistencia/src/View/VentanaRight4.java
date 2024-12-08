package View;

import Controller.AsignaturaController;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import Util.ComponentFactory;
import Util.Constantes;
import Util.EstiloHover;
import Model.HorarioLaboratorioModelo;
import Controller.HorarioLaboratorioController;
import Controller.LaboratorioController;
import java.text.Normalizer;
import com.toedter.calendar.JDateChooser;
import DAO.GenerarTablaClases;
import Model.UsuarioModelo;
import Controller.UsuarioController;
import Model.LaboratorioModelo;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Util.Conexion_BD;
import creandotablasjava.GenerarTablaClases1;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentanaRight4 extends JPanel implements ActionListener {
    // Controlador
    private HorarioLaboratorioController horarioControlador;
    private UsuarioController usuarioControlador;
    private LaboratorioController laboratorioControlador;

    // Componentes principales
    private JLabel lbRegistrosHorarios;
    private JLabel nombreSubPanel1_1, nombreSubPanel1_2;
    private JPanel subPanel1;
    private JLabel lbBuscarLaboratorio;
    private JTextField txtBuscarLaboratorio;
    private JButton btnExportarExcel, btnCrearClase, btnConfiguracion;
    private JTable tablaHorarios;
    private DefaultTableModel modeloHorarios;
    
    private JLabel lbdocente, lbasignatura, lbnroclases, lbnrolab, lbdiax, lbdiay, lbfechainiciox, lbfechainicioy, lbhorainiciox, lbhorainicioy, lbhorafinx, lbhorafiny;
    private JComboBox<String> comboDocente, comboLaboratorio, combonroClases,comboAsignatura, comboDiaX, comboDiaY;
    private JTextField  txtHoraInicioX, txtHoraFinX, txtHoraInicioY, txtHoraFinY;
    private JDateChooser calendarInicioX, calendarInicioY;
    
    private JTextField txtBuscar;
    private JLabel lbBuscar;
    
    private VentanaRight1 ventanaRight1;
    private VentanaRight2 ventanaRight2;
    private VentanaRight3 ventanaRight3;
    
    // Datos
    private ArrayList<HorarioLaboratorioModelo> listaHorarios;

    public VentanaRight4(HorarioLaboratorioController horarioControlador, UsuarioController usuarioControlador, LaboratorioController laboratorioControlador) {
        this.horarioControlador = horarioControlador;
        this.usuarioControlador = usuarioControlador;
        this.laboratorioControlador = laboratorioControlador;

        setLayout(null);
        setBackground(Constantes.COLOR_FONDO_PANEL);

        inicializarComponentes();
        agregarEventos();
        listarHorarios();
    }

    private void inicializarComponentes() {
        // Título
        lbRegistrosHorarios = ComponentFactory.crearEtiqueta("HORARIOS DE LABORATORIO", 50, 30, 750, 52, Constantes.FUENTE_TITULO, Constantes.COLOR_TEXTO_NEGRO);
        add(lbRegistrosHorarios);

        // Subtítulo
        nombreSubPanel1_1 = ComponentFactory.crearEtiqueta("DATOS DE LA NUEVA CLASE", 100, 110, 500, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        nombreSubPanel1_1.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        nombreSubPanel1_1.setOpaque(true);
        nombreSubPanel1_1.setHorizontalAlignment(SwingConstants.CENTER);
        add(nombreSubPanel1_1);
        
        
        btnCrearClase = ComponentFactory.crearBotonAccion("CREAR CLASE", 700, 110, 210, 50);
        btnConfiguracion = ComponentFactory.crearBotonAccion("CONFIGURACION", 940, 110, 250, 50);
        btnExportarExcel = ComponentFactory.crearBotonReporteExcel("EXPORTAR A EXCEL", 940, 40, 250, 50);     
        add(btnCrearClase);
        add(btnConfiguracion);
        add(btnExportarExcel);


        // Subtítulo para la tabla
        nombreSubPanel1_2 = ComponentFactory.crearEtiqueta("LISTA DE HORARIOS", 100, 490, 570, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        nombreSubPanel1_2.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        nombreSubPanel1_2.setOpaque(true);
        nombreSubPanel1_2.setHorizontalAlignment(SwingConstants.CENTER);
        add(nombreSubPanel1_2);

        // Tabla de horarios
        String[] columnasHorarios = {"Laboratorio","Asignatura","Docente","Día", "Hora Inicio", "Hora Fin", "Codigo Horario"};
        modeloHorarios = new DefaultTableModel(null, columnasHorarios);
        tablaHorarios = ComponentFactory.crearTabla(columnasHorarios);
        tablaHorarios.setModel(modeloHorarios);

        JScrollPane scrollTabla = ComponentFactory.crearScrollTabla(tablaHorarios, 100, 560, 1090, 270);
        add(scrollTabla);
        
        inicializarComponentesDatosNuevaClase();
        
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Filtrar(txtBuscar.getText()); // Llama al método de filtro
            }
        });
        
    }
    
    public void inicializarComponentesDatosNuevaClase(){      
        // Subpanel para datos del registro
        subPanel1 = new JPanel(null);
        subPanel1.setBounds(100, 180, 1090, 270);
        subPanel1.setBackground(Color.WHITE);
        subPanel1.setBorder(Constantes.BORDER_NEGRO);
        add(subPanel1);
         
        lbdocente = ComponentFactory.crearEtiqueta("Docente", 30, 20, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbdocente);
        
        //SE SUPONE QUE EL COMBO BOX SE CARGAR CON LOS VALORES DE LA BASE DE DATOS, LO DEL CODE PATROMONIAL ES PRUBEA NOMAS
        
        String[] dias = {"","Lunes", "Martes", "Miercoles", "Juves", "Viernes", "Sabado", "Domingo"};
        
        
        String[] clases = {"","16", "32"}; //ESTO SE QUEDA FIJO SI O SI
        comboDocente = ComponentFactory.crearComboBoxString(new String[]{},30, 50, 300, 30, Constantes.BORDER_HOVER);
        
        subPanel1.add(comboDocente);
        
        lbasignatura = ComponentFactory.crearEtiqueta("Asignatura", 30, 100, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbasignatura);
        
        comboAsignatura = ComponentFactory.crearComboBoxString(new String[]{},30, 130, 300, 30, Constantes.BORDER_HOVER);
        subPanel1.add(comboAsignatura);
        
        
        lbnroclases = ComponentFactory.crearEtiqueta("Nro. Clases", 30, 180, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbnroclases);
        combonroClases = ComponentFactory.crearComboBoxString(clases,30, 210, 300, 30, Constantes.BORDER_HOVER);
        subPanel1.add(combonroClases);
        
        lbnrolab = ComponentFactory.crearEtiqueta("Nro. LAB", 410, 20, 210, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbnrolab);
        
        comboLaboratorio = ComponentFactory.crearComboBoxString(new String[]{}, 410, 50, 150, 30, Constantes.BORDER_HOVER);
        subPanel1.add(comboLaboratorio);
        
        lbdiax = ComponentFactory.crearEtiqueta("Dia X", 410, 100, 210, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbdiax);
        comboDiaX = ComponentFactory.crearComboBoxString(dias,410, 130, 150, 30, Constantes.BORDER_HOVER);
        comboDiaX.setEnabled(false);
        subPanel1.add(comboDiaX);

        lbdiay = ComponentFactory.crearEtiqueta("Dia Y", 410, 180, 210, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbdiay);
        comboDiaY = ComponentFactory.crearComboBoxString(dias, 410, 210, 150, 30, Constantes.BORDER_HOVER);
        comboDiaY.setEnabled(false);
        subPanel1.add(comboDiaY);

        lbfechainiciox = ComponentFactory.crearEtiqueta("Fecha de Inicio (X)", 630, 20, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbfechainiciox);
        calendarInicioX = new JDateChooser();
        calendarInicioX.setBounds(630, 50, 170, 30); // Posición y tamaño
        calendarInicioX.setFont(Constantes.FUENTE_LABEL);
        calendarInicioX.setBorder(Constantes.BORDER_HOVER);
        calendarInicioX.setEnabled(false);
        subPanel1.add(calendarInicioX);

        lbhorainiciox = ComponentFactory.crearEtiqueta("Hora Inicio - Dia X", 630, 100, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbhorainiciox);
        txtHoraInicioX = ComponentFactory.crearCampoTexto( 630, 130, 170, 30, Constantes.BORDER_HOVER);
        txtHoraInicioX.setEnabled(false);
        subPanel1.add(txtHoraInicioX);
        
        lbhorafinx = ComponentFactory.crearEtiqueta("Hora Fin - Dia X", 630, 180, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbhorafinx);
        txtHoraFinX = ComponentFactory.crearCampoTexto(630, 210, 170, 30, Constantes.BORDER_HOVER);
        txtHoraFinX.setEnabled(false);
        subPanel1.add(txtHoraFinX);
        
        lbfechainicioy = ComponentFactory.crearEtiqueta("Fecha de Inicio (Y)", 870, 20, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbfechainicioy);
        calendarInicioY = new JDateChooser();
        calendarInicioY.setBounds(870, 50, 170, 30); // Posición y tamaño
        calendarInicioY.setFont(Constantes.FUENTE_LABEL);
        calendarInicioY.setBorder(Constantes.BORDER_HOVER);
        calendarInicioY.setEnabled(false);
        subPanel1.add(calendarInicioY);

        lbhorainicioy= ComponentFactory.crearEtiqueta("Hora Inicio - Dia Y", 870, 100, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbhorainicioy);
        txtHoraInicioY = ComponentFactory.crearCampoTexto( 870, 130, 170, 30, Constantes.BORDER_HOVER);
        txtHoraInicioY.setEnabled(false);
        subPanel1.add(txtHoraInicioY);
        
        lbhorafiny = ComponentFactory.crearEtiqueta("Hora Fin - Dia Y", 870, 180, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbhorafiny);
        txtHoraFinY = ComponentFactory.crearCampoTexto(870, 210, 170, 30, Constantes.BORDER_HOVER);
        txtHoraFinY.setEnabled(false);
        subPanel1.add(txtHoraFinY);
        
        lbBuscar = ComponentFactory.crearEtiqueta("Buscar: ", 700, 500, 100, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_TEXTO_NEGRO);
        add(lbBuscar);
        txtBuscar = ComponentFactory.crearCampoTexto(810, 500, 300, 30, Constantes.BORDER_NEGRO);
        add(txtBuscar);
        
        cargarComboNroLab();
        cargarComboDocente();
        cargarComboAsignatura();
        
        combonroClases.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numClases = Integer.parseInt(combonroClases.getSelectedItem().toString());
                if (numClases == 16) {
                    activarCamposX();
                } else if (numClases == 32) {
                    activarCamposXY();
                }
            }
        });
    }
    
    private void agregarEventos() {
        // Eventos para botones
        // btnExprotarExcel, btnCrearClase, btnConfiguracion,
        
        //btnAgregar.addActionListener(e -> manejarAgregarHorario());
        //btnModificar.addActionListener(e -> manejarModificarHorario());
        //btnEliminar.addActionListener(e -> manejarEliminarHorario());
        btnConfiguracion.addActionListener((e)->Configuracion());
        btnExportarExcel.addActionListener((e)->{
           horarioControlador.exportarUsuariosAExcel();
           JOptionPane.showMessageDialog(null, "Datos exportados a Excel correctamente 🐧!!", "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        });
        
        // Eventos de hover en botones
        //btnAgregar.addMouseListener(new EstiloHover.HoverAccionBoton(btnAgregar));
        //btnModificar.addMouseListener(new EstiloHover.HoverAccionBoton(btnModificar));
        //btnEliminar.addMouseListener(new EstiloHover.HoverAccionBoton(btnEliminar));
        btnCrearClase.addMouseListener(new EstiloHover.HoverAccionBoton(btnCrearClase));
        btnConfiguracion.addMouseListener(new EstiloHover.HoverAccionBoton(btnConfiguracion));
        btnExportarExcel.addMouseListener(new EstiloHover.HoverAccionBotonExcel(btnExportarExcel));
        
        // Evento de selección en la tabla
        tablaHorarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                llenarCamposDesdeTabla();
            }
        });
        
        btnCrearClase.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String dia1 = comboDiaX.getSelectedItem().toString();
            String dia2 = comboDiaY.getSelectedItem().toString();
            String docente = comboDocente.getSelectedItem().toString();
            String laboratorio = comboLaboratorio.getSelectedItem().toString();
            String asignatura = comboAsignatura.getSelectedItem().toString();
            String horaInicioX = txtHoraInicioX.getText();
            String horaFinX = txtHoraFinX.getText();
            String horaInicioY = txtHoraInicioY.getText();
            String horaFinY = txtHoraFinY.getText();
            
            
            // Obtener las fechas de los calendarios
            Date fechaInicioDateX = calendarInicioX.getDate();
            Date fechaInicioDateY = calendarInicioY.getDate();

            int nummeroClass = Integer.parseInt(combonroClases.getSelectedItem().toString());

            // Verificar que los campos obligatorios no están vacíos
            if (nummeroClass == 16) {
                if (docente.isEmpty() || horaInicioX.isEmpty() || horaFinX.isEmpty() || fechaInicioDateX == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos necesarios.");
                    return;
                }
            } else if (nummeroClass == 32) {
                if (docente.isEmpty() || horaInicioX.isEmpty() || horaFinX.isEmpty() || horaInicioY.isEmpty() || horaFinY.isEmpty() || fechaInicioDateX == null || fechaInicioDateY == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos necesarios.");
                    return;
                }
            }

            // Verificar si las fechas son válidas antes de continuar
            if (fechaInicioDateX == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha de inicio para el día X.");
                return;
            }
            if (nummeroClass == 32 && fechaInicioDateY == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha de inicio para el día Y.");
                return;
            }

            // Formatear las fechas seleccionadas a 'dd-MM-yyyy'
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String fechaInicioFormateadaX = null;
            String fechaInicioFormateadaY = null;
            String nombreTablaX = null;
            String nombreTablaY = null;

            if (nummeroClass == 16) {
                fechaInicioFormateadaX = sdf.format(fechaInicioDateX);
                // Formatear el nombre de la tabla para el día X
                nombreTablaX =
                        laboratorio.toLowerCase().replace(" ", "_") + "_" +
                        dia1.toLowerCase() + "_" +
                        docente.toLowerCase().replace(" ", "_") + "_" +
                        asignatura.toLowerCase().replace(" ", "_") + "_" +
                        horaInicioX.replace(":", "y") + "_" +
                        horaFinX.replace(":", "y");
                JOptionPane.showMessageDialog(null, "Nombre de la tabla generado: \n" + nombreTablaX);
            } else if (nummeroClass == 32) {
                fechaInicioFormateadaX = sdf.format(fechaInicioDateX);
                fechaInicioFormateadaY = sdf.format(fechaInicioDateY);
                // Formatear el nombre de la tabla para el día X
                nombreTablaX =
                        "`"+
                        laboratorio.toLowerCase() + "_" +
                        dia1.toLowerCase() + "_" +
                        docente.toLowerCase().replace(" ", "_") + "_" +
                        asignatura.toLowerCase().replace(" ", "_") + "_" +
                        horaInicioX.replace(":", "y") + "_" +
                        horaFinX.replace(":", "y") + "`"; 
                // Formatear el nombre de la tabla para el día Y
                nombreTablaY =
                        "`"+
                        laboratorio.toLowerCase() + "_" +
                        dia2.toLowerCase() + "_" +
                        docente.toLowerCase().replace(" ", "_") + "_" +
                        asignatura.toLowerCase().replace(" ", "_") + "_" +
                        horaInicioY.replace(":", "y") + "_" +
                        horaFinY.replace(":", "y") + "`";

                // Mostrar los nombres generados en un mensaje
                JOptionPane.showMessageDialog(null, "Nombres de las tablas generados: \n" + nombreTablaX + " y " + nombreTablaY);
            }

            // Crear las tablas en la base de datos
            try (Connection conn = Conexion_BD.getConexionBD()) {
                int numClases = Integer.parseInt(combonroClases.getSelectedItem().toString());
                String[] clasesConFechasX = new String[numClases];
                String[] clasesConFechasY = new String[numClases];

                // Llenar las fechas en las clases con el nuevo formato para el día X
                for (int i = 0; i < numClases; i++) {
                    clasesConFechasX[i] = fechaInicioFormateadaX;
                    // Aquí calculamos la siguiente fecha sumando 7 días
                    if (i > 0) {
                        Calendar calendar = Calendar.getInstance();
                        try {
                            calendar.setTime(sdf.parse(clasesConFechasX[i - 1]));
                            calendar.add(Calendar.DATE, 7); // Añadir 7 días
                            clasesConFechasX[i] = sdf.format(calendar.getTime());  // Usar dd-MM-yyyy
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }

                // Solo llenamos las fechas para el día Y si se trata de 32 clases
                if (nummeroClass == 32) {
                    for (int i = 0; i < numClases; i++) {
                        clasesConFechasY[i] = fechaInicioFormateadaY;
                        // Aquí calculamos la siguiente fecha sumando 7 días adicionales para el día Y
                        if (i > 0) {
                            Calendar calendar = Calendar.getInstance();
                            try {
                                calendar.setTime(sdf.parse(clasesConFechasY[i - 1]));
                                calendar.add(Calendar.DATE, 7); // Añadir 7 días
                                clasesConFechasY[i] = sdf.format(calendar.getTime());  // Usar dd-MM-yyyy
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }

                // Crear las tablas con las fechas generadas
                if (nummeroClass == 16) {
                    GenerarTablaClases.crearTablaConFechas(conn, nombreTablaX, fechaInicioFormateadaX, numClases, 7, clasesConFechasX);
                    JOptionPane.showMessageDialog(null, "Tabla creada con éxito.");
                } else if (nummeroClass == 32) {
                    GenerarTablaClases.crearTablaConFechas(conn, nombreTablaX, fechaInicioFormateadaX, numClases, 7, clasesConFechasX);
                    GenerarTablaClases.crearTablaConFechas(conn, nombreTablaY, fechaInicioFormateadaY, numClases, 7, clasesConFechasY);
                    JOptionPane.showMessageDialog(null, "Tablas creadas con éxito.");
                }

            } catch (SQLException ex) {
                Logger.getLogger(GenerarTablaClases1.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error al crear las tablas: " + ex.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(GenerarTablaClases1.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage());
            }
            
            try {

            // Conexión a la base de datos
            Connection conn = Conexion_BD.getConexionBD();

            // Formatear horarios a formato TIME (hh:mm)
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String formattedHoraInicioX = timeFormat.format(timeFormat.parse(horaInicioX));
            String formattedHoraFinX = timeFormat.format(timeFormat.parse(horaFinX));
            String formattedHoraInicioY = horaInicioY.isEmpty() ? null : timeFormat.format(timeFormat.parse(horaInicioY));
            String formattedHoraFinY = horaFinY.isEmpty() ? null : timeFormat.format(timeFormat.parse(horaFinY));


            // Insertar datos en la tabla 'horario_laboratorio'
            String insertSQL = "INSERT INTO horario_laboratorio " +
                               "(id_laboratorio, id_asignatura, dia, horario_inicio, horario_fin, id_usuario, codigo_horario) " +
                               "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            // Preparar el statement
            try (var ps = conn.prepareStatement(insertSQL)) {
                
               int docente1 = horarioControlador.obtenerIdUsuarioPorNombreController(comboDocente.getSelectedItem().toString());
                int laboratorio1 = horarioControlador.obtenerIDLaboratorioPorNumeroController(comboLaboratorio.getSelectedItem().toString());
                int asignatura1 = horarioControlador.obtenerIdAsignaturaPorNombreController(comboAsignatura.getSelectedItem().toString());
                String nombre1 = validarTexto(nombreTablaX);
                
                // Insertar datos para Día X
                ps.setInt(1, laboratorio1);                  
                ps.setInt(2, asignatura1);                  
                ps.setString(3, dia1);                     
                ps.setString(4, formattedHoraInicioX);       
                ps.setString(5, formattedHoraFinX);          
                ps.setInt(6, docente1);
                ps.setString(7, nombre1);           
                ps.executeUpdate();
   
    
                // Si hay Día Y, insertar también
                if (nombreTablaY != null) {
                    String nombre2 = validarTexto(nombreTablaY);
                    ps.setInt(1, laboratorio1);                 
                    ps.setInt(2, asignatura1);                  
                    ps.setString(3, dia2);                       
                    ps.setString(4, formattedHoraInicioY);      
                    ps.setString(5, formattedHoraFinY);           
                    ps.setInt(6, docente1);
                    ps.setString(7, nombre2);           
                    ps.executeUpdate();
                }
                listarHorarios();
                JOptionPane.showMessageDialog(null, "Clase(s) creada(s) con éxito.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar los datos: " + ex.getMessage());
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage());
            ex.printStackTrace();
        }
        }
    });
    }

    public void cargarComboNroLab() {
        comboLaboratorio.removeAllItems();
        comboLaboratorio.addItem("");
        Set<String> labsAgregados = new HashSet<>();
        for (LaboratorioModelo lab : laboratorioControlador.enlistarLaboratorioController()) {
            if (labsAgregados.add(lab.getNumeroLab())) {
                comboLaboratorio.addItem(lab.getNumeroLab());
            }
        }
    }

    public void cargarComboDocente() {
        comboDocente.removeAllItems();
        comboDocente.addItem("");
        Set<String> profesoresAgregados = new HashSet<>();
        for (UsuarioModelo user : usuarioControlador.enlistarUsuarioDocenteController()) {
            if (profesoresAgregados.add(user.getNombreUsuario())) {
                comboDocente.addItem(user.getNombreUsuario());
            }
        }
    }

    public void cargarComboAsignatura() {
        comboAsignatura.removeAllItems();
        comboAsignatura.addItem("");
        Set<String> asignaturasAgregadas = new HashSet<>();
        for (HorarioLaboratorioModelo horaLab : horarioControlador.enlistarHorarioLaboratorioController()) {
            if (asignaturasAgregadas.add(horarioControlador.obtenerNombreAsignaturaPorIdController(horaLab.getIdAsignatura()))) {
                
                comboAsignatura.addItem(horarioControlador.obtenerNombreAsignaturaPorIdController(horaLab.getIdAsignatura()));
            }
        }
    }
    
    public void insertarHorarioLaboratorio(String dia, String hi, String hf, String nombre_tabla){
        int estado = 0;
        try{
            HorarioLaboratorioModelo horario = new HorarioLaboratorioModelo();
            horario.setIdUsuario(horarioControlador.obtenerIdUsuarioPorNombreController(comboDocente.getSelectedItem().toString()));
            horario.setIdLaboratorio(horarioControlador.obtenerIDLaboratorioPorNumeroController(comboLaboratorio.getSelectedItem().toString()));
            horario.setIdAsignatura(horarioControlador.obtenerIdAsignaturaPorNombreController(comboAsignatura.getSelectedItem().toString()));
            horario.setDia(dia);
            horario.setHorarioInicio(hi);
            horario.setHorarioFin(hf);
            horario.setCodigoHorario(nombre_tabla);
        }catch(Exception ex){
            System.err.println("ERROR: " + ex);
        }
    }
  

    private void mostrarMensaje(int estado, String mensajeExito, String mensajeError) {
        if (estado == 1) {
            JOptionPane.showMessageDialog(null, mensajeExito);
        } else {
            JOptionPane.showMessageDialog(null, mensajeError);
        }
    }


    public void listarHorarios() {
        modeloHorarios.setRowCount(0);
        listaHorarios = horarioControlador.enlistarHorarioLaboratorioController();

        for (HorarioLaboratorioModelo horario : listaHorarios) {
            modeloHorarios.addRow(new Object[]{
                horarioControlador.obtenerNumeroLabPorIdController(horario.getIdLaboratorio()),
                horarioControlador.obtenerNombreAsignaturaPorIdController(horario.getIdAsignatura()),
                horarioControlador.obtenerNombreUsuarioPorIdController(horario.getIdUsuario()),
                horario.getDia(),
                horario.getHorarioInicio(),
                horario.getHorarioFin(),
                horario.getCodigoHorario()
            });
        }
    }

    private void limpiarCampos() {
        txtBuscarLaboratorio.setText("");
    }

    private void llenarCamposDesdeTabla() {
        int filaSeleccionada = tablaHorarios.getSelectedRow();
        if (filaSeleccionada != -1) {
            txtBuscarLaboratorio.setText(tablaHorarios.getValueAt(filaSeleccionada, 1).toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCrearClase){
            
        }
    }    
    
    private void activarCamposX() {
        comboDiaX.setEnabled(true);
        calendarInicioX.setEnabled(true);
        txtHoraInicioX.setEnabled(true);
        txtHoraFinX.setEnabled(true);
        comboDiaY.setEnabled(false);
        calendarInicioY.setEnabled(false);
        txtHoraInicioY.setEnabled(false);
        txtHoraFinY.setEnabled(false);
    }

    // Función para activar campos para 32 clases
    private void activarCamposXY() {
        comboDiaX.setEnabled(true);
        calendarInicioX.setEnabled(true);
        txtHoraInicioX.setEnabled(true);
        txtHoraFinX.setEnabled(true);
        comboDiaY.setEnabled(true);
        calendarInicioY.setEnabled(true);
        txtHoraInicioY.setEnabled(true);
        txtHoraFinY.setEnabled(true);
    }
    
    public void sincronizarVentanas(VentanaRight1 ventanaRight1,VentanaRight2 ventanaRight2, VentanaRight3 ventanaRight3) {
        this.ventanaRight1 = ventanaRight1;
        this.ventanaRight2 = ventanaRight2;
        this.ventanaRight3 = ventanaRight3;
    }
    
    

    private String validarTexto(String texto) {
        // Normalizar el texto y eliminar los acentos
        String textoSinTildes = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "") // Elimina caracteres diacríticos (tildes)
                .replace("ñ", "n")        // Reemplaza 'ñ' por 'n'
                .replace("Ñ", "N");       // Reemplaza 'Ñ' por 'N'

        // Eliminar caracteres no permitidos que no sean letras, números o '_'
        textoSinTildes = textoSinTildes.replaceAll("[^a-zA-Z0-9_]", "");

        // Retorna siempre la palabra transformada sin caracteres especiales
        return textoSinTildes;
    }

    private void Configuracion() {
        AsignaturaController asignaturaController = new AsignaturaController();
        LaboratorioController laboratorioController = new LaboratorioController(); 
        
        VentanaInternalFrame vtn = new VentanaInternalFrame(asignaturaController,laboratorioController);
        vtn.setVisible(true);
    }

    public void Filtrar(String buscar) {
        listaHorarios = horarioControlador.buscarResgistroHorarioLaboratorioController(buscar); 
        modeloHorarios.setRowCount(0); // Limpia la tabla

        // Itera sobre los registros y los agrega a la tabla
        for (HorarioLaboratorioModelo obj : listaHorarios) {
            Object[] fila = {        
                obj.getNumeroLaboratorio(),  
                obj.getAsignaturaNombre(),   
                obj.getNombreUsuario(),      
                obj.getDia(),              
                obj.getHorarioInicio(),    
                obj.getHorarioFin(),         
                obj.getCodigoHorario()      
            };
            modeloHorarios.addRow(fila); 
        }
    }

    

    
}