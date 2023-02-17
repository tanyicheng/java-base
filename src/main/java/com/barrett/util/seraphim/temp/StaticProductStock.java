package com.barrett.util.seraphim.temp;


import lombok.Data;

/**
 * 成品库存-静态表
 *
 * @Author created by barrett in 2023/2/17 11:07
 */
@Data
public class StaticProductStock {
    public static final String TableName = "sc_prod_inout_storage";

    //'单据创建人ID'
    private Long bsCreatedBy;

    //'单据创建人工号'
    private String bsCreatedStaff;
    //       datetime      default null comment '单据创建时间',
    private String bsCreatedTime;
    //  bigint(20)    default null comment '单据修改人ID',
    private Long bsUpdateBy;
    // varchar(10)   default null comment '单据修改人工号',
    private String bsUpdateStaff;
    //datetime      default null comment '单据修改时间',
    private String bsUpdateTime;
    //          varchar(5)    default null comment '单据类型 0:入库单 1:出库单',
    private String type;
    //      varchar(5)    default null comment '业务类别-大类，生产入库，销售出库等',
    private String businessType;
    //           varchar(5)    default null comment '单据类别-小类，成品入库,报废品入库,oba检验等',
    private String billType;
    // varchar(50)   default null comment '原成品出入库单据号',
    private String prodInoutStorageNo;
    //          varchar(50)   default null comment '关联其他业务的单据号,根据类别区分（抽检号，采购单号），比如直采入库，这里就是直采采购单号',
    private String linkNo;
    //   datetime      default null comment '发货日期：销售出库',
    private String deliverGoodsTime;
    //       datetime      default null comment '归属期间，默认单据的创建时间',
    private String belongDate;
    //          bigint(20)    default null comment 'wf签核实例id',
    private Long wfInsId;
    //          varchar(5)    default '1' comment '数据有效性，删除-1，正常1，2作废',
    private String valid;
    //           varchar(5)    default '0' comment '单据状态，0:未完成,1已完成,',
    private String status;
    //           varchar(5)    default '0' comment '同步u8;0:未上传,1:已上传',
    private String syncU8;
    //         varchar(1000) default null comment '备注',
    private String remark;


}
