<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.oasys.util.Constants"%>
<%@page import="com.oasys.shiro.ShiroUser"%>

<%
	ShiroUser user = Constants.getCurrendUser();
%>
<script type="text/javascript" charset="utf-8">
	function logout(b) {
		$.messager.confirm("提示", "确认退出吗?", function(r) {
			if (r) {
				$.ajax({
					async : false,
					cache : false,
					type : "POST",
					url : "logout.do",
					error : function() {
					},
					success : function(json) {
						location.replace("login.jsp");
					}
				});
			}
		});

	}

	var changePwdDlg;
	function changePwd() {
		changePwdDlg = $('<div/>').dialog({
			modal : true,
			title : '更改登录密码',
			width : 350,
			height : 200,
			href : 'user/changePwdDlg.jsp',
			onClose : function() {
				changePwdDlg.dialog('destroy');
			},
			buttons : [ {
				text : '确认',
				iconCls : 'icon-ok',
				handler : function() {
					//密码不能为空，密码必须一致
					if($("#password").val()==""||$("#password").val()==null){
						alert("密码不能为空！");
						return;
					}else{
						if($("#password").val()!=$("#rpwd").val()){
							alert("两次输入密码不一致！");
							$("#chgForm").form("clear");
							return;
						}else{
							//执行更改密码操作
							$.ajax({
								url:'changePwd.do',
								type:'post',
								data:'pwd='+$("#password").val(),
								success:function(rsp){
									$.messager.alert("提示信息",rsp.message);
									changePwdDlg.dialog('destroy');
								},
								error:function(rsp){
									$.messager.alert("提示信息",rsp.message);
								}
							})
						}
					}
					
					
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					changePwdDlg.dialog('destroy');
				}
			}
			]
		});
	}
	function changeTheme(item) {
		var cookiesColor1 = jqueryUtil.cookies.get("cookiesColor");
		if (cookiesColor1 != item) {
			jqueryUtil.cookies.set("cookiesColor", item, 30);
			jqueryUtil.chgSkin(item, cookiesColor1);
		}
	}
	$(function() {
		startTime();
		$(".frame_head_admin_box").css("marginLeft",parseInt($(window).width())*0.5);
		$(".frame_head_admin_box").css("marginTop",23);
	})
	function startTime() {
		var today = new Date()
		var week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
		var year = today.getYear() + 1900
		var month = today.getMonth() + 1
		var date = today.getDate()
		var day = today.getDay()
		var h = today.getHours()
		var m = today.getMinutes()
		var s = today.getSeconds()
		// add a zero in front of numbers<10  
		h = checkTime(h)
		m = checkTime(m)
		s = checkTime(s)
		document.getElementById('time').innerHTML = " " + year + "年" + month
				+ "月" + date + "日  " + week[day] + "  " + h + ":" + m + ":" + s
				+ " "
		t = setTimeout('startTime()', 500)
	}

	function checkTime(i) {
		if (i < 10) {
			i = "0" + i
		}
		return i
	}
</script>
<style>
.frame_head {
	width: 100%;
	height: 61px;
	background: url(extend/head_bg.jpg) repeat-x;
	overflow: hidden;
}

.frame_head_bg {
	width: 355px;
	height: 61px;
	background: url(extend/head_right.jpg) right top no-repeat;
	float: right;
}

.frame_head_admin {
	float: left;
	width: 'auto';
	height: 61px; /* background:url(extend/logo.png)  no-repeat; */
	overflow: hidden;
}

.frame_head_admin_box {
	height: 24px;
	font-size: 12px;
	/* margin: 20px 0 0 980px; */
	line-height: 24px;
	background: #fff;
	opacity: 0.7;
	filter: progid:DXImageTransform.Microsoft.Alpha(opacity=70);
	-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(opacity=70)";
}

.admin_left {
	background: url(extend/admin_left.gif) no-repeat;
	width: 7px;
}

.admin_right {
	background: url(extend/admin_right.gif) no-repeat;
	width: 7px;
}

.admin_icon {
	width: 28px;
	height: 24px;
	background: url(extend/admin_icon.gif) no-repeat;
}

.admin_padding {
	padding: 0 10px;
}

.admin_font {
	font-weight: bold;
}
</style>
<div class="frame_head">
	<div class="frame_head_bg"></div>
	<span style="position: absolute; left: 250px; top: 23px; font: 25px bold; font-family: '微软雅黑'">钱钱金融OA办公系统</span>
	<div class="frame_head_admin">
		<table cellpadding="0" cellspacing="0" border="0"
			class="frame_head_admin_box" style="width: 100%">
			<tr>
				<td width="7" class="admin_left"></td>
				<td width="28" class="admin_icon"></td>
				<td>欢迎您:<span class="admin_font"
					style="margin-right: 10px; color: red;"><%=user.getName()%></span><span
					id="time"></span></td>
				<td></td>
				<td width="7" class="admin_right"></td>
			</tr>
		</table>
	</div>
</div>

<div style="position: absolute; right: 0px; bottom: 0px;">
	<a href="javascript:void(0);" class="easyui-menubutton"
		menu="#layout_north_kzmbMenu" iconCls="icon-help">控制面板</a> <a
		href="javascript:void(0);" class="easyui-menubutton"
		menu="#layout_north_zxMenu" iconCls="icon-logout">注销</a>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div onclick="changePwd();">更改登录密码</div>
	<div class="menu-sep"></div>
	<div>
		<span>更换主题</span>
		<div style="width: 120px;">
			<div onclick="changeTheme('default');">default</div>
			<div onclick="changeTheme('gray');">gray</div>
			<div onclick="changeTheme('black');">black</div>
			<div onclick="changeTheme('metro');">metro</div>
			<div onclick="changeTheme('bootstrap');">bootstrap</div>
		</div>
	</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<!-- <div onclick="loginAndRegDialog.dialog('open');">锁定窗口</div> -->
	<div class="menu-sep"></div>
	<div onclick="logout();">重新登录</div>
	<div onclick="logout(true);">退出系统</div>
</div>
