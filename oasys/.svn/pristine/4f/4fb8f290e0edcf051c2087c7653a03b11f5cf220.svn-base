package com.oasys.webService.model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class User {
	private String userid;
	private String name;
	private Integer departmentid;
	private String office;
	private String mobile;
	private String phone;
	
	public User setUserFunc(String str,String value){
		switch (str) {
		case "userid":
			setUserid(value);
			break;
		case "name":
			setName(value);
			break;
		case "departmentid":
			setDepartmentid(Integer.valueOf(value));
			break;
		case "office":
			setOffice(value);
			break;
		case "mobile":
			setMobile(value);
			break;			
		case "phone":
			setPhone(value);
			break;			
		default:
			break;
		}
		return this;
	} 
	
	public static List<User> parse(String protocolXML){
		List<User> userList = new ArrayList<User>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(
					protocolXML)));
			Element root = doc.getDocumentElement();
			NodeList books = root.getChildNodes();
			User user = new User();
			String value = "";
			if (books != null) {
				for (int i = 0; i < books.getLength(); i++) {
					Node book = books.item(i);
					NodeList nodeList = book.getChildNodes();
					user = new User();
					for (int j = 0; j < nodeList.getLength(); j++) {
						book = nodeList.item(j);
						value = book.getFirstChild() ==null ? "": book.getFirstChild().getNodeValue();
						user = user.setUserFunc(book.getNodeName(), value);
					}
					userList.add(user);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userList;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
