<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>订单详情</title>
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
         .search{
                background-image:url(../media/css/icons/search.png);
                background-repeat:no-repeat;
                height: 30px;
			    /* left: 365px; */
			    position: absolute;
			    /* top: 180px;  */
			    width: 30px;
			    z-index: 99;
        }
	</style>
	<style>
		.lightbox{width:300px;background:#FFFFFF;border:1px solid #ccc;line-height:25px; top:20%; left:20%;}
		.lightbox dt{background:#f4f4f4; padding:5px;}
	</style>
</head>
<body class="easyui-layout" >
	<script type="text/javascript">
		$(function(){
			var left = document.getElementById("simid").getBoundingClientRect().left;
			var top = document.getElementById("simid").getBoundingClientRect().top;
			var width = document.getElementById("simid").offsetWidth;
			
			document.getElementById("search_img").style.left = left+width-25+"px";
			document.getElementById("search_img").style.top = top+2+"px";
		}); 
	</script>
	<div class="indiv" style="height:100%;width:100%;background-color: #F8F8FF;overflow: hidden;" >
		<form id="editform">
			<table cellpadding="12" align="center" width="100%">
				<!-- 一行四条信息 -->
				<tr>
					<td width="10%">设备编号</td>
					<td width="15%"><input type="text" readonly="readonly" name="no"  value="${equipment.no }"></input></td>
					
					<td width="15%">SIM卡</td>
					<td width="15%"><!-- onblur="checkIsBind(this);"  -->
						<input id="simid" type="text" readonly="readonly" name="sim_id" value="${equipment.sim_id }" style="background-color: white;" ></input>
						<div id="search_img" class="search"></div>
					</td>
					
					<td width="15%">柜台 </td>
					<td>
						<select id="countercode" name="counterid"  style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 130px;" >
							<option value="${counterid }" selected="selected">${equipment.counterid }</option>
							<c:if test="${counterlist!=null }">
								<c:forEach items="${counterlist }" var="counterlists">
									<option value="${counterlists.counter_code }">${counterlists.name }</option>
								</c:forEach>
							</c:if>
						</select>　
					</td>
				</tr>
				
				<tr>
					<td width="10%">租用日期</td>
					<td>
					<input width="15%"  readonly="readonly" type="text" name="rent_begindate" value="${equipment.rent_begindate }"></input>
					</td>
					
					<td>预计返还日期 </td>
					<td>
					<input  type="text" readonly="readonly" name="rent_expectdate" value="${equipment.rent_expectdate }"></input>
					</td>
					
					<td >实际返还日期</td>
					<td>
					<input type="text" readonly="readonly"  name="rent_enddate" value="${equipment.rent_enddate }"></input>
					</td>
				</tr>
				
				<tr>
					<td>进货日期</td>
					<td>
					<input type="text" readonly="readonly"  name="stock_date" value="${equipment.stock_date }" />
					<td>报废日期</td>
					<td>
					<input type="text" name="scrap_date" class="Wdate" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy/MM/dd HH:mm:ss'})" value="${equipment.scrap_date }" />
					</td>
					<td>日租金</td>
					<td>
					<input  readonly="readonly" name="day_rent"   type="text" name="rent_enddate"  value="${equipment.day_rent }" />
					</td>
				</tr>
				
				<tr>
					<td>押金 </td>
					<td>
					<input  type="text" readonly="readonly"  name="deposit"  value="${equipment.deposit }"></input>
					</td>
					<td>设备租金 </td>
					<td>
					<input type="text" readonly="readonly"  name="total_rent"  value="${equipment.total_rent}"></input>
					</td>
					<td>设备类型 </td>
					<td>
					<input  type="text"  readonly="readonly"  name="equipment_type" value="${equipment.equipment_type }"></input>
					</td>
				</tr>
				
				<tr>
					<td>状态</td>
					<td>
					 <input  type="text" readonly="readonly"  name=""  value="${equipment.equipment_state }"></input>
					<%-- <select id="equipment_state" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:130px;" name="equipment_state">
								<option value="${equipment_state }" selected="selected">${equipment.equipment_state }</option>
								<option value="0001">可用</option>
								<option value="0002">已租</option>
					</select> --%>
					</td>
					<td>是否有效 </td>
					<td>
					<%-- <input  type="text"  name="rent_day"  value="${equipment.is_valid }"></input> --%>
					<select name="is_valid" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:130px;" >
						<option value="${is_valid }" selected="selected">${equipment.is_valid }</option>
						<option value="0001">有效</option>
						<option value="0002">无效</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>创建人 </td>
					<td>
					<input type="text" readonly="readonly"  name="create_user"  value="${equipment.create_user }"></input>
					</td>
					<td>创建时间 </td>
					<td>
					<input type="text" readonly="readonly"  name="create_time"  value="${equipment.create_time }"></input>
					</td>
				</tr>
				
				<tr>
					<td >备注 </td>
					<td colspan="7">
					<textarea name="remark" style="background-color:#F0F8FF;border: 1px solid #95b8e7;border-radius: 5px;height: 100px;width:98%;resize:none;font-size: 18px;color: black;" resize="none"  readonly="readonly"  id="remark" >${equipment.remark }</textarea>
					</td>
				</tr>
				</table>	
				<!-- 如果此SIM卡已经绑定，记录状态是否要更新 值为1表示已经绑定-->
				<input type="text" style="display: none;" name="isbinding" value="" id="isbinding">
				<!-- 记录设备SIM卡号 -->
				<!-- <input type="text" style="display:none;" id="simnunber"> -->
			</form>
				<button style="float: right;margin-right: 10px;width: 60px;height:30px;" onclick="queding();">确定</button>
			</div>
				
			<script type="text/javascript">
				function queding(){
					jQuery.ajax({
						url:"edit",
						data:$("#editform").serialize(),
						type:"post",
						error:function(){alert("修改失败!");},
						success:function(data){
							if(data=="1"){
								alert("修改成功!");
								parent.$('#edits').dialog('close');
								parent.window.location.reload(true);
							}else{
								alert("修改失败!");
							}
						}
					});
				}
				
				/* $("#simid").click(function(){
					//alert("simid.click");
					if($("#simid").val()!=""){
						$("#simnumber").val(1);
					}
				}); */
				
				function checkIsBind(id){
					//alert(id.value);
						var id = id.value;
						jQuery.ajax({
							url:"../info/checkIsBind",
							data:{id:id},
							type:"post",
							error:function(){alert("ERROR!")},
							success:function(data){
								//alert(data);
								if(data==1){
									if(confirm("此SIM卡已经绑定,确定要更新其绑定设备吗?")){
										$("#isbinding").val("1");
									}
								}
							}
						});
				}
				
				$("#search_img").click(function(){
					parent.frames['simpicklist'].document.getElementById("simpicklisttype").value="1";
					parent.$("#infolist").dialog("open");
				})
			</script>
</body>
</html>