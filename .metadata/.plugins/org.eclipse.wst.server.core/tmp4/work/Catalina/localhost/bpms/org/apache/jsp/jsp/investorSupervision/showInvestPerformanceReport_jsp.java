/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-08-26 02:19:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.investorSupervision;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class showInvestPerformanceReport_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \r\n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("\t\ta{\r\n");
      out.write("\t\t\ttext-decoration:none;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</style>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t//声明一个全局变量row\r\n");
      out.write("\t\t\t//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。\r\n");
      out.write("\t\t\tvar $dg;\r\n");
      out.write("\t\t\tvar $grid;\r\n");
      out.write("\t\t\t$(function() {\r\n");
      out.write(" \t\t\t\t//初始化组织机构\r\n");
      out.write("\t\t\t\t$(\"#orgId\").combotree({\r\n");
      out.write("\t\t\t\t\twidth:171,\r\n");
      out.write("\t\t\t\t\turl:\"orgz/organizationAction!findOrganizationList.action\",\r\n");
      out.write("\t\t\t\t\tidFiled:'id',\r\n");
      out.write("\t\t\t\t \ttextFiled:'name',\r\n");
      out.write("\t\t\t\t \tparentField:'pid',\r\n");
      out.write("\t\t\t\t \tonLoadSuccess:function(data){\r\n");
      out.write("\t\t\t\t \t\t//加一个全部\r\n");
      out.write("\t\t\t\t \t}\r\n");
      out.write("\t\t\t\t}); \r\n");
      out.write("\t\t\t\t//加载投资客户的数据\r\n");
      out.write("\t\t\t\t $dg = $(\"#dg\");\r\n");
      out.write("\t\t\t\t $grid=$dg.datagrid({\r\n");
      out.write("\t\t\t\t\turl : \"investorderAndProducts/investorderAndProductsAction!findInvestPerformanceReportListByDate.action\",\r\n");
      out.write("\t\t\t\t\twidth : 'auto',\r\n");
      out.write("\t\t\t\t\theight : $(this).height()-83,\r\n");
      out.write("\t\t\t\t\tpagination:true,\r\n");
      out.write("\t\t\t\t\trownumbers:true,\r\n");
      out.write("\t\t\t\t\tborder:true,\r\n");
      out.write("\t\t\t\t\tsingleSelect:true,\r\n");
      out.write("\t\t\t\t\tnowrap:true,\r\n");
      out.write("\t\t\t\t\tmultiSort:false,\r\n");
      out.write("\t\t\t\t\tborder:false,\r\n");
      out.write("\t\t\t\t\tfitColumns:true,\r\n");
      out.write("\t\t\t\t\tcolumns : [ [ \r\n");
      out.write("\t\t\t\t\t              {field : 'orgName',  title : '机构名称',    width : parseInt($(this).width()*0.06), align:'center'},\r\n");
      out.write("\t\t\t\t\t              {field : 'investMoneyDay',        title : '当日理财金额（元）',    width : parseInt($(this).width()*0.08), align:'center'},\r\n");
      out.write("\t\t\t\t\t              {field : 'countDay',        title : '当日单数（笔）',    width : parseInt($(this).width()*0.08), align:'center' },\r\n");
      out.write("\t\t\t\t\t              {field : 'investEduMonth',    title : '当月理财金额（元）',    width : parseInt($(this).width()*0.08), align:'center' },\r\n");
      out.write("\t\t\t\t\t              {field : 'investEduMonthOfYear',      title : '当月年化金额（元）', width : parseInt($(this).width()*0.08), align:'center',\r\n");
      out.write("\t\t\t\t\t            \t  formatter:function(value,row){\r\n");
      out.write("\t\t\t\t\t            \t\t  return value.substr(0,value.indexOf('.')+6);\r\n");
      out.write("\t\t\t\t\t            \t  } },\r\n");
      out.write("\t\t\t\t\t              ] ],\r\n");
      out.write("\t\t              toolbar:'#tb',\r\n");
      out.write("\t\t              onClickCell:function(rowIndex, field, value){\r\n");
      out.write("\t\t            \t  $(this).datagrid(\"selectRow\",\"rowIndex\");\r\n");
      out.write("\t\t              }\t\t\t\t\t              \r\n");
      out.write("\t\t\t\t});\t\t\t\t \t\t\t\t \t\t\t\t \r\n");
      out.write("\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t//执行高级查询\r\n");
      out.write("\t\tfunction doSearch(){\t\t\t\r\n");
      out.write("\t\t\tvar orgId=$(\"#orgId\").combotree(\"getValue\");\r\n");
      out.write("\t\t\tvar queryDate=$(\"#queryDate\").datebox(\"getValue\");\r\n");
      out.write("\t\t\t//3、执行高级查询\r\n");
      out.write("\t\t\t$(\"#dg\").datagrid(\"load\",{\r\n");
      out.write("\t\t\t\torgId:orgId,\r\n");
      out.write("\t\t\t\tqueryDate:queryDate\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t//重置条件\r\n");
      out.write("\t\tfunction clearAdvancedQueryConditions(){\r\n");
      out.write("\t\t\t//1、清空高级查询各组件内容\r\n");
      out.write("\t\t\t$(\"#searchForm\").form(\"clear\");\r\n");
      out.write("\t\t\t//2、datagrid重新加载\r\n");
      out.write("\t\t\t$(\"#dg\").datagrid(\"load\",{});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//导出理财业绩报表\r\n");
      out.write("\t\tfunction report_export(){\r\n");
      out.write("\t\t\tvar orgId=$(\"#orgId\").combotree(\"getValue\");\r\n");
      out.write("\t\t\tvar orgName = $(\"#orgId\").combotree(\"getText\");\r\n");
      out.write("\t\t\tvar queryDate=$(\"#queryDate\").datebox(\"getValue\");\r\n");
      out.write("\t\t\t//window.location.href = \"investorderAndProducts/investorderAndProductsAction!doExportExcel.action?orgId=\"+orgId+\"&queryDate=\"+queryDate;\r\n");
      out.write("\t\t\tdownFileByFormPost(\"investorderAndProducts/investorderAndProductsAction!doExportExcel.action\", {\"orgId\":orgId,\"queryDate\":queryDate,\"orgName\":orgName});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t</script>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("      <div data-options=\"region:'center',border : false\">\r\n");
      out.write("      \t<div class=\"position\" style=\"margin-top: 5px;\">您当前所在位置： 业务管理  &gt; 财务监控管理  &gt; 投资客户监管 &gt; 理财业绩报表</div>\r\n");
      out.write("\t\t<!-- 高级查询栏区域 -->\r\n");
      out.write("  \t\t<div class=\"well well-small\" style=\"margin-left: 5px;margin-top: 5px\">\r\n");
      out.write("\t\t\t<form id=\"searchForm\" action=\"\" method=\"post\">\r\n");
      out.write("\t\t\t\t<table cellpadding=\"0\" cellspacing=\"1\" border=\"0\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>机构名称：&nbsp;&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"orgId\" id=\"orgId\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" style=\"width: 170px\"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t<td>查询日期：&nbsp;&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"queryDate\" id=\"queryDate\" class=\"easyui-datebox\" editable=\"true\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"4\" align=\"right\">\r\n");
      out.write("\t\t\t\t\t\t    <a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" plain=\"false\" onclick=\"doSearch();\">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\t    <a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-reload\" plain=\"false\" onclick=\"clearAdvancedQueryConditions()\">条件重置</a>\r\n");
      out.write("\t\t\t\t\t\t</td>\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</tr>\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</form>\t\t\t  \t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t<div id=\"tb\" style=\"padding:2px 0\">\r\n");
      out.write("\t\t\t<a id=\"id4ExportReports\" href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-excel'\" onclick=\"report_export()\">导出业绩报表</a>  \t\t\t\r\n");
      out.write("\t\t</div>\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- 理财业绩数据表格区域 -->\r\n");
      out.write("\t\t<table id=\"dg\" ></table>\t\r\n");
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