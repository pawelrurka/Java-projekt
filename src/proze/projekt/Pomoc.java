package proze.projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa przechowująca ustawienia panelu Pomoc
 */
public class Pomoc extends JPanel {
    /**
     * JPanel pomocy
     */
    private JPanel pomoc;
    /**
     * Przycisk backMenu pozwalający na powrót do menu głównego
     */
    private JButton backMenu;
    /**
     * Zmienna okreslajaca pole przedstawiajace tytul panelu
     */
    private JLabel pomocLabel;

    public Pomoc() {
        pomocLabel = new JLabel();
        pomocLabel.setText("Aby zbić obiekty, wciśnij na niego przycisk myszy. Aby aktywować pauzę, należy wcisnąć pauzę");
        pomocLabel.setForeground(new Color(0, 0, 0));
        pomocLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(pomocLabel);
        add(pomoc);
        setOpaque(false);
        pomoc.setOpaque(false);
        /**
         * Listener obsługujący przycisk powrotu do menuObiekt
         */
        backMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                program.zmienPanel(program.dajObiekt(), 0);
            }
        });
    }
}
