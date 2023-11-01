package com.barrett.mapStruct.demo1;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;

public class AttributeConvertUtil {
    /**
     * json字符串转对象
     *
     * @param jsonStr
     * @return
     */
    @Named("jsonToObject")
    public Attributes jsonToObject(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        return JSONObject.parseObject(jsonStr, Attributes.class);
    }
}
