package com.bpms.util;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailUtil {
	public static void main(String[] args) throws Exception {
		Properties prop = new Properties();
		prop.setProperty("mail.host", "smtp.263xmail.com");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		//使用javamail发送邮件
		//1、创建session
		Session session = Session.getInstance(prop);
		//开启session的debug模式，这样可以查看到程序的email的运行状态
		session.setDebug(true);
		//2、通过session得到transport对象
		Transport ts = session.getTransport();
		//3、使用邮箱的用户名密码连上邮件服务器，发送邮件时，发件人本人需要提交邮箱的用户名密码给smtp服务器，通过验证后才能发送邮件
		ts.connect("smtp.263xmail.com","yibansun@qqjrbj.com","sunyiban123");
		//4、创建邮件
		Message message = createMixedMail(session);
		//5、发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}

	/**
	 * 创建纯文本内容的邮件
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createSimpleMail(Session session)
			throws Exception{
		//创建邮件对象
		MimeMessage message = new MimeMessage(session);
		//指明邮件的发件人
		message.setFrom(new InternetAddress("sunyiban@sina.com"));
		//指明邮件的收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("sunyiban@sina.com"));
		//邮件的标题
		message.setSubject("只包含文本的简单邮件");
		//邮件的文本内容
		message.setContent("你好啊!","text/html;charset=UTF-8");
		//返回创建好的邮件对象
		return message;
	}	
	
	/**
	 * 创建带图片的邮件
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createImageMail(Session session) throws Exception{
		//创建邮件
		MimeMessage message = new MimeMessage(session);
		//设置邮件的基本信息
		//发件人
		message.setFrom(new InternetAddress("sunyiban@sina.com"));
		//收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("sunyiban@sina.com"));
		//邮件标题
		message.setSubject("带图片的邮件");
		
		//准备邮件数据
		//准备邮件正文数据
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("这是一封邮件正文带图片的邮件","text/html;charset=UTF-8");
		//准备图片数据
		MimeBodyPart image = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("WebContent/img/check.png"));
		image.setDataHandler(dh);
		image.setContentID("xxx.png");
		//描述数据关系
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text);
		mm.addBodyPart(image);
		mm.setSubType("related");
		
		message.setContent(mm);
		message.saveChanges();
		//将创建好的邮件写入到E盘以文件形式保存
		message.writeTo(new FileOutputStream("E:\\ImageMail.eml"));
		//返回创建好的邮件
		return message;
	}
	
	/**
	 * 创建带附件的邮件
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createAttachMail (Session session) throws Exception{
		MimeMessage message = new MimeMessage(session);
		
		//设置邮件的基本信息
		//发件人
		message.setFrom(new InternetAddress("sunyiban@sina.com"));
		//收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("sunyiban@sina.com"));
		//邮件标题
		message.setSubject("JavaMail邮件发送测试");
		
		//创建邮件正文，为了避免邮件正文中文乱码问题，需要指定字符编码
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("使用JavaMail创建的带附件的邮件","text/html;charset=UTF-8");
		
		//创建邮件附件
		MimeBodyPart attach = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("WebContent/img/next.png"));
		attach.setDataHandler(dh);
		attach.setFileName(dh.getName());
		
		//创建容器描述数据关系
		MimeMultipart mp = new MimeMultipart();
		mp.addBodyPart(text);
		mp.addBodyPart(attach);
		mp.setSubType("mixed");
		
		message.setContent(mp);
		message.saveChanges();
		return message;
	}
	
	/**
	 * 发送包含内嵌图片和附件的复杂邮件
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createMixedMail(Session session) throws Exception{
		//创建邮件
		MimeMessage message = new MimeMessage(session);
		
		//设置邮件的基本信息
		message.setFrom(new InternetAddress("yibansun@qqjrbj.com"));
		message.setRecipient(Message.RecipientType.TO,new InternetAddress("honghuliu@qqjrbj.com"));
		message.setSubject("带图片和附件的邮件");
		
		//正文
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("263还能集成系统<br/><img src='cid:aaa.png'>","text/html;charset=UTF-8");
		
		//图片
		MimeBodyPart image = new MimeBodyPart();
		image.setDataHandler(new DataHandler(new FileDataSource("WebContent/img/next.png")));
		image.setContentID("aaa.png");
		
		//附件1
		MimeBodyPart attach = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("WebContent/data.json"));
		attach.setDataHandler(dh);
		attach.setFileName(dh.getName());
		
		//附件2
		MimeBodyPart attach2 = new MimeBodyPart();
		DataHandler dh2 = new DataHandler(new FileDataSource("WebContent/readme.txt"));
		attach2.setDataHandler(dh2);
		//中文文件名，编码控制
		attach2.setFileName(MimeUtility.encodeText(dh2.getName()));
		
		//描述关系：正文和图片
		MimeMultipart mp1 = new MimeMultipart();
		mp1.addBodyPart(text);
		mp1.addBodyPart(image);
		mp1.setSubType("related");
		
		//描述关系：正文和附件
		MimeMultipart mp2 = new MimeMultipart();
		mp2.addBodyPart(attach);
		mp2.addBodyPart(attach2);
		
		//代表正文的bodypart
		MimeBodyPart content = new MimeBodyPart();
		content.setContent(mp1);
		mp2.addBodyPart(content);
		mp2.setSubType("mixed");
		
		message.setContent(mp2);
		message.saveChanges();
		
		return message;
	}
}
