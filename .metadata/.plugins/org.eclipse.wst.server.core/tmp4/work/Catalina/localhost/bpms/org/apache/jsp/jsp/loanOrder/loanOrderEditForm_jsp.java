/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-09-18 08:52:06 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.loanOrder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class loanOrderEditForm_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("<style>\r\n");
      out.write(".easyui-textbox {\r\n");
      out.write("\theight: 18px;\r\n");
      out.write("\twidth: 170px;\r\n");
      out.write("\tline-height: 16px;\r\n");
      out.write("\t/*border-radius: 3px 3px 3px 3px;*/\r\n");
      out.write("\tbox-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;\r\n");
      out.write("\ttransition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;\r\n");
      out.write("}\r\n");
      out.write("textarea:focus, input[type=\"text\"]:focus {\r\n");
      out.write("\tborder-color: rgba(82, 168, 236, 0.8);\r\n");
      out.write("\tbox-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px\r\n");
      out.write("\t\trgba(82, 168, 236, 0.6);\r\n");
      out.write("\toutline: 0 none;\r\n");
      out.write("}\r\n");
      out.write("input, textarea {\r\n");
      out.write("\tfont-weight: normal;\r\n");
      out.write("}\r\n");
      out.write(".easyui-aa{\r\n");
      out.write("}\r\n");
      out.write(".table {\r\n");
      out.write("\tbackground-color: transparent;\r\n");
      out.write("\tborder-collapse: collapse;\r\n");
      out.write("\tborder-spacing: 0;\r\n");
      out.write("\tmax-width: 100%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".table {\r\n");
      out.write("\ttext-align: left;\r\n");
      out.write("\tpadding: 6px 10px 6px 10px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".table th {\r\n");
      out.write("\ttext-align: right;\r\n");
      out.write("\tpadding: 6px 10px 6px 10px;\r\n");
      out.write("}\r\n");
      out.write(".table td {\r\n");
      out.write("    text-align: left;\r\n");
      out.write("\tpadding: 6px 10px 6px 10px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\" src=\"jsp/loanOrder/loanOrderBaseForm.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(function(){\r\n");
      out.write("\t// 获取要修改的用户的信息\r\n");
      out.write("\tvar $row = $grid.datagrid('getSelected');\r\n");
      out.write("\t//上传附件所需订单ID \r\n");
      out.write("\t// 渲染所有的下拉列表框信息\r\n");
      out.write("\tRenderCombox();\r\n");
      out.write("    //紧急联系家庭地址\r\n");
      out.write("    renderProvinceSelect('familyProvince', 'familyCity', 'familyArea');\r\n");
      out.write("    //紧急联系人单位地址\r\n");
      out.write("    renderProvinceSelect('compProvince', 'compCity', 'compArea');\r\n");
      out.write("    //工作单位\r\n");
      out.write("    renderProvinceSelect('dwProvince', 'dwCity', 'dwArea');\r\n");
      out.write("\t// 渲染修改订单信息的tab\r\n");
      out.write("\t$(\"#tt\").tabs({\r\n");
      out.write("\t\t onSelect:function(title,index){\r\n");
      out.write("\t\t\t if(0==index){\r\n");
      out.write("\t\t\t\t loadBaseInfo($row);//渲染基本信息\r\n");
      out.write("\t\t\t }else if(1==index){\r\n");
      out.write("\t\t\t\tlinkPeopleDatagrid();//紧急联系人列表\r\n");
      out.write("\t\t\t \t$('#linkPeople').datagrid('options').url = \"contacts/contactsAction!findAllListChacked.action\";\r\n");
      out.write("\t            $('#linkPeople').datagrid('reload',{\"loanerId\": $row.loanerId,\"loanOrderId\": $row.loanOrderId}); \r\n");
      out.write("\t\t\t }else if(2==index){\r\n");
      out.write("\t\t\t\tinitDatagrid();//工作单位列表\r\n");
      out.write("\t\t\t \t$('#dwDatagrid').datagrid('options').url = \"company/companyAction!findAllListChacked.action\";\r\n");
      out.write("\t            $('#dwDatagrid').datagrid('reload',{\"loanerId\": $row.loanerId,\"loanOrderId\": $row.loanOrderId}); \r\n");
      out.write("\t\t\t }else if(3==index){\r\n");
      out.write("\t\t\t\t loadLoanOrderInfo($row);//渲染订单的信息\r\n");
      out.write("\t\t\t }else if(4==index){\r\n");
      out.write("\t\t\t\tinitBankGrid();//开户行列表\r\n");
      out.write("\t\t\t \t$('#bankGrid').datagrid('options').url = \"accountInfo/accountInfoAction!findAllListChacked.action\";\r\n");
      out.write("\t            $('#bankGrid').datagrid('reload',{\"loanerId\": $row.loanerId,\"loanOrderId\": $row.loanOrderId}); \r\n");
      out.write("\t\t\t }else if(5==index){\r\n");
      out.write("\t\t\t } else if(6==index){\r\n");
      out.write("\t\t\t\tloadLoanerJoint($row);//渲染共同借款人的信息\r\n");
      out.write("\t\t\t }\r\n");
      out.write("\t\t }\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#loanOrderEditattType\").combobox({\r\n");
      out.write("\t\tvalueField : 'code',\r\n");
      out.write("\t\ttextField : 'text',\r\n");
      out.write("\t\turl:'common/commonAction!findTextArr.action?codeMyid=attachment_type',\r\n");
      out.write("\t\tonLoadSuccess : function(){\r\n");
      out.write("\t\t\tvar val = $(this).combobox(\"getData\");\r\n");
      out.write("\t\t\tif(!$.isEmptyObject(val)){\r\n");
      out.write("                $(this).combobox(\"select\", val[0][\"code\"]);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\teditable:false \r\n");
      out.write("    });\r\n");
      out.write("\t\r\n");
      out.write("})\r\n");
      out.write("\t// 渲染用户的基本信息\r\n");
      out.write("\tfunction loadBaseInfo(row){\r\n");
      out.write("\t\t$(\"#loanOrderId\").val(row.loanOrderId);\r\n");
      out.write("\t\t$(\"#loanerId\").val(row.loanerId);\r\n");
      out.write("\t\t//发送ajax，查询该贷款人的基本信息\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\t\turl : 'loaner/loanerAction!queryLoaner.action',\r\n");
      out.write("\t\t\t\ttype : 'POST',\r\n");
      out.write("\t\t\t\tdata : {'loanerId': row.loanerId},\r\n");
      out.write("\t\t\t\tdataType : 'JSON',\r\n");
      out.write("\t\t\t\tsuccess : function(row) {\r\n");
      out.write("\t\t\t\t\t$(\"#baseForm\").form(\"load\", row);\r\n");
      out.write("\t\t\t        renderProvinceSelect('hukouProvinceId','hukouCityId','hukouAreaId',row.hukouProvinceId,row.hukouCityId,row.hukouAreaId);\r\n");
      out.write("\t\t\t        renderProvinceSelect('curProvinceId','curCityId','curAreaId',row.curProvinceId,row.curCityId,row.curAreaId);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});  \r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//基本信息保存\r\n");
      out.write("\tfunction toSaveBaseInfo(idh,ids){\r\n");
      out.write("\t\tif(!$(\"#baseForm\").form('validate')){return false;}\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\tcache:true,\r\n");
      out.write("\t\t\ttype:'POST',\r\n");
      out.write("\t\t\turl:'loaner/loanerAction!persistenceLoaner.action',\r\n");
      out.write("\t\t\tdata:$('#baseForm').serialize(),\r\n");
      out.write("\t\t\tasync:false,\r\n");
      out.write("\t\t\tdataType:'JSON',\r\n");
      out.write("\t\t\tsuccess:function(res){\r\n");
      out.write("\t\t\t\tif(res.state){//保存成功\r\n");
      out.write("\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\ttitle : '提示',\r\n");
      out.write("\t\t\t\t\t\tmsg : \"恭喜你,保存成功!\",\r\n");
      out.write("\t\t\t\t\t\ttimeout : 4000 * 2\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$.messager.alert(\"提示\", '出错了，保存失败!',\"error\")\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//查看附件\r\n");
      out.write("\tfunction checkAttachment(){\r\n");
      out.write("\t\tvar loanOrderId = $(\"#loanOrderId\").val();\r\n");
      out.write("\t\tif(''==loanOrderId){\r\n");
      out.write("\t\t\t$.messager.alert(\"提示\",\"请先保存基本信息!\",\"info\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tcheckAttachementDetail('noauditId',loanOrderId,'','');\r\n");
      out.write("\t};\r\n");
      out.write("\t\r\n");
      out.write("\t//上传附件\r\n");
      out.write("\tfunction upploadAttachment(){\r\n");
      out.write("\t\tvar attType = $(\"#loanOrderEditattType\").combobox(\"getValue\");\r\n");
      out.write("\t\tvar loanOrderId = $(\"#loanOrderId\").val();\r\n");
      out.write("\t\tif(''==loanOrderId){\r\n");
      out.write("\t\t\t$.messager.alert(\"提示\",\"请先保存基本信息!\",\"info\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfileUploadsDlg(attType,loanOrderId,'');\r\n");
      out.write("\t};\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("<div class=\"easyui-layout\" data-options=\"fit:true,border:false\">\r\n");
      out.write("\t<div data-options=\"region:'center',border:false\" title=\"\">\r\n");
      out.write("\t\t<div id=\"tt\">\r\n");
      out.write("\t\t\t<div title=\"客户基本资料\" data-options=\"iconCls:'icon-cstbase'\"\r\n");
      out.write("\t\t\t\tstyle=\"padding: 10px\">\r\n");
      out.write("\t\t\t\t<div class=\"well well-small\" style=\"margin-left: 5px;margin-top: 5px;width: 850px;\">\r\n");
      out.write("\t\t\t\t   <form id=\"baseForm\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t\t<input id=\"loanerId\" name=\"loanerId\" type=\"hidden\"/><!-- 贷款人id -->\r\n");
      out.write("\t\t\t\t\t\t<input id=\"loanOrderId\" name=\"loanOrderId\" type=\"hidden\"/><!-- 订单id -->\r\n");
      out.write("\t\t\t\t\t\t<input id=\"createDate\" name=\"createDate\" type=\"hidden\"/> <!-- 创建时间 -->\r\n");
      out.write("\t\t\t\t\t\t<input id=\"sign\" type=\"hidden\" value=\"edit\"/><!-- 修改or保存状态标志 -->\r\n");
      out.write("\t\t\t\t\t\t<table class=\"table\">\r\n");
      out.write("\t\t\t\t\t\t   <tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>姓名:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input id=\"name\" name=\"name\" class=\"easyui-textbox easyui-validatebox\" type=\"text\" data-options=\"required:true,validType:'length[0,100]'\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t     <th>身份证号:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input id=\"idNo\" name=\"idNo\" type=\"text\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"easyui-textbox\" readonly=\"readonly\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>性别:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input name=\"genderType\" type=\"text\" class=\"easyui-textbox easyui-validatebox easyui-aa\" editable='false' panelHeight=\"auto\"/></td> \r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>手机:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input id=\"mobileTel\" name=\"mobileTel\" type=\"text\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'mobile'\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>住址电话:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input id=\"fixedTel\" name=\"fixedTel\" type=\"text\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'phone'\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>家庭电话:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input id=\"familyTel\" name=\"familyTel\" type=\"text\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'phone'\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>年龄:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input id=\"age\" name=\"age\" type=\"text\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'age'\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>婚姻状况:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input id=\"marriageType\" name=\"marriageType\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttype=\"text\" class=\"easyui-textbox easyui-validatebox easyui-aa\" editable='false' panelHeight=\"auto\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>有无子女:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input id=\"hasChild\" name=\"hasChild\" class=\"easyui-textbox easyui-validatebox easyui-aa\"type=\"text\" editable='false' panelHeight=\"auto\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>年收入(元):</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input id=\"annualSalary\" name=\"annualSalary\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttype=\"text\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'mDouble'\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>收入来源:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input id=\"incomeSrc\" name=\"incomeSrc\" type=\"text\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'length[0,100]'\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>居住情况:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input id=\"mortgageStatus\" name=\"mortgageStatus\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttype=\"text\" class=\"easyui-textbox easyui-validatebox easyui-aa\" editable='false' panelHeight=\"auto\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t    <th>月供(元):</th>\r\n");
      out.write("\t\t\t\t\t\t\t    <td>\r\n");
      out.write("\t\t\t\t\t\t\t       <input name=\"houseInstallPay\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'mDouble'\"/>\r\n");
      out.write("\t\t\t\t\t\t\t    </td>\r\n");
      out.write("\t\t\t\t\t\t\t    <th>房租(元):</th>\r\n");
      out.write("\t\t\t\t\t\t\t    <td>\r\n");
      out.write("\t\t\t\t\t\t\t       <input name=\"rent\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'mDouble'\"/>\r\n");
      out.write("\t\t\t\t\t\t\t    </td>\r\n");
      out.write("\t\t\t\t\t\t\t    <th>邮箱:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t   <input id=\"email\" name=\"email\" type=\"text\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:['email','length[0,300]']\"/>\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>QQ号:</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input id=\"qqNo\" name=\"qqNo\" type=\"text\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"easyui-textbox easyui-validatebox\" data-options=\"validType:'qq'\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t  <th>户籍地址:</th>\r\n");
      out.write("\t\t\t\t\t\t\t  <td colspan=\"5\">\r\n");
      out.write("\t\t\t\t\t\t\t            省:<input id=\"hukouProvinceId\" name=\"hukouProvinceId\" type=\"text\" class=\"easyui-combobox\" style=\"width: 100px;\"/>\r\n");
      out.write("\t\t\t\t\t\t\t            市:<input id=\"hukouCityId\" name=\"hukouCityId\" type=\"text\" class=\"easyui-combobox\" style=\"width: 100px;\"/>\r\n");
      out.write("\t\t\t\t\t\t\t     区/县:<input id=\"hukouAreaId\" name=\"hukouAreaId\" type=\"text\" class=\"easyui-combobox\" style=\"width: 100px;\"/>\r\n");
      out.write("\t\t\t\t\t\t\t        街道:<input name=\"hukouAddrDetails\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'length[0,250]'\" style=\"width: 303px;\"/>\r\n");
      out.write("\t\t\t\t\t\t\t  </td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t   <th>现住地址:</th>\r\n");
      out.write("\t\t\t\t\t\t\t   <td colspan=\"5\">\r\n");
      out.write("\t\t\t\t\t\t\t                  省:<input id=\"curProvinceId\" name=\"curProvinceId\" type=\"text\" class=\"easyui-combobox\" style=\"width: 100px;\"/>\r\n");
      out.write("\t\t\t\t\t\t\t                  市:<input id=\"curCityId\" name=\"curCityId\" type=\"text\" class=\"easyui-combobox\" style=\"width: 100px;\"/> \r\n");
      out.write("\t\t\t\t\t\t\t           区/县:<input id=\"curAreaId\" name=\"curAreaId\" type=\"text\" class=\"easyui-combobox\" style=\"width: 100px;\"/> \r\n");
      out.write("\t\t\t\t\t\t\t              街道:<input name=\"curAddrDetails\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true,validType:'length[0,250]'\" style=\"width: 303px;\"/>     \r\n");
      out.write("\t\t\t\t\t\t\t   </td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t   <td colspan=\"6\" style=\"text-align: right;\">\r\n");
      out.write("\t\t\t\t\t\t\t      <a href=\"javascript:void(0)\" id=\"save\" class=\"easyui-linkbutton\" iconCls=\"icon-save\" onclick=\"toSaveBaseInfo('save','edit');\">保存</a>\r\n");
      out.write("\t\t\t\t\t\t\t      <a href=\"javascript:void(0)\" id=\"edit\" class=\"easyui-linkbutton\" iconCls=\"icon-save\" style=\"display: none;\" onclick=\"ableForm();\">修改</a>\r\n");
      out.write("\t\t\t\t\t\t\t   </td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t        </div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div title=\"紧急联系人\" data-options=\"iconCls:'icon-help'\" style=\"padding: 10px\">\r\n");
      out.write("\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "loanOrderLinkPeople.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div title=\"工作单位\" data-options=\"iconCls:'icon-help'\" style=\"padding: 10px\">\r\n");
      out.write("\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "loanOrderWorkUnit.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div title=\"申请贷款信息\" data-options=\"iconCls:'icon-help'\" style=\"padding: 10px\">\r\n");
      out.write("\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "loanOrderInfoForm.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"banks\" title=\"开户行信息\" data-options=\"iconCls:'icon-help'\" style=\"padding: 10px\">\r\n");
      out.write("\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "loanOrderBankForm.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div title=\"附件信息\" data-options=\"iconCls:'icon-help'\" style=\"padding: 10px\">\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t<span style=\"font-weight: bold;margin-left: 30px;\">附件类型:</span>\r\n");
      out.write("\t\t\t\t<input id=\"loanOrderEditattType\" class=\"easyui-textbox easyui-combobox\" />\r\n");
      out.write("\t\t\t\t<a onclick=\"checkAttachment();\" href=\"javascript:void(0);\" class=\"easyui-linkbutton\">查看附件</a>\t\r\n");
      out.write("\t\t\t\t<a onclick=\"upploadAttachment();\" href=\"javascript:void(0);\" class=\"easyui-linkbutton\" >上传附件</a>\t\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div title=\"共同贷款人\" data-options=\"iconCls:'icon-help'\" style=\"padding: 10px\">\r\n");
      out.write("\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "loanOrderJointForm.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>");
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