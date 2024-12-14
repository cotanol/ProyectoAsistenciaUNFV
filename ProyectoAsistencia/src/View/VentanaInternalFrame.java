package View;
import Controller.AsignaturaController;
import Controller.LaboratorioController;
import Model.AsignaturaModelo;
import Model.LaboratorioModelo;
import Util.Conexion_BD;
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


public class VentanaInternalFrame extends JFrame implements MouseListener {
    
    //Controladores
    private AsignaturaController asignaturaControlador;
    private LaboratorioController laboratorioControlador;
    private VentanaRight4 ventanaRight4;
    
    JMenuBar barra;
    JMenu item1;
    JMenuItem m1, m2, m3;
    JInternalFrame internalLaboratorios, internalAsignaturas;
    JPanel panel1,panel2;
    
    JTable tabla1, tabla2;
    DefaultTableModel modelo1, modelo2;
    JScrollPane scroll1, scroll2;
    
    String[] titulo1 = {"ID_LAB","NRO_LABORATORIO","CAPACIDAD"};
    String[] titulo2 = {"ID_ASIG","NOMBRE DE LA ASIGNATURA","CODIGO"};
    
    JButton btnInsertar, btnModificar, btnEliminar, btnLimpiar;
    
    JLabel lbasignatura, lbcodigoasignatura, lbBuscarAsignatura;
    JTextField txtnombreasignatura, txtcodigoasignatura, txtBuscarAsignatura;
    
    JLabel lblaboratorio, lbcapacidad, lbBuscarLaboratorio;
    JTextField txtlaboratorio, txtcapacidad, txtBuscarLaboratorio;
    
    ArrayList<AsignaturaModelo> listaAsignatura;
    ArrayList<LaboratorioModelo> listaLaboratorio;
    
    public VentanaInternalFrame(AsignaturaController asignaturaControlador, LaboratorioController laboratorioControlador, VentanaRight4 ventanaRight4) {
        this.asignaturaControlador = asignaturaControlador;
        this.laboratorioControlador = laboratorioControlador;
        this.ventanaRight4 = ventanaRight4;
        
        setSize(900, 900);
        setTitle("GESTOR DE ASIGNATURAS Y LABORATORIOS");
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        barra = new JMenuBar();
        setJMenuBar(barra);
        item1 = new JMenu("Archivo");
        barra.add(item1);
        m1 = new JMenuItem("Gestor de Laboratorios");
        m1.addActionListener((e) -> InternalFrameLaboratorios());
        item1.add(m1);
        m2 = new JMenuItem("Gestor de Asignaturas");
        m2.addActionListener((e) -> InternalFrameAsignaturas());
        item1.add(m2);
        m3 = new JMenuItem("Salir");
        m3.addActionListener((e) -> Salir());
        item1.add(m3);
        
        internalFrameInitComponents2();
        internalFrameInitComponents1();
        listarAsignatura();
        listarLaboratorio();

       txtBuscarAsignatura.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FiltrarAsignatura(txtBuscarAsignatura.getText()); 
            }
        });
       
       txtBuscarLaboratorio.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FiltrarLaboratorio(txtBuscarLaboratorio.getText()); 
            }
        });
        

       


    }
    
    public void internalFrameInitComponents2(){
        
        //======================================================================        
        // INTERNALFRAME DEL GESTOR DE ASIGNATURAS
        //======================================================================   
        
        internalAsignaturas = new JInternalFrame("Gestor de Asignaturas", true, true, true, true);
        internalAsignaturas.setSize(600, 700); 
        internalAsignaturas.setLocation(150, 50);
        add(internalAsignaturas);

        panel2 = new JPanel();
        panel2.setLayout(null);
        internalAsignaturas.add(panel2);
        
        lbasignatura = new JLabel("Nombre:");
        lbasignatura.setBounds(110,50,100,30);
        panel2.add(lbasignatura);
        txtnombreasignatura = new JTextField();
        txtnombreasignatura.setBounds(190,50,300,30);
        panel2.add(txtnombreasignatura);
        
        lbcodigoasignatura = new JLabel("Codigo:");
        lbcodigoasignatura.setBounds(110,100,100,30);
        panel2.add(lbcodigoasignatura);
        txtcodigoasignatura = new JTextField();
        txtcodigoasignatura.setBounds(190,100,300,30);
        panel2.add(txtcodigoasignatura);
        
        lbBuscarAsignatura = new JLabel("Buscar:");
        lbBuscarAsignatura.setBounds(110,150,100,30);
        panel2.add(lbBuscarAsignatura);
        txtBuscarAsignatura = new JTextField();
        txtBuscarAsignatura.setBounds(190,150,300,30);
        panel2.add(txtBuscarAsignatura);
        
        tabla2 = new JTable();
        scroll2 = new JScrollPane();
        modelo2 = new DefaultTableModel(null,titulo2);
        tabla2.setModel(modelo2);
        tabla2.addMouseListener(this);
        scroll2.setViewportView(tabla2);
        scroll2.setBounds(50,275,500,342);
        panel2.add(scroll2);
       
       btnInsertar = new JButton("Insertar");
       btnInsertar.setBounds(80,210,100,30);
       btnInsertar.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btnInsertar.addActionListener((e)-> {
           InsertarAsignatura();
           ventanaRight4.cargarComboAsignatura();
               });
       panel2.add(btnInsertar);
       
       btnModificar = new JButton("Modificar");
       btnModificar.setBounds(190,210,100,30);
       btnModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btnModificar.addActionListener((e)-> {
           ModificarAsignatura();
           ventanaRight4.cargarComboAsignatura();
               });
       panel2.add(btnModificar);
       
       btnEliminar = new JButton("Eliminar");
       btnEliminar.setBounds(300,210,100,30);
       btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btnEliminar.addActionListener((e)-> {
           EliminarAsignatura();
           ventanaRight4.cargarComboAsignatura();
               });
       panel2.add(btnEliminar);
       
       btnLimpiar = new JButton("Limpiar");
       btnLimpiar.setBounds(410,210,100,30);
       btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btnLimpiar.addActionListener((e)-> Limpiar());
       panel2.add(btnLimpiar);
    }
    
    public void internalFrameInitComponents1(){
              //======================================================================        
            // INTERNALFRAME DEL GESTOR DE LABORATORIOS
            //======================================================================   

            internalLaboratorios = new JInternalFrame("Gestor de Laboratorios", true, true, true, true);
            internalLaboratorios.setSize(600, 700); 
            internalLaboratorios.setLocation(150, 50);
            add(internalLaboratorios);

            panel1 = new JPanel();
            panel1.setLayout(null);
            internalLaboratorios.add(panel1);
            
            lblaboratorio = new JLabel("Nro. Laboratorio:");
            lblaboratorio.setBounds(80,50,100,30);
            panel1.add(lblaboratorio);
            txtlaboratorio = new JTextField();
            txtlaboratorio.setBounds(190,50,300,30);
            panel1.add(txtlaboratorio);

            
            lbcapacidad = new JLabel("Capacidad:");
            lbcapacidad.setBounds(80,100,100,30);
            panel1.add(lbcapacidad);
            txtcapacidad = new JTextField();
            txtcapacidad.setBounds(190,100,300,30);
            panel1.add(txtcapacidad);

            lbBuscarLaboratorio = new JLabel("Buscar:");
            lbBuscarLaboratorio.setBounds(80,150,100,30);
            panel1.add(lbBuscarLaboratorio);
            txtBuscarLaboratorio = new JTextField();
            txtBuscarLaboratorio.setBounds(190,150,300,30);
            panel1.add(txtBuscarLaboratorio);

            tabla1 = new JTable();
            scroll1 = new JScrollPane();
            modelo1 = new DefaultTableModel(null,titulo1);
            tabla1.setModel(modelo1);
            tabla1.addMouseListener(this);
            scroll1.setViewportView(tabla1);
            scroll1.setBounds(50,275,500,342);
            panel1.add(scroll1);

           btnInsertar = new JButton("Insertar");
           btnInsertar.setBounds(80,210,100,30);
           btnInsertar.setCursor(new Cursor(Cursor.HAND_CURSOR));
           btnInsertar.addActionListener((e)-> {
               InsertarLaboratorio();
               ventanaRight4.cargarComboNroLab();
               ventanaRight4.cargarCombosOtrasVentana();
                   });
           panel1.add(btnInsertar);

           btnModificar = new JButton("Modificar");
           btnModificar.setBounds(190,210,100,30);
           btnModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
           btnModificar.addActionListener((e)-> {
               ModificarLaboratorio();
               ventanaRight4.cargarComboNroLab();
               ventanaRight4.cargarCombosOtrasVentana();
           });
           panel1.add(btnModificar);

           btnEliminar = new JButton("Eliminar");
           btnEliminar.setBounds(300,210,100,30);
           btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
           btnEliminar.addActionListener((e)-> {
               EliminarLaboratorio();
               ventanaRight4.cargarComboNroLab();
               ventanaRight4.cargarCombosOtrasVentana();
           });
           panel1.add(btnEliminar);

           btnLimpiar = new JButton("Limpiar");
           btnLimpiar.setBounds(410,210,100,30);
           btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
           btnLimpiar.addActionListener((e)-> Limpiar());
           panel1.add(btnLimpiar);  
           
           listarLaboratorio();
           listarAsignatura();
    }
    
    public void Salir() {
        this.dispose();
    }
    
    public void InsertarAsignatura(){
       if (seLlenaronTodosLosCampos()) {
            AsignaturaModelo asigModelo = new AsignaturaModelo();

            asigModelo.setNombre(txtnombreasignatura.getText());
            asigModelo.setCodigo(txtcodigoasignatura.getText());

            int estado = asignaturaControlador.insertarAsignaturaController(asigModelo);
            
            if (estado == 1) {
                Util.WindowFactory.confirmationWindowCRUD("Registro Insertado","INSERTADO");
            } else {
                Util.WindowFactory.errorWindowCRUD("Registro No Insertado","INSERTADO");
            }
            
            listarAsignatura();
            Limpiar();
        } else {
            Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO");
        } 
    }
    
    public void InsertarLaboratorio(){
       if (seLlenaronTodosLosCampos1()) {
            LaboratorioModelo labModelo = new LaboratorioModelo();

            labModelo.setNumeroLab(txtlaboratorio.getText());
            labModelo.setCapacidad(Integer.parseInt(txtcapacidad.getText()));

            int estado = laboratorioControlador.insertarLaboratorioController(labModelo);
            
            if (estado == 1) {
                Util.WindowFactory.confirmationWindowCRUD("Registro Insertado","INSERTADO");


            } else {
                Util.WindowFactory.errorWindowCRUD("Registro No Insertado","INSERTADO");
            }
            
            listarLaboratorio();
            Limpiar();
        } else {
            Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO");
        }        
    }
    
    public void ModificarAsignatura(){
        if (seLlenaronTodosLosCampos()){
            String codigoAntiguo = (String) tabla2.getValueAt(tabla2.getSelectedRow(), 2);
            String nombre = txtnombreasignatura.getText();
            String codigoNuevo = txtcodigoasignatura.getText();

            AsignaturaModelo asigModelo = new AsignaturaModelo();
            asigModelo.setNombre(nombre);
            asigModelo.setCodigo(codigoNuevo);

            int estado = asignaturaControlador.modificarAsignaturaController(nombre,codigoAntiguo,codigoNuevo);

                if (estado == 1) {
                    Util.WindowFactory.confirmationWindowCRUD("Registro Modificado","MODIFICADO");
                } else {
                    Util.WindowFactory.errorWindowCRUD("Registro No Modificado","MODIFICADO");
                }

            listarAsignatura();
            Limpiar(); 
        }else{
           Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO"); 
        } 
    }
    
    public void ModificarLaboratorio(){
        if (seLlenaronTodosLosCampos1()){
            String codigoTabla = (String) tabla1.getValueAt(tabla1.getSelectedRow(), 1);
            int capacidad = Integer.parseInt(txtcapacidad.getText());
            String codigoNuevo = txtlaboratorio.getText();

            LaboratorioModelo labModelo = new LaboratorioModelo();
            labModelo.setCapacidad(capacidad);
            labModelo.setNumeroLab(codigoNuevo);

            int estado = laboratorioControlador.modificarLaboratorioController(codigoNuevo,capacidad,codigoTabla);

                if (estado == 1) {
                    Util.WindowFactory.confirmationWindowCRUD("Registro Modificado","MODIFICADO");


                } else {
                    Util.WindowFactory.errorWindowCRUD("Registro No Modificado","MODIFICADO");
                }

            listarLaboratorio();
            Limpiar(); 
        }else{
           Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO"); 
        } 
    }
    
    public void EliminarAsignatura(){
        if (seLlenaronTodosLosCampos()){
            String codigo = (String) tabla2.getValueAt(tabla2.getSelectedRow(), 2);
            AsignaturaModelo asigModelo = new AsignaturaModelo();
            asigModelo.setCodigo(codigo);

            int estado = asignaturaControlador.eliminarAsignaturaController(asigModelo);

                if (estado == 1) {
                    Util.WindowFactory.confirmationWindowCRUD("Registro Eliminado","ELIMINADO");
                } else {
                    Util.WindowFactory.errorWindowCRUD("Registro No Eliminado","ELIMINADO");
                }

            listarAsignatura();
            Limpiar();            
        }else{
            Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO"); 
        }

    }
    
    public void EliminarLaboratorio(){
        if (seLlenaronTodosLosCampos1()){
            String codigo = (String) tabla1.getValueAt(tabla1.getSelectedRow(), 1);
            
            LaboratorioModelo labModelo = new LaboratorioModelo();
            labModelo.setNumeroLab(codigo);

            int estado = laboratorioControlador.eliminarLaboratorioController(labModelo);

                if (estado == 1) {
                    Util.WindowFactory.confirmationWindowCRUD("Registro Eliminado","ELIMINADO");

                } else {
                    Util.WindowFactory.errorWindowCRUD("Registro No Eliminado","ELIMINADO");
                }

            listarLaboratorio();
            Limpiar();            
        }else{
            Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO"); 
        }

    }
    
    public void FiltrarAsignatura(String buscar) {
        listaAsignatura = asignaturaControlador.buscarAsignaturaController(buscar); 
        modelo2.setRowCount(0); // Limpia la tabla

        for (AsignaturaModelo obj : listaAsignatura) {
            Object[] fila = {
                obj.getIdAsignatura(),
                obj.getNombre(),
                obj.getCodigo(),   
            };
            modelo2.addRow(fila); 
        }
    }
    
    public void FiltrarLaboratorio(String buscar) {
        listaLaboratorio = laboratorioControlador.buscarLaboratorioController(buscar); 
        modelo1.setRowCount(0); // Limpia la tabla

        for (LaboratorioModelo obj : listaLaboratorio) {
            Object[] fila = {
                obj.getIdLaboratorio(),
                obj.getNumeroLab(),
                obj.getCapacidad(),   
            };
            modelo1.addRow(fila); 
        }
    }
    
    private void Limpiar() {
        txtnombreasignatura.setText("");
        txtcodigoasignatura.setText("");
        txtBuscarAsignatura.setText("");
        
        txtlaboratorio.setText("");
        txtcapacidad.setText("");
        txtBuscarLaboratorio.setText("");
    }
    
    public void InternalFrameAsignaturas() {
        internalAsignaturas.setVisible(true);
    }
    
    public void InternalFrameLaboratorios() {
        internalLaboratorios.setVisible(true);
    }
    
    public void listarAsignatura() {
        modelo2.setRowCount(0);
        listaAsignatura = asignaturaControlador.enlistarAsignaturaController();

        for (AsignaturaModelo usuarioTa : listaAsignatura) {
            modelo2.addRow(new Object[]{
                usuarioTa.getIdAsignatura(),
                usuarioTa.getNombre(),
                usuarioTa.getCodigo()
            });
        }
    }
    
    public void listarLaboratorio() {
        modelo1.setRowCount(0);
        listaLaboratorio = laboratorioControlador.enlistarLaboratorioController();

        for (LaboratorioModelo lab : listaLaboratorio) {
            modelo1.addRow(new Object[]{
                lab.getIdLaboratorio(),
                lab.getNumeroLab(),
                lab.getCapacidad()
            });
        }
    }
    
    private boolean seLlenaronTodosLosCampos() {
        String[] campos = {
            txtnombreasignatura.getText().trim(), txtcodigoasignatura.getText().trim()
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
            txtlaboratorio.getText().trim(), txtcapacidad.getText().trim()
        };

        for (String campo : campos) {
            if (campo.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    private void llenarCamposDesdeTablaAsignatura() {
        int filaSeleccionada = tabla2.getSelectedRow();
        if (filaSeleccionada != -1) {
            txtnombreasignatura.setText(tabla2.getValueAt(filaSeleccionada, 1).toString());
            txtcodigoasignatura.setText(tabla2.getValueAt(filaSeleccionada, 2).toString());
        }
    }
    
    private void llenarCamposDesdeTablaLaboratorio() {
        int filaSeleccionada = tabla1.getSelectedRow();
        if (filaSeleccionada != -1) {
            txtlaboratorio.setText(tabla1.getValueAt(filaSeleccionada, 1).toString());
            txtcapacidad.setText(tabla1.getValueAt(filaSeleccionada, 2).toString());
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == tabla1){
            llenarCamposDesdeTablaLaboratorio();
        }
        if(e.getSource() == tabla2){
            llenarCamposDesdeTablaAsignatura();
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
