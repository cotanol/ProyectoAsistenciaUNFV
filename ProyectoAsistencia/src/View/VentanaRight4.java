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
import Model.UsuarioModelo;
import Controller.UsuarioController;
import Model.AsignaturaModelo;
import Model.LaboratorioModelo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class VentanaRight4 extends JPanel implements ActionListener {
    // Controlador
    private HorarioLaboratorioController horarioControlador;
    private UsuarioController usuarioControlador;
    private LaboratorioController laboratorioControlador;
    private AsignaturaController asignaturaControlador;

    // Componentes principales
    private JLabel lbRegistrosHorarios;
    private JLabel nombreSubPanel1_1, nombreSubPanel1_2;
    private JPanel subPanel1;
    private JLabel lbBuscarLaboratorio;
    private JTextField txtBuscarLaboratorio;
    private JButton btnExportarExcel, btnCrearClase, btnConfiguracion, btnEliminarHorario, btnLimpiarHorario;
    private JTable tablaHorarios;
    private DefaultTableModel modeloHorarios;
    
    private JLabel lbdocente, lbasignatura, lbnroclases, lbnrolab, lbdiax, lbdiay, lbfechainiciox, lbfechainicioy, lbhorainiciox, lbhorainicioy, lbhorafinx, lbhorafiny;
    public  JComboBox<String> comboDocente, comboLaboratorio, combonroClases, comboAsignatura, comboDiaX, comboDiaY;
    private JTextField  txtHoraInicioX, txtHoraFinX, txtHoraInicioY, txtHoraFinY;
    private JDateChooser calendarInicioX, calendarInicioY;
    
    private JTextField txtBuscar;
    private JLabel lbBuscar;
    
    private VentanaRight1 ventanaRight1;
    private VentanaRight2 ventanaRight2;
    private VentanaRight3 ventanaRight3;
    
    // Datos
    private ArrayList<HorarioLaboratorioModelo> listaHorarios;

    public VentanaRight4(HorarioLaboratorioController horarioControlador, UsuarioController usuarioControlador, LaboratorioController laboratorioControlador, AsignaturaController asignaturaControlador) {
        this.horarioControlador = horarioControlador;
        this.usuarioControlador = usuarioControlador;
        this.laboratorioControlador = laboratorioControlador;
        this.asignaturaControlador = asignaturaControlador;

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
        nombreSubPanel1_2 = ComponentFactory.crearEtiqueta("LISTA DE HORARIOS", 100, 490, 360, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        nombreSubPanel1_2.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        nombreSubPanel1_2.setOpaque(true);
        nombreSubPanel1_2.setHorizontalAlignment(SwingConstants.CENTER);
        add(nombreSubPanel1_2);
        
        btnEliminarHorario = ComponentFactory.crearBotonAccion("Eliminar", 1050, 490, 130, 50);
        add(btnEliminarHorario);
        
        btnLimpiarHorario = ComponentFactory.crearBotonAccion("Limpiar", 900, 490, 130, 50);
        add(btnLimpiarHorario);

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
        
        String[] dias = {"","Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        String[] clases = {"","16", "32"}; // Esto queda fijo

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
        calendarInicioX.setBounds(630, 50, 170, 30); 
        calendarInicioX.setFont(Constantes.FUENTE_LABEL);
        calendarInicioX.setBorder(Constantes.BORDER_HOVER);
        calendarInicioX.setEnabled(false);
        subPanel1.add(calendarInicioX);

        lbhorainiciox = ComponentFactory.crearEtiqueta("Hora Inicio - Dia X", 630, 100, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbhorainiciox);
        txtHoraInicioX = ComponentFactory.crearCampoTexto(630, 130, 170, 30, Constantes.BORDER_HOVER);
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
        calendarInicioY.setBounds(870, 50, 170, 30);
        calendarInicioY.setFont(Constantes.FUENTE_LABEL);
        calendarInicioY.setBorder(Constantes.BORDER_HOVER);
        calendarInicioY.setEnabled(false);
        subPanel1.add(calendarInicioY);

        lbhorainicioy= ComponentFactory.crearEtiqueta("Hora Inicio - Dia Y", 870, 100, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbhorainicioy);
        txtHoraInicioY = ComponentFactory.crearCampoTexto(870, 130, 170, 30, Constantes.BORDER_HOVER);
        txtHoraInicioY.setEnabled(false);
        subPanel1.add(txtHoraInicioY);
        
        lbhorafiny = ComponentFactory.crearEtiqueta("Hora Fin - Dia Y", 870, 180, 200, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbhorafiny);
        txtHoraFinY = ComponentFactory.crearCampoTexto(870, 210, 170, 30, Constantes.BORDER_HOVER);
        txtHoraFinY.setEnabled(false);
        subPanel1.add(txtHoraFinY);
        
        lbBuscar = ComponentFactory.crearEtiqueta("Buscar: ", 485, 500, 100, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_TEXTO_NEGRO);
        add(lbBuscar);
        txtBuscar = ComponentFactory.crearCampoTexto(570, 500, 300, 30, Constantes.BORDER_NEGRO);
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
        btnConfiguracion.addActionListener((e)->Configuracion());
        
        btnExportarExcel.addActionListener((e)->{
           horarioControlador.exportarUsuariosAExcel(txtBuscar.getText());
           JOptionPane.showMessageDialog(null, "Datos exportados a Excel correctamente 🐧!!", "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        });
        
        btnEliminarHorario.addActionListener(e -> {
            eliminarHorairoLaboratorio();
            ventanaRight2.cargarComboCodigoHorario();
        });
        
        btnLimpiarHorario.addActionListener(e -> {
            limpiarDatosClase();
        });
        
        btnEliminarHorario.addMouseListener(new EstiloHover.HoverAccionBoton(btnEliminarHorario));
        btnLimpiarHorario.addMouseListener(new EstiloHover.HoverAccionBoton(btnLimpiarHorario));
        btnCrearClase.addMouseListener(new EstiloHover.HoverAccionBoton(btnCrearClase));
        btnConfiguracion.addMouseListener(new EstiloHover.HoverAccionBoton(btnConfiguracion));
        btnExportarExcel.addMouseListener(new EstiloHover.HoverAccionBotonExcel(btnExportarExcel));
        
        
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

            int nummeroClass = 0;
            try {
                nummeroClass = Integer.parseInt(combonroClases.getSelectedItem().toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Seleccione un número de clases.");
                return;
            }

            // Validar campos obligatorios
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

            // Verificar fechas
            if (fechaInicioDateX == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha de inicio para el día X.");
                return;
            }
            if (nummeroClass == 32 && fechaInicioDateY == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha de inicio para el día Y.");
                return;
            }

            // Formatear fecha a 'dd-MM-yyyy'
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String fechaInicioFormateadaX = sdf.format(fechaInicioDateX);
            String fechaInicioFormateadaY = (fechaInicioDateY != null) ? sdf.format(fechaInicioDateY) : null;

            // Formatear horarios a formato TIME (hh:mm)
            try {
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                String formattedHoraInicioX = timeFormat.format(timeFormat.parse(horaInicioX));
                String formattedHoraFinX = timeFormat.format(timeFormat.parse(horaFinX));
                String formattedHoraInicioY = (horaInicioY.isEmpty()) ? null : timeFormat.format(timeFormat.parse(horaInicioY));
                String formattedHoraFinY = (horaFinY.isEmpty()) ? null : timeFormat.format(timeFormat.parse(horaFinY));

                // Insertar el horario para el Día X
                insertarHorarioLaboratorio(dia1, formattedHoraInicioX, formattedHoraFinX, generarCodigoHorario(laboratorio, dia1, docente, asignatura, horaInicioX, horaFinX), fechaInicioFormateadaX);
                
                ventanaRight2.cargarComboCodigoHorario();
                
                
                // Si hay Día Y (32 clases), insertar también el horario del Día Y
                if (nummeroClass == 32 && formattedHoraInicioY != null && formattedHoraFinY != null && fechaInicioFormateadaY != null) {
                    insertarHorarioLaboratorio(dia2, formattedHoraInicioY, formattedHoraFinY, generarCodigoHorario(laboratorio, dia2, docente, asignatura, horaInicioY, horaFinY), fechaInicioFormateadaY);
                    ventanaRight2.cargarComboCodigoHorario();
                    
                    
                }

                listarHorarios();
                JOptionPane.showMessageDialog(null, "Clase(s) creada(s) con éxito.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al procesar la hora: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    });
    }

    private String generarCodigoHorario(String laboratorio, String dia, String docente, String asignatura, String horaInicio, String horaFin) {
        // Generar un código único según tus necesidades. Aquí un ejemplo:
        String cod = laboratorio.toLowerCase().replace(" ", "_") + "_" +
                     dia.toLowerCase() + "_" +
                     docente.toLowerCase().replace(" ", "_") + "_" +
                     asignatura.toLowerCase().replace(" ", "_") + "_" +
                     horaInicio.replace(":", "y") + "_" +
                     horaFin.replace(":", "y");
        return validarTexto(cod);
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
        for (AsignaturaModelo asig : asignaturaControlador.enlistarAsignaturaController()) {
            String nombreAsig = asig.getNombre();
            if (asignaturasAgregadas.add(nombreAsig)) {
                comboAsignatura.addItem(nombreAsig);
            }
        }
    }
    
    public void cargarCombosOtrasVentana() {
        ventanaRight3.cargarComboNroLab();
        ventanaRight2.cargarComboCodigoHorario();
    }
    
    public void insertarHorarioLaboratorio(String dia, String hi, String hf, String nombre_tabla, String fechaIn){
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
            horario.setFechaInicio(fechaIn);
            
            estado = horarioControlador.insertarHorarioLaboratorioController(horario);
            
            if (estado == 1) {
                ventanaRight2.cargarComboCodigoHorario();
            }
            
            mostrarMensaje(estado, "Insertado Correctamente", "Ocurrió un error");
        }catch(Exception ex){
            System.err.println("ERROR: " + ex);
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        
    }
    
    public void eliminarHorairoLaboratorio() {
        int estado = 0;
        try {
            int selectedRow = tablaHorarios.getSelectedRow();
                if (selectedRow == -1) {
                    // Si no hay fila seleccionada, mostrar mensaje
                    Util.WindowFactory.errorWindowCRUD("Registro No Eliminado","ELIMINADO");
                } else {
                    HorarioLaboratorioModelo hl = new HorarioLaboratorioModelo();
                    String codigoHorario = (String) tablaHorarios.getValueAt(tablaHorarios.getSelectedRow(), 6);
                    hl.setCodigoHorario(codigoHorario);
                    estado = horarioControlador.eliminarHorarioLaboratorioController(hl);

                    if(estado == 1){
                        Util.WindowFactory.confirmationWindowCRUD("Registro Eliminado","ELIMINADO");
                    }else{
                        Util.WindowFactory.errorWindowCRUD("Registro No Eliminado","ELIMINADO");
                    }
                    listarHorarios();
                }
   
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
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


    
    public void limpiarDatosClase() {
        // Limpiar datechoser
        calendarInicioX.setDate(null);
        calendarInicioY.setDate(null);
        // Limpiar los JTextField
        txtHoraInicioX.setText("");
        txtHoraFinX.setText("");
        txtHoraInicioY.setText("");
        txtHoraFinY.setText("");
        // Limpiar combobox
        /*comboDocente.setSelectedIndex(0); 
        comboLaboratorio.setSelectedIndex(0);
        combonroClases.setSelectedIndex(0);  */
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Acciones de botones si las hubiese
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

        return textoSinTildes;
    }

    private void Configuracion() {
        AsignaturaController asignaturaController = new AsignaturaController();
        LaboratorioController laboratorioController = new LaboratorioController(); 
        VentanaInternalFrame vtn = new VentanaInternalFrame(asignaturaController,laboratorioController, this);
        vtn.setVisible(true);
    }

    public void Filtrar(String buscar) {
        listaHorarios = horarioControlador.buscarResgistroHorarioLaboratorioController(buscar); 
        modeloHorarios.setRowCount(0); // Limpia la tabla

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
