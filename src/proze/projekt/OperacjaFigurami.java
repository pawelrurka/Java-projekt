package proze.projekt;
import java.util.LinkedList;
import java.awt.Graphics;

/**
 * Klasa OperacjaFigurami przechowująca metody operacji figurami
 */
public class OperacjaFigurami {
    /**
     * Lista przechowujaca wszystkie wywolane figury
     */
    public LinkedList<Figury> figury = new LinkedList<Figury>();

    /**
     * Deklaracja klasy Figury
     */
    private Figury f;

    /**
     * Metoda cykl(), która dobiera cykl do danej figury.
     */
    public void cykl()
    {
        for(int i = 0; i < figury.size(); i++)
        {
            f = figury.get(i);
             if(i%12==0)
             {f.cykl12();}
            else if(i%11==0)
             {f.cykl11();}
            else if(i%10==0)
             {f.cykl10(); }
            else if(i%9==0)
             {f.cykl9(); }
            else if(i%8==0)
             {f.cykl8(); }
            else if(i%7==0)
             {f.cykl7(); }
            else if(i%6==0)
             {f.cykl6(); }
            else if(i%5==0)
             {f.cykl5(); }
            else if(i%4==0)
             {f.cykl4(); }
            else if(i%3==0)
             {f.cykl3(); }
            else if(i%2==0)
             {f.cykl2(); }
            else
                f.cykl1();
        }
    }

    /**
     * Metoda paint() umożliwiająca nam namalowanie wybranej figury na planszę
     * @param
     * @param panel JPanel startu
     */
    public void paint(Graphics g, Start panel)
    {
        for(int i = 0; i < figury.size(); i++)
        {
            f = figury.get(i);
            f.paint(g,panel);

        }

    }

    /**
     * Metoda dodajPrzeciwnika() dodająca przeciwnika na planszę.
     * @param f lista figur
     */
    public void dodajPzeciwnika(Figury f)
    {
        figury.add(f);
    }

    /**
     * Metoda usunPrzeciwnika() eliminująca przeciwnika z planszy.
     * @param i indeks z listy figur, który ma zostać usunięty
     */
    public void usunPrzeciwnika(int i)
    {
        figury.remove(i);
    }


}
