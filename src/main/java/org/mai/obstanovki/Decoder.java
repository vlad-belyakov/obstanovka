package org.mai.obstanovki;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.stream.Stream;

@Component("decoderBean")
public class Decoder {

    FilesTool f;
    public Decoder(FilesTool filesTool){
        f = filesTool;
    }

    public Decoder(){
    }

    public void setFilesTool(FilesTool f){
        this.f = f;
    }
    protected String[] twoBytesGroup(FilesTool f){
        byte[] tempData = f.getByteData();
        String[] convertedData;
        int j = 0;
        byte[] byteMidData = new byte[tempData.length];
        for (int i = 0; i <= tempData.length - 1; i++) {
                String first = fileToHex(tempData[i]);
                if (!(first.equals("\r") ||
                        first.equals("\n") ||
                        first.equals(" ") || first.equals("\f") || first.equals(""))) {
                    //System.out.println(first);
                    byteMidData[j] = tempData[i];
                    j++;
                }
        }

        byte[] byteData = new byte[j];
        System.arraycopy(byteMidData, 0, byteData, 0, j);


        if (byteData.length % 2 == 0) {
            convertedData = new String[byteData.length / 2];
        } else {
            convertedData = new String[(byteData.length + 1) / 2];
        }

        j = 0;
            for (int i = 0; i <= byteData.length - 1; i = i + 2) {
                    if (i != byteData.length - 1) {
                        convertedData[j] = hexToBinary((fileToHex(byteData[i]) + fileToHex(byteData[i + 1])));
                        j++;
                    } else {
                        convertedData[j] = hexToBinary((fileToHex(byteData[i])))/* + "нечетное кол-во байт в файле"*/;
                    }
            }
        return convertedData;
    }

    public @NotNull String hexToBinary(String hex) {

        hex = hex.toUpperCase();
        char[] ch = hex.toCharArray();
        StringBuilder binary = new StringBuilder();
        HashMap<Character, String> hashMap = new HashMap<>();

        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");

        Character[] chars = new Character[ch.length];
        for (int i = 0; i <= ch.length - 1; i++){
            chars[i] = ch[i];
        }
        Stream.of(chars).filter(hashMap::containsKey).forEach(s -> binary.append(hashMap.get(s)));
        return binary.toString();
    }

    public @NotNull String binaryToHex(String hex) {

        hex = hex.toUpperCase();
        String[] txt = new String[hex.length() / 4];
        for (int i = 0; i <= txt.length - 1; i++){
            txt[i] = "";
        }
        StringBuilder binary = new StringBuilder();
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("0000", "0");
        hashMap.put("0001", "1");
        hashMap.put("0010", "2");
        hashMap.put("0011", "3");
        hashMap.put("0100", "4");
        hashMap.put("0101", "5");
        hashMap.put("0110", "6");
        hashMap.put("0111", "7");
        hashMap.put("1000", "8");
        hashMap.put("1001", "9");
        hashMap.put("1010", "A");
        hashMap.put("1011", "B");
        hashMap.put("1100", "C");
        hashMap.put("1101", "D");
        hashMap.put("1110", "E");
        hashMap.put("1111", "F");

        int k = 0;
        for (int i = 0; i <= txt.length - 1; i++){
            for (int j = 0; j <= 3; j++){
                txt[i] += hex.charAt(k);
                k++;
            }
        }
        Stream<String> translate = Stream.of(txt);
        translate.filter(hashMap::containsKey).forEach(s -> {
            binary.append(hashMap.get(s));
        });
        return binary.toString();
    }

    public String binaryToText(String binaryString) {
        if(binaryString.equals("нет данных")){
            return binaryString;
        } else {
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
    }

    protected String byteToBinary(byte byteData) {
        String tempData;
        tempData = String.format("%8s", Integer
                        .toBinaryString(byteData & 0xFF))
                .replace(' ', '0');
        return tempData;
    }

    public String fileToHex(byte data) {
        return (binaryToText(byteToBinary(data)));
    }

    private String[] byteArrToStrArr(byte[] byteData) {
        String[] stringData = new String[byteData.length];
        for (int i = 0; i < byteData.length - 1; i++) {
            stringData[i] = byteToBinary(byteData[i]);
        }
        return stringData;
    }

    public String bigToLittleEndian(String bigendian) {
        ByteBuffer buf = ByteBuffer.allocate(1);

        buf.order(ByteOrder.BIG_ENDIAN);
        buf.putLong(Long.parseLong(bigendian));

        buf.order(ByteOrder.LITTLE_ENDIAN);
        return String.valueOf(buf.getLong(1));
    }
}