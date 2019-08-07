package proze.projekt;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * Klasa parametry, która wykorzystywana jest do wczytania parametrów gry oraz ich przechowaniu.
 * @author Paweł Rurka Gabriela Maciejewska
 */
public class parametry {
    /**
     * Zmienna zawierajaca nazwe gry
     */
    static String nazwaGry;
    /**
     * Zmienna zawierajaca liczbe poziomow
     */
    static int liczbaPoziomów;
    /**
     * Zmienna nazwy bazowej pliku z opisem poziomu
     */
    static String nazwaBazowaPlikuZOpisemPoziomu;
    /**
     * Zmienna zawierajaca wartosc od ktorej rozpoczynamy liczenie poziomow
     */
    static int numeracjaPoziomówZaczynaSięOd;
    /**
     *Zmienna przechowujaca nazwe rozszerzenia pliku z opisem poziomu
     */
    static String rozszerzeniePlikuZOpisemPoziomu;
    /**
     * Zmienna informujaca nas o liczbie stopniu trudnosci w grze
     */
    static int liczbaStopniTrudności;
    /**
     * Zmienna informujaca nas o ile zmieni sie parametr poza zmiana przez stopien trudnosci
     */
    static int zmianaStopniaTrudności;
    /**
     * Zmienna przechowujaca poczatkowa szerokosc planszy
     */
    static int początkowaSzerokośćPlanszy;
    /**
     * Zmienna przechowujaca poczatkowa wysokosc planszy
     */
    static int początkowaWysokośćPlanszy;
    /**
     * Zmienna prezentujaca uzaleznienie rozmiaru obiektow od rozmiaru planszy
     */
    static float początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy;
    /**
     * Boolean informujacy nas o wylosowaniu czy trafilismy na plik graficzny, czy tlo graficzne
     */
    static Boolean czyPlikGraficzny;
    /**
     * Zmienna przechowujaca plik tla lub parametry RGB
     */
    static String plikTła;
    /**
     * Zmienna przechowujaca nazwe pliku obiektu, ktory bedzie naszymi punktami
     */
    static String plikObiektu;
    /**
     * Boolean informujacy nas o wylosowaniu czy trafilismy na przeciwnikow w formie figur geometrycznych, czy w formie image
     */
    static Boolean czyFiguryGeometryczne;
    /**
     * Zmienna informujaca nas jaka figure wylosowalismy(prostokat,trojkat,kolko albo kwadrat)
     */
    static String figuraObiektuGry;
    /**
     * Zawiera kolor tla typu RED
     */
    static int kolorTłaR;
    /**
     * Zawiera kolor tla typu GREEN
     */
    static int kolorTłaG;
    /**
     * Zawiera kolor tla typu BLUE
     */
    static int kolorTłaB;
    /**
     * Zmienna zawierajaca stopien trudnosci w formie static inta
     */
    static int stopienTrudnosci = 0;

    /**
     * Konstruktor parametrow
     */
    parametry() {
        readFile();
    }

    /**
     * Funkcja wczytująca pliki parametryzowane
     * @author Rurka Maciejewska
     */
    public static void readFile() {
        String fileName = "par.txt";
        String line = null;

        try {
            //FileReader czyta tekst pliku w kodowaniu domyślnym.
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String temp;
                if (line.charAt(0) == '#') continue;
                else if (line.contains("nazwaGry")) {
                    nazwaGry = line.substring(line.indexOf('=') + 1, line.indexOf('[') - 1);
                    System.out.println(nazwaGry);
                } else if (line.contains("liczbaPoziomów")) {
                    temp = line.substring(line.indexOf('=') + 1);
                    liczbaPoziomów = Integer.parseInt(temp.substring(0));
                    System.out.println(liczbaPoziomów);
                } else if (line.contains("nazwaBazowaPlikuZOpisemPoziomu")) {
                    nazwaBazowaPlikuZOpisemPoziomu = line.substring(line.indexOf('=') + 1);
                    System.out.println(nazwaBazowaPlikuZOpisemPoziomu);
                } else if (line.contains("numeracjaPoziomówZaczynaSięOd")) {
                    temp = line.substring(line.indexOf('=') + 1);
                    numeracjaPoziomówZaczynaSięOd = Integer.parseInt(temp.substring(0));
                    System.out.println(numeracjaPoziomówZaczynaSięOd);
                } else if (line.contains("rozszerzeniePlikuZOpisemPoziomu")) {
                    rozszerzeniePlikuZOpisemPoziomu = line.substring(line.indexOf('=') + 1);
                    System.out.println(rozszerzeniePlikuZOpisemPoziomu);
                } else if (line.contains("liczbaStopniTrudności")) {
                    temp = line.substring(line.indexOf('=') + 1);
                    liczbaStopniTrudności = Integer.parseInt(temp.substring(0));
                    System.out.println(liczbaStopniTrudności);
                } else if (line.contains("zmianaStopniaTrudności")) {
                    temp = line.substring(line.indexOf('=') + 1);
                    zmianaStopniaTrudności = Integer.parseInt(temp.substring(0));
                    System.out.println(zmianaStopniaTrudności);
                } else if (line.contains("początkowaSzerokośćPlanszy")) {
                    temp = line.substring(line.indexOf('=') + 1);
                    początkowaSzerokośćPlanszy = Integer.parseInt(temp.substring(0));
                    System.out.println(początkowaSzerokośćPlanszy);
                } else if (line.contains("początkowaWysokośćPlanszy")) {
                    temp = line.substring(line.indexOf('=') + 1);
                    początkowaWysokośćPlanszy = Integer.parseInt(temp.substring(0));
                    System.out.println(początkowaWysokośćPlanszy);
                } else if (line.contains("początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy")) {
                    temp = line.substring(line.indexOf('=') + 1);
                    początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy = Float.parseFloat(temp.substring(0));
                    System.out.println(początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy);
                } else if (line.contains("obiektyGry")) {
                    temp = line.substring(line.indexOf('=') + 1);
                    if (temp.equals("figuryGeometryczne")) czyFiguryGeometryczne = true;
                    else czyFiguryGeometryczne = false;
                    System.out.println(czyFiguryGeometryczne);
                } else if (line.contains("tlo")) {
                    temp = line.substring(line.indexOf('=') + 1);
                    if (temp.equals("plikGraficzny")) czyPlikGraficzny = true;
                    else if (temp.equals("jednolite")) czyPlikGraficzny = false;
                    System.out.println(czyPlikGraficzny);
                } else if (line.contains("kolorTła")) {
                    temp = line.substring(line.indexOf('=')+1);
                    kolorTłaR = Integer.parseInt(temp.substring(0, temp.indexOf(' ')));
                    System.out.println(kolorTłaR);
                    temp = temp.substring(temp.indexOf(' ')+1);
                    kolorTłaG = Integer.parseInt(temp.substring(0, temp.indexOf(' ')));
                    System.out.println(kolorTłaG);
                    temp = temp.substring(temp.indexOf(' ')+1);
                    kolorTłaB = Integer.parseInt(temp);
                    System.out.println(kolorTłaB);
                } else if (line.contains("plikTła")) {
                    plikTła = line.substring(line.indexOf('=') + 1);
                    System.out.println(plikTła);
                } else if (line.contains("plikObiektu")) {
                    plikObiektu = line.substring(line.indexOf('=') + 1);
                    System.out.println(plikObiektu);
                } else if (line.contains("figuraObiektuGry")) {
                    figuraObiektuGry = line.substring(line.indexOf('=') + 1);
                    System.out.println(figuraObiektuGry);
                }
            }
            //Zawsze zamyka pliki.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex1) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }

    /**
     * metoda zwracajaca wartosc parametru wysokosci planszy
     * @return początkowaWysokośćPlanszy
     */
    public static int zwrocWysokosc() {

        return początkowaWysokośćPlanszy;
    }

    /**
     * metoda zwracajaca wartosc parametru szerokosci planszy
     * @return zmienna początkowaSzerokośćPlanszy
     */
    public static int zwrocSzerokosc() {
        return początkowaSzerokośćPlanszy;
    }

    /**
     * metoda zwracajaca nazwe gry
     * @return zmienna nazwaGry
     */
    public static String zwrocNazwe(){
        return nazwaGry;
    }

    /**
     * metoda zwracajaca liczbe stopni trudnosci
     * @return zmienna liczbaStopniTrudnosci
     */
    public static int zwrocLiczbeStopniTrudnosci(){
        return liczbaStopniTrudności;
    }


}
