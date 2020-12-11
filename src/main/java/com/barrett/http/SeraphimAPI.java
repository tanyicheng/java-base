package com.barrett.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import static com.barrett.http.ApiConfig.loginApi;


public class SeraphimAPI {

    public static void main(String[] args) throws Exception {

//        new SeraphimAPI().test();
        new SeraphimAPI().get();

    }

    public void test() throws Exception {
        // 1. 设置客户端
        CloseableHttpClient client = HttpClients.createDefault();

        // 2. 发起请求
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(15000)
                .setConnectTimeout(15000)
                .setConnectionRequestTimeout(15000)
                .build();
        HttpGet httpGet = new HttpGet(String.format("%s%s?staffId=%s&password=%s", ApiConfig.baseApi, loginApi, ApiConfig.loginUsername, ApiConfig.getInstance().toMD5(ApiConfig.loginPassword)));
        httpGet.setConfig(requestConfig);

        System.out.println("开始登录");

        CloseableHttpResponse response = client.execute(httpGet);

        // 3. 获取响应实体
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        System.out.println(result);

        if (result != null && !result.equals("")) {
            JSONObject body = JSON.parseObject(result);
            if (!"ok".equalsIgnoreCase(body.getString("result"))) {
                System.out.println(String.format("登录失败，原因：%s", body.getString("msg")));
                return;
            }

            JSONObject data = body.getJSONObject("data");
            String staffId = data.getString("staffId");
            String staffName = data.getString("staffName");
            String cookie = String.format("staffId=%s; tenant=%s", staffId, "seraphim");   // 如果存在中文需要 Base64 编码
            ApiConfig.cookie = cookie;
            System.out.println(String.format("登录成功,%s",  cookie));
            System.out.println("--------"+cookie+"----------");

        }

        System.out.println("登录结束");
    }

    public void get() {
        try {
//            String url = String.format("%s%s", ApiConfig.baseApi, ApiConfig.nameplateGetApi);
            String url = ApiConfig.baseApi + "/sys/role";
            System.out.println(url);
            JSONObject body = HttpUtils.INSTANCE.get(url + "/1", ApiConfig.getInstance().buildJsonHeaders());

            Object data = body.get("data");
//            final JSONObject jsonObject = JSON.parseObject(data.toString());
//            jsonObject.get("desc");
            System.out.println("data："+body.get("data").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

