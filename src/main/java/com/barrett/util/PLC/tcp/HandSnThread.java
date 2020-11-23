package com.barrett.util.PLC.tcp;

import com.barrett.util.PLC.conf.CenterConfigs;
import com.barrett.util.PLC.conf.PlcConfigs;
import com.barrett.util.PLC.conf.TransferUnit;
import com.barrett.util.PLC.conf.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HandSnThread extends Thread {

    private static Logger log = LoggerFactory.getLogger(HandSnThread.class);

    private int listenPort;

    public HandSnThread(int listenPort) {
        this.listenPort = listenPort;
    }


    public static void main(String[] args) {
        Variables.executor.execute(new HandSnThread(502));

    }
    @Override
    public void run() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(listenPort);
            log.info("启动手动耗料客户端连接监听线程，端口：" + listenPort);
            while (true) {
                final Socket client = server.accept();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                            PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                            String appId = reader.readLine();
                            log.info("客户端【" + appId + "】接入！");
                            boolean loopFlag = true;
                            while (loopFlag) {
                                try {
                                    String unitId = reader.readLine();
                                    log.info("收到手动耗料客户端【" + appId + "】的消息：" + unitId);
                                    if (!StringUtils.isEmpty(unitId)) {
                                        String lineNo = "";
                                        String plcIp = "";
                                        if (AppID.HANDSN6.getAppID().equals(appId)) {
                                            lineNo = CenterConfigs.LINE_6;
                                            plcIp = PlcConfigs.ipLsx6_1;
                                            unitId = Variables.UNIT_ID_PREFIX_LINE6 + unitId;
                                        } else if (AppID.HANDSN7.getAppID().equals(appId)) {
                                            lineNo = CenterConfigs.LINE_7;
                                            plcIp = PlcConfigs.ipLsx7_1;
                                            unitId = Variables.UNIT_ID_PREFIX_LINE7 + unitId;
                                        } else {
                                            break;
                                        }
                                        String sn ="1001";
                                        //返回给客户端
                                        writer.println(sn);
                                        writer.flush();
                                        TransferUnit tu = (TransferUnit) Variables.unitsMap.get(unitId);
                                        if (tu != null) {
                                            log.info("客户端【" + appId + "】写入串号到设备【" + unitId + "】");
                                            //checkout
                                            QxPlcUtil.write(plcIp, PlcConfigs.portData, tu.getStartPos() + 2,
                                                HexUtil.strSplitTo2lenArr(sn), true);
                                        } else {
                                            log.info("客户端【" + appId + "】没有找到设备：" + unitId);
                                        }
                                    }else{
                                        loopFlag=false;
                                    }
                                } catch (Exception ex) {
                                    loopFlag = false;
                                    try {
                                        reader.close();
                                        client.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } finally {
                                        log.error("手动耗料客户端【" + appId + "】通信异常：" + ex.getMessage());
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            log.error("手动耗料客户端连接异常：" + ex.getMessage());
                        }
                    }

                }).start();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                server.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
