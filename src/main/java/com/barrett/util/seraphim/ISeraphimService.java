package com.barrett.util.seraphim;

// 生成Dao
public class ISeraphimService {

    public static void main(String[] args) {
        String str = getService();
        System.out.println(str);
    }

    public static String getService() {
        String result = "" +
                "import java.util.List;\n" +
                "import com.seraphim.common.base.BaseMapping;\n" +
                "import com.seraphim.common.pojo.Page;\n" +
                "import org.springframework.stereotype.Service;\n";

        result += "\n" +
                "public interface " + MainSeraphim.className + "Service {\n\n";

        result += "    BaseListBO search" + MainSeraphim.className + "List ("+ MainSeraphim.className+"Form" +" form);\n\n" +
                "     void insert" + MainSeraphim.className + "(" + MainSeraphim.className + " record);\n\n" +
                "     void update" + MainSeraphim.className + "(" + MainSeraphim.className + " record);\n\n" +
                "     " + MainSeraphim.className + " get" + MainSeraphim.className + "ById(Long rowId);\n\n" +
                "     " + MainSeraphim.className + " getUpdateRecordById(Long rowId);\n\n" +
                "     void delete" + MainSeraphim.className + "(String rowIds);\n\n";
//        if (flag == 2) {
//            result +=
//                    "     int insertBatch(@Param(\"parentRowId\") Long parentRowId, @Param(\"list\") List list);\n\n";
//        }
        result += "}";
        return result;
    }

}
