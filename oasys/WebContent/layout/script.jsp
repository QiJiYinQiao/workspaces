	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<%
		String easyuiThemeName="metro";
		Cookie cookies[] =request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie : cookies){
				if (cookie.getName().equals("cookiesColor")) {
					easyuiThemeName = cookie.getValue();
					break;
				}
			}
		}
	%>
	<link rel="stylesheet" type="text/css" href="themes/<%=easyuiThemeName %>/easyui.css">
	<script type="text/javascript" src="js/xheditor/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/xheditor/xheditor-1.1.14-zh-cn.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/jqueryUtil.js"></script>
	<script type="text/javascript" src="js/json2.js"></script>
	<script type="text/javascript" src="js/FusionCharts/FusionCharts.js"></script>
	<script type="text/javascript" src="js/FusionCharts/FusionCharts.jqueryplugin.js"></script>
	<script type="text/javascript" src="js/FusionCharts/FusionChartsExportComponent.js"></script>
	<link rel="stylesheet" type="text/css" href="js/uploadify/uploadify.css"></link>  
	<script type="text/javascript" src="js/uploadify/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/validate.js"></script>
	<script type="text/javascript">
	
	
	/***
	* 批量受理任务页面 合计dataGrid方法 并支持计算合计 
	* @param dg dataGrid对象
	* @param json对象 
	* @实例用法如下：{rowStr:'ppeApp.orgName,ppeApp.appNo',//按照哪列进行分组
				rowGroup:'orgName,heji:ck,appNo,caozuo,sqzt,userName,appDate',//对应rowStr的需要合并单元格的字段
					hejiColumn:'ppeGross',//需要计算的合计值的所在列
					hejiResult:'heji'};//计算后将合计值更新到该列中
	*/
	function dataGirdSumMergeFunc(dg,paramObj){
   	  	   var rows = dg.datagrid("getRows");//获取dataGrid的行
           var mergeMap = {};//按照部门分组Map
           var mergeMapAppNo = {};//按照申请编号分组Map
           var hejiCount = 0;//要进行合并计算的列计数器
           var rowStrArray = paramObj.rowStr.split(",");
           var rowGroupArray = paramObj.rowGroup.split(":");
           var hejiColumn = paramObj.hejiColumn;
           var hejiResult = paramObj.hejiResult;
           var regS = new RegExp("\\,","g");
           if(rows){
             	//构建首次分组Map;
               	for(var i=0;i<rows.length;i++){
               		var orgName = eval("rows[i]."+rowStrArray[0]);
               		if(orgName in mergeMap ){
               			if(null != hejiColumn){
	               			hejiCount +=Number(eval("rows[i]."+hejiColumn).replace(regS,""));
               			}
               			mergeMap[orgName].rowspan++;
               		}else{
               			if(null != hejiColumn){
	               			hejiCount =Number(String(eval("rows[i]."+hejiColumn)).replace(regS,""));
               			}
               			mergeMap[orgName]={"index":i,"rowspan":1}
               		}
               		if(null != hejiColumn){
               			mergeMap[orgName].heji=hejiCount;
               		}
               	}
             	//计算列小计 只有计算列不为空时才计算
             	if(null != hejiColumn){
	         		try {
	         			for(var i in mergeMap){
	         					var iCount = 0;
	         					while(iCount <= mergeMap[i].rowspan){
	         						dg.datagrid('updateRow', { index: mergeMap[i].index+iCount,row: {heji:mergeMap[i].heji}});
	         		 				iCount++;
	         					}
	         			}
	         		} catch (e) {
	         			
	         		}
             	}
         		//进行合并单元格
               	mergeFunc(dg,mergeMap,rowGroupArray[0]);
         		if(rowStrArray.length > 1 && null != rowStrArray[1]){
	         		//构建申请编号Map 
	              	for(var i=0;i<rows.length;i++){
	              		var appNo = eval("rows[i]."+rowStrArray[1]);
	              		if(appNo in mergeMapAppNo ){
	              			mergeMapAppNo[appNo].rowspan++;
	              		}else{
	              			mergeMapAppNo[appNo]={"index":i,"rowspan":1}
	              		}
	              	}
	         		//进行合并单元格
	              	mergeFunc(dg,mergeMapAppNo,rowGroupArray[1]);
         		}
           }
	}
	
	function getMergeMap(rows,columnName,hejiCoulmn){
     	//构建首次分组Map;
     	var hejiCount = 0;
     	var mergeMap = {};
       	for(var i=0;i<rows.length;i++){
       		var orgName = eval("rows[i]."+columnName);
       		if(orgName in mergeMap ){
       			hejiCount += eval("rows[i]."+hejiColumn);
       			mergeMap[orgName].rowspan++;
       		}else{
       			hejiCount = eval("rows[i]."+hejiColumn);
       			mergeMap[orgName]={"index":i,"rowspan":1}
       		}
       		if(null != hejiColumn){
       			mergeMap[orgName].heji=hejiCount;
       		}
       	}
       	return mergeMap;
	}
	
	/***
	 * 表格合并单元格方法
	 * @param dataGrid 表格组件
	 * @param mergeMap 字段分组依据构造的map
	 * @param filedName 需要合并的列ID，支持多个 按照,分割
	 */
	function mergeFunc(dataGrid,mergeMap,filedName){
 		var strArr = filedName.split(",");
 		for(var j in strArr){
 			for(var i in mergeMap){
 				dataGrid.datagrid('mergeCells',{
                    index: mergeMap[i].index,
                    field: strArr[j],
                    rowspan: mergeMap[i].rowspan
                });	
 			}
 		}
 	}

	
	function ajaxLoading(str){ 
	    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body"); 
	    $("<div class=\"datagrid-mask-msg\"></div>").html(str).appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,
	    	top:($(window).height() - 45) / 2}); 
	 } 
	
	 function ajaxLoadEnd(){ 
	     $(".datagrid-mask").remove(); 
	     $(".datagrid-mask-msg").remove();             
	} 
	
	</script>
	<style type="text/css">
		body {
		    font-family:helvetica,tahoma,verdana,sans-serif;
		    font-size:13px;
		    margin:0px 0px 0px 0px;
		    padding:0px 0px 0px 0px;
		}
	</style>