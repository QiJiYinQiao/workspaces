/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-10-16 02:29:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.loanOrder.IPC;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class ipcMicrocreditOpinion_jsp extends org.apache.jasper.runtime.HttpJspBase
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
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("<title>信审报告</title>\r\n");
      out.write("<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"expires\" content=\"0\">\r\n");
      out.write("<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../../layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("</head>\r\n");
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
      out.write("\tvar loanOrderId = '");
      out.print(request.getParameter("loanOrderId"));
      out.write("';\r\n");
      out.write("\t");

		// 将传过来的参数转化为utf-8的字符串类型
		String name = request.getParameter("loanerName");
		String loanerName = new  String (name.getBytes("iso8859-1"),"utf-8");
		String pose =  request.getParameter("purpose");
		String purpose = new  String (pose.getBytes("iso8859-1"),"utf-8");
	
      out.write("\r\n");
      out.write("\tvar loanerName = '");
      out.print(loanerName);
      out.write("';\r\n");
      out.write("\tvar loanerIdNo = '");
      out.print(request.getParameter("loanerIdNo"));
      out.write("';\r\n");
      out.write("\tvar purpose = '");
      out.print(purpose);
      out.write("';\r\n");
      out.write("\t\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t$(\"#microcreditOpinionForm\").form(\"load\",\"microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionByOid.action?loanOrderId=\"+loanOrderId);\r\n");
      out.write("\t\t$(\"#microcreditOpinionForm input[name='name']\").val(loanerName);\r\n");
      out.write("\t\t$(\"#microcreditOpinionForm input[name='idNo']\").val(loanerIdNo);\r\n");
      out.write("\t\t$(\"#microcreditOpinionForm input[name='purpose']\").val(purpose);\r\n");
      out.write("\t\t$(\"#microcreditOpinionForm input\").attr(\"readonly\",\"readonly\").css(\"background-color\",\"#EBEBE4\");\r\n");
      out.write("\t\t$(\"#microcreditOpinionForm textarea\").attr(\"readonly\",\"readonly\").css(\"background-color\",\"#F5F5F5\");\r\n");
      out.write("\t\tsetTimeout(loadAudtiWayText,300);\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tfunction loadAudtiWayText(){\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl:\"common/commonAction!findTextArr.action?codeMyid=audit_way\",\r\n");
      out.write("\t\t\ttype:\"post\",\r\n");
      out.write("\t\t\tasync:false,\r\n");
      out.write("\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\tvar auditWay = $(\"#auditWay\").val();\r\n");
      out.write("\t\t\t\t$.each(data,function(i,item){\r\n");
      out.write("\t\t\t\t\tif(item.code==auditWay){\r\n");
      out.write("\t\t\t\t\t\t$(\"#auditWayText\").val(item.text);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("\t<form id=\"microcreditOpinionForm\" method=\"post\">\r\n");
      out.write("\t\t<input name=\"mcbrId\" type=\"hidden\" />\r\n");
      out.write("\t\t<div style=\"text-align:center;\">\r\n");
      out.write("\t\t\t<font size=\"4\" style=\"font-weight: bold;\">微贷业务呈报意见表</font>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<table cellpadding=\"8px;\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t借款人\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input readonly=\"readonly\" class=\"easyui-validatebox easyui-textbox\" name=\"name\"  type=\"text\" />\r\n");
      out.write("\t\t\t\t\t\t<input name=\"loanOrderId\" type=\"hidden\" />\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t身份证号\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input readonly=\"readonly\" class=\"easyui-validatebox easyui-textbox\" name=\"idNo\"  type=\"text\" />\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t咨询服务费\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input readonly=\"readonly\" class=\"easyui-validatebox easyui-textbox\" name=\"counselingRate\"  type=\"text\" />%\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t共同借款人\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input class=\"easyui-validatebox easyui-textbox\" name=\"coborrowerName\"  type=\"text\" />\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t身份证号\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input class=\"easyui-validatebox easyui-textbox\" name=\"coborrowerIdno\"  type=\"text\" />\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t收取方式\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input readonly=\"readonly\" class=\"easyui-validatebox easyui-textbox\" name=\"collectionMthd\"  type=\"text\" />\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t建议金额(元)\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input class=\"easyui-validatebox easyui-textbox\" name=\"adviceLoanAmt\"  type=\"text\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t期限(月)\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input class=\"easyui-validatebox easyui-textbox\" name=\"adviceLoanPeriod\"  type=\"text\" />\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t放款方式\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input readonly=\"readonly\" class=\"easyui-validatebox easyui-textbox\" name=\"loanMthd\"  type=\"text\" />\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t贷款用途\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input class=\"easyui-validatebox easyui-textbox\" name=\"purpose\"  type=\"text\" value=\"\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t利率(年)\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input readonly=\"readonly\" class=\"easyui-validatebox easyui-textbox\" name=\"loanRate\"  type=\"text\" />%\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t还款方式\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input readonly=\"readonly\" class=\"easyui-validatebox easyui-textbox\" name=\"adviceRepayMthd\"  type=\"text\" />\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>信贷方式:</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input id=\"auditWay\" name=\"auditWay\" type=\"hidden\"/>\r\n");
      out.write("\t\t\t\t\t\t<input id=\"auditWayText\" name=\"auditWayText\" class=\"easyui-textbox\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t经办机构/部门\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input readonly=\"readonly\" class=\"easyui-validatebox easyui-textbox\" name=\"\"  type=\"text\" value=\"IPC项目组-保定\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t经办人\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t\tA:<input id=\"useridA\" class=\"easyui-validatebox easyui-textbox\" name=\"operatorA\"  type=\"text\" />&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\tB:<input id=\"useridB\" class=\"easyui-validatebox easyui-textbox\" name=\"operatorB\"  type=\"text\" /> \r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t<div style=\"width:99.5%;height:270px;\">\r\n");
      out.write("\t\t\t\t<div style=\"padding-left:10px;height:30px;\">\r\n");
      out.write("\t\t\t\t\t\t<span style=\"font-weight:bold;\">风险控制措施:&nbsp;&nbsp;&nbsp;</span>\r\n");
      out.write("\t\t\t\t\t\t<input class=\"easyui-textbox\" name=\"riskCtrlMeasures\" />\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<span style=\"padding-left:20px;font-weight:bold;\">具体措施如下:</span>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div style=\"height:230px;overflow:auto;\">\r\n");
      out.write("\t\t\t\t\t\t<textarea class=\"easyui-validatebox easyui-textbox\" name=\"specificMeasures\" style=\"width:99%;height:220px;resize: none;\">\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t</textarea>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div>\r\n");
      out.write("\t\t\t\t<table cellpadding=\"8px;\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th rowspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t业务经办人\r\n");
      out.write("\t\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input class=\"easyui-validatebox easyui-textbox\" name=\"operatorA\" type=\"text\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<th rowspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t后台人员\r\n");
      out.write("\t\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t\t初次上会\r\n");
      out.write("\t\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input class=\"easyui-validatebox easyui-textbox\" name=\"firstMeeting\" type=\"text\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<th rowspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t部门负责人\r\n");
      out.write("\t\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t\t<td rowspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t<input class=\"easyui-validatebox easyui-textbox\" name=\"deptPrincipal\" type=\"text\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input class=\"easyui-validatebox easyui-textbox\" name=\"operatorB\" type=\"text\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t\t补调核实\r\n");
      out.write("\t\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input class=\"easyui-validatebox easyui-textbox\" name=\"verification\" type=\"text\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</form>\r\n");
      out.write("</body>");
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