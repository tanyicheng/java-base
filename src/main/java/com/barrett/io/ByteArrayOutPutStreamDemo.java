package com.barrett.io;

import java.io.ByteArrayOutputStream;

/**
 * 字节数组流：输入流
 * 1、创建源: 内部维护
 * 2、选择流：不关联源
 * 3、操作（读）
 * 4、释放资源：GC自动关闭
 *
 * @Author created by barrett in 2020/5/14 23:05
 */
public class ByteArrayOutPutStreamDemo {

    public static void main(String[] args) {
        test3();

    }

    static void test3() {
        byte[] src = "Talk is cheap, show me the code.".getBytes();

        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();

            os.write(src);
            os.flush();

            byte[] dest = os.toByteArray();
            System.out.println(dest.length+" ---> "+new String(dest,0,os.size()));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
