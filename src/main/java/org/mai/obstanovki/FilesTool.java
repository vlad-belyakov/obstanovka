package org.mai.obstanovki;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


@Component("filesToolBean")
public class FilesTool {
    private String strPath;
    private Path path;
    private byte[] byteData;
    private String[] decodedData;
    private final Decoder decoder = new Decoder();
    protected int dataLength;


    public FilesTool(String strPath) {
        this.strPath = strPath;
        path = Paths.get(this.strPath);
        readFile(strPath);
    }


    private void readByteData(Path path) {
        try {
            byteData = Files.readAllBytes(path);
            decodedData = new String[4330151];
            decodedData = decoder.twoBytesGroup(this);
            dataLength = decodedData.length;
        } catch (IOException e) {
            throw new RuntimeException(e); // исправить обработку ошибки
        }
    }

    private void readFile(String strPath){
        if (!Files.exists(path))
            System.out.println("файл " + strPath + " не был найден");
        else {
            changeFileProperty(strPath, "filesTool.path =");
            readByteData(path);
        }
    }

    private void changeFileProperty(String strPath, String toChange){
        try {
            this.strPath = strPath;
            Path tempPath = Paths.get("src/main/resources/org/mai/obstanovki/App.properties");
            String str = Files.readString(tempPath);
            if (str.contains(toChange)){
                String[] strArr = str.split("\r\n");
                for (int i = 0; i <= strArr.length - 1; i++){
                    if (strArr[i].startsWith(toChange)){
                        strArr[i] = toChange + strPath;
                    }
                }
                String strTwo = Arrays.toString(strArr).replace("[", "").replace("]", "").replace(", ", "\r\n");
                PrintWriter printWriter = new PrintWriter(String.valueOf(tempPath));
                printWriter.print("");
                printWriter.print(strTwo);
                printWriter.close();
                //System.out.println(Files.readString(tempPath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePath(String strPath) {
        this.strPath = strPath;
        path = Paths.get(strPath);
        readFile(strPath);
    }

    private Path getPath() {
        return path;
    }


    public byte[] getByteData() {
        return byteData;
    }

    public String getData(int i) {
        if (i <= decodedData.length - 1)
            return decodedData[i];
        else return "нет данных";
    }


    public int getDataLength() {
        return dataLength;
    }
}
