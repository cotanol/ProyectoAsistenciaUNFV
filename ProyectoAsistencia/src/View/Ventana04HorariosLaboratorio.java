package View;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Ventana04HorariosLaboratorio extends JFrame {
    private void inicializarComponentes() {
 // Panel izquierdo
        panelLeft = new JPanel(null);
        panelLeft.setBounds(0, 0, 300, 900); // Cambiar dimensiones
        panelLeft.setBackground(COLOR_BASE_BOTONES);
    
    // Panel logo
        panelLogo = new JPanel(null);
        panelLogo.setBounds(0, 0, 300, 150);
        panelLogo.setBackground(COLOR_HOVER_SELECCIONADO1);
   // Logo
        lbImagenLogo = new JLabel();
        ImageIcon logoIcon = new ImageIcon("Images/logo_villarreal.png");
        Image logoImg = logoIcon.getImage().getScaledInstance(250, 110, Image.SCALE_DEFAULT);
        lbImagenLogo.setIcon(new ImageIcon(logoImg));
        lbImagenLogo.setBounds(20, 10, 260, 130);
        panelLogo.add(lbImagenLogo);
    
    // Panel usuario
        panelUser = new JPanel(null);
        panelUser.setBounds(0, 150, 300, 290);
        panelUser.setOpaque(false);
        panelLeft.add(panelUser);
    // Imagen usuario
        lbImagenUser = new JLabel();
        ImageIcon userIcon = new ImageIcon("Images/user_penguin.png");
        Image userImg = userIcon.getImage().getScaledInstance(195, 195, Image.SCALE_DEFAULT);
        lbImagenUser.setIcon(new ImageIcon(userImg));
        lbImagenUser.setBounds(40, 3, 300, 205);
        panelUser.add(lbImagenUser);
    
    // Nombre usuario
        lbNameUser = new JLabel("USUARIO", SwingConstants.CENTER);
        lbNameUser.setForeground(Color.WHITE);
        lbNameUser.setBackground(COLOR_HOVER_SELECCIONADO1);
        lbNameUser.setOpaque(true);
        lbNameUser.setFont(new Font("Poppins", Font.BOLD, 22));
        lbNameUser.setBounds(0, 220, 300, 30);
    
        panelUser.add(lbImagenUser);
        panelUser.add(lbNameUser);
    
        // Botones menú
        btnRegistroUsuarios = crearBotonMenu("Registro de Usuarios", 0, 350);
        btnControlAsistencia = crearBotonMenu("Control de Asistencia", 0, 430);
        btnControlEquipos = crearBotonMenu("Control de Equipos", 0, 510);
        btnHorariosLaboratorio = crearBotonMenu("Horarios de Laboratorio", 0, 590);
        btnCerrarSesion = crearBotonMenu("Cerrar Sesión", 0, 900);
        
        btnHorariosLaboratorio.setBackground(COLOR_HOVER_SELECCIONADO1);
        
// Panel derecho
        panelDerecho = new JPanel(null);
        panelDerecho.setBounds(300, 0, 1300, 900);
        panelDerecho.setBackground(COLOR_FONDO_PANEL);
    
        // Componentes de búsqueda
        lbBuscar = new JLabel("BUSCAR HORARIOS");
        lbBuscar.setBounds(50, 30, 300, 40);
        lbBuscar.setFont(FUENTE_LABEL);
        
        txtBuscarLaboratorio = new JTextField();
        txtBuscarLaboratorio.setBounds(50, 80, 300, 40);
        txtBuscarLaboratorio.setFont(FUENTE_TEXTFIELD);
        txtBuscarLaboratorio.setBorder(BorderFactory.createTitledBorder("Nro. Laboratorio"));
        
        btnBuscar = new JButton("BUSCAR");
        btnBuscar.setBounds(370, 80, 200, 40);
        btnBuscar.setBackground(COLOR_BASE_BOTONES);
        btnBuscar.setForeground(COLOR_TEXTO_BLANCO);
        btnBuscar.setFont(FUENTE_BOTON);
        
        // Configuración de la tabla
        String[] columnNames = {"ASIGNATURA", "LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "SÁBADO"};
        modeloHorarios = new DefaultTableModel(columnNames, 0) {
           @Override
          public boolean isCellEditable(int row, int column) {
               return false;
          }
        };
        
        tablaHorarios = new JTable(modeloHorarios);
        tablaHorarios.setRowHeight(40);
        tablaHorarios.getTableHeader().setFont(FUENTE_LABEL);
        tablaHorarios.setFont(FUENTE_TEXTFIELD);
        
        scrollTablaHorarios = new JScrollPane(tablaHorarios);
        scrollTablaHorarios.setBounds(50, 150, 1200, 850);
        
        // Botones de acción
        btnAgregarHorario = crearBotonAccion("AGREGAR HORARIOS", 1280, 150);
        btnModificarHorario = crearBotonAccion("MODIFICAR HORARIOS", 1280, 220);
        btnEliminarHorario = crearBotonAccion("ELIMINAR HORARIOS", 1280, 290);
    }
    
    private void configurarPaneles() {
        // Configurar panel izquierdo
        panelLeft.add(panelLogo);
        panelLeft.add(panelUser);
        panelLeft.add(btnRegistroUsuarios);
        panelLeft.add(btnControlAsistencia);
        panelLeft.add(btnControlEquipos);
        panelLeft.add(btnHorariosLaboratorio);
        panelLeft.add(btnCerrarSesion);
        
        // Configurar panel derecho
        panelDerecho.add(lbBuscar);
        panelDerecho.add(txtBuscarLaboratorio);
        panelDerecho.add(btnBuscar);
        panelDerecho.add(scrollTablaHorarios);
        panelDerecho.add(btnAgregarHorario);
        panelDerecho.add(btnModificarHorario);
        panelDerecho.add(btnEliminarHorario);
        
        // Agregar paneles principales al frame
        add(panelLeft);
        add(panelDerecho);
        
        // Agregar datos de ejemplo
        Object[][] data = {
            
        };
        
        for (Object[] row : data) {
            modeloHorarios.addRow(row);
        }
    }
    
    private JButton crearBotonMenu(String texto, int x, int y) {
JButton button = new JButton(texto);
        button.setBounds(x, y, 300, 70); // Cambiar dimensiones
     button.setBackground(COLOR_BASE_BOTONES);
     button.setForeground(COLOR_TEXTO_BLANCO);
     button.setFont(FUENTE_MENU);
     button.setBorderPainted(false);
     button.setFocusPainted(false);
     button.setHorizontalAlignment(SwingConstants.LEFT);
     button.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        
        // Efecto hover
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            if (button.getBackground() != COLOR_HOVER_SELECCIONADO1) {
                button.setBackground(COLOR_HOVER_SELECCIONADO2);
            }
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            if (button.getBackground() != COLOR_HOVER_SELECCIONADO1) {
                button.setBackground(COLOR_BASE_BOTONES);
            }
        }
    });
    
    return button;
}
    
    private JButton crearBotonAccion(String texto, int x, int y) {
JButton button = new JButton(texto);
     button.setBounds(x, y, 200, 70); // Dimensiones más similares
     button.setBackground(COLOR_BASE_BOTONES);
     button.setForeground(COLOR_TEXTO_BLANCO);
     button.setFont(FUENTE_BOTON);
     button.setBorderPainted(false);
     button.setFocusPainted(false);
        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setBackground(COLOR_HOVER_SELECCIONADO2);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(COLOR_BASE_BOTONES);
        }
    });
    
    return button;
}
 // Colores
    private static final Color COLOR_BASE_BOTONES = new Color(233, 113, 50); // Cambiar al naranja de Ventana01
    private static final Color COLOR_HOVER_SELECCIONADO1 = new Color(255, 152, 0); // Naranja original
    private static final Color COLOR_HOVER_SELECCIONADO2 = new Color(255, 198, 66);
    private static final Color COLOR_TEXTO_BLANCO = Color.WHITE;
    private static final Color COLOR_TEXTO_NEGRO = Color.BLACK;
    private static final Color COLOR_FONDO_PANEL = new Color(238, 238, 238);
    private static final Color COLOR_NARANJA_UNFV = new Color(233, 113, 50); // Ajustar al color de Ventana01

// Fuentes (ajustadas a las de Ventana01)
    private static final Font FUENTE_TITULO = new Font("Poppins", Font.BOLD, 50);
    private static final Font FUENTE_SUBTITULO = new Font("Poppins", Font.BOLD, 30);
    private static final Font FUENTE_LABEL = new Font("Poppins", Font.BOLD, 20);
    private static final Font FUENTE_TEXTFIELD = new Font("Poppins", Font.PLAIN, 18);
    private static final Font FUENTE_BOTON = new Font("Poppins", Font.BOLD, 20);
    private static final Font FUENTE_MENU = new Font("Poppins", Font.PLAIN, 22);
    
    // Bordes
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Border border1 = BorderFactory.createLineBorder(COLOR_HOVER_SELECCIONADO1, 2);
    
    private JPanel panelLeft;
    private JPanel panelDerecho;
    private JPanel panelLogo;
    private JPanel panelUser;
    private JButton btnRegistroUsuarios, btnControlAsistencia, btnControlEquipos, btnHorariosLaboratorio, btnCerrarSesion;
    private JLabel lbImagenUser, lbImagenLogo, lbNameUser;
    private JPanel panelRight1, panelRight2, panelRight3, panelRight4;
    private JLabel lbBuscar;
    private JTextField txtBuscarLaboratorio;
    private JButton btnBuscar;
    private JTable tablaHorarios;
    private JScrollPane scrollTablaHorarios;
    private DefaultTableModel modeloHorarios;
    private JButton btnAgregarHorario;
    private JButton btnModificarHorario;
    private JButton btnEliminarHorario;

    // Clase interna para el diálogo de agregar/modificar horarios
    private class DialogoHorario extends JDialog {
        private JTextField txtCurso;
        private JTextField[] txtHorarios;
        private JButton btnGuardar, btnCancelar;
        private boolean guardadoExitoso = false;
        private String[] datosHorario;

        public DialogoHorario(Frame parent, String[] datosExistentes) {
            super(parent, "Gestionar Horario", true);
            setSize(600, 420);
            setLocationRelativeTo(parent);
            setLayout(null);
            
            JLabel lblCurso = new JLabel("Nombre del Curso:");
            lblCurso.setBounds(20, 20, 200, 30);
            lblCurso.setFont(FUENTE_LABEL);
            
            txtCurso = new JTextField();
            txtCurso.setBounds(20, 50, 540, 30);
            txtCurso.setFont(FUENTE_TEXTFIELD);
            
            String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sabado"};
            txtHorarios = new JTextField[6];
            
            for(int i = 0; i < diasSemana.length; i++) {
                JLabel lblDia = new JLabel(diasSemana[i] + ":");
                lblDia.setBounds(20, 100 + (i * 40), 100, 30);
                lblDia.setFont(FUENTE_LABEL);
                add(lblDia);
                
                txtHorarios[i] = new JTextField();
                txtHorarios[i].setBounds(120, 100 + (i * 40), 440, 30);
                txtHorarios[i].setFont(FUENTE_TEXTFIELD);
                
                // Filtro para solo permitir números y caracteres especiales
                ((PlainDocument) txtHorarios[i].getDocument()).setDocumentFilter(new DocumentFilter() {
                    @Override
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) 
                            throws BadLocationException {
                        if (text.matches("[0-9: -]*")) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }
                });
                add(txtHorarios[i]);
            }
            
            // Si hay datos existentes, prellenar los campos
            if (datosExistentes != null) {
                txtCurso.setText(datosExistentes[0]);
                for (int i = 0; i < txtHorarios.length; i++) {
                    if (i < datosExistentes.length - 1){
                    txtHorarios[i].setText(datosExistentes[i + 1]);                        
                    }
                }
            }
            
            btnGuardar = new JButton("Guardar");
            btnGuardar.setBounds(140, 350, 150, 30);
            btnGuardar.setBackground(COLOR_BASE_BOTONES);
            btnGuardar.setForeground(COLOR_TEXTO_BLANCO);
            btnGuardar.setFont(FUENTE_BOTON);
            
            btnCancelar = new JButton("Cancelar");
            btnCancelar.setBounds(300, 350, 150, 30);
            btnCancelar.setBackground(COLOR_BASE_BOTONES);
            btnCancelar.setForeground(COLOR_TEXTO_BLANCO);
            btnCancelar.setFont(FUENTE_BOTON);
            
            add(lblCurso);
            add(txtCurso);
            add(btnGuardar);
            add(btnCancelar);
            
            btnGuardar.addActionListener(e -> guardarHorario());
            btnCancelar.addActionListener(e -> dispose());
        }
        
        private void guardarHorario() {
            if(txtCurso.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese el nombre del curso", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            datosHorario = new String[7];
            datosHorario[0] = txtCurso.getText().trim();
            
            for(int i = 0; i < txtHorarios.length; i++) {
                datosHorario[i + 1] = txtHorarios[i].getText().trim();
            }
            
            guardadoExitoso = true;
            dispose();
        }
        
        public boolean isGuardadoExitoso() {
            return guardadoExitoso;
        }
        
        public String[] getDatosHorario() {
            return datosHorario;
        }
    }
    
    public Ventana04HorariosLaboratorio() {
    setTitle("GESTOR DE LABORATORIO DE LA UNIVERSIDAD NACIONAL FEDERICO VILLARREAL");
    setSize(1600, 900); // Cambiar tamaño
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    setLayout(null);
    
    inicializarComponentes();
    configurarPaneles();
    configurarEventos();
    }
    
    // [El resto de los métodos existentes permanecen igual hasta configurarEventos()]
    
    private void configurarEventos() {
        
        btnBuscar.addActionListener(e -> {
            String busqueda = txtBuscarLaboratorio.getText().trim().toLowerCase();
            if(!busqueda.isEmpty()) {
                buscarEnTabla(busqueda);
            } else {
                // Restaurar tabla original si la búsqueda está vacía
                tablaHorarios.setModel(modeloHorarios);
            }
        });
        
        btnAgregarHorario.addActionListener(e -> {
            DialogoHorario dialogo = new DialogoHorario(this, null);
            dialogo.setVisible(true);
            
            if(dialogo.isGuardadoExitoso()) {
                String[] datos = dialogo.getDatosHorario();
                modeloHorarios.addRow(datos);
            }
        });
        
        btnModificarHorario.addActionListener(e -> {
            int filaSeleccionada = tablaHorarios.getSelectedRow();
            if(filaSeleccionada >= 0) {
                String[] datosExistentes = new String[6];
                for(int i = 0; i < tablaHorarios.getColumnCount(); i++) {
                    datosExistentes[i] = (String)tablaHorarios.getValueAt(filaSeleccionada, i);
                }
                
                DialogoHorario dialogo = new DialogoHorario(this, datosExistentes);
                dialogo.setVisible(true);
                
                if(dialogo.isGuardadoExitoso()) {
                    String[] datos = dialogo.getDatosHorario();
                    for(int i = 0; i < datos.length; i++) {
                        modeloHorarios.setValueAt(datos[i], filaSeleccionada, i);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Por favor seleccione un horario para modificar", 
                    "Selección requerida", 
                    JOptionPane.WARNING_MESSAGE);
            }
        });
        
        btnEliminarHorario.addActionListener(e -> {
            int filaSeleccionada = tablaHorarios.getSelectedRow();
            if(filaSeleccionada >= 0) {
                int confirmacion = JOptionPane.showConfirmDialog(this, 
                    "¿Está seguro de eliminar este horario?", 
                    "Confirmar eliminación", 
                    JOptionPane.YES_NO_OPTION);
                
                if(confirmacion == JOptionPane.YES_OPTION) {
                    modeloHorarios.removeRow(filaSeleccionada);
                }
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Por favor seleccione un horario para eliminar", 
                    "Selección requerida", 
                    JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    
    private void buscarEnTabla(String busqueda) {
        
        
        
    // Obtener nombres de columnas del modelo actual
    Object[] columnNames = new Object[modeloHorarios.getColumnCount()];
    for (int i = 0; i < modeloHorarios.getColumnCount(); i++) {
        columnNames[i] = modeloHorarios.getColumnName(i);
    }
    
    DefaultTableModel modeloTemporal = new DefaultTableModel(columnNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    for(int i = 0; i < modeloHorarios.getRowCount(); i++) {
        boolean encontrado = false;
        // Buscar en todas las columnas
        for(int j = 0; j < modeloHorarios.getColumnCount(); j++) {
            String valor = ((String)modeloHorarios.getValueAt(i, j)).toLowerCase();
            if(valor.contains(busqueda)) {
                encontrado = true;
                break;
            }
        }
    if(encontrado) {
            Object[] fila = new Object[modeloHorarios.getColumnCount()];
            for(int j = 0; j < modeloHorarios.getColumnCount(); j++) {
                fila[j] = modeloHorarios.getValueAt(i, j);
            }
            modeloTemporal.addRow(fila);
        }
    }
    tablaHorarios.setModel(modeloTemporal);
}
    
    // [El resto de los métodos existentes permanecen igual]
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Ventana04HorariosLaboratorio().setVisible(true);
        });
    }
}