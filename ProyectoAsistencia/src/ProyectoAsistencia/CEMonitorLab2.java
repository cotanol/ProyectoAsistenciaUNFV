package ProyectoAsistencia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CEMonitorLab2 extends JFrame implements ActionListener {

    JTable tablaMonitores;
    JButton btnGuardar, btnVolver;

    // Datos para el código patrimonial y número de serie (Lab 2)
    String[] codPatrimonial = {
            "180090", "180092", "180096", "180097", "180099", "180102", "180104", "180107", "180109",
            "180110", "180115", "180123", "180129", "180133", "180142", "180144", "180155", "180169", "180170", "180172"
    };

    String[] numSerie = {
            "MXL3493958", "MXL349395C", "MXL349393T", "MXL3493934", "MXL3493933", "MXL349394K", "MXL3493960",
            "MXL349393V", "MXL349396C", "MXL349398H", "MXL349397G", "MXL3493916", "MXL349393C", "MXL3493978",
            "MXL349397W", "MXL3493992", "MXL349393M", "MXL349393G", "MXL349396T", "MXL3493979"
    };

    // Opciones de estado para los monitores
    String[] estados = {
            "Operativo", "Fallas de Video", "Sin Imagen", "Parpadeo Intermitente", "Descolorido", "Pixeles Muertos",
            "Pantalla Rota", "Problemas de Conexión", "Sobrecalentamiento", "Sin Encendido"
    };

    public CEMonitorLab2() {
        setTitle("Control de Monitores - LAB 2");
        setSize(600, 500);  // Ajuste del tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("CONTROL DE MONITORES - LAB 2", SwingConstants.CENTER);
        lblTitulo.setBounds(150, 10, 300, 30);
        add(lblTitulo);

        // Tabla de monitores
        String[] columnas = {"Cod. Patrimonial", "Número de Serie", "Estado"};
        Object[][] datos = new Object[codPatrimonial.length][3];
        for (int i = 0; i < codPatrimonial.length; i++) {
            datos[i][0] = codPatrimonial[i];
            datos[i][1] = numSerie[i];
            datos[i][2] = estados[0];  // Estado inicial
        }

        DefaultTableModel model = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;  // Solo la columna "Estado" es editable
            }

            @Override
            public Class<?> getColumnClass(int column) {
                return column == 2 ? JComboBox.class : String.class;
            }
        };

        tablaMonitores = new JTable(model);

        // Agregar ComboBox como editor de la columna "Estado"
        JComboBox<String> comboBox = new JComboBox<>(estados);
        tablaMonitores.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox));

        tablaMonitores.setRowHeight(30);  // Altura de las filas
        JScrollPane scrollPane = new JScrollPane(tablaMonitores);
        scrollPane.setBounds(50, 50, 500, 300);
        add(scrollPane);

        // Botón Guardar
        btnGuardar = new JButton("GUARDAR");
        btnGuardar.setBounds(150, 400, 100, 30);
        btnGuardar.addActionListener(this);
        add(btnGuardar);

        // Botón Volver
        btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(350, 400, 100, 30);
        btnVolver.addActionListener(this);
        add(btnVolver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            // Implementación para guardar los cambios (lógica adicional)
            JOptionPane.showMessageDialog(this, "Cambios guardados exitosamente.");
        } else if (e.getSource() == btnVolver) {
            // Volver al menú de control de equipos
            this.dispose();
            MenuControlEquipos menu = new MenuControlEquipos();
            menu.setVisible(true);
        }
    }

    public static void main(String[] args) {
        CEMonitorLab2 ceMonitorLab2 = new CEMonitorLab2();
        ceMonitorLab2.setVisible(true);
    }
}
