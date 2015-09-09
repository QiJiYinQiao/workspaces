package cn.itcast.ssh.domain;

public class Employee {
	private Long id;//主键ID
	private String name;//用户名
	private String password;//密码
	private String email;//电子邮箱
	private String role;//角色
	private Employee manager;

}
