package ProyectoAsistencia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class RegistroProgApliB extends JFrame implements ActionListener {

    JTable tablaAsistencia;
    JButton btnGuardar, btnVolver;

    // Estructura global para almacenar la asistencia (Código -> Días de Asistencia)
    static Map<String, Boolean[]> asistenciaData = new HashMap<>();  // Static para persistencia

    public RegistroProgApliB() {
        setTitle("Control de Asistencia - Prog. Aplicada (B)");
        setSize(1200, 500);  // Ajusta el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("CONTROL DE ASISTENCIA - PROG. APLICADA (B)", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 1000, 30);
        add(lblTitulo);

        // Modelo de tabla con columnas separadas para Código y Apellidos/Nombres
        String[] columnas = {"Código", "Apellidos y Nombres", "03/09", "05/09", "10/09", "12/09", "17/09", "19/09", "24/09", "26/09", 
                             "01/10", "03/10", "08/10", "10/10", "15/10", "17/10", "22/10", "24/10", "29/10", "31/10", 
                             "05/11", "07/11", "12/11", "14/11", "19/11", "21/11", "26/11", "28/11", "03/12", "05/12", 
                             "10/12", "12/12", "17/12", "19/12", "24/12", "26/12", "31/12"};

        Object[][] datos = {
                {"2023023522", "Aguilar Bendezú Richard Kofi", getAsistencia("2023023522", 0), getAsistencia("2023023522", 1), getAsistencia("2023023522", 2), getAsistencia("2023023522", 3)},
                {"2023023531", "Alcántara Sedano Patrick Alexander", getAsistencia("2023023531", 0), getAsistencia("2023023531", 1), getAsistencia("2023023531", 2), getAsistencia("2023023531", 3)},
                {"2023034489", "Alvarado Palacios Mac Arthur", getAsistencia("2023034489", 0), getAsistencia("2023034489", 1), getAsistencia("2023034489", 2), getAsistencia("2023034489", 3)},
                {"2023023585", "Arroyo De La Cruz Álvaro Helí", getAsistencia("2023023585", 0), getAsistencia("2023023585", 1), getAsistencia("2023023585", 2), getAsistencia("2023023585", 3)},
                {"2023035664", "Avalos Amoretti Rubí Valentina", getAsistencia("2023035664", 0), getAsistencia("2023035664", 1), getAsistencia("2023035664", 2), getAsistencia("2023035664", 3)},
                {"2022016452", "Bautista Ayala Piero Valentino", getAsistencia("2022016452", 0), getAsistencia("2022016452", 1), getAsistencia("2022016452", 2), getAsistencia("2022016452", 3)},
                {"2023034498", "Beltrán Ñahuincopa Luis Ángel", getAsistencia("2023034498", 0), getAsistencia("2023034498", 1), getAsistencia("2023034498", 2), getAsistencia("2023034498", 3)},
                {"2018025191", "Bernal Zuñiga Jean Paul", getAsistencia("2018025191", 0), getAsistencia("2018025191", 1), getAsistencia("2018025191", 2), getAsistencia("2018025191", 3)},
                {"2021018488", "Casavilca Carlos Neil Josué", getAsistencia("2021018488", 0), getAsistencia("2021018488", 1), getAsistencia("2021018488", 2), getAsistencia("2021018488", 3)},
                {"2023023647", "Castro More Jesús Anthony", getAsistencia("2023023647", 0), getAsistencia("2023023647", 1), getAsistencia("2023023647", 2), getAsistencia("2023023647", 3)},
                {"2023023683", "Chamorro Cueva Jazzir Robert", getAsistencia("2023023683", 0), getAsistencia("2023023683", 1), getAsistencia("2023023683", 2), getAsistencia("2023023683", 3)},
                {"2023023727", "Farfán Sánchez Josué Miguel", getAsistencia("2023023727", 0), getAsistencia("2023023727", 1), getAsistencia("2023023727", 2), getAsistencia("2023023727", 3)},
                {"2023023754", "Gamboa Espinoza Said Raúl", getAsistencia("2023023754", 0), getAsistencia("2023023754", 1), getAsistencia("2023023754", 2), getAsistencia("2023023754", 3)},
                {"2023023816", "Grijalva Aquino Sebastián Rosel", getAsistencia("2023023816", 0), getAsistencia("2023023816", 1), getAsistencia("2023023816", 2), getAsistencia("2023023816", 3)},
                {"2023023825", "Guerra Ríos Joselyn Alexandra", getAsistencia("2023023825", 0), getAsistencia("2023023825", 1), getAsistencia("2023023825", 2), getAsistencia("2023023825", 3)},
                {"2023036171", "Joaquín Caballero Grace Madeleine", getAsistencia("2023036171", 0), getAsistencia("2023036171", 1), getAsistencia("2023036171", 2), getAsistencia("2023036171", 3)},
                {"2023023941", "León Chuquitay Gianfranco Vincenzo", getAsistencia("2023023941", 0), getAsistencia("2023023941", 1), getAsistencia("2023023941", 2), getAsistencia("2023023941", 3)},
                {"2023023959", "Llashag Antonio Mateo", getAsistencia("2023023959", 0), getAsistencia("2023023959", 1), getAsistencia("2023023959", 2), getAsistencia("2023023959", 3)},
                {"2019002406", "Lozano Jauregui Cynthia Lucía", getAsistencia("2019002406", 0), getAsistencia("2019002406", 1), getAsistencia("2019002406", 2), getAsistencia("2019002406", 3)},
                {"2023023968", "Maldonado Salvatierra Sebastián Andre", getAsistencia("2023023968", 0), getAsistencia("2023023968", 1), getAsistencia("2023023968", 2), getAsistencia("2023023968", 3)},
                {"2023024011", "Núñez Aguirre Pool", getAsistencia("2023024011", 0), getAsistencia("2023024011", 1), getAsistencia("2023024011", 2), getAsistencia("2023024011", 3)},
                {"2023024038", "Padilla Morales David", getAsistencia("2023024038", 0), getAsistencia("2023024038", 1), getAsistencia("2023024038", 2), getAsistencia("2023024038", 3)},
                {"2022000643", "Poma Crispin Dayana Margarita", getAsistencia("2022000643", 0), getAsistencia("2022000643", 1), getAsistencia("2022000643", 2), getAsistencia("2022000643", 3)},
                {"2023024109", "Ramos Roncal Anthony Gonzalo", getAsistencia("2023024109", 0), getAsistencia("2023024109", 1), getAsistencia("2023024109", 2), getAsistencia("2023024109", 3)},
                {"2021018202", "Rivera Tuesta Álvaro Daniel", getAsistencia("2021018202", 0), getAsistencia("2021018202", 1), getAsistencia("2021018202", 2), getAsistencia("2021018202", 3)},
                {"2023024154", "Salcedo Lazo Fredd Jhordan", getAsistencia("2023024154", 0), getAsistencia("2023024154", 1), getAsistencia("2023024154", 2), getAsistencia("2023024154", 3)},
                {"2023024163", "Salcedo Morales Francisco José", getAsistencia("2023024163", 0), getAsistencia("2023024163", 1), getAsistencia("2023024163", 2), getAsistencia("2023024163", 3)},
                {"2023024216", "Santisteban Tineo Neptali Roy Sebastian", getAsistencia("2023024216", 0), getAsistencia("2023024216", 1), getAsistencia("2023024216", 2), getAsistencia("2023024216", 3)},
                {"2023024225", "Siquita Cerezo Eduar", getAsistencia("2023024225", 0), getAsistencia("2023024225", 1), getAsistencia("2023024225", 2), getAsistencia("2023024225", 3)},
                {"2022016407", "Taboada Almeyda Luis Eduardo", getAsistencia("2022016407", 0), getAsistencia("2022016407", 1), getAsistencia("2022016407", 2), getAsistencia("2022016407", 3)},
                {"2023024243", "Tristan Uturunco Carlos Eduardo", getAsistencia("2023024243", 0), getAsistencia("2023024243", 1), getAsistencia("2023024243", 2), getAsistencia("2023024243", 3)},
                {"2023024279", "Ventocilla Cornejo Krisdein Abigail", getAsistencia("2023024279", 0), getAsistencia("2023024279", 1), getAsistencia("2023024279", 2), getAsistencia("2023024279", 3)},
                {"2022022442", "Vásquez Antonio Claudio Enrique", getAsistencia("2022022442", 0), getAsistencia("2022022442", 1), getAsistencia("2022022442", 2), getAsistencia("2022022442", 3)},
                {"2023024305", "Zamora Rodríguez Enzo", getAsistencia("2023024305", 0), getAsistencia("2023024305", 1), getAsistencia("2023024305", 2), getAsistencia("2023024305", 3)},
                {"2023024314", "Zárate Zavaleta Marco Alexandre", getAsistencia("2023024314", 0), getAsistencia("2023024314", 1), getAsistencia("2023024314", 2), getAsistencia("2023024314", 3)}
        };

        DefaultTableModel model = new DefaultTableModel(datos, columnas) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 0 || column == 1 ? String.class : Boolean.class;
            }
        };

        tablaAsistencia = new JTable(model);
        tablaAsistencia.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el autoajuste para que funcione el scroll horizontal
        tablaAsistencia.getColumnModel().getColumn(0).setPreferredWidth(100); // Ajusta el ancho de la columna de Códigos
        tablaAsistencia.getColumnModel().getColumn(1).setPreferredWidth(300); // Ajusta el ancho de la columna de Apellidos y Nombres

        // Añadir el JScrollPane para que tenga scroll horizontal y vertical
        JScrollPane scrollPane = new JScrollPane(tablaAsistencia);
        scrollPane.setBounds(50, 70, 1100, 300);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        // Botón Guardar
        btnGuardar = new JButton("GUARDAR");
        btnGuardar.setBounds(400, 400, 120, 30);
        btnGuardar.addActionListener(this);
        add(btnGuardar);

        // Botón Volver
        btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(550, 400, 120, 30);
        btnVolver.addActionListener(this);
        add(btnVolver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            guardarAsistencia();  // Llama al método para guardar la asistencia
        } else if (e.getSource() == btnVolver) {
            // Volver al menú anterior
            this.dispose();  // Cierra la ventana actual
            Lab2Asistencia lab2 = new Lab2Asistencia();  // Regresa al menú de Laboratorio 2
            lab2.setVisible(true);
        }
    }

    // Método para obtener la asistencia guardada o devolver FALSE si no hay datos
    private Boolean getAsistencia(String codigo, int index) {
        Boolean[] asistencia = asistenciaData.get(codigo);
        if (asistencia != null) {
            return asistencia[index];
        } else {
            return Boolean.FALSE;
        }
    }

    // Método para guardar la asistencia
    private void guardarAsistencia() {
        DefaultTableModel model = (DefaultTableModel) tablaAsistencia.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String codigo = model.getValueAt(i, 0).toString();  // Obtener el código
            Boolean[] asistencia = new Boolean[36];  // 36 días de asistencia

            // Obtener los valores de las casillas marcadas
            for (int j = 0; j < 36; j++) {
                asistencia[j] = (Boolean) model.getValueAt(i, j + 2);
            }

            // Guardar la asistencia en el mapa
            asistenciaData.put(codigo, asistencia);
        }

        // Confirmación de guardado
        JOptionPane.showMessageDialog(this, "Asistencia guardada correctamente.");
    }

    public static void main(String[] args) {
        RegistroProgApliB registroAsistencia = new RegistroProgApliB();
        registroAsistencia.setVisible(true);
    }
}
