/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author brigi
 */
public class Constantes {
     // Colores
    public static final Color COLOR_BASE_BOTONES = new Color(255, 152, 0);
    public static final Color COLOR_HOVER_SELECCIONADO1 = new Color(233, 113, 50);
    public static final Color COLOR_HOVER_SELECCIONADO2 = new Color(255, 198, 66);
    public static final Color COLOR_TEXTO_BLANCO = Color.WHITE;
    public static final Color COLOR_TEXTO_NEGRO = Color.BLACK;
    public static final Color COLOR_FONDO_PANEL = new Color(238, 238, 238);

    // Fuentes
    public static final Font FUENTE_TITULO = new Font("arial", Font.BOLD, 50);
    public static final Font FUENTE_SUBTITULO = new Font("arial", Font.BOLD, 30);
    public static final Font FUENTE_LABEL = new Font("arial", Font.BOLD, 20);
    public static final Font FUENTE_TEXTFIELD = new Font("arial", Font.PLAIN, 18);
    public static final Font FUENTE_BOTON = new Font("arial", Font.BOLD, 20);
    public static final Font FUENTE_MENU = new Font("arial", Font.PLAIN, 22);

    // Bordes
    public static final Border BORDER_NEGRO = BorderFactory.createLineBorder(Color.BLACK, 2);
    public static final Border BORDER_HOVER = BorderFactory.createLineBorder(COLOR_HOVER_SELECCIONADO1, 2);
}
