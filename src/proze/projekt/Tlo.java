package proze.projekt;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa przechowująca ustawienia panelu tła
 */
public class Tlo extends JPanel {
    /**
     * Zmienna wykorzystywana w celu wczytania odpowiedniego pliku w postaci zdjęcia na tlo.
     */
    Image img;

    /**
     * Metoda pobierająca obraz z dysku
     */
    public Tlo() {
        img = Toolkit.getDefaultToolkit().createImage("plik.jpg");
    }

    /**
     *Metoda rysująca obrazek
     * @param g
     */
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     *Metoda ustawiająca obraz na tlo
     * @param g
     */
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0,getWidth(), getHeight(), this);
        super.paint(g);
    }
}