package com.barrett.base.lambda;

/**
 * lambda 表达式推导
 * @Author created by barrett in 2020/6/3 16:50
 */
public class LambdaTests {
    //2、使用内部类
    static class Like2 implements Ilike{

        @Override
        public void lambda() {
            System.out.println("I like Lambda2");
        }
    }
    public static void main(String[] args) {
        Ilike like= new Like();
        like.lambda();
        like = new Like2();
        like.lambda();

        //3、方法内部类
        class Like3 implements Ilike{

            @Override
            public void lambda() {
                System.out.println("I like Lambda3");
            }
        }
        like = new Like3();
        like.lambda();

        //4、匿名内部类
        like = new Ilike() {
            @Override
            public void lambda() {
                System.out.println("I like Lambda4");
            }
        };
        like.lambda();

        //4、lambda （接口中只能有一个方法）
        like = ()->{
            System.out.println("I like Lambda5");
        };
        like.lambda();
    }
}

interface Ilike{
    void lambda();
}
//1、外部类
class Like implements Ilike{

    @Override
    public void lambda() {
        System.out.println("I like Lambda");
    }
}