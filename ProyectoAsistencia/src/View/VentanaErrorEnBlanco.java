package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class VentanaErrorEnBlanco extends JFrame implements ActionListener{
    JPanel panelPrincipal;
    JLabel lberror, lberror1, lberror2, lberror3, lbImagen;
    JButton btnerror;
    ImageIcon image1, imageScalada;
    
    public VentanaErrorEnBlanco(){
        UIManager.put("Button.select", new Color(0, 0, 0, 0));
        setSize(470,300);
        setTitle("Error del Login: Campos en Blanco");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //----------------------------
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setOpaque(true);
        panelPrincipal.setSize(470,300);
        panelPrincipal.setBackground(Color.white);
        add(panelPrincipal);
        //----------------------------
        image1 = new ImageIcon("Images/ConstructorTraje.gif");
        imageScalada = new ImageIcon(image1.getImage().getScaledInstance(128, 167, Image.SCALE_DEFAULT));
        lbImagen = new JLabel(imageScalada);
        lbImagen.setBackground(Color.red);
        lbImagen.setOpaque(true);
        lbImagen.setBounds(270,0,128,167);
        panelPrincipal.add(lbImagen);
        //----------------------------
        lberror = new JLabel("ERROR");
        lberror.setLayout(null);
        lberror.setBounds(0,35,300,30);
        lberror.setForeground(Color.red);
        lberror.setFont(new Font("poppins",1,30));
        lberror.setHorizontalAlignment(SwingConstants.CENTER);
        lberror.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lberror);
        //-----------------------------
        lberror1 = new JLabel("''Campos en Blanco''");
        lberror1.setBounds(0,85,300,15);
        lberror1.setForeground(Color.black);
        lberror1.setFont(new Font("poppins",0,15));
        lberror1.setHorizontalAlignment(SwingConstants.CENTER);
        lberror1.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lberror1);
        //-------------------------------
        lberror2 = new JLabel("No puedes dejar");
        lberror2.setBounds(0,105,300,15);
        lberror2.setForeground(Color.black);
        lberror2.setFont(new Font("poppins",0,17));
        lberror2.setHorizontalAlignment(SwingConstants.CENTER);
        lberror2.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lberror2);
        //--------------------------------
        lberror3 = new JLabel("campos en BLANCO.");
        lberror3.setBounds(0,125,300,15);
        lberror3.setForeground(Color.black);
        lberror3.setFont(new Font("poppins",0,15));
        lberror3.setHorizontalAlignment(SwingConstants.CENTER);
        lberror3.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lberror3);
        
        btnerror = new JButton("Continuar");
        btnerror.setBounds(150,190,150,40);
        btnerror.setBackground(new Color(233,113,50));
        btnerror.setForeground(Color.white);
        btnerror.setFont(new Font("poppins",1,17));
        btnerror.addActionListener(this);
        btnerror.setBorderPainted(false); 
        btnerror.setFocusPainted(false);
        panelPrincipal.add(btnerror);
        
        btnerror.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {

                btnerror.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
         }

        @Override
        public void mouseExited(MouseEvent e) {

                btnerror.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaura el cursor
            }
        });
    }
    
    public static void main(String[] args) {
        VentanaErrorEnBlanco error = new VentanaErrorEnBlanco();
        error.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnerror){
            this.dispose();
        }
    }
}