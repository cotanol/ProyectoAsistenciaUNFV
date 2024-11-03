/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author brigi
 */
public class CustomMouseAdapters {
    // Adapter para cambiar el fondo y texto en botones de menú
    public static class BotonMenuMouseAdapter extends MouseAdapter {
        private JButton boton;
        private Color colorBase;
        private Color colorHover;
        private Color colorTextoBase;
        private Color colorTextoHover;

        public BotonMenuMouseAdapter(JButton boton, Color colorBase, Color colorHover, Color colorTextoBase, Color colorTextoHover) {
            this.boton = boton;
            this.colorBase = colorBase;
            this.colorHover = colorHover;
            this.colorTextoBase = colorTextoBase;
            this.colorTextoHover = colorTextoHover;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            boton.setBackground(colorHover);
            boton.setForeground(colorTextoHover);
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            boton.setBackground(colorBase);
            boton.setForeground(colorTextoBase);
            boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    // Adapter para cambiar el fondo y texto en botones de acción
    public static class BotonAccionMouseAdapter extends MouseAdapter {
        private JButton boton;
        private Color colorBase;
        private Color colorHover;
        private Color colorTextoBase;
        private Color colorTextoHover;

        public BotonAccionMouseAdapter(JButton boton, Color colorBase, Color colorHover, Color colorTextoBase, Color colorTextoHover) {
            this.boton = boton;
            this.colorBase = colorBase;
            this.colorHover = colorHover;
            this.colorTextoBase = colorTextoBase;
            this.colorTextoHover = colorTextoHover;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            boton.setBackground(colorHover);
            boton.setForeground(colorTextoHover);
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            boton.setBackground(colorBase);
            boton.setForeground(colorTextoBase);
            boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
}
