package com.barrett.util.seraphim.temp;


/**
 * 简版工作流的流程节点
 * @Author created by barrett in 2022/3/8 16:54
 */
public class WorkFlowModelNode {
    public static final String TableName = "wf_re_model_node";

    //模型设计id
    private Long modelId;
    //任务节点id
//    private String taskId;
    //任务节点key
    private String taskKey;
    //任务节点顺序
    private String taskNo;
    //审批信息:上级部门｜固定领导
    private String approvalInfo;
    //表达式:金额大于5000 | 请假天数大于3天
    private String expression;
    //职称
    private String jobTitle;
    //是否会签:1是，2否
    private String izJointly;
    //会签通过率：设定后满足一定比例即表示通过
    private String passRate;
    //版本号
    private String version;




}
