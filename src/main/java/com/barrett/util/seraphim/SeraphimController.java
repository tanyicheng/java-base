package com.barrett.util.seraphim;


public class SeraphimController {

    public static String getController() {
        {
            String result = "" +
                    "import org.springframework.beans.factory.annotation.Autowired;\n" +
                    "import org.springframework.stereotype.Controller;\n" +
                    "import com.seraphim.common.base.BaseController;\n" +
                    "import org.springframework.web.bind.annotation.*;\n\n";
            result += "import " + MainSeraphim.servicePackage + ";\n\n";

            result += "@Controller\n" +
                    "@RequestMapping(\"/" + MainSeraphim.smailclName + "\")\n" +
                    "public class " + MainSeraphim.className + MainSeraphim.ctrlPostfix + " extends BaseController{\n\n";
            result +=
                    "    @Autowired\n" +
                            "    private " + MainSeraphim.serviceClassName + " " + MainSeraphim.serviceSmailclName + ";\n\n";

            result +=
                    "    //获取对象列表\n" +
                    "    @ResponseBody\n" +
                    "    @PostMapping(\"/list\")\n" +
                    "    public String selectList(@RequestBody " + MainSeraphim.className + " form) {\n" +
                    "       BaseListBO bo = " + MainSeraphim.serviceSmailclName + ".search"+ MainSeraphim.className+"List(form);\n" +
                    "        return dataTableJson(bo.getTotalcount(), bo.getDatalist());\n" +
                    "    }\n\n";

            result += "" +
                    "    //新增记录\n" +
                    "    @ResponseBody\n" +
                    "    @PostMapping(\"/add\")\n" +
                    "    public String addRecord(@RequestBody " + MainSeraphim.className + " form) {\n" +
                    "        " + MainSeraphim.serviceSmailclName + ".insert" + MainSeraphim.className + "(form);\n" +
                    "        return getSuccessResult();\n" +
                    "    }\n\n";

            result += "" +
                    "    //编辑对象\n" +
                    "    @CustomerSecurity(url=\"/update\")\n" +
                    "    @ResponseBody\n" +
                    "    @GetMapping(value = \"/getById/{rowId}\")\n" +
                    "    public String get"+ MainSeraphim.className +"ById(@PathVariable Long rowId) {\n" +
                    "        " + MainSeraphim.className + " record = " + MainSeraphim.serviceSmailclName + ".getUpdateRecordById(id);\n" +
                    "        return getSuccessResult(record);\n" +
                    "    }\n\n";

            result += "" +
                    "    //更新记录\n" +
                    "    @ResponseBody\n" +
                    "    @PostMapping(value = \"/update\")\n" +
                    "    public String updateRecord(@RequestBody " + MainSeraphim.className + " form) {\n" +
                    "        " + MainSeraphim.serviceSmailclName + ".update" + MainSeraphim.className + "(form);\n" +
                    "        return getSuccessResult();\n" +
                    "    }\n\n";

            result += "" +
                    "    //删除或批量删除记录\n" +
                    "    @ResponseBody\n" +
                    "    @RequestMapping(value = \"/delete/{rowId}\", method = RequestMethod.DELETE)\n" +
                    "    public String removeBatch(@PathVariable Long rowId) {\n" +
                    "        " + MainSeraphim.serviceSmailclName + ".delete" + MainSeraphim.className + "(form.getIds());\n" +
                    "        return getSuccessResult();\n" +
                    "    }\n\n";


            result += "}";
            return result;
        }
    }
}
