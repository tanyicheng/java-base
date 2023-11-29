package com.barrett.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.List;

public class JsonTest {

    String jsonstr = "[{\"planIds\": \"1709825468523417601,1709821020405297153,1709554545459216386,1709554523426537474\", \"planList\": [{\"id\": \"1709825468523417601\", \"name\": \"核心肌耐力\"}, " +
            "{\"id\": \"1709821020405297153\", \"name\": \"强化手臂日\"}, {\"id\": \"1709554545459216386\", \"name\": \"倒三角塑形\"}, {\"id\": \"1709554523426537474\", \"name\": \"练腿日\"}], \"whichDay\": 1}, {\"planIds\": \"1709554545459216386\", \"planList\": [{\"id\": \"1709554545459216386\", \"name\": \"倒三角塑形\"}], \"whichDay\": 2}, {\"planIds\": \"\", \"planList\": [], \"whichDay\": 3}, {\"planIds\": \"1631793002594353154\", \"planList\": [{\"id\": \"1631793002594353154\", \"name\": \"铠甲胸训练计划\"}], \"whichDay\": 4}, {\"planIds\": \"1709554545459216386\", \"planList\": [{\"id\": \"1709554545459216386\", \"name\": \"倒三角塑形\"}], \"whichDay\": 5}, {\"planIds\": \"1709554523426537474\", \"planList\": [{\"id\": \"1709554523426537474\", \"name\": \"练腿日\"}], \"whichDay\": 6}, {\"planIds\": \"\", \"planList\": [], \"whichDay\": 7}]";

    @Test
    public void parse(){
        List<JSONObject> list = (List<JSONObject>) JSONArray.parse(jsonstr);

        for (JSONObject obj : list) {
            JSONArray planList = obj.getJSONArray("planList");
            for (Object o : planList) {
                JSONObject jo  = (JSONObject) o;
                String name = jo.getString("name");
            }
        }
        System.out.println("结束-----");
    }
}
