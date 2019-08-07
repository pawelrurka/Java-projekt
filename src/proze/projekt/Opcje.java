package proze.projekt;

import javafx.scene.control.RadioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Klasa przechowująca ustawienia panelu Opcje
 */
public class Opcje extends JPanel {
    /**
     * JPanel opcji
     */
    private JPanel opcje;
    /**
     * Przycisk pozwalajacy na powrot do menu
     */
    private JButton backMenu;
    /**
     *
     */
    private JLabel tytul;
    /**
     * Konstruktor parametry()
     */
    parametry param = new parametry();
    /**
     * Deklaracja tej zmiennej umozliwia nam przypisaniu jej parametru z pliku ktory jest niezbedny do stworzenia stopni trudnosci zaleznych od pliku
     */
    public static int stopienTrudnosci;

    public Opcje() {
        add(tytul);
        String[] opcjeStrings5  = {"Normalny", "Łatwy", "Bardzo łatwy", "Trudny", "Bardzo trudny"};
        String[] opcjeStrings4 = {"Normalny", "Łatwy", "Bardzo łatwy", "Trudny"};
        String[] opcjeStrings3 = {"Normalny", "Łatwy", "Bardzo łatwy"};
        String[] opcjeStrings2 = {"Bardzo łatwy", "Łatwy"};
        String[] opcjeStrings1 = {"Bardzo łatwy"};
        JComboBox opcjeBox;
        if(param.liczbaStopniTrudności==5){
            opcjeBox = new JComboBox(opcjeStrings5);
            add(opcjeBox);
            opcjeBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox)e.getSource();
                    String msg = (String)cb.getSelectedItem();
                    switch (msg){
                        case "Bardzo trudny":
                            stopienTrudnosci = 5;
                            break;
                        case "Trudny":
                            stopienTrudnosci = 4;
                            break;
                        case "Normalny":
                            stopienTrudnosci = 3;
                            break;
                        case "Łatwy":
                            stopienTrudnosci = 2;
                            break;
                        case "Bardzo łatwy":
                            stopienTrudnosci = 1;
                            break;
                    }
                }
            });

        }
        if(param.liczbaStopniTrudności==4){
            opcjeBox = new JComboBox(opcjeStrings4);
            add(opcjeBox);
            opcjeBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String msg = (String) cb.getSelectedItem();
                    switch (msg) {
                        case "Trudny":
                            stopienTrudnosci = 4;
                            break;
                        case "Normalny":
                            stopienTrudnosci = 3;
                            break;
                        case "Łatwy":
                            stopienTrudnosci = 2;
                            break;
                        case "Bardzo łatwy":
                            stopienTrudnosci = 1;
                            break;
                    }
                }
            });
        }
        if(param.liczbaStopniTrudności==3){
            opcjeBox = new JComboBox(opcjeStrings3);
            add(opcjeBox);
            opcjeBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String msg = (String) cb.getSelectedItem();
                    switch (msg) {
                        case "Normalny":
                            stopienTrudnosci = 3;
                            break;
                        case "Łatwy":
                            stopienTrudnosci = 2;
                            break;
                        case "Bardzo łatwy":
                            stopienTrudnosci = 1;
                            break;
                    }
                }
            });
        }
        if(param.liczbaStopniTrudności==2){
            opcjeBox = new JComboBox(opcjeStrings2);
            System.out.println("xd2");
            add(opcjeBox);
            opcjeBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String msg = (String) cb.getSelectedItem();
                    switch (msg) {
                        case "Łatwy":
                            stopienTrudnosci = 2;
                            break;
                        case "Bardzo łatwy":
                            stopienTrudnosci = 1;
                            break;
                    }
                }
            });
        }
        if(param.liczbaStopniTrudności==1){
            opcjeBox = new JComboBox(opcjeStrings1);
            add(opcjeBox);
            opcjeBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String msg = (String) cb.getSelectedItem();
                    switch (msg) {
                        case "Bardzo łatwy":
                            stopienTrudnosci = 1;
                            break;
                    }
                }
            });


        }

        add(opcje);

        setOpaque(false);
        opcje.setOpaque(false);
        tytul.setText("POZIOMY TRUDNOŚCI: ");


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
