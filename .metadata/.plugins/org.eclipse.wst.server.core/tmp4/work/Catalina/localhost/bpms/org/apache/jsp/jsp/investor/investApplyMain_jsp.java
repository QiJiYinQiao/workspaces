/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-08-14 02:33:18 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.investor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class investApplyMain_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>投资申请</title>\r\n");
      out.write("</head>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var provinceArr = jqueryUtil.getAreaTextArr(1);//获取省\r\n");
      out.write("var relationshipArr =jqueryUtil.getTextArr(\"relationship_type\");//与本人关系\r\n");
      out.write("var sexArr = jqueryUtil.getTextArr(\"gender_type\");//性别\r\n");
      out.write("var degreeTypeArr = jqueryUtil.getTextArr(\"degree_type\");//学历\r\n");
      out.write("var jobTypeArr = jqueryUtil.getTextArr(\"job_type\")//工作类型\r\n");
      out.write("$(function(){\r\n");
      out.write("\tcreateAppdg();\r\n");
      out.write("});\r\n");
      out.write("//投资申请列表\r\n");
      out.write("function createAppdg(){\r\n");
      out.write("\t$(\"#dg\").datagrid({\r\n");
      out.write("\t\turl:'investOrder/investOrderAction!findListByInvestorAndInvestOrder.action',\r\n");
      out.write("\t\twidth: 'auto',\r\n");
      out.write("\t\theight: 830,\r\n");
      out.write("\t\tpagination:true,\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\tborder:false,\r\n");
      out.write("\t\tsingleSelect:true,\r\n");
      out.write("\t\tnowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。\r\n");
      out.write("\t\tpageSize:30,\r\n");
      out.write("\t\tpageList:[10,20,30,40],\r\n");
      out.write("\t\tremoteSort:false,//定义是否从服务器对数据进行排序。\r\n");
      out.write("\t\tstriped:true,//是否显示斑马线\r\n");
      out.write("\t\tcolumns:[[\r\n");
      out.write("\t\t        {field : 'chName',title : '客户姓名',width :80,align : 'center'},\r\n");
      out.write("                {field : 'idNo',title : '证件号码',width : 180,align : 'center'},\r\n");
      out.write("\t            {field : 'genderType',title : '性别',width : 80,align : 'center',formatter:function(value,row,index){\r\n");
      out.write("\t            \treturn jqueryUtil.showText(value,sexArr);\r\n");
      out.write("\t            }},\r\n");
      out.write("                {field : 'mobileTel',title : '移动电话',width :100,align : 'center'},\r\n");
      out.write("                {field : 'industry',title : '所属行业',width : 150,align : 'center'},\r\n");
      out.write("                {field : 'jobType',title : '职业',width :80,align : 'center',formatter:function(value,row,index){\r\n");
      out.write("                \treturn jqueryUtil.showText(value,jobTypeArr);\r\n");
      out.write("                }},\r\n");
      out.write("                {field : 'yearsOfWork',title : '工作年限',width : 60,align : 'center'},\r\n");
      out.write("                {field : 'degreeType',title : '学历',width : 60,align : 'center',formatter:function(value,row,index){\r\n");
      out.write("                \treturn jqueryUtil.showText(value,degreeTypeArr);\r\n");
      out.write("                }},\r\n");
      out.write("                {field : 'birthday',title : '出生日期',width : 150,align : 'center',formatter:function(value,row,index){\r\n");
      out.write("                \tif(value==null || value==\"\"){\r\n");
      out.write("                \t\treturn \"\";\r\n");
      out.write("                \t}\r\n");
      out.write("                    return value.split(\" \")[0]; \r\n");
      out.write("                }},\r\n");
      out.write("                {field : 'email',title : '邮箱',width : 150,align : 'center'},\r\n");
      out.write("                {field : 'contractNo',title : '合同编号',width :100,align : 'center'},\r\n");
      out.write("                {field : 'bankName',title : '开户行名称',width :100,align : 'center'},\r\n");
      out.write("                {field : 'actNo',title : '账号',width :100,align : 'center'},\r\n");
      out.write("                {field : 'actName',title : '账户名称',width :100,align : 'center'},\r\n");
      out.write("                {field : 'aa',title : '操作',width :180,align : 'center',formatter:function(value,row,index){\r\n");
      out.write("                \tif(row.processStatus == \"0\"){\r\n");
      out.write("             \t    \treturn \"<a href='javascript:void(0);' onclick='toDelete(\"+ index + \");'>删除</a>&nbsp;&nbsp;\"+\"<a href='javascript:void(0);' onclick='sumitInvestOrder(\"+ index + \");'>启动投资流程</a>\";\r\n");
      out.write("             \t    }else if(row.processStatus == \"1\"){\r\n");
      out.write("             \t    \treturn \"<a href='javascript:void(0);' onclick='checkProcessImg(\"+ index + \");'>查看流程图</a>\";\r\n");
      out.write("             \t    }else{\r\n");
      out.write("             \t    \treturn \"<a href='javascript:void(0);' onclick='checkInvestOrderOpinions(\"+ index + \");'>查看审批意见</a>\";\r\n");
      out.write("             \t    }\r\n");
      out.write("                }}\r\n");
      out.write("                \r\n");
      out.write("\t   ]],\r\n");
      out.write("\t   toolbar:[{\r\n");
      out.write("\t\t   iconCls: 'icon-add',\r\n");
      out.write("\t\t   text:'新增',\r\n");
      out.write("\t\t   handler:toAdd\r\n");
      out.write("\t   }]\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("/**\r\n");
      out.write(" * 新增\r\n");
      out.write(" */\r\n");
      out.write("function toAdd(){\r\n");
      out.write("\tcreateInvestordg();\r\n");
      out.write("\tcreateContacts();\r\n");
      out.write("\t$(\"#addDialog\").dialog(\"open\");\r\n");
      out.write("}\r\n");
      out.write("/**\r\n");
      out.write(" * 渲染investordg投资人列表\r\n");
      out.write(" */\r\n");
      out.write("function createInvestordg(){\r\n");
      out.write("\t$(\"#investordg\").datagrid({\r\n");
      out.write("\t\turl:'investor/investorAction!findAllInvestor.action',\r\n");
      out.write("\t\twidth: 885,\r\n");
      out.write("\t\theight: 534,\r\n");
      out.write("\t\tpagination:true,\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\tborder:false,\r\n");
      out.write("\t\tsingleSelect:true,\r\n");
      out.write("\t\tnowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。\r\n");
      out.write("\t\tpageSize:20,\r\n");
      out.write("\t\tpageList:[10,20,30,40],\r\n");
      out.write("\t\tremoteSort:false,//定义是否从服务器对数据进行排序。\r\n");
      out.write("\t\tstriped:true,//是否显示斑马线\r\n");
      out.write("\t\tonClickRow:function(rowIndex,rowData){//单击事件\r\n");
      out.write("\t\t\t$(\"#contactsdg\").datagrid(\"reload\",{loanerId:rowData.investorId});\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tcolumns:[[\r\n");
      out.write("\t\t        {field : 'chName',title : '客户姓名',width :80,align : 'center'},\r\n");
      out.write("                {field : 'idNo',title : '证件号码',width : 120,align : 'center'},\r\n");
      out.write("\t            {field : 'genderType',title : '性别',width : 80,align : 'center',formatter:function(value,row,index){\r\n");
      out.write("\t            \treturn jqueryUtil.showText(value,sexArr);\r\n");
      out.write("\t            }},\r\n");
      out.write("                {field : 'mobileTel',title : '移动电话',width :150,align : 'center'},\r\n");
      out.write("                {field : 'industry',title : '所属行业',width : 150,align : 'center'},\r\n");
      out.write("                {field : 'jobType',title : '职业',width :80,align : 'center',formatter:function(value,row,index){\r\n");
      out.write("                \treturn jqueryUtil.showText(value,jobTypeArr);\r\n");
      out.write("                }},\r\n");
      out.write("                {field : 'yearsOfWork',title : '工作年限',width : 60,align : 'center'},\r\n");
      out.write("                {field : 'degreeType',title : '学历',width : 60,align : 'center',formatter:function(value,row,index){\r\n");
      out.write("                \treturn jqueryUtil.showText(value,degreeTypeArr);\r\n");
      out.write("                }},\r\n");
      out.write("                {field : 'birthday',title : '出生日期',width : 150,align : 'center'},\r\n");
      out.write("                {field : 'email',title : '邮箱',width : 150,align : 'center'}\r\n");
      out.write("\t   ]],\r\n");
      out.write("\t   toolbar:[{\r\n");
      out.write("\t\t   iconCls: 'icon-add',\r\n");
      out.write("\t\t   text:'确定',\r\n");
      out.write("\t\t   handler:toSave\r\n");
      out.write("\t   }]\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//紧急联系人列表\r\n");
      out.write("function createContacts(){\r\n");
      out.write("\t$(\"#contactsdg\").datagrid({\r\n");
      out.write("\t\turl:'contacts/contactsAction!findAllList.action',\r\n");
      out.write("\t\twidth: 885,\r\n");
      out.write("\t\theight: 534,\r\n");
      out.write("\t\tpagination:true,\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\tborder:false,\r\n");
      out.write("\t\tsingleSelect:false,\r\n");
      out.write("\t\tnowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。\r\n");
      out.write("\t\tpageSize:20,\r\n");
      out.write("\t\tpageList:[10,20,30,40],\r\n");
      out.write("\t\tremoteSort:false,//定义是否从服务器对数据进行排序。\r\n");
      out.write("\t\tstriped:true,//是否显示斑马线\r\n");
      out.write("\t\tcolumns : [ [\r\n");
      out.write("\t\t             {field : 'chName',title : '姓名',width : 80,rowspan:2,align : 'center'},\r\n");
      out.write("\t\t             {field : 'relationship',title : '与本人关系',width : 80,rowspan:2,align : 'center',formatter:function(value,row,index){\r\n");
      out.write("\t\t            \t return jqueryUtil.showText(value,relationshipArr);\r\n");
      out.write("\t\t             }},\r\n");
      out.write("\t\t             {field : 'tel',title : '移动电话',width : 140,rowspan:2,align : 'center'},\r\n");
      out.write("\t\t             {field : 'idNo',title : '证件号码',width : 120,rowspan:2,align : 'center'},\r\n");
      out.write("\t\t             {title : '通讯地址',width : 340,colspan:4,align : 'center'}\r\n");
      out.write("\t\t],[\r\n");
      out.write("\t\t\t\t\t {field : 'compProvince',title : '省',width : 80,align : 'center',formatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t\t\t return jqueryUtil.showText(value,provinceArr);\r\n");
      out.write("\t\t\t\t\t }},\r\n");
      out.write("\t\t\t\t\t {field : 'compCity',title : '市',width : 80,align : 'center',formatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t\t\t var cityArr = jqueryUtil.getAreaTextArr(row.compProvince);\r\n");
      out.write("\t\t\t\t\t\t return jqueryUtil.showText(value,cityArr);\r\n");
      out.write("\t\t\t\t\t }},\r\n");
      out.write("\t\t\t\t\t {field : 'compArea',title : '县/区',width : 80,align : 'center',formatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t\t\t var areaArr = jqueryUtil.getAreaTextArr(row.compCity);\r\n");
      out.write("\t\t\t\t\t\t return jqueryUtil.showText(value,areaArr);\r\n");
      out.write("\t\t\t\t\t }},\r\n");
      out.write("\t\t\t\t\t {field : 'compAddrDetails',title : '详细地址',width : 200,align : 'center'}\r\n");
      out.write("\t\t]]\r\n");
      out.write("\t})\r\n");
      out.write("}\r\n");
      out.write("//确定\r\n");
      out.write("function toSave(){\r\n");
      out.write("\tvar row = $(\"#investordg\").datagrid(\"getSelected\");\r\n");
      out.write("\tif(row == null){\r\n");
      out.write("\t\t$.messager.alert(\"提示\",\"请您选择一行记录!\",\"warning\");\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\t//发送ajax\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\ttype:'POST',\r\n");
      out.write("\t\turl:'investOrder/investOrderAction!saveInvestOrder.action',\r\n");
      out.write("\t\tdata:'investorId='+row.investorId+'&idCrad='+row.idNo+'&mobTel='+row.mobileTel+\"&investorName=\"+row.chName,\r\n");
      out.write("\t\tdataType:'JSON',\r\n");
      out.write("\t\tsuccess:function(iJson){\r\n");
      out.write("\t\t\tif(iJson.status){\r\n");
      out.write("\t\t\t\t$(\"#dg\").datagrid(\"reload\");//刷新列表\r\n");
      out.write("\t\t\t\t$(\"#addDialog\").dialog(\"close\");//关闭弹窗\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\ttitle : iJson.title,\r\n");
      out.write("\t\t\t\tmsg : iJson.message,\r\n");
      out.write("\t\t\t\ttimeout : 4000 * 2\r\n");
      out.write("\t\t\t}); \r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//删除\r\n");
      out.write("function toDelete(index){\r\n");
      out.write("\tvar row = getRowData(index);\r\n");
      out.write("\tif(row == null){\r\n");
      out.write("\t\t$.messager.alert(\"提示\",\"请选择一条记录执行删除!\",\"warning\");\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\t//发送ajax\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\ttype:'POST',\r\n");
      out.write("\t\turl:'investOrder/investOrderAction!doDeleteInvestOrder.action',\r\n");
      out.write("\t\tdata:'investOrderId='+row.investOrderId,\r\n");
      out.write("\t\tdataType:'JSON',\r\n");
      out.write("\t\tsuccess:function(iJson){\r\n");
      out.write("\t\t\tif(iJson.status){\r\n");
      out.write("\t\t\t\t$(\"#dg\").datagrid(\"deleteRow\",index);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\ttitle : iJson.title,\r\n");
      out.write("\t\t\t\tmsg : iJson.message,\r\n");
      out.write("\t\t\t\ttimeout : 4000 * 2\r\n");
      out.write("\t\t\t}); \r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//工具\r\n");
      out.write("function getRowData(index) {\r\n");
      out.write("\tif (!$.isNumeric(index) || index < 0) {\r\n");
      out.write("\t\treturn undefined;\r\n");
      out.write("\t}\r\n");
      out.write("\tvar rows = $(\"#dg\").datagrid(\"getRows\");\r\n");
      out.write("\treturn rows[index];\r\n");
      out.write("}\r\n");
      out.write("//提交申请\r\n");
      out.write("function sumitInvestOrder(index){\r\n");
      out.write("\tvar row = this.getRowData(index);\r\n");
      out.write("\t$.messager.confirm('确定','是否确定提交所选的数据吗？',\tfunction(flag) {\r\n");
      out.write("\t\tif (flag) {\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\turl : \"investOrder/investOrderAction!saveStartProcessInstance.action\",\r\n");
      out.write("\t\t\t\t\tdata : {\"investOrderId\" : row.investOrderId},\r\n");
      out.write("\t\t\t\t\tsuccess : function(rsp) {\r\n");
      out.write("\t\t\t\t\t\tif(rsp.status){\r\n");
      out.write("\t\t\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\t\t\ttitle : rsp.title,\r\n");
      out.write("\t\t\t\t\t\t\t\tmsg : rsp.message,\r\n");
      out.write("\t\t\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#dg\").datagrid('reload');\r\n");
      out.write("\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\tparent.$.messager.alert(rsp.title,rsp.message,'error');\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("}\r\n");
      out.write("//查看流程图\r\n");
      out.write("function checkProcessImg(index){\r\n");
      out.write("\tvar row = this.getRowData(index);\r\n");
      out.write("\tvar src = \"investOrder/investOrderAction!checkWorkFlowImg.action?investOrderId=\" + row.investOrderId;\r\n");
      out.write("\t$('#imageDialog').dialog(\"open\");\r\n");
      out.write("\t$(\"#image\").attr(\"src\", src);\r\n");
      out.write("}\r\n");
      out.write("var row;\r\n");
      out.write("//查看审批意见\r\n");
      out.write("function checkInvestOrderOpinions(index){\r\n");
      out.write("\tvar rows = $(\"#dg\").datagrid(\"getRows\");\r\n");
      out.write("\trow = rows[index];//获取本条数据\r\n");
      out.write("\t$('#OpinionsDialog').dialog({    \r\n");
      out.write("\t    title: '历史审查意见',    \r\n");
      out.write("\t    width: 800,    \r\n");
      out.write("\t    height: 500,    \r\n");
      out.write("\t    closed: false,    \r\n");
      out.write("\t    cache: false,    \r\n");
      out.write("\t    href: 'jsp/investOrder/optionsList.jsp',    \r\n");
      out.write("\t    modal: true   \r\n");
      out.write("\t});   \r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("   <table id=\"dg\" style=\"margin: 0px;padding: 0px;overflow: auto;\"></table>\r\n");
      out.write("   <!-- 新增div -->\r\n");
      out.write("   <div id=\"addDialog\" class=\"easyui-dialog\" style=\"width:900px;height:600px;\" title=\"新增\" data-options=\"modal:true,resizable:true,iconCls:'icon-add',closed: true\">\r\n");
      out.write("      <div id=\"tt\" class=\"easyui-tabs\" data-options=\"border:false\">   \r\n");
      out.write("\t    <div title=\"投资客户\">   \r\n");
      out.write("\t       <table id=\"investordg\" style=\"margin: 0px;padding: 0px;\"></table>\r\n");
      out.write("\t    </div>   \r\n");
      out.write("\t    <div title=\"紧急联系人\">   \r\n");
      out.write("\t       <table id=\"contactsdg\" style=\"margin: 0px;padding: 0px;\"></table>    \r\n");
      out.write("\t    </div>   \r\n");
      out.write("\t </div> \r\n");
      out.write("   </div>\r\n");
      out.write("   <!-- 流程图片弹框 -->\r\n");
      out.write("   <div id=\"imageDialog\" class=\"easyui-dialog\" title=\"流程图片\"\r\n");
      out.write("\t\t\tdata-options=\"border:false,closed:true,fit:true\">\r\n");
      out.write("\t\t\t<img id=\"image\" src=\"\">\r\n");
      out.write("   </div>\r\n");
      out.write("   <!-- 审查意见 -->\r\n");
      out.write("   <div id=\"OpinionsDialog\"></div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}