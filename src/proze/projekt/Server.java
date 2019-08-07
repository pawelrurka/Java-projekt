package proze.projekt;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;

import static proze.projekt.parametry.*;

/**
 * Główna część klasy Serwer.
 */

public class Server extends  Thread{
    /**
     * Nowe "gniazdko połączeniowe"
     */
    private ServerSocket serverSocket;
    /**
     * Zmienna zwracająca liczbę połączonych klientów
     */
    private int clientNumber = 0;
    /**
     * Lista aktywnych połączeń
     */
    private LinkedList<ClientHandler> activeSockets;
    /**
     * Parametry serwera
     */
    private ServerParameters serverParameters;

    public Server(int port)  {
        try
        {

            serverParameters = new ServerParameters();
            activeSockets = new LinkedList<ClientHandler>();
            serverSocket = new ServerSocket(port);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Miejsce uruchomienia wątka
     */
    @Override
    public void run() {
        try {
            while(true){

                Socket clientSocket = serverSocket.accept();
                System.out.println("A new client is trying to connect : " + clientSocket);
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

                clientNumber++;
                out.writeUTF("LOGGED_IN" + '\n' + serverParameters.createParamPackage());

                activeSockets.addLast(new ClientHandler(clientSocket, in, out,clientNumber-1));
                Thread thread = activeSockets.getLast();
                thread.start();
            }


        }
        catch (IOException  e) {
            stopServer();
            e.printStackTrace();
            return;
        }

    }

    /**
     * Zatrzymanie serwera
     */
    private void stopServer() {
        try {
            serverSocket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        clientNumber = 0;
        activeSockets.clear();
    }

    public class ServerParameters{
        /**
         * Najniższy wynik z rankingu
         */
        private int lowestHighscore;
        /**
         * Nazwa gry
         */
        private String nazwaGry;
        /**
         * Liczba poziomów gry
         */
        private int liczbaPoziomów;
        /**
         * Zmienna informujaca nas o nazwie "poziomów"
         */
        private String nazwaBazowaPlikuZOpisemPoziomu;
        /**
         * Od jakiej wartości rozpoczyna się numeracja poziomów
         */
        private int numeracjaPoziomówZaczynaSięOd;
        /**
         * Rozszerzenie pliku z opisem poziomu
         */
        private String rozszerzeniePlikuZOpisemPoziomu;
        /**
         * Liczba stopni trudności
         */
        private int liczbaStopniTrudności;
        /**
         * Jaka zmiana nastąpi przy wyborze stopnia trudności
         */
        private int zmianaStopniaTrudności;
        /**
         * Początkowa szerokość planszy
         */
        private int początkowaSzerokośćPlanszy;
        /**
         * Początkowa wysokość planszy
         */
        private int początkowaWysokośćPlanszy;
        /**
         * Jak zależy rozmiar obiektu od początkowych wartości szerokości i wysokości planszy
         */
        private float początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy;
        /**
         * Czy plik na tło jest plikiem graficznym
         */
        private Boolean czyPlikGraficzny;
        /**
         * Plik tła
         */
        private String plikTła;
        /**
         * Plik obiektu
         */
        private String plikObiektu;
        /**
         * Czy przeciwnikami zostaną figury geometryczne, czy obrazek
         */
        private Boolean czyFiguryGeometryczne;
        /**
         *
         */
        private String figuraObiektuGry;
        /**
         * Kolor tła barwy RED
         */
        private int kolorTłaR;
        /**
         * Kolor tła barwy GREEN
         */
        private int kolorTłaG;
        /**
         * Kolor tła barwy BLUE
         */
        private int kolorTłaB;
        /**
         * Stopien trudnosci, na podstawie którego tworzymy opcje
         */
        private int stopienTrudnosci ;

        /**
         * Parametry serwera pobierane z pliku parametrów klienta
         */
        ServerParameters(){
            parametry.readFile();
            nazwaGry = parametry.nazwaGry;
            liczbaPoziomów = parametry.liczbaPoziomów;
            nazwaBazowaPlikuZOpisemPoziomu = parametry.nazwaBazowaPlikuZOpisemPoziomu;
            numeracjaPoziomówZaczynaSięOd = parametry.numeracjaPoziomówZaczynaSięOd;
            rozszerzeniePlikuZOpisemPoziomu = parametry.rozszerzeniePlikuZOpisemPoziomu;

            liczbaStopniTrudności = parametry.liczbaStopniTrudności;
            zmianaStopniaTrudności = parametry.zmianaStopniaTrudności;

            początkowaSzerokośćPlanszy = parametry.początkowaSzerokośćPlanszy;
            początkowaWysokośćPlanszy = parametry.początkowaWysokośćPlanszy;
            początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy = parametry.początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy;

            czyPlikGraficzny = parametry.czyPlikGraficzny;
            plikTła = parametry.plikTła;
            plikObiektu = parametry.plikTła;

            czyFiguryGeometryczne = parametry.czyFiguryGeometryczne;
            figuraObiektuGry=parametry.figuraObiektuGry;

            kolorTłaR = parametry.kolorTłaR;
            kolorTłaG = parametry.kolorTłaG;
            kolorTłaB = parametry.kolorTłaB;
            stopienTrudnosci = parametry.stopienTrudnosci;
        }

        /**
         * Zwraca liczbe poziomów
         * @return liczba poziomów
         */
        public int getLevelNumber()
        {
            return liczbaPoziomów;
        }

        /**
         * Zwraca najniższy wynik w rankingu
         * @return najniższy wynik w rankingu
         */
        public int getLowestHighscore(){
            return lowestHighscore;
        }

        /**
         * Tworzy string, w którym zawarte są wszystkie parametry pliku gry
         * @return string z parametrami
         */
        public String createParamPackage(){
            String params = "";
            params += nazwaGry + "\n";
            params += liczbaPoziomów + "\n";
            params += nazwaBazowaPlikuZOpisemPoziomu + "\n";
            params += numeracjaPoziomówZaczynaSięOd + "\n";
            params += rozszerzeniePlikuZOpisemPoziomu + "\n";
            params += liczbaStopniTrudności + "\n";
            params += zmianaStopniaTrudności + "\n";
            params += początkowaSzerokośćPlanszy + "\n";
            params += początkowaWysokośćPlanszy + "\n";
            params += początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy + "\n";
            if(czyPlikGraficzny)
                params += "1" + "\n";
            else
                params += "0" + "\n";
            params += plikTła + "\n";
            params += plikObiektu + "\n";
            if(czyFiguryGeometryczne)
                params += "1" + "\n";
            else
                params += "0" + "\n";
            params += figuraObiektuGry + "\n";
            params += kolorTłaR + "\n";
            params += kolorTłaG + "\n";
            params += kolorTłaB + "\n";
            return params;


        }


    }
    private class ClientHandler extends Thread {
        /**
         * Wejscie
         */
        private final DataInputStream dis;
        /**
         * Wyjście
         */
        private final DataOutputStream dos;
        /**
         * "Gniazdko połączeniowe"
         */
        private final Socket socket;
        /**
         * indeks klienta
         */
        private final int clientIndex;


        /**
         * Konstruktor ClientHandlera
         * @param s "gniazdko połączeniowe"
         * @param dis "wejście"
         * @param dos "wyjście"
         * @param clientIndex
         */
        ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos,int clientIndex) {
            this.socket = s;
            this.dis = dis;
            this.dos = dos;
            this.clientIndex = clientNumber;

        }

        @Override
        public void run() {
            while (true) {
                try {
                    if(!checkIfAlive())
                        return;
                    String received = dis.readUTF();

                    switch(received) {
                        case "GET_HIGH_SCORES": // Client is asking for highscores
                            //read from file and send highscores
                            break;
                        case "GET_LEVELS": //
                            dos.writeUTF("LEVELS"+serverParameters.getLevelNumber());
                            break;
                        case "SCORE": //
                            if(received.startsWith("SCORE")) {
                                received = received.substring(5);
                                String highScore[] = received.split(" ");
                                int score = Integer.parseInt(highScore[1]);
                                if(score<=serverParameters.getLowestHighscore()){
                                    dos.writeUTF("SCORE_DECLINED");
                                }
                                else{
                                    //updateScoreBoard()
                                    dos.writeUTF("SCORE_ACCEPTED");
                                }

                            }
                            break;
                        case "CLOSE":
                            activeSockets.remove(clientIndex);
                            clientNumber--;
                            dos.writeUTF("CLOSED");



                    }
                }
                catch (SocketException e) {

                    e.printStackTrace();
                    activeSockets.remove(this);
                    clientNumber--;

                    return;

                }
                catch (IOException e) {
                    e.printStackTrace();
                    try {
                        socket.close();
                        return;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        }
        private boolean checkIfAlive() { return !socket.isClosed(); }
    }
    private LinkedList<ClientHandler> getActiveSockets()
    {
        return activeSockets;
    }

}
