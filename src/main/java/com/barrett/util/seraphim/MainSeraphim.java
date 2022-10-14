package com.barrett.util.seraphim;


import com.barrett.util.seraphim.temp.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * seraphim 项目
 * 页面代码参考
 * 【焊接排版机配置】常用CRUD
 *
 * @Author created by barrett in 2019/1/5 21:12
 */
public class MainSeraphim {

    // dao、service、mapper、controller 需要放入的路径
    public final static String urlName = "src/main/java/com/barrett/util/seraphim/code";
    // 固定字段
    public static String[] params = {"rowId", "createdBy", "createdTime", "updateBy", "updateTime", "isValid"};
    //fixme 需要生成的类
    public static Object object = new WorkFlowAgent();
    //fixme 表名
    public static String table;

    static {
        try {
            Field field = object.getClass().getField("TableName");
            table = (String) field.get(object);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //批量插入时主表id,这里主要用来mapper.xml中替换关联的id
    //fixme 手动修改字段
    public static String parentId = "parentId";

    //flag = 2 需要增加批量插入; 手动=1
    static int flag = 2;

    /**
     * TODO 准备工作：
     * 以实体类为主：添加 TableName
     * 去掉 BaseBean 中包含的字段
     *
     * @Author created by barrett in 2019/1/5 14:44
     */
    public static void main(String[] args) throws InterruptedException {

        MainSeraphim mc = new MainSeraphim();

        if(StringUtils.isEmpty(table)){
            System.out.println("table name is null");
            return;
        }
        //0、建表
        createTbaleSql();
//         1、TODO Dao
        mc.createDao(flag);
//         2、TODO Mapper
        daoPackage = daopath.replace("/", ".") + "." + daoClassName;
        mc.createMapper(flag);

        // 3、生成 Service
        mc.createIService();
        mc.createService();

        // 4、生成 Controller
        servicePackage = servicePath.replace("/", ".") + "." + serviceClassName;
        mc.createCtrl();

//        getParam(object);
    }


    public static String getParam(Object obj) {
        Field[] field = obj.getClass().getDeclaredFields();
        System.out.println("{");
        for (int i = 0; i < field.length; i++) {
            String name = field[i].getName();
            System.out.println("\""+name+"\""+":"+"\""+i+"\",");
        }
        System.out.println("}");
        return null;
    }

    /**
     * 建表
     * @Author created by barrett in 2019/1/13 15:09
     */
    public static void createTbaleSql() {
        SeraphimMapper.fixParam = SeraphimMapper.fixedParams(MainSeraphim.params);
        SeraphimMapper.printCreateTable();
    }
    // Mapper =================== start
    public static String prefix = "t.";
    //类名
    public static String className = object.getClass().getSimpleName();
    //类路径
    public static String classPath = object.getClass().getName();
    // 首字母小写的类名
    public static String smailclName = SeraphimMapper.lowerFirst(className);
    // Mapper =================== end

    public final static String daopath = urlName;
    public final static String servicePath = urlName;
    public final static String ctrlPath = urlName;
    public final static String mapperPath = urlName;

    // dao 类名
    public static String daoPackage = "Dao未找到";
    public static String servicePackage = "Service未找到";

    public final static String daoPostfix = "Mapper";
    public final static String mapperPostfix = "Mapper";
    public final static String servicePostfix = "ServiceImpl";
    public final static String iservicePostfix = "Service";
    public final static String ctrlPostfix = "Controller";

    public final static String daoClassName = className + daoPostfix;
    public final static String daoSmailclName = SeraphimMapper.lowerFirst(daoClassName);
    public final static String fileName = "/" + className;
    public final static String serviceClassName = className + servicePostfix;
    public final static String serviceSmailclName = SeraphimMapper.lowerFirst(serviceClassName);


    /**
     * flag: 2增加批量插入
     *
     * @Author created by barrett in 2019/4/1 09:17
     */
    public void createDao(int flag) {
        String str = SeraphimDao.getDao(flag);
        WriteFile.createJavaPath(daopath, fileName + daoPostfix, str);
//        System.out.println(str);
    }

    /**
     * flag: 2增加批量插入
     *
     * @Author created by barrett in 2019/4/1 09:17
     */
    public void createMapper(int flag) {

        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
                "<mapper namespace=\"" + daoPackage + "\">\n";
        SeraphimMapper.fixParam = SeraphimMapper.fixedParams(MainSeraphim.params);
        str += SeraphimMapper.getResultMap(object);//打印 resultMap (根据实体类)
        str += SeraphimMapper.getSql(object, prefix, true);//根据实体类查询 Base_Column_List
        str += SeraphimMapper.getSelect(object);
        str += SeraphimMapper.getInsert(object);
        str += SeraphimMapper.getUpdate(object);
        str += SeraphimMapper.getById(object);
        str += SeraphimMapper.getUpdateRecordById(object);
        if(flag == 2){
            str+= SeraphimMapper.getInsertBatch(object);
        }
        str += SeraphimMapper.getDelete(object);
        str += "</mapper>";

//        System.out.println(str);
        WriteFile.createXmlPath(mapperPath, fileName + mapperPostfix, str);
    }

    public void createService() {
        daoPackage = daopath.replace("/", ".") + "." + daoClassName;
        String str = SeraphimService.getService();
//        System.out.println(str);
        WriteFile.createJavaPath(servicePath, fileName + servicePostfix, str);
    }

    //service 接口
    public void createIService() {
        daoPackage = daopath.replace("/", ".") + "." + daoClassName;
        String str = ISeraphimService.getService();
//        System.out.println(str);
        WriteFile.createJavaPath(servicePath, fileName + "Service", str);
    }

    public void createCtrl() {
        servicePackage = servicePath.replace("/", ".") + "." + serviceClassName;
        String str = SeraphimController.getController();
//        System.out.println(str);
        WriteFile.createJavaPath(ctrlPath, fileName + ctrlPostfix, str);
    }

}
