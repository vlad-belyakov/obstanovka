package org.mai.project.obstanovka;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesTool {
    private String strPath;
    private Path path;
    private byte[] byteData;
    private String[] stringData;

    public FilesTool(String strPath, char type){
        this.strPath = strPath;
        path = Paths.get(this.strPath);
        if (!Files.exists(path))
            System.out.println("файл " + path + " не был найден");
    }

    private void readData(Path path, char type) {
        if (type == 'b') {
            try {
                byteData = Files.readAllBytes(path);
            } catch (IOException e) {
                throw new RuntimeException(e); // исправить обработку ошибки
            }
        } else if (type == 's') {
            while (Files.is)
        }
    }

    public void changePath(String strPath){
        this.strPath = strPath;
        path = Paths.get(strPath);
    }

    private byte[][] obstanovkaConvertData(byte[] byteData){
        boolean two = true;
        byte[][] convertedData;
        int j = 0;

        if (byteData.length % 2 == 0) {
            convertedData = new byte[byteData.length/2][2];
        }else{
            convertedData = new byte[byteData.length/2 + 1][2];
            two = false;
        }

        for (int i = 0; i <= byteData.length - 1; i++){
                if (i % 2 != 0) {
                    convertedData[j][0] = byteData[i];
                    if (!two && i == byteData.length - 1)
                        convertedData[j][1] = 0;
                    System.out.println("файл содержал нечетное кол-во байтов");
                } else {
                    convertedData[j][1] = byteData[i];
                    j++;
                    String s;
                }
            }
        }
    }

}
