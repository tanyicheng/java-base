package com.barrett.util.PLC;

import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.procimg.InputRegister;
import net.wimpi.modbus.procimg.Register;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Description //TODO 读取
 * @Author barrett
 * @Date 2020/8/31 14:05
 * @Param 
 * @return 
 **/
public class ModbusRead {
    private static Logger logger = Logger.getLogger(ModbusRead.class.getName()); // 日志打印类


    public static void main(String[] args) throws Exception {

        //        String result = readByTCP("localhost", 502, "02", 12605, 2, 1);

        //        String result = readByTCP("localhost", 502, "03", 12605, 2, 1);

        //2线实测
//        readByTCP("192.168.200.23", 502, "03", 12605, 21, 1);

        String ip="127.0.0.1";
        int port=502;
        String funCode = "03";
        int ref=0;//参数也叫起始地址
        int qty = 10;//读取长度
        int slaveId=1;//从站地址
        readByTCP(ip,port, funCode, ref, qty, slaveId);

    }


    /**
     * @Description //TODO 通过TCP读取
     * @Author barrett
     * @Date 2020/8/31 13:30
     * @param ip
     * @param port
     * @param funcode 功能码
     * @param ref 参数，也可理解为起始地址
     * @param quantity 读取长度
     * @param unitId ：slaveId，从站地址
     * @return 
     **/
    public static String readByTCP(String ip, int port, String funcode, int ref, int quantity, Integer unitId)
            throws Exception {
        TCPMasterConnection con = null; // the connection
        ModbusTCPTransaction transaction = null; // the transaction
        // ModbusRequest req = null; // the request
        try {
            ModbusResponse res = null; // the response
            String data = null;
            InetAddress addr = InetAddress.getByName(ip);
            con = new TCPMasterConnection(addr);
            con.setPort(port);
            con.connect();
            transaction = new ModbusTCPTransaction(con);
            ModbusRequest request = getReadRequest(funcode, ref, quantity);
            // logger.info( "req=" + request );
            if (unitId != null) {
                request.setUnitID(unitId);
            }
            transaction.setRequest(request);
            transaction.execute();
            res = transaction.getResponse();
            return getResponseString(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ModbusRequest getReadRequest(String func, int ref, int quantity) {
        if (func.equals("05"))// 功能码：05
            return new WriteCoilRequest(ref, true);
        else if (func.equals("01"))// 功能码：01
            return new ReadCoilsRequest(ref, quantity);
        else if (func.equals("02"))// 功能码：02
            return new ReadInputDiscretesRequest(ref, quantity);
        else if (func.equals("04"))// 功能码：04
            return new ReadInputRegistersRequest(ref, quantity);
        else if (func.equals("03"))// 功能码：03
            return new ReadMultipleRegistersRequest(ref, quantity);
        else if (func.equals("06")) {
            Register r = ModbusCoupler.getReference().getProcessImageFactory().createRegister();
            // r.setValue( 420 );
            // 功能码：06
            return new WriteSingleRegisterRequest(0, r);
        } else if (func.equals("16"))// 功能码：16 return new
        {
            Register rr = ModbusCoupler.getReference().getProcessImageFactory().createRegister();
            rr.setValue(420);
            return new WriteMultipleRegistersRequest(0, new Register[] {rr});
        }

        return null;

    }

    private static String getResponseString(ModbusResponse res) {
        String data = null;
        if (res == null) {
            logger.info("UDP请求无返回值");
            return null;
        }
        logger.info("\nTransactionID=" + res.getTransactionID() + "\nProtocolID=" + res.getProtocolID()
                + "\nDataLength=" + res.getDataLength() + "\nUnitID=" + res.getUnitID() + "\nFunctionCode="
                + res.getFunctionCode() + "\nHexMessage=" + res.getHexMessage() + "");
        if (res instanceof ReadCoilsResponse) {
            ReadCoilsResponse t = (ReadCoilsResponse) res;
            logger.info("\ndata=" + t.getCoils().toString());
            data = t.getCoils().toString();
        }
        if (res instanceof ReadInputDiscretesResponse) {
            ReadInputDiscretesResponse t = (ReadInputDiscretesResponse) res;
            logger.info("\ndata=" + t.getDiscretes().toString());
            data = t.getDiscretes().toString();
            System.out.println("t.getDiscretes():" + t.getDiscretes().toString());
        }
        if (res instanceof ReadInputRegistersResponse) {
            ReadInputRegistersResponse t = (ReadInputRegistersResponse) res;

            InputRegister[] s = t.getRegisters();
            List<Integer> valList = new ArrayList<Integer>();
            for (InputRegister ss : s) {
                // System.out.println( ss.getValue() );
                valList.add(ss.getValue());
            }
            data = "04-" + valList;
            logger.info("\ndata=" + data);
        }
        if (res instanceof ReadMultipleRegistersResponse) {
            ReadMultipleRegistersResponse t = (ReadMultipleRegistersResponse) res;
            // logger.info( "\ndata=" + t.getRegisters().toString() );
            Register[] rlist = t.getRegisters();
            List<Integer> valList = new ArrayList<Integer>();
            for (Register r : rlist) {
                valList.add(r.getValue());
            }
            data = "03-" + valList;
            logger.info("\ndata=" + data);
        }
        return data;
    }
}
