package com.barrett.util.seraphim;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {


    public static void createJava(String path, String name, String content) {
        String pathName = "src/main/java/" + path + name+".java";
        System.out.println(pathName);
        File file = new File(pathName);
        FileOutputStream out = null;
        try {
            // true 追加内容，false: 覆盖
            out = new FileOutputStream(file, false);
            if (!file.exists()) {
                file.createNewFile();
            }
            StringBuffer sb = new StringBuffer();
            // 引入的包名
            String pkname = path.replace("/", ".");
            sb.append("package " + pkname+";\n");
            // 引入实体类名
            sb.append("import " + MainSeraphim.classPath +";\n");
            sb.append(content);
            out.write(sb.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void createXml(String path, String name, String content) {
        String pathName = "src/main/java/" + path + name+".xml";
        System.out.println(pathName);
        File file = new File(pathName);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file, false);
            if (!file.exists()) {
                file.createNewFile();
            }
            StringBuffer sb = new StringBuffer();
            sb.append(content);
            out.write(sb.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void createJS(String path, String name, String content) {
        String pathName = "src/test/java/com/fancheng/createdCode/" + path + name+".js";
        System.out.println(pathName);
        File file = new File(pathName);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file, false);
            if (!file.exists()) {
                file.createNewFile();
            }
            StringBuffer sb = new StringBuffer();
            sb.append(content);
            out.write(sb.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 全路径
     * @Author created by barrett in 2019/2/22 10:21
     */
    public static void createJavaPath(String path, String name, String content) {
        String pathName = path + name+".java";
        System.out.println(pathName);
        File file = new File(pathName);
        FileOutputStream out = null;
        try {
            // true 追加内容，false: 覆盖
            out = new FileOutputStream(file, false);
            if (!file.exists()) {
                file.createNewFile();
            }
            StringBuffer sb = new StringBuffer();
            // 引入的包名
            String pkname = path.replace("/", ".");
            sb.append("package " + pkname+";\n");
            // 引入实体类名
            sb.append("import " + MainSeraphim.classPath +";\n");
            sb.append(content);
            out.write(sb.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 全路径
     * @Author created by barrett in 2019/2/22 10:21
     */
    public static void createXmlPath(String path, String name, String content) {
        String pathName = path + name+".xml";
        System.out.println(pathName);
        File file = new File(pathName);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file, false);
            if (!file.exists()) {
                file.createNewFile();
            }
            StringBuffer sb = new StringBuffer();
            sb.append(content);
            out.write(sb.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
