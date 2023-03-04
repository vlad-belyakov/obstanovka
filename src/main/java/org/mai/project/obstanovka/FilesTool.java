package org.mai.project.obstanovka;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesTool {
    private boolean stop = false;
    private String strPath;
    private Path path;
    private byte[] byteData;
    private String[] tempData;
    private String[] decodedData;
    Decoder decoder = new Decoder();

    public static void main(String[] args) {
        new FilesTool();
    }

    public FilesTool(String strPath) {
        this.strPath = strPath;
        path = Paths.get(this.strPath);
        if (!Files.exists(path))
            System.out.println("файл " + strPath + " не был найден");
        else readByteData(path);

        /*for (int i = 0; i <= decodedData.length - 1; i++){
            if (decodedData[i] != null)
            System.out.println(decodedData[i]);
            else{
                System.out.println(i);
                break;
            }
        }*/
    }


    public FilesTool(){
        byteData = ("1a cf fc 1d 3c 88 0f d7 0c 00 00 00 08 17 04 71\n"/* +
                        "00 30 20 00 20 00 00 1a cf fc 1d 0c 88 43 c0 0f\n" +
                        "00 01 00 08 17 04 71 20 00 00 30 20 01 72 6d 3a\n" +
                        "20 1a cf fc 1d 0c 88 44 c0 26 00 01 00 08 17 04\n" +
                        "71 20 00 00 30 20 01 63 61 6e 6e 6f 74 20 72 65\n" +
                        "6d 6f 76 65 20 60 2f 72 6f 6f 74 2f 2a 2e 64 61\n" +
                        "74 27 1a cf fc 1d 0c 88 45 c0 26 00 01 00 08 17\n" +
                        "04 71 20 00 00 30 20 01 3a 20 4e 6f 20 73 75 63\n" +
                        "68 20 66 69 6c 65 20 6f 72 20 64 69 72 65 63 74\n" +
                        "6f 72 79 1a cf fc 1d 0c 88 46 c0 0c 00 01 00 08\n" +
                        "17 04 71 20 00 00 30 20 01 0a 1a cf fc 1d 9c 88\n" +
                        "1f c6 0d 01 01 00 08 17 04 71 20 00 00 30 00 02\n" +
                        "35 4c a1 36 d4 2c 37 4c a1 36 da 2c 37 4c 8d 36\n" +
                        "e4 2c 35 4c a3 36 d5 2c 35 4c 94 36 db 2c 35 4c\n" +
                        "9b 36 e1 2c 31 4c 93 36 eb 2c 35 4c 97 36 e2 2c\n" +
                        "34 4c 95 36 d9 2c 3f 4c 9a 36 db 2c 3a 4c 97 36"*/).getBytes(StandardCharsets.UTF_8);
        tempData = decoder.twoBytesGroup(this);
        if (tempData.length % 3 == 0) {
            decodedData = new String[tempData.length / 3];
        } else if(tempData.length % 3 == 1){
            decodedData = new String[(tempData.length + 2) / 3];
        } else {
            decodedData = new String[(tempData.length + 1) / 3];
        }
        int j = 0;
        for (int i = 0; i <= tempData.length - 1; i++) {
            if (i != tempData.length - 2 && i != tempData.length - 1) {
                decodedData[j] = decoder
                        .hexToBinary(binaryToText(tempData[i])) + decoder
                        .hexToBinary(binaryToText(tempData[i + 1])) + decoder
                        .hexToBinary(binaryToText(tempData[i + 2]));
                i = i + 2;
                j++;
            } else if (i == tempData.length - 2) {
                decodedData[j] = decoder
                        .hexToBinary(decoder
                                .getPrettyView(binaryToText(tempData[i]))) + decoder
                        .hexToBinary(decoder
                                .getPrettyView(binaryToText(tempData[i + 1]))) + "конец";
                i++;
                j++;
            } else decodedData[j] = decoder
                    .hexToBinary(decoder
                            .getPrettyView(binaryToText(tempData[i]))) + "точно конец";
        }
        for (int i = 0; i <= decodedData.length - 1; i++){
            System.out.println(decodedData[i]);
        }
    }


    private void readByteData(Path path) {
        try {
            byteData = Files.readAllBytes(path);
            decodedData = new String[4330151];
            decodedData = decoder.twoBytesGroup(this);
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


    public byte[] getByteData(){
        return byteData;
    }

    public String getData(int i){
        if (i <= decodedData.length - 1)
        return decodedData[i];
        else throw new NullPointerException(); // переработать ошибку
    }

    private String[] byteArrToStrArr(byte[] byteData){
        String[] stringData = new String[byteData.length];
        for (int i = 0; i < byteData.length - 1; i++) {
            stringData[i] = byteToStr(byteData[i]);
        }
        return stringData;
    }

    protected String byteToStr(byte byteData){
        String tempData;
            tempData = String.format("%8s", Integer
                            .toBinaryString(byteData & 0xFF))
                            .replace(' ', '0');
        return tempData;
    }

    public String binaryToText(String binaryString) {
        binaryString = binaryString.replace(" ", "");
        StringBuilder stringBuilder = new StringBuilder();
        int charCode;
        for (int i = 0; i < binaryString.length(); i += 8) {
            charCode = Integer.parseInt(binaryString.substring(i, i + 8), 2);
            String returnChar = Character.toString((char) charCode);
            if(!returnChar.equals("\r")){
                stringBuilder.append(returnChar);
            }
        }
        return stringBuilder.toString();
    }

    public String obstanovkaParsing(byte data){
        return decoder.hexToBinary(binaryToText(byteToStr(data)));
    }
}
