<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragrma","no-cache");
response.setDateHeader("Expires",0);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	<meta HTTP-EQUIV="expires" CONTENT="0">
	<title>登陆首页</title>
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		.indiv input{
			border: 1px solid #95b8e7;
			border-radius: 5px;
			height: 20px;
			/* background-color:#B0C4DE; */
			font-size:16px;
			background-color:#F0F8FF;
			
		}
		td span{
			font-size: 16px;
		}
		#screens td{
			color: black;
			font-size: 16px;
		}
		#screens td input{
			color: black;
		}
		.datagrid-view{
			background-color: #F8F8FF;
			font-size: 16px;
		}
		.searchinput{
			border: 1px solid #95b8e7;border-radius: 5px;height:20px;
		}
	
		</style>
</head>
<body class="easyui-layout">
	<script >
		  $(function(){
				 var today = Date.parse(new Date());
				 var diffday = 30*24*60*60*1000;
				 var begin = today-diffday;
				 var begindate = new Date(begin).pattern("yyyy-MM-dd");
				 $("#begindate").val(begindate);
				 $("#enddate").val(new Date().pattern("yyyy-MM-dd"));
				 var begindate = $('#begindate').val();
				 var enddate = $('#enddate').val();
				 $('#dg').datagrid({
					url:"getOrderByTime?begintime="+begindate+"&endtime="+enddate
				});
				 var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
				 $(pager).pagination({  
					 	beforePageText: '第',//页数文本框前显示的汉字  
				        afterPageText: '页    共 {pages} 页',  
				        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
				    });  
			
			});  
	</script>
	<script type="text/javascript">
	/**   
	* 扩展两个方法   
	*/
	$.extend($.fn.datagrid.methods, {
	    /** 
	    * 开打提示功能   
	    * @param {} jq   
	    * @param {} params 提示消息框的样式   
	    * @return {}   
	    */
	    doCellTip: function (jq, params) {
	        function showTip(data, td, e) {
	            if ($(td).text() == "")
	                return;
	            data.tooltip.text($(td).text()).css({
	                top: (e.pageY + 10) + 'px',
	                left: (e.pageX + 20) + 'px',
	                'z-index': $.fn.window.defaults.zIndex,
	                display: 'block'
	            });
	        };
	        return jq.each(function () {
	            var grid = $(this);
	            var options = $(this).data('datagrid');
	            if (!options.tooltip) {
	                var panel = grid.datagrid('getPanel').panel('panel');
	                var defaultCls = {
	                    'border': '1px solid #333',
	                    'padding': '1px',
	                    'color': '#333',
	                    'background': '#f7f5d1',
	                    'position': 'absolute',
	                    'max-width': '200px',
	                    'border-radius': '4px',
	                    '-moz-border-radius': '4px',
	                    '-webkit-border-radius': '4px',
	                    'display': 'none'
	                }
	                var tooltip = $("<div id='celltip'></div>").appendTo('body');
	                tooltip.css($.extend({}, defaultCls, params.cls));
	                options.tooltip = tooltip;
	                panel.find('.datagrid-body').each(function () {
	                    var delegateEle = $(this).find('> div.datagrid-body-inner').length
	                            ? $(this).find('> div.datagrid-body-inner')[0]
	                            : this;
	                    $(delegateEle).undelegate('td', 'mouseover').undelegate(
	                            'td', 'mouseout').undelegate('td', 'mousemove')
	                            .delegate('td', {
	                                'mouseover': function (e) {
	                                    if (params.delay) {
	                                        if (options.tipDelayTime)
	                                            clearTimeout(options.tipDelayTime);
	                                        var that = this;
	                                        options.tipDelayTime = setTimeout(
	                                                function () {
	                                                    showTip(options, that, e);
	                                                }, params.delay);
	                                    } else {
	                                        showTip(options, this, e);
	                                    }

	                                },
	                                'mouseout': function (e) {
	                                    if (options.tipDelayTime)
	                                        clearTimeout(options.tipDelayTime);
	                                    options.tooltip.css({
	                                        'display': 'none'
	                                    });
	                                },
	                                'mousemove': function (e) {
	                                    var that = this;
	                                    if (options.tipDelayTime) {
	                                        clearTimeout(options.tipDelayTime);
	                                        options.tipDelayTime = setTimeout(
	                                                function () {
	                                                    showTip(options, that, e);
	                                                }, params.delay);
	                                    } else {
	                                        showTip(options, that, e);
	                                    }
	                                }
	                            });
	                });

	            }

	        });
	    },
	    /** 
	    * 关闭消息提示功能   
	    * @param {} jq   
	    * @return {}   
	    */
	    cancelCellTip: function (jq) {
	        return jq.each(function () {
	            var data = $(this).data('datagrid');
	            if (data.tooltip) {
	                data.tooltip.remove();
	                data.tooltip = null;
	                var panel = $(this).datagrid('getPanel').panel('panel');
	                panel.find('.datagrid-body').undelegate('td',
	                                'mouseover').undelegate('td', 'mouseout')
	                                .undelegate('td', 'mousemove')
	            }
	            if (data.tipDelayTime) {
	                clearTimeout(data.tipDelayTime);
	                data.tipDelayTime = null;
	            }
	        });
	    }
	});
	</script>
	<div data-options="region:'center',title:'订单管理'">
		<div style="background-color:#F8F8FF;align:center;width: 100%;height: 60px;overflow: hidden;">
					<form  style="margin-left: 1%;text-align: right;margin-top: 20px;">
						<table style="float: right;">
							<tr>
							<td>柜台</td>
							<td>
								<select id="counterid" class="searchstyle" name="counterid" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;">
									<option value="" selected="selected">全部</option>
									<c:if test="${counterlist!=null }">
										<c:forEach items="${counterlist }" var="counterlists">
											<option value="${counterlists.counter_code }">${counterlists.name }</option>
										</c:forEach>
									</c:if>
								</select>
							</td>
							<td>　国家</td>
							<td>
								<select id="d_countrys" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;" name="d_countrys">
									<option value="" selected="selected">全部</option>
									<c:if test="${country!=null }">
										<c:forEach items="${country }" var="countrys">
											<option value="${countrys.ITEM_VALUE }">${countrys.ITEM_TEXT }</option>
										</c:forEach>
									</c:if>
								</select>
							</td>
							<td>　设备类型</td>
							<td>
								<select id="equipment_type" class="searchstyle" name="equipment_type" style="border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width: 140px;">
									<option value="" selected="selected">全部</option>
									<option value="0001" >内部设备</option>
									<option value="0002">外部设备</option>
								</select>
							</td>
							<td>　日期:</td><td><input id="begindate" name="begindate" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'enddate\')}'})"/></td>
							<td>--</td><td><input id="enddate" name="enddate" value="" style="border: 1px solid #95b8e7;border-radius: 5px;" class="Wdate" type="text" onFocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'begindate\')}'})"/> </td>
							<td><a onclick="exportform();" href="exportform" class="easyui-linkbutton" style="margin-right: 5px;width: 80px;height: 25px;">导出报表</a></td>
							<td><a onclick="submits();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">查询</a></td>
							<!-- <td><a href="exportform" class="easyui-linkbutton" style="margin-right: 2%;width: 80px;height: 25px;">导出报表</a></td> -->
							</tr>
						</table>
					</form>
				</div>
		<!-- 表单开始 -->
				<table id="dg" style="overflow: auto;height: 90%;" 
				data-options="rownumbers:true,pagination:false,singleSelect:true,method:'get', remoteSort:false,multiSort:false, showFooter: true">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'order_num',width:200,align:'center'">订单号</th>
				<th data-options="field:'rent_begindate',width:200,align:'center',sortable:true">日期</th>
				<th data-options="field:'counterid',width:140,align:'center'">柜台</th>
				<th data-options="field:'d_country',width:80,align:'center'">国家</th>
				<th data-options="field:'rent_day',width:80,align:'center'">日租金</th>
				<th data-options="field:'actuallday',width:80,align:'center'">使用天数</th>
				<th data-options="field:'acutallchargeday',width:80,align:'center'">实际收费天数</th>
				<th data-options="field:'cost_discount',width:80,align:'center'">优惠金额</th>
				<th data-options="field:'cost_sum',width:80,align:'center'">实际收入</th>
				<th data-options="field:'equipment_no',width:80,align:'center'">设备类型</th>
				<th data-options="field:'payment',width:80,align:'center'">支付方式</th>
				<th data-options="field:'modify_user',width:80,align:'center'">制单人</th>
				<th data-options="field:'remark',width:200,align:'center'">备注</th>
				</tr>
				</thead>
				</table>
		<!-- 表单结束 -->
	</div>
	 <script type="text/javascript">
	 	$(function(){
	 		 $('#dg').datagrid({
	 	        onLoadSuccess: function (data) {
	 	            $('#dg').datagrid('doCellTip', {
	 	                onlyShowInterrupt: true,     //是否只有在文字被截断时才显示tip，默认值为false            
	 	                position: 'bottom',   //tip的位置，可以为top,botom,right,left
	 	                cls: { 'background-color': '#D1EEEE' },  //tip的样式
	 	                delay: 100   //tip 响应时间
	 	            });
	 	        }
	 	    });
	 	    $('.datagrid-header div').css('textAlign', 'center');   
	 	});
	 
		function submits(){
			var begindate = $('#begindate').val();
			var enddate = $('#enddate').val();
			var counterid = $("#counterid").val();
			var equipment_type = $("#equipment_type").val();
			var d_countrys = $("#d_countrys").val();
			 $('#dg').datagrid({
				url:"getOrderByTime?begintime="+begindate+"&endtime="+enddate+"&counterid="+counterid+"&d_countrys="+d_countrys+"&equipment_type="+equipment_type,
			 });  
			 var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
			 $(pager).pagination({  
				 	beforePageText: '第',//页数文本框前显示的汉字  
			        afterPageText: '页    共 {pages} 页',  
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
			    }); 
			 
			 $('#dg').datagrid({
		 	        onLoadSuccess: function (data) {
		 	            $('#dg').datagrid('doCellTip', {
		 	                onlyShowInterrupt: false,     //是否只有在文字被截断时才显示tip，默认值为false            
		 	                position: 'bottom',   //tip的位置，可以为top,botom,right,left
		 	                cls: { 'background-color': '#D1EEEE' },  //tip的样式
		 	                delay: 100   //tip 响应时间
		 	            });
		 	        }
		 	    });
		 	    $('.datagrid-header div').css('textAlign', 'center');   
			 
		}
		
		function exportform(){
			var begindate = $('#begindate').val();
			var enddate = $('#enddate').val();
			var counterid = $("#counterid").val();
			var equipment_type = $("#equipment_type").val();
			var d_countrys = $("#d_countrys").val();
			if(begindate=="" || enddate == ""){
				alert("请选择日期!");
				return false;
			}
			jQuery.ajax({
				url:'exportform',
				type:'post',
				async:false,
				data:{begindate:begindate,enddate:enddate,counterid:counterid,equipment_type:equipment_type,d_countrys:d_countrys},
				error:function(){},
				success:function(data){
					if(data==1){
						return true;
					}
				}
			}); 
		}
	</script>
	
		<script language="javascript" type="text/javascript">  
			   /**     
				 * 对Date的扩展，将 Date 转化为指定格式的String     
				 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符     
				 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)     
				 * eg:     
				 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423     
				 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04     
				 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04     
				 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04     
				 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18     
				 */       
				Date.prototype.pattern=function(fmt) {        
				    var o = {        
				    "M+" : this.getMonth()+1, //月份        
				    "d+" : this.getDate(), //日        
				    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时        
				    "H+" : this.getHours(), //小时        
				    "m+" : this.getMinutes(), //分        
				    "s+" : this.getSeconds(), //秒        
				    "q+" : Math.floor((this.getMonth()+3)/3), //季度        
				    "S" : this.getMilliseconds() //毫秒        
				    };        
				    var week = {        
				    "0" : "\日",        
				    "1" : "\一",        
				    "2" : "\二",        
				    "3" : "\三",        
				    "4" : "\四",        
				    "5" : "\五",        
				    "6" : "\六"       
				    };        
				    if(/(y+)/.test(fmt)){        
				        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));        
				    }        
				    if(/(E+)/.test(fmt)){        
				        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\星\期" : "\周") : "")+week[this.getDay()+""]);        
				    }        
				    for(var k in o){        
				        if(new RegExp("("+ k +")").test(fmt)){        
				            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));        
				        }        
				    }        
				    return fmt;        
				}      
</script>  
</body>
 
</html>