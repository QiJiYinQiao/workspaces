package com.oasys.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Entity
@Table(name = "t_department",catalog="QQMS")
public class Department {
	
	private Integer id;
	private Integer departmentId;
	private String departmentName;
	private String description;
	private Integer deptId;
	
	public Department setDepartMentFunc(String str,String value){
		switch (str) {
		case "departmentId":
			setDepartmentId(Integer.valueOf(value.toString()));
			break;
		case "departmentName":
			setDepartmentName(value);
			break;
		case "description":
			setDescription(value);
			break;
		case "depId":
			setDeptId(Integer.valueOf(value));
			break;
		default:
			break;
		}
		return this;
	} 
	
	public static List<Department> parse(String protocolXML){
		List<Department> deptList = new ArrayList<Department>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(
					protocolXML)));
			Element root = doc.getDocumentElement();
			NodeList books = root.getChildNodes();
			Department dept = new Department();
			String value = "";
			if (books != null) {
				for (int i = 0; i < books.getLength(); i++) {
					Node book = books.item(i);
					NodeList nodeList = book.getChildNodes();
					dept = new Department();
					for (int j = 0; j < nodeList.getLength(); j++) {
						book = nodeList.item(j);
						value = book.getFirstChild() ==null ? "": book.getFirstChild().getNodeValue();
						dept = dept.setDepartMentFunc(book.getNodeName(), value);
					}
					deptList.add(dept);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return deptList;
	}
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "DEPARTMENT_ID")
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	@Column(name = "DEPARTMENT_NAME",length = 50)
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	@Column(name = "DESCRIPTION",length = 50)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "DEPT_ID")
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
}
