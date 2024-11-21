
package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class VentanaConfirmacionCrudRegistroModificado extends JFrame implements ActionListener {
    JPanel panelPrincipal;
    JLabel lbRegistro, lbCrud,lberror1, lberror2, lberror3, lbImagen;
    JButton btnerror;
    ImageIcon image1, imageScalada;
    
    public VentanaConfirmacionCrudRegistroModificado(){
        UIManager.put("Button.select", new Color(0, 0, 0, 0));
        setSize(470,300);
        setTitle("Ventana de Confirmación: Registro Modificado");
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
        image1 = new ImageIcon("Images/ConstructorAmarillo.gif");
        imageScalada = new ImageIcon(image1.getImage().getScaledInstance(128, 167, Image.SCALE_DEFAULT));
        lbImagen = new JLabel(imageScalada);
        lbImagen.setBackground(Color.red);
        lbImagen.setOpaque(true);
        lbImagen.setBounds(270,10,128,167);
        panelPrincipal.add(lbImagen);
        //----------------------------
        lbRegistro = new JLabel("REGISTRO");
        lbRegistro.setLayout(null);
        lbRegistro.setBounds(0,60,300,30);
        lbRegistro.setForeground(Color.BLACK);
        lbRegistro.setFont(new Font("arial",1,30));
        lbRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        lbRegistro.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lbRegistro);
        
        lbCrud = new JLabel("MODIFICADO");
        lbCrud.setLayout(null);
        lbCrud.setBounds(0,95,300,30);
        lbCrud.setForeground(Color.BLACK);
        lbCrud.setFont(new Font("arial",1,30));
        lbCrud.setHorizontalAlignment(SwingConstants.CENTER);
        lbCrud.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lbCrud);
        
        btnerror = new JButton("Continuar");
        btnerror.setBounds(150,190,150,40);
        btnerror.setBackground(new Color(233,113,50));
        btnerror.setForeground(Color.white);
        btnerror.setFont(new Font("arial",1,17));
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
        VentanaConfirmacionCrudRegistroModificado error = new VentanaConfirmacionCrudRegistroModificado();
        error.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnerror){
            this.dispose();
        }
    }
}
