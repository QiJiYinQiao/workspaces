/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.21
 * Generated at: 2015-05-25 06:59:46 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.cstManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class cstMain_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/E:/workspaces/.metadata/.plugins/org.eclipse.wst.server.core/tmp3/wtpwebapps/qqms/WEB-INF/lib/shiro-all-1.2.1.jar!/META-INF/shiro.tld", Long.valueOf(1343195352000L));
    _jspx_dependants.put("/WEB-INF/lib/shiro-all-1.2.1.jar", Long.valueOf(1431328416631L));
  }

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

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname;

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
    _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.release();
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
      out.write("    <title>客户管理</title>\r\n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \r\n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tvar $dg;\r\n");
      out.write("\t\t\tvar $grid;\r\n");
      out.write("\t\t\t$(function() {\r\n");
      out.write("\t\t\t\t $dg = $(\"#dg\");\r\n");
      out.write("\t\t\t\t $grid=$dg.datagrid({\r\n");
      out.write("\t\t\t\t\turl : \"cst/cstAction!findCustomerList.action\",\r\n");
      out.write("\t\t\t\t\twidth : 'auto',\r\n");
      out.write("\t\t\t\t\theight : $(this).height()-85,\r\n");
      out.write("\t\t\t\t\tpagination:true,\r\n");
      out.write("\t\t\t\t\trownumbers:true,\r\n");
      out.write("\t\t\t\t\tborder:true,\r\n");
      out.write("\t\t\t\t\tstriped:true,\r\n");
      out.write("\t\t\t\t\tsingleSelect:true,\r\n");
      out.write("\t\t\t\t\tcolumns : [ [ {field : 'name',title : '客户名称',width : parseInt($(this).width()*0.1)},\r\n");
      out.write("\t\t\t\t\t              {field : 'myid',title : '客户编码',width : parseInt($(this).width()*0.1)},\r\n");
      out.write("\t\t\t\t\t              {field : 'customerStatus',title : '客户状态',width : parseInt($(this).width()*0.1), \r\n");
      out.write("\t\t\t\t\t            \t  formatter:function(value,row){\r\n");
      out.write("\t\t\t\t            \t\t  if(\"T\"==row.customerStatus)\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\treturn \"<font color=green>交易中<font>\";\r\n");
      out.write("\t\t\t\t\t            \t\t  else\r\n");
      out.write("\t\t\t\t\t            \t\t\treturn \"<font color=red>禁用<font>\";  \r\n");
      out.write("\t\t\t\t\t\t\t\t\t}},\r\n");
      out.write("\t\t\t\t\t              {field : 'tel',title : '电话',width : parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {hidden : 'cityId',title : '城市',width : parseInt($(this).width()*0.1),align : 'left',\r\n");
      out.write("\t\t\t\t\t            \t  formatter:function(value,row){\r\n");
      out.write("\t\t\t\t\t            \t\t\treturn \"0\"+row.cityId;  \r\n");
      out.write("\t\t\t\t\t\t\t\t\t}},\r\n");
      out.write("\t\t\t\t\t              {hidden : 'saleId',title : '销售',width : parseInt($(this).width()*0.1),align : 'left',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tformatter:function(value,row){\r\n");
      out.write("\t\t\t\t\t            \t\t\treturn \"0\"+row.saleId;  \r\n");
      out.write("\t\t\t\t\t\t\t\t\t}},\r\n");
      out.write("\t\t\t\t\t              {field : 'fax',title : '传真',width :parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'email',title : '邮箱',width : parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'address',title : '地址',width : parseInt($(this).width()*0.3),align : 'left'}\r\n");
      out.write("\t\t\t\t\t              ] ],toolbar:'#tb'\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t//搜索框\r\n");
      out.write("\t\t\t\t$(\"#searchbox\").searchbox({ \r\n");
      out.write("\t\t\t\t\t menu:\"#mm\", \r\n");
      out.write("\t\t\t\t\t prompt :'模糊查询',\r\n");
      out.write("\t\t\t\t    searcher:function(value,name){   \r\n");
      out.write("\t\t\t\t    \tvar str=\"{\\\"searchName\\\":\\\"\"+name+\"\\\",\\\"searchValue\\\":\\\"\"+value+\"\\\"}\";\r\n");
      out.write("\t\t\t            var obj = eval('('+str+')');\r\n");
      out.write("\t\t\t            $dg.datagrid('reload',obj); \r\n");
      out.write("\t\t\t\t    }\r\n");
      out.write("\t\t\t\t   \r\n");
      out.write("\t\t\t\t}); \r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t//删除\r\n");
      out.write("\t\t\tfunction delRows(){\r\n");
      out.write("\t\t\t\tvar row = $dg.datagrid('getSelected');\r\n");
      out.write("\t\t\t\tif(row){\r\n");
      out.write("\t\t\t\t\tvar rowIndex = $dg.datagrid('getRowIndex', row);\r\n");
      out.write("\t\t\t\t\tparent.$.messager.confirm(\"提示\",\"确定要删除记录吗?\",function(r){  \r\n");
      out.write("\t\t\t\t\t    if (r){  \r\n");
      out.write("\t\t\t\t\t    \t$dg.datagrid('deleteRow', rowIndex);\r\n");
      out.write("\t\t\t\t\t    \t$.ajax({\r\n");
      out.write("\t\t\t\t\t\t\t\turl:\"cst/cstAction!delCustomer.action\",\r\n");
      out.write("\t\t\t\t\t\t\t\tdata: \"customerId=\"+row.customerId,\r\n");
      out.write("\t\t\t\t\t\t\t\tsuccess: function(rsp){\r\n");
      out.write("\t\t\t\t\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttitle : rsp.title,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tmsg : rsp.message,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t    }  \r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\ttitle : \"提示\",\r\n");
      out.write("\t\t\t\t\t\tmsg :\"请选择一行记录!\",\r\n");
      out.write("\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//弹窗修改\r\n");
      out.write("\t\t\tfunction updRowsOpenDlg() {\r\n");
      out.write("\t\t\t\tvar row = $dg.datagrid('getSelected');\r\n");
      out.write("\t\t\t\tif (row) {\r\n");
      out.write("\t\t\t\t\tparent.$.modalDialog({\r\n");
      out.write("\t\t\t\t\t\ttitle : '编辑客户',\r\n");
      out.write("\t\t\t\t\t\twidth : 900,\r\n");
      out.write("\t\t\t\t\t\theight :550,\r\n");
      out.write("\t\t\t\t\t\thref : \"jsp/cstManager/cstEditDlg.jsp?tempId=\"+row.customerId,\r\n");
      out.write("\t\t\t\t\t\tonLoad:function(){\r\n");
      out.write("\t\t\t\t\t\t\tvar f = parent.$.modalDialog.handler.find(\"#form\");\r\n");
      out.write("\t\t\t\t\t\t\trow.saleId=(typeof(row.saleId)==\"undefined\")?row.saleId:\"0\"+row.saleId;\r\n");
      out.write("\t\t\t\t\t\t\trow.cityId=(typeof(row.cityId)==\"undefined\")?row.cityId:\"0\"+row.cityId;\r\n");
      out.write("\t\t\t\t\t\t\tf.form(\"load\", row);\r\n");
      out.write("\t\t\t\t\t\t},\t\t\t\r\n");
      out.write("\t\t\t\t\t\tbuttons : [ {\r\n");
      out.write("\t\t\t\t\t\t\ttext : '编辑',\r\n");
      out.write("\t\t\t\t\t\t\ticonCls : 'icon-ok',\r\n");
      out.write("\t\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\tparent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好\r\n");
      out.write("\t\t\t\t\t\t\t\tvar f = parent.$.modalDialog.handler.find(\"#form\");\r\n");
      out.write("\t\t\t\t\t\t\t\tf.submit();\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\t\t\ttext : '取消',\r\n");
      out.write("\t\t\t\t\t\t\ticonCls : 'icon-cancel',\r\n");
      out.write("\t\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\tparent.$.modalDialog.handler.dialog('destroy');\r\n");
      out.write("\t\t\t\t\t\t\t\tparent.$.modalDialog.handler = undefined;\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t]\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\ttitle :\"提示\",\r\n");
      out.write("\t\t\t\t\t\tmsg :\"请选择一行记录!\",\r\n");
      out.write("\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//弹窗增加\r\n");
      out.write("\t\t\tfunction addRowsOpenDlg() {\r\n");
      out.write("\t\t\t\tparent.$.modalDialog({\r\n");
      out.write("\t\t\t\t\ttitle : '添加客户',\r\n");
      out.write("\t\t\t\t\twidth : 900,\r\n");
      out.write("\t\t\t\t\theight :550,\r\n");
      out.write("\t\t\t\t\thref : \"jsp/cstManager/cstEditDlg.jsp\",\r\n");
      out.write("\t\t\t\t\tbuttons : [ {\r\n");
      out.write("\t\t\t\t\t\ttext : '保存',\r\n");
      out.write("\t\t\t\t\t\ticonCls : 'icon-ok',\r\n");
      out.write("\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\tparent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好\r\n");
      out.write("\t\t\t\t\t\t\tvar f = parent.$.modalDialog.handler.find(\"#form\");\r\n");
      out.write("\t\t\t\t\t\t\tf.submit();\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\t\ttext : '取消',\r\n");
      out.write("\t\t\t\t\t\ticonCls : 'icon-cancel',\r\n");
      out.write("\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\tparent.$.modalDialog.handler.dialog('destroy');\r\n");
      out.write("\t\t\t\t\t\t\tparent.$.modalDialog.handler = undefined;\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t]\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//高级搜索 删除 row\r\n");
      out.write("\t\t\tfunction tbCompanySearchRemove(curr) {\r\n");
      out.write("\t\t\t\t\t$(curr).closest('tr').remove();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//高级查询\r\n");
      out.write("\t\t\tfunction tbsCompanySearch() {\r\n");
      out.write("\t\t\t\tjqueryUtil.gradeSearch($dg,\"#tbCompanySearchFm\",\"jsp/company/companySearchDlg.jsp\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t</script>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("      <div data-options=\"region:'center',border : false\">\r\n");
      out.write("  \t\t<div class=\"well well-small\" style=\"margin-left: 5px;margin-top: 5px\">\r\n");
      out.write("\t\t\t\t<span class=\"badge\">提示</span>\r\n");
      out.write("\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t在此你可以对<span class=\"label-info\"><strong>客户和客户联系人</strong></span>进行编辑!\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"tb\" style=\"padding:2px 0\">\r\n");
      out.write("\t\t\t<table cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td style=\"padding-left:2px\">\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_shiro_005fhasPermission_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_shiro_005fhasPermission_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_shiro_005fhasPermission_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td style=\"padding-left:2px\">\r\n");
      out.write("\t\t\t\t\t\t<input id=\"searchbox\" type=\"text\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td style=\"padding-left:2px\">\r\n");
      out.write("\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" plain=\"true\" onclick=\"javascript:void(0);\">高级查询</a>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"mm\">\r\n");
      out.write("\t\t\t\t<div name=\"name\">客户名称</div>\r\n");
      out.write("\t\t\t\t<div name=\"myid\">客户编码</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<table id=\"dg\" title=\"客户管理\"></table>\r\n");
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

  private boolean _jspx_meth_shiro_005fhasPermission_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  shiro:hasPermission
    org.apache.shiro.web.tags.HasPermissionTag _jspx_th_shiro_005fhasPermission_005f0 = (org.apache.shiro.web.tags.HasPermissionTag) _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.get(org.apache.shiro.web.tags.HasPermissionTag.class);
    _jspx_th_shiro_005fhasPermission_005f0.setPageContext(_jspx_page_context);
    _jspx_th_shiro_005fhasPermission_005f0.setParent(null);
    // /jsp/cstManager/cstMain.jsp(188,6) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f0.setName("cstAdd");
    int _jspx_eval_shiro_005fhasPermission_005f0 = _jspx_th_shiro_005fhasPermission_005f0.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\" onclick=\"addRowsOpenDlg();\">添加</a>\r\n");
        out.write("\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_shiro_005fhasPermission_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_shiro_005fhasPermission_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f0);
    return false;
  }

  private boolean _jspx_meth_shiro_005fhasPermission_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  shiro:hasPermission
    org.apache.shiro.web.tags.HasPermissionTag _jspx_th_shiro_005fhasPermission_005f1 = (org.apache.shiro.web.tags.HasPermissionTag) _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.get(org.apache.shiro.web.tags.HasPermissionTag.class);
    _jspx_th_shiro_005fhasPermission_005f1.setPageContext(_jspx_page_context);
    _jspx_th_shiro_005fhasPermission_005f1.setParent(null);
    // /jsp/cstManager/cstMain.jsp(191,6) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f1.setName("cstEdit");
    int _jspx_eval_shiro_005fhasPermission_005f1 = _jspx_th_shiro_005fhasPermission_005f1.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-edit\" plain=\"true\" onclick=\"updRowsOpenDlg();\">编辑</a>\r\n");
        out.write("\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_shiro_005fhasPermission_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_shiro_005fhasPermission_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f1);
    return false;
  }

  private boolean _jspx_meth_shiro_005fhasPermission_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  shiro:hasPermission
    org.apache.shiro.web.tags.HasPermissionTag _jspx_th_shiro_005fhasPermission_005f2 = (org.apache.shiro.web.tags.HasPermissionTag) _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.get(org.apache.shiro.web.tags.HasPermissionTag.class);
    _jspx_th_shiro_005fhasPermission_005f2.setPageContext(_jspx_page_context);
    _jspx_th_shiro_005fhasPermission_005f2.setParent(null);
    // /jsp/cstManager/cstMain.jsp(194,6) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f2.setName("cstDel");
    int _jspx_eval_shiro_005fhasPermission_005f2 = _jspx_th_shiro_005fhasPermission_005f2.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" plain=\"true\" onclick=\"delRows();\">删除</a>\r\n");
        out.write("\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_shiro_005fhasPermission_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_shiro_005fhasPermission_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f2);
    return false;
  }
}
