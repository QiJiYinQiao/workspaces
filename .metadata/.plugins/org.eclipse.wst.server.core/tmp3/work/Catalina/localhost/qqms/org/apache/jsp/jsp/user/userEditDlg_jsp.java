/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.21
 * Generated at: 2015-09-07 03:39:03 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class userEditDlg_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_classes = null;
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery.md5.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/validate.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t$(\"#organizeId\").combotree({\r\n");
      out.write("\t\t\twidth:171,\r\n");
      out.write("\t\t\turl:\"orgz/organizationAction!findOrganizationList.action\",\r\n");
      out.write("\t\t\tidFiled:'id',\r\n");
      out.write("\t\t \ttextFiled:'name',\r\n");
      out.write("\t\t \tparentField:'pid',\r\n");
      out.write("\t\t \tonSelect:function(node){\r\n");
      out.write("\t\t \t\t//$(\"#organizeName\").val(node.text);\r\n");
      out.write("\t\t \t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#form\").form({\r\n");
      out.write("\t\t\turl :\"user/userAction!persistenceUsersDig.action\",\r\n");
      out.write("\t\t\tonSubmit : function() {\r\n");
      out.write("\t\t\t\tparent.$.messager.progress({\r\n");
      out.write("\t\t\t\t\ttitle : '提示',\r\n");
      out.write("\t\t\t\t\ttext : '数据处理中，请稍后....'\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\tvar isValid = $(this).form('validate');\r\n");
      out.write("\t\t\t\tif (!isValid) {\r\n");
      out.write("\t\t\t\t\tparent.$.messager.progress('close');\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t//20150512 前台修改用户信息,MD5操作\r\n");
      out.write("\t\t\t\t//若密码作了修改\r\n");
      out.write("\t\t\t\tvar row = parent.$.modalDialog.openner.datagrid('getSelected');\r\n");
      out.write("\t\t\t\tif(row!=null){\r\n");
      out.write("\t\t\t\t\tif($(\"#password\").val() != row.password){\r\n");
      out.write("\t\t\t\t\t\t$(\"#password\").val($.md5($(\"#password\").val()).toUpperCase());\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$(\"#password\").val($.md5($(\"#password\").val()).toUpperCase());\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tsuccess : function(result) {\r\n");
      out.write("\t\t\t\tparent.$.messager.progress('close');\r\n");
      out.write("\t\t\t\tresult = $.parseJSON(result);\r\n");
      out.write("\t\t\t\tif (result.status) {\r\n");
      out.write("\t\t\t\t\tparent.reload;\r\n");
      out.write("\t\t\t\t\tparent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为role.jsp页面预定义好了\r\n");
      out.write("\t\t\t\t\tparent.$.modalDialog.handler.dialog('close');\r\n");
      out.write("\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\ttitle : result.title,\r\n");
      out.write("\t\t\t\t\t\tmsg : result.message,\r\n");
      out.write("\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\ttitle :  result.title,\r\n");
      out.write("\t\t\t\t\t\tmsg : result.message,\r\n");
      out.write("\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("<style>\n");
      out.write("\t.easyui-textbox{\r\n");
      out.write("\t\theight: 18px;\r\n");
      out.write("\t\twidth: 170px;\r\n");
      out.write("\t\tline-height: 16px;\r\n");
      out.write("\t    /*border-radius: 3px 3px 3px 3px;*/\r\n");
      out.write("\t    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;\r\n");
      out.write("\t    transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\ttextarea:focus, input[type=\"text\"]:focus{\r\n");
      out.write("\t    border-color: rgba(82, 168, 236, 0.8);\r\n");
      out.write("\t    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(82, 168, 236, 0.6);\r\n");
      out.write("\t    outline: 0 none;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\ttable {\r\n");
      out.write("\t    background-color: transparent;\r\n");
      out.write("\t    border-collapse: collapse;\r\n");
      out.write("\t    border-spacing: 0;\r\n");
      out.write("\t    max-width: 100%;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfieldset {\r\n");
      out.write("\t    border: 0 none;\r\n");
      out.write("\t    margin: 0;\r\n");
      out.write("\t    padding: 0;\r\n");
      out.write("\t}\r\n");
      out.write("\tlegend {\r\n");
      out.write("\t    -moz-border-bottom-colors: none;\r\n");
      out.write("\t    -moz-border-left-colors: none;\r\n");
      out.write("\t    -moz-border-right-colors: none;\r\n");
      out.write("\t    -moz-border-top-colors: none;\r\n");
      out.write("\t    border-color: #E5E5E5;\r\n");
      out.write("\t    border-image: none;\r\n");
      out.write("\t    border-style: none none solid;\r\n");
      out.write("\t    border-width: 0 0 1px;\r\n");
      out.write("\t    color: #999999;\r\n");
      out.write("\t    line-height: 20px;\r\n");
      out.write("\t    display: block;\r\n");
      out.write("\t    margin-bottom: 10px;\r\n");
      out.write("\t    padding: 0;\r\n");
      out.write("\t    width: 100%;\r\n");
      out.write("\t}\r\n");
      out.write("\tinput, textarea {\r\n");
      out.write("\t    font-weight: normal;\r\n");
      out.write("\t}\r\n");
      out.write("\ttable ,th,td{\r\n");
      out.write("\t\ttext-align:left;\r\n");
      out.write("\t\tpadding: 6px;\r\n");
      out.write("\t}\r\n");
      out.write("</style>\r\n");
      out.write("<div class=\"easyui-layout\" data-options=\"fit:true,border:false\">\r\n");
      out.write("\t<div data-options=\"region:'center',border:false\" title=\"\" style=\"overflow: hidden;padding: 10px;\">\r\n");
      out.write("\t\t<form id=\"form\" method=\"post\">\r\n");
      out.write("\t\t\t<fieldset>\r\n");
      out.write("\t\t\t\t<legend><img src=\"extend/fromedit.png\" style=\"margin-bottom: -3px;\"/> 用户编辑</legend>\r\n");
      out.write("\t\t\t\t<input name=\"userId\" id=\"userId\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t\t<input name=\"created\" id=\"created\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t\t<input name=\"creater\" id=\"creater\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t\t<input name=\"status\" id=\"status\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t\t <table>\r\n");
      out.write("\t\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t    <td style=\"text-align: right;\">用户编码:</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"myid\" id=\"myid\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'length[0,25]'\"/></td>\r\n");
      out.write("\t\t\t\t\t\t<td style=\"text-align: right;\">用户账号:</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"account\"  class=\"easyui-textbox easyui-validatebox\" id=\"account\" data-options=\"required:true,validType:'length[0,25]'\"/></td>\r\n");
      out.write("\t\t\t\t\t </tr>\r\n");
      out.write("\t\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t    <td style=\"text-align: right;\">用户名:</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"name\" id=\"name\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'length[0,25]'\"/></td>\r\n");
      out.write("\t\t\t\t\t\t<td style=\"text-align: right;\">用户密码:</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"password\" name=\"password\" type=\"password\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'length[0,64]'\"/></td>\r\n");
      out.write("\t\t\t\t\t </tr>\r\n");
      out.write("\t\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t    <td style=\"text-align: right;\">重复密码:</td>\r\n");
      out.write("\t\t\t\t\t    <td>\r\n");
      out.write("\t\t\t\t\t      <input id=\"rpwd\" name=\"rpwd\" type=\"password\" class=\"easyui-textbox easyui-validatebox\" required=\"required\" validType=\"same['password']\"/>  \r\n");
      out.write("\t\t\t\t\t    </td>\r\n");
      out.write("\t\t\t\t\t    <td style=\"text-align: right;\">邮箱:</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"email\" name=\"email\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'email'\"/></td>\r\n");
      out.write("\t\t\t\t\t </tr>\r\n");
      out.write("\t\t\t\t\t  <tr>\r\n");
      out.write("\t\t\t\t\t\t<td style=\"text-align: right;\">电话:</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"tel\" name=\"tel\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'mobile'\"/></td>\r\n");
      out.write("\t\t\t\t\t    <td style=\"text-align: right;\">组织部门:</td>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"3\"><input id=\"organizeId\" name=\"organizeId\" type=\"text\" class=\"easyui-textbox easyui-validatebox\"/></td>\r\n");
      out.write("\t\t\t\t\t </tr>\r\n");
      out.write("\t\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t\t<td style=\"text-align: right;\">描述:</td>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"3\"><textarea class=\"easyui-textbox\" name=\"description\"  style=\"width: 415px;height: 100px;\"></textarea></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t </table>\r\n");
      out.write("\t\t\t</fieldset>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
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