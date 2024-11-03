
package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VentanaErrorEnBlanco extends JFrame implements ActionListener{
    JPanel panelPrincipal;
    JLabel lberror, lberror1, lberror2, lberror3;
    JButton btnerror;
    
    public VentanaErrorEnBlanco(){
        setSize(310,250);
        setTitle("Error del Login");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //----------------------------
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setOpaque(true);
        panelPrincipal.setSize(310,250);
        panelPrincipal.setBackground(Color.white);
        add(panelPrincipal);
        //----------------------------
        lberror = new JLabel("ERROR");
        lberror.setLayout(null);
        lberror.setBounds(0,20,300,30);
        lberror.setForeground(Color.red);
        lberror.setFont(new Font("poppins",1,30));
        lberror.setHorizontalAlignment(SwingConstants.CENTER);
        lberror.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lberror);
        //-----------------------------
        lberror1 = new JLabel("''Campos en Blanco''");
        lberror1.setBounds(0,65,300,15);
        lberror1.setForeground(Color.black);
        lberror1.setFont(new Font("poppins",0,15));
        lberror1.setHorizontalAlignment(SwingConstants.CENTER);
        lberror1.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lberror1);
        //-------------------------------
        lberror2 = new JLabel("No puedes dejar");
        lberror2.setBounds(0,90,300,15);
        lberror2.setForeground(Color.black);
        lberror2.setFont(new Font("poppins",0,17));
        lberror2.setHorizontalAlignment(SwingConstants.CENTER);
        lberror2.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lberror2);
        //--------------------------------
        lberror3 = new JLabel("campos en BLANCO.");
        lberror3.setBounds(0,110,300,15);
        lberror3.setForeground(Color.black);
        lberror3.setFont(new Font("poppins",0,15));
        lberror3.setHorizontalAlignment(SwingConstants.CENTER);
        lberror3.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lberror3);
        
        btnerror = new JButton("Continuar");
        btnerror.setBounds(100,150,100,40);
        btnerror.addActionListener(this);
        panelPrincipal.add(btnerror);
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
