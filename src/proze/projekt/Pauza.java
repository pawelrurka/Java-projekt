package proze.projekt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Klasa przechowujaca operacje wykorzystywane do rozpoczęcia i zakończenia pauzy
 */
public class Pauza implements KeyListener {
    private boolean podczasPauzy = false;
    private long czasPauzy;

    /**
     * Metoda zwracajaca zmienna wartosc zmiennej poczaszPauzy
     * @return zmienna podczasPauzy
     */
    public boolean getPodczasPauzy() {
        return podczasPauzy;
    }

    /**
     * metoda zmieniajaca wartosc zmiennej podczasPauzy na wartosc przeciwna
     */
    public void setPodczasPauzy() {
        podczasPauzy = !podczasPauzy;
    }

    /**
     * Metoda zerujaca wartosc zmiennej czasPauzy
     */
    public void zerujCzasPauzy(){
        czasPauzy=0;
    }

    /**
     *  Metoda zwracajaca zmienna czasPauzy
     * @return zmienna czasPauzy
     */
    public long getCzasPauzy() {
        return czasPauzy;
    }

    /**
     * Ustawia wartosc czasu pauzy na wartosc timera nanosekund
     */
    public void setCzasPauzy() {
        this.czasPauzy = System.nanoTime();
    }

    /**
     * KeyEvent zwiazany z uruchamianiem pauzy.
     * @param e KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * KeyEvent aktywowany po wcisnieciu klawisza spacji.
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            setCzasPauzy();
            setPodczasPauzy();
        }
    }

    /**
     * Niewykorzystywany KeyEvent wymagany do optymalnego dzialania KeyEventu pauzy.
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
