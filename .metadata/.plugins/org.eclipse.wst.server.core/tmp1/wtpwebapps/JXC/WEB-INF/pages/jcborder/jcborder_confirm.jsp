<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>网站订单详情</title>
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		input{
			border: 1px solid #95b8e7;border-radius: 5px;height: 20px;background-color: rgb(240,248,255);
		}
		td{
			font-size: 16px;
		}
		
		 .container{
                position:relative;
        }
        table{border-collapse:collapse;border-spacing:0;}
        td{padding:5px;margin:5px;} 
        table tr td input{
        	border: 1px solid #95b8e7;border-radius: 5px;background-color: rgb(240,248,255);
        	readonly:readonly;
        }
	</style>
	<style>
		.lightbox{width:300px;background:#FFFFFF;border:1px solid #ccc;line-height:25px; top:20%; left:20%;}
		.lightbox dt{background:#f4f4f4; padding:5px;}
	</style>
</head>
<body class="easyui-layout" >
	<div class="indiv" style="height:100%;width:100%;background-color: #F8F8FF;overflow: hidden;" >
		<table cellpadding="4" align="center" width="100%" style="margin-left: 20px;margin-top: 20px;">
		<!-- 一行四条信息 -->
		<tr>
			 <td width="15%">目的地</td>
			<td width="18%"><input  type="text" name="upassport" value="${jcborder.goodname }" readonly="readonly" ></input></td>
			 
			<td width="10%">姓名</td>
			<td width="18%">
			<input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" readonly="readonly"  name="uname" value="${jcborder.name }"></input>
			</td>
			
			<td width="10%">手机号</td>
			<td width="18%"><!-- checkMobile(this.value) -->
			<input style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  readonly="readonly" id="rent_p_tel" name="uphone" value="${jcborder.phone }"></input>
			</td>
			
		</tr>
		<tr>
			<td>城市</td>
			<td><input  type="text" name="upassport" value="${jcborder.cfcity }" readonly="readonly" ></input></td>
			
			<td>柜台</td>
			<td colspan="3"><input style="width: 250px;"  type="text" name="upassport" value="${jcborder.qhaddress }" readonly="readonly" ></input></td>
			
		</tr>
		<tr>
			<td>用户名</td>
			<td width="15%"><input  type="text" name="username" value="${jcborder.username }" readonly="readonly" ></input></td>
			
			<td>租用日期</td>
			<td>
			<input readonly="readonly" type="text" name="rentdate" value="${jcborder.cfdate }" readonly="readonly" />
			</td>
			
			<td>返还日期</td>
			<td>
			<input readonly="readonly" type="text" name="enddate" value="${jcborder.returndate }" readonly="readonly" />
			</td>
		</tr>
		
		<tr>
			<td>JCB卡号 </td>
			<td>
			<input  style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" value="${jcborder.jbc }"  readonly="readonly" ></input>
			</td>
			
			<td>卡种</td>
			<td>
			<input  style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" value="${jcborder.cardtype }"  readonly="readonly" ></input>
			</td>
			
			<td>租金</td>
			<td><!-- onkeyup="keyups(this.value)"  -->
			<input  style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;"  readonly="readonly" type="text" name="rent_cost" value="${jcborder.rent}" ></input>
			</td>
			
		</tr>
		<tr>
			<td>押金 </td>
			<td>
			<input id="deposit" name="deposit" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" readonly="readonly" type="text"  value="${jcborder.ordersyajin }"></input>
			</td>
		
			<td>总价 </td>
			<td>
			<input id="shouldgetcost" style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text"  readonly="readonly"  name="shouldgetcost" value="${jcborder.allprice }" ></input>
			</td>
			
			<td>订单状态</td>
			<td>
			<input  style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" value="${jcborder.state }"  readonly="readonly" ></input>
			</td>
		</tr>	
		<tr>
			<td>支付状态</td>
			<td>
			<input  style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" value="${jcborder.payment }" readonly="readonly"  ></input>
			</td>
			
			<td>取货方式</td>
			<td>
			<input  style="border: 1px solid #95b8e7;border-radius: 5px;height: 20px;" type="text" value="${jcborder.way }" readonly="readonly"  ></input>
			</td>
			
			<td>护照号</td>
			<td><input  type="text" name="upassport" value="${jcborder.passport }" readonly="readonly" ></input></td>
		</tr>
		<tr>
			<td>是否电话确认 </td>
			<td>
				<select id="isphoneconfirm" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="isphoneconfirm">
					<option value="1" selected="selected">是</option>
					<option value="0" >否</option>
				</select>
			</td>
			
			<td>下单日期 </td>
			<td>
			<input style="border: 1px solid #95b8e7;border-radius: 5px;" readonly="readonly" type="text" name="modifytime" value="${jcborder.createtime}" />
			</td>
			
		</tr>
		<tr>
			<td >用户留言</td>
			<td colspan="7">
			<textarea  style="border: 1px solid #95b8e7;border-radius: 5px;height: 60px;width:90%;resize:none;overflow-Y:scroll;font-size: 18px;background-color: rgb(240,248,255);" resize="none"  readonly="readonly"  name="remark">${jcborder.remark }</textarea>
			</td>
		</tr>
		<tr>
			<td >电话留言</td>
			<td colspan="7">
			<textarea  style="border: 1px solid #95b8e7;border-radius: 5px;height: 60px;width:90%;resize:none;overflow-Y:scroll;font-size: 18px;background-color:white;" resize="none" id="phoneremark" name="phoneremark"></textarea>
			</td>
		</tr>
		</table>	
		<input value="${jcborder.orderid }" id="orderid" type="hidden">
		<a onclick="cancelEdit();" class="easyui-linkbutton" style="margin-right: 20px;margin-top:10px;width: 60px;height: 25px;float: right;">取消</a>
		<a onclick="confirmEdit();" class="easyui-linkbutton" style="margin-right: 20px;margin-top:10px;width: 60px;height: 25px;float: right;">确认</a>
	</div>
			
		<script type="text/javascript">
			function confirmEdit(){
					var isphoneconfirm = $("#isphoneconfirm").val();
					var phoneremark = $("#phoneremark").val();
					var orderid = $("#orderid").val();
					//alert("isphoneconfirm="+isphoneconfirm+":"+phoneremark);
					jQuery.ajax({
						url:"editPhoneMsg",
						type:"post",
						data:{isphoneconfirm:isphoneconfirm,phoneremark:phoneremark,id:orderid},
						error:function(){},
						success:function(data){
							if(data==1){
								alert("保存成功!");
								parent.$("#dlg").dialog("close");
								parent.$("#jcbOrderTable").datagrid("reload");
							}else{
								alert("保存失败!");
							}
						}
					})
			}
			function cancelEdit(){
				parent.$("#dlg").dialog("close");
			}
		</script>
</body>
</html>