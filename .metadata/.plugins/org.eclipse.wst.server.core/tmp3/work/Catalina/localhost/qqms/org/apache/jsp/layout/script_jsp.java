/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.21
 * Generated at: 2015-07-14 02:09:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class script_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\t\r\n");
      out.write("\t");

		String easyuiThemeName="metro";
		Cookie cookies[] =request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie : cookies){
				if (cookie.getName().equals("cookiesColor")) {
					easyuiThemeName = cookie.getValue();
					break;
				}
			}
		}
	
      out.write("\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"themes/");
      out.print(easyuiThemeName );
      out.write("/easyui.css\">\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/xheditor/jquery-1.8.0.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/xheditor/xheditor-1.1.14-zh-cn.min.js\" charset=\"utf-8\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"themes/icon.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/common.css\">\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jqueryUtil.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/json2.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/FusionCharts/FusionCharts.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/FusionCharts/FusionCharts.jqueryplugin.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/FusionCharts/FusionChartsExportComponent.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t$(function(){\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("               /* $('#tt').tabs({\r\n");
      out.write("                    onLoad:function(panel){\r\n");
      out.write("                        var plugin = panel.panel('options').title;\r\n");
      out.write("                    },\r\n");
      out.write("                    tools:[{\r\n");
      out.write("                            iconCls:'icon-color',\r\n");
      out.write("                            handler:function(){\r\n");
      out.write("                                //window.open(\"http://easyui.btboys.com/reply.html\");\r\n");
      out.write("                              \r\n");
      out.write("                               $('#mm').menu({   \r\n");
      out.write("\t\t\t\t\t\t\t\t    onClick:function(item){   \r\n");
      out.write("                            \t   \t\t\tvar cookiesColor1=jqueryUtil.cookies.get(\"cookiesColor\");\r\n");
      out.write("                            \t   \t\tif(cookiesColor1!=item.id){\r\n");
      out.write("\t\t\t\t\t\t\t\t        \tjqueryUtil.cookies.set(\"cookiesColor\",item.id,30);\r\n");
      out.write("\t\t\t\t\t\t\t\t        \tjqueryUtil.chgSkin(item.id,cookiesColor1);\r\n");
      out.write("                            \t   \t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t    }   \r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("                                $('#mm').menu('show', {   \r\n");
      out.write("\t\t\t\t\t\t\t\t  left: 1222,   \r\n");
      out.write("\t\t\t\t\t\t\t\t  top: 90   \r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("                            }\r\n");
      out.write("                        }],\r\n");
      out.write("\t\t\t\t\t\textractor:function(data){\r\n");
      out.write("\t\t\t\t\t\t\tvar tmp = $('<div></div>').html(data);\r\n");
      out.write("\t\t\t\t\t\t\tdata = tmp.find('#content').html();\r\n");
      out.write("\t\t\t\t\t\t\ttmp.remove();\r\n");
      out.write("\t\t\t\t\t\t\treturn data;\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("                });\r\n");
      out.write("\t\t\t\ttry{\r\n");
      out.write("\t\t\t\t　　top.location.hostname;\r\n");
      out.write("\t\t\t\t　　if (top.location.hostname != window.location.hostname) {\r\n");
      out.write("\t\t\t\t　　　　top.location.href =window.location.href;\r\n");
      out.write("\t\t\t\t　　}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tcatch(e){\r\n");
      out.write("\t\t\t\t　　top.location.href = window.location.href;\r\n");
      out.write("\t\t\t\t}*/\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("            });\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("\t\tbody {\r\n");
      out.write("\t\t    font-family:helvetica,tahoma,verdana,sans-serif;\r\n");
      out.write("\t\t    font-size:13px;\r\n");
      out.write("\t\t    margin:0px 0px 0px 0px;\r\n");
      out.write("\t\t    padding:0px 0px 0px 0px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</style>");
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
