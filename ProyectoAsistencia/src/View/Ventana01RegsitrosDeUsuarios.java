
package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import Model.*;
import java.util.ArrayList;


public class Ventana01RegsitrosDeUsuarios extends JFrame implements ActionListener {
    
    int idUsuario;
    
    JPanel panelLeft, panelRight1, panelRight2, panelRight3, panelRight4, panelLogo, panelUser;
    
    ImageIcon imagen1, imagen2, imagenEscala1, imagenEscala2, imagen3, imagenEscalada3;
    JLabel lbImagenUser, lbImagenLogo, lbNameUser;
    
    JButton btnCerrarSesion, btnRegsitroUsuarios, btnControlAsistencia, btnControlEquipos, btnHorariosLaboratorio;
    
    JLabel lbRegistrosUsuarios, lbControlAsistencia, lbControlEquipos, lbHorariosLaboratorio,
            nombreSubPanel1_1, nombreSubPanel1_2;
    
    JPanel subPanel1, subPanel2, subPanel3, subPanel4, subPanel5, subPanel6, subPanel7, subPanel8;
    JScrollPane  scrollSubPanel1_2;
    
    JLabel lbNombres, lbApellidos, lbTipoDeDocumento, lbNumeroDeDocumento, lbNumeroDeContacto, lbCargo,
            lbUsuario, lbContraseña, lbEmail;

    JTextField txtNombres, txtApellidos, txtTipoDeDocumento, txtNumeroDeDocumento, txtNumeroDeContacto, txtCargo,
            txtUsuario, txtContraseña, txtEmail;
    
    JComboBox<String> cbTipoDeDocumento, cbCargo;
    
    JButton btnAgregar, btnModificar, btnEliminar;
    
    Color colorBaseBotones = new Color(255,152,0);  
    Color colorHoverSeleccionado1 = new Color(233,113,50);
    Color colorHoverSeleccionado2 = new Color(255,198,66);
    
    Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Borde negro de 2 px
    Border border1 = BorderFactory.createLineBorder(colorHoverSeleccionado1,2);
    
    JTable tablaUsuarios;
    DefaultTableModel modeloUsuario;
    
    ArrayList<Usuario> listaUsuarios;
    Usuario usuario;
    UsuarioDAO usuarioDAO;
    
    
    private boolean boton1Seleccionado = false;
    private boolean boton2Seleccionado = false;
    private boolean boton3Seleccionado = false;
    private boolean boton4Seleccionado = false;
    
    public Ventana01RegsitrosDeUsuarios(){
        //COMANDO PARA QUE NO HAYA FOCO A LA HORA DE PRESIONAR EL BOTON
        UIManager.put("Button.select", new Color(0, 0, 0, 0)); // Foco transparente de seleccion
        //PROPIEDADES DE LA VENTANA EN GENERAL
        setSize(1600,900);
        setTitle("GESTOR DE LABORATORIO DE LA UNIVERSIDAD NACIONAL FEDERCIO VILLARREAL");
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        //DAO
        usuarioDAO = new UsuarioDAO();
        
        
        
        
        //-----------------------------
        //PANEL IZQUIERDO DONDE ESTA LAS PESTAÑAS DE TODOS LOS MENUS
        panelLeft = new JPanel();
        panelLeft.setLayout(null);
        panelLeft.setBounds(0,0,300,900);
        panelLeft.setBackground(new Color(255,152,0));
        add(panelLeft);
        //------------------------------
        //PANELES DERECHOS DONDE ESTANA LOS ELEMENTOS DE CADA SECCION
        //PANEL DERECHO 1 - REGISTRO DE USUARIOS
        panelRight1 = new JPanel();
        panelRight1.setLayout(null);
        panelRight1.setBounds(300,0,1300,900);
        panelRight1.setBackground(new Color (238,238,238));
        add(panelRight1);
        //--------------------------------
        //PANEL DERECHO 2 - CONTROL DE ASISTENCIA
        panelRight2 = new JPanel();
        panelRight2.setLayout(null);
        panelRight2.setBounds(300,0,1300,900);
        panelRight2.setBackground(new Color (238,238,238));
        panelRight2.setOpaque(true);
        add(panelRight2);
        //--------------------------------
        //PANEL DERECHO 3 - CONTROL DE EQUIPOS
        panelRight3 = new JPanel();
        panelRight3.setLayout(null);
        panelRight3.setBounds(300,0,1300,900);
        panelRight3.setBackground(new Color (238,238,238));
        add(panelRight3);
        //--------------------------------
        //PANEL DERECHO 4 - HORARIOS DE LABORATORIO
        panelRight4 = new JPanel();
        panelRight4.setLayout(null);
        panelRight4.setBounds(300,0,1300,900);
        panelRight4.setBackground(new Color (238,238,238));
        add(panelRight4);
        //--------------------------------
        //PANEL LOGO VA DENTRO DEL (PANEL IZQUIEROD / PANEL LEFT)
        panelLogo = new JPanel();
        panelLogo.setLayout(null);
        panelLogo.setBounds(0,0,300,150);
        panelLogo.setBackground(new Color(233,113,50));
        panelLeft.add(panelLogo);
        
        //DE ESTA FORMA SE AGREGA IMAGENES
        // SE REQUIERE 2 TIPOS "ImageIcon" y un JLabel
        imagen3 =new ImageIcon("Images/logo_villarreal.png");
        imagenEscalada3 = new ImageIcon(imagen3.getImage().getScaledInstance(250, 110, Image.SCALE_DEFAULT));
        lbImagenLogo = new JLabel(imagenEscalada3,SwingConstants.CENTER);
        lbImagenLogo.setBounds(20,10,260,130);
        panelLogo.add(lbImagenLogo);
        
        //--------------------------------
        //PANEL USUER VA DENTRO DEL (PANEL IZQUIEROD / PANEL LEFT)
        //EN ESTE PANEL ESTA EL NOMBRE DE USUARIO CON EL PINGUINO ORRAI
        panelUser = new JPanel();
        panelUser.setLayout(null);
        panelUser.setBounds(0,150,300,290);
        panelUser.setOpaque(false);
        panelLeft.add(panelUser);
        
        //CREAMOS LA ETIQUETA IMAGEN PARA PONER AL PINGUINO ORRAI VAGAZO
        imagen2 = new ImageIcon("Images/user_penguin.png");
        imagenEscala2 = new ImageIcon(imagen2.getImage().getScaledInstance(195, 195, Image.SCALE_DEFAULT));

        lbImagenUser = new JLabel(imagenEscala2,SwingConstants.CENTER);
        lbImagenUser.setBackground(colorBaseBotones);
        lbImagenUser.setOpaque(false);
        lbImagenUser.setBounds(0,7,300,205);
        panelUser.add(lbImagenUser);
        
        //SE CREO UN METODO PARA CAPTURAR EL NOMBRE DE USUARIO Y PODER MOSTRARLO
        //EN EL PANEL USER QUE ESTA DENTRO DEL PANEL LEFT O IZQUIERDO
        //POR ESO NO HAY ND AQUI DE BAJO DEL LABEL
        lbNameUser = new JLabel();
        lbNameUser.setForeground(Color.BLACK);
        lbNameUser.setBackground(new Color(233,113,50));
        lbNameUser.setOpaque(true);
        lbNameUser.setForeground(Color.white);
        lbNameUser.setFont(new Font("poppins", 1, 22));
        lbNameUser.setHorizontalAlignment(SwingConstants.CENTER);
        lbNameUser.setBounds(0, 220, 300, 30);
        panelUser.add(lbNameUser); // Asegúrate de que esté agregado al panel correctamente
        
        //--------------------------------
        //SE AGREGAN LOS BOTONES DE LAS PETAÑAS DE CADA SECCION
        //ESTOS BOTONES VAN DENTRO DEL PANEL LEFT O IZQUIERDO
        
        //BOTON/PESTAÑA - RESGISTRO DE USUARIOS (DEFAULT) CUANDO SE INGRESA AL PROGRAMA
        btnRegsitroUsuarios = new JButton("Registro de Usuarios");
        btnRegsitroUsuarios.setBounds(0,410,300,70);
        btnRegsitroUsuarios.addActionListener(this); 
        btnRegsitroUsuarios.setBackground(new Color(255,152,0));
        btnRegsitroUsuarios.setForeground(Color.black);
        btnRegsitroUsuarios.setFont(new Font("poppins",0,22));
        btnRegsitroUsuarios.setBorderPainted(false); // marco del boton no seleccionado
        btnRegsitroUsuarios.setFocusPainted(false); // nombre del boton no seleccionado
        panelLeft.add(btnRegsitroUsuarios);
        
        //BOTON/PESTAÑA - CONTROL DE ASISTENCIAS
        btnControlAsistencia = new JButton("Control de Asistencia");
        btnControlAsistencia.setBounds(0,480,300,70);
        btnControlAsistencia.addActionListener(this); 
        btnControlAsistencia.setBackground(new Color(255,152,0));
        btnControlAsistencia.setForeground(Color.black);
        btnControlAsistencia.setFont(new Font("poppins",0,22));
        btnControlAsistencia.setBorderPainted(false); // marco del boton no seleccionado
        btnControlAsistencia.setFocusPainted(false); // nombre del boton no seleccionado
        panelLeft.add(btnControlAsistencia);
        
        //BOTON/PESTAÑA - CONTROL DE EQUIPOS
        btnControlEquipos = new JButton("Control de Equipos");
        btnControlEquipos.setBounds(0,550,300,70);
        btnControlEquipos.addActionListener(this); 
        btnControlEquipos.setBackground(new Color(255,152,0));
        btnControlEquipos.setForeground(Color.black);
        btnControlEquipos.setFont(new Font("poppins",0,22));
        btnControlEquipos.setBorderPainted(false); // marco del boton no seleccionado (aspecto visula de seleccion anular)
        btnControlEquipos.setFocusPainted(false); // nombre del boton no seleccionado (aspecto visula de seleccion anular)
        panelLeft.add(btnControlEquipos);
        
        //BOTON/PESTAÑA - HORARIOS DE LABORATORIO
        btnHorariosLaboratorio = new JButton("Horarios de Laboratorio");
        btnHorariosLaboratorio.setBounds(0,620,300,70);
        btnHorariosLaboratorio.addActionListener(this); 
        btnHorariosLaboratorio.setBackground(new Color(255,152,0));
        btnHorariosLaboratorio.setForeground(Color.black);
        btnHorariosLaboratorio.setFont(new Font("poppins",0,22));
        btnHorariosLaboratorio.setBorderPainted(false); // marco del boton no seleccionado
        btnHorariosLaboratorio.setFocusPainted(false); // nombre del boton no seleccionado
        btnHorariosLaboratorio.setFocusable(false); // elimina el foco cuando hay un click
        panelLeft.add(btnHorariosLaboratorio);
        
        //------------------------------------------
        //BOTON (CERRAR SESION) - ESTE BOTON ESTA DENTRO DEL PANEL LEFT / IZQUIERDO
        btnCerrarSesion = new JButton("Cerrar Sesion");
        btnCerrarSesion.setBounds(50,730,200,70);
        btnCerrarSesion.addActionListener(this);  
        btnCerrarSesion.setBackground(new Color(233,113,50));
        btnCerrarSesion.setForeground(Color.white);
        btnCerrarSesion.setFont(new Font("poppins",1,16));
        btnCerrarSesion.setBorderPainted(false);// marco del boton no seleccionado
        btnCerrarSesion.setFocusPainted(false); // nombre del boton no seleccionado
        panelLeft.add(btnCerrarSesion);
        //--------------------------------
   

//TITULO DE LA VENTANA DE CONTROL DE ASISTENCIA (AQUI COMIENZA EL PANEL-RIGHT 1)     

        //NOMBRE/TITULO PRINCIPAL DEL PANEL-RIGHT1
        lbRegistrosUsuarios = new JLabel("REGISTRO DE USUARIOS");
        lbRegistrosUsuarios.setBounds(50,30,590,52);
        lbRegistrosUsuarios.setFont(new Font("poppins",1,50));
        lbRegistrosUsuarios.setBackground(Color.green);
        lbRegistrosUsuarios.setOpaque(true);
        panelRight1.add(lbRegistrosUsuarios);
        
        
        
//---------------------------ELEMENTOS DEL PANEL RIGHT - 1 DE REGISTRO USUARIOS --------------------------       
        
        //-----------------------------------------------------------------------------
        //ETIQUETA LABEL - DATOS DEL REGSITRO (INDICA QUE HAY QUE REGISTRAR DATOS)
        nombreSubPanel1_1 = new JLabel("DATOS DEL REGISTRO", SwingConstants.CENTER);
        nombreSubPanel1_1.setBounds(100, 110, 500, 50); // Ajustar posición para que esté superpuesta
        nombreSubPanel1_1.setBackground(colorHoverSeleccionado1);
        nombreSubPanel1_1.setOpaque(true);
        nombreSubPanel1_1.setFont(new Font("poppins", Font.BOLD, 30));
        nombreSubPanel1_1.setForeground(Color.white);
        panelRight1.add(nombreSubPanel1_1);
        //-----------------------------------------------------------------------------
        //SUBPANEL1 DEL PANEL DERECHO 1 / RIGHT 1 - REGISTROS DE USUSARIOS
        subPanel1 = new JPanel();
        subPanel1.setLayout(null);
        subPanel1.setBounds(100, 180, 1090, 270); 
        subPanel1.setBackground(Color.white);
        subPanel1.setBorder(border);
        panelRight1.add(subPanel1); 
        //-----------------------------------------------------------------------------
        //ETIQEUTAS Y CUADROS DE TEXTO DE NOMBRES, APELLIDOS, TIPO DE DOCUEMNTO,
        //NRO. DOCUMENTO, NRO. CONTACTO, CARGO, USUARIO, CONTRASEÑA, EMAIL
        
        lbNombres = new JLabel("Nombres");
        lbNombres.setBounds(30, 20, 200, 30);
        lbNombres.setFont(new Font("poppins", Font.BOLD, 20));
        lbNombres.setForeground(colorHoverSeleccionado1);
        subPanel1.add(lbNombres);

        txtNombres = new JTextField();
        txtNombres.setBounds(30, 50, 300, 30);
        txtNombres.setFont(new Font("poppins", Font.PLAIN, 18));
        txtNombres.setBorder(border1);
        subPanel1.add(txtNombres);

        lbApellidos = new JLabel("Apellidos");
        lbApellidos.setBounds(30, 100, 200, 30);
        lbApellidos.setFont(new Font("poppins", Font.BOLD, 20));
        lbApellidos.setForeground(colorHoverSeleccionado1);
        subPanel1.add(lbApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(30, 130, 300, 30);
        txtApellidos.setFont(new Font("poppins", Font.PLAIN, 18));
        txtApellidos.setBorder(border1);
        subPanel1.add(txtApellidos);

        lbUsuario = new JLabel("Usuario");
        lbUsuario.setBounds(30, 180, 200, 30); // Ajustar para la tercera fila en la primera columna
        lbUsuario.setFont(new Font("poppins", Font.BOLD, 20));
        lbUsuario.setForeground(colorHoverSeleccionado1);
        subPanel1.add(lbUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(30, 210, 300, 30);
        txtUsuario.setFont(new Font("poppins", Font.PLAIN, 18));
        txtUsuario.setBorder(border1);
        subPanel1.add(txtUsuario);


        lbTipoDeDocumento = new JLabel("Tipo de Documento");
        lbTipoDeDocumento.setBounds(435, 20, 210, 30);
        lbTipoDeDocumento.setFont(new Font("poppins", Font.BOLD, 20));
        lbTipoDeDocumento.setForeground(colorHoverSeleccionado1);
        subPanel1.add(lbTipoDeDocumento);
        
        cbTipoDeDocumento = new JComboBox<>();
        cbTipoDeDocumento.setBounds(435, 50, 210, 30);
        cbTipoDeDocumento.setBorder(border1);
        cbTipoDeDocumento.addItem("");
        cbTipoDeDocumento.addItem("DNI");
        cbTipoDeDocumento.addItem("PASAPORTE");
        subPanel1.add(cbTipoDeDocumento);
        /*
        txtTipoDeDocumento = new JTextField();
        txtTipoDeDocumento.setBounds(435, 50, 210, 30);
        txtTipoDeDocumento.setFont(new Font("poppins", Font.PLAIN, 18));
        txtTipoDeDocumento.setBorder(border1);
        subPanel1.add(txtTipoDeDocumento);*/

        lbNumeroDeContacto = new JLabel("Nro. de Contacto");
        lbNumeroDeContacto.setBounds(435, 100, 210, 30);
        lbNumeroDeContacto.setFont(new Font("poppins", Font.BOLD, 20));
        lbNumeroDeContacto.setForeground(colorHoverSeleccionado1);
        subPanel1.add(lbNumeroDeContacto);

        txtNumeroDeContacto = new JTextField();
        txtNumeroDeContacto.setBounds(435, 130, 210, 30);
        txtNumeroDeContacto.setFont(new Font("poppins", Font.PLAIN, 18));
        txtNumeroDeContacto.setBorder(border1);
        subPanel1.add(txtNumeroDeContacto);

        lbContraseña = new JLabel("Contraseña");
        lbContraseña.setBounds(435, 180, 210, 30); // Ajustar para la tercera fila en la segunda columna
        lbContraseña.setFont(new Font("poppins", Font.BOLD, 20));
        lbContraseña.setForeground(colorHoverSeleccionado1);
        subPanel1.add(lbContraseña);

        txtContraseña = new JTextField();
        txtContraseña.setBounds(435, 210, 210, 30);
        txtContraseña.setFont(new Font("poppins", Font.PLAIN, 18));
        txtContraseña.setBorder(border1);
        subPanel1.add(txtContraseña);


        lbNumeroDeDocumento = new JLabel("Nro. de Documento");
        lbNumeroDeDocumento.setBounds(730, 20, 250, 30);
        lbNumeroDeDocumento.setFont(new Font("poppins", Font.BOLD, 20));
        lbNumeroDeDocumento.setForeground(colorHoverSeleccionado1);
        subPanel1.add(lbNumeroDeDocumento);

        txtNumeroDeDocumento = new JTextField();
        txtNumeroDeDocumento.setBounds(730, 50, 300, 30);
        txtNumeroDeDocumento.setFont(new Font("poppins", Font.PLAIN, 18));
        txtNumeroDeDocumento.setBorder(border1);
        subPanel1.add(txtNumeroDeDocumento);

        //JComboBox cbTipoDeDocumento, cbCargo;
        lbCargo = new JLabel("Cargo");
        lbCargo.setBounds(730, 100, 250, 30);
        lbCargo.setFont(new Font("poppins", Font.BOLD, 20));
        lbCargo.setForeground(colorHoverSeleccionado1);
        subPanel1.add(lbCargo);
        /**
         * 
        **/
        cbCargo = new JComboBox<>();
        cbCargo.setBounds(730, 130, 300, 30);
        cbCargo.setBorder(border1);
        cbCargo.addItem("");
        cbCargo.addItem("ADMINISTRADOR");
        cbCargo.addItem("DOCENTE");
        subPanel1.add(cbCargo);

        lbEmail = new JLabel("Email");
        lbEmail.setBounds(730, 180, 250, 30); // Ajustar para la tercera fila en la tercera columna
        lbEmail.setFont(new Font("poppins", Font.BOLD, 20));
        lbEmail.setForeground(colorHoverSeleccionado1);
        subPanel1.add(lbEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(730, 210, 300, 30);
        txtEmail.setFont(new Font("poppins", Font.PLAIN, 18));
        txtEmail.setBorder(border1);
        subPanel1.add(txtEmail);
        //-----------------------------------------------------------------------------
        // BOTONES AGREGAR, MODIFICAR Y ELIMINAR A LA HORA DE REGISTRAR USUARIOS

        btnAgregar = new JButton("AGREGAR");
        btnAgregar.setBounds(700, 110, 150, 50); // Ajustar posición para que esté superpuesta
        btnAgregar.setBackground(colorHoverSeleccionado1);
        btnAgregar.setOpaque(true);
        btnAgregar.setFont(new Font("poppins", Font.BOLD, 20));
        btnAgregar.setForeground(Color.white);
        btnAgregar.setBorderPainted(false); // marco del boton no seleccionado
        btnAgregar.setFocusPainted(false); // nombre del boton no seleccionado
        btnAgregar.addActionListener(this);
        panelRight1.add(btnAgregar);

        btnModificar = new JButton("MODIFICAR");
        btnModificar.setBounds(870, 110, 150, 50); // Ajustar posición para que esté superpuesta
        btnModificar.setBackground(colorHoverSeleccionado1);
        btnModificar.setOpaque(true);
        btnModificar.setFont(new Font("poppins", Font.BOLD, 20));
        btnModificar.setForeground(Color.white);
        btnModificar.setBorderPainted(false); // marco del boton no seleccionado
        btnModificar.setFocusPainted(false); // nombre del boton no seleccionado
        btnModificar.addActionListener(this);
        panelRight1.add(btnModificar);

        btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setBounds(1040, 110, 150, 50); // Ajustar posición para que esté superpuesta
        btnEliminar.setBackground(colorHoverSeleccionado1);
        btnEliminar.setOpaque(true);
        btnEliminar.setFont(new Font("poppins", Font.BOLD, 20));
        btnEliminar.setForeground(Color.white);
        btnEliminar.setBorderPainted(false); // marco del boton no seleccionado
        btnEliminar.setFocusPainted(false); // nombre del boton no seleccionado
        btnEliminar.addActionListener(this);
        panelRight1.add(btnEliminar);

//------------------------------------------------------------------------------------------
//-- EN ESTA PARTE COMIENZA EL SUB-PANEL 2 - DEL PANEL DERECHO / PANEL RIGHT 1
        //FRANSHECO CREA LA TABLA AQUI Y PONLE SCROLL CON CRUD GAAA

        nombreSubPanel1_2 = new JLabel("LISTA DE USUARIOS REGISTRADOS",SwingConstants.CENTER);
        nombreSubPanel1_2.setBounds(100,490,570,50);
        nombreSubPanel1_2.setBackground(colorHoverSeleccionado1);
        nombreSubPanel1_2.setOpaque(true);
        nombreSubPanel1_2.setFont(new Font("poppins",1,30));
        nombreSubPanel1_2.setForeground(Color.white);
        panelRight1.add(nombreSubPanel1_2);
        
        
        /*
        subPanel2 = new JPanel();
        subPanel2.setBounds(100,560,1090,270);
        subPanel2.setBackground(Color.white);
        subPanel2.setBorder(border);
        panelRight1.add(subPanel2);
        */
        
        String[] columnasUsuario = {"Id","Nombres", "Apellidos", "Tipo Doc.", "Nro. Doc.", "Numero", "Cargo", "Apodo", "Contrasena", "email"};
        modeloUsuario = new DefaultTableModel(columnasUsuario, 0);
        tablaUsuarios = new JTable(modeloUsuario);
        scrollSubPanel1_2 = new JScrollPane(tablaUsuarios);
        scrollSubPanel1_2.setBounds(100,560,1090,270);
        scrollSubPanel1_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollSubPanel1_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelRight1.add(scrollSubPanel1_2);
        
        
//TITULO DE LA VENTANA DE CONTROL DE ASISTENCIA (AQUI COMIENZA EL PANELRIGHT 2)
        lbControlAsistencia = new JLabel("CONTROL DE ASISTENCIA");
        lbControlAsistencia.setBounds(50,30,630,52);
        lbControlAsistencia.setFont(new Font("poppins",1,50));
        lbControlAsistencia.setBackground(Color.green);
        lbControlAsistencia.setOpaque(true);
        panelRight2.add(lbControlAsistencia);

    
        subPanel3 = new JPanel();
        subPanel3.setLayout(null);
        subPanel3.setBounds(100, 180, 1090, 270); 
        subPanel3.setBackground(Color.white);
        subPanel3.setBorder(border);
        panelRight2.add(subPanel3);

        subPanel4 = new JPanel();
        subPanel4.setLayout(null);
        subPanel4.setBounds(100,560,1090,270);
        subPanel4.setBackground(Color.white);
        subPanel4.setBorder(border);
        panelRight2.add(subPanel4); 
        
//TITULO DE LA VENTANA DE CONTROL DE ASISTENCIA (AQUI COMIENZA EL PANELRIGHT 3)
        lbControlEquipos = new JLabel("CONTROL DE EQUIPOS");
        lbControlEquipos.setBounds(50,30,590,52);
        lbControlEquipos.setFont(new Font("poppins",1,50));
        lbControlEquipos.setBackground(Color.green);
        lbControlEquipos.setOpaque(true);
        panelRight3.add(lbControlEquipos);
        
        subPanel5 = new JPanel();
        subPanel5.setLayout(null);
        subPanel5.setBounds(100, 180, 1090, 270); 
        subPanel5.setBackground(Color.white);
        subPanel5.setBorder(border);
        panelRight3.add(subPanel5);

        subPanel6 = new JPanel();
        subPanel6.setLayout(null);
        subPanel6.setBounds(100,560,1090,270);
        subPanel6.setBackground(Color.white);
        subPanel6.setBorder(border);
        panelRight3.add(subPanel6);
       
//TITULO DE LA VENTANA DE CONTROL DE ASISTENCIA (AQUI COMIENZA EL PANELRIGHT 4)
        lbHorariosLaboratorio = new JLabel("HORARIOS DE LABORATORIO");
        lbHorariosLaboratorio.setBounds(50,30,720,52);
        lbHorariosLaboratorio.setFont(new Font("poppins",1,50));
        lbHorariosLaboratorio.setBackground(Color.green);
        lbHorariosLaboratorio.setOpaque(true);
        panelRight4.add(lbHorariosLaboratorio);

       
        subPanel7 = new JPanel();
        subPanel7.setLayout(null);
        subPanel7.setBounds(100, 180, 1090, 270); 
        subPanel7.setBackground(Color.white);
        subPanel7.setBorder(border);
        panelRight4.add(subPanel7);

        subPanel8 = new JPanel();
        subPanel8.setLayout(null);
        subPanel8.setBounds(100,560,1090,270);
        subPanel8.setBackground(Color.white);
        subPanel8.setBorder(border);
        panelRight4.add(subPanel8); 
        
//-----------------------------------------------------------------------------    
        btnRegsitroUsuarios.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        // Cambia a color de hover solo si el botón no está seleccionado
            if (!boton1Seleccionado) {
                btnRegsitroUsuarios.setBackground(colorHoverSeleccionado1);
                btnRegsitroUsuarios.setForeground(Color.white);
            }
                btnRegsitroUsuarios.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
         }

        @Override
        public void mouseExited(MouseEvent e) {
        // Restaura el color base si el botón no está seleccionado
            if (!boton1Seleccionado) {
                btnRegsitroUsuarios.setBackground(colorBaseBotones);
                btnRegsitroUsuarios.setForeground(Color.black);
            }
                btnRegsitroUsuarios.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaura el cursor
            }
        });

        btnRegsitroUsuarios.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        // Cambia a color seleccionado y desmarca el otro botón
            boton1Seleccionado = true;
            boton2Seleccionado = false;
            boton3Seleccionado = false;
            boton4Seleccionado = false;
            btnRegsitroUsuarios.setBackground(colorHoverSeleccionado1);
            btnControlAsistencia.setBackground(colorBaseBotones);
            btnControlEquipos.setBackground(colorBaseBotones);
            btnHorariosLaboratorio.setBackground(colorBaseBotones);
            btnRegsitroUsuarios.setForeground(Color.white);
            btnControlAsistencia.setForeground(Color.black);
            btnControlEquipos.setForeground(Color.black);
            btnHorariosLaboratorio.setForeground(Color.black);

            }
        });
//-----------------------------------------------------------------------------    
        btnControlAsistencia.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        // Cambia a color de hover solo si el botón no está seleccionado
            if (!boton2Seleccionado) {
                btnControlAsistencia.setBackground(colorHoverSeleccionado1);
                btnControlAsistencia.setForeground(Color.white);
            }
                btnControlAsistencia.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
        }

        @Override
        public void mouseExited(MouseEvent e) {
        // Restaura el color base si el botón no está seleccionado
            if (!boton2Seleccionado) {
                btnControlAsistencia.setBackground(colorBaseBotones);
                btnControlAsistencia.setForeground(Color.black);
            }
                btnControlAsistencia.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaura el cursor
            }
        });

        btnControlAsistencia.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        // Cambia a color seleccionado y desmarca el otro botón
            boton1Seleccionado = false;
            boton2Seleccionado = true;
            boton3Seleccionado = false;
            boton4Seleccionado = false;
            btnRegsitroUsuarios.setBackground(colorBaseBotones);
            btnControlAsistencia.setBackground(colorHoverSeleccionado1);
            btnControlEquipos.setBackground(colorBaseBotones);
            btnHorariosLaboratorio.setBackground(colorBaseBotones);
            btnRegsitroUsuarios.setForeground(Color.black);
            btnControlAsistencia.setForeground(Color.white);
            btnControlEquipos.setForeground(Color.black);
            btnHorariosLaboratorio.setForeground(Color.black);

            }
        });
//-----------------------------------------------------------------------------    
        btnControlEquipos.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        // Cambia a color de hover solo si el botón no está seleccionado
            if (!boton3Seleccionado) {
                btnControlEquipos.setBackground(colorHoverSeleccionado1);
                btnControlEquipos.setForeground(Color.white);
            }
                btnControlEquipos.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
         }

        @Override
        public void mouseExited(MouseEvent e) {
        // Restaura el color base si el botón no está seleccionado
            if (!boton3Seleccionado) {
                btnControlEquipos.setBackground(colorBaseBotones);
                btnControlEquipos.setForeground(Color.black);
            }
                btnControlEquipos.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaura el cursor
            }
        });

        btnControlEquipos.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        // Cambia a color seleccionado y desmarca el otro botón
            boton1Seleccionado = false;
            boton2Seleccionado = false;
            boton3Seleccionado = true;
            boton4Seleccionado = false;
            btnRegsitroUsuarios.setBackground(colorBaseBotones);
            btnControlAsistencia.setBackground(colorBaseBotones);
            btnControlEquipos.setBackground(colorHoverSeleccionado1);
            btnHorariosLaboratorio.setBackground(colorBaseBotones);
            btnRegsitroUsuarios.setForeground(Color.black);
            btnControlAsistencia.setForeground(Color.black);
            btnControlEquipos.setForeground(Color.white);
            btnHorariosLaboratorio.setForeground(Color.black);

            }
        });
//-----------------------------------------------------------------------------    
        btnHorariosLaboratorio.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        // Cambia a color de hover solo si el botón no está seleccionado
            if (!boton4Seleccionado) {
                btnHorariosLaboratorio.setBackground(colorHoverSeleccionado1);
                btnHorariosLaboratorio.setForeground(Color.white);
            }
                btnHorariosLaboratorio.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
        }

        @Override
        public void mouseExited(MouseEvent e) {
        // Restaura el color base si el botón no está seleccionado
            if (!boton4Seleccionado) {
                btnHorariosLaboratorio.setBackground(colorBaseBotones);
                btnHorariosLaboratorio.setForeground(Color.black);
            }
                btnHorariosLaboratorio.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaura el cursor
            }
        });

        btnHorariosLaboratorio.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        // Cambia a color seleccionado y desmarca el otro botón
            boton1Seleccionado = false;
            boton2Seleccionado = false;
            boton3Seleccionado = false;
            boton4Seleccionado = true;
            btnRegsitroUsuarios.setBackground(colorBaseBotones);
            btnControlAsistencia.setBackground(colorBaseBotones);
            btnControlEquipos.setBackground(colorBaseBotones);
            btnHorariosLaboratorio.setBackground(colorHoverSeleccionado1);
            btnRegsitroUsuarios.setForeground(Color.black);
            btnControlAsistencia.setForeground(Color.black);
            btnControlEquipos.setForeground(Color.black);
            btnHorariosLaboratorio.setForeground(Color.white);

            }
        });
//-----------------------------------------------------------------------------   
        btnCerrarSesion.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        // Cambia a color de hover solo si el botón no está seleccionado
            
                btnCerrarSesion.setBackground(colorHoverSeleccionado2);
                btnCerrarSesion.setForeground(Color.black);
                btnCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
         }

        @Override
        public void mouseExited(MouseEvent e) {
        // Restaura el color base si el botón no está seleccionado
            
                btnCerrarSesion.setBackground(new Color(233,113,50));
                btnCerrarSesion.setForeground(Color.white);
                btnCerrarSesion.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaura el cursor
            }
        });
//-----------------------------------------------------------------------------
        btnAgregar.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        // Cambia a color de hover solo si el botón no está seleccionado
            
                btnAgregar.setBackground(colorHoverSeleccionado2);
                btnAgregar.setForeground(Color.black);
                btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
         }

        @Override
        public void mouseExited(MouseEvent e) {
        // Restaura el color base si el botón no está seleccionado
            
                btnAgregar.setBackground(new Color(233,113,50));
                btnAgregar.setForeground(Color.white);
                btnAgregar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaura el cursor
            }
        });
//-----------------------------------------------------------------------------    
        btnModificar.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        // Cambia a color de hover solo si el botón no está seleccionado
            
                btnModificar.setBackground(colorHoverSeleccionado2);
                btnModificar.setForeground(Color.black);
                btnModificar.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
         }

        @Override
        public void mouseExited(MouseEvent e) {
        // Restaura el color base si el botón no está seleccionado
            
                btnModificar.setBackground(new Color(233,113,50));
                btnModificar.setForeground(Color.white);
                btnModificar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaura el cursor
            }
        });
 //-----------------------------------------------------------------------------   
        btnEliminar.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        // Cambia a color de hover solo si el botón no está seleccionado
            
                btnEliminar.setBackground(colorHoverSeleccionado2);
                btnEliminar.setForeground(Color.black);
                btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
         }

        @Override
        public void mouseExited(MouseEvent e) {
        // Restaura el color base si el botón no está seleccionado
            
                btnEliminar.setBackground(new Color(233,113,50));
                btnEliminar.setForeground(Color.white);
                btnEliminar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaura el cursor
            }
        });
        //Tabla devuelva valores al seleccionar
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int filaSeleccionada = tablaUsuarios.getSelectedRow();
            if (filaSeleccionada != -1) { // Verifica que una fila esté seleccionada
                idUsuario = Integer.parseInt((modeloUsuario.getValueAt(filaSeleccionada, 0).toString()));
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
        });
    
        
        //Listar
        listarUsuario();
            
    }

//----------------------------------------------------------------------------- 
    // METODO PRINCIPAL - ESTA EN EL PROGRAMA PARA EJECUTAR MAS RAPIDO
    public static void main(String[] args){
        Ventana01RegsitrosDeUsuarios vtn = new Ventana01RegsitrosDeUsuarios();
        vtn.setVisible(true);
    }
//-----------------------------------------------------------------------------
    //ESTE ES EL ACTION LISTENER GENERAL
    //PARA OCULATAR Y MOSTRAR PANELES DERECHOS / PANELES RIGHT 1,2,3,4
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == btnCerrarSesion){
          QuieresCerrarSession window = new QuieresCerrarSession();
           window.setVisible(true);
           this.dispose();
       }
       if(e.getSource() == btnRegsitroUsuarios ){
           panelRight1.setVisible(true);
           panelRight2.setVisible(false);
           panelRight3.setVisible(false);
           panelRight4.setVisible(false);
       }
       if(e.getSource() == btnControlAsistencia ){
           panelRight1.setVisible(false);
           panelRight2.setVisible(true);
           panelRight3.setVisible(false);
           panelRight4.setVisible(false);
       }
       if(e.getSource() == btnControlEquipos ){
           panelRight1.setVisible(false);
           panelRight2.setVisible(false);
           panelRight3.setVisible(true);
           panelRight4.setVisible(false);
       }
       if(e.getSource() == btnHorariosLaboratorio ){
           panelRight1.setVisible(false);
           panelRight2.setVisible(false);
           panelRight3.setVisible(false);
           panelRight4.setVisible(true);
       }
        // Logica de agregar Uusuario
        if(e.getSource() == btnAgregar) {
           
            usuario = new Usuario();
           
            String nombre = txtNombres.getText();
            String apellido = txtApellidos.getText();
            String nombUsuario = txtUsuario.getText();
            String tipoDoc = cbTipoDeDocumento.getSelectedItem().toString();
            String numero = txtNumeroDeContacto.getText();
            String contrasena = txtContraseña.getText();
            String nroDoc = txtNumeroDeDocumento.getText();
            String cargo = cbCargo.getSelectedItem().toString();
            String email = txtEmail.getText();
           
            usuario.setNombres(nombre);
            usuario.setApellidos(apellido);
            usuario.setNombreUsuario(nombUsuario);
            usuario.setTipoDocumento(tipoDoc);
            usuario.setNumero(numero);
            usuario.setContrasena(contrasena);
            usuario.setNroDocumento(nroDoc);
            usuario.setCargo(cargo);
            usuario.setEmail(email);
            usuario.setIdUsuario(usuarioDAO.ultimoId() + 1);
           
            int estado = usuarioDAO.insertarUsuario(usuario);
            
            if (estado == 1) {
                JOptionPane.showMessageDialog(null, "Registro Insertado 🐧!!");
            } else {
                JOptionPane.showMessageDialog(null, "Registro no Insertado 🐧!!");
            }
            
            listarUsuario();
            
        }
        if (e.getSource() == btnModificar) {
            
            usuario = new Usuario();
            
            String nombre = txtNombres.getText();
            String apellido = txtApellidos.getText();
            String nombUsuario = txtUsuario.getText();
            String tipoDoc = cbTipoDeDocumento.getSelectedItem().toString();
            String numero = txtNumeroDeContacto.getText();
            String contrasena = txtContraseña.getText();
            String nroDoc = txtNumeroDeDocumento.getText();
            String cargo = cbCargo.getSelectedItem().toString();
            String email = txtEmail.getText();
            
            
            usuario.setNombres(nombre);
            usuario.setApellidos(apellido);
            usuario.setNombreUsuario(nombUsuario);
            usuario.setTipoDocumento(tipoDoc);
            usuario.setNumero(numero);
            usuario.setContrasena(contrasena);
            usuario.setNroDocumento(nroDoc);
            usuario.setCargo(cargo);
            usuario.setEmail(email);
            usuario.setIdUsuario(idUsuario);
            
            
            int estado = usuarioDAO.modificarUsuario(usuario);
            
            if (estado == 1) {
                JOptionPane.showMessageDialog(null, "Registro Modificado 🐧!!");
            } else {
                JOptionPane.showMessageDialog(null, "Registro no Modificado 🐧!!");
            }
            
            listarUsuario();
            
        }
        
        if (e.getSource() == btnEliminar) {
            
            usuario = new Usuario();
            
            usuario.setIdUsuario(idUsuario);
            
            int estado = usuarioDAO.eliminarUsuario(usuario);
            
            if (estado == 1) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado 🐧!!");
            } else {
                JOptionPane.showMessageDialog(null, "Registro no Eliminado 🐧!!");
            }
            
            listarUsuario();
            
        }
       
    }
    
    public void listarUsuario() {
        
        modeloUsuario.setRowCount(0);
        listaUsuarios = usuarioDAO.enlistarUsuario();
        
        for (Usuario usuarioTa: listaUsuarios) {
            modeloUsuario.addRow(new Object[] {
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
//-----------------------------------------------------------------------------
        // ESTE ES EL METODO CON EL CUAL OBTENEMOS EL NOMBRE DE USUARIO 
        // DIGITADO EN EL LOGIN
        
    public void setUser(String user) {
        lbNameUser.setText(user);  // Configura el texto del JLabel con el nombre de usuario
    }

    

 
}
