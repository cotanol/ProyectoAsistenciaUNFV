package ProyectoAsistencia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CETecladoLab1 extends JFrame implements ActionListener {

    JTable tablaTeclados;
    JButton btnGuardar, btnVolver;

    // Datos de ejemplo para el código patrimonial y número de serie
    String[] codPatrimonial = {
            "180437", "180444", "180445", "180446", "180452", "180457", "180458", "180459", "180460",
            "180466", "180467", "180468", "180470", "180471", "180473", "180475", "180477", "180479",
            "180485", "180488", "180489", "180490", "180493", "180495", "180496", "180497", "180503",
            "180507", "180508", "180511", "180513", "180520", "180521", "180523", "180429"
    };

    String[] numSerie = {
            "7CL34000MK", "9CT33809A5", "7CL34000I4", "7CL34000HC", "7CL34000K2", "7CL34000I6",
            "7CL34000AC", "7CL34000HQ", "7CL34000DD", "7CL34000G9", "7CL34000M4", "7CL34000A5",
            "7CL34000JY", "7CL3400083", "7CL34000FF", "7CL34000MG", "7CL34000H5", "7CL34000J5",
            "7CL34000MM", "7CL34000H3", "7CL34000JS", "7CL34000LJ", "7CL34000AN", "9CT33809AA",
            "7CL34000H0", "7CL34000LM", "7CL34000F4", "7CL3400095", "7CL34000G4", "7CL340007M",
            "7CL34000FE", "7CL34000AM", "7CL34000GW", "7CL34000HN", "7CL34000I7"
    };

    // Opciones de estado para los teclados
    String[] estados = {
            "Operativo", "Teclas atascadas", "Falla de conexión", "Teclas no responden", "Teclas desgastadas", "Desconfigurado"
    };

    public CETecladoLab1() {
        setTitle("Control de Teclados - LAB 1");
        setSize(600, 500);  // Ajuste del tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("CONTROL DE TECLADOS - LAB 1", SwingConstants.CENTER);
        lblTitulo.setBounds(150, 10, 300, 30);
        add(lblTitulo);

        // Tabla de teclados
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

        tablaTeclados = new JTable(model);

        // Agregar ComboBox como editor de la columna "Estado"
        JComboBox<String> comboBox = new JComboBox<>(estados);
        tablaTeclados.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox));

        tablaTeclados.setRowHeight(30);  // Altura de las filas
        JScrollPane scrollPane = new JScrollPane(tablaTeclados);
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
            CEquiposLab1 menu = new CEquiposLab1();
            menu.setVisible(true);
        }
    }

    public static void main(String[] args) {
        CETecladoLab1 ceTecladoLab1 = new CETecladoLab1();
        ceTecladoLab1.setVisible(true);
    }
}
