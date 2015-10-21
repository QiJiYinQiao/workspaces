<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>价目表</title>
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
		 .container{
                position:relative;
        }
        table{border-collapse:collapse;border-spacing:0;text-align: center;}
        td{padding:5px;margin:5px;} 
		table.gridtable th {
			font-size:18px;
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #666666;
			background-color: #dedede;
		}
		table.gridtable td {
			font-size:14px;
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #666666;
		}
	</style>
	<style>
		.lightbox{width:300px;background:#FFFFFF;border:1px solid #ccc;line-height:25px; top:20%; left:20%;}
		.lightbox dt{background:#f4f4f4; padding:5px;}
	</style>
</head>
<body class="easyui-layout" >
	<div data-options="region:'center',title:'价目表'">
	<div class="indiv" style="height:100%;width:100%;background-color: #F8F8FF;overflow: auto;" >
				<table class="gridtable" cellpadding="12" align="center" width="100%">
					<tr><th>国家</th><th>官方价格</th><th>JCB价格</th><th>400价格</th><th>淘宝价格</th></tr>
					<tr><td>香港</td><td>40</td><td>15</td><td>18</td><td>12</td></tr>
					<tr><td>香港澳门通用</td><td>40</td><td>18</td><td>20</td><td>15</td></tr>
					<tr><td>台湾</td><td>40</td><td>15</td><td>18</td><td>18</td></tr>
					<tr><td>泰国</td><td>40</td><td>15</td><td>18</td><td>15</td></tr>
					<tr><td>越南</td><td>40</td><td>20</td><td>25</td><td>25</td></tr>
					<tr><td>韩国</td><td>40</td><td>15</td><td>18</td><td>15</td></tr>
					<tr><td>新加坡</td><td>40</td><td>28</td><td>33</td><td>33</td></tr>
					<tr><td>日本</td><td>40</td><td>15</td><td>18</td><td>22</td></tr>
					<tr><td>德国</td><td>50</td><td>42</td><td>45</td><td>45</td></tr>
					<tr><td>意大利</td><td>50</td><td>42</td><td>45</td><td>45</td></tr>
					<tr><td>法国</td><td>50</td><td>42</td><td>45</td><td>45</td></tr>
					<tr><td>俄罗斯</td><td>50</td><td>42</td><td>45</td><td>45</td></tr>
					<tr><td>欧洲通用</td><td>99</td><td>65</td><td>70</td><td>60</td></tr>
					<tr><td>美国</td><td>70</td><td>62</td><td>65</td><td>65</td></tr>
					<tr><td>关岛</td><td>50</td><td>45</td><td>47</td><td>47</td></tr>
					<tr><td>澳大利亚新西兰通用</td><td>60</td><td>55</td><td>57</td><td>57</td></tr>
					<tr><td>塞班</td><td>65</td><td>55</td><td>55</td><td>55</td></tr>
					<tr><td>柬埔寨</td><td>40</td><td>32</td><td>35</td><td>35</td></tr>
					<tr><td>印度尼西亚</td><td>40</td><td>26</td><td>28</td><td>28</td></tr>
					<tr><td>马尔代夫</td><td>40</td><td>32</td><td>34</td><td>34</th></tr>
					
				</table>	
			</div>
	</div>
			<script type="text/javascript">
				function  hide(){
					alert(document.getElementById("screens"));
					alert(top.document.getElementById("screens"));
					top.document.getElementById("screens").fadeOut("1500");
				}
				
				//====================计算两个日期间的时间差（天数）开始===============================
				$(function(){
					var realreturn = $("#real_return").val();
					var rentdate = $("#rent_date").val();
					var d3 = $("rent_expectdate").val();
					
					var date1=null;
					var date2=new Date();
					function Swith(strdate){
					var strYear=strdate.substring(0,4);
					var strMonth=strdate.substring(5,7); 
					var strDay=strdate.substring(8,10);
					/* var strHours=strdate.substring(11,13);
					var strMinutes=strdate.substring(14,16); ,strHours,strMinutes*/
					return new Date(strYear,strMonth,strDay); 
					}
					
					function GetTime(dateM,datetype){
					     var s;                            // 声明变量。
					     var MinMilli = 1000 * 60;         // 初始化变量。
					     var HrMilli = MinMilli * 60;
					     var DyMilli = HrMilli * 24;
					     //s="";
					     if (datetype=="d"){
					     s=Math.round(Math.abs(dateM/DyMilli));
					     }else if (datetype=="h"){
					    s +=Math.round(Math.abs(dateM/HrMilli))+"小时"
					     }else if (datetype=="m"){
					    s +=Math.round(Math.abs(dateM/MinMilli))+"分";
					     } else{
					    s +=Math.round(Math.abs(dateM/1000))+"秒"
					     }
					     return(s);                        // 返回结果。
					}
					if(realreturn!=""){
						var daycounts = GetTime((Date.parse(Swith(realreturn))-Date.parse(Swith(rentdate))),"d")+1;
						$("#daycounts").val(daycounts);
					}
				});	
			</script>
</body>
</html>