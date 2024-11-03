package Util;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class BotonMenuActionListener implements ActionListener {

    private String nombrePanel;
    private CardLayout cardLayout;
    private JPanel panelDerecho;

    public BotonMenuActionListener(String nombrePanel, CardLayout cardLayout, JPanel panelDerecho) {
        this.nombrePanel = nombrePanel;
        this.cardLayout = cardLayout;
        this.panelDerecho = panelDerecho;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Cambia al panel especificado en el CardLayout
        cardLayout.show(panelDerecho, nombrePanel);
    }
}