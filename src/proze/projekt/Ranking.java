package proze.projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;

/**
 * Klasa Ranking przechowujaca informacje o 10 najlepszych wynikach rozgrywek.
 */
public class Ranking extends JPanel {
    /**
     * JPanel rankingu, na ktorym znajduja sie elementy GUI zwiazane z nim.
     */
    private JPanel ranking;
    /**
     * Zmienna okreslajaca pole przedstawiajace tytul
     */
    private JLabel rankingLabel;
    /**
     * Przycisk powrotu do menu
     */
    private JButton backMenu;
    /**
     * Zmienna okreslajaca, czy bedziemy zapisywac nowy wynik, czy tylko otwierac liste, aby przejrzec istiejace wyniki
     */
    public boolean czyzapisujemy;
    /**
     * Zmienna w ktorej przechowywana jest nick gracza, który aktualnie zakończyl gre
     */
    public String nickdozapisu;
    /**
     * Zmienna w ktorej przechowywana jest liczba punktow gracza, który aktualnie zakończyl gre
     */
    public int wynikdozapisu;
    /**
     * Zmienna okreslajaca pole przedstawiajace pierwszy wynik
     */
    private JLabel wynik1=null;
    /**
     * Zmienna okreslajaca pole przedstawiajace drugi wynik
     */
    private JLabel wynik2=null;
    /**
     * Zmienna okreslajaca pole przedstawiajace trzeci wynik
     */
    private JLabel wynik3=null;
    /**
     * Zmienna okreslajaca pole przedstawiajace czwarty wynik
     */
    private JLabel wynik4=null;
    /**
     * Zmienna okreslajaca pole przedstawiajace piaty wynik
     */
    private JLabel wynik5=null;
    /**
     * Zmienna okreslajaca pole przedstawiajace szosty wynik
     */
    private JLabel wynik6=null;
    /**
     * Zmienna okreslajaca pole przedstawiajace siodmy wynik
     */
    private JLabel wynik7=null;
    /**
     * Zmienna okreslajaca pole przedstawiajace osmy wynik
     */
    private JLabel wynik8=null;
    /**
     * Zmienna okreslajaca pole przedstawiajace dziewiaty wynik
     */
    private JLabel wynik9=null;
    /**
     * Zmienna okreslajaca pole przedstawiajace dziesiaty wynik
     */
    private JLabel wynik10=null;
    /**
     * Tablica przechowujaca liste 10 najlepszych punktow wynikow
     */
    int[] tablicapunktow=new int[10];
    /**
     * Tablica przechowujaca liste 10 najlepszych nickow ktore odpowiadaja najlepszym wynikom
     */
    String[] tablicanickow=new String[10];
    /**
     * Metoda parsująca liczbę w postaci String na liczbę w postaci int
     * @param xVal wartości w String
     * @return wartość w int
     */
    private int ConvertIntoNumeric(String xVal)
    {
        try
        {
            return Integer.parseInt(xVal);
        }
        catch(Exception ex)
        {
            return 0;
        }
    }



    public Ranking(Start start, Menu menu) {

        czyzapisujemy = start.czyzapis;
        nickdozapisu = menu.nick;
        wynikdozapisu = start.sumaPunktow;
        tablicapunktow= start.tablicaPunktowGry;
        tablicanickow = start.tablicaNickowGry;

        /**
         * Wczytanie listy wynikow z pliku do tablicy z nickami i tablicy z punktami
         */
        try {
            FileReader fr = new FileReader("wyniki.txt");
            BufferedReader br = new BufferedReader(fr);
            String nickdozapisu;
            int punktydozapisu;
            String liniapliku;
            for (int i = 0; i < 10; i++) {
                liniapliku = br.readLine();
                String[] dane = liniapliku.split(" ");
                System.out.println(dane[0]);
                punktydozapisu = ConvertIntoNumeric(dane[0]);
                nickdozapisu = dane[1];
                tablicapunktow[i] = punktydozapisu;
                tablicanickow[i] = nickdozapisu;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Błąd zapisu do pliku..");
        }
        catch (NumberFormatException e){
            System.out.println("not a number");
        }

            GridLayout layout = new GridLayout(12, 1, 1, 0);
            layout.setVgap(5);
            setLayout(layout);
            rankingLabel = new JLabel();
            add(rankingLabel);
            rankingLabel.setText("Wyniki");
            rankingLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
            rankingLabel.setHorizontalAlignment(JLabel.CENTER);
            wynik1 = new JLabel();
            add(wynik1);
            wynik1.setText("1: " + tablicanickow[0] + "   " + tablicapunktow[0]);
            wynik1.setHorizontalAlignment(JLabel.CENTER);
            wynik2 = new JLabel();
            add(wynik2);
            wynik2.setText("2: " + tablicanickow[1] + "   " + tablicapunktow[1]);
            wynik2.setHorizontalAlignment(JLabel.CENTER);
            wynik3 = new JLabel();
            add(wynik3);
            wynik3.setText("3: " + tablicanickow[2] + "   " + tablicapunktow[2]);
            wynik3.setHorizontalAlignment(JLabel.CENTER);
            wynik4 = new JLabel();
            add(wynik4);
            wynik4.setText("4: " + tablicanickow[3] + "   " + tablicapunktow[3]);
            wynik4.setHorizontalAlignment(JLabel.CENTER);
            wynik5 = new JLabel();
            add(wynik5);
            wynik5.setText("5: " + tablicanickow[4] + "   " + tablicapunktow[4]);
            wynik5.setHorizontalAlignment(JLabel.CENTER);
            wynik6 = new JLabel();
            add(wynik6);
            wynik6.setText("6: " + tablicanickow[5] + "   " + tablicapunktow[5]);
            wynik6.setHorizontalAlignment(JLabel.CENTER);
            wynik7 = new JLabel();
            add(wynik7);
            wynik7.setText("7: " + tablicanickow[6] + "   " + tablicapunktow[6]);
            wynik7.setHorizontalAlignment(JLabel.CENTER);
            wynik8 = new JLabel();
            add(wynik8);
            wynik8.setText("8: " + tablicanickow[7] + "   " + tablicapunktow[7]);
            wynik8.setHorizontalAlignment(JLabel.CENTER);
            wynik9 = new JLabel();
            add(wynik9);
            wynik9.setText("9: " + tablicanickow[8] + "   " + tablicapunktow[8]);
            wynik9.setHorizontalAlignment(JLabel.CENTER);
            wynik10 = new JLabel();
            add(wynik10);
            wynik10.setText("10: " + tablicanickow[9] + "    " + tablicapunktow[9]);
            wynik10.setHorizontalAlignment(JLabel.CENTER);

            add(ranking);
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
