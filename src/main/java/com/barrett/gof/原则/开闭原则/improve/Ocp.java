package com.barrett.gof.原则.开闭原则.improve;

/**
 * 1) 优点是比较好理解，简单易操作。
 * 2) 缺点是违反了设计模式的ocp原则，即对扩展开放(提供方)，对修改关闭(使用方)。
 * 即当我们给类增加新功能的时候，尽量不修改代码，或者尽可能少修改代码.
 * 3) 比如我们这时要新增加一个图形种类 三角形，我们需要做如下修改，修改的地方 较多
 * TODO 改进的思路分析
 * 思路：把创建Shape类做成抽象类，并提供一个抽象的draw方法，让子类去实现即可，
 * 这样我们有新的图形种类时，只需要让新的图形类继承Shape，并实现draw方法即可，
 * 使用方的代码就不需要修改 -> 满足了开闭原则
 *
 * @author created by barrett in 2020/12/15 21:45
 **/
public class Ocp {

    public static void main(String[] args) {
        //使用看看存在的问题
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Triangle());
        //todo 新增的类
        graphicEditor.drawShape(new OtherGraphic());
    }

}

//这是一个用于绘图的类 [使用方]
class GraphicEditor {
    //接收Shape对象，调用draw方法
    public void drawShape(Shape s) {
        s.draw();
    }


}

//Shape类，基类
abstract class Shape {
    int m_type;

	//todo 抽象方法
    public abstract void draw();
}

class Rectangle extends Shape {
    Rectangle() {
        super.m_type = 1;
    }

    @Override
    public void draw() {
        System.out.println(" 绘制矩形 ");
    }
}

class Circle extends Shape {
    Circle() {
        super.m_type = 2;
    }

    @Override
    public void draw() {
        System.out.println(" 绘制圆形 ");
    }
}

//新增画三角形
class Triangle extends Shape {
    Triangle() {
        super.m_type = 3;
    }

    @Override
    public void draw() {
        System.out.println(" 绘制三角形 ");
    }
}

//todo 新增一个图形,每个子类实现父类方法
class OtherGraphic extends Shape {
    OtherGraphic() {
        super.m_type = 4;
    }

    @Override
    public void draw() {
        System.out.println(" 绘制其它图形 ");
    }
}
