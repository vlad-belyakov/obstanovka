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


    public void starts() {
        FilesTool obst = new FilesTool("C:\\Users\\vledb\\OneDrive\\Документы\\obstanovka\\thirdfile.txt");
        Decoder decoder = new Decoder();
        int j = 23;
        boolean isReading = true;
        do {
            for (int i = 0; i <= 286; i++) {
                String syncOne = obst.getData(j) + obst.getData(j + 1);
                String syncTwo = obst.getData(j + 2) + obst.getData(j + 3);
                String synOne = decoder.binaryToHex(syncOne);
                String synTwo = decoder.binaryToHex(syncTwo);
                String synctri = synOne + synTwo;
                 if(!obst.getData(j).equals("нет данных")) {
                    if (!synctri.equals("1ACFFC1D")) {
                        //System.out.println(obst.getData(j) + " | " + decoder.binaryToHex(obst.getData(j)));
                        if(i != 286) System.out.println(decoder.binaryToText(obst.getData(j)) + decoder.binaryToText(obst.getData(j + 1)));
                        j++;
                    } else {
                        System.out.println("пак получен");
                        j += 23;
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
