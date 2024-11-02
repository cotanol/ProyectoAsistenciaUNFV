
package View;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class QuieresCerrarSession extends JFrame implements ActionListener, MouseListener {
    
    JLabel lbpregunta, lbImagen;
    JButton btnSi, btnNo;
    ImageIcon image1, imageScalada;
    
    
    public QuieresCerrarSession(){
        setSize(600,400);
        setTitle("Ventana de Confirmación de Cierre de Session");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        
        UIManager.put("Button.select", new Color(0, 0, 0, 0));
        
        
        /*
        imagen2 = new ImageIcon("Images/user_icon.png");
        imagenEscala2 = new ImageIcon(imagen2.getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));
        
        lbImagenUser = new JLabel(imagenEscala2,SwingConstants.CENTER);
        lbImagenUser.setBounds(50,10,200,240);
        panelUser.add(lbImagenUser);
        */
        image1 = new ImageIcon("Images/real_baile.gif");
        imageScalada = new ImageIcon(image1.getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT));
        lbImagen = new JLabel(imageScalada);
        lbImagen.setBounds(190,70,200,200);
        add(lbImagen);
        
        lbpregunta = new JLabel("¿Realmente Quieres Cerrar Sessión?");
        lbpregunta.setFont(new Font("poppins",1,16));
        //lbpregunta.setBackground(Color.green);
        lbpregunta.setOpaque(true);
        lbpregunta.setBounds(145,30,310,30);
        add(lbpregunta);
        
        btnSi = new JButton("SI");
        btnSi.setBounds(140,300,100,30);
        btnSi.setBackground(new Color(233,113,50));
        btnSi.setForeground(Color.white);
        btnSi.setFont(new Font("poppins",1,22));
        btnSi.addActionListener(this);
        btnSi.setBorderPainted(false); 
        btnSi.setFocusPainted(false);
        add(btnSi);

        
        btnSi.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {

                btnSi.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
         }

        @Override
        public void mouseExited(MouseEvent e) {

                btnSi.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaura el cursor
            }
        });
        
        btnNo = new JButton("NO");
        btnNo.setBounds(330,300,100,30);
        btnNo.setBackground(new Color(233,113,50));
        btnNo.setForeground(Color.white);
        btnNo.setFont(new Font("poppins",1,22));
        btnNo.addActionListener(this);
        btnNo.setBorderPainted(false); 
        btnNo.setFocusPainted(false);
        add(btnNo);
        
        btnNo.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {

                btnNo.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a mano
         }

        @Override
        public void mouseExited(MouseEvent e) {

                btnNo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaura el cursor
            }
        });
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSi){
            this.dispose();
            Login vtn = new Login();
            vtn.setVisible(true);
            
        }
        
        if(e.getSource() == btnNo){
            this.dispose();
            Ventana01RegsitrosDeUsuarios vtn = new Ventana01RegsitrosDeUsuarios();
            vtn.setVisible(true);
        }
    }

    
    public static void main(String[] args){
        QuieresCerrarSession vtn = new QuieresCerrarSession();
        vtn.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
