/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-08-25 03:07:07 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.investorSupervision;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class showDueInvestorDetails_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("\t   a{\r\n");
      out.write("\t\t\ttext-decoration:none;\r\n");
      out.write("\t\t} \r\n");
      out.write("\t</style>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\t//声明一个全局变量row\r\n");
      out.write("\t\t\t//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。\r\n");
      out.write("\t\t\tvar row;\r\n");
      out.write("\t\t\tvar $dg;\r\n");
      out.write("\t\t\tvar $grid;\r\n");
      out.write("\t\t\tvar excelurl = \"investorSupervision/investorSupervisionAction!doExportExcel.action\";\r\n");
      out.write("\t\t\t$(function() {\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//初始化高级查询区域的组件\r\n");
      out.write("\t\t\t\t//初始化理财产品列表\t\t\t\t\r\n");
      out.write("\t\t\t\t$(\"#productCombobox\").combobox({\r\n");
      out.write("\t\t\t\t    url:'investProduct/investProductAction!getAllInvestProducts4Combobox.action',    \r\n");
      out.write("\t\t\t\t    valueField:'code',    \r\n");
      out.write("\t\t\t\t    textField:'text' \t\t\t\t\t\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t//加载投资客户的数据\r\n");
      out.write("\t\t\t\t $dg = $(\"#dg\");\r\n");
      out.write("\t\t\t\t $grid=$dg.datagrid({\r\n");
      out.write("\t\t\t\t\turl : \"investorSupervision/investorSupervisionAction!findDueInvestorInfoDetails.action\",\r\n");
      out.write("\t\t\t\t\twidth : 'auto',\r\n");
      out.write("\t\t\t\t\theight : $(this).height()-165,\r\n");
      out.write("\t\t\t\t\tpagination:true,\r\n");
      out.write("\t\t\t\t\trownumbers:true,\r\n");
      out.write("\t\t\t\t\tborder:false,\r\n");
      out.write("\t\t\t\t\tsingleSelect:true,\r\n");
      out.write("\t\t\t\t\tnowrap:true,\r\n");
      out.write("\t\t\t\t\tmultiSort:false,\r\n");
      out.write("\t\t\t\t\tcolumns : [ [ \r\n");
      out.write("\t\t\t\t\t              {field : 'contractNo',  title : '合同编号',    width : parseInt($(this).width()*0.06), align:'center', \r\n");
      out.write("\t\t\t\t\t            \t  formatter: function(value, row, index){\r\n");
      out.write("\t\t\t\t\t            \t\t  if(row.contractNo == null || row.contractNo == \"\"){\t\t\t\t\t            \t\t\t  \r\n");
      out.write("\t\t\t\t\t\t            \t\treturn \"\";\t\t\t\t\t            \t\t\t  \t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t            \t\t  }else{\t\t\t\t\t            \t\t\t  \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t  \treturn \"<a href=\\\"javascript:void(0)\\\" onclick=\\\"showInvestContractDetailsView(\"+index+\")\\\">\"+value+\"</a>\";\t\t\t\t\t            \t\t\t \r\n");
      out.write("\t\t\t\t\t            \t\t  }\r\n");
      out.write("\t\t\t\t\t            \t  }\t\t\r\n");
      out.write("\t\t\t\t\t              },\r\n");
      out.write("\t\t\t\t\t              {field : 'investorName',        title : '客户姓名',    width : parseInt($(this).width()*0.05), align:'center',\r\n");
      out.write("\t\t\t\t\t            \t  formatter: function(value, row, index){\r\n");
      out.write("\t\t\t\t\t            \t\t  return \"<a href=\\\"javascript:void(0)\\\" onclick=\\\"showInvestorView(\"+index+\")\\\">\"+value+\"</a>\";\r\n");
      out.write("\t\t\t\t\t            \t  }\t\t\t\t\t            \t  \r\n");
      out.write("\t\t\t\t\t              },\r\n");
      out.write("\t\t\t\t\t              {field : 'idCrad',        title : '身份证号码',    width : parseInt($(this).width()*0.08), align:'center' },\r\n");
      out.write("\t\t\t\t\t              {field : 'mobTel',    title : '联系方式',    width : parseInt($(this).width()*0.06), align:'center' },\r\n");
      out.write("\t\t\t\t\t              {field : 'bankName',      title : '开户行名称', width : parseInt($(this).width()*0.07), align:'center' },\r\n");
      out.write("\t\t\t\t\t              {field : 'actNo',    title : '开户行账号',    width : parseInt($(this).width()*0.08), align:'center' },\r\n");
      out.write("\t\t\t\t\t              {field : 'prodName',      title : '理财产品',   width : parseInt($(this).width()*0.05), align:'center',\r\n");
      out.write("\t\t\t\t\t            \t  formatter: function(value, row, index){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t      var result =\"<a href='javascript:void(0);' onclick='showInvestorAndInvestProductsDetailsView(\\\"\"+row.investOrderId+\"\\\");'>详情</a>\";\r\n");
      out.write("\t\t\t\t\t      \t\t\t\t  return result;\t\t\t\t\t            \t\t  \r\n");
      out.write("\t\t\t\t\t            \t  }\t\t\t\t\t            \t\t  \r\n");
      out.write("\t\t\t\t\t              },\r\n");
      out.write("\t\t\t\t\t              {field : 'investEdu',      title : '理财金额（元）',   width : parseInt($(this).width()*0.06), align:'center' },\r\n");
      out.write("\t\t\t\t\t              {field : 'totalInterest',  title : '应付利息（元）',       width : parseInt($(this).width()*0.06), align:'center' },\r\n");
      out.write("\t\t\t\t\t              {field : 'totalAmountDue', title : '应付本息（元）',       width : parseInt($(this).width()*0.06), align:'center' },\r\n");
      out.write("\t\t\t\t\t              {field : 'interestDate',   title : '计息日期',       width : parseInt($(this).width()*0.08), align:'center' },\r\n");
      out.write("\t\t\t\t\t              {field : 'endDate',        title : '到期日期',       width : parseInt($(this).width()*0.08), align:'center' }\r\n");
      out.write("\t\t\t\t\t              ] ],\r\n");
      out.write("\t\t              toolbar:'#tb',\r\n");
      out.write("\t\t              onClickCell:function(rowIndex, field, value){\r\n");
      out.write("\t\t            \t  $(this).datagrid(\"selectRow\",\"rowIndex\");\r\n");
      out.write("\t\t              }\t\t\t\t\t              \r\n");
      out.write("\t\t\t\t});\t\t\t\t \t\t\t\t \t\t\t\t \r\n");
      out.write("\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t// 根据索引获取行的基本信息的函数\r\n");
      out.write("\t\tfunction getRowData (index) {\r\n");
      out.write("\t        if (!$.isNumeric(index) || index < 0) { return undefined; }\r\n");
      out.write("\t        var rows = $dg.datagrid(\"getRows\");\r\n");
      out.write("\t        return rows[index];\r\n");
      out.write("\t    }\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 查看该投资人的\"合同详情\"\r\n");
      out.write("\t\tfunction showInvestContractDetailsView(index){\r\n");
      out.write("\t\t\tvar row = this.getRowData(index);\r\n");
      out.write("\t\t\tconsole.info(\"合同详情 : \"+row.investOrderId)\r\n");
      out.write("\t\t\t$('#contractInfoDialog').dialog({    \r\n");
      out.write("\t\t\t    title: '合同详情',    \r\n");
      out.write("\t\t\t    width: 700,    \t\r\n");
      out.write("\t\t\t    height: 600,    \r\n");
      out.write("\t\t\t    closed: false,\r\n");
      out.write("\t\t\t    closable: true,\r\n");
      out.write("\t\t\t    cache: false,    \r\n");
      out.write("\t\t\t    href: 'investOrder/investOrderAction!findInvestorOrderContractDetails.action?investOrderId='+row.investOrderId,    \r\n");
      out.write("\t\t\t    modal: true   \r\n");
      out.write("\t\t\t});\t\t\t\t\r\n");
      out.write("\t\t}\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//查看投资人详细信息\r\n");
      out.write("\t\tfunction showInvestorView(index){\r\n");
      out.write("\t\t\tvar rows = $(\"#dg\").datagrid(\"getRows\");\r\n");
      out.write("\t\t\tvar row = rows[index];\r\n");
      out.write("\t\t\t$('#investorView').dialog({    \r\n");
      out.write("\t\t\t\t\t    title: '投资客户详情',    \r\n");
      out.write("\t\t\t\t\t    width: 800,    \r\n");
      out.write("\t\t\t\t\t    height: 450,    \r\n");
      out.write("\t\t\t\t\t    closed: false,    \r\n");
      out.write("\t\t\t\t\t    cache: false,    \r\n");
      out.write("\t\t\t\t\t    href: 'investor/investorAction!findInvestorByInvestorId.action?investorId='+row.investorId,    \r\n");
      out.write("\t\t\t\t\t    modal: true   \r\n");
      out.write("\t\t\t\t\t}); \r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//查看该投资人的理财产品详情界面\r\n");
      out.write("\t\tfunction showInvestorAndInvestProductsDetailsView(investOrderId){\r\n");
      out.write("\t\t\t$('#investorAndInvestProductsDialog').dialog({    \r\n");
      out.write("\t\t\t    title: '理财产品详情',    \r\n");
      out.write("\t\t\t    width: 1000,    \t\r\n");
      out.write("\t\t\t    height: 250,    \r\n");
      out.write("\t\t\t    closed: false,\r\n");
      out.write("\t\t\t    closable: true,\r\n");
      out.write("\t\t\t    cache: false,    \r\n");
      out.write("\t\t\t    href: 'investOrder/investOrderAction!findInvestorAndInvestProductsDetails.action?investOrderId='+investOrderId,    \r\n");
      out.write("\t\t\t    modal: true   \r\n");
      out.write("\t\t\t});\t\t\t\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//执行高级查询\r\n");
      out.write("\t\tfunction doSearch(){\t\t\t\r\n");
      out.write("\t\t\t//1、收集高级查询数据。\r\n");
      out.write("\t\t\tvar contractNo = $(\"#contractNo\").val();        //获取合同编号\r\n");
      out.write("\t\t\tvar investorName = $(\"#investorName\").val();    //获取客户姓名\r\n");
      out.write("\t\t\tvar prodId = $(\"#productCombobox\").combobox('getValue');  //获取理财产品的ID\r\n");
      out.write("\t\t\tvar interestDate01 = $(\"#interestDate01\").datebox('getValue');   //获取计息日01\r\n");
      out.write("\t\t\tvar interestDate02 = $(\"#interestDate02\").datebox('getValue');   //获取计息日02\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tvar endDate01 = $(\"#endDate01\").datebox('getValue');       //获取到期日期01\r\n");
      out.write("\t\t\tvar endDate02 = $(\"#endDate02\").datebox('getValue');       //获取到期日期02\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//2、对收集的数据进行校验\r\n");
      out.write("\t\t\tif(interestDate01 > interestDate02 ){\r\n");
      out.write("\t\t\t\t$.messager.alert(\"警告\", \"计息日期输入错误，请选择正确的日期\", 'info');\r\n");
      out.write("\t\t\t\t$(\"#interestDate01\").datebox('clear');\r\n");
      out.write("\t\t\t\t$(\"#interestDate02\").datebox('clear');\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif(endDate01 > endDate02 ){\r\n");
      out.write("\t\t\t\t$.messager.alert(\"警告\", \"到期日期输入错误，请选择正确的日期\", 'info');\r\n");
      out.write("\t\t\t\t$(\"#endDate01\").datebox('clear');\r\n");
      out.write("\t\t\t\t$(\"#endDate02\").datebox('clear');\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\t\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t//3、执行高级查询\r\n");
      out.write("\t\t\t$(\"#dg\").datagrid(\"load\",{\r\n");
      out.write("\t\t\t\tcontractNo : contractNo,\r\n");
      out.write("\t\t\t\tinvestorName : investorName,\r\n");
      out.write("\t\t\t\tprodId : prodId,\r\n");
      out.write("\t\t\t\tinterestDate01 : interestDate01,\r\n");
      out.write("\t\t\t\tinterestDate02 : interestDate02,\r\n");
      out.write("\t\t\t\tendDate01 : endDate01,\r\n");
      out.write("\t\t\t\tendDate02 : endDate02\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t//设置导出报表的url\r\n");
      out.write("\t\t\texcelurl = \"investorSupervision/investorSupervisionAction!doExportExcel.action?contractNo=\"+contractNo+\"&investorName=\"+ encodeURI(encodeURI(investorName))+\"&prodId=\"+prodId+\"&interestDate01=\"+interestDate01+\"&interestDate02=\"+interestDate02+\"&endDate01=\"+endDate01+\"&endDate02=\"+endDate02;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t//充值条件\r\n");
      out.write("\t\tfunction clearAdvancedQueryConditions(){\r\n");
      out.write("\t\t\t//1、清空高级查询各组件内容\r\n");
      out.write("\t\t\t$(\"#searchForm\").form(\"clear\");\r\n");
      out.write("\t\t\t//2、datagrid重新加载\r\n");
      out.write("\t\t\t$(\"#dg\").datagrid(\"load\",{});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//导出报表\r\n");
      out.write("\t\tfunction toExportExcel(){\r\n");
      out.write("\t\t\twindow.location.href = excelurl;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t</script>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("      <div data-options=\"region:'center',border : false\">\r\n");
      out.write("      \t<div class=\"position\" style=\"margin-top: 5px;\">您当前所在位置： 业务管理  &gt; 财务监控管理  &gt; 投资客户监管 &gt; 到期客户明细</div>\r\n");
      out.write("\t\t<!-- 高级查询栏区域 -->\r\n");
      out.write("  \t\t<div class=\"well well-small\" style=\"margin-left: 5px;margin-top: 5px\">\r\n");
      out.write("\t\t\t<form id=\"searchForm\" action=\"\" method=\"post\">\r\n");
      out.write("\t\t\t\t<table cellpadding=\"0\" cellspacing=\"1\" border=\"0\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>合同编号：&nbsp;&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"contractNo\" id=\"contractNo\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" style=\"width: 170px\"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t<td>客户姓名：&nbsp;&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"investorName\" id=\"investorName\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" style=\"width: 170px\"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t<td>理财产品：&nbsp;&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"prodId\" id=\"productCombobox\" class=\"easyui-combobox\" style=\"width: 170px\"/></td>\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>计息日期：&nbsp;&nbsp;</td>\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<td>从&nbsp;&nbsp;<input name=\"interestDate01\" id=\"interestDate01\" class=\"easyui-datebox\" editable=\"true\"/>\r\n");
      out.write("\t\t\t\t\t\t    &nbsp;到&nbsp;<input name=\"interestDate02\" id=\"interestDate02\" class=\"easyui-datebox\" editable=\"true\"/></td>\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<tr>\t\r\n");
      out.write("\t\t\t\t\t\t<td>到期日期：&nbsp;&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t<td>从&nbsp;&nbsp;<input name=\"endDate\" id=\"endDate01\" class=\"easyui-datebox\" editable=\"true\"/>\r\n");
      out.write("\t\t\t\t\t\t    &nbsp;到&nbsp;<input name=\"endDate\" id=\"endDate02\" class=\"easyui-datebox\" editable=\"true\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"4\" align=\"right\">\r\n");
      out.write("\t\t\t\t\t\t    <a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" plain=\"false\" onclick=\"doSearch();\">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\t    <a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-reload\" plain=\"false\" onclick=\"clearAdvancedQueryConditions()\">条件重置</a>\r\n");
      out.write("\t\t\t\t\t\t</td>\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</tr>\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</form><br/>\r\n");
      out.write("\t\t\t注：不输入任何查询条件，默认查询所有<b><font color=\"red\">合同到期日期</font></b>为<b><font color=\"red\">当月</font></b>的客户投资信息\t\t\t  \t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t<div id=\"tb\">\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-excel\" onclick=\"toExportExcel()\">导出excel</a>  \t\t\t\r\n");
      out.write("\t\t</div>\t\t\r\n");
      out.write("\t\t<!-- 到期客户明细数据表格区域 -->\r\n");
      out.write("\t\t<table id=\"dg\" ></table>\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- 合同信息对话框区域 -->\r\n");
      out.write("\t\t<div id=\"contractInfoDialog\"></div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 投资客户数据对话框区域 -->\r\n");
      out.write("\t\t<div id=\"investorView\"></div>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- 理财产品详情对话框区域 -->\r\n");
      out.write("\t\t<div id=\"investorAndInvestProductsDialog\"></div>\r\n");
      out.write("\r\n");
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