package creandotablasjava;
import DAO.GenerarTablaClases;
import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Util.Conexion_BD;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerarTablaClases1 extends JFrame {

    private JComboBox<String> comboDocente, comboLaboratorio, comboClases, comboDiaX, comboDiaY;
    private JTextField txtAsignatura, txtHoraInicioX, txtHoraFinX, txtHoraInicioY, txtHoraFinY;
    private JDateChooser calendarInicioX, calendarInicioY;
    private JButton btnCrearClase; // Botón para crear la clase

    public GenerarTablaClases1() {
        // Configuración del JFrame
        setTitle("Generación de Tablas de Clases");
        setLayout(null); // Usamos layout null para ubicar componentes a mano
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Docente ComboBox
        String[] docentes = {"","Juan", "Ana", "Carlos", "Maria Osea","CAMOTE MORADO","TIO XIANCAS"};
        comboDocente = new JComboBox<>(docentes);
        comboDocente.setBounds(150, 30, 200, 30); // Posición y tamaño
        add(comboDocente);

        // Asignatura TextField
        txtAsignatura = new JTextField();
        txtAsignatura.setBounds(150, 70, 200, 30); // Posición y tamaño
        add(txtAsignatura);

        // Laboratorio ComboBox
        String[] laboratorios = {"","Laboratorio 1", "Laboratorio 2", "Laboratorio 3", "Laboratorio 4", "Laboratorio 5", "Laboratorio 6"};
        comboLaboratorio = new JComboBox<>(laboratorios);
        comboLaboratorio.setBounds(150, 110, 200, 30); // Posición y tamaño
        add(comboLaboratorio);

        // Nro. Clases ComboBox
        String[] clases = {"","16", "32"};
        comboClases = new JComboBox<>(clases);
        comboClases.setBounds(150, 150, 200, 30); // Posición y tamaño
        add(comboClases);

        String[] dias = {"","Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};

        comboDiaX = new JComboBox<>(dias);
        comboDiaX.setBounds(150, 190, 200, 30);
        comboDiaX.setEnabled(false);
        add(comboDiaX);

        comboDiaY = new JComboBox<>(dias);
        comboDiaY.setBounds(150, 230, 200, 30);
        comboDiaY.setEnabled(false);
        add(comboDiaY);

        // Hora Inicio X
        txtHoraInicioX = new JTextField();
        txtHoraInicioX.setBounds(150, 270, 200, 30);
        txtHoraInicioX.setEnabled(false);
        add(txtHoraInicioX);

        // Hora Fin X
        txtHoraFinX = new JTextField();
        txtHoraFinX.setBounds(150, 310, 200, 30);
        txtHoraFinX.setEnabled(false);
        add(txtHoraFinX);

        // Hora Inicio Y
        txtHoraInicioY = new JTextField();
        txtHoraInicioY.setBounds(150, 350, 200, 30);
        txtHoraInicioY.setEnabled(false);
        add(txtHoraInicioY);

        // Hora Fin Y
        txtHoraFinY = new JTextField();
        txtHoraFinY.setBounds(150, 390, 200, 30);
        txtHoraFinY.setEnabled(false);
        add(txtHoraFinY);

        // Calendario Fecha Inicio X (usando JDateChooser)
        calendarInicioX = new JDateChooser();
        calendarInicioX.setBounds(150, 430, 200, 30); // Posición y tamaño
        calendarInicioX.setEnabled(false);  // Inicialmente deshabilitado
        add(calendarInicioX);

        // Calendario Fecha Inicio Y (usando JDateChooser)
        calendarInicioY = new JDateChooser();
        calendarInicioY.setBounds(150, 590, 200, 30); // Posición y tamaño
        calendarInicioY.setEnabled(false);  // Inicialmente deshabilitado
        add(calendarInicioY);

        // Botón Crear Clase
        btnCrearClase = new JButton("Crear Clase");
        btnCrearClase.setBounds(150, 630, 200, 30); // Posición y tamaño
        add(btnCrearClase);

        // Etiquetas
        JLabel lblDocente = new JLabel("Docente");
        lblDocente.setBounds(30, 30, 100, 30);
        add(lblDocente);

        JLabel lblAsignatura = new JLabel("Asignatura");
        lblAsignatura.setBounds(30, 70, 100, 30);
        add(lblAsignatura);

        JLabel lblLaboratorio = new JLabel("Nro. LAB");
        lblLaboratorio.setBounds(30, 110, 100, 30);
        add(lblLaboratorio);

        JLabel lblNroClases = new JLabel("Nro. Clases");
        lblNroClases.setBounds(30, 150, 100, 30);
        add(lblNroClases);

        JLabel lblDiaX = new JLabel("Dia X");
        lblDiaX.setBounds(30, 190, 100, 30);
        add(lblDiaX);

        JLabel lblDiaY = new JLabel("Dia Y");
        lblDiaY.setBounds(30, 230, 100, 30);
        add(lblDiaY);

        JLabel lblHoraInicioX = new JLabel("Hora Inicio - Dia X");
        lblHoraInicioX.setBounds(30, 270, 100, 30);
        add(lblHoraInicioX);

        JLabel lblHoraFinX = new JLabel("Hora Fin - Dia X");
        lblHoraFinX.setBounds(30, 310, 100, 30);
        add(lblHoraFinX);

        JLabel lblHoraInicioY = new JLabel("Hora Inicio - Dia Y");
        lblHoraInicioY.setBounds(30, 350, 100, 30);
        add(lblHoraInicioY);

        JLabel lblHoraFinY = new JLabel("Hora Fin - Dia Y");
        lblHoraFinY.setBounds(30, 390, 100, 30);
        add(lblHoraFinY);

        JLabel lblFechaInicioX = new JLabel("Fecha de Inicio (X)");
        lblFechaInicioX.setBounds(30, 430, 100, 30);
        add(lblFechaInicioX);

        JLabel lblFechaInicioY = new JLabel("Fecha de Inicio (Y)");
        lblFechaInicioY.setBounds(30, 590, 100, 30);
        add(lblFechaInicioY);

        // Hacer visible el JFrame
        setSize(400, 800);
        setVisible(true);

        // Añadir funcionalidad para el combo de clases
        comboClases.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sel = (String)comboClases.getSelectedItem();
                if (sel == null || sel.isEmpty()) return; 
                int numClases = Integer.parseInt(sel);
                if (numClases == 16) {
                    activarCamposX();
                } else if (numClases == 32) {
                    activarCamposXY();
                }
            }
        });

        // Añadir funcionalidad al botón Crear Clase
        btnCrearClase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dia1 = (String)comboDiaX.getSelectedItem();
                String dia2 = (String)comboDiaY.getSelectedItem();
                String docente = comboDocente.getSelectedItem().toString();
                String asignatura = txtAsignatura.getText().trim();
                String horaInicioX = txtHoraInicioX.getText().trim();
                String horaFinX = txtHoraFinX.getText().trim();
                String horaInicioY = txtHoraInicioY.getText().trim();
                String horaFinY = txtHoraFinY.getText().trim();
                String laboratorio = comboLaboratorio.getSelectedItem().toString().trim();

                // Verificación de selección del combo Clases
                String sel = (String)comboClases.getSelectedItem();
                if (sel == null || sel.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Seleccione el número de clases.");
                    return;
                }

                int nummeroClass = Integer.parseInt(sel);

                // Obtener las fechas de los calendarios
                Date fechaInicioDateX = calendarInicioX.getDate();
                Date fechaInicioDateY = calendarInicioY.getDate();

                // Validaciones
                if (nummeroClass == 16) {
                    if (docente.isEmpty() || asignatura.isEmpty() || horaInicioX.isEmpty() || horaFinX.isEmpty() || fechaInicioDateX == null || dia1.isEmpty() || dia1.equals("")) {
                        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos para 16 clases.");
                        return;
                    }
                } else if (nummeroClass == 32) {
                    if (docente.isEmpty() || asignatura.isEmpty() || horaInicioX.isEmpty() || horaFinX.isEmpty() ||
                        horaInicioY.isEmpty() || horaFinY.isEmpty() || fechaInicioDateX == null || fechaInicioDateY == null ||
                        dia1.isEmpty() || dia2.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos para 32 clases.");
                        return;
                    }
                }

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
                String fechaInicioFormateadaX = sdf.format(fechaInicioDateX);
                String fechaInicioFormateadaY = null;
                if (nummeroClass == 32) {
                    fechaInicioFormateadaY = sdf.format(fechaInicioDateY);
                }

                String nombreTablaX = null;
                String nombreTablaY = null;

                if (nummeroClass == 16) {
                fechaInicioFormateadaX = sdf.format(fechaInicioDateX);
                // Formatear el nombre de la tabla para el día X
                nombreTablaX =
                        "`" +
                        laboratorio.toLowerCase().replace(" ", "") + "" +
                        dia1.toLowerCase() + "_" +
                        docente.toLowerCase().replace(" ", "") + "" +
                        asignatura.toLowerCase().replace(" ", "") + "" +
                        horaInicioX.replace(":", "-") + "_" +
                        horaFinX.replace(":", "-")
                        + "`";
                JOptionPane.showMessageDialog(null, "Nombre de la tabla generado: \n" + nombreTablaX);
            } else if (nummeroClass == 32) {
                fechaInicioFormateadaX = sdf.format(fechaInicioDateX);
                fechaInicioFormateadaY = sdf.format(fechaInicioDateY);
                // Formatear el nombre de la tabla para el día X
                nombreTablaX =
                        "`" +
                        laboratorio.toLowerCase().replace(" ", "") + "" +
                        dia1.toLowerCase() + "_" +
                        docente.toLowerCase().replace(" ", "") + "" +
                        asignatura.toLowerCase().replace(" ", "") + "" +
                        horaInicioX.replace(":", "-") + "_" +
                        horaFinX.replace(":", "-")
                        + "`";
                // Formatear el nombre de la tabla para el día Y
                nombreTablaY = 
                        "`" +
                        laboratorio.toLowerCase() + "_" +
                        dia2.toLowerCase() + "_" +
                        docente.toLowerCase().replace(" ", "") + "" +
                        asignatura.toLowerCase().replace(" ", "") + "" +
                        horaInicioY.replace(":", "-") + "_" +
                        horaFinY.replace(":", "-")
                        + "`";

                // Mostrar los nombres generados en un mensaje
                JOptionPane.showMessageDialog(null, "Nombres de las tablas generados: \n" + nombreTablaX + " y " + nombreTablaY);
            }

                // Crear las tablas en la base de datos
                try (Connection conn = Conexion_BD.getConexionBD()) {
                    int numClases = Integer.parseInt(comboClases.getSelectedItem().toString());
                    String[] clasesConFechasX = new String[numClases];
                    String[] clasesConFechasY = null;

                    // Llenar las fechas en las clases con el nuevo formato para el día X
                    for (int i = 0; i < numClases; i++) {
                        clasesConFechasX[i] = fechaInicioFormateadaX;
                        if (i > 0) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(sdf.parse(clasesConFechasX[i - 1]));
                            calendar.add(Calendar.DATE, 7); // Añadir 7 días
                            clasesConFechasX[i] = sdf.format(calendar.getTime());
                        }
                    }

                    // Solo llenamos las fechas para el día Y si se trata de 32 clases
                    if (nummeroClass == 32) {
                        clasesConFechasY = new String[numClases];
                        for (int i = 0; i < numClases; i++) {
                            clasesConFechasY[i] = fechaInicioFormateadaY;
                            if (i > 0) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(sdf.parse(clasesConFechasY[i - 1]));
                                calendar.add(Calendar.DATE, 7); // Añadir 7 días
                                clasesConFechasY[i] = sdf.format(calendar.getTime());
                            }
                        }
                    }

                    if (nummeroClass == 16) {
                        GenerarTablaClases.crearTablaConFechas(conn, nombreTablaX, fechaInicioFormateadaX, numClases, 7, clasesConFechasX);
                        JOptionPane.showMessageDialog(null, "Tabla creada con éxito (16 clases).");
                    } else if (nummeroClass == 32) {
                        GenerarTablaClases.crearTablaConFechas(conn, nombreTablaX, fechaInicioFormateadaX, numClases, 7, clasesConFechasX);
                        GenerarTablaClases.crearTablaConFechas(conn, nombreTablaY, fechaInicioFormateadaY, numClases, 7, clasesConFechasY);
                        JOptionPane.showMessageDialog(null, "Tablas creadas con éxito (32 clases).");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(GenerarTablaClases1.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error al crear las tablas: " + ex.getMessage());
                } catch (Exception ex) {
                    Logger.getLogger(GenerarTablaClases1.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage());
                }

                // Insertar datos en la tabla repositorio_clases
                try {

                    Connection conn = Conexion_BD.getConexionBD();

                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    String formattedHoraInicioX = timeFormat.format(timeFormat.parse(horaInicioX));
                    String formattedHoraFinX = timeFormat.format(timeFormat.parse(horaFinX));
                    String formattedHoraInicioY = (horaInicioY.isEmpty() ? null : timeFormat.format(timeFormat.parse(horaInicioY)));
                    String formattedHoraFinY = (horaFinY.isEmpty() ? null : timeFormat.format(timeFormat.parse(horaFinY)));

                    String insertSQL = "INSERT INTO repositorio_clases " +
                               "(laboratorio, asignatura, docente, dia, horario_inicio, horario_fin, codigo_clase) " +
                               "VALUES (?, ?, ?, ?, ?, ?, ?)";

                    try (var ps = conn.prepareStatement(insertSQL)) {
                        // Insertar datos para Día X
                        ps.setString(1, laboratorio);            
                        ps.setString(2, asignatura);            
                        ps.setString(3, docente);              
                        ps.setString(4, dia1);                
                        ps.setString(5, formattedHoraInicioX); 
                        ps.setString(6, formattedHoraFinX);    
                        ps.setString(7, nombreTablaX);         
                        ps.executeUpdate();

                        // Si hay Día Y (32 clases), insertar también para Día Y
                        if (nummeroClass == 32 && nombreTablaY != null) {
                            ps.setString(1, laboratorio);             
                            ps.setString(2, asignatura);             
                            ps.setString(3, docente);               
                            ps.setString(4, dia2); // usar dia2 para el segundo día
                            ps.setString(5, formattedHoraInicioY);  
                            ps.setString(6, formattedHoraFinY);     
                            ps.setString(7, nombreTablaY);          
                            ps.executeUpdate();
                        }

                        JOptionPane.showMessageDialog(null, "Clase(s) creada(s) y datos guardados con éxito.");
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

    // Función para activar campos para 16 clases
    private void activarCamposX() {
        comboDiaX.setEnabled(true);
        calendarInicioX.setEnabled(true);
        txtHoraInicioX.setEnabled(true);
        txtHoraFinX.setEnabled(true);

        // Deshabilitar campos día Y por si se cambió de 32 a 16
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

    public static void main(String[] args) {
        new GenerarTablaClases1();
    }
}
