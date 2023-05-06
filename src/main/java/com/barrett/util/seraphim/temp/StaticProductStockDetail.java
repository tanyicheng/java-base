package com.barrett.util.seraphim.temp;

import lombok.Data;


/**
 * 成品出入库-静态明细表
 *
 * @Author created by barrett in 2023/2/17 16:53
 */
public class StaticProductStockDetail  {
    public static final String TableName = "sc_prod_inout_storage_detail";

    //'单据创建人ID'
    private Long bsCreatedBy;

    //'单据创建人工号'
    private String bsCreatedStaff;
    //        '单据创建时间',
    private String bsCreatedTime;
    //   '单据修改人ID',
    private Long bsUpdateBy;
    //  '单据修改人工号',
    private String bsUpdateStaff;
    // '单据修改时间',
    private String bsUpdateTime;
    //           '单据类型 0:入库单 1:出库单',
    private String type;
    //  '原成品出入库单据号',
    private String prodInoutStorageNo;
    //           '数据有效性，删除-1，正常1，2作废',
    private String valid;
    //            '单据状态，0:未完成,1已完成,',
    private String status;
    //            '同步u8;0:未上传,1:已上传',
    private String syncU8;
    //          '备注',
    private String remark;

    //datetime       '出入库时间，可参考 st_prod_in_storage_pallet',
    private String inoutTime;
    // bigint(20)     '库别id',
    private Long storageId;
    // bigint(20)     '储位id',
    private Long locationId;
    // bigint(20)     '栈板号id',
    private Long palletId;
    // varchar(20)    '栈板号',
    private String palletNo;
    // varchar(200)   '生产ocm',
    private String prodOcmNo;
    // varchar(200)   '销售ocm',
    private String saleOcmNo;
    // varchar(5)    default '1' comment '类型：1、好品，2、报废品',
    private String productType;
    // varchar(200)   '生产单号',
    private String billNo;
    //varchar(200)   '生产批号',
    private String batchNo;
    //varchar(100)   '组件序列号（存在替换条码）',
    private String barCode;
    // varchar(100)   '原条码序列号',
    private String barCodeOriginal;
    // varchar(100)   '生产型号（bomTUV）',
    private String prodTuv;
    //bigint(20)     '生产型号id',
    private Long prodTuvId;
    // varchar(100)   '铭牌型号(销售TUV)',
    private String saleTuv;
    // bigint(20)     '销售型号id',
    private Long saleTuvId;
    // bigint(20)     '成品料号id',
    private Long productCodeId;
    // varchar(50)    '成品料号编码',
    private String productCodeNo;
    // bigint(20)     '生产计划单id',
    private Long planId;
    // varchar(20)    '生产计划单号',
    private String planNo;

}
