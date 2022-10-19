package com.barrett.base.正则表达式;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用java自带方法，判断数字和字母
 *
 * @author created by barrett in 2021/11/16 22:22
 **/
public class PatternDemo1 {

    public static void main(String[] args) {
//        PatternDemo1 p = new PatternDemo1();
//        String str = "AA";
//        String number = p.getNumberStr(str);
//        System.out.println(str.replace(number,""));
//        System.out.println(number);
//        System.out.println(p.getNumberStr2(str));
        checkEmail();
    }


    public String getNumberStr(String code) {
        StringBuilder number = new StringBuilder();
        for (char c : code.toCharArray()) {
            //如果是数字
            if (Character.isDigit(c)) {
                number.append(c);
            } else {
                number = new StringBuilder();
            }
        }
        return number.toString();
    }

    public String getNumberStr2(String code) {
        StringBuilder number = new StringBuilder();
        for (char c : code.toCharArray()) {
            //如果是数字
            if (Character.isDigit(c)) {
                number.append(c);
            } else {
                number = new StringBuilder();
            }
        }

        return code.replace(number, "") + getNextNumber(number.toString());
    }

    public String getNextNumber(String str) {
        if (StringUtils.isNotEmpty(str)) {
            int number = Integer.parseInt(str);
            StringBuilder code = new StringBuilder();
            //序列号自增
            number += 1;
            //如果转换数字以后的长度和传参一致，则表示数字开头有0存在，需要补位
            if (Integer.toString(number).length() != str.length()) {
                int dif = str.length() - Integer.toString(number).length();
                for (int i = 0; i < dif; i++) {
                    code.append("0");
                }
            }
            return code.toString() + number;
        }
        return "";
    }

    public static void checkEmail() {
        String email = "Dikw_091.2@163.c";
        String regex = "^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";
        boolean matches = Pattern.matches(regex, email);
        System.out.println(matches);
    }
}
