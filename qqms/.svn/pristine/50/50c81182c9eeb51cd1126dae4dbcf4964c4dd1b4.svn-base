var sexArr = jqueryUtil.getTextArr("gender_type");//性别
var marriageArr = jqueryUtil.getTextArr("marriage_type");//婚姻状况
var idTpyeArr = jqueryUtil.getTextArr("id_type");//证件类型
var degreeArr = jqueryUtil.getTextArr("degree_type");//学历
var jobTypeArr = jqueryUtil.getTextArr("job_type");//职业类型
var companyArr = jqueryUtil.getTextArr("company_size");//单位规模
var hasChildArr = jqueryUtil.getTextArr("has_child");//家庭情况
var provinceArr = jqueryUtil.getAreaTextArr(1);//获取省
$(function(){
	var $dg = $("#dg");//列表
	$grid=$dg.datagrid({
		url : "investor/investorAction!findAllList.action",
		width : 'auto',
		height : $(this).height()-84,
		pageSize:20,
		pagination:true,
		rownumbers:true,
		border:false,
		singleSelect:false,
		striped:true,
		onDblClickRow:function(index,row){
			showDetails(row);
		},
		columns : [ [ {field : 'name',title : '客户姓名',width : 100,align : 'center'},
		              {field : 'invstrType',title : '客户类型',width : 80,align : 'center',formatter:function(value,row,index){
		            	  return "个人";
		              }},
		              {field : 'jobType',title : '职业',width : 100,align : 'center',formatter:function(value,row,index){
		            	  return jqueryUtil.showText(value,jobTypeArr);
		              }},
		              {field : 'companyName',title : '单位名称',width : 120,align : 'center'},
		              {field : 'idType',title : '证件类型',width :80,align : 'center',formatter:function(value,row,index){
		            	  return jqueryUtil.showText(value,idTpyeArr);
		              }},
		              {field : 'idNo',title : '证件号码',width :180,align : 'center'},
		              {field : 'degreeType',title : '学历',width :80,align : 'center',formatter:function(value,row,index){
		            	  return jqueryUtil.showText(value,degreeArr);
		              }},
		              {field : 'yearsOfWork',title : '工作年限(年)',width : 80,align : 'center'},
		              {field : 'mobileTel',title : '手机号码',width :100,align : 'center'},
		              {field : 'email',title : '电子邮箱',width : 150,align : 'center'},
		              {field : 'description',title : '描述',width : 480,align : 'left'},
		              {field : 'text',title : '紧急联系人',width : 120,align : 'center',formatter:function(value,row,index){
		            	  return "<a href=\"javascript:void(0);\" onclick=\"showLinkman("+row.invstrId+");\">查看紧急联系人</a>";
		              }}
		              ] ],toolbar:'#tb'
	});
	getBoxJobType();//工作类型下拉框
});
//渲染搜索中工作类型的下拉框
function getBoxJobType(){
	var data = JSON.stringify(jobTypeArr);
	$('#jobType').combobox({    
	    data:eval(data),    
	    valueField:'code',    
	    textField:'text'   
	});  
}
//搜索
function doSearch(){
	var name = $("#name").val();//客户姓名
	var jobType = $("#jobType").combobox('getValue');//工作类型
	var idNo = $("#idNo").val();//证件号码
	var mobileTel = $("#mobileTel").val();//手机号码
	var email = $("#email").val();//电子邮箱
	var description = $("#description").val();//描述
	$("#dg").datagrid('reload',{name:name,jobType:jobType,idNo:idNo,mobileTel:mobileTel,email:email,description:description}); 
}
//详情
function showDetails(row){
	var $dl = $("#dl");//详情
	var htmlstr= "<table width='100%' height='100%' style='border-collapse:collapse; font-size: 12px;background-color:#F4f4f4;' cellpadding='5' border='0' align='center'>";
	htmlstr += "<tr>"; 
	htmlstr += "<th width='16%' class='textStyle'>姓名:</th>";
	htmlstr += "<td width='26%'>"+(row.name==null?" ":row.name)+"</td>";
	htmlstr += "<th width='16%' class='textStyle'>性别:</th>";
	htmlstr += "<td width='26%'>"+jqueryUtil.showText(row.genderType,sexArr)+"</td>";
	htmlstr += "</tr>";
	
	htmlstr += "<tr>"; 
	htmlstr += "<th width='16%' class='textStyle'>国籍:</th>";
	htmlstr += "<td width='26%'>"+(row.nationality==null?" ":row.nationality)+"</td>";
	htmlstr += "<th width='16%' class='textStyle'>出生日期:</th>";   
	htmlstr += "<td width='26%'>"+(row.birthday==null?" ":row.birthday)+"</td>";  
	htmlstr += "</tr>";
	
	htmlstr += "<tr>"; 
	htmlstr += "<th width='16%' class='textStyle'>婚姻状况:</th>";   
	htmlstr += "<td width='26%'>"+jqueryUtil.showText(row.marriageType,marriageArr)+"</td>"; 
	htmlstr += "<th width='16%' class='textStyle'>证件类型:</th>";   
	htmlstr += "<td width='26%'>"+jqueryUtil.showText(row.idType,idTpyeArr)+"</td>";  
	htmlstr += "</tr>";
	
	htmlstr += "<tr>"; 
	htmlstr += "<th width='16%' class='textStyle'>证件号码:</th>";
	htmlstr += "<td width='26%'>"+(row.idNo==null?" ":row.idNo)+"</td>";
	htmlstr += "<th width='16%' class='textStyle'>签发日期:</th>";   
	htmlstr += "<td width='26%'>"+(row.idIssueDate==null?" ":row.idIssueDate)+"</td>"; 
	htmlstr += "</tr>";
	
	htmlstr += "<tr>"; 
	htmlstr += "<th width='16%' class='textStyle'>失效日期:</th>";
	htmlstr += "<td width='26%'>"+(row.idExpireDate==null?" ":row.idExpireDate)+"</td>";
	htmlstr += "<th width='16%' class='textStyle'>发证机构所在地:</th>";   
	htmlstr += "<td width='26%'>"+(row.idLocation==null?" ":row.idLocation)+"</td>";  
	htmlstr += "</tr>";
	
	htmlstr += "<tr>"; 
	htmlstr += "<th width='16%' class='textStyle'>学历:</th>";
	htmlstr += "<td width='26%'>"+jqueryUtil.showText(row.degreeType,degreeArr)+"</td>";
	htmlstr += "<th width='16%' class='textStyle'>职业:</th>";   
	htmlstr += "<td width='26%'>"+jqueryUtil.showText(row.jobType,jobTypeArr)+"</td>";  
	htmlstr += "</tr>";
	
	htmlstr += "<tr>"; 
	htmlstr += "<th width='16%' class='textStyle'>行业:</th>";
	htmlstr += "<td width='26%'>"+(row.industryId==null?" ":row.industryId)+"</td>";
	htmlstr += "<th width='16%' class='textStyle'>单位名称:</th>";
	htmlstr += "<td width='26%'>"+(row.companyName==null?" ":row.companyName)+"</td>";  
	htmlstr += "</tr>";
	
	htmlstr += "<tr>"; 
	htmlstr += "<th width='16%' class='textStyle'>工作年限(年):</th>";   
	htmlstr += "<td width='26%'>"+(row.yearsOfWork==null?" ":row.yearsOfWork)+"</td>"; 
	htmlstr += "<th width='16%' class='textStyle'>单位规模:</th>";
	htmlstr += "<td width='26%'>"+jqueryUtil.showText(row.companyScaleType,companyArr)+"</td>";
	htmlstr += "</tr>";
	
	htmlstr += "<tr>"; 
	htmlstr += "<th width='16%' class='textStyle'>移动电话:</th>";   
	htmlstr += "<td width='26%'>"+(row.mobileTel==null?" ":row.mobileTel)+"</td>"; 
	htmlstr += "<th width='16%' class='textStyle'>固定电话:</th>";
	htmlstr += "<td width='26%'>"+(row.fixedTel==null?" ":row.fixedTel)+"</td>";
	htmlstr += "</tr>";
	
	htmlstr += "<tr>"; 
	htmlstr += "<th width='16%' class='textStyle'>电子邮箱:</th>";   
	htmlstr += "<td width='26%'>"+(row.email==null?" ":row.email)+"</td>";  
	htmlstr += "<th width='16%' class='textStyle'>省:</th>";
	htmlstr += "<td width='26%'>"+jqueryUtil.showText(row.province,provinceArr)+"</td>";
	htmlstr += "</tr>";
	var cityArr = jqueryUtil.getAreaTextArr(row.province);//获取市
	var xianArr = jqueryUtil.getAreaTextArr(row.city);//获取县
	htmlstr += "<tr>"; 
	htmlstr += "<th width='16%' class='textStyle'>市:</th>";   
	htmlstr += "<td width='26%'>"+jqueryUtil.showText(row.city,cityArr)+"</td>"; 
	htmlstr += "<th width='16%' class='textStyle'>区:</th>";
	htmlstr += "<td width='26%'>"+jqueryUtil.showText(row.area,xianArr)+"</td>";
	htmlstr += "</tr>";
	
	htmlstr += "<tr>"; 
	htmlstr += "<th width='16%' class='textStyle'>街:</th>";   
	htmlstr += "<td width='26%'>"+(row.street==null?" ":row.street)+"</td>";  
	htmlstr += "<th width='16%' class='textStyle'>邮编:</th>";
	htmlstr += "<td width='26%'>"+(row.zip==null?" ":row.zip)+"</td>";
	htmlstr += "</tr>";
	
	htmlstr += "<tr>"; 
	htmlstr += "<th width='16%' class='textStyle'>家庭情况:</th>";   
	htmlstr += "<td width='26%' colspan='3'>"+jqueryUtil.showText(row.hasChild,hasChildArr)+"</td>"; 
	htmlstr += "</tr>";
	
	htmlstr += "<tr>"; 
	htmlstr += "<td colspan='4' align='center'><a id=\"btn\" href=\"javascript:void(0);\" onclick=\"javascript:doClose();\">关闭</a></td>"; 
	htmlstr += "</tr>";
	htmlstr += "</table>";
	$dl.html(htmlstr);
	//渲染关闭按钮
	$("#btn").linkbutton({    
	    iconCls: 'icon-cancel',
	    plain:false
	});  
	//弹出详情弹框
	$dl.dialog({    
	    title: '详情',    
	    width: 600,    
	    height: 400,  
	    resizable:true,
	    closed: false,    
	    modal: true   
	});  
}
//关闭详情弹框
function doClose(){
	$("#dl").dialog('close');
}
//打开紧急联系人列表
function showLinkman(invstrId){
	var $dllm = $("#dlLinkman");//紧急联系人弹框
	var $lmdg = $("#linkmanDatagrid");//紧急联系人列表
	$lmdg.datagrid({
		url : 'emccontact/emccontactAction!findAllList.action?contactId='+invstrId,
		width:'98%',
		height :$(this).height()-367,
		pagination:false,
		rownumbers:true,
		border:false,
		singleSelect:false,
		striped:true,
		/*frozenColumns:[[
					  {field : 'name',title : '姓名',width : 80,align : 'center'},
					  {field : 'idType',title : '证件类型',width : 80,align : 'center',formatter:function(value,row,index){
		            	  return jqueryUtil.showText(value,idTpyeArr);
		              }},
					  {field : 'idNo',title : '证件号码',width : 150,align : 'center'}
		]],*/
		columns : [ [ {field : 'name',title : '姓名',width : 80,align : 'center'},
					  {field : 'idType',title : '证件类型',width : 80,align : 'center',formatter:function(value,row,index){
		            	  return jqueryUtil.showText(value,idTpyeArr);
		              }},
					  {field : 'idNo',title : '证件号码',width : 150,align : 'center'},
		              {field : 'tel',title : '移动电话',width : 100,align : 'center'},
		              {field : 'fixedTel',title : '固定电话',width : 100,align : 'center'},
		              {field : 'email',title : '电子邮箱',width : 150,align : 'center'},
		              {field : 'genderType',title : '性别',width : 50,align : 'center'},
		              {field : 'birthday',title : '出生年月',width : 100,align : 'center',formatter:function(value,row,index){
		            	  if(null!=value){
		            		  var date = new Date(value);
		            		  return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
		            	  }
		            	  return " ";
		              }},
		              {field : 'province',title : '省',width : 50,align : 'center',formatter:function(value,row,index){
		            	 return jqueryUtil.showText(value,provinceArr);
		              }},
		              {field : 'city',title : '市',width : 50,align : 'center',formatter:function(value,row,index){
		            	  var cityArr = jqueryUtil.getAreaTextArr(row.province);//获取市
		            	  return jqueryUtil.showText(value,cityArr);
		              }},
		              {field : 'area',title : '区',width : 50,align : 'center',formatter:function(value,row,index){
		            	  var xianArr = jqueryUtil.getAreaTextArr(row.city);//获取县
		            	  return jqueryUtil.showText(value,xianArr);
		              }},
		              {field : 'street',title : '街',width : 150,align : 'center'},
		              {field : 'zip',title : '邮编',width : 60,align : 'center'},
		              {field : 'relationship',title : '与您的关系',width : 250,align : 'left'}
		] ],
		onLoadSuccess:function(data){
            if(data.total==0){
                var dc = $(this).data('datagrid').dc;
                var header2Row = dc.header2.find('tr.datagrid-header-row');
                dc.body2.find('table').append(header2Row.clone().css({"visibility":"hidden"}));
            }
        }
	});
	//把DIV渲染成弹框
	$dllm.dialog({
		title:'紧急联系人信息',
		width:800,
		height: 500,
		resizable:true,//是否可以改变弹框大小
	    closed: false,    
	    modal: true 
	});
}