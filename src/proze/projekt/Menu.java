package proze.projekt;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa przechowująca ustawienia panelu Menu
 */
public class Menu extends JPanel {
    private JPanel menu;
    private JLabel tytul;

    private JButton pomocButton;
    private JButton rankingButton;
    private JButton startButton;
    private JButton wyjścieButton;
    private JButton opcjeButton;

    private Ranking nowyRanking;

    public static String nick;

    /**
     * Sluży wyświetlaniu przycisków pozwalających na przejście do kolejnych etapów gry/części projektu
     */

    public Menu() {
        menu.setOpaque(false);
        setOpaque(false);
        add(tytul);
        add(menu);
        tytul.setVisible(true);
        menu.setVisible(true);

        /**
         * Listener obsługujący przycisk start
         */
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sNick = JOptionPane.showInputDialog("Podaj swój nick:");
                if(!sNick.isEmpty()) {
                    nick = sNick;

                    program.zmienPanel(program.dajObiekt(), 1);
                }
                if(sNick.isEmpty()){
                    System.out.println("wpisz nick!");
                }
                else
                    System.out.println(" ");
            }
        });

        /**
         * Listener obsługujący przycisk opcjeObiekt
         */
        opcjeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                program.zmienPanel(program.dajObiekt(), 2);
            }
        });

        /**
         * Listener obsługujący przycisk rankingObiekt
         */
        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                program.zmienPanel(program.dajObiekt(), 3);
            }
        });

        /**
         * Listener obsługujący przycisk wyjścia
         */
        wyjścieButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /**
         * Listener obsługujący przycisk pomocObiekt
         */
        pomocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                program.zmienPanel(program.dajObiekt(), 4);
            }
        });

    }

    public Ranking getNowyRanking() {
        return nowyRanking;
    }


}
