package ProyectoAsistencia;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.*;

public class CECPULab1 extends JFrame implements ActionListener {

    JTable tablaEstaciones;
    JButton btnGuardar, btnVolver;

    // Listado de cod. patrimoniales y número de serie
    String[] codPatrimonial = {
        "MXL3493967", "MXL3493939", "MXL3493990", "MXL349398M", "MXL349398Q", "MXL349398C",
        "MXL349394G", "MXL3493937", "MXL349397P", "MXL3493923", "MXL3493984", "MXL349393X",
        "MXL349395B", "MXL3493963", "MXL3493985", "MXL349396M", "MXL3493986", "MXL349395J",
        "MXL3493969", "MXL349397H", "MXL349395Y", "MXL349393K", "MXL349395N", "MXL3493941",
        "MXL3493987", "MXL3493962", "MXL3493936", "MXL349394J", "MXL349395X", "MXL349398S",
        "MXL349391V", "MXL349393N", "MXL349393Q", "MXL3493966", "MXL349393J"
    };

    String[] numSerie = {
        "180105", "180112", "180113", "180114", "180120", "180125", "180126", "180127", 
        "180128", "180134", "180135", "180136", "180138", "180139", "180141", "180143", 
        "180145", "180147", "180153", "180156", "180157", "180158", "180161", "180163", 
        "180164", "180171", "180175", "180176", "180179", "180181", "180188", "180189", 
        "180191", "180157", "180153"
    };

    String[] estados = {
        "Operativo", "Falla de fuente de poder", "Falla de disco duro", 
        "Falla de memoria RAM", "Problema con la placa base (motherboard)", 
        "Falla de tarjeta gráfica (GPU)", "Problema de conectividad"
    };

    public CECPULab1() {
        setTitle("CONTROL DE ESTACIONES DE TRABAJO - LAB 1");
        setSize(800, 600); // Tamaño ajustado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("CONTROL DE ESTACIONES DE TRABAJO - LAB 1", SwingConstants.CENTER);
        lblTitulo.setBounds(150, 20, 500, 30);
        add(lblTitulo);

        // Modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Cod. Patrimonial");
        model.addColumn("Número de Serie");
        model.addColumn("Estado");

        // Llenar tabla con los datos
        for (int i = 0; i < codPatrimonial.length; i++) {
            model.addRow(new Object[]{codPatrimonial[i], numSerie[i], "Operativo"});
        }

        // Crear la tabla
        tablaEstaciones = new JTable(model);
        tablaEstaciones.setRowHeight(30);

        // Añadir JComboBox en la columna de Estado
        TableColumn estadoColumn = tablaEstaciones.getColumnModel().getColumn(2);
        JComboBox<String> comboBox = new JComboBox<>(estados);
        estadoColumn.setCellEditor(new DefaultCellEditor(comboBox));

        // Crear JScrollPane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaEstaciones);
        scrollPane.setBounds(50, 70, 700, 400);
        add(scrollPane);

        // Botón Guardar
        btnGuardar = new JButton("GUARDAR");
        btnGuardar.setBounds(250, 500, 120, 40);
        btnGuardar.addActionListener(this);
        add(btnGuardar);

        // Botón Volver
        btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(400, 500, 120, 40);
        btnVolver.addActionListener(this);
        add(btnVolver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            // Acción para guardar
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
        } else if (e.getSource() == btnVolver) {
            this.dispose();  // Cierra la ventana actual
            CEquiposLab1 lab1 = new CEquiposLab1();  // Vuelve a la ventana de LAB 1
            lab1.setVisible(true);
        }
    }

    public static void main(String[] args) {
        CECPULab1 frame = new CECPULab1();
        frame.setVisible(true);
    }
}
