package com.barrett.PLC.serial;

import com.barrett.PLC.tcp.HexUtil;
import gnu.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import static com.barrett.PLC.tcp.HexUtil.listToByte;

/**
 * 串口工具类 https://blog.csdn.net/wangmx1993328/article/details/88692848
 * b：波特率（baudrate）
 * d：数据位（datebits），SerialPort 支持 5,6,7,8
 * s：停止位（stopbits），SerialPort 支持 1,2,3
 * p：校验位 (parity)，SerialPort 支持 0,1,2,3,4
 */
public class SerialPortTool {
    private static final Logger logger = LoggerFactory.getLogger(SerialPortTool.class);//slf4j 日志记录器

    public static void main(String[] args) {
        //读取温湿度地址
        String hex = "01 03 00 00 00 02 C4 0B";
//        byte[] bytes = new byte[]{0x01, 0x03, 0x00, 0x00, 0x00, 0x02, (byte) 0xC4, 0x0B};
        //设置485地址为 6
//        String hex = "06 06 00 02 00 06 A9 BF";
//        byte[] bytes = new byte[hex.split(" ").length];

        List<Byte> list = HexUtil.hexStringToByteList(hex.replaceAll(" ", ""));
//        byte[] bytes = HexUtil.hexStringToByte(hex.replaceAll(" ", ""));
//        SerialPort serialPort = SerialPortTool.openComPort(null, 9600, 8, 1, 0);
//        SerialPortTool.sendDataToComPort(serialPort, bytes);
//        SerialPortTool.closeComPort(serialPort);

        DSerialPortRS485 ds = new DSerialPortRS485();
        ds.listPort();
        ds.selectPort("COM3", 9600, 8, 1, 0);

        //fixme crc校验未测试成功
//        byte[] crc16 = crc16(listToByte(list), list.size());
//        list.add(crc16[0]);
//        list.add(crc16[1]);

        ds.writeByte(listToByte(list));
        ds.startRead(1);


        //发送16进制数据——实际应用中串口通信传输的数据，大都是 16 进制
//        String hexStrCode = "455A432F5600";
//        serialPort = SerialPortTool.openComPort(null, 9600, 8, 1, 0);
//        SerialPortTool.sendDataToComPort(serialPort, hexString2Bytes(hexStrCode));
//        SerialPortTool.closeComPort(serialPort);
    }

/**
 * 测试结果
 * WARNING:  RXTX Version mismatch
 * 	Jar version = RXTX-2.2 (CVS snapshot 2011.02.03, modified by CMU CREATE Lab, http://code.google.com/p/create-lab-commons/)
 * 	native lib Version = RXTX-2.2-20081207 Cloudhopper Build rxtx.cloudhopper.net
 * now to list all Port of this PC：gnu.io.CommPortEnumerator@59f95c5d
 * COM1, null
 * COM2, null
 * COM3, null
 * COM8, null
 * COM9, null
 * COM19, null
 * COM20, null
 * 串口通讯测试 --> 端口选择成功，当前端口：COM3,现在实例化 SerialPort:
 * 串口通讯测试 --> 实例 SerialPort 成功！
 * 串口通讯测试 --> 信息发送成功！01 03 00 00 00 02 C4 0B
 * 串口通讯测试 --> 开始监听来自'COM3'的数据--------------
 * 串口通讯测试 --> 监听程序将在1秒后关闭。。。。
 * 串口通讯测试 --> 接收到端口返回数据(长度为27)：01 03 04 01 DA 00 EF 9B B8
 * 16:55:57.713 [Thread-0] INFO  c.b.util.PLC.serial.SerialPortTool - 温度：23.9，湿度：47.4
 * 串口通讯测试 --> 端口 COM3 监听关闭了！
 *
 * Process finished with exit code 0
 **/

    /**
     * 查找电脑上所有可用 com 端口
     *
     * @return 可用端口名称列表，没有时 列表为空
     */
    public static final ArrayList<String> findSystemAllComPort() {
        /**
         *  getPortIdentifiers：获得电脑主板当前所有可用串口
         */
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portNameList = new ArrayList<>();

        /**
         *  将可用串口名添加到 List 列表
         */
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();//名称如 COM1、COM2....
            portNameList.add(portName);
        }
        return portNameList;
    }

    /**
     * 打开电脑上指定的串口
     *
     * @param portName 端口名称，如 COM1，为 null 时，默认使用电脑中能用的端口中的第一个
     * @param b        波特率(baudrate)，如 9600
     * @param d        数据位（datebits），如 SerialPort.DATABITS_8 = 8
     * @param s        停止位（stopbits），如 SerialPort.STOPBITS_1 = 1
     * @param p        校验位 (parity)，如 SerialPort.PARITY_NONE = 0
     * @return 打开的串口对象，打开失败时，返回 null
     */
    public static final SerialPort openComPort(String portName, int b, int d, int s, int p) {
        CommPort commPort = null;
        try {
            //当没有传入可用的 com 口时，默认使用电脑中可用的 com 口中的第一个
            if (portName == null || "".equals(portName)) {
                List<String> comPortList = findSystemAllComPort();
                if (comPortList != null && comPortList.size() > 0) {
                    portName = comPortList.get(0);
                }
            }
            logger.info("开始打开串口：portName=" + portName + ",baudrate=" + b + ",datebits=" + d + ",stopbits=" + s + ",parity=" + p);

            //通过端口名称识别指定 COM 端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            /**
             * open(String TheOwner, int i)：打开端口
             * TheOwner 自定义一个端口名称，随便自定义即可
             * i：打开的端口的超时时间，单位毫秒，超时则抛出异常：PortInUseException if in use.
             * 如果此时串口已经被占用，则抛出异常：gnu.io.PortInUseException: Unknown Application
             */
            commPort = portIdentifier.open(portName, 5000);
            /**
             * 判断端口是不是串口
             * public abstract class SerialPort extends CommPort
             */
            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                /**
                 * 设置串口参数：setSerialPortParams( int b, int d, int s, int p )
                 * b：波特率（baudrate）
                 * d：数据位（datebits），SerialPort 支持 5,6,7,8
                 * s：停止位（stopbits），SerialPort 支持 1,2,3
                 * p：校验位 (parity)，SerialPort 支持 0,1,2,3,4
                 * 如果参数设置错误，则抛出异常：gnu.io.UnsupportedCommOperationException: Invalid Parameter
                 * 此时必须关闭串口，否则下次 portIdentifier.open 时会打不开串口，因为已经被占用
                 */
                serialPort.setSerialPortParams(b, d, s, p);
                logger.info("打开串口 " + portName + " 成功...");
                return serialPort;
            } else {
                logger.error("当前端口 " + commPort.getName() + " 不是串口...");
            }
        } catch (NoSuchPortException e) {
            e.printStackTrace();
        } catch (PortInUseException e) {
            logger.warn("串口 " + portName + " 已经被占用，请先解除占用...");
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            logger.warn("串口参数设置错误，关闭串口，数据位[5-8]、停止位[1-3]、验证位[0-4]...");
            e.printStackTrace();
            if (commPort != null) {//此时必须关闭串口，否则下次 portIdentifier.open 时会打不开串口，因为已经被占用
                commPort.close();
            }
        }
        logger.error("打开串口 " + portName + " 失败...");
        return null;
    }

    /**
     * 往串口发送数据
     *
     * @param serialPort 串口对象
     * @param order      待发送数据
     */
    public static void sendDataToComPort(SerialPort serialPort, byte[] orders) {
        OutputStream outputStream = null;
        try {
            if (serialPort != null) {
                outputStream = serialPort.getOutputStream();
                outputStream.write(orders);
                outputStream.flush();
                logger.info("往串口 " + serialPort.getName() + " 发送数据：" + Arrays.toString(orders) + " 完成...");
            } else {
                logger.error("gnu.io.SerialPort 为null，取消数据发送...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 关闭串口
     *
     * @param serialport 待关闭的串口对象
     */
    public static void closeComPort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();
            logger.info("关闭串口 " + serialPort.getName());
        }
    }

    /**
     * 16进制字符串转十进制字节数组 fixme 有问题
     * 这是常用的方法，如某些硬件的通信指令就是提供的16进制字符串，发送时需要转为字节数组再进行发送
     *
     * @param strSource 16进制字符串，如 "455A432F5600"，每两位对应字节数组中的一个10进制元素
     *                  默认会去除参数字符串中的空格，所以参数 "45 5A 43 2F 56 00" 也是可以的
     * @return 十进制字节数组, 如 [69, 90, 67, 47, 86, 0]
     */
    public static byte[] hexString2Bytes(String strSource) {
        if (strSource == null || "".equals(strSource.trim())) {
            System.out.println("hexString2Bytes 参数为空，放弃转换.");
            return null;
        }
        strSource = strSource.replace(" ", "");
        int l = strSource.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            ret[i] = Integer.valueOf(strSource.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return ret;
    }

    //将字节数组转换为16进制字符串
    public static String BinaryToHexString(byte[] bytes) {
        String hexStr = "0123456789ABCDEF";
        String result = "";
        String hex = "";
        for (byte b : bytes) {
            hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
            hex += String.valueOf(hexStr.charAt(b & 0x0F));
            result += hex + " ";
        }
        return result;
    }

    public static double transform(String hex, int begin, int end) {
        double res = 0.0;
        String sub = hex.replaceAll(" ", "").substring(begin, end);
        Double x = Double.valueOf(Integer.parseInt(sub, 16));
        res = x / 10;
        return res;
    }

    static char CRCHTalbe[] = {
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40};

    static char CRCLTalbe[] = {
            0x00, 0xC0, 0xC1, 0x01, 0xC3, 0x03, 0x02, 0xC2, 0xC6, 0x06, 0x07, 0xC7,
            0x05, 0xC5, 0xC4, 0x04, 0xCC, 0x0C, 0x0D, 0xCD, 0x0F, 0xCF, 0xCE, 0x0E,
            0x0A, 0xCA, 0xCB, 0x0B, 0xC9, 0x09, 0x08, 0xC8, 0xD8, 0x18, 0x19, 0xD9,
            0x1B, 0xDB, 0xDA, 0x1A, 0x1E, 0xDE, 0xDF, 0x1F, 0xDD, 0x1D, 0x1C, 0xDC,
            0x14, 0xD4, 0xD5, 0x15, 0xD7, 0x17, 0x16, 0xD6, 0xD2, 0x12, 0x13, 0xD3,
            0x11, 0xD1, 0xD0, 0x10, 0xF0, 0x30, 0x31, 0xF1, 0x33, 0xF3, 0xF2, 0x32,
            0x36, 0xF6, 0xF7, 0x37, 0xF5, 0x35, 0x34, 0xF4, 0x3C, 0xFC, 0xFD, 0x3D,
            0xFF, 0x3F, 0x3E, 0xFE, 0xFA, 0x3A, 0x3B, 0xFB, 0x39, 0xF9, 0xF8, 0x38,
            0x28, 0xE8, 0xE9, 0x29, 0xEB, 0x2B, 0x2A, 0xEA, 0xEE, 0x2E, 0x2F, 0xEF,
            0x2D, 0xED, 0xEC, 0x2C, 0xE4, 0x24, 0x25, 0xE5, 0x27, 0xE7, 0xE6, 0x26,
            0x22, 0xE2, 0xE3, 0x23, 0xE1, 0x21, 0x20, 0xE0, 0xA0, 0x60, 0x61, 0xA1,
            0x63, 0xA3, 0xA2, 0x62, 0x66, 0xA6, 0xA7, 0x67, 0xA5, 0x65, 0x64, 0xA4,
            0x6C, 0xAC, 0xAD, 0x6D, 0xAF, 0x6F, 0x6E, 0xAE, 0xAA, 0x6A, 0x6B, 0xAB,
            0x69, 0xA9, 0xA8, 0x68, 0x78, 0xB8, 0xB9, 0x79, 0xBB, 0x7B, 0x7A, 0xBA,
            0xBE, 0x7E, 0x7F, 0xBF, 0x7D, 0xBD, 0xBC, 0x7C, 0xB4, 0x74, 0x75, 0xB5,
            0x77, 0xB7, 0xB6, 0x76, 0x72, 0xB2, 0xB3, 0x73, 0xB1, 0x71, 0x70, 0xB0,
            0x50, 0x90, 0x91, 0x51, 0x93, 0x53, 0x52, 0x92, 0x96, 0x56, 0x57, 0x97,
            0x55, 0x95, 0x94, 0x54, 0x9C, 0x5C, 0x5D, 0x9D, 0x5F, 0x9F, 0x9E, 0x5E,
            0x5A, 0x9A, 0x9B, 0x5B, 0x99, 0x59, 0x58, 0x98, 0x88, 0x48, 0x49, 0x89,
            0x4B, 0x8B, 0x8A, 0x4A, 0x4E, 0x8E, 0x8F, 0x4F, 0x8D, 0x4D, 0x4C, 0x8C,
            0x44, 0x84, 0x85, 0x45, 0x87, 0x47, 0x46, 0x86, 0x82, 0x42, 0x43, 0x83,
            0x41, 0x81, 0x80, 0x40};

    public static int GetCRC16(char buffer, int dataLen) {
        char CRC_H = 0xFF; // 高 CRC 字节初始化
        char CRC_L = 0xFF; // 低 CRC 字节初始化
        int index; // CRC 循环中的索引

        while (dataLen-- == 0) {
            // 计算 CRC
            index = CRC_L ^ buffer++;
            CRC_L = (char) (CRC_H ^ CRCHTalbe[index]);
            CRC_H = CRCLTalbe[index];
        }
        return ((CRC_H << 8) | CRC_L);
    }

    public static int[] getCrc(byte[] data) {
        int flag;
        // 16位寄存器，所有数位均为1
        int wcrc = 0xffff;
        for (int i = 0; i < data.length; i++) {
            // 16 位寄存器的低位字节
            // 取被校验串的一个字节与 16 位寄存器的低位字节进行“异或”运算
            wcrc = 0xa001;
        }
        //获取低八位
        int low = wcrc >> 8;//或者wcrc/256
        int up = wcrc % 256;//获取高八位
        int[] crc = {up, low};
        return crc;
    }

    public static byte[] crc16(byte[] pucFrame,int usLen) {
        int i = 0;
        byte[] res = new byte[]{(byte) 0xFF, (byte) 0xFF};
        int idx = 0x0000;
        while (usLen-- > 0) {
            idx = Math.abs(res[0] ^ pucFrame[i]);
            res[0] = (byte)(res[1] ^ CRCHTalbe[idx]);
            res[1] = (byte) CRCLTalbe[idx];
            i++;
        }
        return res;
    }



}