package proze.projekt;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Klasa Figury opisująca parametry i cykle figur
 */
public class Figury {
    /**
     * Zmienna wykorzystywana przy metodach cykl() oraz rysowaniu figur.
     */
    private double x=0;
    /**
     * Zmienna wykorzystywana przy metodach cykl() oraz rysowaniu figur.
     */
    private double y=0;
    /**
     * Zmienna wykorzystywana przy metodach cykl() oraz rysowaniu figur.
     */
    public int xP;
    /**
     * Zmienna wykorzystywana przy metodach cykl() oraz rysowaniu figur.
     */
    public int yP;

    private Image img;
    /**
     * Metoda Figury(x,y) opisuje cykle, z którymi poruszać się będą figury
     * @param x położenie na szerokości planszy
     * @param y położenie na wysokości planszy
     */
    Figury(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Metoda cykl1() sluzaca do okreslania polozenia figury na planszy
     */
    public void cykl1() {
        if(x>=xP+xP/20)
            x=-1;
        x=x+1;
    /**
    * Metoda cykl2() sluzaca do okreslania polozenia figury na planszy
    */
    }
    public void cykl2() {
        if(x<=-xP/20)
            x=xP+1;
        if(y>=yP+yP/20)
            y=0;
        y=y+1;
        x=x-1;
    }
    /**
     * Metoda cykl3() sluzaca do okreslania polozenia figury na planszy
     */
    public void cykl3() {
        if(y>=yP+yP/20)
            y=-1;
        y=y+1;
    }
    /**
     * Metoda cykl4() sluzaca do okreslania polozenia figury na planszy
     */
    public void cykl4() {
        if(y<=-yP/20)
            y=yP+1;
        y=y-1;
    }
    /**
     * Metoda cykl5() sluzaca do okreslania polozenia figury na planszy
     */
    public void cykl5() {
        if(x>=xP+xP/20)
            x=-1;
        if(y<=-yP/20)
            y=yP+1;
        x=x+1;
        y=y-1;
    }
    /**
     * Metoda cykl6() sluzaca do okreslania polozenia figury na planszy
     */
    public void cykl6() {
        if(x<=-xP/20)
            x=xP+1;
        if(y>=yP+yP/20)
            y=-1;
        x=x-1;
        y=y+1;
    }
    /**
     * Metoda cykl7() sluzaca do okreslania polozenia figury na planszy
     */
    public void cykl7() {
        if(x<=-xP/20)
            x=xP+1;
        if(y<=-yP/20)
            y=yP+1;
        x=x-1.5;
        y=y-1.5;
    }
    /**
     * Metoda cykl8() sluzaca do okreslania polozenia figury na planszy
     */
    public void cykl8() {
        if(x<=-xP/20)
            x=xP+1;
        if(y<=-yP/20)
            y=yP+1;
        x=x-1;
        y=y-1;
    }
    /**
     * Metoda cykl9() sluzaca do okreslania polozenia figury na planszy
     */
    public void cykl9() {
        if(x<=-xP/20)
            x=xP+1;
        if(y>=yP+yP/20)
            y=yP+1;
        x=x-1;
        y=y+1;
    }
    /**
     * Metoda cykl10() sluzaca do okreslania polozenia figury na planszy
     */
    public void cykl10() {
        if(x>=xP+xP/20)
            x=xP+1;
        if(y<=-yP/20)
            y=yP+1;
        x=x+1;
        y=y-1;
    }
    /**
     * Metoda cykl11() sluzaca do okreslania polozenia figury na planszy
     */
    public void cykl11() {
        if(x<=-xP/10)
            x=xP+1;
        if(y<=-yP/10)
            y=yP+1;
        x=x+1;
        y=y+1;
    }
    /**
     * Metoda cykl12() sluzaca do okreslania polozenia figury na planszy
     */
     public void cykl12() {
     if(x>=xP+xP/20)
         x = 0;
     x=x+3;
     if(y<=-yP/20)
         y= yP+1;
         y-=1;
     }

    /**
     * Metoda paint(), która odpowiada za rysowanie obiektów zgodnie z parametrami
     * @param g zmienna typu Graphics
     * @param panel JPanel Startu
     */
    public void paint(Graphics g, Start panel) {
        img = Toolkit.getDefaultToolkit().createImage("plikObiektu.jpg");
        Dimension size = panel.getSize();
        /**
         * Parametr zwracajacy wartosc uzywana do okreslenia szerokosci przeciwnikow
         */
        int szerokosc = (xP/10);
        /**
         * Parametr zwracajacy wartosc uzywana do okreslenia dlugosci przeciwnikow
         */
        int wysokosc = (yP/20);
        xP = size.width;
        yP = size.height;
        if (parametry.czyFiguryGeometryczne==true) {
            if (panel.param.figuraObiektuGry.contentEquals("kwadraty")) {
                g.setColor(Color.pink);
                g.fillRect((int) this.x, (int) this.y, szerokosc, szerokosc);
                g.drawRect((int) this.x, (int) this.y, szerokosc, szerokosc);

            } else if (panel.param.figuraObiektuGry.contentEquals("kółka")) {
                g.setColor(Color.pink);
                g.fillOval((int) this.x, (int) this.y, szerokosc, szerokosc);
                g.drawOval((int) this.x, (int) this.y, szerokosc, szerokosc);
            } else if (panel.param.figuraObiektuGry.equals("trójkąty")) {
                g.setColor(Color.pink);
                g.fillPolygon(new int[]{(int) this.x, (int) this.x + (szerokosc / 2), (int) this.x + wysokosc},
                        new int[]{(int) this.y + szerokosc, (int) this.y, (int) this.y + wysokosc}, 3);
                g.drawPolygon(new int[]{(int) this.x, (int) this.x + (szerokosc / 2), (int) this.x + wysokosc},
                        new int[]{(int) this.y + szerokosc, (int) this.y, (int) this.y + wysokosc}, 3);

            } else if (panel.param.figuraObiektuGry.equals("prostokąty")) {
                g.setColor(Color.pink);
                g.fillRect((int) this.x, (int) this.y, szerokosc, wysokosc);
                g.drawRect((int) this.x, (int) this.y, szerokosc, wysokosc);
            }
        }
        else if (parametry.czyFiguryGeometryczne==false) {
            g.setColor(Color.pink);
            g.fillRect((int) this.x, (int) this.y, szerokosc, wysokosc);
            g.drawRect((int) this.x, (int) this.y, szerokosc, wysokosc);
        }
    }

    /**
     * Metoda czyTrafiony(xm,ym) o typie boolean sprawdza, czy wprowadzone parametry zawierają się w parametrach figur.
     * Metoda ta używana jest, gdy należy sprawdzić, czy kliknięcie myszką w obiekt w celu jego zbicia pozwala na te zbicie.
     * @param xm szerokość figury
     * @param ym wysokość figury
     * @return wartosci true lub false
     */

    public boolean czyTrafiony(int xm, int ym, Start panel) {
        if (panel.param.czyFiguryGeometryczne == true) {
            if (panel.param.figuraObiektuGry.equals("prostokąty")) {
                if (xm >= x && xm <= x + (xP / 10) && ym >= y && ym <= y + (yP / 10))
                    return true;
            } else if (panel.param.figuraObiektuGry.equals("kwadraty")) {
                if (xm >= x && xm <= x + (xP / 10) && ym >= y && ym <= y + (yP / 4.25))
                    return true;
            } else if (panel.param.figuraObiektuGry.equals("kółka")) {
                if (xm >= x && xm <= x + (xP / 10) && ym >= y && ym <= y + (yP / 4.25))
                    return true;
            } else if (panel.param.figuraObiektuGry.equals("trójkąty")) {
                if (xm >= x && xm <= x + (xP / 20) && ym >= y && ym <= y + (yP / 4.25))
                    return true;
            }
        } else if (parametry.czyFiguryGeometryczne == false) {
            if (xm >= x && xm <= x + (xP / 10) && ym >= y && ym <= y + (yP / 10)) {
                return true;
            }
        }
        return false;
    }
}

