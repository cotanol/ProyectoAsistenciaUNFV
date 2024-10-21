package ProyectoAsistencia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CEPizarraLab1 extends JFrame implements ActionListener {

    JTable tablaPizarra;
    JButton btnGuardar, btnVolver;

    // Listado de cod. patrimonial
    String[] codPatrimonial = {"177690"};
    
    // Estados disponibles para la pizarra digital
    String[] estados = {
        "Operativa", 
        "Problema de calibración", 
        "No responde al tacto", 
        "Falla de conectividad (a la computadora o red)", 
        "Pantalla dañada", 
        "Software desactualizado o con fallos"
    };

    public CEPizarraLab1() {
        setTitle("CONTROL DE PIZARRA DIGITAL - LAB 1");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("CONTROL DE PIZARRA DIGITAL - LAB 1", SwingConstants.CENTER);
        lblTitulo.setBounds(100, 20, 400, 30);
        add(lblTitulo);

        // Modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Cod. Patrimonial");
        model.addColumn("Estado");

        // Llenar la tabla con los datos
        for (String cod : codPatrimonial) {
            model.addRow(new Object[]{cod, "Operativa"});
        }

        // Crear la tabla
        tablaPizarra = new JTable(model);
        tablaPizarra.setRowHeight(30);

        // Añadir JComboBox en la columna de Estado
        TableColumn estadoColumn = tablaPizarra.getColumnModel().getColumn(1);
        JComboBox<String> comboBox = new JComboBox<>(estados);
        estadoColumn.setCellEditor(new DefaultCellEditor(comboBox));

        // Crear JScrollPane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaPizarra);
        scrollPane.setBounds(50, 70, 500, 200);
        add(scrollPane);

        // Botón Guardar
        btnGuardar = new JButton("GUARDAR");
        btnGuardar.setBounds(150, 300, 120, 40);
        btnGuardar.addActionListener(this);
        add(btnGuardar);

        // Botón Volver
        btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(300, 300, 120, 40);
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
        CEPizarraLab1 frame = new CEPizarraLab1();
        frame.setVisible(true);
    }
}
