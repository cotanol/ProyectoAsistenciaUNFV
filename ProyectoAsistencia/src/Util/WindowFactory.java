
package Util;

import View.Login;
import View.VentanaPrincipal;
import javax.swing.*;
import java.awt.*;
//import java.io.Console;

public class WindowFactory {

    public static JFrame confirmationWindowCRUD(String title, String msj) {
        // Cambiar apariencia del botón (opcional)
        UIManager.put("Button.select", new Color(0, 0, 0, 0));

        // Crear la ventana
        JFrame ventana = new JFrame();
        ventana.setTitle("Ventana de Confirmacion: " + title);
        ventana.setSize(470, 300);
        ventana.setLayout(null); // Posicionamiento manual
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setOpaque(true);
        panelPrincipal.setSize(470,300);
        panelPrincipal.setBackground(Color.white);
        ventana.add(panelPrincipal);

        JLabel lbRegistro = ComponentFactory.crearEtiquetaMsj("REGISTRO", 0, 60, 300, 30, Constantes.ERROR_FUENTE_LABEL_REGISTRO, Constantes.COLOR_TEXTO_NEGRO);
        panelPrincipal.add(lbRegistro);
        
        if(msj.equals("MODIFICADO")){
            JLabel lbimagen = ComponentFactory.insertarImagenLabel("Images/ConstructorAmarillo.gif", 128, 167, 270, 10);
            panelPrincipal.add(lbimagen);  
            JLabel lbCrud = ComponentFactory.crearEtiquetaMsj(msj, 0, 95, 300, 30, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lbCrud);   
        }else if(msj.equals("INSERTADO")){
            JLabel lbimagen = ComponentFactory.insertarImagenLabel("Images/PenguinDance.gif", 149, 142, 270, 20);
            panelPrincipal.add(lbimagen);             
            JLabel lbCrud = ComponentFactory.crearEtiquetaMsj(msj, 0, 95, 300, 30, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lbCrud);  
        }else if(msj.equals("ELIMINADO")){
            lbRegistro.setBounds(0,60,280,30);
            JLabel lbCrud = ComponentFactory.crearEtiquetaMsj(msj, 0, 95, 280, 30, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lbCrud);
            JLabel lbimagen = ComponentFactory.insertarImagenLabel("Images/PenguinCleaner.gif", 174, 141, 230, 20);
            lbimagen.setBackground(Color.WHITE);
            panelPrincipal.add(lbimagen);       
            
        }
   
        // Crear y agregar el botón "Cerrar"
        JButton cerrar = ComponentFactory.crearBotonAccion("Continuar",150,190,150,40);
        cerrar.setFont(new Font("arial",1,17));
        // Configurar el botón de cerrar sesión
        cerrar.addMouseListener(new EstiloHover.HoverAccionBoton(cerrar));
        cerrar.addActionListener(e -> ventana.dispose());
        panelPrincipal.add(cerrar);
        
        // Mostrar la ventana
        ventana.setVisible(true);
        return ventana;
        
    }
    
    public static JFrame errorWindowCRUD(String title, String msj) {
        // Cambiar apariencia del botón (opcional)

        // Crear la ventana
        JFrame ventana = new JFrame();
        ventana.setTitle("Error: " + title);
        ventana.setSize(470, 300);
        ventana.setLayout(null); // Posicionamiento manual
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setOpaque(true);
        panelPrincipal.setSize(470,300);
        panelPrincipal.setBackground(Color.white);
        ventana.add(panelPrincipal);

        JLabel lberror = ComponentFactory.crearEtiquetaMsj("ERROR", 0, 35, 300, 30, Constantes.ERROR_FUENTE_LABEL_REGISTRO, Constantes.COLOR_TEXTO_ROJO);
        panelPrincipal.add(lberror);
        
        JLabel lbimagen = ComponentFactory.insertarImagenLabel("Images/ConstructorTraje.gif", 128, 167, 270, 0);
        panelPrincipal.add(lbimagen); 
        
        if(msj.equals("MODIFICADO")){
            JLabel lberror1 = ComponentFactory.crearEtiquetaMsj("''Registro No Modificado''", 0, 85, 300, 15, Constantes.FUENTE_TEXTO_ERROR_15, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror1);
            JLabel lberror2 = ComponentFactory.crearEtiquetaMsj("Seleccione un registro", 0, 105, 300, 17, Constantes.FUENTE_TEXTO_ERROR_17, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror2);
            JLabel lberror3 = ComponentFactory.crearEtiquetaMsj("para poder MODIFICAR.", 0, 128, 300, 15, Constantes.FUENTE_TEXTO_ERROR_15, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror3);
        }else if(msj.equals("INSERTADO")){
            JLabel lberror1 = ComponentFactory.crearEtiquetaMsj("''Registro No Insertado''", 0, 85, 300, 15, Constantes.FUENTE_TEXTO_ERROR_15, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror1);
            JLabel lberror2 = ComponentFactory.crearEtiquetaMsj("No se pudo", 0, 105, 300, 15, Constantes.FUENTE_TEXTO_ERROR_17, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror2); 
            JLabel lberror3 = ComponentFactory.crearEtiquetaMsj("INSERTAR el REGISTRO.", 0, 125, 300, 15, Constantes.FUENTE_TEXTO_ERROR_15, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror3);
        }else if(msj.equals("ELIMINADO")){
            JLabel lberror1 = ComponentFactory.crearEtiquetaMsj("''Registro No Eliminado''", 0, 85, 300, 15, Constantes.FUENTE_TEXTO_ERROR_15, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror1);
            JLabel lberror2 = ComponentFactory.crearEtiquetaMsj("Seleccione un registro", 0, 105, 300, 17, Constantes.FUENTE_TEXTO_ERROR_17, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror2); 
            JLabel lberror3 = ComponentFactory.crearEtiquetaMsj("para poder ELIMINAR.", 0, 125, 300, 15, Constantes.FUENTE_TEXTO_ERROR_15, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror3);            
        }

        
        
        // Crear y agregar el botón "Cerrar"
        JButton cerrar = ComponentFactory.crearBotonAccion("Continuar",150,190,150,40);
        cerrar.setFont(new Font("arial",1,17));
        // Configurar el botón de cerrar sesión
        cerrar.addMouseListener(new EstiloHover.HoverAccionBoton(cerrar));
        cerrar.addActionListener(e -> ventana.dispose());
        panelPrincipal.add(cerrar);
        
        // Mostrar la ventana
        ventana.setVisible(true);
        return ventana;
        
    }
    
    public static JFrame errorLogin(String title, String msj){
       // Cambiar apariencia del botón (opcional)

        // Crear la ventana
        JFrame ventana = new JFrame();
        ventana.setTitle("Error" + title);
        ventana.setSize(470, 300);
        ventana.setLayout(null); // Posicionamiento manual
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setOpaque(true);
        panelPrincipal.setSize(470,300);
        panelPrincipal.setBackground(Color.white);
        ventana.add(panelPrincipal);

        JLabel lberror = ComponentFactory.crearEtiquetaMsj("ERROR", 0, 35, 300, 30, Constantes.ERROR_FUENTE_LABEL_REGISTRO, Constantes.COLOR_TEXTO_ROJO);
        panelPrincipal.add(lberror);
        
        JLabel lbimagen = ComponentFactory.insertarImagenLabel("Images/ConstructorTraje.gif", 128, 167, 270, 0);
        panelPrincipal.add(lbimagen); 
        
        if(msj.equals("CAMPOS_EN_BLANCO")){
            JLabel lberror1 = ComponentFactory.crearEtiquetaMsj("''Campos en Blanco''", 0, 85, 300, 15, Constantes.FUENTE_TEXTO_ERROR_15, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror1);
            JLabel lberror2 = ComponentFactory.crearEtiquetaMsj("No puedes dejar", 0, 105, 300, 15, Constantes.FUENTE_TEXTO_ERROR_17, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror2);
            JLabel lberror3 = ComponentFactory.crearEtiquetaMsj("campos en BLANCO.", 0, 125, 300, 15, Constantes.FUENTE_TEXTO_ERROR_15, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror3);       
        }else if(msj.equals("USUARIO_CONTRASEÑA_INCORRECTOS")){
            JLabel lberror1 = ComponentFactory.crearEtiquetaMsj("''Usuario o Contraseña''", 0, 85, 300, 15, Constantes.FUENTE_TEXTO_ERROR_15, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror1);
            JLabel lberror2 = ComponentFactory.crearEtiquetaMsj("''incorrectos''", 0, 105, 300, 15, Constantes.FUENTE_TEXTO_ERROR_17, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror2);
            JLabel lberror3 = ComponentFactory.crearEtiquetaMsj("Volver a digitar datos.", 0, 125, 300, 15, Constantes.FUENTE_TEXTO_ERROR_15, Constantes.COLOR_TEXTO_NEGRO);
            panelPrincipal.add(lberror3);              
        }
        

        // Crear y agregar el botón "Cerrar"
        JButton cerrar = ComponentFactory.crearBotonAccion("Continuar",150,190,150,40);
        cerrar.setFont(new Font("arial",1,17));
        // Configurar el botón de cerrar sesión
        cerrar.addMouseListener(new EstiloHover.HoverAccionBoton(cerrar));
        cerrar.addActionListener(e -> ventana.dispose());
        panelPrincipal.add(cerrar);
        
        // Mostrar la ventana
        ventana.setVisible(true);
        return ventana; 
    }
    
    public static JFrame windowClose(String title, String msj, String nombreUsuario){
        // Crear la ventana
        JFrame ventana = new JFrame();
        ventana.setTitle("Ventana de Cofirmación: " + title);
        ventana.setSize(600, 400);
        ventana.setLayout(null); // Posicionamiento manual
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);


        if(msj.equals("CIERRE_SESION")){
            VentanaPrincipal w = new VentanaPrincipal();
            w.dispose();
            JLabel imagen = ComponentFactory.insertarImagenLabel("Images/real_baile.gif", 180, 180, 190, 70);
            ventana.add(imagen);   
            JLabel lbpregunta = ComponentFactory.preguntaVentanaCerrar("¿Realmente Quieres Salir del Programa?", 0, 30, 600, 30);
            ventana.add(lbpregunta);
            
            JButton btnSi = ComponentFactory.crearBotonAccion("SI", 140, 300, 100, 30);
            btnSi.setFont(new Font("arial",1,22));
            btnSi.addMouseListener(new EstiloHover.HoverAccionBoton(btnSi));
            btnSi.addActionListener(e -> {
                ventana.dispose();
                Login vtn = new Login();
                vtn.setVisible(true);    
            });
            ventana.add(btnSi);

            JButton btnNo = ComponentFactory.crearBotonAccion("NO", 330,300,100,30);
            btnNo.setFont(new Font("arial",1,22));
            btnNo.addMouseListener(new EstiloHover.HoverAccionBoton(btnNo));
            btnNo.addActionListener(e -> {
                ventana.dispose();
                w.setUser(nombreUsuario);
                w.permisosDocente(w.obtenerElUsuario(nombreUsuario));
                w.setVisible(true);  
            });
            ventana.add(btnNo);
        
        }else if(msj.equals("CIERRE_PROGRAMA")){
            JLabel imagen = ComponentFactory.insertarImagenLabel("Images/penguin_tp.gif", 180, 180, 210, 80);
            ventana.add(imagen);   
            JLabel lbpregunta = ComponentFactory.preguntaVentanaCerrar("¿Realmente Quieres Salir del Programa?", 0, 30, 600, 30);
            ventana.add(lbpregunta);    
            
            JButton btnSi = ComponentFactory.crearBotonAccion("SI", 140, 300, 100, 30);
            btnSi.setFont(new Font("arial",1,22));
            btnSi.addMouseListener(new EstiloHover.HoverAccionBoton(btnSi));
            btnSi.addActionListener(e -> System.exit(0));
            ventana.add(btnSi);

            JButton btnNo = ComponentFactory.crearBotonAccion("NO", 330,300,100,30);
            btnNo.setFont(new Font("arial",1,22));
            btnNo.addMouseListener(new EstiloHover.HoverAccionBoton(btnNo));
            btnNo.addActionListener(e -> ventana.dispose());
            ventana.add(btnNo);            
        }

        
        
        
        // Mostrar la ventana
        ventana.setVisible(true);
        return ventana; 
    }
    
    public static void main(String[] args) {
       /*
       //CODIGOS PARA COPIAR Y PEGAR DEFRENTE PARA QUE APAREZCA LA VENTANA
       
       //VENTANAS DE CIERRE
       Util.WindowFactory.windowClose("Cirre de Programa","CIERRE_PROGRAMA"); 
       Util.WindowFactory.windowClose("Cirre de Sesión","CIERRE_SESION");
       
       //VENTANAS DE CONFIRMACION DE CRUD
       Util.WindowFactory.confirmationWindowCRUD("Registro Eliminado","ELIMINADO");
       Util.WindowFactory.confirmationWindowCRUD("Registro Modificado","MODIFICADO");
       Util.WindowFactory.confirmationWindowCRUD("Registro Insertado","INSERTADO");
       
       //VENTANAS DE ERROR DE CRUD
        Util.WindowFactory.errorWindowCRUD("Registro No Eliminado","ELIMINADO");
        Util.WindowFactory.errorWindowCRUD("Registro No Modificado","MODIFICADO");
        Util.WindowFactory.errorWindowCRUD("Registro No Insertado","INSERTADO");
        Util.WindowFactory.errorLogin(": Campos en Blanco","CAMPOS_EN_BLANCO");
        
        //VENTANAS DE ERRORES DE LOGIN
        Util.WindowFactory.errorLogin(" del login: Campos en Blanco","CAMPOS_EN_BLANCO");
        Util.WindowFactory.errorLogin(" del login: Usuario/Contraseña Incorrectos","USUARIO_CONTRASEÑA_INCORRECTOS");
        */
       
       //NOTA: UNA VEZ QUE COPIES LOS CODIGO, BORRAR METODO MAIN DE ESTA CLASE PORQUE NO SIRVE OK
    }
}
