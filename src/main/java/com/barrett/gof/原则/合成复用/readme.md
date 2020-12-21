#B类调用 A类方法

聚合：设置成员变量，通过set方法传递
```
private A a;
a.setA(A a){
    this.a=a;
}
```

依赖：通过方法参数传递A类对象
```
public void useA(A a)
```

组合：设置成员变量，直接新建对象，初始化B类时，A类也会初始化，耦合性比上面2种高
```
private A a = new A();
```