package org.mai.project.obstanovka;

import org.jetbrains.annotations.NotNull;
import java.util.HashMap;


public class Decoder {

    private String firstIt;
    private String secondIt;

    protected String getPrettyView(String code) {
        if (code.startsWith(" ")){
            code = code.replace(" ", "");
        }else{
            //code = code.replace(" ", "");
            code = code + " ";
        }
        return code;
    }

    protected String[] twoBytesGroup(FilesTool f){
        byte[] tempData = f.getByteData();
        String[] convertedData;
        boolean two = true;
        int j = 0;
        byte[] byteData;
        for (int i = 0; i <= tempData.length - 1; i++) {
                String first = f.obstanovkaParsing(tempData[i]);
                if (!(first.equals("\r") ||
                        first.equals("\n") ||
                        first.equals(" "))) {
                    System.out.println(first);
                    j++;
                }
        }
        byteData = new byte[j];
        j = 0;
        for (int i = 0; i <= tempData.length - 1; i++){
            String first = f.obstanovkaParsing(tempData[i]);
            if (!(first.equals("\r") ||
                    first.equals("\n") ||
                    first.equals(" "))) {
                byteData[j] = tempData[i];
                j++;
            }
        }

        if (byteData.length % 2 == 0) {
            convertedData = new String[byteData.length / 2];
        } else {
            convertedData = new String[(byteData.length + 1) / 2];
            two = false;
        }

        j = 0;
            for (int i = 0; i <= byteData.length - 1; i = i + 2) {
                    if (i != byteData.length - 1) {
                        convertedData[j] = f.obstanovkaParsing(byteData[i]) + f.obstanovkaParsing(byteData[i + 1]);
                        j++;
                    } else {
                        convertedData[j] = f.obstanovkaParsing(byteData[i])/* + "нечетное кол-во байт в файле"*/;
                    }
            }
        return convertedData;
    }

    protected @NotNull String hexToBinary(String hex) {

        hex = hex.toUpperCase();
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

        int i;
        char ch;
        for (i = 0; i < hex.length(); i++) {
            ch = hex.charAt(i);
            if (hashMap.containsKey(ch))
                binary.append(hashMap.get(ch));
            else if (ch == ' ') {
                binary.append(" ");
            }
        }
        return binary.toString();
    }
}
