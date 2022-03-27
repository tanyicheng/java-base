package com.barrett.util.seraphim.temp;


//运行实例
public class WorkFlowTaskInstance {
    public static final String TableName = "wf_ru_task_inst";

    //流程定义id
    private String procdefId;
    //流程定义唯一key，vacation:4:79
    private String procdefKey;
    //租户
    private String tenantId;
    //流程发起类型
    private int initiatorType;
    //流程发起范围：人｜部门
    private String initiator;

    //实例开始时间：任务激活后更新开始时间
    private String startTime;
    //实例结束时间
    private String endTime;
    //耗时：=开始-结束
    private String timeConsuming;
    //实例状态
    private int status;
    private String remark;
    //当前审批人描述（xxx审批）
    private String currentApprover;
    //流程发起人工号
    private String initiatorNo;




}
