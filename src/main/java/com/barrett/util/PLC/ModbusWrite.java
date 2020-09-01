package com.barrett.util.PLC;

import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.WriteMultipleRegistersRequest;
import net.wimpi.modbus.msg.WriteSingleRegisterRequest;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.procimg.SimpleRegister;

import java.net.InetAddress;

/**
 * @Description //TODO 写数据
 * @Author barrett
 * @Date 2020/8/31 14:05
 * @Param
 * @return
 **/
public class ModbusWrite {

    public static void main(String[] args) throws Exception {
        try {
            /* The important instances of the class */
            TCPMasterConnection con = null; // the connection
            ModbusTCPTransaction trans = null; // the transaction
            WriteMultipleRegistersRequest req = null; // the write request

            /* Variables for storing the parameters */
            InetAddress addr = null; // the slave's address 从站地址
            int port = 502; // the default port

            // 1. Setup the parameters
            addr = InetAddress.getByName("127.0.0.1"); // ** The address assigned to the module **

            // 2. Open the connection
            con = new TCPMasterConnection(addr);
            con.setPort(port);
            con.connect();
            System.out.println("--- Message: Line:36 success --- ");

            //写入的数值
            Register reg = new SimpleRegister(88);
            Register reg2 = new SimpleRegister(66);
            //起始地址
//            WriteSingleRegisterRequest singleReq = new WriteSingleRegisterRequest(0, reg);
            //可以发送多个值，ref 起始地址，后面的依次根据
            req = new WriteMultipleRegistersRequest(5, new Register[] {reg,reg2});
            // (= address 17)

            // 4w. Prepare the transaction
            trans = new ModbusTCPTransaction(con);
            trans.setRequest(req);

            // 5w. Execute the transaction repeat times
            trans.execute4Write();

            // 6. Close the connection
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
            ex.printStackTrace();
        }
    }
}
