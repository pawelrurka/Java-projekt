package proze.projekt;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Random;


import static proze.projekt.program.menuObiekt;
import static proze.projekt.program.opcjeObiekt;



/**
 * Klasa przechowująca informacje o rozpoczynającej się grze(o jej starcie)
 */
public class Start extends JPanel implements Runnable {
    /**
     * JPanel gry
     */
    private JPanel start;
    /**
     * Zmienna w ktorej przechowywany jest tytul gry.
     */
    private JLabel tytul;
    /**
     * Zmienna w ktorej przechowywany jest czas pozostaly do przejscia poziomu/gry.
     */
    private JLabel czas;
    /**
     * Zmienna w ktorej przechowywana jest suma punktow ze wszystkich etapow rozgrywki.
     */
    private JLabel punkty;
    /**
     * Zmienna w ktorej przechowywany jest numer aktywnego poziomu.
     */
    private JLabel level;
    /**
     * JFrame wykorzystywany przy Keylistenerze pauzy.
     */
    private JFrame ramek;
    /**
     * Zmienna w ktorej przechowywana jest liczba przeciwnikow pozostalych do zestrzelenia w danym poziomie.
     */
    private JLabel przeciwnicy;
    /**
     * zmienna wykorzystywana do zliczania punktow ze wszystkich poziomow oraz punktow dodatkowych.
     */
    public static int sumaPunktow;
    /**
     * Informuje o tym, czy powinnismy juz rozpoczac procedure zapisu do rankingu danego nicku i punktów.
     */
    public static boolean czyzapis=false;
    /**
     * Konstruktor klasy parametry().
     */
    parametry param = new parametry();
    /**
     * Zmienna wykorzystywana w timerze gry.
     */
    static int timer = 0;
    /**
     * Zmienna wykorzystywana w timerze pauzy gry.
     */
    static int timer2 = 0;
    /**
     * zmienna zliczajaca punkty (tylko) za zbite obiekty.
     */
    static int punktyGra = 0;
    /**
     * zmienna opisujaca poziom gry.
     */
    static int poziom = 1;
    /**
     * zmienna opisujaca stopien trudnosci gry.
     */
    public static int stopienTrudnosciGry;
    /**
     * zmienna warunkujaca rozpoczecie badz zatrzymanie petli gry.
     */
    private boolean gra = false;
    /**
     * zmienna warunkujaca rozpoczecie badz zatrzymanie pauzy gry.
     */
    private boolean pauza = true;
    /**
     * wyjątek, ktorego operacje uruchamiaja badz zatrzymuja gre.
     */
    private Thread wyjatek;
    /**
     * Deklaracja obiektu OperacjaFigurami()
     */
    private OperacjaFigurami wektor = new OperacjaFigurami();
    /**
     * zmienna informujaca petle gry o rozpoczeciu gry - pierwszym i kazdym kolejnym
     */
    private boolean poczatekGry = true;
    /**
     * Deklaracja funkcji Random()
     */
    Random rand = new Random();
    /**
     * zmienna ilosc przeciwnikow, ktorzy pojawia sie na planszy
     */
    int iloscPrzeciwnikow = rand.nextInt(11) + 5;
    /**
     * zmienna sluzaca do okreslenia rozmiaru Panelu
     */
    int xPanel;
    /**
     * zmienna sluzaca do okreslenia panelu
     */
    int yPanel;
    /**
     * zmienna wykorzystywana do ustalenia warunkow przejscia gry/ przejscia na kolejny poziom/ przegrania gry
     */
    public double czasGry;
    /**
     * deklaracja klasy Pauza
     */
    private Pauza nasluchiwacz;
    /**
     * Tablica przechowujaca liste 10 najlepszych punktow wynikow
     */
   public static int[] tablicaPunktowGry =new int[10];
    /**
     * Tablica przechowujaca liste 10 najlepszych nickow ktore odpowiadaja najlepszym wynikom
     */
    public static String[] tablicaNickowGry =new String[10];
    /**
     * Zmienna boolean sluzaca do odswiezania wczytanego rankingu.
     */
    public static boolean czyWczytaj;

    /**
     * Metoda parsująca liczbę w postaci String na liczbę w postaci int
     * @param xVal wartość w String
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
    private long czasRozpoczeciaPauzy = 0;
    /**
     * Inicjalizacja obrazu
     */
    Image img;

    /**
     * Metoda Start() uruchamia najważniejsze elementy w oknie Start takie jak tlo, tytuł, czy preferowany rozmiar okna.
     */
    public Start(Opcje opcjeObiekt, JFrame ramka, Menu menuObiekt) {
        ramek = ramka;
        add(start);
        addMouseListener(null);
        addMouseMotionListener(null);

        start.setVisible(true);
        setOpaque(false);
        start.setOpaque(false);

        tytul = new JLabel();
        add(tytul);
        tytul.setText(parametry.zwrocNazwe());
        tytul.setForeground(new Color(255, 255, 255));
            tytul.setFont(new Font("SansSerif", Font.BOLD, 40));

        tytul.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red));

        przeciwnicy = new JLabel();
        add(przeciwnicy);
        przeciwnicy.setForeground(new Color(51, 255, 231));
        przeciwnicy.setFont(new Font("SansSerif", Font.BOLD, 20));

        czas = new JLabel();
        add(czas);
        System.out.println(param.zwrocLiczbeStopniTrudnosci()+"a");
        setPreferredSize(new Dimension(param.zwrocSzerokosc(), param.zwrocWysokosc()));

        img = Toolkit.getDefaultToolkit().createImage("plik.jpg");
        punkty = new JLabel();
        add(punkty);

        level =  new JLabel();
        add(level);
        level.setForeground(new Color(17, 12, 200));
        level.setFont(new Font("Sanserif", Font.BOLD,20));

        stopienTrudnosciGry=opcjeObiekt.stopienTrudnosci;
    }

    /**
     * metoda zwracajaca parametr stopnia trudnosi uzyskany z klasy Opcje
     * @param opcjeObiekt obiekt panelu opcji
     * @return parametr stopnia trudnosci
     */
    public static int zwrocStopienTrudnosci(Opcje opcjeObiekt){
            stopienTrudnosciGry = opcjeObiekt.stopienTrudnosci;
            return stopienTrudnosciGry;

    }
    /**
     * metoda rysująca tło wraz z obiektami
     * @param g
     */
    public void paintComponent(Graphics g) {
        Dimension size = getSize();
        xPanel = size.width;
        yPanel = size.height;
        g.drawImage(img, 0, 0, size.width, size.height, this);
        wektor.paint(g, this);
    }
    /**
     * Metoda rozpocznij() uruchamiająca proces gry.
     */
    public synchronized void rozpocznij() {
        if (gra) {
            return;
        }
        gra = true;
        wyjatek = new Thread(this);
        wyjatek.start();
    }
    /**
     * Metoda zatrzymująca grę.
     */
    public synchronized void zakoncz() {
        if (!gra)
            return;
        gra = false;

            wyjatek.stop();
    }
    /**
     * Metoda run() odpowiedzialna za uruchuchamianie animacji.
     */
    public void run() {
        /**
         * Timer okreslajacy czas rozpoczecia gry
         */
        long czasRozpoczecia = System.nanoTime();
        /**
         * Zmienna wykorzystywana w liczenia zmiennej ns
         */
        final double iloscCykli = 60.0;
        /**
         * Zmienna sluzaca okresleniu sekundy
         */
        double roznica = 0;
        /**
         * Zmienna sluzaca okresleniu
         */
        double  roznica2 = 0;
        double czasWPauzie = 0;
        double czasPoczatkowy1 = 40 - param.zmianaStopniaTrudności/10*4;
        double ns = 1000000000 / iloscCykli;
        /**
         * Zmienna sluzaca do informowania o ilosci fps(klatek na sekunde)
         */
        int fps = 0;
        /**
         * Zmienna sluzaca do informowania o ilosci aktualizacji na sekunde)
         */
        int updates = 0;
        /**
         * Zmienna wykorzystywana do uzyskania timera zwracajacego nam w konsoli wartosc klatek, roznicy i aktualizacji.
         */
        long time = System.currentTimeMillis();
        pojawiajaceObiekty(wektor);
        long czas;
        if(zwrocStopienTrudnosci(opcjeObiekt)==0)
            stopienTrudnosciGry=1;
        else
            stopienTrudnosciGry = zwrocStopienTrudnosci(opcjeObiekt);
        System.out.println(stopienTrudnosciGry);

        czasPoczatkowy1 -= stopienTrudnosciGry;
        int czasPoczatkowy = (int)czasPoczatkowy1;

        nasluchiwacz = new Pauza();
        ramek.addKeyListener(nasluchiwacz);

        /**
         * Pętla while(gra) zawięrająca obsługę elementów gry takich jak czas, animacja, czy przejścia na inne poziomy.
         */

        while (gra) {
            if (poczatekGry) {
                czyzapis = false;
                ustawCzasGry();
                poczatekGry = false;
                sumaPunktow = 0;
                timer = 0;
                punktyGra = 0;
                poziom = 1;
                czasWPauzie = 1;
                timer2 = 0;
                roznica2 = 0;
                nasluchiwacz.zerujCzasPauzy();

                zmianaCzasu(czasPoczatkowy);
                zmianaPunktow(0);
                zmianaPoziomu(poziom);


            }

            zmianaPrzeciwnikow();
            pauza = !nasluchiwacz.getPodczasPauzy();
            if (pauza) {
                czasRozpoczeciaPauzy = nasluchiwacz.getCzasPauzy();
                long czasAktualny = System.nanoTime();
                roznica += (czasAktualny - czasRozpoczecia) / ns;
                czasRozpoczecia = czasAktualny;
                timer -= timer2;
                if (roznica >= 1) {
                    cykl();
                    roznica = roznica - 1;
                    fps++;
                    timer++;
                    if (timer % 60 == 0)
                        zmianaCzasu(czasPoczatkowy + (poziom - 1) * czasPoczatkowy - (timer / 60));
                    repaint();
                }
                updates++;
                if (System.currentTimeMillis() - time > 1000) {
                    time += 1000;
                    System.out.println("Frames: " + updates + ", Updaty: " + fps + ", Roznica: " + roznica);
                    fps = 0;
                    updates = 0;
                }
                if (poziom < 1) {
                    if (wektor.figury.size() == 0) {
                        poziom++;
                        graZakonczona();
                        kolejnyPoziom(poziom);
                        zmianaCzasu(czasPoczatkowy + (poziom - 1) * czasPoczatkowy - (timer / 60));
                        System.out.println(timer);
                    }
                }
                if (poziom == 1 && wektor.figury.size() == 0) {
                    int pozostalyCzas = czasPoczatkowy + (poziom - 1) * czasPoczatkowy - (timer / 60);
                    sumaPunktow = punktyGra + pozostalyCzas * 10 * stopienTrudnosciGry;

                    program.zmienPanel(program.dajObiekt(), 0);
                    System.out.println(sumaPunktow);
                    czyzapis = true;


                    if (czyzapis == true) {
                        /**
                         * Dodanie w odpowiednim miejscu wyniku w przypadku wyrażenia chęci zapisania nowego, tak, aby lista była nadal posortowana malejaco
                         */
                        if (tablicaPunktowGry[9] < sumaPunktow) {
                            for (int i = 0; i < 10; i++) {
                                if (sumaPunktow > tablicaPunktowGry[i]) {
                                    for (int j = 9; j > i; j--) {
                                        tablicaPunktowGry[j] = tablicaPunktowGry[j - 1];
                                        tablicaNickowGry[j] = tablicaNickowGry[j - 1];
                                    }
                                    tablicaPunktowGry[i] = sumaPunktow;
                                    tablicaNickowGry[i] = menuObiekt.nick;
                                    break;
                                }
                            }

                        }
                        /**
                         * Zapisanie tablicy z nickami z nickami i tablicy z punktami do pliku w przypadku checi zapisania nowego wyniku
                         */
                        try {
                            FileWriter filewriter = new FileWriter("wyniki.txt");
                            PrintWriter printwriter = new PrintWriter(filewriter);
                            for (int i = 0; i < 10; i++) {
                                printwriter.println(tablicaPunktowGry[i] + " " + tablicaNickowGry[i]);
                            }
                            JOptionPane.showMessageDialog(this, "Wygrales! Twoj wynik: " + sumaPunktow + " Dodatkowe punkty: " + pozostalyCzas * 10 * stopienTrudnosciGry + "\n"+"Ranking" +
                                    "\n"+"1. " + tablicaNickowGry[0] +" "+ tablicaPunktowGry[0]+
                                    "\n"+"2. " + tablicaNickowGry[1] +" "+ tablicaPunktowGry[1]+
                                    "\n"+"3. " + tablicaNickowGry[2] +" "+ tablicaPunktowGry[2]+
                                    "\n"+"4. " + tablicaNickowGry[3] +" "+ tablicaPunktowGry[3]+
                                    "\n"+"5. " + tablicaNickowGry[4] +" "+ tablicaPunktowGry[4]+
                                    "\n"+"6. " + tablicaNickowGry[5] +" "+ tablicaPunktowGry[5]+
                                    "\n"+"7. " + tablicaNickowGry[6] +" "+ tablicaPunktowGry[6]+
                                    "\n"+"8. " + tablicaNickowGry[7] +" "+ tablicaPunktowGry[7]+
                                    "\n"+"9. " + tablicaNickowGry[8] +" "+ tablicaPunktowGry[8]+
                                    "\n"+"10. " + tablicaNickowGry[9] +" "+ tablicaPunktowGry[9],"Zwycięstwo", JOptionPane.NO_OPTION);
                            printwriter.close();
                        } catch (IOException e) {
                            System.out.println("Błąd zapisu do pliku");
                        }
                    }
                    /**
                     * Wczytanie listy wynikow z pliku do tablicy z nickami i tablicy z punktami
                     */
                        /*try
                        {
                            FileReader filereader = new FileReader("wyniki.txt");
                            BufferedReader bufferedreader = new BufferedReader(filereader);
                            String nickdozapisu;
                            int punktydozapisu;
                            String liniapliku;
                            for(int i=0;i<10;i++)
                            {
                                liniapliku=bufferedreader.readLine();
                                String[] dane=liniapliku.split(" ");
                                punktydozapisu=ConvertIntoNumeric(dane[0]);
                                nickdozapisu=dane[1];
                                tablicaPunktowGry[i]=punktydozapisu;
                                tablicaNickowGry[i]=nickdozapisu;
                            }
                            bufferedreader.close();
                        }
                        catch (IOException e)
                        {
                            System.out.println("Błąd zapisu do pliku");
                        }
                        */
                    poczatekGry = true;
                    graZakonczona();
                    zakoncz();

                }
                if ((((czasAktualny - czasGry) / 1000000000) - czasPoczatkowy - czasWPauzie) > 0 + (czasPoczatkowy * (poziom - 1))) {
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////
                    zmianaCzasu(0);
                    JOptionPane.showMessageDialog(this, "Czas się skończył. Przegrałeś! Spróbuj jeszcze raz.", "Game over", JOptionPane.NO_OPTION);
                    program.zmienPanel(program.dajObiekt(), 0);
                    poczatekGry = true;
                    graZakonczona();
                    zakoncz();
                }
                roznica2 = 0;
                timer2 = 0;
            }
            else
            {
                long czasAktualny = System.nanoTime();
                roznica2 += (czasAktualny - czasRozpoczeciaPauzy) / ns;
                czasRozpoczeciaPauzy = czasAktualny;
                if (roznica2 >= 1) {
                    roznica2 = roznica2 - 1;
                    timer2++;
                    if (timer2 % 60 == 0){
                        czasWPauzie++;
                    }
                }
            }

        }
            zakoncz();

    }

    /**
     * metoda przypisująca parametrowi czasGry czas rozpoczęcia.
     */
    public void ustawCzasGry(){

        czasGry = System.nanoTime();
    }

    /**
     * metoda czyszcząca listę z nietrafionych obiektów
     */
    private void graZakonczona(){

        wektor.figury.clear();
    }

    /**
     * metoda wyświetlająca czas pozostały do przejścia gry w  JPanel'u start
     * @param timer zmienna sluzaca do ustalenia czasu w grze, jest parametrem odejmowanym od wartości dodatniej
     */
    private void zmianaCzasu(int timer){

        czas.setText("Czas: "+ timer);
        czas.setForeground(new Color(200, 50, 194));
        czas.setFont(new Font("SansSerif", Font.BOLD, 20));

    }

    /**
     * metoda wyświetlająca liczbę obiektów pozostałych do zestrzelenia
     */
    private void zmianaPrzeciwnikow(){
        przeciwnicy.setText("Pozostali przeciwnicy: "+wektor.figury.size());
    }

    /**
     * metoda wyświetlająca liczbę uzyskanych punktów
     * @param punktyGra punkty zdobyte w czasie poziomu/poziomów
     */
    private void zmianaPunktow(int punktyGra){
        punkty.setText("Punkty: " + punktyGra);
        punkty.setForeground(new Color(200, 147, 67));
        punkty.setFont(new Font("Sanserif", Font.BOLD,20));

    }

    /**
     * metoda wyświetlająca numer trwającego poziomu
     * @param poziom zmienna ukazujaca aktualny poziom
     */
    private void zmianaPoziomu(int poziom){
        level.setText("Poziom: " + poziom);

    }

    /**
     * metoda aktualizująca parametry dla kolejnego poziomu
     * @param poziom
     */
    private void kolejnyPoziom(int poziom){
        pojawiajaceObiekty(wektor);
        zmianaPunktow(punktyGra);
        zmianaPoziomu(poziom);

    }

    /**
     * Metoda pojawiajaceObiekty() generuje liczby losowe w celu uzyskania położenia przeciwników, których następnie dodaje.iloscPrzeciwnikow
     * Liczba przeciwników zdeterminowana jest za pomocą parametru iloscPrzeciwnikow, którego losowość zaprezentowana jest na początku tegoż pliku
     * @param w lista figur
     */
    private void pojawiajaceObiekty(OperacjaFigurami w) {
            iloscPrzeciwnikow += poziom;
        for (int i = 0; i < iloscPrzeciwnikow; i++) {
            Random rand1 = new Random();
            /**
             * Zmienna okreslajaca w jakim miejscu znajdzie się figura.
             */
            double b = rand1.nextInt(5);
            Random rand2 = new Random();
            /**
             * Zmienna określająca w jakim miejscu znajdzie się figura.
             */
            double c = rand2.nextInt(5);
            w.dodajPzeciwnika(new Figury((xPanel * b/5), (yPanel * c/5)));
        }
    }

    /**
     * Metoda cykl() uruchamiająca cykle z wektora dla wszystkich figur
     */
    private void cykl() {
        wektor.cykl();
    }

    /**
     * Metoda czyObiektUmarl() wykorzystująca metodę czyTrafiony(xm,ym). Metoda w zależności od tego, co zwróci nam czyTrafiony(xm,ym)
     * usunie danego przeciwnika bądź pozostanie niewykorzystana.
     * @param xm szerokosc
     * @param ym wysokosc
     */
    public void czyObiektUmarl(int xm, int ym, Start panel)
    {
        for(int i=0;i<=wektor.figury.size()-1;i++){
            if(wektor.figury.get(i).czyTrafiony(xm,ym,panel))
            {
                wektor.usunPrzeciwnika(i);

                punktyGra+=(100 + param.zmianaStopniaTrudności + (stopienTrudnosciGry-1)*10);

                int dodatkowePunkty = rand.nextInt(11) + 5;
                if(dodatkowePunkty==10)
                    punktyGra+=200;
                zmianaPunktow(punktyGra);


            }

        }
    }

}
