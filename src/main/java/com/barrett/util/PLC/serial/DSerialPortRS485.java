package com.barrett.util.PLC.serial;

import gnu.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import static com.barrett.util.PLC.serial.SerialPortTool.*;

/**
 * @项目名称 :illegalsms
 * @文件名称 :SerialPort.java
 * @所在包 :org.serial
 * @功能描述 : 串口类
 * @修改记录 :
 */
public class DSerialPortRS485 implements Runnable, SerialPortEventListener {

    private static final Logger logger = LoggerFactory.getLogger(SerialPortTool.class);

    private String appName = "串口通讯测试";
    private int timeout = 2000;// open 端口时的等待时间
    private int threadTime = 0;

    private CommPortIdentifier commPort;
    private SerialPort serialPort;
    private InputStream inputStream;
    private OutputStream outputStream;

    /**
     * @方法名称 :listPort
     * @功能描述 :列出所有可用的串口
     * @返回值类型 :void
     */
    @SuppressWarnings("rawtypes")
    public void listPort() {
        CommPortIdentifier cpid;
        Enumeration en = CommPortIdentifier.getPortIdentifiers();

        System.out.println("now to list all Port of this PC：" + en);

        while (en.hasMoreElements()) {
            cpid = (CommPortIdentifier) en.nextElement();
            if (cpid.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println(cpid.getName() + ", " + cpid.getCurrentOwner());
            }
        }
    }

    /**
     * @param portName
     * @方法名称 :selectPort
     * @功能描述 :选择一个端口，比如：COM1
     * @返回值类型 :void
     */
    @SuppressWarnings("rawtypes")
    public void selectPort(String portName, int b, int d, int s, int p) {

        this.commPort = null;
        CommPortIdentifier cpid;
        Enumeration en = CommPortIdentifier.getPortIdentifiers();

        while (en.hasMoreElements()) {
            cpid = (CommPortIdentifier) en.nextElement();
            if (cpid.getPortType() == CommPortIdentifier.PORT_SERIAL && cpid.getName().equals(portName)) {
                this.commPort = cpid;
                break;
            }
        }

        openPort(b, d, s, p);
    }

    /**
     * @方法名称 :openPort
     * @功能描述 :打开SerialPort
     * @返回值类型 :void
     */
    private void openPort(int b, int d, int s, int p) {
        if (commPort == null)
            log(String.format("无法找到名字为'%1$s'的串口！", commPort.getName()));
        else {
            log("端口选择成功，当前端口：" + commPort.getName() + ",现在实例化 SerialPort:");

            try {
                serialPort = (SerialPort) commPort.open(appName, timeout);
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
                log("实例 SerialPort 成功！");
            } catch (PortInUseException e) {
                throw new RuntimeException(String.format("端口'%1$s'正在使用中！", commPort.getName()));
            } catch (UnsupportedCommOperationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @方法名称 :checkPort
     * @功能描述 :检查端口是否正确连接
     * @返回值类型 :void
     */
    private void checkPort() {
        if (commPort == null)
            throw new RuntimeException("没有选择端口，请使用 " + "selectPort(String portName) 方法选择端口");

        if (serialPort == null) {
            throw new RuntimeException("SerialPort 对象无效！");
        }
    }

    /**
     * @param message
     * @方法名称 :write
     * @功能描述 :向端口发送数据，请在调用此方法前 先选择端口，并确定SerialPort正常打开！
     * @返回值类型 :void
     */
    public void write(String message) {
        checkPort();

        try {
            outputStream = new BufferedOutputStream(serialPort.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("获取端口的OutputStream出错：" + e.getMessage());
        }

        try {
            outputStream.write(message.getBytes("GBK"), 0, message.getBytes("GBK").length);
            //outputStream.write(message.getBytes());
            log("信息发送成功！" + new String(message.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException("向端口发送信息时出错：" + e.getMessage());
        } finally {
            try {
                outputStream.close();
            } catch (Exception e) {
            }
        }
    }

    public void writeByte(byte[] bytes) {
        checkPort();

        try {
            outputStream = new BufferedOutputStream(serialPort.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("获取端口的OutputStream出错：" + e.getMessage());
        }

        try {
            outputStream.write(bytes, 0, bytes.length);
            //outputStream.write(message.getBytes());
            String str = BinaryToHexString(bytes);
            log("信息发送成功！" + str);
        } catch (IOException e) {
            throw new RuntimeException("向端口发送信息时出错：" + e.getMessage());
        } finally {
            try {
                outputStream.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * @param time 监听程序的存活时间，单位为秒，0 则是一直监听
     * @方法名称 :startRead
     * @功能描述 :开始监听从端口中接收的数据
     * @返回值类型 :void
     */
    public void startRead(int time) {
        checkPort();

        try {
            inputStream = new BufferedInputStream(serialPort.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("获取端口的InputStream出错：" + e.getMessage());
        }

        try {
            serialPort.addEventListener(this);
        } catch (TooManyListenersException e) {
            throw new RuntimeException(e.getMessage());
        }

        serialPort.notifyOnDataAvailable(true);

        log(String.format("开始监听来自'%1$s'的数据--------------", commPort.getName()));
        if (time > 0) {
            this.threadTime = time * 1000;
            Thread t = new Thread(this);
            t.start();
            log(String.format("监听程序将在%1$d秒后关闭。。。。", time));
        }
    }

    /**
     * @方法名称 :close
     * @功能描述 :关闭 SerialPort
     * @返回值类型 :void
     */
    public void close() {
        serialPort.close();
        serialPort = null;
        commPort = null;
    }

    public void log(String msg) {
        System.out.println(appName + " --> " + msg);
    }

    /**
     * 数据接收的监听处理函数
     */
    @Override
    public void serialEvent(SerialPortEvent arg0) {

        try {
            switch (arg0.getEventType()) {
                case SerialPortEvent.BI:/* Break interrupt,通讯中断 */
                case SerialPortEvent.OE:/* Overrun error，溢位错误 */
                case SerialPortEvent.FE:/* Framing error，传帧错误 */
                case SerialPortEvent.PE:/* Parity error，校验错误 */
                case SerialPortEvent.CD:/* Carrier detect，载波检测 */
                case SerialPortEvent.CTS:/* Clear to send，清除发送 */
                case SerialPortEvent.DSR:/* Data set ready，数据设备就绪 */
                case SerialPortEvent.RI:/* Ring indicator，响铃指示 */
                case SerialPortEvent.OUTPUT_BUFFER_EMPTY:// Output buffer is empty，输出缓冲区清空
                    break;
                case SerialPortEvent.DATA_AVAILABLE://Data available at the serial 端口有可用数据。读到缓冲数组，输出到终端
                    byte[] readBuffer = new byte[inputStream.available()];
                    String readStr = "";
                    String s2 = "";

                    while (inputStream.available() > 0) {
                        inputStream.read(readBuffer);
                        readStr += BinaryToHexString(readBuffer);
                    }

                    s2 = new String(readBuffer).trim();

                    log("接收到端口返回数据(长度为" + readStr.length() + ")：" + readStr);


                    double shi = transform(readStr, 6, 10);
                    ;
                    double wen = transform(readStr, 10, 14);
                    ;
                    readStr.substring(8, 12);
                    logger.info("温度：{}，湿度：{}", wen, shi);
//                    log(s2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(threadTime);
            serialPort.close();
            log(String.format("端口 %s 监听关闭了！", commPort.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        int l = src.length() / 2;
        System.out.println(l);
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = Byte.decode("0x" + src.substring(i * 2, m) + src.substring(m, n));
        }
        return ret;
    }


    public static void main(String[] args) {
        //0x01, 0x03, 0x04, 0x01, (byte) 0xC4, 0x00, (byte) 0xEA, 0x3B, (byte) 0xBD
        byte[] bytes = new byte[]{0x01, 0x03, 0x04, 0x01, (byte) 0xC4, 0x00, (byte) 0xEA};
//        byte[] bytes = new byte[]{0x01, 0x03, 0x00, 0x00, 0x00, 0x02, (byte) 0xC4, 0x0B};
//        String s = BinaryToHexString(bytes);

        String str = "01030401C400EA3BBD";
//        String str = "01030401C400EA3BBD";
//        String res = str.replaceAll(" ", "").substring(6, 10);
//        System.out.println(res);


//        for (byte s : bytes) {
//            System.out.println(s);
//        }

        System.out.println(0x3B + "         " + 0xBD);
        System.out.println("=====================");
//        byte[] bytes2 = hexStringToByte(str);

        for (byte s : bytes) {
//            System.out.println(s);
        }


        byte[] crc = crc16(bytes, bytes.length);
        for (byte i : crc) {
            System.out.println(i);
        }
    }
}