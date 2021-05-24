package com.barrett.util.seraphim.temp;

public class IVDevicesCollect {
	public static final String TableName = "mp_iv_devices_collect";
	private String barcode;
	private String eqpId;//机台号
	private String facilityId;//车间号
	private String lineld;//线号
	private String devId;//设备ID
	private String dateTime;//日期和时间
	private String serial;//	序列号
	private String pmax;//	最大功率
	private String isc;//	短路电流
	private String voc;//	开路电压
	private String ipm;//	最大功率点电流
	private String vpm;//	最大功率点电压
	private String ff;//	填充因子
	private String rs;//	串联电阻
	private String rsM;//	串联电阻，多光强下
	private String rsh;//并联电阻
	private String eff;//转换效率
	private String tObject;//	待测物温度
	private String tTarget;//修正目标温度
	private String irrTarget;//修正目标辐照度
	private String jsc;//	短路电流密度
	private String gear;//	档位
	private String sweepTime;//	扫描时间
	private String sweepDirection;//	扫描方向
	private String sweepMode;//扫描模式
	private String irrFlag;//辐照度修正标志
	private String lti;//	长时辐照不稳定度
	private String irrMonCell;//参考电池测得辐照度
	private String iscMonCell;//参考电池测得短路电流
	private String tAmbient;//环境温度
	private String areaModule;//组件面积
	private String manufacturer;//制造商
	private String loopPlus;//循环计数
	private String pvld;//定点功率
	private String vld;//定点电压
	private String ivld;//定点电流
	private String jigId;//工装ID
	private String tMod1;//红外温度1
	private String tMod2;//红外温度2
	private String tMod3;//红外温度3
	private String tMod4;//红外温度4
	private String kd1;//K型接触式温度1
	private String kd2;//K型接触式温度2
	private String kd3;//K型接触式温度3
	private String kd4;//K型接触式温度4
	private String bTime;//时间日期分开显示用
	private String bDate;//时间日期分开显示用
	private String bEff;//百分比显示
	private String bAlarm;//有参数异常
	private String bNg;//NG信息 分档位other时候为真
	private String bUuid;//唯一标识，时间戳
	private String productIn;//进料时间
	private String productOut;//出料时间
	private String productProcessStart;//	测试开始时间
	private String productProcessEnd;//测试结束时间
	private String mesOffline;//mes离线
	private String isCalibration;//校准测试
	private String isCheck;//自检测试
	private String calibrationTakeTime;//	智能校准耗时
	private String lampcount;//氙灯计数


}