/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.21
 * Generated at: 2015-09-09 02:33:52 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class basic_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<!-- Required Stylesheets -->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/reset.css\" media=\"screen\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/text.css\" media=\"screen\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/fonts/ptsans/stylesheet.css\" media=\"screen\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/fluid.css\" media=\"screen\" />\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mws.style.css\" media=\"screen\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/icons/icons.css\" media=\"screen\" />\r\n");
      out.write("\r\n");
      out.write("<!-- Demo and Plugin Stylesheets -->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/demo.css\" media=\"screen\" />\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugins/colorpicker/colorpicker.css\" media=\"screen\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugins/jimgareaselect/css/imgareaselect-default.css\" media=\"screen\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugins/fullcalendar/fullcalendar.css\" media=\"screen\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugins/fullcalendar/fullcalendar.print.css\" media=\"print\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugins/tipsy/tipsy.css\" media=\"screen\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugins/sourcerer/Sourcerer-1.2.css\" media=\"screen\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugins/jgrowl/jquery.jgrowl.css\" media=\"screen\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugins/spinner/spinner.css\" media=\"screen\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/jui/jquery.ui.css\" media=\"screen\" />\r\n");
      out.write("\r\n");
      out.write("<!-- Theme Stylesheet -->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mws.theme.css\" media=\"screen\" />\r\n");
      out.write("\r\n");
      out.write("<!-- clock css S-->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/normalize.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/default.css\">\r\n");
      out.write("<link rel=\"stylesheet\" media=\"screen\" href=\"css/main.css\"/>\r\n");
      out.write("<!-- clock css E-->\r\n");
      out.write("\r\n");
      out.write("<!-- JavaScript Plugins -->\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery-1.7.1.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/jimgareaselect/jquery.imgareaselect.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/jquery.dualListBox-1.3.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/jgrowl/jquery.jgrowl.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/jquery.filestyle.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/fullcalendar/fullcalendar.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/jquery.dataTables.js\"></script>\r\n");
      out.write("<!--[if lt IE 9]>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/flot/excanvas.min.js\"></script>\r\n");
      out.write("<![endif]-->\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/flot/jquery.flot.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/flot/jquery.flot.pie.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/flot/jquery.flot.stack.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/flot/jquery.flot.resize.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/colorpicker/colorpicker.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/tipsy/jquery.tipsy.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/sourcerer/Sourcerer-1.2.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/jquery.placeholder.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/jquery.validate.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/jquery.mousewheel.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugins/spinner/ui.spinner.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery-ui.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/mws.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/demo.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/themer.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/demo.dashboard.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\" src=\"js/jquery.thooClock.js\"></script> \r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
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
