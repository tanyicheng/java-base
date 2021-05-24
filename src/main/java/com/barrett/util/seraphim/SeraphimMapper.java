package com.barrett.util.seraphim;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

// 创建mybatis xml需要的
public class SeraphimMapper {

    public static List<String> fixParam = new ArrayList<>();

    public static void printResultMap() {
        String resultMap = getResultMap(MainSeraphim.object);//打印 resultMap (根据实体类)
        System.out.println(resultMap);
    }

    public static void printBaseColumn() {
        String sql = getSql(MainSeraphim.object, MainSeraphim.prefix, true);//根据实体类查询 Base_Column_List
        System.out.println(sql);
    }

    public static void printSelectAll() {
        String sql = getSelect(MainSeraphim.object);
        System.out.println(sql);
    }

    public static void printInsert() {
        String sql = getInsert(MainSeraphim.object);
        System.out.println(sql);
    }

    public static void printUpdate() {
        String sql = getUpdate(MainSeraphim.object);
        System.out.println(sql);
    }

    public static void printGet() {
        String sql = getById(MainSeraphim.object);
        System.out.println(sql);
    }

    public static void printGetUpdate() {
        String sql = getUpdateRecordById(MainSeraphim.object);
        System.out.println(sql);
    }

    public static void printCreateTable() {
        String sql = createTable(MainSeraphim.object);
        System.out.println(sql);
    }

    public static void main(String[] args) {
        fixParam = fixedParams(MainSeraphim.params);
        String delete = getDelete(MainSeraphim.object);
        System.out.println(delete);
        // 创建表 sql
//        printCreateTable();
//        printResultMap();
//        printBaseColumn();
//        printSelectAll();
//        printInsert();
//        printUpdate();
//        printGet();
//        printGetUpdate();
    }

    /**
     * 打印resultMap(根据实体类)
     *
     * @Author created by barrett in 2018/12/14 16:34
     */
    public static String getResultMap(Object t) {
        Field[] field = t.getClass().getDeclaredFields();
        String result = "";
        result += "<resultMap id=\"BaseResultMap\" type=\"" + t.getClass().getName() + "\">\n"
                + "  <id column=\"row_id\" property=\"rowId\"/>\n";
        // 添加固定字段
        for (String s : fixParam) {
            if (!s.contains("id")) {
                String[] arr = s.split("=");
                result += "  <result column=\"" + arr[1] + "\" property=\"" + arr[0] + "\" />\n";
            }
        }
        for (Field f : field) {
            String str = f.getGenericType().toString();
            //获取 mybatis jdbcType类型
//            String jdbcType = getJdbcType(str);
            String fieldName = f.getName();
            //拼接get方法
//			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            String pro = propertyToField(fieldName);
            //拼接目标：<result column="ROW_ID" property="rowId" jdbcType="BIGINT"></result> 不加此列：jdbcType=\""+jdbcType+"\"
            if (!StringUtils.isEmpty(pro))
                result += "  <result column=\"" + pro + "\" property=\"" + fieldName + "\" />\n";
        }

        result += "</resultMap>\n\n";
        return result;
    }

    /**
     * 获取查询类型 Base_Column_List
     *
     * @return id, status, is_del, create_time, create_by, update_time, update_by
     * @Param flag 是否打印sql 标签 <sql id="Base_Column_List">
     * @Author created by barrett in 2018/12/14 16:39
     */
    public static String getSql(Object o, String prefix, boolean flag) {
        Field[] field = o.getClass().getDeclaredFields();
        String result = "";
        if (flag) {
            result = "<sql id=\"Base_Column_List\">\n  ";
        }
//        result += "t.row_id as `key`,"; // antd 查询列表需要的key
        // 添加固定字段
        for (String s : fixParam) {
            String[] arr = s.split("=");
            result += prefix + arr[1] + ", ";
        }
        for (int i = 0; i < field.length; i++) {
            Field f = field[i];
            //获取 mybatis jdbcType类型
            String fieldName = f.getName();
            String pro = propertyToField(fieldName);
            if (!StringUtils.isEmpty(pro)) {
                if (i < field.length - 1) {
                    if (StringUtils.isEmpty(prefix)) { // 为空的需要加引号 ``
                        result += "`" + pro + "`, ";
                    } else {
                        result += prefix + pro + ", ";
                    }
                } else {
                    if (StringUtils.isEmpty(prefix)) { // 为空的需要加引号 ``
                        result += "`" + pro + "`";
                    } else {
                        result += prefix + pro;
                    }
                }
            }
        }

        if (flag) {
            result += "\n</sql>\n\n";
        } else {
            result += "\n\n";
        }
        return result;
    }

    /**
     * flag : 是否需要 row_id
     * @Author created by barrett in 2019/4/1 10:18
     */
    public static String getInsertSql(Object o,int flag) {
        Field[] field = o.getClass().getDeclaredFields();
        String result = "";
        // 添加固定字段
        for (String s : fixParam) {
            String[] arr = s.split("=");
            if(flag == 2){ //不需要 row_id
                if(!arr[1].equals("row_id")){
                    result += "`" + arr[1] + "`, ";
                }
            }else{
                result += "`" + arr[1] + "`, ";
            }
        }
        for (int i = 0; i < field.length; i++) {
            Field f = field[i];
            //获取 mybatis jdbcType类型
            String fieldName = f.getName();
            String pro = propertyToField(fieldName);
            if (!StringUtils.isEmpty(pro)) {
                if (i < field.length - 1) {
                    result += "`" + pro + "`, ";
                } else {
                    result += "`" + pro + "`";
                }
            }
        }
        return result;
    }

    /**
     * selectAll
     *
     * @Author created by barrett in 2018/12/14 16:50
     */
    public static String getSelect(Object t) {
        Field[] field = t.getClass().getDeclaredFields();
        String result = "<select id=\"select" + MainSeraphim.className + "List\" resultMap=\"BaseResultMap\">\n" +
                "  select <include refid=\"Base_Column_List\"/> from " + MainSeraphim.table + " t\n"
                + "  <where>\n " ;
        for (Field f : field) {
            // 实体字段
            String fieldName = f.getName();
            // 数据库字段
            String pro = propertyToField(fieldName);
            if (!StringUtils.isEmpty(pro)) {
                result += "    <if test=\"params." + fieldName + " != null and params." + fieldName + " !=''\">\n" +
                        "      and t." + pro + " = #{params." + fieldName + "}\n" +
                        "    </if>\n";
            }
        }
        // 添加固定字段
//        for (String s : fixParam) {
//            String[] arr = s.split("=");
//            if (s.contains("authLevel")) {
//                result += "    <if test=\"params.authLevel != null and params.authLevel !=''\">\n" +
//                        "       <![CDATA[ and t.auth_level >= #{params.authLevel} ]]>\n" +
//                        "    </if>\n";
//            } else if (!s.contains("id")) {
//                result += "    <if test=\"params." + arr[0] + " != null and params." + arr[0] + " !=''\">\n" +
//                        "      and t." + arr[1] + " = #{params." + arr[0] + "}\n" +
//                        "    </if>\n";
//            }
//        }
        result += "  </where>\n";

        result += "</select>\n\n";
        return result;
    }

    public static String getInsert(Object t) {
        Field[] field = t.getClass().getDeclaredFields();
        String result = "";
        result += "<insert id=\"insert"+ MainSeraphim.className+"\" parameterType=\"" + t.getClass().getName() + "\">\n";
        result += "  insert into " + MainSeraphim.table + " (\n";

        String column = getInsertSql(t,2);
        result += "     " + column + ")\n" +
                "  values (\n     ";
        // 添加固定字段
        for (String s : fixParam) {
            String[] arr = s.split("=");
            if (!arr[0].equals("rowId")) {
                result += "#{" + arr[0] + "},";
            }
        }
        for (int i = 0; i < field.length; i++) {
            Field f = field[i];
            // 实体字段
            String fieldName = f.getName();
            if (!"TableName".equals(fieldName)) {
                if (i < field.length - 1) {
                    result += "#{" + fieldName + "},";
                } else {
                    result += "#{" + fieldName + "}";
                }
            }
        }

        result += ")\n</insert>\n\n";
        return result;
    }

    public static String getInsertBatch(Object t) {
        Field[] field = t.getClass().getDeclaredFields();
        String result = "";
        result += "<insert id=\"insertBatch\" parameterType=\"" + t.getClass().getName() + "\">\n";
        result += "  insert into " + MainSeraphim.table + " (\n";

        String column = getInsertSql(t,2);
        result += "     " + column + ")\n" +
                "        values \n";

        result += "        <foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">\n" +
                "        (\n     ";

        // 添加固定字段
        for (String s : fixParam) {
            String[] arr = s.split("=");
            if (!arr[0].equals("rowId")) {
                result += "#{item." + arr[0] + "},";
            }
        }
        for (int i = 0; i < field.length; i++) {
            Field f = field[i];
            // 实体字段
            String fieldName = f.getName();
            if (!"TableName".equals(fieldName)) {
                if(MainSeraphim.parentId.equals(fieldName)){
                    result += "#{parentRowId},";
                }else{
                    if (i < field.length - 1) {
                        result += "#{item." + fieldName + "},";
                    } else {
                        result += "#{item." + fieldName + "}";
                    }
                }
            }
        }

        result += ")\n" +
                "        </foreach>\n" +
                "    </insert>\n\n";
        return result;
    }

    public static String getUpdate(Object t) {
        Field[] field = t.getClass().getDeclaredFields();
        String result = "<update id=\"update"+ MainSeraphim.className+"\" parameterType=\"" + t.getClass().getName() + "\">\n" +
                "  update " + MainSeraphim.table + "\n"
                + "  <set>\n ";

        for (Field f : field) {
            // 实体字段
            String fieldName = f.getName();
            // 数据库字段
            String pro = propertyToField(fieldName);
            if (!StringUtils.isEmpty(pro)) {
                result +=
                        "    <if test=\"" + fieldName + " != null and " + fieldName + " !=''\">\n" +
                                "      `" + pro + "` = #{" + fieldName + "},\n" +
                                "    </if>\n";
            }
        }
        // 添加固定字段
        for (int i = 0; i < fixParam.size(); i++) {
            String s = fixParam.get(i);
            if (!s.contains("id")) {
                String[] arr = s.split("=");
                if (i < fixParam.size() - 1) {
                    result +=
                            "    <if test=\"" + arr[0] + " != null and " + arr[0] + " !=''\">\n" +
                                    "      `" + arr[1] + "` = #{" + arr[0] + "},\n" +
                                    "    </if>\n";
                } else {
                    result +=
                            "    <if test=\"" + arr[0] + " != null and " + arr[0] + " !=''\">\n" +
                                    "      `" + arr[1] + "` = #{" + arr[0] + "}\n" +
                                    "    </if>\n";
                }
            }
        }
        result += "  </set>\n";
        result += "  where row_id = #{rowId}\n";
        result += "</update>\n\n";
        return result;
    }

    /**
     * 详情
     *
     * @Author created by barrett in 2018/12/24 21:46
     */
    public static String getById(Object t) {
        String result = "<select id=\"get" + MainSeraphim.className + "ById\" parameterType=\"java.lang.Long\" resultMap=\"BaseResultMap\">\n" +
                "  select <include refid=\"Base_Column_List\"/>\n " +
                " from " + MainSeraphim.table + " t\n";
        result += "  where t.row_id = #{id}\n";
        result += "</select>\n\n";
        return result;
    }
    /**
     * 删除
     * @Author created by barrett in 2019/10/22 09:57
     */
    public static String getDelete(Object t) {
        String result =
                "<delete id=\"del" + MainSeraphim.className + "ByIds\" >\n" +
                "   delete from "+ MainSeraphim.table+"\n" +
                "   where ROW_ID in\n" +
                "   <foreach item=\"rowId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">\n" +
                "       #{rowId}\n" +
                "   </foreach>\n";
        result += "</delete>\n\n";
        return result;
    }

    /**
     * 获取编辑对象
     *
     * @Author created by barrett in 2018/12/24 21:46
     */
    public static String getUpdateRecordById(Object t) {
        String result = "<select id=\"getUpdateRecordById\" parameterType=\"java.lang.Long\" resultMap=\"BaseResultMap\">\n" +
                "  select <include refid=\"Base_Column_List\"/>\n " +
                " from " + MainSeraphim.table + " t\n";
        result += "  where t.row_id = #{id}\n";
        result += "</select>\n\n";
        return result;
    }

    public static String createTable(Object obj) {
        Field[] field = obj.getClass().getDeclaredFields();
        String result = "CREATE TABLE `" + MainSeraphim.table + "` (\n";
        // 添加固定字段
        for (String s : fixParam) {
            String[] arr = s.split("=");
            String jdbcType = "VARCHAR(100)";
            if ("authLevel".equals(arr[0])) {
                jdbcType = "INT(5) DEFAULT NULL COMMENT '10超级管理员，20普通管理员，30普通用户'";
            } else if ("isDel".equals(arr[0])) {
                jdbcType = "INT(5) DEFAULT NULL COMMENT '否删除（1：已删除：0：未删除）'";
            } else if ("status".equals(arr[0])) {
                jdbcType = "INT(5) DEFAULT NULL COMMENT '默认（0：可用：1：禁用）'";
            } else {
                jdbcType = "VARCHAR(50) DEFAULT NULL COMMENT ''";
            }
            if ("rowId".equals(arr[0])) {
                String pro = propertyToField(arr[1]);
                result += "`row_id` BIGINT(20) NOT NULL AUTO_INCREMENT,\n";
            } else {
                String pro = propertyToField(arr[1]);
                result += "`" + pro + "` " + jdbcType + ",\n";
            }
        }

        for (Field f : field) {
            String fieldName = f.getName();
            if (!"TableName".equals(fieldName)) {
                String str = f.getGenericType().toString();
                //获取 mybatis jdbcType类型
                String pro = propertyToField(fieldName);
                String jdbcType = getJdbcType(str);
                if ("VARCHAR".equals(jdbcType)) {
                    jdbcType += "(200)";
                } else if ("INT".equals(jdbcType)) {
                    jdbcType += "(5)";
                } else if ("BIGINT".equals(jdbcType)) {
                    jdbcType += "(20)";
                }  else if ("DOUBLE".equals(jdbcType)) {
                    jdbcType += "(10,2)";
                } else {
                    jdbcType += "";
                }
                result += "`" + pro + "` " + jdbcType + " DEFAULT NULL COMMENT '',\n";
            }
        }
        result += "PRIMARY KEY (`row_id`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='';\n";
        return result;
    }

    // ===========================================以下封装的方法=========================================================

    /**
     * 根据数据类型获取mysql 对应的数据类型
     * created by tanyicheng in 2018年7月5日-上午11:56:33
     *
     * @return
     */
    public static String getJdbcType(String str) {
        List<Map<String, String>> list = dataTypeList();
        for (Map<String, String> map : list) {
            Set<String> keySet = map.keySet();
            //java类型
            String javaType = keySet.iterator().next();
            if (javaType.equals(str)) {
                return map.get(javaType);
            }
        }
        return null;
    }

    /**
     * java属性转数据库字段
     *
     * @param property
     * @return
     * @author tanyicheng 创建时间：2018年7月5日-下午2:35:49
     */
    public static String propertyToField(String property) {
        if (null == property || "TableName".equals(property)) {
            return "";
        }
        char[] chars = property.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char c : chars) {
            if (CharUtils.isAsciiAlphaUpper(c)) {
                sb.append("_" + StringUtils.lowerCase(CharUtils.toString(c)));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 数据类型
     * created by tanyicheng in 2018年7月5日-下午1:23:06
     *
     * @return
     */
    public static List<Map<String, String>> dataTypeList() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("class java.lang.String", "VARCHAR");
        list.add(map);
        map = new HashMap<String, String>();
        map.put("class java.lang.Character", "VARCHAR");
        list.add(map);
        map = new HashMap<>();
        map.put("class java.lang.Long", "BIGINT");
        list.add(map);
        map = new HashMap<>();
        map.put("class java.lang.Integer", "INTEGER");
        list.add(map);
        map = new HashMap<>();
        map.put("class java.lang.Short", "SMALLINT");
        list.add(map);
        map = new HashMap<>();
        map.put("class java.lang.Double", "DOUBLE");
        list.add(map);
        map = new HashMap<>();
        map.put("class java.lang.Float", "FLOAT");
        list.add(map);
        map = new HashMap<>();
        map.put("class java.lang.Byte", "BINARY");
        list.add(map);
        map = new HashMap<>();
        map.put("class java.lang.Boolean", "BOOLEAN");
        list.add(map);
        map = new HashMap<>();
        map.put("class java.util.Date", "TIMESTAMP");
        list.add(map);
        return list;
    }

    /**
     * 首字母小写
     *
     * @Author created by barrett in 2019/1/4 22:24
     */
    public static String lowerFirst(String oldStr) {
        char[] chars = oldStr.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    /**
     * 固定参数,返回 userName=user_name 数组
     *
     * @Author created by barrett in 2019/1/1 21:22
     */
    public static List<String> fixedParams(String[] arr) {
        List<String> str = new ArrayList();
        for (String s : arr) {
            str.add(s + "=" + propertyToField(s));
        }
        return str;
    }
}
