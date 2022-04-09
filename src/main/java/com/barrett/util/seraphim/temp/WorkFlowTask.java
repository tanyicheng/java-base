package com.barrett.util.seraphim.temp;


/**
 * 运行时任务
 * @Author created by barrett in 2022/3/8 16:54
 */
public class WorkFlowTask {
    public static final String TableName = "wf_ru_task";

    //任务实例id
    private String taskInstId;

    //任务节点key
    private String taskKey;
    //任务节点顺序
    private String taskNo;
    //表达式:金额大于5000 | 请假天数大于3天
    private String expression;
    //是否会签:1是，2否
    private int izJointly;
    //会签通过率：设定后满足一定比例即表示通过
    private Integer passRate;
    //fixme 代理人任务id
    private String agentTaskId;
    //start---审批人信息---添加代理人后，此审批人只能看不能签核，
    //工号
    private String staffId;
    //姓名
    private String staffName;
    //职称-描述信息（总监审批）
    private String position;
    //所在部门id
    private String orgId;
    //父部门id
    private String parentOrgId;
    //---end---

    //开始时间：任务激活后更新开始时间
    private String startTime;
    //审批时间
    private String endTime;
    //耗时：=开始-审批
    private String timeConsuming;
    //任务状态
    private int status;

    //签核结果,Y,N
    private int signType;
    //签核结果描述
    private String remark;



}
