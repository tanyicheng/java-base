package com.barrett.util.seraphim.temp;


//         row_id       bigint auto_increment primary key,
//         created_by   varchar(50)  null comment '',
//         created_time varchar(50)  null,
//         update_by    varchar(50)  null comment '工号',
//         update_time  varchar(50)  null,
//         is_valid     varchar(50)  null comment 'Y有效，N无效',

//         user_id     varchar(255) null comment '委托人工号',
//         user_name     varchar(255) null comment '委托人姓名',
//         agent_id     varchar(255) null comment '代理人工号',
//         agent_name   varchar(255) null comment '代理人姓名',
//         model_name   varchar(200) null comment '模型名称',
//         model_key    varchar(200) null comment '模型key',
//         start_date  varchar(50)  null comment '开始日期',
//         end_date  varchar(50)  null comment '结束日期',
//         tenant_id    varchar(200) null comment '租户',
//         `desc`       varchar(200) null comment '描述'
public class WorkFlowAgent {
    public static final String TableName = "wf_re_agent";

    private String userId;
    private String userName;
    private String agentId;
    private String agentName;

    //模型名称
    private String modelName;
    //模型key
    private String modelKey;
    //
    private String startDate;
    private String endDate;
    private String tenantId;
    //描述
    private String desc;



}
