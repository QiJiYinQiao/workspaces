/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-08-14 02:54:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.investOrder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class taskDeptZlForm_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\ttable {border-radius: 5px;}\r\n");
      out.write("\t.linkSpan{\r\n");
      out.write("\t  padding:5px;\r\n");
      out.write("\t  display:-moz-inline-box;\r\n");
      out.write("\t  display:inline-block;\r\n");
      out.write("\t  width:40%; \r\n");
      out.write("\t  text-align: center;\r\n");
      out.write("\t}\r\n");
      out.write("\t.linkSpanS{\r\n");
      out.write("\t  padding:5px;\r\n");
      out.write("\t  display:-moz-inline-box;\r\n");
      out.write("\t  display:inline-block;\r\n");
      out.write("\t  width:10%; \r\n");
      out.write("\t  text-align: center;\r\n");
      out.write("\t}\r\n");
      out.write("\ta{text-decoration: none;}\r\n");
      out.write("\ta:hover {\r\n");
      out.write("\t color: #FF0000;\r\n");
      out.write("\t}\r\n");
      out.write("\t.table th{\r\n");
      out.write("\t\ttext-align: right;\r\n");
      out.write("\t}\r\n");
      out.write("\t.table td{\r\n");
      out.write("\t\ttext-align: left;\r\n");
      out.write("\t}\t\r\n");
      out.write("\ttextarea{resize: none;}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var row,row1;\r\n");
      out.write("var investorId='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${investorId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("var investOrderId='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${investOrderId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("var taskId='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${taskId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("$(function(){\r\n");
      out.write("\t//查询投资人详细信息\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl:'investor/investorAction!findInvestorById.action',\r\n");
      out.write("\t\t\tdata:'investorId='+investorId,\r\n");
      out.write("\t\t\tdataType:'json',\r\n");
      out.write("\t\t\tasync : false,\r\n");
      out.write("\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\trow = data;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t})\r\n");
      out.write("\t// 渲染姓名\r\n");
      out.write("\t$(\"#acceptTaskForm input[name='name']\").val(row.chName);\r\n");
      out.write("\t// 渲染身份证号\r\n");
      out.write("\t$(\"#acceptTaskForm input[name='idNo']\").val(row.idNo);\r\n");
      out.write("\t// 查看申请状态\r\n");
      out.write("\t$(\"#lookLoanOrderdg\").datagrid({\r\n");
      out.write("\t\turl : \"investOrderHis/investOrderHisAction!findAllInvestOrderHisList.action?investOrderId=\"+investOrderId,\r\n");
      out.write("\t\twidth : 'auto',\r\n");
      out.write("\t\theight : 240,\r\n");
      out.write("\t\tpagination:true,\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\tborder:true,\r\n");
      out.write("\t\tsingleSelect:true,\r\n");
      out.write("\t\tnowrap:true,\r\n");
      out.write("\t\tcolumns : [ [ \r\n");
      out.write("\t\t              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),sortable:true},\r\n");
      out.write("\t\t              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1)},\r\n");
      out.write("\t\t              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t              {field : 'comment',title : '审批意见',width :parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t              {field : 'aa',title : '查看附件',width :parseInt($(this).width()*0.09),align : 'left',\r\n");
      out.write("\t\t\t            \tformatter:function(value,row,index){\r\n");
      out.write("\t\t\t            \t\treturn \"<a href='javascript:void(0);' onclick='lookAttachment(\"+index+\");'>查看附件</a>　　\" ;\r\n");
      out.write("\t\t\t            \t}  \r\n");
      out.write("\t\t              }\r\n");
      out.write("\t\t              ] ]\r\n");
      out.write("\t});\r\n");
      out.write("\t$(\"#upload_form_div input:first\").combobox({\r\n");
      out.write("\t\tvalueField : 'code',\r\n");
      out.write("\t\ttextField : 'text',\r\n");
      out.write("\t\turl:'common/commonAction!findTextArr.action?codeMyid=attachment_type_invest',\r\n");
      out.write("\t\tonLoadSuccess : function(){\r\n");
      out.write("\t\t\tattempData = $(\"#upload_form_div input:first\").combobox(\"getData\");\r\n");
      out.write("\t\t\tvar val = $(this).combobox(\"getData\");\r\n");
      out.write("            for (var item in val[0]) {\r\n");
      out.write("                if (item == \"code\") {\r\n");
      out.write("                    $(this).combobox(\"select\", val[0][item]);\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\teditable:false ,\r\n");
      out.write("    });\r\n");
      out.write("\t\r\n");
      out.write("\tloadAttachmentList('attachmentList',investOrderId);\r\n");
      out.write("});\t\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t/**==完善客户及合同信息==*/\r\n");
      out.write("\tfunction completeOrderInfo(){\r\n");
      out.write("\t\t//查询地址\r\n");
      out.write("\t\tvar addr=new Array();\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\turl:'investor/investorAction!findAddressById.action',\r\n");
      out.write("\t\t\t\tdata:'addressId='+row[\"commAddr\"],\r\n");
      out.write("\t\t\t\tdataType:'json',\r\n");
      out.write("\t\t\t\tasync : false,\r\n");
      out.write("\t\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\t\taddr=data;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}); \r\n");
      out.write("\t\t//打开dialog\r\n");
      out.write("\t\t$(\"#dd\").dialog({\r\n");
      out.write("\t\t\ttitle : '编辑',\r\n");
      out.write("\t\t\twidth : 920,\r\n");
      out.write("\t\t\theight : 600,\r\n");
      out.write("\t\t\tmodal:true,\r\n");
      out.write("\t\t\t//href : \"jsp/investOrder/investOrderEditForm.jsp?investOrderId=\"+investOrderId,\r\n");
      out.write("\t\t\thref:'investorderAndProducts/investorderAndProductsAction!gotoCompleteOrderInfo.action?orderId='+investOrderId,\r\n");
      out.write("\t\t\tonLoad:function(){\r\n");
      out.write("\t\t\t\tvar f = $(\"#baseInfoForm\");\r\n");
      out.write("\t\t\t\tif(addr!=null){\r\n");
      out.write("\t\t\t\t\trow[\"provinceId\"]=addr[\"provinceId\"];\r\n");
      out.write("\t\t\t\t\trow[\"cityId\"]=addr[\"cityId\"];\r\n");
      out.write("\t\t\t\t\trow[\"areaId\"]=addr[\"areaId\"]; \r\n");
      out.write("\t\t\t\t\trow[\"addressDetails\"]=addr[\"addrDetails\"];\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tf.form(\"load\", row);\r\n");
      out.write("\t\t\t\trenderProvinceSelect('provinceId','cityId','areaId');\r\n");
      out.write("\t\t\t\t$(\"#provinceId\").combobox(\"setValue\",row.provinceId);\r\n");
      out.write("\t\t        $(\"#cityId\").combobox(\"setValue\",row.cityId);\r\n");
      out.write("\t\t        $(\"#areaId\").combobox(\"setValue\",row.areaId); \r\n");
      out.write("\t\t        initLinkPeopleGrid(row.investorId);\t\r\n");
      out.write("\t\t        //查询订单详情\r\n");
      out.write("\t\t        $.ajax({\r\n");
      out.write("\t\t\t\t\turl:'investOrder/investOrderAction!findByInvestOrderId.action',\r\n");
      out.write("\t\t\t\t\tdata:'investOrderId='+investOrderId,\r\n");
      out.write("\t\t\t\t\tdataType:'json',\r\n");
      out.write("\t\t\t\t\tasync : false,\r\n");
      out.write("\t\t\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\t\t\trow1 = data;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t})\r\n");
      out.write("\t\t\t\t$(\"#constractInfoForm\").form(\"load\",row1);\r\n");
      out.write("\t\t        $(\"#orderStatus\").val(row1.orderStatus.statusCode);\r\n");
      out.write("\t\t\t} \r\n");
      out.write("\t\t}); \r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t/**======审批通过或驳回=======*/\r\n");
      out.write("\tfunction  submitTask(result,object) {\r\n");
      out.write("\t\tconsole.info(result);\r\n");
      out.write("\t\t// 验证备注信息是否已经填写\r\n");
      out.write("\t\tif($(\"#comment\").val()==\"\"){\r\n");
      out.write("\t\t\t$.messager.alert(\"提示\",\"请填写备注信息后再进行提交!\",\"warning\")\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t// 确认是否提交\r\n");
      out.write("\t\t$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){\r\n");
      out.write("\t\t\tif (r){\r\n");
      out.write("\t\t\t\tvar data = {\r\n");
      out.write("\t\t\t\t\t\"comment\" : $(\"#comment\").val(),\r\n");
      out.write("\t\t\t\t\t\"result\" :result,\r\n");
      out.write("\t\t\t\t\t\"investOrderId\" :investOrderId,\r\n");
      out.write("\t\t\t\t\t\"taskId\": taskId,\r\n");
      out.write("\t\t\t\t\t\"processingResult\":result==\"DeptAssistantAgree\"?\"A\":\"B\"\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\t\turl : \"investOrder/investOrderAction!submitTask.action\",\r\n");
      out.write("\t\t\t\t\tdata : data,\r\n");
      out.write("\t\t\t\t\tsuccess : function(msg) {\r\n");
      out.write("\t\t\t\t\t\tparent.$.modalDialog.openner.datagrid('reload');\r\n");
      out.write("\t\t\t\t\t\tparent.$.modalDialog.handler.dialog('close');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t/**=============上传附件及显示已上传附件列表===================*/\r\n");
      out.write("\t//上传附件方法\r\n");
      out.write("\t\tfunction fileUploads(obj){\r\n");
      out.write("\t\t\tvar id = $(obj).parent().prev().children().attr(\"id\");\r\n");
      out.write("\t\t\tvar listId = $(obj).parent().prev().prev().attr(\"id\");\r\n");
      out.write("\t\t\tvar cDivClone = $(\"#\"+id).children(\"div:first\");\r\n");
      out.write("\t\t\tvar cDiv = $(\"#\"+id).children();\r\n");
      out.write("\t\t\tvar att_types = \"\"; //附件类型\r\n");
      out.write("\t\t\tvar fileNames = \"\"; //附件名\r\n");
      out.write("\t\t\tvar fileIds = [];\t//附件id\r\n");
      out.write("\t\t\tfor(var i = 0 ; i < cDiv.length; i++){\r\n");
      out.write("\t\t\t\tvar cDivId = cDiv[i].id;\r\n");
      out.write("\t\t\t\t//console.info(\"cDivId=======\"+cDivId);\r\n");
      out.write("\t\t\t\tvar att_type = $(\"#\"+cDivId+\" input:first\").combobox(\"getValue\");\r\n");
      out.write("\t\t\t\t//console.info(\"att_type=======\"+att_type);\r\n");
      out.write("\t\t\t\tvar fileName = $(\"#\"+cDivId+\" input[name='fileName']\").val();\r\n");
      out.write("\t\t\t\t//console.info(\"fileName=======\"+fileName);\r\n");
      out.write("\t\t\t\tvar fileId = $(\"#\"+cDivId+\" input:last\").attr(\"id\");\r\n");
      out.write("\t\t\t\t//console.info(\"fileId=======\"+fileId);\r\n");
      out.write("\t\t\t\tvar fileValue = document.getElementById(fileId).value;\r\n");
      out.write("\t\t\t\t//console.info(\"fileValue=======\"+fileValue);\r\n");
      out.write("\t\t\t\tif(\"\"==att_type || \"\"==fileName || \"\"==fileValue){\r\n");
      out.write("\t\t\t\t\t$.messager.alert(\"提示\",\"请填写完整信息\",\"info\");\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tatt_types  += att_type + \",\";\r\n");
      out.write("\t\t\t\tfileNames += fileName + \",\";\r\n");
      out.write("\t\t\t\tfileIds[i] = fileId;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$.ajaxFileUpload({\r\n");
      out.write("\t\t\t\turl:'attachment/attachmentAction!saveInvestAttachment.action',\r\n");
      out.write("\t\t\t\tdata:{\"fileName\":fileNames,\"attType\":att_types,\"investOrderId\" : investOrderId},\r\n");
      out.write("\t\t\t\tfileElementId:fileIds,\r\n");
      out.write("\t\t\t\tsecureuri:false,\r\n");
      out.write("\t\t\t\tdataType:'text',\r\n");
      out.write("\t\t\t\tasync : false,\r\n");
      out.write("\t\t\t\tsuccess:function(data,status){\r\n");
      out.write("\t\t\t\t\tloadAttachmentList(listId,investOrderId);\r\n");
      out.write("\t\t\t\t\tdata = $.parseJSON(data);\r\n");
      out.write("\t\t\t\t\t$.messager.alert(\"提示\",data.message,\"info\");\r\n");
      out.write("\t\t\t\t\t$(\"#\"+id).empty().append(cDivClone);\r\n");
      out.write("\t\t\t\t\tconsole.info(id);\r\n");
      out.write("\t\t\t\t\tconsole.info(cDivClone);\r\n");
      out.write("\t\t\t\t\t$(cDivClone).children(\"a:first\").click();\r\n");
      out.write("\t\t\t\t\t$(cDivClone).remove();\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\terror: function(){\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t/**=========加载附件列表========*/ \r\n");
      out.write("\t\tfunction loadAttachmentList(listId,investOrderId){\r\n");
      out.write("\t\t\t$(\"#\"+listId).empty();\r\n");
      out.write("\t\t\tvar str = \"<div id='firstDiv\"+listId+\"' style='width:50%;height:30px;float: left;'><span class='linkSpan'>附件名称</span></a><span class='linkSpan'>附件类型</span><span class='linkSpanS'>操作</span></div><div id='secondDiv\"+listId+\"' style='width:50%;height:30px;float: left;'><span class='linkSpan'>附件名称</span></a><span class='linkSpan'>附件类型</span><span class='linkSpanS'>操作</span></div>\";\r\n");
      out.write("\t\t\t$(\"#\"+listId).append(str);\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\turl : \"attachment/attachmentAction!findAttachmentByOrderTypeAndOrderId.action\",\r\n");
      out.write("\t\t\t\tdata : {\"orderType\":\"investorOrder\",\"investOrderId\":investOrderId},\r\n");
      out.write("\t\t\t\ttype : \"post\",\r\n");
      out.write("\t\t\t\tasync : false,\r\n");
      out.write("\t\t\t\tsuccess : function(data){\r\n");
      out.write("\t\t\t\t\tif(data.length==0){\r\n");
      out.write("\t\t\t\t\t\t$(\"#firstDiv\"+listId).empty();\r\n");
      out.write("\t\t\t\t\t\t$(\"#secondDiv\"+listId).empty();\r\n");
      out.write("\t\t\t\t\t}else if(data.length==1){\r\n");
      out.write("\t\t\t\t\t\t$(\"#secondDiv\"+listId).empty();\r\n");
      out.write("\t\t\t\t\t} \r\n");
      out.write("\t\t\t\t\tif(data){\r\n");
      out.write("\t\t\t\t\t\tvar attId;\r\n");
      out.write("\t\t\t\t\t\tvar attName;\r\n");
      out.write("\t\t\t\t\t\tvar attType;\r\n");
      out.write("\t\t\t\t\t\tvar linkStr = \"\";\r\n");
      out.write("\t\t\t\t\t\tvar j = 0;\r\n");
      out.write("\t\t\t\t\t\t$.each(data,function(i,item){\r\n");
      out.write("\t\t\t\t\t\t\tattId = data[i].attId;\r\n");
      out.write("\t\t\t\t\t\t\tattName = data[i].attName;\r\n");
      out.write("\t\t\t\t\t\t\tattType = data[i].attType;\r\n");
      out.write("\t\t\t\t\t\t\tattTypeName = data[i].attTypeName;\r\n");
      out.write("\t\t\t\t\t\t\tlinkStr = \"<div style='width:50%;height:30px;float: left;'><input type='hidden' name='attId' value='\"+attId+\"' /><a target='_blank' href='jsp/openoffice/documentView.jsp?attId=\"+attId+\"'><span class='linkSpan'>\"+attName+\"</span></a><span class='linkSpan'>\"+attTypeName+\"</span><a href='javascript:void(0);' class='attachBackLinkButton' onclick=\\\"deleteAttachment(this,'\"+attId+\"');\\\">删除</a><a href='javascript:void(0);' class='attachBackLinkButton' onclick=\\\"downloadAttachment('\"+attId+\"');\\\">　下载</a></div>\";\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#\"+listId).append(linkStr);\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\terror : function(){\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\r\n");
      out.write("\t\t// 根据行索引获取行信息\r\n");
      out.write("\t\tfunction getRowData (index) {\r\n");
      out.write("\t\t    if (!$.isNumeric(index) || index < 0) { return undefined; }\r\n");
      out.write("\t\t    var rows = $(\"#lookLoanOrderdg\").datagrid(\"getRows\");\r\n");
      out.write("\t\t    return rows[index];\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//查看附件信息\r\n");
      out.write("\t\tfunction lookAttachment(index){\r\n");
      out.write("\t\t\t\tvar row = getRowData(index);\r\n");
      out.write("\t\t\t\t$(\"#lookAttachmentList\").datagrid({\r\n");
      out.write("\t\t\t\t\turl : \"attachment/attachmentAction!findAllAttachmentList.action\",\r\n");
      out.write("\t\t\t\t\twidth : 'auto',\r\n");
      out.write("\t\t\t\t\theight : 430,\r\n");
      out.write("\t\t\t\t\tpagination:false,\r\n");
      out.write("\t\t\t\t\trownumbers:true,\r\n");
      out.write("\t\t\t\t\tborder:false,\r\n");
      out.write("\t\t\t\t\tsingleSelect:true,\r\n");
      out.write("\t\t\t\t\tnowrap:true,\r\n");
      out.write("\t\t\t\t\tqueryParams:{\"orderId\":row.investOrderId,\"userId\":row.assignee,\"orderType\":\"attachment_type_invest\"},\r\n");
      out.write("\t\t\t\t\tmultiSort:false,\r\n");
      out.write("\t\t\t\t\tcolumns : [ [ \r\n");
      out.write("\t\t\t\t\t              {field : 'attName',title : '附件名称',width : 200,sortable:true,align:'center'},\r\n");
      out.write("\t\t\t\t\t              {field : 'attTypeName',title : '附件类型',width : 160,align:'center'},\r\n");
      out.write("\t\t\t\t\t              {field : 'creatorName',title : '创建者',width : 170,align:'center'},\r\n");
      out.write("\t\t\t\t\t              {field : 'id',title : '查看附件',width :220,align:'center',formatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t\t            \t\tvar result = \"<a target='_blank' href='jsp/openoffice/documentView.jsp?attId=\"+row.attId+\"'>在线预览</a>　　\" ;\r\n");
      out.write("\t\t\t\t\t            \t\t\tresult += \"<a target='_blank' href='javascript:void(0);' onclick=\\\"downloadAttachment('\"+row.attId+\"');\\\">下载</a>　　\" ;\r\n");
      out.write("\t\t\t\t\t            \t\treturn result;\r\n");
      out.write("\t\t\t\t\t              }}\r\n");
      out.write("\t\t\t\t    ] ]\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$('#lookInfo').accordion(\"select\",\"附件信息\"); \r\n");
      out.write("\t\t}\r\n");
      out.write("</script>\r\n");
      out.write("\t\t<!-- 受理任务 S -->\r\n");
      out.write("\t\t<div data-options=\"region:'north',title:'North Title',split:true\">\r\n");
      out.write("\t\t\t<div style=\"height: 280px;overflow: auto;\" >\r\n");
      out.write("\t\t\t<form id=\"acceptTaskForm\" method=\"post\">\r\n");
      out.write("\t\t\t\t <input name=\"id\" id=\"id\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t\t <input name=\"auditId\" type=\"hidden\" value=\"noauditId\"/>\r\n");
      out.write("\t\t\t\t <table class=\"table\" cellpadding=\"5px;\">\r\n");
      out.write("\t\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t    <th>客户姓名:</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"name\" readonly=\"readonly\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>身份证号:</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"idNo\" readonly=\"readonly\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t \t<th>备注:</th>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t\t\t<textarea id=\"comment\" name=\"comment\" class=\"easyui-validatebox easyui-textbox\" style=\"width:300px;height:70px;\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t </table>\r\n");
      out.write("\t\t\t\t<div id=\"attachmentList\" style=\"width:100%;display:block;float:left;\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div id=\"upload_form_div_add\">\r\n");
      out.write("\t\t\t\t\t<div id=\"upload_form_father_idDiv\" style=\"width:100%;\">\r\n");
      out.write("\t\t\t\t\t\t<div id=\"upload_form_div\">\r\n");
      out.write("\t\t\t\t\t\t\t<font size=\"2\" style=\"font-weight: bold;\">　上传附件:&nbsp;</font>\r\n");
      out.write("\t\t\t\t\t\t\t<input class=\"easyui-textbox easyui-combobox\" type=\"text\" />\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"fileName\" type=\"text\" placeholder=\"请输入附件名\">\r\n");
      out.write("\t\t\t\t\t\t\t<input id=\"file\" name=\"file\" type=\"file\"  onchange=\"fileChange(this);\" > \r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\" onclick=\"addACredential(this);\">添加</a>\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" plain=\"true\" onclick=\"removeACredential(this);\">删除</a> \r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div id=\"upload_form\" style=\"width: 100%; height: 30px; text-align: right;\">\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"fileUploads(this)\">上传附件</a>\r\n");
      out.write("\t\t\t\t</div> \r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div style=\"width: 880px;height:30px;\">\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"completeOrderInfo()\">完善客户及合同信息</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitTask('DeptAssistantAgree',this);\">审批通过</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitTask('DeptAssistantReject',this);\">审批驳回</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t    <div id=\"lookInfo\" class=\"easyui-accordion\" style=\"height: 300px;width: 980px;overflow: hidden;\">\r\n");
      out.write("\t\t\t    <div title=\"备注信息\" data-options=\"iconCls:'icon-cstbase',selected:true\" >   \r\n");
      out.write("\t\t\t\t\t<table id=\"lookLoanOrderdg\" title=\"申请备注的信息\"></table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t    <div title=\"附件信息\" data-options=\"iconCls:'icon-cstbase'\" >   \r\n");
      out.write("\t\t\t\t\t<table id=\"lookAttachmentList\" title=\"申请附件的信息\"></table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div>   \r\n");
      out.write("\t\t<!-- 受理任务 E -->\t\t\r\n");
      out.write("\t\t<div id=\"dd\"></div>\t");
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
