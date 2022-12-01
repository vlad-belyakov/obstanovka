package org.mai.project.obstanovka;

public class obstanovkaData {
    private final String SYNC_WORDS;
    private final String PID;
    private final String PSQ;
    private final String PL;

    public obstanovkaData(){
        FilesTool obst = new FilesTool("C:\\Users\\vledb\\OneDrive\\Документы\\obstanovka");
        SYNC_WORDS = obst.getData(0) + obst.getData(1);
        PID = obst.getData(2);
        PSQ = obst.getData(3);
        PL = obst.getData(4);
    }

}
