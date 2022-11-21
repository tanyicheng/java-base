package com.barrett.oss;

import com.qiniu.storage.Region;
import lombok.Data;

import java.util.Properties;

/**
 * 七牛云配置
 * @Author created by barrett in 2022/11/20 08:00
 */
@Data
public class QiNiuConfig {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private Region region;
    private String domainOfBucket;
    private long expireInSeconds;

    private QiNiuConfig() { //单例设计模式
        Properties prop = new Properties();
        try {
            prop.load(QiNiuConfig.class.getResourceAsStream("/qiniu.properties"));
            accessKey = prop.getProperty("qiniu.access-key");
            secretKey = prop.getProperty("qiniu.secret-key");
            bucket = prop.getProperty("qiniu.bucket");
            domainOfBucket = prop.getProperty("qiniu.domain-of-bucket");
            expireInSeconds = Long.parseLong(prop.getProperty("qiniu.expire-in-seconds"));
            String zoneName = prop.getProperty("qiniu.region");
            if (zoneName.equals("region0")) {
                region = Region.region0();
            } else if (zoneName.equals("region1")) {
                region = Region.region1();
            } else if (zoneName.equals("region2")) {
                region = Region.region2();
            } else if (zoneName.equals("regionAs0")) {
                region = Region.regionAs0();
            } else if (zoneName.equals("regionNa0")) {
                region = Region.regionNa0();
            } else {
                throw new Exception("Region对象配置错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static QiNiuConfig instance = new QiNiuConfig();

    public static QiNiuConfig getInstance() {
        return instance;
    }
}