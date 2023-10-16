package org.mai.obstanovki;


public class ObstanovkaData extends Thread {


    private  String SYNC_WORDS;
    private  String SYNC_WORDS_2;
    private  String PID;
    private  String PSQ;
    private  String PL;
    private  String ON_BOARD_MINUTES;
    private  String ON_BOARD_SECONDS;
    private  String ON_BOARD_DAYS;
    private  String ON_BOARD_HOURS;
    private  String ON_BOARD_YEARS;
    private  String ON_BOARD_MONTHS;

    public void run(){
        starts();
    }


    public void star(){
        FilesTool obst = new FilesTool("C:\\Users\\vledb\\OneDrive\\Документы\\obstanovka\\secondfile.txt");
        SYNC_WORDS = "0001101011001111";
        //System.out.println("Sync Words " + SYNC_WORDS);
        SYNC_WORDS_2 = "1111110000011101";
        //System.out.println("Sync Words " + SYNC_WORDS_2);
        PID = (obst.getData(4) + obst.getData(5));
        //System.out.println("PID " + PID);
        PSQ = (obst.getData(6) + obst.getData(7));
        //System.out.println("PSQ " + PSQ);
        PL = (obst.getData(8) + obst.getData(9));
        //System.out.println("PL " + PL);
        ON_BOARD_MINUTES = obst.binaryToText((obst.getData(10) + obst.getData(11)).split(" ")[0]);
        //System.out.println("ON BOARS MINUTES " + ON_BOARD_MINUTES);
        ON_BOARD_SECONDS = obst.binaryToText(obst.getData(7).split(" ")[1]);
        //System.out.println("ON BOARS SECONDS " + ON_BOARD_SECONDS);
        ON_BOARD_DAYS = obst.binaryToText(obst.getData(8).split(" ")[0]);
        //System.out.println("ON BOARS DAYS " + ON_BOARD_DAYS);
        ON_BOARD_HOURS = obst.binaryToText(obst.getData(8).split(" ")[1]);
        //System.out.println("ON BOARS HOURS " + ON_BOARD_HOURS);
        ON_BOARD_YEARS = obst.binaryToText(obst.getData(9).split(" ")[0]);
        //System.out.println("ON BOARS YEARS " + ON_BOARD_YEARS);
        ON_BOARD_MONTHS = obst.binaryToText(obst.getData(9).split(" ")[1]);
        //System.out.println("ON BOARS MONTHS " + ON_BOARD_MONTHS);
    }

    public void starts() {
        FilesTool obst = new FilesTool("C:\\Users\\vledb\\OneDrive\\Документы\\obstanovka\\thirdfile.txt");
        int j = 0;
        boolean isReading = true;
        do {
            for (int i = 0; i <= 286; i++) {
                String syncOne = obst.getData(j) + obst.getData(j + 1);
                String syncTwo = obst.getData(j + 2) + obst.getData(j + 3);
                String synOne = obst.byteToHex(syncOne);
                String synTwo = obst.byteToHex(syncTwo);
                String synctri = synOne + synTwo;
                if(!obst.getData(j).equals("нет данных")) {
                    if (!synctri.equals("1ACFFC1D")) {
                        System.out.println(obst.getData(j) + " | " + obst.byteToHex(obst.getData(j)));
                        j++;
                    } else if (synctri.equals("1ACFFC1D") && i == 0) {
                        System.out.println(obst.getData(j) + " | " + obst.byteToHex(obst.getData(j)));
                        j++;
                    } else {
                        System.out.println("пак получен");
                        i = 0;
                        //j += 23;
                        break;
                    }
                } else {
                    System.out.println("нет данных");
                    isReading = false;
                    break;
                }
            }
        }while(isReading);
    }
}
