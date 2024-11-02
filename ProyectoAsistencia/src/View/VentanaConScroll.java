package View;

import javax.swing.*;
import java.awt.*;

public class VentanaConScroll extends JFrame {

    public VentanaConScroll() {
        // Configuración de la ventana
        setTitle("Panel con Scroll");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el panel que contendrá las etiquetas
        JPanel panelConEtiquetas = new JPanel();
        panelConEtiquetas.setLayout(null); // Usar Layout null para posicionar manualmente
        panelConEtiquetas.setPreferredSize(new Dimension(500, 1000)); // Dimensión mayor a 500 de alto

        // Agregar varias etiquetas más allá de los 500 píxeles de altura
        for (int i = 0; i < 20; i++) {
            JLabel label = new JLabel("Etiqueta " + (i + 1));
            label.setBounds(20, i * 50, 200, 30); // Posicionar cada etiqueta
            panelConEtiquetas.add(label);
        }

        // Crear JScrollPane con el panel
        JScrollPane scrollPane = new JScrollPane(panelConEtiquetas);
        scrollPane.setBounds(50, 50, 500, 500); // Establecer el área visible de 500x500
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Solo scroll vertical

        // Agregar JScrollPane a la ventana
        add(scrollPane);
        setLayout(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaConScroll ventana = new VentanaConScroll();
            ventana.setVisible(true);
        });
    }
}
