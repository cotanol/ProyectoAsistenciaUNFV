package ProyectoHorario;

import javax.swing.*;
import java.awt.*;

public class HorarioLab1 extends JFrame {

    public HorarioLab1() {
        setTitle("HORARIOS 2024-II");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para los títulos
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new GridLayout(2, 1));

        // Título principal
        JLabel lblTitulo = new JLabel("HORARIOS 2024-II", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(lblTitulo);

        // Subtítulo
        JLabel lblSubtitulo = new JLabel("HORARIO ACADÉMICO : LABORATORIO 01", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 18));
        panelTitulo.add(lblSubtitulo);

        // Añadir el panel de títulos en la parte superior
        add(panelTitulo, BorderLayout.NORTH);

        // Crear panel para la tabla
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new GridLayout(13, 7)); // Mantiene la estructura 13 filas, 7 columnas

        // Encabezados
        String[] headers = {"HORAS", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO"};
        for (String header : headers) {
            JLabel lblHeader = new JLabel(header, SwingConstants.CENTER);
            lblHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panelTabla.add(lblHeader);
        }

        // Horarios
        String[] horas = {
            "08:00 - 08:50", "08:50 - 09:40", "09:40 - 11:20", "11:20 - 13:00", "13:00 - 14:40",
            "14:40 - 15:30", "15:30 - 16:20", "16:20 - 17:10", "17:10 - 18.50", "18:50 - 19:40",
            "20:30 - 21:20", "21:20 - 22:10"
        };

        // Llenar las horas en la primera columna
        for (String hora : horas) {
            JLabel lblHora = new JLabel(hora, SwingConstants.CENTER);
            lblHora.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panelTabla.add(lblHora);

            // Añadir celdas vacías para las demás columnas (6 columnas más)
            for (int j = 0; j < 6; j++) {
                JLabel lblVacio = new JLabel("", SwingConstants.CENTER);
                lblVacio.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panelTabla.add(lblVacio);
            }
        }

        // Rellenar con botones para cada curso en posiciones específicas
        Component[] components = panelTabla.getComponents();
        components[7 * 4 + 1] = createButton("PROGRAMACION APLICADA I", new Color(255, 204, 153));
        components[7 * 4 + 5] = createButton("PROGRAMACION APLICADA I", new Color(255, 204, 153));
        components[7 * 7 + 0] = createButton("TOPICOS ESPECIALES\nEN INTERNET DE LAS COSAS", new Color(189, 215, 238));
        components[7 * 8 + 0] = createButton("INTELIGENCIA DE NEGOCIOS", new Color(255, 255, 153));
        components[7 * 9 + 0] = createButton("INTELIGENCIA DE NEGOCIOS", new Color(255, 255, 153));
        components[7 * 5 + 6] = createButton("TALLER DE INTEGRACION\nDE SISTEMAS", new Color(198, 224, 180));

        // Añadir panel al contenedor principal
        add(panelTabla, BorderLayout.CENTER);
    }

    // Método para crear botones de cursos
    private JButton createButton(String text, Color color) {
        JButton btn = new JButton("<html><center>" + text.replace("\n", "<br>") + "</center></html>");
        btn.setBackground(color);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return btn;
    }

    public static void main(String[] args) {
        HorarioLab1 horario = new HorarioLab1();
        horario.setVisible(true);
    }
}
