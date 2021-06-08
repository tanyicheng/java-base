package com.barrett.test;

public class TestString2 {
    public static void main(String[] args) {
        String str = "temp/SpareGoldApply_1622211837573.bpmn1";

        boolean bpmn = str.endsWith("bpmn");
        str = null;

        assert str == null;
        System.out.println(str);
    }
}
