/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-07-30 06:32:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\r');
      out.write('\n');

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	/*response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
	response.flushBuffer();*/

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    <title>欢迎登陆</title>\r\n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \r\n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      out.write("\t<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"extend/iconkey.ico\" media=\"screen\" />\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/xheditor/jquery-1.8.0.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery.cookie.js\"></script>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/zice.style.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tipsy.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/icon.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/buttons.css\">\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/iphone.check.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery-jrumble.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery.tipsy.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery.md5.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/login.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\tif(top!=self){\r\n");
      out.write("\t\t\tif(top.location != self.location)\r\n");
      out.write("\t\t\t top.location=self.location; \r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t <style type=\"text/css\">\r\n");
      out.write("html {\r\n");
      out.write("\tbackground-image: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("label.iPhoneCheckLabelOn span {\r\n");
      out.write("\tpadding-left: 0px\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#versionBar {\r\n");
      out.write("\tbackground-color: #212121;\r\n");
      out.write("\tposition: fixed;\r\n");
      out.write("\twidth: 100%;\r\n");
      out.write("\theight: 35px;\r\n");
      out.write("\tbottom: 0;\r\n");
      out.write("\tleft: 0;\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\tline-height: 35px;\r\n");
      out.write("\tz-index: 11;\r\n");
      out.write("\t-webkit-box-shadow: black 0px 10px 10px -10px inset;\r\n");
      out.write("\t-moz-box-shadow: black 0px 10px 10px -10px inset;\r\n");
      out.write("\tbox-shadow: black 0px 10px 10px -10px inset;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".copyright {\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\tfont-size: 10px;\r\n");
      out.write("\tcolor: #CCC;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".copyright a {\r\n");
      out.write("\tcolor: #A31F1A;\r\n");
      out.write("\ttext-decoration: none\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/*update-begin--Author:tanghong  Date:20130419 for：【是否】按钮错位*/\r\n");
      out.write(".on_off_checkbox{\r\n");
      out.write("\twidth:0px;\r\n");
      out.write("}\r\n");
      out.write("/*update-end--Author:tanghong  Date:20130419 for：【是否】按钮错位*/\r\n");
      out.write("#login .logo {\r\n");
      out.write("\twidth: 500px;\r\n");
      out.write("\theight: 51px;\r\n");
      out.write("}\r\n");
      out.write("#cap{\r\n");
      out.write("margin-left: 88px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("\t<div id=\"alertMessage\"></div>\r\n");
      out.write("\t<div id=\"successLogin\"></div>\r\n");
      out.write("\t<div class=\"text_success\">\r\n");
      out.write("\t\t<img src=\"extend/loader_green.gif\" alt=\"Please wait\" /> <span>登陆成功!请稍后....</span>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"login\">\r\n");
      out.write("\t\t<div class=\"ribbon\" style=\"background-image:url(extend/typelogin.png);\"></div>\r\n");
      out.write("\t\t<div class=\"inner\">\r\n");
      out.write("\t\t\t<div class=\"logo\">\r\n");
      out.write("\t\t\t\t<img src=\"extend/toplogo-jeecg.png\" />\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"formLogin\">\r\n");
      out.write("\t\t\t\t<form name=\"formLogin\" action=\"systemAction!load.action\" id=\"formLogin\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t<input name=\"userKey\" type=\"hidden\" id=\"userKey\" value=\"D1B5CC2FE46C4CC983C073BCA897935608D926CD32992B5900\" />\r\n");
      out.write("\t\t\t\t\t<div class=\"tip\">\r\n");
      out.write("\t\t\t\t\t\t<input class=\"userName\" name=\"userName\" type=\"text\" id=\"userName\" title=\"用户名\" iscookie=\"true\" value=\"admin\" nullmsg=\"请输入用户名!\" />\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"tip\">\r\n");
      out.write("\t\t\t\t\t\t<input class=\"password\" name=\"password\" type=\"password\" id=\"password\" title=\"密码\" value=\"admin\" nullmsg=\"请输入密码!\" />\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div id=\"cap\" class=\"tip\">\r\n");
      out.write("\t\t\t\t\t\t<!-- <input class=\"captcha\" name=\"captcha\" type=\"text\" id=\"captcha\"  nullmsg=\"请输入验证码!\" /> -->\r\n");
      out.write("\t\t\t\t\t\t<input class=\"captcha\" name=\"captcha\" type=\"text\" id=\"captcha\" />\r\n");
      out.write("\t\t\t\t\t\t<img style=\"width:85px;height:35px;margin-top: -10px;\" align=\"absmiddle\" id=\"Kaptcha\" src=\"Kaptcha.jpg\"/>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"loginButton\">\r\n");
      out.write("\t\t\t\t\t\t<div style=\"float: left; margin-left: -9px;\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"checkbox\" id=\"on_off\" name=\"remember\" checked=\"ture\" class=\"on_off_checkbox\" value=\"0\" /> <span class=\"f_help\">是否记住用户名?</span>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div style=\"float: right; padding: 3px 0; margin-right: -12px;\">\r\n");
      out.write("\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<ul class=\"uibutton-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<li><a class=\"uibutton normal\" href=\"javascript:void(0);\" id=\"but_login\">登陆</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<li><a class=\"uibutton normal\" href=\"javascript:void(0);\" id=\"forgetpass\">重置</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"float: left; margin-left: 30px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\"><span class=\"f_help\">是否初始化admin的密码</span></a>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"clear\"></div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"shadow\"></div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!--Login div-->\r\n");
      out.write("\t<div class=\"clear\"></div>\r\n");
      out.write("\t<div id=\"versionBar\">\r\n");
      out.write("\t\t<div class=\"copyright\">\r\n");
      out.write("\t\t\t<span><strong>Copyright © </strong>&nbsp; 钱钱金融信息服务（北京）有限公司</span>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
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
