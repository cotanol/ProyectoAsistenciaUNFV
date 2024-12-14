package View;
import Controller.AlumnoController;
import Controller.AsignaturaController;
import Controller.HorariosAlumnoController;
import Controller.LaboratorioController;
import Model.AlumnoModelo;
import Model.AsignaturaModelo;
import Model.HorariosAlumnoModelo;
import Model.LaboratorioModelo;
import Util.Conexion_BD;
import View.VentanaRight2;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import View.VentanaRight4;
import View.VentanaRight3;


public class VentanaInternalFrameGUI02 extends JFrame implements MouseListener {
    
    //Controladores
    private HorariosAlumnoController horariosAlumnoControlador;
    private AlumnoController alumnoControlador;
    private VentanaRight2 ventanaRight2;
    
    JMenuBar barra;
    JMenu item1;
    JMenuItem m1, m2, m3;
    JInternalFrame internalEstudiante, internalClase;
    JPanel panel1,panel2;
    
    JTable tabla1, tabla2;
    DefaultTableModel modelo1, modelo2;
    JScrollPane scroll1, scroll2;
    
    String[] titulo1 = {"CODIGO","APELLIDOS","NOMBRES"};
    String[] titulo2 = {"CODIGO ESTUDIANTE","CODIGO HORARIO"};
    
    JButton btnInsertar, btnModificar, btnEliminar, btnLimpiar;
    
    JLabel lbCodigoHorario, lbCodigoEstudiante1, lbBuscarHorario, lbNombres,lbApellidosHorario, lbNombreHorario;
    JTextField txtnCodigoHorario, txtCodEstudiante, txtBuscarHorarioEstudiante, txtNombres, txtApellidosHorario, txtNombreHorario;
    
    JLabel lbCodigoEstudiante, lbApellidos, lbBuscarEstudiante;
    JTextField txtCodigoEstudiante, txtApellidos, txtBuscarEstudiante;
    
    ArrayList<AsignaturaModelo> listaAsignatura;
    ArrayList<AlumnoModelo> listaAlumno;
    ArrayList<HorariosAlumnoModelo> listaHorariosAlumno;
    
    
    public VentanaInternalFrameGUI02(HorariosAlumnoController horariosAlumnoControlador, AlumnoController alumnoControlador, VentanaRight2 ventanaRight2) {
        this.horariosAlumnoControlador = horariosAlumnoControlador;
        this.alumnoControlador = alumnoControlador;
        this.ventanaRight2 = ventanaRight2;
        
        setSize(900, 900);
        setTitle("GESTOR DE ESTUDIANTES");
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        barra = new JMenuBar();
        setJMenuBar(barra);
        item1 = new JMenu("Archivo");
        barra.add(item1);
        m1 = new JMenuItem("Gestor de Estudiante");
        m1.addActionListener((e) -> InternalFrameEstudiantes());
        item1.add(m1);
        m2 = new JMenuItem("Gestor de Clase: Eliminar Estudiante");
        m2.addActionListener((e) -> InternalFrameClase());
        item1.add(m2);
        m3 = new JMenuItem("Salir");
        m3.addActionListener((e) -> Salir());
        item1.add(m3);
        
        internalFrameInitComponents2();
        internalFrameInitComponents1();
        listarEstudiante();
        listarHorariosAlumno();
        
       txtBuscarHorarioEstudiante.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FiltrarCodigoHorario(txtBuscarHorarioEstudiante.getText()); 
            }
        });
       
       txtBuscarEstudiante.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FiltrarEstudiante(txtBuscarEstudiante.getText()); 
            }
        });

    }

    public void internalFrameInitComponents2(){
        
        //======================================================================        
        // INTERNALFRAME DEL GESTOR DE ASIGNATURAS
        //======================================================================   
        
        internalClase = new JInternalFrame("Gestor de Clase: Eliminar Estudiante", true, true, true, true);
        internalClase.setSize(600, 700); 
        internalClase.setLocation(150, 50);
        add(internalClase);

        panel2 = new JPanel();
        panel2.setLayout(null);
        internalClase.add(panel2);
        
        lbCodigoHorario = new JLabel("Código Horario:");
        lbCodigoHorario.setBounds(80,50,100,30);
        panel2.add(lbCodigoHorario);
        txtnCodigoHorario = new JTextField();
        txtnCodigoHorario.setBounds(190,50,300,30);
        panel2.add(txtnCodigoHorario);
        
        lbCodigoEstudiante1 = new JLabel("Codigo Estudiante:");
        lbCodigoEstudiante1.setBounds(62,100,120,30);
        panel2.add(lbCodigoEstudiante1);
        txtCodEstudiante = new JTextField();
        txtCodEstudiante.setBounds(190,100,300,30);
        panel2.add(txtCodEstudiante);

        lbBuscarHorario = new JLabel("Buscar:");
        lbBuscarHorario.setBounds(123,150,100,30);
        panel2.add(lbBuscarHorario);
        txtBuscarHorarioEstudiante = new JTextField();
        txtBuscarHorarioEstudiante.setBounds(190,150,300,30);
        panel2.add(txtBuscarHorarioEstudiante);
        
        tabla2 = new JTable();
        scroll2 = new JScrollPane();
        modelo2 = new DefaultTableModel(null,titulo2);
        tabla2.setModel(modelo2);
        tabla2.addMouseListener(this);
        scroll2.setViewportView(tabla2);
        scroll2.setBounds(50,275,500,342);
        panel2.add(scroll2);
        
       btnEliminar = new JButton("Eliminar");
       btnEliminar.setBounds(300,210,100,30);
       btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btnEliminar.addActionListener((e)-> {
           EliminarEstudianteHorario();
               });
       panel2.add(btnEliminar);
       
       btnLimpiar = new JButton("Limpiar");
       btnLimpiar.setBounds(410,210,100,30);
       btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btnLimpiar.addActionListener((e)-> Limpiar());
       panel2.add(btnLimpiar);
       
       listarHorariosAlumno();
    }
    
    public void internalFrameInitComponents1(){
              //======================================================================        
            // INTERNALFRAME DEL GESTOR DE LABORATORIOS
            //======================================================================   

            internalEstudiante = new JInternalFrame("Gestor de Estudiante", true, true, true, true);
            internalEstudiante.setSize(600, 700); 
            internalEstudiante.setLocation(150, 50);
            add(internalEstudiante);

            panel1 = new JPanel();
            panel1.setLayout(null);
            internalEstudiante.add(panel1);
            
            lbCodigoEstudiante = new JLabel("Código de Estudiante:");
            lbCodigoEstudiante.setBounds(50,50,130,30);
            panel1.add(lbCodigoEstudiante);
            txtCodigoEstudiante = new JTextField();
            txtCodigoEstudiante.setBounds(190,50,300,30);
            panel1.add(txtCodigoEstudiante);

            
            lbApellidos = new JLabel("Apellidos:");
            lbApellidos.setBounds(120,100,100,30);
            panel1.add(lbApellidos);
            txtApellidos = new JTextField();
            txtApellidos.setBounds(190,100,300,30);
            panel1.add(txtApellidos);
            
            lbNombres = new JLabel("Nombres:");
            lbNombres.setBounds(120,150,100,30);
            panel1.add(lbNombres);
            txtNombres = new JTextField();
            txtNombres.setBounds(190,150,300,30);
            panel1.add(txtNombres);
            
            lbBuscarEstudiante = new JLabel("Buscar:");
            lbBuscarEstudiante.setBounds(130,200,100,30);
            panel1.add(lbBuscarEstudiante);
            txtBuscarEstudiante = new JTextField();
            txtBuscarEstudiante.setBounds(190,200,300,30);
            panel1.add(txtBuscarEstudiante);

            tabla1 = new JTable();
            scroll1 = new JScrollPane();
            modelo1 = new DefaultTableModel(null,titulo1);
            tabla1.setModel(modelo1);
            tabla1.addMouseListener(this);
            scroll1.setViewportView(tabla1);
            scroll1.setBounds(50,330,500,312);
            panel1.add(scroll1);

           btnInsertar = new JButton("Insertar");
           btnInsertar.setBounds(80,260,100,30);
           btnInsertar.setCursor(new Cursor(Cursor.HAND_CURSOR));
           btnInsertar.addActionListener((e)-> {
               InsertarAlumno();
               ventanaRight2.ListarAlumno();
                   });
           panel1.add(btnInsertar);

           btnModificar = new JButton("Modificar");
           btnModificar.setBounds(190,260,100,30);
           btnModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
           btnModificar.addActionListener((e)-> {
               ModificarAlumno();
               ventanaRight2.ListarAlumno();
                   });
           panel1.add(btnModificar);

           btnEliminar = new JButton("Eliminar");
           btnEliminar.setBounds(300,260,100,30);
           btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
           btnEliminar.addActionListener((e)-> {
               EliminarAlumno();
               ventanaRight2.ListarAlumno();
                   });
           panel1.add(btnEliminar);

           btnLimpiar = new JButton("Limpiar");
           btnLimpiar.setBounds(410,260,100,30);
           btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
           btnLimpiar.addActionListener((e)-> Limpiar());
           panel1.add(btnLimpiar);  
           
           listarEstudiante();
    }
    
    public void Salir() {
        this.dispose();
    }

    public void InsertarAlumno(){
       if (seLlenaronTodosLosCampos1()) {
           
            AlumnoModelo aluModelo = new AlumnoModelo();
            aluModelo.setCodigoAlumno(txtCodigoEstudiante.getText());
            aluModelo.setApellidos(txtApellidos.getText());
            aluModelo.setNombres(txtNombres.getText());
            
            int estado = alumnoControlador.insertarAlumnoController(aluModelo);
            
            if (estado == 1) {
                Util.WindowFactory.confirmationWindowCRUD("Registro Insertado","INSERTADO");
            } else {
                Util.WindowFactory.errorWindowCRUD("Registro No Insertado","INSERTADO");
            }
            
            listarEstudiante();
            Limpiar();
        } else {
            Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO");
        }        
    }

    public void ModificarAlumno(){
        if (seLlenaronTodosLosCampos1()){
            
            String codigoAlumnoAntiguo = (String) tabla1.getValueAt(tabla1.getSelectedRow(), 0);
            String nombre = txtNombres.getText();
            String apellido = txtApellidos.getText();
            String codigoAlumnoNuevo = txtCodigoEstudiante.getText();

            int estado = alumnoControlador.modificarAlumnoController(codigoAlumnoNuevo,nombre,apellido,codigoAlumnoAntiguo);

                if (estado == 1) {
                    Util.WindowFactory.confirmationWindowCRUD("Registro Modificado","MODIFICADO");


                } else {
                    Util.WindowFactory.errorWindowCRUD("Registro No Modificado","MODIFICADO");
                }

            listarEstudiante();
            Limpiar(); 
        }else{
           Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO"); 
        } 
    }
    
    public void EliminarAlumno(){
        if (seLlenaronTodosLosCampos1()){
            String codigo = (String) tabla1.getValueAt(tabla1.getSelectedRow(), 0);
            
            AlumnoModelo aluModelo = new AlumnoModelo();
            aluModelo.setCodigoAlumno(codigo);

            int estado = alumnoControlador.eliminarAlumnoController(aluModelo);

                if (estado == 1) {
                    Util.WindowFactory.confirmationWindowCRUD("Registro Eliminado","ELIMINADO");

                } else {
                    Util.WindowFactory.errorWindowCRUD("Registro No Eliminado","ELIMINADO");
                }

            listarEstudiante();
            Limpiar();            
        }else{
            Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO"); 
        }

    }
    
    public void EliminarEstudianteHorario(){
        if (seLlenaronTodosLosCampos()){

            String codigoEstudiante = (String) tabla2.getValueAt(tabla2.getSelectedRow(), 0);
            String codigoHorario = (String) tabla2.getValueAt(tabla2.getSelectedRow(), 1);
            
            System.out.println("code estudainte: " + codigoEstudiante);
            System.out.println("code horario: " + codigoHorario);
            
            int idHorario = horariosAlumnoControlador.obtenerIdHorarioPorCodigoHorario(codigoHorario);
            int idEstudiante = horariosAlumnoControlador.obtenerIdEstudiantePorCodigoEstudiante(codigoEstudiante);
            
            System.out.println("id horario:" + idHorario);
            System.out.println("id Estduiante:" + idEstudiante);
            
            int estado = horariosAlumnoControlador.eliminarHorarioAlumnoController(idEstudiante, idHorario);

                if (estado == 1) {
                    Util.WindowFactory.confirmationWindowCRUD("Registro Eliminado","ELIMINADO");
                } else {
                    Util.WindowFactory.errorWindowCRUD("Registro No Eliminado","ELIMINADO");
                }
            System.out.println("VALOR OBTENIDO:" + estado);
            listarHorariosAlumno();    
            Limpiar();            
        }else{
            Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO"); 
        }

    }   
    
     public void FiltrarCodigoHorario(String buscar) {
        listaHorariosAlumno = horariosAlumnoControlador.buscarResgistroHorarioAlumno(buscar); 
        modelo2.setRowCount(0); 

        for (HorariosAlumnoModelo obj : listaHorariosAlumno) {
            Object[] fila = {
                obj.getCodigoEstudiante(),
                obj.getCodigoHorario(),  
            };
            modelo2.addRow(fila); 
        }
    }
    
    public void FiltrarEstudiante(String buscar) {
        listaAlumno = alumnoControlador.buscarResgistroAlumnoController(buscar); 
        modelo1.setRowCount(0); // Limpia la tabla

        for (AlumnoModelo obj : listaAlumno) {
            Object[] fila = {
                obj.getCodigoAlumno(),
                obj.getApellidos(),
                obj.getApellidos(),   
            };
            modelo1.addRow(fila); 
        }
    }
    
    private void Limpiar() {
        txtnCodigoHorario.setText("");
        txtCodEstudiante.setText("");
        
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCodigoEstudiante.setText("");
    }
    
    public void InternalFrameClase() {
        internalClase.setVisible(true);
    }
    
    public void InternalFrameEstudiantes() {
        internalEstudiante.setVisible(true);
    }
    
    public void listarEstudiante() {
        modelo1.setRowCount(0);
        listaAlumno = alumnoControlador.enlistarAlumnoController();

        for (AlumnoModelo alu : listaAlumno) {
            modelo1.addRow(new Object[]{
                alu.getCodigoAlumno(),
                alu.getApellidos(),
                alu.getNombres()
            });
        }
    }
    
    public void listarHorariosAlumno() {
        modelo2.setRowCount(0);
        listaHorariosAlumno = horariosAlumnoControlador.enlistarHorarioAlumno();

        for (HorariosAlumnoModelo horarioAlumno : listaHorariosAlumno) {
            modelo2.addRow(new Object[]{
                horariosAlumnoControlador.obtenerCodigoEstudiantePorId(horarioAlumno.getIdHorario()),
                horariosAlumnoControlador.obtenerCodigoHoraioPorId(horarioAlumno.getIdHorario())
            });
        }
    }
    
    private boolean seLlenaronTodosLosCampos() {
        String[] campos = {
            txtCodEstudiante.getText().trim(), txtnCodigoHorario.getText().trim()
        };

        for (String campo : campos) {
            if (campo.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    private boolean seLlenaronTodosLosCampos1() {
        String[] campos = {
            txtCodigoEstudiante.getText().trim(), txtApellidos.getText().trim(), txtNombres.getText().trim()
        };

        for (String campo : campos) {
            if (campo.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void llenarCamposDesdeTablaClase() {
        int filaSeleccionada = tabla2.getSelectedRow();
        if (filaSeleccionada != -1) {
            txtCodEstudiante.setText(tabla2.getValueAt(filaSeleccionada, 0).toString());
            txtnCodigoHorario.setText(tabla2.getValueAt(filaSeleccionada, 1).toString());
        }
    }
    
    private void llenarCamposDesdeTablaAlumno() {
        int filaSeleccionada = tabla1.getSelectedRow();
        if (filaSeleccionada != -1) {
            txtCodigoEstudiante.setText(tabla1.getValueAt(filaSeleccionada, 0).toString());
            txtApellidos.setText(tabla1.getValueAt(filaSeleccionada, 1).toString());
            txtNombres.setText(tabla1.getValueAt(filaSeleccionada, 2).toString());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == tabla1){
            llenarCamposDesdeTablaAlumno();
        }
        if(e.getSource() == tabla2){
            llenarCamposDesdeTablaClase();
        }
    }

    
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

   
}
