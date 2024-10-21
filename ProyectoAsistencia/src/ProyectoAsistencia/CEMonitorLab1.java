package ProyectoAsistencia;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;

public class CEMonitorLab1 extends JFrame implements ActionListener {

    JTable tablaMonitores;
    JButton btnGuardar, btnVolver;

    // Datos de ejemplo para el código patrimonial y número de serie
    String[] codPatrimonial = {
            "180105", "180112", "180113", "180114", "180120", "180125", "180126", "180127", "180128",
            "180134", "180135", "180136", "180138", "180139", "180141", "180143", "180145", "180147",
            "180153", "180156", "180157", "180158", "180161", "180163", "180164", "180171", "180175",
            "180176", "180179", "180181", "180188", "180189", "180191", "180157", "180153"
    };

    String[] numSerie = {
            "MXL3493967", "MXL3493939", "MXL3493990", "MXL349398M", "MXL349398Q", "MXL349398C",
            "MXL349394G", "MXL3493937", "MXL349397P", "MXL3493923", "MXL3493984", "MXL349393X",
            "MXL349395B", "MXL3493963", "MXL3493985", "MXL349396M", "MXL3493986", "MXL349395J",
            "MXL3493969", "MXL349397H", "MXL349395Y", "MXL349393K", "MXL349395N", "MXL3493941",
            "MXL3493987", "MXL3493962", "MXL3493936", "MXL349394J", "MXL349395X", "MXL349398S",
            "MXL349391V", "MXL349393N", "MXL349393Q", "MXL3493966", "MXL349393J"
    };

    // Opciones de estado para los monitores
    String[] estados = {
            "Operativo", "Fallas de Video", "Sin Imagen", "Parpadeo Intermitente", "Descolorido", "Pixeles Muertos",
            "Pantalla Rota", "Problemas de Conexión", "Sobrecalentamiento", "Sin Encendido"
    };

    public CEMonitorLab1() {
        setTitle("Control de Monitores - LAB 1");
        setSize(600, 500);  // Ajuste del tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("CONTROL DE MONITORES - LAB 1", SwingConstants.CENTER);
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
        CEMonitorLab1 ceMonitorLab1 = new CEMonitorLab1();
        ceMonitorLab1.setVisible(true);
    }
}
