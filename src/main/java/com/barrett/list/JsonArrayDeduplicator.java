package com.barrett.list;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//list 去除重复key, 合并value
public class JsonArrayDeduplicator {
    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(new JSONObject().put("id", 1).put("value", "10"));
        jsonArray.put(new JSONObject().put("id", 2).put("value", "20"));
        jsonArray.put(new JSONObject().put("id", 3).put("value", "30"));
        jsonArray.put(new JSONObject().put("id", 1).put("value", "40"));
        jsonArray.put(new JSONObject().put("id", 2).put("value", "50"));
        jsonArray.put(new JSONObject().put("id", 1).put("value", "60"));

        Map<Integer, JSONObject> deduplicatedMap = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject item = jsonArray.getJSONObject(i);
            int id = item.getInt("id");
            if (deduplicatedMap.containsKey(id)) {
                JSONObject existingItem = deduplicatedMap.get(id);
                existingItem.put("value", existingItem.getStr("value") + ","+item.getStr("value"));
            } else {
                deduplicatedMap.put(id, item);
            }
        }

        JSONArray deduplicatedJsonArray = new JSONArray();
        deduplicatedMap.values().forEach(deduplicatedJsonArray::put);

        System.out.println(deduplicatedJsonArray);
    }
}