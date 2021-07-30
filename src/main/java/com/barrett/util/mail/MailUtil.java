package com.barrett.util.mail;


import com.barrett.PLC.tcp.QxPlcUtil;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Properties;

/**
 * 测试成功，可以发送到163和qq 邮箱,阿里云企业邮箱tanyicheng@ryhx.com.cn
 * 发件服务区：163邮箱
 * 新注册邮箱需要设置pop3,第三方登入授权码
 *
 * @author tanyicheng
 * 2016年10月23日-下午12:08:48
 */
public class MailUtil {
    private final static Logger syslog = LoggerFactory.getLogger(MailUtil.class);
    //获取配置文件中的发件人信息：  账号  授权码
    private static String mailSender = "xiaoyifam@163.com";
    private static String MailAuthorization = "";

    static class Inclass implements Runnable {
        private Message msg;
        private boolean flag = true;//是否出现异常，如果有异常则循环调用其他发件账号

        private Inclass(Message msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            try {
                syslog.info("发送邮件开始--> , 账号：" + mailSender + "   授权码: " + MailAuthorization);
                // 发送邮件
                Transport.send(msg);
                System.out.println("邮箱发送成功");
                syslog.info("邮箱发送成功");
            } catch (MessagingException e) {
                e.printStackTrace();
                syslog.error("邮件发送失败：", e);
            }

        }

        /**
         * 配置发送邮件参数 25端口发送
         *
         * @param to
         * @param topic
         * @param message
         * @author tanyicheng 2016年11月5日-上午11:58:22
         */
        public static void sendEmail(String to, String topic, String message) {
            Properties pro = new Properties();
            pro.put("mail.smtp.host", "smtp.163.com");// 发送的邮件服务器
//		pro.put("mail.smtp.host", "smtp.qq.com");// 发送的邮件服务器
//		pro.put("mail.smtp.port", "25");//qq邮箱访问端口
            pro.put("mail.smtp.auth", "true");// 配置是否验证账号
            pro.put("mail.debug", "true");//便于调试
            //获取配置文件中的发件人信息
            // 获得Session,验证发送者的账号密码
            Session session = Session.getDefaultInstance(pro, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailSender, MailAuthorization);
                }
            });
            // 获得store对象
            Message msg = new MimeMessage(session);

            try {
                // 设置邮件接收者
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                // 设置邮件的主题
                msg.setSubject(topic);
                // 设置邮件的发送者（发送账号，别名，编码）
                msg.setFrom(new InternetAddress(mailSender, "1felse", "UTF-8"));
			/*
	        //    To: 增加收件人（可选）
			msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("dd@receive.com", "USER_DD", "UTF-8"));
	        //    Cc: 抄送（可选）
			msg.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
	        //    Bcc: 密送（可选）
			msg.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));*/

                // 邮件的内容
                msg.setContent(message, "text/html;charset=utf-8");
                // 异步处理
                Inclass inclass = new Inclass(msg);
                Thread thread = new Thread(inclass);
                thread.start();
            } catch (UnsupportedEncodingException e) {
                System.out.println("不支持的字符编码");
                syslog.error("不支持的字符编码：", e);
                e.printStackTrace();
            } catch (AddressException e) {
                System.out.println("地址错误 ");
                syslog.error("地址错误", e);
            } catch (MessagingException e) {
                e.printStackTrace();
                syslog.error("邮件发送异常：", e);
            }
        }

        /**
         * 采用465 ssl加密方式发送邮件
         *
         * @param to
         * @param topic
         * @param message
         * @author tanyicheng 创建时间：2017年10月11日-下午8:33:50
         */
        public static void sendEmailSll(String to, String topic, String message) {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.163.com");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "false");

            //获取配置文件中的发件人信息
            // 获得Session,验证发送者的账号密码
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailSender, MailAuthorization);
                }
            });
            // 获得store对象
            Message msg = new MimeMessage(session);

            try {
                // 设置邮件接收者
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                // 设置邮件的主题
                msg.setSubject(topic);
                // 设置邮件的发送者（发送账号，别名，编码）
                msg.setFrom(new InternetAddress(mailSender, "1felse", "UTF-8"));

                // 邮件的内容
                msg.setContent(message, "text/html;charset=utf-8");
                // 异步处理
                Inclass inclass = new Inclass(msg);
                Thread thread = new Thread(inclass);
                thread.start();
            } catch (UnsupportedEncodingException e) {
                System.out.println("不支持的字符编码");
                syslog.error("不支持的字符编码：", e);
                e.printStackTrace();
            } catch (AddressException e) {
                System.out.println("地址错误 ");
                syslog.error("地址错误", e);
            } catch (MessagingException e) {
                e.printStackTrace();
                syslog.error("邮件发送异常：", e);
            }
        }

        public static void main(String[] args) {
            //接收人账号，主题，内容
            //sendEmail("18115059001@163.com", "java测试", "恭喜发财");
            // i_1felse@163.com
//            sendEmailSll("yicheng.tan@seraphim-energy.com", "测试标题", "上线无bug");
//        sendEmailSll("65797706@qq.com", "测试标题", "上线无bug");
            sendEmail("yicheng.tan@seraphim-energy.com", "java测试", "恭喜发财");
        }

        /**
         * **退信代码说明**：
         　　•421 HL:REP 该IP发送行为异常，存在接收者大量不存在情况，被临时禁止连接。请检查是否有用户发送病毒或者垃圾邮件，并核对发送列表有效性；
         　　•421 HL:ICC 该IP同时并发连接数过大，超过了网易的限制，被临时禁止连接。请检查是否有用户发送病毒或者垃圾邮件，并降低IP并发连接数量；
         　　•421 HL:IFC 该IP短期内发送了大量信件，超过了网易的限制，被临时禁止连接。请检查是否有用户发送病毒或者垃圾邮件，并降低发送频率；
         　　•421 HL:MEP 该IP发送行为异常，存在大量伪造发送域域名行为，被临时禁止连接。请检查是否有用户发送病毒或者垃圾邮件，并使用真实有效的域名发送；
         　　•450 MI:CEL 发送方出现过多的错误指令。请检查发信程序；
         　　•450 MI:DMC 当前连接发送的邮件数量超出限制。请减少每次连接中投递的邮件数量；
         　　•450 MI:CCL 发送方发送超出正常的指令数量。请检查发信程序；
         　　•450 RP:DRC 当前连接发送的收件人数量超出限制。请控制每次连接投递的邮件数量；
         　　•450 RP:CCL 发送方发送超出正常的指令数量。请检查发信程序；
         　　•450 DT:RBL 发信IP位于一个或多个RBL里。请参考http://www.rbls.org/关于RBL的相关信息；
         　　•450 WM:BLI 该IP不在网易允许的发送地址列表里；
         　　•450 WM:BLU 此用户不在网易允许的发信用户列表里；
         　　•451 DT:SPM ,please try again 邮件正文带有垃圾邮件特征或发送环境缺乏规范性，被临时拒收。请保持邮件队列，两分钟后重投邮件。需调整邮件内容或优化发送环境；
         　　•451 Requested mail action not taken: too much fail authentication 登录失败次数过多，被临时禁止登录。请检查密码与帐号验证设置；
         　　•451 RP:CEL 发送方出现过多的错误指令。请检查发信程序；
         　　•451 MI:DMC 当前连接发送的邮件数量超出限制。请控制每次连接中投递的邮件数量；
         　　•451 MI:SFQ 发信人在15分钟内的发信数量超过限制，请控制发信频率；
         　　•451 RP:QRC 发信方短期内累计的收件人数量超过限制，该发件人被临时禁止发信。请降低该用户发信频率；
         　　•451 Requested action aborted: local error in processing 系统暂时出现故障，请稍后再次尝试发送；
         　　•500 Error: bad syntaxU 发送的smtp命令语法有误；
         　　•550 MI:NHD HELO命令不允许为空；
         　　•550 MI:IMF 发信人电子邮件地址不合规范。请参考http://www.rfc-editor.org/关于电子邮件规范的定义；
         　　•550 MI:SPF 发信IP未被发送域的SPF许可。请参考http://www.openspf.org/关于SPF规范的定义；
         　　•550 MI:DMA 该邮件未被发信域的DMARC许可。请参考http://dmarc.org/关于DMARC规范的定义；
         　　•550 MI:STC 发件人当天的连接数量超出了限定数量，当天不再接受该发件人的邮件。请控制连接次数；
         　　•550 RP:FRL 网易邮箱不开放匿名转发（Open relay）；
         　　•550 RP:RCL 群发收件人数量超过了限额，请减少每封邮件的收件人数量；
         　　•550 RP:TRC 发件人当天内累计的收件人数量超过限制，当天不再接受该发件人的邮件。请降低该用户发信频率；
         　　•550 DT:SPM 邮件正文带有很多垃圾邮件特征或发送环境缺乏规范性。需调整邮件内容或优化发送环境；
         　　•550 Invalid User 请求的用户不存在；
         　　•550 User in blacklist 该用户不被允许给网易用户发信；
         　　•550 User suspended 请求的用户处于禁用或者冻结状态；
         　　•550 Requested mail action not taken: too much recipient  群发数量超过了限额；
         　　•552 Illegal Attachment 不允许发送该类型的附件，包括以.uu .pif .scr .mim .hqx .bhx .cmd .vbs .bat .com .vbe .vb .js .wsh等结尾的附件；
         　　•552 Requested mail action aborted: exceeded mailsize limit 发送的信件大小超过了网易邮箱允许接收的最大限制；
         　　•553 Requested action not taken: NULL sender is not allowed 不允许发件人为空，请使用真实发件人发送；
         　　•553 Requested action not taken: Local user only  SMTP类型的机器只允许发信人是本站用户；
         　　•553 Requested action not taken: no smtp MX only  MX类型的机器不允许发信人是本站用户；
         　　•553 authentication is required  SMTP需要身份验证，请检查客户端设置；
         　　•**554 DT:SPM 发送的邮件内容包含了未被许可的信息，或被系统识别为垃圾邮件。请检查是否有用户发送病毒或者垃圾邮件；**
         　　•554 DT:SUM 信封发件人和信头发件人不匹配；
         　　•554 IP is rejected, smtp auth error limit exceed 该IP验证失败次数过多，被临时禁止连接。请检查验证信息设置；
         　　•554 HL:IHU 发信IP因发送垃圾邮件或存在异常的连接行为，被暂时挂起。请检测发信IP在历史上的发信情况和发信程序是否存在异常；
         　　•554 HL:IPB 该IP不在网易允许的发送地址列表里；
         　　•554 MI:STC 发件人当天内累计邮件数量超过限制，当天不再接受该发件人的投信。请降低发信频率；
         　　•554 MI:SPB 此用户不在网易允许的发信用户列表里；
         　　•554 IP in blacklist 该IP不在网易允许的发送地址列表里。
         */
    }
}

