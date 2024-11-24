package Util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EstiloHover {

    /**
     * Hover para botones generales.
     */
    public static class HoverMenuBoton extends MouseAdapter {
        private final JButton boton;

        public HoverMenuBoton(JButton boton) {
            this.boton = boton;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            boton.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
            boton.setForeground(Constantes.COLOR_TEXTO_BLANCO);
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor a mano
        }

        @Override
        public void mouseExited(MouseEvent e) {
            boton.setBackground(Constantes.COLOR_BASE_BOTONES);
            boton.setForeground(Constantes.COLOR_TEXTO_NEGRO);
            boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaurar cursor predeterminado
        }
    }

    /**
     * Hover para botones de acción (diferente color al pasar el mouse).
     */
    public static class HoverAccionBoton extends MouseAdapter {
        private final JButton boton;

        public HoverAccionBoton(JButton boton) {
            this.boton = boton;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            boton.setBackground(Constantes.COLOR_HOVER_SELECCIONADO2);
            boton.setForeground(Constantes.COLOR_TEXTO_NEGRO);
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor a mano
        }

        @Override
        public void mouseExited(MouseEvent e) {
            boton.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
            boton.setForeground(Constantes.COLOR_TEXTO_BLANCO);
            boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restaurar cursor predeterminado
        }
    }
}
