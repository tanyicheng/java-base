package com.barrett.base.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.barrett.beans.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象、字符、json对象互转
 *
 * @author created by barrett in 2021/6/25 14:42
 **/
public class JsonUtils {

    public static void main(String[] args) {
//        String str = objToJsonStr();
//        jsonStrToJsonObj(str);
        String str = listToJsonStr();
        jsonStrToList(str);
    }

    public static String objToJsonStr() {

        Person user = new Person();
        user.setId(1);
        user.setName("lzc");
        String json = JSON.toJSONString(user);//关键
        System.out.println(json);
        return json;
    }

    public static String listToJsonStr() {

        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("name","aa");
        map.put("age","100");
        list.add(map);
        list.add(map);
        list.add(map);

        String json = JSON.toJSONString(list);//关键
        System.out.println(json);
        return json;
    }

    public static void jsonStrToJsonObj(String str){
        //TODO 注意对象必须要有全部参数的构造器，否则数据装换会丢失
        Person json = JSONObject.parseObject(str,Person.class);
        String name = json.getName();

        System.out.println(name);
    }

    public static void jsonStrToList(String str){
        //TODO 注意对象必须要有全部参数的构造器，否则数据装换会丢失
        List<Map<String,String>> list = JSONObject.parseObject(str,List.class);
        for (Map<String, String> map : list) {
            System.out.println(map.get("name")+" - "+map.get("age"));
        }

    }


}
