/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-09-07 03:02:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.loanOrder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class taskUnClaimMain_jsp extends org.apache.jasper.runtime.HttpJspBase
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

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    <title>代办任务</title>\r\n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \r\n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tvar $grid;\r\n");
      out.write("\t\t\t$(function() {\r\n");
      out.write("\t\t\t\t $grid=$(\"#dg\").datagrid({\r\n");
      out.write("\t\t\t\t\turl : \"loanOrder/loanOrderAction!findAllUnClaimTask.action\",\r\n");
      out.write("\t\t\t\t\twidth : 'auto',\r\n");
      out.write("\t\t\t\t\theight : $(this).height()*0.96,\r\n");
      out.write("\t\t\t\t\tpagination:true,\r\n");
      out.write("\t\t\t\t\trownumbers:true,\r\n");
      out.write("\t\t\t\t\tborder:true,\r\n");
      out.write("\t\t\t\t\tsingleSelect:true,\r\n");
      out.write("\t\t\t\t\tnowrap:true,\r\n");
      out.write("\t\t\t\t\tmultiSort:false,\r\n");
      out.write("\t\t\t\t\tcolumns : [ [ {field : 'name',title : '客户姓名',width : parseInt($(this).width()*0.06)},\r\n");
      out.write("\t\t\t\t\t              {field : 'idNo',title : '身份证号',width : parseInt($(this).width()*0.1)},\r\n");
      out.write("\t\t\t\t\t              {field : 'age',title : '年龄',width : parseInt($(this).width()*0.03)},\r\n");
      out.write("\t\t\t\t\t              {field : 'annualSalary',title : '年收入(单位:元)',width : parseInt($(this).width()*0.06)},\r\n");
      out.write("\t\t\t\t\t              {field : 'mortgageStatus',title : '居住情况',width : parseInt($(this).width()*0.1)},\r\n");
      out.write("\t\t\t\t\t              {field : 'loanAmount',title : '申请贷款额度(单位:元)',width : parseInt($(this).width()*0.08)},\r\n");
      out.write("\t\t\t\t\t              {field : 'loanMin',title : '最低接受额度(单位:元)',width : parseInt($(this).width()*0.08)},\r\n");
      out.write("\t\t\t\t\t              {field : 'loanPeriod',title : '申请贷款期限',width : parseInt($(this).width()*0.1)},\r\n");
      out.write("\t\t\t\t\t              {field : 'repayMethod',title : '还款方式',width : parseInt($(this).width()*0.1)},\r\n");
      out.write("\t\t\t\t\t              {field : 'purpose',title : '贷款用途',width : parseInt($(this).width()*0.1)},\r\n");
      out.write("\t\t\t\t\t              {field : 'orderStatus',title : '订单状态',width : parseInt($(this).width()*0.1),\r\n");
      out.write("\t\t\t\t\t            \t  formatter: function(value,row,index){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\treturn value.statusName;\r\n");
      out.write("\t\t\t\t\t            \t  }\r\n");
      out.write("\t\t\t\t\t              }, \r\n");
      out.write("\t\t\t\t\t              {field : 'operate',title : '操作',width : parseInt($(this).width()*0.2),\r\n");
      out.write("\t\t\t\t\t            \t  formatter: function(value,row,index){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tvar result=\"<a href='javascript:void(0);' onclick='loanOrderInfo(\"+ index + \");'>查看申请详情</a>　 \";\r\n");
      out.write("\t\t      \t\t\t\t\t\t\tresult +=  \"<a href='javascript:void(0);' onclick='lookLoanOrderProcessCommentDialog(\"+index+\");'>查看审批意见</a>　　\";\r\n");
      out.write("\t\t      \t\t\t\t\t\t\tresult +=\"<a href='javascript:void(0);' onclick='showImage(\"+index+\");'>查看审批流程</a>　　\";\r\n");
      out.write("\t\t      \t\t\t\t\t\t\tresult +=\"<a href='javascript:void(0);' onclick='claimTask(\"+index+\");'>签收任务</a>\";\r\n");
      out.write("\t\t      \t\t\t\t\t\t\treturn result;\r\n");
      out.write("\t\t\t\t\t      \t\t\t}\r\n");
      out.write("\t\t\t\t\t              }\r\n");
      out.write("\t\t\t\t\t              ] ],toolbar:'#tb'\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//查看详情\r\n");
      out.write("\t\tfunction loanOrderInfo(index) {\r\n");
      out.write("\t\t\tvar row = getRowData($grid,index);\r\n");
      out.write("\t\t\twindow.open(\"jsp/loanOrder/loanOrderDetailsForm.jsp?loanerId=\"+row.loanerId+\"&loanOrderId=\"+row.loanOrderId,\r\n");
      out.write("\t\t\t\t\t\"详情\", 'height=650, width=1000, top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 查看流程图片\r\n");
      out.write("\t\tfunction  showImage(index) {\r\n");
      out.write("\t\t\tvar row = getRowData($grid,index);\r\n");
      out.write("\t\t\tvar src = \"loanOrder/loanOrderAction!getDiagramResourceByTaskId.action?taskId=\"+ row.taskId;\r\n");
      out.write("\t\t\t$('#imageDialog').dialog(\"open\");\r\n");
      out.write("\t\t\t$(\"#image\").attr(\"src\", src);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t    \r\n");
      out.write("\t\t// 领取任务\r\n");
      out.write("\t\tfunction claimTask(index) {\r\n");
      out.write("\t\t\tvar row = getRowData($grid,index);\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\turl : \"loanOrder/loanOrderAction!saveClaimTask.action\",\r\n");
      out.write("\t\t\t\tdata : {\"taskId\" : row.taskId},\r\n");
      out.write("\t\t\t\tsuccess : function(rsp) {\r\n");
      out.write("\t\t\t\t\tif(rsp.status){\r\n");
      out.write("\t\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\t\ttitle : rsp.title,\r\n");
      out.write("\t\t\t\t\t\t\tmsg : rsp.message,\r\n");
      out.write("\t\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t// 如果是初审通过状态，IPC进行挑拣，领取任务后直接默认通过办理----此处可以理解为IPC简单的挑拣，下一结点才为办理\r\n");
      out.write("\t\t\t\t\t\tif(row.orderStatus.statusCode!=null && row.orderStatus.statusCode ==\"LoanOrder_InitialAuditThrough\"){\r\n");
      out.write("\t\t\t\t\t\t\tsubmitChooseTask(row);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\tparent.$.messager.alert(rsp.title,rsp.message,'warning');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t$grid.datagrid('reload');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 查看流程批注\r\n");
      out.write("\t\tfunction lookLoanOrderProcessCommentDialog(index) {\r\n");
      out.write("\t\t\tvar row = getRowData($grid,index);\r\n");
      out.write("\t\t\tparent.$.modalDialog.openner= $grid;\r\n");
      out.write("\t\t\tparent.$.modalDialog({\r\n");
      out.write("\t\t\t\ttitle : '审批意见查看',\r\n");
      out.write("\t\t\t\twidth : 1000,\r\n");
      out.write("\t\t\t\theight : 650,\r\n");
      out.write("\t\t\t\thref : \"jsp/loanOrder/loanOrderProcessComment.jsp\"});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 此处方法就是为了ipc挑拣准备的默认执行方法\r\n");
      out.write("\t\tfunction  submitChooseTask(row) {\r\n");
      out.write("\t\t\tvar data = {\r\n");
      out.write("\t\t\t\t\"comment\" : \"IPC挑拣完毕\",\r\n");
      out.write("\t\t\t\t\"result\"  :   \"IPCInvestigationDeptChooseThrough\",\r\n");
      out.write("\t\t\t\t\"loanOrderId\" : row.loanOrderId,\r\n");
      out.write("\t\t\t\t\"taskId\": row.taskId,\r\n");
      out.write("\t\t\t\t\"processingResult\":\"A\"\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\turl : \"loanOrder/loanOrderAction!submitTask.action\",\r\n");
      out.write("\t\t\t\tdata : data,\r\n");
      out.write("\t\t\t\tsuccess : function(msg) {\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t</script>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("      <div data-options=\"region:'center',border : false\">\r\n");
      out.write("  \t\t<div class=\"well well-small\" style=\"margin-left: 5px;margin-top: 5px\">\r\n");
      out.write("\t\t\t\t业务管理-->贷款业务管理-->代办任务\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<table id=\"dg\"></table>\r\n");
      out.write("\t    <div id=\"imageDialog\"  class=\"easyui-dialog\" title=\"流程图片\" data-options=\"border:false,closed:true,fit:true\">\r\n");
      out.write("\t\t\t<img id=\"image\" src=\"\" >\r\n");
      out.write("\t\t</div>\r\n");
      out.write("  \t</div>\t\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
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
