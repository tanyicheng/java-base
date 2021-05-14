package com.barrett.sms;

import java.io.*;
import java.net.*;
import java.security.*;
import java.text.*;
import java.util.*;
/**
 * 掌骏传媒 短信平台
 * @author created by barrett in 2021/5/14 10:58
 **/
public class SMS {
	static String id = "15270";		//帐号的ID
	static String ac = "fcrjhy";		//帐号
	static String pw = "fcrjhy";	//帐号的密码
	static String mobile = "18661214275";	//需要下发的手机号码
	static String content = "【中国体育彩票】陶总，恭喜您中";	//需要下发的短信内容
	static String xml = "http://sms.izjun.cn";
	
	static Date date = new Date();
	static SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddhhmmss");
	static String dateStr = sf.format(date);//时间戳，年月日时分秒
	static String str = ac + pw + dateStr;//MD5加密，账号+密码+时间戳     把帐号和密码写上去
	static String sign= MD5Encode(str);//MD5加密值
	public static void main(String[] args) throws Exception {
		SMS.sendmsg();		//提交发送短信
		SMS.checkYUE();		//查询短信帐号余额
		SMS.GetReport();	//获取短信下发状态
		SMS.GetMo();		//获取上行回复
	}
	
	public static void sendmsg() throws Exception {		//发送短信的接口调用
		String urlString = xml + "/v2sms.aspx?";
		String send = "action=send&userid="+id+"&timestamp="+dateStr+"&sign="+sign+
				"&mobile="+mobile+"&content=" +content+"&sendTime=&extno=";
        URL url = new URL(urlString);
		HttpURLConnection hp = (HttpURLConnection) url.openConnection();
        byte[] b = send.getBytes("utf-8");
        hp.setRequestMethod("POST");  
        hp.setDoOutput(true);  
        hp.setDoInput(true);
        OutputStream out = hp.getOutputStream();
        out.write(b);
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(hp.getInputStream(),"utf-8"));  
        String inputLine; 
        System.out.println("提交短信：");
        while ((inputLine = in.readLine()) != null) {System.out.println(inputLine);} 
        in.close();  
        hp.disconnect();
        System.out.println("");
	}
	
	public static void checkYUE() throws Exception {	//查询余额的接口调用	
		String urlString = xml + "/v2sms.aspx?";
		String send = "action=overage&userid="+id+"&timestamp="+dateStr+"&sign=" +sign;
        URL url = new URL(urlString);
		HttpURLConnection hp = (HttpURLConnection) url.openConnection();
        byte[] b = send.getBytes("utf-8");
        hp.setRequestMethod("POST");  
        hp.setDoOutput(true);  
        hp.setDoInput(true);
        OutputStream out = hp.getOutputStream();
        out.write(b);
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(hp.getInputStream(),"utf-8"));  
        String inputLine;
        System.out.println("余额查询：");
        while ((inputLine = in.readLine()) != null) {System.out.println(inputLine);} 
        in.close();  
        hp.disconnect();
        System.out.println("");
	}
	
	public static void GetReport() throws Exception {	//状态报告的接口调用
		String urlString = xml + "/v2statusApi.aspx?";
		String send = "action=query&userid="+id+"&timestamp="+dateStr+"&sign=" +sign;
        URL url = new URL(urlString);
		HttpURLConnection hp = (HttpURLConnection) url.openConnection();
        byte[] b = send.getBytes("utf-8");
        hp.setRequestMethod("POST");  
        hp.setDoOutput(true);  
        hp.setDoInput(true);
        OutputStream out = hp.getOutputStream();
        out.write(b);
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(hp.getInputStream(),"utf-8"));  
        String inputLine; 
        System.out.println("状态报告：");
        while ((inputLine = in.readLine()) != null) {System.out.println(inputLine);} 
        in.close();  
        hp.disconnect();
        System.out.println("");
	}
	
	public static void GetMo() throws Exception {		//上行回复的接口调用
		String urlString = xml + "/v2callApi.aspx?";
		String send = "action=query&userid="+id+"&timestamp="+dateStr+"&sign="+sign;
        URL url = new URL(urlString);
		HttpURLConnection hp = (HttpURLConnection) url.openConnection();
        byte[] b = send.getBytes("utf-8");
        hp.setRequestMethod("POST");  
        hp.setDoOutput(true);  
        hp.setDoInput(true);
        OutputStream out = hp.getOutputStream();
        out.write(b);
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(hp.getInputStream(),"utf-8"));  
        String inputLine; 
        System.out.println("上行回复：");
        while ((inputLine = in.readLine()) != null) {System.out.println(inputLine);} 
        in.close();  
        hp.disconnect();
        System.out.println("");
	}
	
	public final static String MD5Encode(String s){
		try{
			byte[] btInput  = s.getBytes("utf-8");
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++){
				int val = ((int) md[i]) & 0xff;
				if (val < 16){sb.append("0");}
				sb.append(Integer.toHexString(val));
				}return sb.toString();
			}catch (Exception e){return null;}
	}
}
