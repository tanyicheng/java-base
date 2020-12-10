package com.barrett.PLC.serial;


public class HexstringtoByte {

    public static void main(String[] args) {
        String s = "01030401C400EA3BBD";
        byte[] a = HexstringtoByte.decodeHex(s);
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }


    public static byte[] decodeHex(String hex) {
        char[] chars = hex.toCharArray();
        for (int i = 0; i < chars.length; i++) {
//            System.out.println(chars[i]);
        }

        byte[] bytes = new byte[chars.length / 2];
        int byteCount = 0;
        for (int i = 0; i < chars.length; i += 2) {
            byte newByte = 0x00;
            newByte |= hexCharToByte(chars[i]);
            newByte <<= 4;
            newByte |= hexCharToByte(chars[i + 1]);
            bytes[byteCount] = newByte;
            byteCount++;
        }
        return bytes;
    }

    private static byte hexCharToByte(char ch) {
        switch (ch) {
            case '0':
                return 0x00;
            case '1':
                return 0x01;
            case '2':
                return 0x02;
            case '3':
                return 0x03;
            case '4':
                return 0x04;
            case '5':
                return 0x05;
            case '6':
                return 0x06;
            case '7':
                return 0x07;
            case '8':
                return 0x08;
            case '9':
                return 0x09;
            case 'a':
                return 0x0A;
            case 'b':
                return 0x0B;
            case 'c':
                return 0x0C;
            case 'd':
                return 0x0D;
            case 'e':
                return 0x0E;
            case 'f':
                return 0x0F;
        }
        return 0x00;
    }
}
