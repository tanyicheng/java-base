package com.barrett.util.seraphim.temp;


/**
 * 简版工作流的流程设计
 * @Author created by barrett in 2022/3/8 16:54
 */
public class WorkFlowModel {
    public static final String TableName = "wf_re_model";

    //模型名称
    private String modelName;
    //模型key
    private String modelKey;
    //类别：财务、考勤，是否需要？
    private String category;
    //最新修改时间：每次发布是否需要记录历史的流程版本
    private String lastUpdateTime;
    //版本号
    private String version;
    //租户ID
    private String tenantId;
    //是否发布版本：1是：可编辑，2否：不可编辑
    private String izRelease;
    //发布的主id
    private Long parentId;
    //描述
    private String desc;



}
