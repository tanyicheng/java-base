package com.barrett.api;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.security.MessageDigest;

public class ApiConfig {
    private ApiConfig() {
    }

    public static ApiConfig getInstance() {
        return new ApiConfig();
    }

    static String baseApi = "http://127.0.0.1";
    static String loginApi = "/sys/login/doLogin";
    static String loginUsername = "S1561";
    static String loginPassword = "888888";
    static String nameplateGetApi = "/newmes/namePlate/client/getNamePlateListByBarCode";
    static String cookie = "staffId=S1561; tenant=seraphim";

    public String toMD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public Header[] buildJsonHeaders() {
        try {
            Header[] headers = new Header[4];
            headers[0] = new BasicHeader("Cookie", this.cookie);
            headers[1] = new BasicHeader("Content-Type", "application/json; charset=utf-8");
            headers[2] = new BasicHeader("Accept", "application/json");
            headers[3] = new BasicHeader("Accept-Charset", "UTF-8");
            return headers;
        } finally {
        }
    }
}
