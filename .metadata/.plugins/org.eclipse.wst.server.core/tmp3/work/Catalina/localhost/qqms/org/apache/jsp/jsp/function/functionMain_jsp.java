/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.21
 * Generated at: 2015-09-07 03:47:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.function;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class functionMain_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/E:/workspaces/.metadata/.plugins/org.eclipse.wst.server.core/tmp3/wtpwebapps/qqms/WEB-INF/lib/shiro-all-1.2.1.jar!/META-INF/shiro.tld", Long.valueOf(1343195352000L));
    _jspx_dependants.put("/WEB-INF/lib/shiro-all-1.2.1.jar", Long.valueOf(1436839055669L));
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
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    <title>程式管理</title>\r\n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \r\n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tvar $dg;\r\n");
      out.write("\t\t\tvar $grid;\r\n");
      out.write("\t\t\tvar typedata=[{\"type\":\"F\",\"typeName\":\"菜单\"},{\"type\":\"O\",\"typeName\":\"操作\"}];\r\n");
      out.write("\t\t\t$(function() {\r\n");
      out.write("\t\t\t\t $dg = $(\"#dg\");\r\n");
      out.write("\t\t\t\t $grid=$dg.treegrid({\r\n");
      out.write("\t\t\t\t\twidth : 'auto',\r\n");
      out.write("\t\t\t\t\theight : $(this).height()-90,\r\n");
      out.write("\t\t\t\t\turl : \"function/functionAction!findAllFunctionList.action\",\r\n");
      out.write("\t\t\t\t\trownumbers:true,\r\n");
      out.write("\t\t\t\t\tanimate: true,\r\n");
      out.write("\t\t\t\t\tcollapsible: true,\r\n");
      out.write("\t\t\t\t\tfitColumns: true,\r\n");
      out.write("\t\t\t\t\tstriped:true,\r\n");
      out.write("\t\t\t\t\tborder:true,\r\n");
      out.write("\t\t\t\t\t//singleSelect:false,\r\n");
      out.write("\t\t\t\t\tidField: 'permissionId',\r\n");
      out.write("\t\t\t\t\ttreeField: 'name',\r\n");
      out.write("\t\t\t\t\t frozenColumns:[[\r\n");
      out.write("\t\t\t\t\t                 {title:'程式名称',field:'name',editor : {type:'validatebox',options:{required:true}},width:parseInt($(this).width()*0.2),\r\n");
      out.write("\t\t\t\t\t                  formatter:function(value){\r\n");
      out.write("\t\t\t\t\t                   return '<span style=\"color:red\">'+value+'</span>';\r\n");
      out.write("\t\t\t\t\t                  }\r\n");
      out.write("\t\t\t\t\t                 }\r\n");
      out.write("\t\t\t\t\t    ]],\r\n");
      out.write("\t\t\t\t\tcolumns : [ [ //{field:'ck',checkbox:true},\r\n");
      out.write("\t\t\t\t\t              //{field : 'name',title : '程式名称',width : 250,editor : {type:'validatebox',options:{required:true}}},\r\n");
      out.write("\t\t\t\t\t              {field : 'pname',title : '父程式名称',width : parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'sort',title : '排序编码',width : parseInt($(this).width()*0.1),editor:{type:'numberbox'}},\r\n");
      out.write("\t\t\t\t\t              {field : 'iconCls',title : '程式图标',align : 'center',width : parseInt($(this).width()*0.1),\r\n");
      out.write("\t\t\t\t\t            \t  formatter:function(value,row){\r\n");
      out.write("\t\t\t\t\t            \t\t  return \"<span class='\"+row.iconCls+\"' style='display:inline-block;vertical-align:middle;width:16px;height:16px;'></span>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t            \t  editor:{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\ttype:'combobox',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\toptions:{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t//valueField:'type',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t//textField:'typeName',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tdata:$.iconData,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tformatter : function(v) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\treturn $.formatString('<span class=\"{0}\" style=\"display:inline-block;vertical-align:middle;width:16px;height:16px;\"></span>{1}', v.value, v.value);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tvalue : 'wrench'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}},\r\n");
      out.write("\t\t\t\t\t              {field : 'url',title : '程式路径',width : parseInt($(this).width()*0.1),align : 'left',editor : {type:'validatebox',options:{required:true}}},\r\n");
      out.write("\t\t\t\t\t              {field : 'myid',title : '程式编码',width : parseInt($(this).width()*0.1),align : 'left',editor : {type:'validatebox',options:{required:true}}},\r\n");
      out.write("\t\t\t\t\t              {field : 'type',title : '程式类型',width : parseInt($(this).width()*0.1),align : 'left',\r\n");
      out.write("\t\t\t\t\t            \t  formatter:function(value,row){\r\n");
      out.write("\t\t\t\t\t            \t\t  if(\"F\"==row.type)\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\treturn \"<font color=green>菜单<font>\";\r\n");
      out.write("\t\t\t\t\t\t            \t\t  else\r\n");
      out.write("\t\t\t\t\t\t            \t\t\treturn \"<font color=red>操作<font>\";  \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\teditor:{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\ttype:'combobox',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\toptions:{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tvalueField:'type',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\ttextField:'typeName',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tdata:typedata,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\trequired:true\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}},\r\n");
      out.write("\t\t\t\t\t              {field : 'isused',title : '是否启用',width : parseInt($(this).width()*0.1),align : 'center',\r\n");
      out.write("\t\t\t\t\t            \t  formatter:function(value,row){\r\n");
      out.write("\t\t\t\t\t            \t\t  if(\"Y\"==row.isused)\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\treturn \"<font color=green>是<font>\";\r\n");
      out.write("\t\t\t\t\t            \t\t  else\r\n");
      out.write("\t\t\t\t\t            \t\t\treturn \"<font color=red>否<font>\";  \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\teditor:{type:'checkbox',options:{on:'Y',off:'N'}}\r\n");
      out.write("\t\t\t\t\t              },\r\n");
      out.write("\t\t\t\t\t              {field : 'description',title : '程式描述',width : parseInt($(this).width()*0.2),align : 'left',editor : \"text\"}\r\n");
      out.write("\t\t\t\t\t              ] ],toolbar:'#tb'\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\tvar flag=true;\r\n");
      out.write("\t\t\tfunction endEdit(){\r\n");
      out.write("\t\t\t\tvar select = $dg.treegrid('getSelections');\r\n");
      out.write("\t\t\t\tif(select){\r\n");
      out.write("\t\t\t\t\tvar nodes = $dg.treegrid('getData');\r\n");
      out.write("\t\t\t\t\tcheckedNodes(nodes);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\treturn flag;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//遍历节点和子节点\r\n");
      out.write("\t\t\tfunction checkedNodes(nodes){\r\n");
      out.write("\t\t\t\tif(nodes){\r\n");
      out.write("\t\t\t\t\t$.each(nodes,function(i,node){\r\n");
      out.write("\t\t\t\t\t\tif(node){\r\n");
      out.write("\t\t\t\t\t\t\t$dg.treegrid('endEdit', node.permissionId);\r\n");
      out.write("\t\t\t\t\t\t\tvar temp=$dg.treegrid('validateRow', node.permissionId);\r\n");
      out.write("\t\t\t\t\t\t\tif(!temp){ flag= false; }\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\tif(node.children){\r\n");
      out.write("\t\t\t\t\t\t\tcheckedNodes(node.children);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\treturn flag;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tfunction editNode(){\r\n");
      out.write("\t\t\t\tvar nodes = $dg.treegrid('getSelections');\r\n");
      out.write("\t\t\t\tif(nodes==null||nodes==\"\"){\r\n");
      out.write("\t\t\t\t\t$.messager.alert(\"提示\", \"请选择行记录!\");\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$.each(nodes,function(i,node){\r\n");
      out.write("\t\t\t\t\t\tif(node){\r\n");
      out.write("\t\t\t\t\t\t\t$dg.treegrid('beginEdit', node.permissionId);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction removeNode(){\r\n");
      out.write("\t\t\t\tvar node = $dg.treegrid('getSelected');\r\n");
      out.write("\t\t\t\tif(node){\r\n");
      out.write("\t\t\t\t\tparent.$.messager.confirm(\"提示\",\"确定要删除记录吗?\",function(r){  \r\n");
      out.write("\t\t\t\t\t    if (r){  \r\n");
      out.write("\t\t\t\t\t    \t$.post(\"function/functionAction!delFunction.action\", {id:node.permissionId}, function(rsp) {\r\n");
      out.write("\t\t\t\t\t\t\t\tif(rsp.status){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$dg.treegrid('remove', node.permissionId);\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttitle : rsp.title,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tmsg : rsp.message,\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t}, \"JSON\").error(function() {\r\n");
      out.write("\t\t\t\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttitle :\"提示\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\tmsg :\"提交错误了！\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t    }  \r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\ttitle :\"提示\",\r\n");
      out.write("\t\t\t\t\t\tmsg :\"请选择一行记录!\",\r\n");
      out.write("\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction saveNodes(){\r\n");
      out.write("\t\t\t\tif(endEdit()){\r\n");
      out.write("\t\t\t\t\tif ($dg.treegrid('getChanges').length) {\r\n");
      out.write("\t\t\t\t\t\tvar inserted = $dg.treegrid('getChanges', \"inserted\");\r\n");
      out.write("\t\t\t\t\t\tvar deleted = $dg.treegrid('getChanges', \"deleted\");\r\n");
      out.write("\t\t\t\t\t\tvar updated = $dg.treegrid('getChanges', \"updated\");\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\tvar effectRow = new Object();\r\n");
      out.write("\t\t\t\t\t\tif (inserted.length) {\r\n");
      out.write("\t\t\t\t\t\t\teffectRow[\"inserted\"] = JSON.stringify(inserted);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\tif (deleted.length) {\r\n");
      out.write("\t\t\t\t\t\t\teffectRow[\"deleted\"] = JSON.stringify(deleted);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\tif (updated.length) {\r\n");
      out.write("\t\t\t\t\t\t\teffectRow[\"updated\"] = JSON.stringify(updated);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t$.post(\"function/functionAction!persistenceFunction.action\", effectRow, function(rsp) {\r\n");
      out.write("\t\t\t\t\t\t\tif(rsp.status){\r\n");
      out.write("\t\t\t\t\t\t\t\t$dg.datagrid('acceptChanges');\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t$.messager.alert(rsp.title, rsp.message);\r\n");
      out.write("\t\t\t\t\t\t}, \"JSON\").error(function() {\r\n");
      out.write("\t\t\t\t\t\t\t$.messager.alert(\"提示\", \"提交错误了！\");\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$.messager.alert(\"提示\", \"字段验证未通过!请查看\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//增加并列项\r\n");
      out.write("\t\t\tfunction addStandPlaceNode(){\r\n");
      out.write("\t\t\t\tvar temp=jqueryUtil.getRandTime();\r\n");
      out.write("\t\t\t\tvar node = $dg.treegrid('getSelected');\r\n");
      out.write("\t\t\t\tif (node){\r\n");
      out.write("\t\t\t\t\t$dg.treegrid('insert', {\r\n");
      out.write("\t\t\t\t\t\tafter: node.permissionId,\r\n");
      out.write("\t\t\t\t\t\tdata: {\r\n");
      out.write("\t\t\t\t\t\t\tpermissionId:temp,\r\n");
      out.write("\t\t\t\t\t\t\tpid:node.pid,\r\n");
      out.write("\t\t\t\t\t\t\tpname:node.pname,\r\n");
      out.write("\t\t\t\t\t\t\tsort:node.sort+1,\r\n");
      out.write("\t\t\t\t\t\t\turl:'javascript:void(0);',\r\n");
      out.write("\t\t\t\t\t\t\tstatus:'add'\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t$dg.treegrid('unselect', node.permissionId);\r\n");
      out.write("\t\t\t\t\t$dg.treegrid('select', temp);\r\n");
      out.write("\t\t\t\t\t$dg.treegrid('beginEdit', temp);\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$.messager.alert(\"提示\", \"请选择一行记录!\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//增加子项\r\n");
      out.write("\t\t\tfunction addSubitemNode(){\r\n");
      out.write("\t\t\t\tvar temp=jqueryUtil.getRandTime();\r\n");
      out.write("\t\t\t\tvar node = $dg.treegrid('getSelected');\r\n");
      out.write("\t\t\t\tif (node){\r\n");
      out.write("\t\t\t\t\t$dg.treegrid('insert', {\r\n");
      out.write("\t\t\t\t\t\tafter: node.permissionId,\r\n");
      out.write("\t\t\t\t\t\tdata: {\r\n");
      out.write("\t\t\t\t\t\t\tpermissionId:temp,\r\n");
      out.write("\t\t\t\t\t\t\tpid:node.permissionId,\r\n");
      out.write("\t\t\t\t\t\t\tpname:node.name,\r\n");
      out.write("\t\t\t\t\t\t\tsort:node.sort+1,\r\n");
      out.write("\t\t\t\t\t\t\turl:'javascript:void(0);',\r\n");
      out.write("\t\t\t\t\t\t\tstatus:'add'\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t$dg.treegrid('unselect', node.permissionId);\r\n");
      out.write("\t\t\t\t\t$dg.treegrid('select', temp);\r\n");
      out.write("\t\t\t\t\t$dg.treegrid('beginEdit', temp);\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$.messager.alert(\"提示\", \"请选择一行记录!\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//弹窗修改\r\n");
      out.write("\t\t\tfunction updRowsOpenDlg() {\r\n");
      out.write("\t\t\t\tvar row = $dg.treegrid('getSelected');\r\n");
      out.write("\t\t\t\tif (row) {\r\n");
      out.write("\t\t\t\t\tparent.$.modalDialog({\r\n");
      out.write("\t\t\t\t\t\ttitle : \"编辑程式\",\r\n");
      out.write("\t\t\t\t\t\twidth : 600,\r\n");
      out.write("\t\t\t\t\t\theight : 400,\r\n");
      out.write("\t\t\t\t\t\thref : \"jsp/function/functionEditDlg.jsp?tempId=\"+row.type,\r\n");
      out.write("\t\t\t\t\t\tonLoad:function(){\r\n");
      out.write("\t\t\t\t\t\t\tvar f = parent.$.modalDialog.handler.find(\"#form\");\r\n");
      out.write("\t\t\t\t\t\t\tf.form(\"load\", row);\r\n");
      out.write("\t\t\t\t\t\t},\t\t\t\r\n");
      out.write("\t\t\t\t\t\tbuttons : [ {\r\n");
      out.write("\t\t\t\t\t\t\ttext : '编辑',\r\n");
      out.write("\t\t\t\t\t\t\ticonCls : 'icon-ok',\r\n");
      out.write("\t\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\tparent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好\r\n");
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
      out.write("\t\t\t\tvar row = $dg.treegrid('getSelected');\r\n");
      out.write("\t\t\t\tif(row){\r\n");
      out.write("\t\t\t\t\tif(row.type==\"O\"){\r\n");
      out.write("\t\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\t\ttitle :\"提示\",\r\n");
      out.write("\t\t\t\t\t\t\tmsg :\"操作暂无下层!\",\r\n");
      out.write("\t\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\tparent.$.modalDialog({\r\n");
      out.write("\t\t\t\t\t\t\ttitle : \"添加程式\",\r\n");
      out.write("\t\t\t\t\t\t\twidth : 600,\r\n");
      out.write("\t\t\t\t\t\t\theight : 400,\r\n");
      out.write("\t\t\t\t\t\t\thref : \"jsp/function/functionEditDlg.jsp\",\r\n");
      out.write("\t\t\t\t\t\t\tonLoad:function(){\r\n");
      out.write("\t\t\t\t\t\t\t\tif(row){\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar f = parent.$.modalDialog.handler.find(\"#form\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\tf.form(\"load\", {\"pid\":row.permissionId});\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t},\t\r\n");
      out.write("\t\t\t\t\t\t\tbuttons : [ {\r\n");
      out.write("\t\t\t\t\t\t\t\ttext : '保存',\r\n");
      out.write("\t\t\t\t\t\t\t\ticonCls : 'icon-ok',\r\n");
      out.write("\t\t\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tparent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar f = parent.$.modalDialog.handler.find(\"#form\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\tf.submit();\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\t\t\t\ttext : '取消',\r\n");
      out.write("\t\t\t\t\t\t\t\ticonCls : 'icon-cancel',\r\n");
      out.write("\t\t\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tparent.$.modalDialog.handler.dialog('destroy');\r\n");
      out.write("\t\t\t\t\t\t\t\t\tparent.$.modalDialog.handler = undefined;\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t]\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tparent.$.modalDialog({\r\n");
      out.write("\t\t\t\t\t\ttitle : \"添加程式\",\r\n");
      out.write("\t\t\t\t\t\twidth : 600,\r\n");
      out.write("\t\t\t\t\t\theight : 400,\r\n");
      out.write("\t\t\t\t\t\thref : \"jsp/function/functionEditDlg.jsp\",\r\n");
      out.write("\t\t\t\t\t\tonLoad:function(){\r\n");
      out.write("\t\t\t\t\t\t\tif(row){\r\n");
      out.write("\t\t\t\t\t\t\t\tvar f = parent.$.modalDialog.handler.find(\"#form\");\r\n");
      out.write("\t\t\t\t\t\t\t\tf.form(\"load\", {\"pid\":row.permissionId});\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t},\t\r\n");
      out.write("\t\t\t\t\t\tbuttons : [ {\r\n");
      out.write("\t\t\t\t\t\t\ttext : '保存',\r\n");
      out.write("\t\t\t\t\t\t\ticonCls : 'icon-ok',\r\n");
      out.write("\t\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\tparent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好\r\n");
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
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t</script>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("  \t<div class=\"well well-small\" style=\"margin-left: 5px;margin-top: 5px\">\r\n");
      out.write("\t\t\t\t<span class=\"badge\">提示</span>\r\n");
      out.write("\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t在此你可以对<span class=\"label-info\"><strong>菜单功能</strong></span>进行编辑!  &nbsp;<span class=\"label-info\"><strong>注意</strong></span>操作功能是对菜单功能的操作权限！\r\n");
      out.write("\t\t\t\t\t请谨慎填写程序编码，权限区分标志，请勿重复!\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("    <div id=\"tb\" style=\"padding:10px;height:auto\">\r\n");
      out.write("\t\t\t<div style=\"margin-bottom:5px\">\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_shiro_005fhasPermission_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_shiro_005fhasPermission_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_shiro_005fhasPermission_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t<!-- ");
      if (_jspx_meth_shiro_005fhasPermission_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_shiro_005fhasPermission_005f4(_jspx_page_context))
        return;
      out.write(" -->\t\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("  \t\t<table id=\"dg\" title=\"程式管理\"></table>\r\n");
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
    // /jsp/function/functionMain.jsp(364,3) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f0.setName("funAdd");
    int _jspx_eval_shiro_005fhasPermission_005f0 = _jspx_th_shiro_005fhasPermission_005f0.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<!--  <a href=\"javascript:void(0);\" class=\"easyui-splitbutton\" data-options=\"menu:'#mm1',iconCls:'icon-add'\">添加</a>\r\n");
        out.write("\t\t\t\t<div id=\"mm1\" style=\"width:150px;\">\r\n");
        out.write("\t\t\t\t\t<div data-options=\"iconCls:'icon-undo'\" onclick=\"addStandPlaceNode();\">增加并列项</div>\r\n");
        out.write("\t\t\t\t\t<div data-options=\"iconCls:'icon-redo'\" onclick=\"addSubitemNode();\">增加子项</div>\r\n");
        out.write("\t\t\t\t</div>-->\r\n");
        out.write("\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\" onclick=\"addRowsOpenDlg();\">添加</a>\r\n");
        out.write("\t\t\t");
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
    // /jsp/function/functionMain.jsp(372,3) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f1.setName("funEdit");
    int _jspx_eval_shiro_005fhasPermission_005f1 = _jspx_th_shiro_005fhasPermission_005f1.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-edit\" plain=\"true\" onclick=\"updRowsOpenDlg();\">编辑</a>\r\n");
        out.write("\t\t\t");
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
    // /jsp/function/functionMain.jsp(375,3) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f2.setName("funDel");
    int _jspx_eval_shiro_005fhasPermission_005f2 = _jspx_th_shiro_005fhasPermission_005f2.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" plain=\"true\" onclick=\"removeNode();\">删除</a>\r\n");
        out.write("\t\t\t");
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

  private boolean _jspx_meth_shiro_005fhasPermission_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  shiro:hasPermission
    org.apache.shiro.web.tags.HasPermissionTag _jspx_th_shiro_005fhasPermission_005f3 = (org.apache.shiro.web.tags.HasPermissionTag) _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.get(org.apache.shiro.web.tags.HasPermissionTag.class);
    _jspx_th_shiro_005fhasPermission_005f3.setPageContext(_jspx_page_context);
    _jspx_th_shiro_005fhasPermission_005f3.setParent(null);
    // /jsp/function/functionMain.jsp(378,7) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f3.setName("funEndEdit");
    int _jspx_eval_shiro_005fhasPermission_005f3 = _jspx_th_shiro_005fhasPermission_005f3.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-cancel\" plain=\"true\" onclick=\"endEdit();\">结束编辑</a>\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_shiro_005fhasPermission_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_shiro_005fhasPermission_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f3);
    return false;
  }

  private boolean _jspx_meth_shiro_005fhasPermission_005f4(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  shiro:hasPermission
    org.apache.shiro.web.tags.HasPermissionTag _jspx_th_shiro_005fhasPermission_005f4 = (org.apache.shiro.web.tags.HasPermissionTag) _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.get(org.apache.shiro.web.tags.HasPermissionTag.class);
    _jspx_th_shiro_005fhasPermission_005f4.setPageContext(_jspx_page_context);
    _jspx_th_shiro_005fhasPermission_005f4.setParent(null);
    // /jsp/function/functionMain.jsp(381,3) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f4.setName("funSave");
    int _jspx_eval_shiro_005fhasPermission_005f4 = _jspx_th_shiro_005fhasPermission_005f4.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-save\" plain=\"true\" onclick=\"saveNodes();\">保存</a>\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_shiro_005fhasPermission_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_shiro_005fhasPermission_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f4);
    return false;
  }
}
