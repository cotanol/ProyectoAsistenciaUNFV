/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class ComponentFactory {
    // Colores y fuentes pueden ser referenciados desde aquí
    private static final Color COLOR_BASE_BOTONES = new Color(255, 152, 0);
    private static final Color COLOR_HOVER_SELECCIONADO1 = new Color(233, 113, 50);
    private static final Color COLOR_TEXTO_BLANCO = Color.WHITE;
    private static final Color COLOR_TEXTO_NEGRO = Color.BLACK;
    private static final Font FUENTE_MENU = new Font("Poppins", Font.PLAIN, 22);
    private static final Font FUENTE_BOTON = new Font("Poppins", Font.BOLD, 20);
    private static final Font FUENTE_TEXTFIELD = new Font("Poppins", Font.PLAIN, 18);

    public static JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(COLOR_BASE_BOTONES);
        boton.setForeground(COLOR_TEXTO_NEGRO);
        boton.setFont(FUENTE_MENU);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        return boton;
    }

    public static JButton crearBotonAccion(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto);
        boton.setBackground(COLOR_HOVER_SELECCIONADO1);
        boton.setForeground(COLOR_TEXTO_BLANCO);
        boton.setFont(FUENTE_BOTON);
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
        campoTexto.setFont(FUENTE_TEXTFIELD);
        campoTexto.setBorder(border);
        return campoTexto;
    }
}
