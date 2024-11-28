package Util;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.time.LocalTime;

public class ComponentFactory {
    
    public static JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(Constantes.COLOR_BASE_BOTONES);
        boton.setForeground(Constantes.COLOR_TEXTO_NEGRO);
        boton.setFont(Constantes.FUENTE_MENU);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        return boton;
    }

    public static JButton crearBotonAccion(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto);
        boton.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        boton.setForeground(Constantes.COLOR_TEXTO_BLANCO);
        boton.setFont(Constantes.FUENTE_BOTON);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        return boton;
    }
    
    public static JButton crearBotonReporteExcel(String texto, int x, int y, int ancho, int alto){
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto);
        boton.setBackground(Constantes.COLOR_BOTON_EXCEL);
        boton.setForeground(Constantes.COLOR_TEXTO_BLANCO);
        boton.setFont(Constantes.FUENTE_BOTON);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        return boton;
    }
    
    public static JLabel crearEtiqueta(String texto, int x, int y, int ancho, int alto, Font fuente, Color colorTexto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setBounds(x, y, ancho, alto);
        etiqueta.setFont(fuente);
        etiqueta.setForeground(colorTexto);
        return etiqueta;
    }

    public static JTextField crearCampoTexto(int x, int y, int ancho, int alto, Border border) {
        JTextField campoTexto = new JTextField();
        campoTexto.setBounds(x, y, ancho, alto);
        campoTexto.setFont(Constantes.FUENTE_TEXTFIELD);
        campoTexto.setBorder(border);
        return campoTexto;
    }

    // Método para JComboBox de String
    public static JComboBox<String> crearComboBoxString(String[] opciones, int x, int y, int ancho, int alto, Border border) {
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        comboBox.setBounds(x, y, ancho, alto);
        comboBox.setFont(Constantes.FUENTE_TEXTFIELD);
        comboBox.setBorder(border);
        return comboBox;
    }

    // Método para JComboBox de Integer
    public static JComboBox<Integer> crearComboBoxInteger(Integer[] opciones, int x, int y, int ancho, int alto, Border border) {
        JComboBox<Integer> comboBox = new JComboBox<>(opciones);
        comboBox.setBounds(x, y, ancho, alto);
        comboBox.setFont(Constantes.FUENTE_TEXTFIELD);
        comboBox.setBorder(border);
        return comboBox;
    }

    // Método para JComboBox de LocalTime
    public static JComboBox<LocalTime> crearComboBoxLocalTime(LocalTime[] opciones, int x, int y, int ancho, int alto, Border border) {
        JComboBox<LocalTime> comboBox = new JComboBox<>(opciones);
        comboBox.setBounds(x, y, ancho, alto);
        comboBox.setFont(Constantes.FUENTE_TEXTFIELD);
        comboBox.setBorder(border);
        return comboBox;
    }

    public static JTable crearTabla(String[] columnas) {
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);
        tabla.setFont(Constantes.FUENTE_TEXTFIELD);
        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(Constantes.FUENTE_LABEL);
        tabla.setSelectionBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        tabla.setSelectionForeground(Constantes.COLOR_TEXTO_BLANCO);
        return tabla;
    }

    public static JScrollPane crearScrollTabla(JTable tabla, int x, int y, int ancho, int alto) {
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(x, y, ancho, alto);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        return scroll;
    }
}
