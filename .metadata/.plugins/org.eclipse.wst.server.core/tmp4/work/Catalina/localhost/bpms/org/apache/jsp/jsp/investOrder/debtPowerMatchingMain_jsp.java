/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-09-18 03:06:58 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.investOrder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class debtPowerMatchingMain_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("<title>债权匹配</title>\r\n");
      out.write("</head>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var repayMethodArr = jqueryUtil.getTextArr(\"repay_method\");//还款方式\r\n");
      out.write("var mortgageStatusArr = jqueryUtil.getTextArr(\"mortgage_status\");//房屋居住情况\r\n");
      out.write("var row;\r\n");
      out.write("$(function(){\r\n");
      out.write("\tcreateLoanOrderGrid();\r\n");
      out.write("});\r\n");
      out.write("//贷款订单列表\r\n");
      out.write("function createLoanOrderGrid(){\r\n");
      out.write("\t$(\"#loanOrderGrid\").datagrid({\r\n");
      out.write("\t\turl:'loanOrder/loanOrderAction!findLoanOrderListByOrderStatus.action',\r\n");
      out.write("\t\twidth: 'auto',\r\n");
      out.write("\t\theight: $(this).height()-40,\r\n");
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
      out.write("\t\t      {field : 'name',title : '客户姓名',width:100,align:'center'},\r\n");
      out.write("              {field : 'idNo',title : '身份证号',width:180,align:'center'},\r\n");
      out.write("              {field : 'age',title : '年龄',width:80,align:'center'},\r\n");
      out.write("              {field : 'annualSalary',title : '年收入(单位:万)',width:120,align:'center'},\r\n");
      out.write("              {field : 'mortgageStatus',title : '居住情况',width:150,align:'center',formatter:function(value,row,index){\r\n");
      out.write("            \t  return jqueryUtil.showText(value,mortgageStatusArr);\r\n");
      out.write("              }},\r\n");
      out.write("              {field : 'loanAmount',title : '申请贷款额度(单位:万)',width:150,align:'center'},\r\n");
      out.write("              {field : 'loanMin',title : '最低接受额度(单位:万)',width : 150,align:'center'},\r\n");
      out.write("              {field : 'loanPeriod',title : '申请贷款期限(月)',width:150,align:'center'},\r\n");
      out.write("              {field : 'repayMethod',title : '还款方式',width:150,align:'center',formatter:function(value,row,index){\r\n");
      out.write("            \t  return jqueryUtil.showText(value,repayMethodArr);\r\n");
      out.write("              }},\r\n");
      out.write("              {field : 'purpose',title : '贷款用途',width:250,align:'center'},\r\n");
      out.write("              {field : 'caozuo',title : '操作',width:150,align:'center',formatter:function(value,row,index){\r\n");
      out.write("            \t  return \"<a href=\\\"javascript:void(0)\\\" onclick=\\\"showDebtPowerMatchingDialog(\"+index+\");\\\">债权匹配</a>\";\r\n");
      out.write("              }}\r\n");
      out.write("\t   ]]\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//债权匹配弹框\r\n");
      out.write("function showDebtPowerMatchingDialog(index){\r\n");
      out.write("\tvar rows = $(\"#loanOrderGrid\").datagrid(\"getRows\");\r\n");
      out.write("\trow = rows[index];\r\n");
      out.write("\t$('#debtPowerMatchingDialog').dialog({    \r\n");
      out.write("\t    title: '债权匹配',    \r\n");
      out.write("\t    width: 1000,    \r\n");
      out.write("\t    height: 600,    \r\n");
      out.write("\t    closed: false,    \r\n");
      out.write("\t    cache: false,    \r\n");
      out.write("\t    href: 'debtMatching/debtMatchingAction!findLoanOrderByLoanOrderId.action?loanOrderId='+row.loanOrderId,    \r\n");
      out.write("\t    modal: true   \r\n");
      out.write("\t}); \r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"position\" style=\"margin-top: 5px;\">您当前所在位置： 业务管理  &gt; 财富业务管理  &gt; 债权匹配 </div>\r\n");
      out.write("   <table id=\"loanOrderGrid\"></table>\r\n");
      out.write("   <!-- 债权匹配弹框 -->\r\n");
      out.write("   <div id=\"debtPowerMatchingDialog\"></div>\r\n");
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