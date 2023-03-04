package org.mai.project.obstanovka;

public class obstanovkaData {
    private final String SYNC_WORDS;

    private final String SYNC_WORDS_2;
    private final String PID;
    private final String PSQ;
    private final String PL;
    private final String ON_BOARD_MINUTES;
    private final String ON_BOARD_SECONDS;
    private final String ON_BOARD_DAYS;
    private final String ON_BOARD_HOURS;
    private final String ON_BOARD_YEARS;
    private final String ON_BOARD_MONTHS;

    public obstanovkaData(){
        FilesTool obst = new FilesTool("C:\\Users\\vledb\\OneDrive\\Документы\\obstanovka\\secondfile.txt");
        SYNC_WORDS = (obst.getData(0) + obst.getData(1)).replace(" ", "");
        System.out.println("Sync Words " + SYNC_WORDS);
        SYNC_WORDS_2 = (obst.getData(2) + obst.getData(3)).replace(" ", "");
        System.out.println("Sync Words " + SYNC_WORDS_2);
        PID = (obst.getData(4) + obst.getData(5)).replace(" ", "");
        System.out.println("PID " + PID);
        PSQ = (obst.getData(6) + obst.getData(7)).replace(" ", "");
        System.out.println("PSQ " + PSQ);
        PL = (obst.getData(8) + obst.getData(9)).replace(" ", "");
        System.out.println("PL " + PL);
        ON_BOARD_MINUTES = obst.binaryToText((obst.getData(10) + obst.getData(11)).split(" ")[0]);
        System.out.println("ON BOARS MINUTES " + ON_BOARD_MINUTES);
        ON_BOARD_SECONDS = obst.binaryToText(obst.getData(7).split(" ")[1]);
        System.out.println("ON BOARS SECONDS " + ON_BOARD_SECONDS);
        ON_BOARD_DAYS = obst.binaryToText(obst.getData(8).split(" ")[0]);
        System.out.println("ON BOARS DAYS " + ON_BOARD_DAYS);
        ON_BOARD_HOURS = obst.binaryToText(obst.getData(8).split(" ")[1]);
        System.out.println("ON BOARS HOURS " + ON_BOARD_HOURS);
        ON_BOARD_YEARS = obst.binaryToText(obst.getData(9).split(" ")[0]);
        System.out.println("ON BOARS YEARS " + ON_BOARD_YEARS);
        ON_BOARD_MONTHS = obst.binaryToText(obst.getData(9).split(" ")[1]);
        System.out.println("ON BOARS MONTHS " + ON_BOARD_MONTHS);

    }

}
