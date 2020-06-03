package com.barrett.base.lambda;

/**
 * lambda 表达式推导
 * @Author created by barrett in 2020/6/3 16:50
 */
public class LambdaTests2 {

    public static void main(String[] args) {
        Ilove love = (int a,int b) ->{
            System.out.println("结果是："+(a+b));
            return a+b;
        };
        love.lambda(5,6);

        love = (a,b)->{
            System.out.println("结果是："+(a+b));
            return a+b;
        };
        int result = love.lambda(3, 9);

        love=(a,b)-> a+b;
    }
}

interface Ilove{
    int lambda(int a,int b);
}

class Love implements Ilove{

    @Override
    public int lambda(int a,int b) {
        return a+b;
    }
}
