package com.barrett.gof.策略模式.mes;

public class MainTest {
    public static void main(String[] args) {
        ProdLine pl = new ProdLine();
        pl.setTenant(new SeraphimMes());
        pl.setSite(new StackUp());

        //开始刷入
        brush(pl);
    }


    public static void brush(ProdLine prodLine){

        prodLine.brush();

    }
}
