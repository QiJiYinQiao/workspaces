/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-07-23 09:31:52 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.loanOrder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class taskApprovalLeaderForm_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("var $creditAuditReport;\r\n");
      out.write("var attempData;\r\n");
      out.write("var $datagrid;\r\n");
      out.write("$(function(){\r\n");
      out.write("\t// 查看申请状态\r\n");
      out.write("\t$row = parent.$.modalDialog.openner.datagrid('getSelected');\r\n");
      out.write("\t$datagrid = $(\"#lookLoanOrderdg\").datagrid({\r\n");
      out.write("\t\turl : \"loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action\",\r\n");
      out.write("\t\twidth : 'auto',\r\n");
      out.write("\t\theight : 240,\r\n");
      out.write("\t\tpagination:true,\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\tborder:true,\r\n");
      out.write("\t\tsingleSelect:true,\r\n");
      out.write("\t\tnowrap:true,\r\n");
      out.write("\t\tqueryParams:{\"loanOrderId\":$row.loanOrderId},\r\n");
      out.write("\t\tmultiSort:false,\r\n");
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
      out.write("\t\r\n");
      out.write("\t$(\"#upload_form_div input:first\").combobox({\r\n");
      out.write("\t\tvalueField : 'code',\r\n");
      out.write("\t\ttextField : 'text',\r\n");
      out.write("\t\turl:'common/commonAction!findTextArr.action?codeMyid=attachment_type',\r\n");
      out.write("\t\tonLoadSuccess : function(){\r\n");
      out.write("\t\t\tattempData = $(\"#upload_form_div input:first\").combobox(\"getData\");\r\n");
      out.write("            var val = $(this).combobox(\"getData\");\r\n");
      out.write("            for (var item in val[0]) {\r\n");
      out.write("                if (item == \"code\") {\r\n");
      out.write("                    $(this).combobox(\"select\", val[0][item]);\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\teditable:false ,\r\n");
      out.write("    });\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\tloadAttachmentList('attachmentList','noauditId',$row.loanOrderId);\r\n");
      out.write("\t\r\n");
      out.write("});\r\n");
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t// 提交表单信息\r\n");
      out.write("\t\tfunction  submitTask(result,object) {\r\n");
      out.write("\t\t\t// 验证备注信息是否已经填写\r\n");
      out.write("\t\t\tif($(\"#comment\").val()==\"\"){\r\n");
      out.write("\t\t\t\t$.messager.alert(\"提示\",\"请填写备注信息后再进行提交!\",\"warning\")\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t// 确认是否提交\r\n");
      out.write("\t\t\t$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){\r\n");
      out.write("\t\t\t\tif (r){\r\n");
      out.write("\t\t\t\t\tvar data = {\r\n");
      out.write("\t\t\t\t\t\t\"comment\" : $(\"#comment\").val(),\r\n");
      out.write("\t\t\t\t\t\t\"result\" :result,\r\n");
      out.write("\t\t\t\t\t\t\"loanOrderId\" : $row.loanOrderId,\r\n");
      out.write("\t\t\t\t\t\t\"taskId\": $row.taskId,\r\n");
      out.write("\t\t\t\t\t\t\"processingResult\":result==\"ApprovalLeaderThrough\"?\"A\":\"B\"\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\t\t\turl : \"loanOrder/loanOrderAction!submitTask.action\",\r\n");
      out.write("\t\t\t\t\t\tdata : data,\r\n");
      out.write("\t\t\t\t\t\tsuccess : function(msg) {\r\n");
      out.write("\t\t\t\t\t\t\tparent.$.modalDialog.openner.datagrid('reload');\r\n");
      out.write("\t\t\t\t\t\t\tparent.$.modalDialog.handler.dialog('close');\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 根据行索引获取行信息\r\n");
      out.write("\t\tfunction getRowData (index) {\r\n");
      out.write("\t        if (!$.isNumeric(index) || index < 0) { return undefined; }\r\n");
      out.write("\t        var rows = $datagrid.datagrid(\"getRows\");\r\n");
      out.write("\t        return rows[index];\r\n");
      out.write("\t    }\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 查看附件\r\n");
      out.write("\t\tfunction lookAttachment(index){\r\n");
      out.write("\t\t\tvar row = getRowData(index);\r\n");
      out.write("\t\t\t// 附件信息\r\n");
      out.write("\t\t\t$(\"#lookAttachmentList\").datagrid({\r\n");
      out.write("\t\t\t\turl : \"attachment/attachmentAction!findAttachmentListByUserIdAndOrderId.action\",\r\n");
      out.write("\t\t\t\twidth : 'auto',\r\n");
      out.write("\t\t\t\theight : 240,\r\n");
      out.write("\t\t\t\tpagination:true,\r\n");
      out.write("\t\t\t\trownumbers:true,\r\n");
      out.write("\t\t\t\tborder:true,\r\n");
      out.write("\t\t\t\tsingleSelect:true,\r\n");
      out.write("\t\t\t\tnowrap:true,\r\n");
      out.write("\t\t\t\tqueryParams:{\"loanOrderId\":row.loanOrderId,\"userId\":row.assignee},\r\n");
      out.write("\t\t\t\tmultiSort:false,\r\n");
      out.write("\t\t\t\tcolumns : [ [ \r\n");
      out.write("\t\t\t\t              {field : 'attName',title : '附件名称',width : parseInt($(this).width()*0.1),sortable:true},\r\n");
      out.write("\t\t\t\t              {field : 'attTypeName',title : '附件类型',width : parseInt($(this).width()*0.1)},\r\n");
      out.write("\t\t\t\t              {field : 'creatorName',title : '创建者',width : parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t\t\t              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.1),align : 'left',\r\n");
      out.write("\t\t\t\t            \tformatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t            \t\treturn \"<a href='javascript:void(0);' onclick=''>在线预览</a>　　\" ;\r\n");
      out.write("\t\t\t\t            \t}  \r\n");
      out.write("\t\t\t\t              }\r\n");
      out.write("\t\t\t\t              ] ]\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$('#lookInfo').accordion(\"select\",\"附件信息\"); \r\n");
      out.write("\t\t}\r\n");
      out.write("</script>\r\n");
      out.write("\t\t<!-- 受理任务 S -->\r\n");
      out.write("\t\t\t<div data-options=\"region:'north',title:'North Title',split:true\">\r\n");
      out.write("\t\t\t\t<div style=\"width: 980px;height: 280px;overflow: auto;\">\r\n");
      out.write("\t\t\t\t\t<form id=\"acceptTaskForm\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t\t <input name=\"id\" id=\"id\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t\t\t\t <input name=\"auditId\" type=\"hidden\" value=\"noauditId\"/>\r\n");
      out.write("\t\t\t\t\t\t <table class=\"table\" cellpadding=\"5px;\">\r\n");
      out.write("\t\t\t\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t\t\t    <th>客户姓名:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input name=\"name\" readonly=\"readonly\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>身份证号:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input name=\"idNo\" readonly=\"readonly\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t \t<th>备注:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<textarea id=\"comment\" name=\"comment\" class=\"easyui-validatebox easyui-textbox\" style=\"width:300px;height:70px;\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t </table>\r\n");
      out.write("\t\t\t\t\t\t<div id=\"attachmentList\" style=\"width:100%;display:block;float:left;\">\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div id=\"upload_form_div_add\">\r\n");
      out.write("\t\t\t\t\t\t\t<div id=\"upload_form_father_idDiv\" style=\"width:100%;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div id=\"upload_form_div\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<font size=\"2\" style=\"font-weight: bold;\">　上传附件:&nbsp;</font>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input class=\"easyui-textbox easyui-combobox\" type=\"text\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input name=\"fileName\" type=\"text\" placeholder=\"请输入附件名\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input id=\"file\" name=\"file\" type=\"file\"  onchange=\"fileChange(this);\" > \r\n");
      out.write("\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\" onclick=\"addACredential(this);\">添加</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" plain=\"true\" onclick=\"removeACredential(this);\">删除</a> \r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div id=\"upload_form\" style=\"width: 100%; height: 30px; text-align: right;\">\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"fileUploads(this)\">上传附件</a>\r\n");
      out.write("\t\t\t\t\t\t</div> \r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div style=\"width: 980px;height:30px;\">\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitTask('ApprovalLeaderThrough',this);\">审批经理通过</a>\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitTask('ApprovalLeaderRefuse',this);\">审批经理拒贷</a>\r\n");
      out.write("\t\t\t\t</div>\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<div id=\"lookInfo\" class=\"easyui-accordion\" style=\"height: 300px;width: 980px;overflow: hidden;\">\r\n");
      out.write("\t\t\t    <div title=\"备注信息\" data-options=\"iconCls:'icon-cstbase',selected:true\" style=\"padding:10px;\">   \r\n");
      out.write("\t\t\t\t\t<table id=\"lookLoanOrderdg\" title=\"申请备注的信息\"></table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t    <div title=\"附件信息\" data-options=\"iconCls:'icon-cstbase'\" style=\"padding:10px;\">   \r\n");
      out.write("\t\t\t\t\t<table id=\"lookAttachmentList\" title=\"申请附件的信息\"></table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>   \r\n");
      out.write("\t\t<!-- 受理任务 E -->\t\t\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\r\n");
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
