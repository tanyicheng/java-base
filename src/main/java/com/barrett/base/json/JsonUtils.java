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
        Person json = JSONObject.parseObject(str,Person.class);
        String name = json.getName();

        System.out.println(name);

    }
}
