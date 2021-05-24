package com.barrett.util.seraphim;

// 生成Dao
public class SeraphimDao {

    public static void main(String[] args) {
        String str = getDao(1);
        System.out.println(str);
    }

    public static String getDao(int flag) {
        String result = "import org.apache.ibatis.annotations.Mapper;\n" +
                "import java.util.List;\n" +
                "import com.seraphim.common.base.BaseMapping;\n" +
                "import com.seraphim.common.pojo.Page;\n" +
                "import org.apache.ibatis.annotations.Param;\n";

        result += "\n" +
                "public interface " + MainSeraphim.className + MainSeraphim.daoPostfix + " extends BaseMapping {\n\n";

        result += "     List<" + MainSeraphim.className + "> select" + MainSeraphim.className + "List (Page page);\n\n" +
                "     int insert"+ MainSeraphim.className+"(" + MainSeraphim.className + " record);\n\n" +
                "     int update"+ MainSeraphim.className+"(" + MainSeraphim.className + " record);\n\n" +
                "     " + MainSeraphim.className + " get" + MainSeraphim.className + "ById(Long rowId);\n\n" +
                "     " + MainSeraphim.className + " getUpdateRecordById(Long rowId);\n\n" +
                "     int del"+ MainSeraphim.className+"ByIds(Long[] rowIds);\n\n";
        if (flag == 2) {
            result +=
                    "     int insertBatch(@Param(\"parentRowId\") Long parentRowId, @Param(\"list\") List list);\n\n";
        }
        result += "}";
        return result;
    }

}
