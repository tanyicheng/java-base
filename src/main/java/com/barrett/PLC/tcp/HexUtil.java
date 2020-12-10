package com.barrett.PLC.tcp;

import java.util.*;

public class HexUtil {

    public static void main(String[] args) {
        String str = "01 03 04 01 C4 00 EA 3B BD";
        byte[] bytes = hexStringToByte(str);

        for (byte s : bytes) {
            System.out.println(s);
        }
    }

    /**
     * 
     * 把16进制字符串转换成字节数组
     * 
     * @author shigui.yu
     * @date Sep 7, 2016 10:39:40 AM
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    public static List<Byte> hexStringToByteList(String hex) {
        int len = (hex.length() / 2);
        char[] achar = hex.toCharArray();
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            list.add((byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1])));
        }
        return list;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * 把字节数组转换成16进制字符串
     * 
     * @author shigui.yu
     * @date Sep 7, 2016 10:39:31 AM
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 
     * 返回固定长度的16进制字符串
     * 
     * @author shigui.yu
     * @date Sep 7, 2016 10:41:34 AM
     * @param num
     * @param length
     * @param swapHL 高低位互换
     * @return
     */
    public static final String intToHexString(int num, int length, boolean swapHL) {
        String hex = Integer.toHexString(num).toUpperCase();
        if (length % 2 != 0) {
            return hex;
        }

        if (hex.length() < length) {
            int len = hex.length();
            for (int i = 0; i < length - len; i++) {
                hex = "0" + hex;
            }
        } else {
            hex = hex.substring(0, length);
        }
        if (length > 2 && swapHL) {
            int len = length / 2;
            String[] arr = new String[len];
            for (int i = 0; i < len; i++) {
                arr[i] = hex.substring(i * 2, (i + 1) * 2);
            }
            Collections.reverse(Arrays.asList(arr));
            String str = "";
            for (String item : arr) {
                str += item;
            }
            hex = str;
        }
        return hex;
    }

    /**
     * 
     * 16进制字符串转化为数字
     * 
     * @author shigui.yu
     * @date Sep 7, 2016 11:15:45 AM
     * @param hex
     * @param swapHL
     * @return
     */
    public static final int hexStringToInt(String hex, boolean swapHL) {
        if (swapHL) {
            int len = hex.length() / 2;
            String[] arr = new String[len];
            for (int i = 0; i < len; i++) {
                arr[i] = hex.substring(i * 2, (i + 1) * 2);
            }
            Collections.reverse(Arrays.asList(arr));
            String str = "";
            for (String item : arr) {
                str += item;
            }
            hex = str;
        }
        return Integer.parseInt(hex, 16);
    }

    /**
     * 
     * 四个16进制字符串转换成2个字符
     * 
     * @author shigui.yu
     * @date Sep 7, 2016 12:33:48 PM
     * @param hex
     * @return
     */
    public static final String hex4ToStr2(String hex) {
        if (hex.length() != 4) {
            return "";
        }
        String str1 = hex.substring(0, 2);
        String str2 = hex.substring(2, 4);
        StringBuilder sb = new StringBuilder("");
        if (!"00".equals(str1)) {
            sb.append((char) Integer.parseInt(str1, 16));
        }
        if (!"00".equals(str2)) {
            sb.append((char) Integer.parseInt(str2, 16));
        }
        return sb.toString();
    }

    /**
     * 
     * 两个字符转成四个16进制字符
     * 
     * @author shigui.yu
     * @date Sep 7, 2016 12:58:39 PM
     * @param str
     * @return
     */
    public static final String str2ToHex4(String str, boolean swapHL) {
        char[] chars = str.toCharArray();
        String hexH = Integer.toHexString(chars[0]).toUpperCase();
        String hexL = "00";
        if (str.length() == 2) {
            hexL = Integer.toHexString(chars[1]).toUpperCase();
        }
        if (swapHL) {
            return hexL + hexH;
        } else {
            return hexH + hexL;
        }
    }

    /**
     * 
     * 将字符串拆分成每个元素2个长度的字符串数组
     * 
     * @author shigui.yu
     * @date Sep 8, 2016 2:09:37 PM
     * @param str
     * @return
     */
    public static final String[] strSplitTo2lenArr(String str) {
        int len = str.length() / 2;
        if (str.length() % 2 != 0) {
            len++;
        }
        String[] arr = new String[len];
        for (int i = 0; i < len; i++) {
            if (i == len - 1) {
                arr[i] = str.substring(i * 2);
            } else {
                arr[i] = str.substring(i * 2, (i + 1) * 2);
            }
        }
        return arr;
    }

    /**
     * 
     * 字符数组合成字符串
     * 
     * @author shigui.yu
     * @date Sep 17, 2016 1:49:39 PM
     * @param arr
     * @return
     */
    public static final String arrToStr(String[] arr) {
        if (arr != null && arr.length > 0) {
            StringBuilder sb = new StringBuilder("");
            for (String item : arr) {
                sb.append(hex4ToStr2(item));
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * 
     * 获取len长度的0
     * 
     * @author shigui.yu
     * @date Dec 29, 2016 1:27:05 PM
     * @param len
     * @return
     */
    public static final String getLengZero(int len) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < len; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    public static byte[] listToByte(List<Byte> list) {
        if (list == null || list.size() < 0)
            return null;
        byte[] bytes = new byte[list.size()];
        int i = 0;
        Iterator<Byte> iterator = list.iterator();
        while (iterator.hasNext()) {
            bytes[i] = iterator.next();
            i++;
        }
        return bytes;
    }
}
