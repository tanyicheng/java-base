package com.barrett.util.seraphim;


public class SeraphimService {

    public static String getService() {
        String result = "" +
                "import java.util.List;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import com.seraphim.common.pojo.BaseListBO;\n" +
                "import com.seraphim.common.pojo.Page;\n";

        result += "import " + MainSeraphim.daoPackage + ";\n\n";

        result += "@Service\n" +
                "public class " + MainSeraphim.className + MainSeraphim.servicePostfix + " implements "+ MainSeraphim.className+"Service {\n\n";
        result +=
                "    @Autowired\n" +
                        "    private " + MainSeraphim.daoClassName + " " + MainSeraphim.daoSmailclName + ";\n\n";

        result +=
                "    //查询所有记录\n" +
                        "    @Override\n" +
                        "    public BaseListBO search" + MainSeraphim.className + "List(" + MainSeraphim.className+"Form" + " form) {\n" +
                        "        Page page = Page.getInstance(form);\n" +
                        "        List<" + MainSeraphim.className + "> list = " + MainSeraphim.daoSmailclName + ".select"+ MainSeraphim.className+"List(page);\n" +
                        "        return BaseListBO.getInstance(page, list);\n" +
                        "    }\n\n";

        result += "" +
                "    //查询记录详情\n" +
                "    @Override\n" +
                "    public " + MainSeraphim.className + " get" + MainSeraphim.className + "ById(Long id) {\n" +
                "        " + MainSeraphim.className + " record = " + MainSeraphim.daoSmailclName + ".get" + MainSeraphim.className + "ById(id);\n" +
                "        return record;\n" +
                "    }\n\n";

        result += "" +
                "    //获取编辑对象\n" +
                "    @Override\n" +
                "    public " + MainSeraphim.className + " getUpdateRecordById(Long id) {\n" +
                "        " + MainSeraphim.className + " record = " + MainSeraphim.daoSmailclName + ".getUpdateRecordById(id);\n" +
                "        return record;\n" +
                "    }\n\n";

        result += "" +
                "    //添加记录\n" +
                "    @Override\n" +
                "    public void insert" + MainSeraphim.className + "(" + MainSeraphim.className + " form) {\n" +
                "        " + MainSeraphim.daoSmailclName + ".insert" + MainSeraphim.className + "(form);\n" +
                "    }\n\n";

        result += "" +
                "    //修改记录\n" +
                "    @Override\n" +
                "    public void update" + MainSeraphim.className + "(" + MainSeraphim.className + " form) {\n" +
                "        " + MainSeraphim.daoSmailclName + ".update" + MainSeraphim.className + "(form);\n" +
                "    }\n\n";

        result += "" +
                "    //批量删除记录\n" +
                "    @Override\n" +
                "    public void delete" + MainSeraphim.className + "(String rowIds) {\n" +
                "if (StringUtils.isNotBlank(rowIds)) {\n" +
                "\t\t\t String[] s = rowIds.split(\",\");\n" +
                "\t\t\t int i = s.length;\n" +
                "\t\t\t Long[] arr = new Long[i];\n" +
                "\t\t\t for (int a = 0; a < i; a++) {\n" +
                "\t\t\t\t arr[a] = Long.valueOf(s[a]);\n" +
                "\t\t\t }\n" +
                "        " + MainSeraphim.daoSmailclName + ".del"+ MainSeraphim.className+"ByIds(arr);\n" +
                "    }}\n\n";


        result += "}";
        return result;
    }
}
