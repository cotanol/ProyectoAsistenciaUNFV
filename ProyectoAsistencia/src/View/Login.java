
package View;

import Controller.UsuarioController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;

import Model.UsuarioModelo;
import java.util.ArrayList;

public class Login extends JFrame implements ActionListener {

    //TODAS LAS ETIQUETAS
    
    String user;
    JPanel panelHeader, panelMenu, panelImg;
    ImageIcon logo;
    JLabel imageLabel;
    
    JLabel lbUsuario, lbPassword;
    JTextField txtUsuario;
    JPasswordField txtPassword;
    JButton btnEntrar, btnSalir;
    
    
    public Login(){
        UIManager.put("Button.select", new Color(0, 0, 0, 0)); // Foco transparente de seleccion
        //CARACTERISTICAS DE LA VENTANA
        setSize(1000,600);
        setTitle("Login del Gestionador");
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //----------------------------------------------------------------------
        
        //PANEL HEADER - CARACTERISTICAS
        panelHeader = new JPanel();
        panelHeader.setLayout(null);
        panelHeader.setBounds(0,0,700,130);
        panelHeader.setBackground(new Color(233,113,50));
        add(panelHeader);

        //POSICIONAMIENTO DEL LOGO DE LA VILLARREAL
        logo = new ImageIcon(getClass().getResource("/Imagenes/logo_villarreal.png"));
        Image logoImage = logo.getImage(); // Obtener la imagen del ImageIcon
        Image scaledLogoImage = logoImage.getScaledInstance(240, 100, Image.SCALE_SMOOTH); // Escalar la imagen a 240x100
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage); // Crear un nuevo ImageIcon con la imagen escalada


        imageLabel = new JLabel(scaledLogoIcon); // Usar el ImageIcon escalado
        imageLabel.setLayout(null);
        imageLabel.setBounds(30, 15, 240, 100); // Posicionar el JLabel dentro del pane
        panelHeader.add(imageLabel);
        //---------------------------------------------------
        
        //PANEL MENU - PANEL COLOR GRIS CLARO - CARACTERISTICAS
        panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(0,130,700,570);
        panelMenu.setBackground(new Color(238,238,238));
        add(panelMenu);
        
        lbUsuario = new JLabel("INICIAR SESIÓN");
        lbUsuario.setBounds(50,40,230,45);
        lbUsuario.setOpaque(true);
        lbUsuario.setFont(new Font("poppins",1,30));
        panelMenu.add(lbUsuario);
        
        lbUsuario = new JLabel("USUARIO");
        lbUsuario.setBounds(50,95,200,26);
        lbUsuario.setFont(new Font("poppins",0,26));
        panelMenu.add(lbUsuario);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(50,130,230,35);
        txtUsuario.setFont(new Font("arial",0,20));
        txtUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro de 2 píxeles
        panelMenu.add(txtUsuario);
        
        lbPassword = new JLabel("CONTRASEÑA");
        lbPassword.setBounds(50,180,200,30);
        lbPassword.setFont(new Font("poppins",0,26));
        panelMenu.add(lbPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(50,215,230,35);
        txtPassword.setFont(new Font("arial",0,26));
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro de 2 píxeles
        panelMenu.add(txtPassword);
        
        btnEntrar = new JButton("ENTRAR");
        btnEntrar.setBounds(50,280,150,60);
        btnEntrar.setBackground(new Color(233,113,50));
        btnEntrar.setForeground(Color.white);
        btnEntrar.setFont(new Font("poppins",0,20));
        btnEntrar.setBorderPainted(false); // marco del boton no seleccionado (aspecto visula de seleccion anular)
        btnEntrar.setFocusPainted(false); // nombre del boton no seleccionado (aspecto visula de seleccion anular)
        btnEntrar.addActionListener(this);
        panelMenu.add(btnEntrar);
        
        btnEntrar.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
        }

        @Override
        public void mouseExited(MouseEvent e) {
            btnEntrar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaura el cursor
        }
        });
        
        btnSalir = new JButton("SALIR");
        btnSalir.setBounds(220,280,150,60);
        btnSalir.setBackground(new Color(233,113,50));
        btnSalir.setForeground(Color.white);
        btnSalir.setFont(new Font("poppins",0,20));
        btnSalir.setBorderPainted(false); // marco del boton no seleccionado (aspecto visula de seleccion anular)
        btnSalir.setFocusPainted(false); // nombre del boton no seleccionado (aspecto visula de seleccion anular)
        btnSalir.addActionListener(this);
        panelMenu.add(btnSalir);
        
        btnSalir.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        
                btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
         }

        @Override
        public void mouseExited(MouseEvent e) {
                btnSalir.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaura el cursor
            }
        });
        
        //----------------------------------------------------------------------
        
        //PANEL IMG- PANEL DE LA IMAGEN DE LA SEDE VILLARREAL
        panelImg = new JPanel();
        panelImg.setLayout(null);
        panelImg.setBounds(700,0,300,700);
        panelImg.setBackground(Color.green);
        add(panelImg);
        
        // Cargar la imagen de la universidad y escalarla para el panel
        ImageIcon imageIcon1 = new ImageIcon(getClass().getResource("/Imagenes/central_villarreal.jpg")); // Asegúrate que la ruta sea correcta
        Image image = imageIcon1.getImage();

        // Obtener la parte central de la imagen
        // Aquí puedes usar la imagen original y cortarla, pero si solo quieres escalar toda la imagen y que se enfoque en el centro:
        Image scaledImage1 = image.getScaledInstance(1000, 700, Image.SCALE_SMOOTH); // Escalar la imagen para que cubra el panel

        // Crear un nuevo ImageIcon con la imagen escalada
        ImageIcon scaledImageIcon1 = new ImageIcon(scaledImage1);

        // Añadir la imagen al JLabel
        JLabel imageLabel1 = new JLabel(scaledImageIcon1);
        imageLabel1.setBounds(0, 0, 300, 700); // La imagen cubrirá todo el panel

        // Añadir el JLabel al panel
        panelImg.add(imageLabel1);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSalir){
            QuieresSalirDelPrograma vtn = new QuieresSalirDelPrograma();
            vtn.setVisible(true);
        }
        if(e.getSource() == btnEntrar){
            Entrar();
        }
    }
    
    public void Entrar(){
        UsuarioController usuarioControlador = new UsuarioController();
        ArrayList<String> usu = new ArrayList<>();
        ArrayList<String> pass = new ArrayList<>();
        
        for (UsuarioModelo usuario: usuarioControlador.enlistarUsuarioController ()) {
            usu.add(usuario.getNombreUsuario());
            pass.add(usuario.getContrasena());
        }
        
        String admin = "admin";
        String passAdmin = "123";
        
        usu.add(admin);
        pass.add(passAdmin);
        
        
        boolean estado = false;
        String usuario = txtUsuario.getText();
        String clave = new String(txtPassword.getPassword());
        if(usuario.isBlank() || clave.isBlank()){
            VentanaErrorEnBlanco error = new VentanaErrorEnBlanco();
            error.setVisible(true);
        }else{
            
            for(int i=0;i<usu.size();i++){
                if(usu.get(i).equals(usuario) && pass.get(i).equals(clave)){
                    estado = true;
                    break;
                }
            }
            
            if(estado == false){
              VentanaErrorUserNoRegistrado error1 = new VentanaErrorUserNoRegistrado();
              error1.setVisible(true);  
            }
            
            if(estado == true){
                this.dispose();
                Ventana01RegistrosDeUsuarios vtn = new Ventana01RegistrosDeUsuarios();
                vtn.setUser(usuario);
                vtn.setVisible(true);
            }
            
        }
        
        
        
    }


}
