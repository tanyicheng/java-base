package com.barrett.PLC.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class QxPlcUtil {

    private static Logger log = LoggerFactory.getLogger(QxPlcUtil.class);

    public static final Map<String, Socket> socketMap = new HashMap<String, Socket>();

    /**
     * 
     * 批量读取
     * 
     * @author shigui.yu
     * @date Sep 19, 2016 1:20:19 PM
     * @param ip
     * @param port
     * @param startPos
     * @param swapHL
     * @return
     * @throws Exception
     */
    public static String[] read(String ip, int port, int startPos, int num, boolean swapHL) throws Exception {
        Socket socket = socketMap.get(ip + "." + port);
        try {
            if (socket == null) {
                socket = new Socket(ip, port);
                socketMap.put(ip + "." + port, socket);
            }
            synchronized (socket) {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                StringBuffer sb = new StringBuffer("5000"); //副标题，命令代码
                sb.append("00");//网络编号
                sb.append("FF"); //PLC编号
                sb.append("FF03");//IO编号
                sb.append("00");//模块站号

                StringBuilder sbReqData = new StringBuilder();
                sbReqData.append("0100");//CPU监视定时器
                sbReqData.append("0104");//命令
                sbReqData.append("0000");//子命令
                sbReqData.append(HexUtil.intToHexString(startPos, 6, true));//起始软元件
                sbReqData.append("AF");//软元件代码，R*=AF
                sbReqData.append(HexUtil.intToHexString(num, 4, true));//软元件数
                sb.append(HexUtil.intToHexString(sbReqData.toString().length() / 2, 4, true));//请求数据长度
                sb.append(sbReqData.toString());
                String readCmd = sb.toString();
                dos.write(HexUtil.hexStringToByte(readCmd));

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                byte[] buffer = new byte[9]; //从开头到数据长度
                dis.readFully(buffer);
                String response = HexUtil.bytesToHexString(buffer);//从开头到数据长度
                String[] results = null;
                if ("D000".equals(response.substring(0, 4))) {
                    int dataLen = HexUtil.hexStringToInt(response.substring(14), true);
                    buffer = new byte[dataLen];
                    dis.readFully(buffer);
                    String dataResponse = HexUtil.bytesToHexString(buffer);//数据
                    String resCode = dataResponse.substring(0, 4); //结束代码
                    if ("0000".equals(resCode)) {
                        dataResponse = dataResponse.substring(4); //数据
                        results = new String[num];
                        for (int i = 0; i < num * 2; i += 2) {
                            String resultL = dataResponse.substring(i * 2, (i + 1) * 2); //返回结果
                            String resultH = dataResponse.substring((i + 1) * 2, (i + 2) * 2); //返回结果
                            if (swapHL) {
                                results[i / 2] = resultH + resultL;
                            } else {
                                results[i / 2] = resultL + resultH;
                            }
                        }
                    }
                }
                return results;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socketMap.put(ip + "." + port, null);
            throw ex;
        }
    }

    /**
     * 
     * 批量写入
     * 
     * @author shigui.yu
     * @date Sep 19, 2016 1:18:35 PM
     * @param ip
     * @param port
     * @param startPos
     * @param datas
     * @param swapHL
     * @throws Exception
     */
    public static void write(String ip, int port, int startPos, String[] datas, boolean swapHL) throws Exception {
        Socket socket = socketMap.get(ip + "." + port);
        try {
            if (socket == null) {
                socket = new Socket(ip, port);
                socketMap.put(ip + "." + port, socket);
            }
            synchronized (socket) {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                StringBuffer sb = new StringBuffer("5000"); //副标题，命令代码
                sb.append("00");//网络编号
                sb.append("FF"); //PLC编号
                sb.append("FF03");//IO编号
                sb.append("00");//模块站号
                StringBuilder sbReqData = new StringBuilder();
                sbReqData.append("0100");//CPU监视定时器
                sbReqData.append("0114");//命令
                sbReqData.append("0000");//子命令
                sbReqData.append(HexUtil.intToHexString(startPos, 6, true));//起始软元件
                sbReqData.append("AF");//软元件代码，R*=AF
                sbReqData.append(HexUtil.intToHexString(21, 4, true));//软元件数
                for (String item : datas) {
                    sbReqData.append(HexUtil.str2ToHex4(item, swapHL));
                }
                //补全空白
                for (int i = datas.length; i < 21; i++) {
                    sbReqData.append("0000");
                }
                sb.append(HexUtil.intToHexString(sbReqData.toString().length() / 2, 4, true));//请求数据长度
                sb.append(sbReqData.toString());
                String writeCmd = sb.toString();
                dos.write(HexUtil.hexStringToByte(writeCmd));

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                byte[] buffer = new byte[9]; //从开头到数据长度
                dis.readFully(buffer);
                String response = HexUtil.bytesToHexString(buffer);//从开头到数据长度
                if ("D000".equals(response.substring(0, 4))) {
                    int dataLen = HexUtil.hexStringToInt(response.substring(14), true);
                    buffer = new byte[dataLen];
                    dis.readFully(buffer);
                    //                String dataResponse = HexUtil.bytesToHexString(buffer); //数据
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socketMap.put(ip + "." + port, null);
            throw ex;
        }
    }

    /**
     * 
     * 读取一个地址的数字
     * 
     * @author shigui.yu
     * @date Sep 19, 2016 1:24:47 PM
     * @param ip
     * @param port
     * @param pos
     * @param swapHL
     * @return
     * @throws Exception
     */
    public static int read(String ip, int port, int pos, boolean swapHL) throws Exception {
        Socket socket = socketMap.get(ip + "." + port);
        try {
            if (socket == null) {
                socket = new Socket(ip, port);
                socketMap.put(ip + "." + port, socket);
            }
            synchronized (socket) {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                StringBuffer sb = new StringBuffer("5000"); //副标题，命令代码
                sb.append("00");//网络编号
                sb.append("FF"); //PLC编号
                sb.append("FF03");//IO编号
                sb.append("00");//模块站号

                StringBuilder sbReqData = new StringBuilder();
                sbReqData.append("0100");//CPU监视定时器
                sbReqData.append("0104");//命令
                sbReqData.append("0000");//子命令
                sbReqData.append(HexUtil.intToHexString(pos, 6, true));//起始软元件
                sbReqData.append("AF");//软元件代码，R*=AF
                sbReqData.append(HexUtil.intToHexString(1, 4, true));//软元件数
                sb.append(HexUtil.intToHexString(sbReqData.toString().length() / 2, 4, true));//请求数据长度
                sb.append(sbReqData.toString());
                String readCmd = sb.toString();
                System.out.println("发送帧>>>>>>>>>>>> "+readCmd);
                dos.write(HexUtil.hexStringToByte(readCmd));

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                byte[] buffer = new byte[9]; //从开头到数据长度
                dis.readFully(buffer);
                String response = HexUtil.bytesToHexString(buffer);//从开头到数据长度

                System.out.println("接受帧>>>>>>>>>>>> "+readCmd);
                int result = -1;
                if ("D000".equals(response.substring(0, 4))) {
                    int dataLen = HexUtil.hexStringToInt(response.substring(14), true);
                    buffer = new byte[dataLen];
                    dis.readFully(buffer);
                    String dataResponse = HexUtil.bytesToHexString(buffer);//数据
                    String resCode = dataResponse.substring(0, 4); //结束代码
                    if ("0000".equals(resCode)) {
                        dataResponse = dataResponse.substring(4); //数据
                        String resultL = dataResponse.substring(0, 2); //返回结果
                        String resultH = dataResponse.substring(2, 4); //返回结果
                        if (swapHL) {
                            result = Integer.parseInt(resultH + resultL, 16);
                        } else {
                            result = Integer.parseInt(resultL + resultH, 16);
                        }
                    }
                }
                return result;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socketMap.put(ip + "." + port, null);
            throw ex;
        }
    }

    /**
     * 
     * 往一个地址写入一个数字
     * 
     * @author shigui.yu
     * @date Sep 19, 2016 1:25:39 PM
     * @param ip
     * @param port
     * @param pos
     * @param x
     * @throws Exception
     */
    public static void write(String ip, int port, int pos, int x) throws Exception {
        Socket socket = socketMap.get(ip + "." + port);
        try {
            if (socket == null) {
                socket = new Socket(ip, port);
                socketMap.put(ip + "." + port, socket);
            }
            synchronized (socket) {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                StringBuffer sb = new StringBuffer("5000"); //副标题，命令代码
                sb.append("00");//网络编号
                sb.append("FF"); //PLC编号
                sb.append("FF03");//IO编号
                sb.append("00");//模块站号
                StringBuilder sbReqData = new StringBuilder();
                sbReqData.append("0100");//CPU监视定时器
                sbReqData.append("0114");//命令
                sbReqData.append("0000");//子命令
                sbReqData.append(HexUtil.intToHexString(pos, 6, true));//起始软元件
                sbReqData.append("AF");//软元件代码，R*=AF
                sbReqData.append(HexUtil.intToHexString(1, 4, true));//软元件数
                sbReqData.append(HexUtil.intToHexString(x, 4, true));
                sb.append(HexUtil.intToHexString(sbReqData.toString().length() / 2, 4, true));//请求数据长度
                sb.append(sbReqData.toString());
                String writeCmd = sb.toString();
                dos.write(HexUtil.hexStringToByte(writeCmd));

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                byte[] buffer = new byte[9]; //从开头到数据长度
                dis.readFully(buffer);
                String response = HexUtil.bytesToHexString(buffer);//从开头到数据长度
                if ("D000".equals(response.substring(0, 4))) {
                    int dataLen = HexUtil.hexStringToInt(response.substring(14), true); //数据长度
                    buffer = new byte[dataLen];
                    dis.readFully(buffer);
                    //                String dataResponse = HexUtil.bytesToHexString(buffer); //数据
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socketMap.put(ip + "." + port, null);
            throw ex;
        }
    }

    /**
     * 
     * 定时器
     * 
     * @author shigui.yu
     * @date Sep 19, 2016 1:26:04 PM
     * @param ip
     * @param port
     * @param pos
     */
    public static void timer(String ip, int port, int pos) {
        boolean loopFlag = true;
        Socket socket = socketMap.get(ip + "." + port);
        try {
            if (socket == null) {
                socket = new Socket(ip, port);
                socketMap.put(ip + "." + port, socket);
            }
            synchronized (socket) {
                int num = 0;
                while (loopFlag) {
                    num = (num == 0 ? 1 : 0);//0 1 交替
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    StringBuffer sb = new StringBuffer("5000"); //副标题，命令代码
                    sb.append("00");//网络编号
                    sb.append("FF"); //PLC编号
                    sb.append("FF03");//IO编号
                    sb.append("00");//模块站号
                    StringBuilder sbReqData = new StringBuilder();
                    sbReqData.append("0100");//CPU监视定时器
                    sbReqData.append("0114");//命令
                    sbReqData.append("0000");//子命令
                    sbReqData.append(HexUtil.intToHexString(pos, 6, true));//起始软元件
                    sbReqData.append("AF");//软元件代码，R*=AF
                    sbReqData.append(HexUtil.intToHexString(1, 4, true));//软元件数
                    sbReqData.append(HexUtil.intToHexString(num, 4, true));
                    sb.append(HexUtil.intToHexString(sbReqData.toString().length() / 2, 4, true));//请求数据长度
                    sb.append(sbReqData.toString());
                    String writeCmd = sb.toString();
                    dos.write(HexUtil.hexStringToByte(writeCmd));

                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    byte[] buffer = new byte[9]; //从开头到数据长度
                    dis.readFully(buffer);
                    String response = HexUtil.bytesToHexString(buffer);//从开头到数据长度
                    if ("D000".equals(response.substring(0, 4))) {
                        int dataLen = HexUtil.hexStringToInt(response.substring(14), true); //数据长度
                        buffer = new byte[dataLen];
                        dis.readFully(buffer);
                        //                    String dataResponse = HexUtil.bytesToHexString(buffer); //数据
                    }
                    Thread.sleep(1000);//1000毫秒
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socketMap.put(ip + "." + port, null);
            timer(ip, port, pos);//重启定时器
        }
    }

    /**
     * 
     * 检查器
     * 
     * @author shigui.yu
     * @date Sep 19, 2016 1:26:15 PM
     * @param ip
     * @param port
     * @param pos
     */
    public static void check(String ip, int port, int pos) {
        boolean loopFlag = true;
        Socket socket = socketMap.get(ip + "." + port);
        try {
            if (socket == null) {
                socket = new Socket(ip, port);
                socketMap.put(ip + "." + port, socket);
            }
            synchronized (socket) {
                int lastNum = 0;
                int errorNum = 0;
                while (loopFlag) {
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    StringBuffer sb = new StringBuffer("5000"); //副标题，命令代码
                    sb.append("00");//网络编号
                    sb.append("FF"); //PLC编号
                    sb.append("FF03");//IO编号
                    sb.append("00");//模块站号

                    StringBuilder sbReqData = new StringBuilder();
                    sbReqData.append("0100");//CPU监视定时器
                    sbReqData.append("0104");//命令
                    sbReqData.append("0000");//子命令
                    sbReqData.append(HexUtil.intToHexString(pos, 6, true));//起始软元件
                    sbReqData.append("AF");//软元件代码，R*=AF
                    sbReqData.append(HexUtil.intToHexString(1, 4, true));//软元件数
                    sb.append(HexUtil.intToHexString(sbReqData.toString().length() / 2, 4, true));//请求数据长度
                    sb.append(sbReqData.toString());
                    String readCmd = sb.toString();
                    dos.write(HexUtil.hexStringToByte(readCmd));

                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    byte[] buffer = new byte[9]; //从开头到数据长度
                    dis.readFully(buffer);
                    String response = HexUtil.bytesToHexString(buffer);//从开头到数据长度
                    if ("D000".equals(response.substring(0, 4))) {
                        int dataLen = HexUtil.hexStringToInt(response.substring(14), true);
                        buffer = new byte[dataLen];
                        dis.readFully(buffer);
                        String dataResponse = HexUtil.bytesToHexString(buffer);//数据
                        String resCode = dataResponse.substring(0, 4); //结束代码
                        if ("0000".equals(resCode)) {
                            dataResponse = dataResponse.substring(4); //数据
                            String resultL = dataResponse.substring(0, 2); //返回结果
                            String resultH = dataResponse.substring(2, 4); //返回结果
                            int result = Integer.parseInt(resultH + resultL, 16);
                            if (result != lastNum) {
                                lastNum = result;
                                errorNum = 0;
                            } else {
                                errorNum++;
                            }
                        }
                    }
                    if (errorNum > 2) {
                        log.info(ip + "检测设备异常！");
                        loopFlag = false;
                        break;
                    }
                    Thread.sleep(1000);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socketMap.put(ip + "." + port, null);
            check(ip, port, pos);
        }
    }
}
