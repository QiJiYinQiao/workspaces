/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.21
 * Generated at: 2015-05-25 07:02:06 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class userList_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("<title>用户管理</title>\r\n");
      out.write("<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"expires\" content=\"0\">\r\n");
      out.write("<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("jsp/user/userList.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("js/validate.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery.md5.js\"></script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <!-- 提示信息区域 -->\r\n");
      out.write("\t<div class=\"well well-small\" style=\"margin-left: 5px; margin-top: 5px\">\r\n");
      out.write("\t\t<span class=\"badge\">提示</span>\r\n");
      out.write("\t\t<p>\r\n");
      out.write("\t\t\t在此你可以对<span class=\"label-info\"><strong>用户</strong></span>进行编辑!\r\n");
      out.write("\t\t</p>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 工具栏区域 -->\r\n");
      out.write("\t<div id=\"tb\" style=\"padding:2px 0\">\r\n");
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
      out.write("\t\t\t\t\t\t<input id=\"searchbox\" class=\"easyui-searchbox\" data-options=\"searcher:likeSearch,prompt:'模糊搜索',menu:'#mm'\" type=\"text\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td style=\"padding-left:2px\">\r\n");
      out.write("\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" plain=\"true\" onclick=\"userSearch();\">高级查询</a>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t  </div>\r\n");
      out.write("\t  <!-- 搜索下拉框 选项-->\r\n");
      out.write("\t  <div id=\"mm\">\r\n");
      out.write("\t\t\t<div data-options=\"name:'myid'\">用户编码</div>\r\n");
      out.write("\t\t\t<div data-options=\"name:'account'\">用户账户</div>\r\n");
      out.write("\t\t\t<div data-options=\"name:'name'\">用户名</div>\r\n");
      out.write("\t\t\t<div data-options=\"name:'email'\">邮箱</div>\r\n");
      out.write("\t\t\t<div data-options=\"name:'tel'\">电话</div>\r\n");
      out.write("\t\t\t<div data-options=\"name:'organizeName'\">组织</div>\r\n");
      out.write("\t\t\t<div data-options=\"name:'description'\">描述</div>\r\n");
      out.write("\t  </div>\r\n");
      out.write("\t  \r\n");
      out.write("\t  <table id=\"dg\" title=\"用户管理\"></table>\r\n");
      out.write("\t  \r\n");
      out.write("\t  <div id=\"fd\" class=\"easyui-dialog\" data-options=\"title:'新增',closed:true,cache:false,modal: true\" style=\"width: 550px;height: 330px;\">\r\n");
      out.write("\t\t  <form id=\"form\" method=\"post\" novalidate='true'>\r\n");
      out.write("\t\t\t<input name=\"userId\" id=\"userId\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t<input name=\"created\" id=\"created\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t<input name=\"creater\" id=\"creater\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t<input name=\"status\" id=\"status\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t<input name=\"organizeName\" id=\"organizeName\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t<table cellspacing=\"10\">\r\n");
      out.write("\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t    <td align=\"right\">用户编码:</td>\r\n");
      out.write("\t\t\t\t\t<td><input name=\"myid\" id=\"myid\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'length[0,85]'\"/></td>\r\n");
      out.write("\t\t\t\t\t<td align=\"right\">用户账号:</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"account\" name=\"account\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'length[0,85]'\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t </tr>\r\n");
      out.write("\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t    <td align=\"right\">用户名:</td>\r\n");
      out.write("\t\t\t\t\t<td><input name=\"name\" id=\"name\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'length[0,85]'\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t\t<td align=\"right\">用户密码:</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"password\" name=\"password\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'length[0,85]'\"/></td>\r\n");
      out.write("\t\t\t\t </tr>\r\n");
      out.write("\t\t\t\t  <tr>\r\n");
      out.write("\t\t\t\t    <td align=\"right\">邮箱:</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"email\" name=\"email\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'email'\" /></td>\r\n");
      out.write("\t\t\t\t\t<td align=\"right\">电话:</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"tel\" name=\"tel\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'mobile'\" /></td>\r\n");
      out.write("\t\t\t\t </tr>\r\n");
      out.write("\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t    <td align=\"right\">组织部门:</td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"3\"><input id=\"organizeId\" name=\"organizeId\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t </tr>\r\n");
      out.write("\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t<td align=\"right\">描述:</td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"3\"><textarea class=\"easyui-textbox\" name=\"description\" id=\"description\"  style=\"width: 435px;height: 100px;\"></textarea></td>\r\n");
      out.write("\t\t\t\t </tr>\r\n");
      out.write("\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t<td align=\"right\" colspan=\"4\" >\r\n");
      out.write("\t\t\t\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-save'\" style=\"width: 80px;\" onclick=\"saveData();\">保存</a>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-cancel'\" style=\"width: 80px;\" onclick=\"javascript:$('#fd').dialog('close');\">取消</a>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t     </tr>\r\n");
      out.write("\t\t\t   </table>\r\n");
      out.write("\t        </form>\r\n");
      out.write("\t  </div>\r\n");
      out.write("</body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("//模糊查询\r\n");
      out.write("function likeSearch(value,name){\r\n");
      out.write("\tvar str=\"{\\\"searchName\\\":\\\"\"+name+\"\\\",\\\"searchValue\\\":\\\"\"+value+\"\\\"}\";\r\n");
      out.write("    var obj = eval('('+str+')');\r\n");
      out.write("    $('#dg').datagrid('reload',obj); \r\n");
      out.write("}\r\n");
      out.write("//打开编辑弹窗\r\n");
      out.write("function openEditDiv(){\r\n");
      out.write("\t var row = $('#dg').datagrid('getSelected');\r\n");
      out.write("\t var rows = $('#dg').datagrid('getSelections');\r\n");
      out.write("\t if(rows!=null && rows.length>1){\r\n");
      out.write("\t\t $.messager.alert('提示','您只能选择一条记录!','info');\r\n");
      out.write("\t\t return;\r\n");
      out.write("\t }\r\n");
      out.write("     if (row){\r\n");
      out.write("            $('#fd').dialog('open').dialog('setTitle','修改'); \r\n");
      out.write("            $('#form').form('load',row);\r\n");
      out.write("        \r\n");
      out.write("     }else{\r\n");
      out.write("            $.messager.alert('提示','请选择一条数据！','warning');\r\n");
      out.write("     }\r\n");
      out.write("} \r\n");
      out.write("//打开新增弹窗\r\n");
      out.write("function openAddDiv(){\r\n");
      out.write("\t$('#fd').dialog('refresh','");
      out.print(basePath );
      out.write("jsp/user/new.jsp'); //刷新dialog \r\n");
      out.write("\t$('#fd').dialog('open');\r\n");
      out.write("}\r\n");
      out.write("//新增保存\r\n");
      out.write("function saveData(){\r\n");
      out.write("\t$('#form').form('submit',{\r\n");
      out.write("        url: 'user/userAction!persistenceUsersDig.action',\r\n");
      out.write("        onSubmit: function(){\r\n");
      out.write("        \t$.messager.progress({\r\n");
      out.write("\t\t\t\ttitle : '提示',\r\n");
      out.write("\t\t\t\ttext : '数据处理中，请稍后....'\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\tvar isValid = $(this).form('validate');\r\n");
      out.write("\t\t\tif (!isValid) {\r\n");
      out.write("\t\t\t\t$.messager.progress('close');\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar row = $(\"#dg\").datagrid('getSelected');\r\n");
      out.write("\t\t\tif(row!=null){\r\n");
      out.write("\t\t\t\tif($(\"#password\").val() != row.Password){\r\n");
      out.write("\t\t\t\t\t$(\"#password\").val($.md5($(\"#password\").val()).toUpperCase());\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\t$(\"#password\").val($.md5($(\"#password\").val()).toUpperCase());\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("        },\r\n");
      out.write("        success: function(result){\r\n");
      out.write("        \t$.messager.progress('close');\r\n");
      out.write("                result = $.parseJSON(result);//解析json字符串\r\n");
      out.write("                if (result.status) {\r\n");
      out.write("                \t $('#fd').dialog('close'); //弹框关闭       \r\n");
      out.write("                     $('#dg').datagrid('reload'); //列表刷新\r\n");
      out.write("\t\t\t\t\t $.messager.show({    //右下角弹出提示框\r\n");
      out.write("\t\t\t\t\t\ttitle : result.title,\r\n");
      out.write("\t\t\t\t\t\tmsg : result.message,\r\n");
      out.write("\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t });\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$.messager.show({\r\n");
      out.write("\t\t\t\t\t\ttitle :  result.title,\r\n");
      out.write("\t\t\t\t\t\tmsg : result.message,\r\n");
      out.write("\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("}\r\n");
      out.write("//删除\r\n");
      out.write("function removeData(){\r\n");
      out.write("\t var rows = $('#dg').datagrid('getSelections');//获取选中的记录\r\n");
      out.write("\t if(rows!=null&&rows.length>=1){\r\n");
      out.write("\t\t var ids = new Array();\r\n");
      out.write("\t\t $.messager.confirm('删除', '删除该记录将不可恢复，确认删除吗?', function(d) {\r\n");
      out.write("\t\t\t if(d){\r\n");
      out.write("\t\t\t\t $.each(rows,function(i,row){\r\n");
      out.write("\t\t\t\t\t\tif (row) {\r\n");
      out.write("\t\t\t\t\t\t\tvar rowIndex = $('#dg').datagrid('getRowIndex', row);\r\n");
      out.write("\t\t\t\t\t\t\t$('#dg').datagrid('deleteRow', rowIndex);\r\n");
      out.write("\t\t\t\t\t\t\tids.push(row.userId);//将ID放入数组中\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t });\r\n");
      out.write("\t\t\t\t ids = ids.join(\",\");// 转换为字符串\r\n");
      out.write("\t\t\t\t $.ajax({\r\n");
      out.write("\t\t\t\t\t    type:'post',\r\n");
      out.write("\t\t\t\t\t\turl:\"user/userAction!delUsers.action\",\r\n");
      out.write("\t\t\t\t\t\tdata: \"IDS=\"+ids,\r\n");
      out.write("\t\t\t\t\t\tsuccess: function(rsp){\r\n");
      out.write("\t\t\t\t\t\t\t$.messager.show({\r\n");
      out.write("\t\t\t\t\t\t\t\ttitle : rsp.title,\r\n");
      out.write("\t\t\t\t\t\t\t\tmsg : rsp.message,\r\n");
      out.write("\t\t\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t }\r\n");
      out.write("\t\t })\r\n");
      out.write("\t }else{\r\n");
      out.write("\t\t $.messager.alert(\"提示\",\"请至少选择一条记录!\",\"warning\");\r\n");
      out.write("\t }\r\n");
      out.write("}\r\n");
      out.write("//打开高级搜索窗口\r\n");
      out.write("function  userSearch() {\r\n");
      out.write("\tjqueryUtil.gradeSearch($(\"#dg\"),\"#userSearchFm\",\"jsp/user/userSearchDlg.jsp\");\r\n");
      out.write("}\r\n");
      out.write("//高级搜索 del row\r\n");
      out.write("function userSearchRemove(curr) {\r\n");
      out.write("\t\t$(curr).closest('tr').remove();\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
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

  private boolean _jspx_meth_shiro_005fhasPermission_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  shiro:hasPermission
    org.apache.shiro.web.tags.HasPermissionTag _jspx_th_shiro_005fhasPermission_005f0 = (org.apache.shiro.web.tags.HasPermissionTag) _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.get(org.apache.shiro.web.tags.HasPermissionTag.class);
    _jspx_th_shiro_005fhasPermission_005f0.setPageContext(_jspx_page_context);
    _jspx_th_shiro_005fhasPermission_005f0.setParent(null);
    // /jsp/user/userList.jsp(43,6) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f0.setName("userAdd");
    int _jspx_eval_shiro_005fhasPermission_005f0 = _jspx_th_shiro_005fhasPermission_005f0.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\" onclick=\"openAddDiv();\">添加</a>\r\n");
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
    // /jsp/user/userList.jsp(46,6) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f1.setName("userEdit");
    int _jspx_eval_shiro_005fhasPermission_005f1 = _jspx_th_shiro_005fhasPermission_005f1.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-edit\" plain=\"true\" onclick=\"openEditDiv();\">编辑</a>\r\n");
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
    // /jsp/user/userList.jsp(49,6) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f2.setName("userDel");
    int _jspx_eval_shiro_005fhasPermission_005f2 = _jspx_th_shiro_005fhasPermission_005f2.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" plain=\"true\" onclick=\"removeData();\">删除</a>\r\n");
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
