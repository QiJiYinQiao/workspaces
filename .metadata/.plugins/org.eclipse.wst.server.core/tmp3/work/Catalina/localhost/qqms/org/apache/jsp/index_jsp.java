/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.21
 * Generated at: 2015-07-14 02:09:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\r');
      out.write('\n');

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    <title>欢迎</title>\r\n");
      out.write("    <meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \r\n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t$(function(){\r\n");
      out.write("\t\t\tinitMenu();\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif (jqueryUtil.isLessThanIe8()) {\r\n");
      out.write("\t\t\t\t$.messager.show({\r\n");
      out.write("\t\t\t\t\ttitle : '警告',\r\n");
      out.write("\t\t\t\t\tmsg : '您使用的浏览器版本太低！<br/>建议您使用谷歌浏览器来获得更快的页面响应效果！',\r\n");
      out.write("\t\t\t\t\ttimeout : 1000 * 30\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction initMenu(){\r\n");
      out.write("\t\t\tvar $ma=$(\"#menuAccordion\");\r\n");
      out.write("\t\t\t$ma.accordion({animate:true,fit:true,border:false});\r\n");
      out.write("\t\t\t$.post(\"systemAction!findAllFunctionList.action\", {userName:\"1\"}, function(rsp) {\r\n");
      out.write("\t\t\t\t$.each(rsp,function(i,e){\r\n");
      out.write("\t\t\t\t\tvar menulist =\"<div class=\\\"well well-small\\\">\";\r\n");
      out.write("\t\t\t\t\tif(e.child && e.child.length>0){\r\n");
      out.write("\t\t\t\t\t\t$.each(e.child,function(ci,ce){\r\n");
      out.write("\t\t\t\t\t\t\tvar effort=ce.name+\"||\"+ce.iconCls+\"||\"+ce.url;\r\n");
      out.write("\t\t\t\t\t\t\tmenulist+=\"<a href=\\\"javascript:void(0);\\\" class=\\\"easyui-linkbutton\\\" data-options=\\\"plain:true,iconCls:'\"+ce.iconCls+\"'\\\" onclick=\\\"addTab('\"+effort+\"');\\\">\"+ce.name+\"</a><br/>\";\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tmenulist+=\"</div>\";\r\n");
      out.write("\t\t\t\t\t$ma.accordion('add', {\r\n");
      out.write("\t\t\t            title: e.name,\r\n");
      out.write("\t\t\t            content: menulist,\r\n");
      out.write("\t\t\t\t\t\tborder:false,\r\n");
      out.write("\t\t\t            iconCls: e.iconCls,\r\n");
      out.write("\t\t\t            selected: false\r\n");
      out.write("\t\t\t        });\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}, \"JSON\").error(function() {\r\n");
      out.write("\t\t\t\t$.messager.alert(\"提示\", \"获取菜单出错,请重新登陆!\");\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("\t#menuAccordion a.l-btn span span.l-btn-text {\r\n");
      out.write("\t    display: inline-block;\r\n");
      out.write("\t    height: 14px;\r\n");
      out.write("\t    line-height: 14px;\r\n");
      out.write("\t    margin: 0px 0px 0px 10px;\r\n");
      out.write("\t    padding: 0px 0px 0px 10px;\r\n");
      out.write("\t    vertical-align: baseline;\r\n");
      out.write("\t    width: 128px;\r\n");
      out.write("\t}\r\n");
      out.write("\t#menuAccordion \ta.l-btn span span.l-btn-icon-left {\r\n");
      out.write("\t    background-position: left center;\r\n");
      out.write("\t    padding: 0px 0px 0px 20px;\r\n");
      out.write("\t}\r\n");
      out.write("\t#menuAccordion .panel-body {\r\n");
      out.write("\t\tpadding:5px;\r\n");
      out.write("\t}\r\n");
      out.write("\t#menuAccordion span:focus{\r\n");
      out.write("\t\toutline: none;\r\n");
      out.write("\t}\r\n");
      out.write("\t</style>\r\n");
      out.write("  </head>\r\n");
      out.write(" <body class=\"easyui-layout\">\r\n");
      out.write("\t<div data-options=\"region:'north',border:false\" style=\"height:40px;background:#EEE;padding:10px;overflow: hidden;\"  href=\"layout/north.jsp\"></div>\r\n");
      out.write("\t<div data-options=\"region:'west',split:true,title:'主要菜单'\" style=\"width:200px;\">\r\n");
      out.write("\t\t\t<div id=\"menuAccordion\"></div>\r\n");
      out.write("\t</div> \r\n");
      out.write("\t<div data-options=\"region:'south',border:false\" style=\"height:25px;background:#EEE;padding:5px;\" href=\"layout/south.jsp\"></div>\r\n");
      out.write("\t<div data-options=\"region:'center',plain:true,title:'欢迎来到钱钱金融 客户关系管理系统'\" style=\"overflow: hidden;\"  href=\"layout/center.jsp\"></div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
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
