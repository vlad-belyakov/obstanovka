package org.mai.project.obstanovka;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesTool {
    private String strPath;
    private Path path;
    private byte[] byteData;
    private String[] stringData;

    public FilesTool(String strPath) {
        this.strPath = strPath;
        path = Paths.get(this.strPath);
        if (!Files.exists(path))
            System.out.println("файл " + strPath + " не был найден");
        else readByteData(path);
    }

    private void readByteData(Path path) {
            try {
                byteData = Files.readAllBytes(path);
                stringData = obstanovkaToStrData();
            } catch (IOException e) {
                throw new RuntimeException(e); // исправить обработку ошибки
            }
    }

    public void changePath(String strPath) {
        this.strPath = strPath;
        path = Paths.get(strPath);
    }

    public Path getPath(){
        return path;
    }

    public String[] getStringData(){
        return stringData;
    }

    public byte[] getByteData(){
        return byteData;
    }

    public String getData(int i){
        if (i <= stringData.length - 1)
        return stringData[i];
        else throw new NullPointerException(); // переработать ошибку
    }

    private byte[][] obstanovkaConvertData() {
        boolean two = true;
        byte[][] convertedData;
        int j = 0;

        if (byteData.length % 2 == 0) {
            convertedData = new byte[byteData.length / 2][2];
        } else {
            convertedData = new byte[(byteData.length + 1) / 2][2];
            two = false;
        }

        for (int i = 0; i <= byteData.length - 1; i++) {
            if (i % 2 == 0) {
                convertedData[j][0] = byteData[i];
                if (!two && i == byteData.length - 1) {
                    convertedData[j][1] = 0;
                    System.out.println("файл содержал нечетное кол-во байтов");
                }
            } else {
                convertedData[j][1] = byteData[i];
                j++;
            }
        }
        return convertedData;
    }

    private String[] obstanovkaToStrData(){
        String[] convertedData;
        boolean two = false;
        if (byteData.length % 2 == 0) {
            convertedData = new String[byteData.length / 2];
        } else {
            convertedData = new String[(byteData.length + 1) / 2];
            two = true;
        }
        if(two) {
            for (int i = 0; i <= byteData.length - 1; i = i + 2) {
                convertedData[i] = byteToStr(byteData[i]) + byteToStr(byteData[i + 1]);
            }
        }else{
            for (int i = 0; i <= byteData.length - 1; i = i + 2) {
                if(i != byteData.length - 1)
                convertedData[i] = byteToStr(byteData[i]) + byteToStr(byteData[i + 1]);
                else {
                    convertedData[i] = byteToStr(byteData[i]) + "нечетное кол-во байт в файле";
                }
            }
        }
        return convertedData;
    }

    private String[] byteArrToStrArr(byte[] byteData){
        String[] stringData = new String[byteData.length];
        for (int i = 0; i < byteData.length - 1; i++) {
            stringData[i] = byteToStr(byteData[i]);
        }
        return stringData;
    }

    private String byteToStr(byte byteData){
        String tempData = "";
            tempData = String.format("%8s", Integer
                            .toBinaryString(byteData & 0xFF))
                            .replace(' ', '0');
        return tempData;
    }
}
