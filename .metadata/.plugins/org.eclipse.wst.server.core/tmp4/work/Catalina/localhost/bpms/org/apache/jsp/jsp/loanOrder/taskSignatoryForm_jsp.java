/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-09-21 05:32:08 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.loanOrder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class taskSignatoryForm_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("\t#acceptTaskForm table input{border: none;}\r\n");
      out.write("\t.table th{ text-align: right;}\r\n");
      out.write("\t.table td{ text-align: left;}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var $row;\r\n");
      out.write("var $datagrid;\r\n");
      out.write("$(function(){\r\n");
      out.write("\t// 查看申请状态\r\n");
      out.write("\t$row = $grid.datagrid('getSelected');\r\n");
      out.write("\t$datagrid = $(\"#lookLoanOrderdg\").datagrid({\r\n");
      out.write("\t\turl : \"loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action\",\r\n");
      out.write("\t\twidth : 'auto',\r\n");
      out.write("\t\theight : 350,\r\n");
      out.write("\t\tpagination:false,\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\tborder:true,\r\n");
      out.write("\t\tsingleSelect:true,\r\n");
      out.write("\t\tnowrap:true,\r\n");
      out.write("\t\tqueryParams:{\"loanOrderId\":$row.loanOrderId},\r\n");
      out.write("\t\tmultiSort:false,\r\n");
      out.write("\t\tfitColumns:true,\r\n");
      out.write("\t\tcolumns : [ [ \r\n");
      out.write("\t\t              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),sortable:true},\r\n");
      out.write("\t\t              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1)},\r\n");
      out.write("\t\t              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t              {field : 'comment',title : '审批意见',width :parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.09),align : 'left',\r\n");
      out.write("\t\t\t            \tformatter:function(value,row,index){\r\n");
      out.write("\t\t\t            \t\treturn \"<a href='javascript:void(0);' onclick='lookAttachment(\"+index+\");'>查看附件</a>　　\" ;\r\n");
      out.write("\t\t\t            \t}  \r\n");
      out.write("\t\t              }\r\n");
      out.write("\t\t              ] ]\r\n");
      out.write("\t});\r\n");
      out.write("\t// 渲染姓名\r\n");
      out.write("\t$(\"#acceptTaskForm input[name='name']\").val($row.name);\r\n");
      out.write("\t// 渲染身份证号\r\n");
      out.write("\t$(\"#acceptTaskForm input[name='idNo']\").val($row.idNo);\r\n");
      out.write("\r\n");
      out.write("\t$(\"#attType\").combobox({\r\n");
      out.write("\t\tvalueField : 'code',\r\n");
      out.write("\t\ttextField : 'text',\r\n");
      out.write("\t\turl:'common/commonAction!findTextArr.action?codeMyid=attachment_type',\r\n");
      out.write("\t\tonLoadSuccess : function(){\r\n");
      out.write("\t\t\tvar val = $(this).combobox(\"getData\");\r\n");
      out.write("\t\t\tif(!$.isEmptyObject(val)){\r\n");
      out.write("                $(this).combobox(\"select\", val[0][\"code\"]);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\teditable:false\r\n");
      out.write("    });\r\n");
      out.write("\t\r\n");
      out.write("\t//查看附件\r\n");
      out.write("\t$(\"#checkAttachment\").click(function(){\r\n");
      out.write("\t\tcheckAttachementDetail('noauditId',$row.loanOrderId,'');\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t//上传附件\r\n");
      out.write("\t$(\"#upploadAttachment\").click(function(){\r\n");
      out.write("\t\tvar attType = $(\"#attType\").combobox(\"getValue\");\r\n");
      out.write("\t\tfileUploadsDlg(attType,$row.loanOrderId);\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t//查询此订单是否存在合同信息，如果有，保存合同ID\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\turl:'loanContract/loanContractAction!checkIsContractExist.action',\r\n");
      out.write("\t\tdata:{\"loanOrderId\":$row.loanOrderId},\r\n");
      out.write("\t\ttype:\"post\",\r\n");
      out.write("\t\tsuccess:function(data){\r\n");
      out.write("\t\t\tif(data){\r\n");
      out.write("\t\t\t\t$(\"#lcId\").val(data.lcId);\r\n");
      out.write("\t\t\t\t$(\"#contractNo\").val(data.contractNo);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("\t// 提交表单信息\r\n");
      out.write("\tfunction  submitTask(result) {\r\n");
      out.write("\t\t\tvar data = {\r\n");
      out.write("\t\t\t\t\"comment\" : $(\"#comment\").val(),\r\n");
      out.write("\t\t\t\t\"result\" :result,\r\n");
      out.write("\t\t\t\t\"loanOrderId\" : $row.loanOrderId,\r\n");
      out.write("\t\t\t\t\"taskId\": $row.taskId,\r\n");
      out.write("\t\t\t\t\"processingResult\":result==\"SignatoryThrough\"?\"A\":\"B\"\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\turl : \"loanOrder/loanOrderAction!submitTask.action\",\r\n");
      out.write("\t\t\t\tdata : data,\r\n");
      out.write("\t\t\t\tsuccess : function(msg) {\r\n");
      out.write("\t\t\t\t\t$grid.datagrid('reload');\r\n");
      out.write("\t\t\t\t\t$taskFormDialog.dialog('close');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t// 查看附件\r\n");
      out.write("\tfunction lookAttachment(index){\r\n");
      out.write("\t\tvar row = getRowData($datagrid,index);\r\n");
      out.write("\t\t// 附件信息\r\n");
      out.write("\t\tcheckAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//生成合同，向合同表插入数据\r\n");
      out.write("\tfunction createContract(){\r\n");
      out.write("\t\tvar lcId = $(\"#lcId\").val();\r\n");
      out.write("\t\tvar contractNo = $(\"#contractNo\").val();\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl:\"loanContract/loanContractAction!saveLoanContract.action\",\r\n");
      out.write("\t\t\ttype:\"post\",\r\n");
      out.write("\t\t\tdata:{\"loanOrderId\":$row.loanOrderId,\"lcId\":lcId,\"contractNo\":contractNo},\r\n");
      out.write("\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\tdata = $.parseJSON(data);\r\n");
      out.write("\t\t\t\tif(data.status){\r\n");
      out.write("\t\t\t\t\t$.messager.show({\r\n");
      out.write("\t\t\t\t\t\ttitle:'提示',\r\n");
      out.write("\t\t\t\t\t\tmsg:data.message,\r\n");
      out.write("\t\t\t\t\t\ttimeout:5000,\r\n");
      out.write("\t\t\t\t\t\tshowType:'slide'\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\texportContract();\r\n");
      out.write("\t\t\t\t\t$(\"#lcId\").val(data.data.lcId);\r\n");
      out.write("\t\t\t\t\t$(\"#contractNo\").val(data.data.contractNo);\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$.messager.alert(\"提示\",data.message,\"error\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t// 导出合同\r\n");
      out.write("\tfunction exportContract(){\r\n");
      out.write("\t\tdownFileByFormPost(\"loanContract/loanContractAction!downloadContract.action\", \r\n");
      out.write("\t\t\t\t{\"loanOrderId\":$row.loanOrderId}\r\n");
      out.write("\t\t);\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t// 完善合同信息弹窗框\r\n");
      out.write("\tfunction saveContract(){\r\n");
      out.write("\t\tvar lcId = $(\"#lcId\").val();\r\n");
      out.write("\t\tif(lcId==\"\"){\r\n");
      out.write("\t\t\t$.messager.alert(\"提示\",\"请先生成合同信息!\",\"info\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(!isNotEmptyComment()) return;\r\n");
      out.write("\t\t$(\".txtDate\").datebox({\r\n");
      out.write("            required: \"true\",\r\n");
      out.write("            editable:false,\r\n");
      out.write("        });\r\n");
      out.write("\t\t$(\"#saveContract\").css(\"display\",\"block\").dialog({\r\n");
      out.write("\t\t\ttitle:\"稽核信息记录表\",\r\n");
      out.write("\t\t\twidth: 1000,    \r\n");
      out.write("\t\t    height: 650,    \r\n");
      out.write("\t\t    closed: false,    \r\n");
      out.write("\t\t    cache: false,    \r\n");
      out.write("\t\t    modal: true ,\r\n");
      out.write("\t\t});\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t// 完善合同并签约\r\n");
      out.write("\tfunction saveContAndSinatoryThroughFun(result){\r\n");
      out.write("\t\tif(!$(\"#contactForm\").form(\"validate\")) return ;\r\n");
      out.write("\t\t$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){\r\n");
      out.write("\t\t\tif(r){\r\n");
      out.write("\t\t\t\t$(\"#contactForm\").form('submit',{\r\n");
      out.write("\t\t\t\t\turl:\"loanContract/loanContractAction!completeContract.action\",\r\n");
      out.write("\t\t\t\t\tonSubmit : function(param) {\r\n");
      out.write("\t\t\t\t\t\tvar isValid = $(this).form('validate');\r\n");
      out.write("\t\t\t\t\t\treturn isValid; // 返回false终止表单提交\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\t\tdata = $.parseJSON(data);\r\n");
      out.write("\t\t\t\t\tif(data.status){\r\n");
      out.write("\t\t\t\t\t\t$.messager.show({\r\n");
      out.write("\t\t\t\t\t\t\ttitle:'提示',\r\n");
      out.write("\t\t\t\t\t\t\tmsg:data.message,\r\n");
      out.write("\t\t\t\t\t\t\ttimeout:5000,\r\n");
      out.write("\t\t\t\t\t\t\tshowType:'slide'\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t$(\"#contactForm\").form(\"clear\");\r\n");
      out.write("\t\t\t\t\t\t$(\"#saveContract\").dialog(\"close\");\r\n");
      out.write("\t\t\t\t\t\tsubmitTask(result);\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t$.messager.alert(\"提示\",data.message,\"info\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t// 客户拒绝签\r\n");
      out.write("\tfunction sinatoryRefuseFun(result){\r\n");
      out.write("\t\tif(!isNotEmptyComment()) return;\r\n");
      out.write("\t\t$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){\r\n");
      out.write("\t\t\tif(r){\t\r\n");
      out.write("\t\t\t\t\tsubmitTask(result);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t// 验证备注信息不为空时\r\n");
      out.write("\tfunction isNotEmptyComment(){\r\n");
      out.write("\t\tif($(\"#comment\").val()==\"\"){\r\n");
      out.write("\t\t\t$.messager.alert(\"提示\",\"请填写备注信息后再进行提交!\",\"warning\")\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("\t<!-- 受理任务 S -->\r\n");
      out.write("<div data-options=\"region:'north',title:'North Title',split:true\">\r\n");
      out.write("\t<div style=\"width: 980px;height: 190px;overflow: auto;\">\r\n");
      out.write("\t\t<form id=\"acceptTaskForm\" method=\"post\">\r\n");
      out.write("\t\t\t <input name=\"id\" id=\"id\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t <input name=\"auditId\" type=\"hidden\" value=\"noauditId\"/>\r\n");
      out.write("\t\t\t <table class=\"table\" cellpadding=\"5px;\">\r\n");
      out.write("\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t    <th>客户姓名:</th>\r\n");
      out.write("\t\t\t\t\t<td><input name=\"name\" readonly=\"readonly\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>身份证号:</th>\r\n");
      out.write("\t\t\t\t\t<td><input name=\"idNo\" readonly=\"readonly\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t \t<th>备注:</th>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t\t<textarea id=\"comment\" name=\"comment\" class=\"easyui-validatebox easyui-textbox\" style=\"width:300px;height:70px;\"></textarea>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t附件类型:\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input id=\"attType\" class=\"easyui-textbox easyui-combobox\" />\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t<a id=\"checkAttachment\" href=\"javascript:void(0);\" class=\"easyui-linkbutton\">查看附件</a>\t\r\n");
      out.write("\t\t\t\t\t\t<a id=\"upploadAttachment\" href=\"javascript:void(0);\" class=\"easyui-linkbutton\" >上传附件</a>\t\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t </table>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div style=\"width:980px;height:30px;\">\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"createContract();\">生成并导出合同</a>\r\n");
      out.write("\t\t<!-- <a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"downloadContract();\">导出合同信息</a> -->\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"saveContract();\">完善合同并签约</a>\r\n");
      out.write("\t\t<!-- <a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitTask('SignatoryThrough',this);\">客户签约</a> -->\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"sinatoryRefuseFun('SignatoryRefuse');\">客户拒签</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"lookInfo\" class=\"easyui-accordion\" style=\"height: 390px;width: 980px;overflow: hidden;\">\r\n");
      out.write("\t    <div title=\"备注信息\" data-options=\"iconCls:'icon-cstbase',selected:true\" >   \r\n");
      out.write("\t\t\t<table id=\"lookLoanOrderdg\" title=\"申请备注的信息\"></table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>   \r\n");
      out.write("<!-- 受理任务 E -->\t\t\r\n");
      out.write("\r\n");
      out.write("<div id=\"saveContract\" style=\"display:none;\">\r\n");
      out.write("\t <form id=\"contactForm\" method=\"post\">\r\n");
      out.write("\t\t<input id=\"lcId\" name=\"lcId\" type=\"hidden\" /><!-- 合同id -->\r\n");
      out.write("\t\t<input id=\"contractNo\" name=\"contractNo\" type=\"hidden\" /><!-- 合同编号 -->\r\n");
      out.write("\t\t<table class=\"table\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>合同签署地:</th>\r\n");
      out.write("\t\t\t\t<td><input name=\"contractSignSite\" class=\"easyui-validatebox easyui-textbox\" type=\"text\" data-options=\"required:true\"/></td>\r\n");
      out.write("\t\t\t    <th>合同签署日期:</th><!-- class=\"easyui-validatebox easyui-datebox\" data-options=\"required:true\" -->\r\n");
      out.write("\t\t\t\t<td><input class=\"txtDate\" name=\"contractSignDate\" type=\"text\" /></td>\r\n");
      out.write("\t\t\t\t<th>共同贷款人签字日期:</th>\r\n");
      out.write("\t\t\t\t<td><input name=\"ljSignDate\"\r\n");
      out.write("\t\t\t\t\ttype=\"text\" class=\"easyui-validatebox easyui-datebox\" data-options=\"editable:false\"/></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>委托代理人姓名:</th>\r\n");
      out.write("\t\t\t\t<td><input name=\"agent\" type=\"text\"\r\n");
      out.write("\t\t\t\t\tclass=\"easyui-validatebox easyui-textbox\"/></td>\r\n");
      out.write("\t\t\t\t<th>委托代理人身份证号:</th>\r\n");
      out.write("\t\t\t\t<td><input name=\"agentIdno\"\r\n");
      out.write("\t\t\t\t\ttype=\"text\" class=\"easyui-validatebox easyui-textbox\" data-options=\"validType:'idcard'\"/></td>\r\n");
      out.write("\t\t\t\t<th>委托代理人签字日期:</th>\r\n");
      out.write("\t\t\t\t<td><input name=\"agentSignDate\" type=\"text\"\r\n");
      out.write("\t\t\t\t\tclass=\"easyui-validatebox easyui-datebox \" data-options=\"editable:false\"/></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t    <th>还款开始日期:</th>\r\n");
      out.write("\t\t\t    <td>\r\n");
      out.write("\t\t\t       <input class=\"txtDate\" name=\"repaymentBgDate\" />\r\n");
      out.write("\t\t\t    </td>\r\n");
      out.write("\t\t\t    <th>还款结束日期:</th>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t   <input class=\"txtDate\" name=\"repaymentEdDate\" type=\"text\"\r\n");
      out.write("\t\t\t\t\t/>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<th>贷款结束日期:</th>\r\n");
      out.write("\t\t\t    <td>\r\n");
      out.write("\t\t\t       <input class=\"txtDate\" name=\"loanEdDate\" />\r\n");
      out.write("\t\t\t    </td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<!-- <tr>\r\n");
      out.write("\t\t\t\t<th>月还款日:</th>\r\n");
      out.write("\t\t\t    <td>\r\n");
      out.write("\t\t\t       <input name=\"monthlyRepaymentDate\" class=\"easyui-combobox\" data-options=\"\r\n");
      out.write("\t\t\t\t\trequired:true,\r\n");
      out.write("\t\t\t\t\tvalueField: 'label',\r\n");
      out.write("\t\t\t\t\ttextField: 'value',\r\n");
      out.write("\t\t\t\t\tdata: [{\r\n");
      out.write("\t\t\t\t\t\tlabel: '15',\r\n");
      out.write("\t\t\t\t\t\tvalue: '15'\r\n");
      out.write("\t\t\t\t\t},{\r\n");
      out.write("\t\t\t\t\t\tlabel: '30',\r\n");
      out.write("\t\t\t\t\t\tvalue: '30'\r\n");
      out.write("\t\t\t\t\t}]\" />\r\n");
      out.write("\t\t\t    </td>\r\n");
      out.write("\t\t\t</tr> -->\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>备注信息:</th>\r\n");
      out.write("\t\t\t\t<td colspan=\"5\"><textarea name=\"remark\" type=\"text\" style=\"width:100%;height:75px;\"\r\n");
      out.write("\t\t\t\t\tclass=\"easyui-textbox easyui-validatebox\" data-options=\"required:true\"/></textarea></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t   <td colspan=\"6\" style=\"text-align: right;\">\r\n");
      out.write("\t\t\t      <a href=\"javascript:void(0)\" id=\"save\" class=\"easyui-linkbutton\" iconCls=\"icon-save\" onclick=\"saveContAndSinatoryThroughFun('SignatoryThrough');\">保存</a>\r\n");
      out.write("\t\t\t   </td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</form>\r\n");
      out.write("</div>\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t\t\r\n");
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