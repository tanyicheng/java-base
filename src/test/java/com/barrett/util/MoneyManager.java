package com.barrett.util;


import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 交行周期最多53天 24号出账 18号还款
 * 招行周期最多48天 9号出账 27号还款
 * 江南农村银行 账单日20号，14号还款
 * 江南农村银行 账单日15号，09号还款
 *
 * 钱宝科技 到账时间 8点到22点
 *
 * 江南 1-15套现；3-9还款 120元手续费
 * 交行 3-8套现；4-18还款 120手续费 浪费14天
 * 江南 4-15套现；6-9还款 120手续费 浪费3天
 * 交行 6-8套现；7-18还款 120手续费
 * 江南 7-15套现；8-9还款 120手续费
 * 交行 8-8套现；9-18还款 120手续费
 * 江南 9-15套现；11-9还款 120手续费
 *
 *
 * 江南 1-20套现；3-4还款
 * 交行 3-4套现；4-18还款  浪费10天
 * 江南 4-18套现；6-4还款  浪费8天
 * 交行 6-4套现；7-18还款
 * 江南 7-10套现；8-4还款
 * 交行 8-8套现；9-18还款
 * 江南 9-10套现；11-4还款
 *
 * @Author created by barrett in 2019/1/25 22:14
 *
 * 交行 12-24套现，2-18还款 120手续费
 * 江南 2-15套现，4-9还款 120手续费
 * 借呗 4-9 套现，4-24还款 15手续费
 * 交行 4-24套现，5-18还款 120手续费
 * 江南 5-15套现，7-9还款 120手续费
 * 借呗 7-9 套现，7-24还款 15手续费
 * 交行 9-24套现，10-18还款 120手续费
 *
 *
 * 交行 12-24套现，2-18还款 120手续费
 * 江南 2-15套现，4-9还款 120手续费
 * 借呗 4-9 套现，4-24还款 15手续费
 * 交行 4-24套现，5-18还款 120手续费
 * 江南 5-15套现，7-9还款 120手续费
 * 借呗 7-9 套现，7-24还款 15手续费
 * 交行 9-24套现，10-18还款 120手续费
 */
public class MoneyManager {

    static DecimalFormat df = new DecimalFormat("0.00");
    //通过计算获得
    private static double shouxu = 0; // 手续费
    private static double licaiPrice = 0; // 最终理财金额,设置0 计算获得

    // 本金
    private static double benjin = 89000;
    // 存入天数
    private static long day = 31;//下面重新计算
    // pos机手续费+提现费
    private static double pos = 0.486 / 100;
    // 有些pos机需要额外支付每笔手续费 1-3元
    private static double posPrice = 3 * 3;
    // 是否有积分1有，0无
    private static int flag = 0;
    // 理财收益率
    private static double shouyilv = 3.4 / 100;

    /**
     * 19-1225 本金 89000 手续费 441
     * 20-0224 本金 30000 手续费 150
     * 20-0225 本金 59300 手续费 292.55
     * 20-0424 本金88559.91  手续费440.09  实际收益 460
     *
     * @Author created by barrett in 2019/12/25 20:45
     */
    public static void main(String[] args) {
        //计算收益天数
        day = DateUtil.dateDifferenceNum("2020-04-24", "2020-06-18", "dd");
        calculate(benjin, pos, posPrice, shouyilv, flag, day);

    }


    /**
     * 计算收益
     * 理财的金额，年华收益率
     */
    private static double shouYi(double licai, double shouyilv, long day) {
        // 年利率
        double nianlilv = Double.valueOf(df.format(licai * shouyilv));
        // 日利率
        double rililv = Double.valueOf(df.format(nianlilv / 12 / 30));
        double shouyi = Double.valueOf(df.format(day * rililv));
        System.out.println("每天收益：" + rililv + " 元");
        System.out.println("【" + day + "】天的收益：" + shouyi + " 元");
        return shouyi;
    }

    /**
     * 信用卡刷卡手续费
     *
     * @Param 本金，刷卡费率
     */
    private static double shouXuFei(double benjin, double pos, double posPrice) {
        // 手续费
        if (shouxu == 0)
            shouxu = Double.valueOf(df.format(benjin * pos)) + posPrice;
        if (licaiPrice == 0) {
            licaiPrice = benjin - shouxu;
        }
        System.out.println("本金：" + benjin + "元; 手续费：" + shouxu + "元;  " + "理财金额：" + licaiPrice);
        // 日利率
        // double rililv = nianlilv / 12 / 30;
        // System.out.println("日利率："+rililv);
        return shouxu;
    }

    /**
     * 套现计算
     *
     * @param benjin
     * @param pos      手续费
     * @param posPrice POS机额外费用
     * @param shouyilv 年华收益率
     * @param flag     是否有积分1有，0无
     * @param day      理财收益天数
     * @Author created by barrett in 2019/2/4 13:25
     */
    private static Double calculate(double benjin, double pos, double posPrice, double shouyilv, int flag, long day) {
        double shouxufei = shouXuFei(benjin, pos, posPrice);
        double shouyi = shouYi(licaiPrice, shouyilv, day);
        System.out.println("---------------------------");
        double sum = shouyi - shouxufei;
        BigDecimal b = new BigDecimal(sum);
        double lixi = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        double dikou = Double.valueOf(df.format(benjin / 10000 * 15)) * flag;

        System.out.println("本次刷卡利息：" + lixi + " 元 + 积分：" + benjin + " = " + dikou + " 元");
        System.out.println("本次刷卡加积分抵扣盈亏：【" + df.format((lixi + dikou)) + "】");

        double jifen = Double.valueOf(df.format(benjin / 20));
        System.out.println("一年可刷6次，总利息：" + df.format(lixi * 6) + " 元, 总积分：" + benjin * 6);
        System.out.println("招商获得积分：" + jifen);
        return null;
    }
}