package com.barrett.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Http 工具
 * @author fishcat
 * @date 2018/11/22 9:15 AM
 */
public enum HttpUtils {

    //
    INSTANCE;

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    private CloseableHttpClient client = HttpClients.custom().build();

    private final Integer TIMEOUT = 5000;

    private RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(TIMEOUT)
            .setConnectTimeout(TIMEOUT)
            .setConnectionRequestTimeout(TIMEOUT)
            .build();

    private Consumer<Exception> globalExceptionHandler;

    public void registerGlobalExceptionHandler(Consumer<Exception> globalExceptionHandler) {
        this.globalExceptionHandler = globalExceptionHandler;
    }

    public JSONObject get(String url) {
        return get(url, null, globalExceptionHandler);
    }

    public JSONObject get(String url, Header[] headers) {
        return get(url, headers, globalExceptionHandler);
    }

    private JSONObject get(String url, Header[] headers, Consumer<Exception> errorCallback) {
        try {
            // Get 请求
            HttpGet request = new HttpGet(url);
            request.setConfig(requestConfig);
            if (headers != null) request.setHeaders(headers);

            // 发起请求
            HttpEntity entity = client.execute(request).getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            log.info("Get请求 [{}] 响应结果 [{}]", url, result);

            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error("Get请求 [{}] 发生异常 [{}]", url, e);
            if (errorCallback != null) errorCallback.accept(e);
        }
        return null;
    }

    public JSONObject post(String url, String params) {
        return post(url, params, null, globalExceptionHandler);
    }

    public JSONObject post(String url, String params, Header[] headers) {
        return post(url, params, headers, globalExceptionHandler);
    }

    private JSONObject post(String url, String params, Header[] headers, Consumer<Exception> errorCallback) {
        try {
            // Post 请求
            HttpPost request = new HttpPost(url);
            request.setConfig(requestConfig);
            if (params != null) request.setEntity(new StringEntity(params, "UTF-8"));
            if (headers != null) request.setHeaders(headers);

            // 发起请求
            HttpEntity entity = client.execute(request).getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            log.info("Post请求 [{}] 响应结果 [{}]", url, result);

            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error("Post请求 [{}] 发生异常 [{}]", url, e);
            if (errorCallback != null) errorCallback.accept(e);
        }
        return null;
    }

    public JSONObject upload(String url, Header[] headers, String formFileFieldName, // 默认为file
                             Map<String, Object> params, String fileName, byte[] fileBytes) {
        try {
            // Post 请求
            HttpPost request = new HttpPost(url);
            request.setConfig(requestConfig);
            if (headers != null) request.setHeaders(headers);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody(formFileFieldName == null ? "file" : formFileFieldName, fileBytes, ContentType.APPLICATION_OCTET_STREAM, fileName);
            if (params != null) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    builder.addTextBody(entry.getKey(), entry.getValue().toString());
                }
            }
            request.setEntity(builder.build());

            // 发起请求
            HttpEntity entity = client.execute(request).getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            log.info("Post请求 [{}] 响应结果 [{}]", url, result);

            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error("Post请求 [{}] 发生异常 [{}]", url, e);
            if (globalExceptionHandler != null) globalExceptionHandler.accept(e);
        }
        return null;
    }
}
