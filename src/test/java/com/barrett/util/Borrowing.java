package com.barrett.util;

/**
 * 贷款计算
 * 未完成
 * @author created by barrett in 2021/5/23 19:35
 **/
public class Borrowing {
    //贷款本金
    public long benjin = 60 * 1000;
    //组合贷利率
    public double zuhelilv = 5.2 / 100;
    //纯商贷
    public double shanglilv = 5.2 / 100;
    //存款年限
    public int year = 20;

    public static void main(String[] args) {
        Gjj gjj = new Gjj();
        Double glixi = gjj.lixi();
        Double slixi = gjj.lixi();
        System.out.println(glixi + slixi);
    }


}

/**
 * 公积金
 *
 * @author created by barrett in 2021/5/23 19:41
 **/
class Gjj {
    //贷款本金
    public long benjin = 30 * 10000;
    //利率
    public double shanglilv = 3.25 / 100;
    //存款年限
    public int year = 20;

    public Double lixi() {
        double result = benjin * shanglilv * year;

        System.out.println(result);
        return result;
    }
}

/**
 * 商贷
 *
 * @author created by barrett in 2021/5/23 19:43
 **/
class Shang {
    //贷款本金
    public long benjin = 60 * 10000;
    //利率
    public double lilv = 5.25 / 100;
    //存款年限
    public int year = 20;

    public Double lixi() {
        double result = benjin * lilv * year;

        System.out.println(result);
        return result;
    }
}