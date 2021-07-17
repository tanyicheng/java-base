package com.barrett.base.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.barrett.beans.Person;

/**
 * 对象、字符、json对象互转
 *
 * @author created by barrett in 2021/6/25 14:42
 **/
public class JsonUtils {

    public static void main(String[] args) {
        String str = objToJsonStr();
        jsonStrToJsonObj(str);

        String abc = "qweqwe qweqew oiuouo asdsad";
        String[] split = abc.split("abcdefghijklmnopqrstuvwxyz1234567890");
        System.out.println(split.length);

    }

    public static String objToJsonStr() {

        Person user = new Person();
        user.setId(1);
        user.setName("lzc");
        String json = JSON.toJSONString(user);//关键
        System.out.println(json);
        return json;
    }

    public static void jsonStrToJsonObj(String str){
        //TODO 注意对象必须要有全部参数的构造器，否则数据装换会丢失
        Person json = JSONObject.parseObject(str,Person.class);
        String name = json.getName();

        System.out.println(name);

    }
}
