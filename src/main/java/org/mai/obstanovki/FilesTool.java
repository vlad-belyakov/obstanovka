package org.mai.obstanovki;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesTool {
    private String strPath;
    private Path path;
    private byte[] byteData;
    private String[] decodedData;
    Decoder decoder = new Decoder();
    protected int dataLength;


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
            decodedData = new String[4330151];
            decodedData = decoder.twoBytesGroup(this);
            dataLength = decodedData.length;
        } catch (IOException e) {
            throw new RuntimeException(e); // исправить обработку ошибки
        }
    }

    public void changePath(String strPath) {
        this.strPath = strPath;
        path = Paths.get(strPath);
    }

    public Path getPath() {
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

    public String getData(int i, boolean isReverse) {
        if (i <= decodedData.length - 1)
            return reverseStr(decodedData[i]);
        else throw new NullPointerException(); // переработать ошибку
    }

    public int getDataLength() {
        return dataLength;
    }

    private String[] byteArrToStrArr(byte[] byteData) {
        String[] stringData = new String[byteData.length];
        for (int i = 0; i < byteData.length - 1; i++) {
            stringData[i] = byteToStr(byteData[i]);
        }
        return stringData;
    }

    protected String byteToStr(byte byteData) {
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
            if (!returnChar.equals("\r")) {
                stringBuilder.append(returnChar);
            }
        }
        return stringBuilder.toString();
    }

    public String bigToLittleEndian(String bigendian) {
        ByteBuffer buf = ByteBuffer.allocate(1);

        buf.order(ByteOrder.BIG_ENDIAN);
        buf.putLong(Long.parseLong(bigendian));

        buf.order(ByteOrder.LITTLE_ENDIAN);
        return String.valueOf(buf.getLong(1));
    }

    public String fileToHex(byte data) {
        return (binaryToText(byteToStr(data)));
    }


    public String hexToBytes(String data) {
        return decoder.hexToBinary(data);
    }

    public String reverseStr(String data){
        return new StringBuilder(data).reverse().toString();
    }

    public String byteToHex(String data){
        return decoder.binaryToHex(data);
    }
}
