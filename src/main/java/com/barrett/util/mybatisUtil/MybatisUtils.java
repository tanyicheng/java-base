package com.barrett.util.mybatisUtil;

import com.barrett.beans.Person;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析mybatis sql 语句
 * @author created by barrett in 2020/11/13 15:45
 **/
public class MybatisUtils {

    public static void main(String[] args) {

        String str = " ==>  Preparing: select ms.name SUPPLIER_NAME, m.* from ( select a.GDS_NAME, CASE WHEN t1.order_type='51000' THEN '1' WHEN t1.order_type='140792344026894' THEN '1' ELSE '0' END IN_OUT_STATUS, t1.ROW_ID, t1.OUT_STORAGE_NO as RECEIPT_NO, t1.LAST_UPDATE_BY, t1.LAST_UPDATE_TIME, v1.STAFF_ID as CREATED_NO, v1.STAFF_NAME as CREATED_NAME, t1.P_BATCH_NO, t2.GOODS_NO, t3.STORAGE_ID, t3.LOCATION_ID, t3.BOX_NO, t3.NUM, t3.OUT_NO, a.CODE, a.BRAND_NAME, a.DESCRIPTION, a.UNIT_NAME, b.CN_NAME, b.EN_NAME, c.LOCATION, '0' AS DJ, cd.NAME DJNAME, v1.DEPA_NAME , o1.SUPPLIER_ID , t1.order_type, t1.REMARK from ST_OUT_STORAGE t1 left join v_sys_userinfo v1 on t1.CREATED_BY = v1.ROW_ID left join cd_code_list cd on cd.row_id = t1.order_type ,ST_OUT_STORAGE_GOODS t2 left join MD_MATERIAL_SPECIFIC a on a.ROW_ID = t2.GOODS_NO, ST_OUT_STORAGE_DETAIL t3 left join MD_STORAGE_DEF b on b.ROW_ID = t3.STORAGE_ID left join MD_LOCATION_DEF c on c.ROW_ID = t3.LOCATION_ID left join pur_order o1 on t3.OUT_NO = o1.ORDER_NO WHERE t1.ROW_ID = t2.OUT_STORAGE_ID and t2.ROW_ID = t3.GOODS_ID and t1.P_BATCH_NO like '%9-%' and t1.LAST_UPDATE_TIME >= ? and t1.LAST_UPDATE_TIME <= ? and t1.order_type like '%51004%' and t1.STATUS in ('31','71','7') and t1.SOURCE <> '3' and t1.SOURCE <> '31' and ((t1.order_type not in('140792344026894','51000')) OR (t1.order_type IS NULL)) union all select a.GDS_NAME, '1' IN_OUT_STATUS, t1.ROW_ID, t1.OUT_STORAGE_NO as RECEIPT_NO, t1.LAST_UPDATE_BY, t1.LAST_UPDATE_TIME, v1.STAFF_ID as CREATED_NO, v1.STAFF_NAME as CREATED_NAME, t1.P_BATCH_NO, t2.GOODS_NO, t3.STORAGE_ID, t3.LOCATION_ID, t3.BOX_NO, t3.NUM, t3.OUT_NO, a.CODE, a.BRAND_NAME, a.DESCRIPTION, a.UNIT_NAME, b.CN_NAME, b.EN_NAME, c.LOCATION, '0' AS DJ, cd.NAME DJNAME, v1.DEPA_NAME , o1.SUPPLIER_ID , t1.order_type, t1.REMARK from ST_OUT_STORAGE t1 left join v_sys_userinfo v1 on t1.CREATED_BY = v1.ROW_ID left join cd_code_list cd on cd.row_id = t1.order_type ,ST_OUT_STORAGE_GOODS t2 left join MD_MATERIAL_SPECIFIC a on a.ROW_ID = t2.GOODS_NO, ST_OUT_STORAGE_DETAIL t3 left join MD_STORAGE_DEF b on b.ROW_ID = t3.STORAGE_ID left join MD_LOCATION_DEF c on c.ROW_ID = t3.LOCATION_ID left join pur_order o1 on t3.OUT_NO = o1.ORDER_NO WHERE t1.ROW_ID = t2.OUT_STORAGE_ID and t2.ROW_ID = t3.GOODS_ID and t1.P_BATCH_NO like '%9-%' and t1.LAST_UPDATE_TIME >= ? and t1.LAST_UPDATE_TIME <= ? and t1.order_type like '%51004%' and t1.STATUS in ('31','71','7') and t1.SOURCE <> '3' and t1.SOURCE <> '31' and ((t1.order_type in('140792344026894','51000')) or (t1.order_type is null) ) union all select d.GDS_NAME, '1' IN_OUT_STATUS, t4.ROW_ID, t4.IN_STORAGE_NO as RECEIPT_NO, t4.LAST_UPDATE_BY, t4.LAST_UPDATE_TIME, v1.STAFF_ID as CREATED_NO, v1.STAFF_NAME as CREATED_NAME, t5.P_BATCH_NO as P_BATCH_NO, t5.GOODS_NO, t6.STORAGE_ID, t6.LOCATION_ID, t6.BOX_NO, t6.NUM, t4.OUT_NO, d.CODE, d.BRAND_NAME, d.DESCRIPTION, d.UNIT_NAME, e.CN_NAME, e.EN_NAME, f.LOCATION, '0' AS DJ, co.NAME DJNAME, v1.DEPA_NAME, o2.SUPPLIER_ID, t4.order_type, t4.REMARK from ST_IN_STORAGE t4 left join v_sys_userinfo v1 on t4.CREATED_BY = v1.ROW_ID left join cd_code_list co on co.row_id = t4.order_type left join pur_order o2 on t4.OUT_NO = o2.ORDER_NO ,ST_IN_STORAGE_GOODS t5 left join MD_MATERIAL_SPECIFIC d on d.ROW_ID = t5.GOODS_NO, ST_IN_STORAGE_DETAIL t6 left join MD_STORAGE_DEF e on e.ROW_ID = t6.STORAGE_ID left join MD_LOCATION_DEF f on f.ROW_ID = t6.LOCATION_ID WHERE t4.ORDER_TYPE = '51005' and t4.ROW_ID = t5.IN_STORAGE_ID and t5.ROW_ID = t6.GOODS_ID and t5.P_BATCH_NO like '%9-%' and t4.LAST_UPDATE_TIME >= ? and t4.LAST_UPDATE_TIME <= ? and t4.order_type like '%51004%' and t4.STATUS = '4' and t4.SOURCE <> '3' and t4.SOURCE <> '31' union all select d.GDS_NAME, CASE WHEN t7.order_type='51000' THEN '1' when t7.order_type='140792344026894' THEN '1' ELSE '0' end IN_OUT_STATUS, t7.ROW_ID, t7.IN_STORAGE_NO as RECEIPT_NO, t7.LAST_UPDATE_BY, t7.LAST_UPDATE_TIME, v1.STAFF_ID as CREATED_NO, v1.STAFF_NAME as CREATED_NAME, t8.P_BATCH_NO as P_BATCH_NO, t8.GOODS_NO, t9.STORAGE_ID, t9.LOCATION_ID, t9.BOX_NO, t9.NUM, CASE WHEN (co.NAME='好品退库' or co.NAME='线上物品退库') THEN t8.OUT_NO ELSE t7.out_no END OUT_NO, d.CODE, d.BRAND_NAME, d.DESCRIPTION, d.UNIT_NAME, e.CN_NAME, e.EN_NAME, f.LOCATION, '0' AS DJ, co.NAME DJNAME, v1.DEPA_NAME , CASE WHEN (co.NAME='好品退库' or co.NAME='线上物品退库') THEN t8.supply_id ELSE o3.SUPPLIER_ID END SUPPLIER_ID, t7.order_type, t7.REMARK from ST_IN_STORAGE t7 left join v_sys_userinfo v1 on t7.CREATED_BY = v1.ROW_ID left join cd_code_list co on co.row_id = t7.order_type left join pur_order o3 on t7.OUT_NO = o3.ORDER_NO ,ST_IN_STORAGE_GOODS t8 left join MD_MATERIAL_SPECIFIC d on d.ROW_ID = t8.GOODS_NO, ST_IN_STORAGE_DETAIL t9 left join MD_STORAGE_DEF e on e.ROW_ID = t9.STORAGE_ID left join MD_LOCATION_DEF f on f.ROW_ID = t9.LOCATION_ID WHERE t7.ORDER_TYPE != '51005' and t7.ROW_ID = t8.IN_STORAGE_ID and t8.ROW_ID = t9.GOODS_ID and t8.P_BATCH_NO like '%9-%' and t7.LAST_UPDATE_TIME >= ? and t7.LAST_UPDATE_TIME <= ? and t7.order_type like '%51004%' and t7.STATUS = '4' and t7.SOURCE <> '3' and t7.SOURCE <> '31' and ((t7.order_type not in('140792344026894','51000')) or (t7.order_type is null)) and t7.order_type not in(select row_id from cd_code_list where notes='tgys-rk') union all select d.GDS_NAME, '1' IN_OUT_STATUS, t7.ROW_ID, t7.IN_STORAGE_NO as RECEIPT_NO, t7.LAST_UPDATE_BY, t7.LAST_UPDATE_TIME, v1.STAFF_ID as CREATED_NO, v1.STAFF_NAME as CREATED_NAME, t8.P_BATCH_NO as P_BATCH_NO, t8.GOODS_NO, t9.STORAGE_ID, t9.LOCATION_ID, t9.BOX_NO, t9.NUM, CASE WHEN t7.OUT_NO IS NOT NULL THEN t7.out_no ELSE t8.out_no END out_no, d.CODE, d.BRAND_NAME, d.DESCRIPTION, d.UNIT_NAME, e.CN_NAME, e.EN_NAME, f.LOCATION, '0' AS DJ, co.NAME DJNAME, v1.DEPA_NAME , t8.SUPPLY_ID AS SUPPLIER_ID, t7.order_type, t7.REMARK from ST_IN_STORAGE t7 left join v_sys_userinfo v1 on t7.CREATED_BY = v1.ROW_ID left join cd_code_list co on co.row_id = t7.order_type left join pur_order o3 on t7.OUT_NO = o3.ORDER_NO ,ST_IN_STORAGE_GOODS t8 left join MD_MATERIAL_SPECIFIC d on d.ROW_ID = t8.GOODS_NO, ST_IN_STORAGE_DETAIL t9 left join MD_STORAGE_DEF e on e.ROW_ID = t9.STORAGE_ID left join MD_LOCATION_DEF f on f.ROW_ID = t9.LOCATION_ID WHERE t7.ORDER_TYPE != '51005' and t7.ROW_ID = t8.IN_STORAGE_ID and t8.ROW_ID = t9.GOODS_ID and t8.P_BATCH_NO like '%9-%' and t7.LAST_UPDATE_TIME >= ? and t7.LAST_UPDATE_TIME <= ? and t7.order_type like '%51004%' and t7.STATUS = '4' and t7.SOURCE <> '3' and t7.SOURCE <> '31' and (t7.order_type in('140792344026894','51000') or t7.order_type is null or t7.order_type in(select row_id from cd_code_list where notes='tgys-rk')) ) m LEFT JOIN md_supplier ms ON m.supplier_id=ms.row_id WHERE m.order_type=? order by m.LAST_UPDATE_TIME desc limit 0,10 \n" +
                "2020/11/13-13:17:20.425 [http-bio-8085-exec-6] DEBUG c.s.m.s.d.s.M.searchInOutStorageListForFinance - ==> Parameters: 2020-11-12 10:00:00(String), 2020-11-13 09:59:59(String), 2020-11-12 10:00:00(String), 2020-11-13 09:59:59(String), 2020-11-12 10:00:00(String), 2020-11-13 09:59:59(String), 2020-11-12 10:00:00(String), 2020-11-13 09:59:59(String), 2020-11-12 10:00:00(String), 2020-11-13 09:59:59(String), 51004(String)";
        String[] split = str.split("\\n");
        String sql = split[0].replace("==>  Preparing:","");
        String param = split[1];
        param = param.split("==> Parameters:")[1];
        String[] arr = param.split(",");
        List<String> list = new ArrayList<>();
        for (String s : arr) {
//            String par = s.substring(0,s.lastIndexOf("("));
            String[] split1 = s.split("[(]");
            String par = split1[0];
            String type = split1[1];
            if(hasNumber(type)){
                sql=sql.replaceFirst("[?]",par.trim());
            }else{
                sql=sql.replaceFirst("[?]","'"+par.trim()+"'");
            }
//            System.out.println(par);
//            list.add(par);
        }


//        String[] sqlarr = sql.split("\\?");
//        for (String s : sqlarr) {
//
//        }

        System.out.println(sql);
    }

    /**
     * 是否数字类型
     * @author created by barrett in 2020/11/13 10:10
     **/
    public static boolean hasNumber(String str){
        boolean flag = false;
        if(str.contains("Integer")){
            flag=true;
        }else if(str.contains("Long")){
            flag=true;
        }else if(str.contains("Double")){
            flag=true;
        }else if(str.contains("Float")){
            flag=true;
        }else if(str.contains("Short")){
            flag=true;
        }
        return flag;
    }
}
