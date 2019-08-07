package proze.projekt; /**
 * Główna klasa programu
 */

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * JFrame programu. Zawiera wszystkie podrzędne JPanele
 */
public class program extends JFrame {
    /**
     * Deklaracja obiektu ramki
     */
    public static JFrame ramka = new JFrame("PROZE Maciejewska Rurka" );

    /**
     * Deklaracja obiektu panelu opcji
     */
    public static Opcje opcjeObiekt = new Opcje();

    /**
     * Deklaracja  obiektu panelu  pomocy
     */
    public static Pomoc pomocObiekt = new Pomoc();

    /**
     * Deklaracja obiektu panelu tła
     */
    public static Tlo tlo = new Tlo();

    /**
     * Deklaracja  obiektu panelu  menuObiekt
     */
    public static Menu menuObiekt = new Menu();

    /**
     * Deklaracja  obiektu panelu  startu
     */
    public static Start start = new Start(opcjeObiekt, ramka, menuObiekt);

    /**
     * Deklaracja obiektu panelu rankingu
     */

    public static Ranking rankingObiekt = new Ranking(start,menuObiekt);


    /**
     * Metoda zmieniająca panel wyświetlany w ramce.
     *
     * @param ramka zmiany paneli odbywają się w obszarze ramki
     */
    public static void zmienPanel(JFrame ramka, int x) {
        ramka.getContentPane().removeAll();
        ramka.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                start.czyObiektUmarl(e.getX(), e.getY(),start);
            }
        });
        switch (x) {
            case 0:
                menuObiekt.setVisible(true);
                start.setVisible(false);
                pomocObiekt.setVisible(false);
                opcjeObiekt.setVisible(false);
                rankingObiekt.setVisible(false);
                ramka.getContentPane().add(menuObiekt);
                break;
            case 1:
                menuObiekt.setVisible(false);
                start.setVisible(true);
                pomocObiekt.setVisible(false);
                opcjeObiekt.setVisible(false);
                rankingObiekt.setVisible(false);
                ramka.getContentPane().add(start);
                start.rozpocznij();
                break;
            case 2:
                ramka.getContentPane().add(opcjeObiekt);
                menuObiekt.setVisible(false);
                start.setVisible(false);
                pomocObiekt.setVisible(false);
                opcjeObiekt.setVisible(true);
                rankingObiekt.setVisible(false);
                break;
            case 3:
                ramka.getContentPane().add(rankingObiekt);
                menuObiekt.setVisible(false);
                start.setVisible(false);
                pomocObiekt.setVisible(false);
                opcjeObiekt.setVisible(false);
                rankingObiekt.setVisible(true);
                break;
            case 4:
                menuObiekt.setVisible(false);
                start.setVisible(false);
                pomocObiekt.setVisible(true);
                opcjeObiekt.setVisible(false);
                rankingObiekt.setVisible(false);
                ramka.getContentPane().add(pomocObiekt);
                break;
        }
        SwingUtilities.updateComponentTreeUI(ramka);
    }

    /**
     * Metoda zwracająca referencje na obiekt ramki
     *
     * @return ramka - nasz JFrame
     */
    public static JFrame dajObiekt() {

        return ramka;
    }

    /**
     * Funkcja main().
     *
     * @param args nieużywane
     */
    static String ip;

    public static void main(String[] args) throws IOException {
        parametry param = new parametry();
        program gra = new program();
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setSize(param.zwrocSzerokosc(), param.zwrocWysokosc());
        //ramka.setContentPane(tlo);
        zmienPanel(ramka, 0);
        ramka.setVisible(true);
    }
}
