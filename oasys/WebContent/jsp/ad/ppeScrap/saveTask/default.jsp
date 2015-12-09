<%@page import="com.oasys.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    <form id="taskForm" name="taskForm"  method="post" style="width: 800px;margin-left:5px;">
    <input id="applyStr" name="applyStr" type="hidden"  value="<%= Constants.APPLY_FOR_ADJUSTMENT %>" />
    <table class="table"  width="95%">
				<tr>
					<th align="right">申请人:</th>
					<td>
						<input id="userName" name="userName" type="text"  class="easyui-textbox"  disabled="disabled" />
					</td>				
					<th align="right">申请部门:</th>						
					<td>
						<input id="orgName" name="orgName" type="text"   class="easyui-textbox"  disabled="disabled"  />
					</td>
					<th align="right">申请时间:</th>
					<td>
						<input name="appDate" id="appDate" class="easyui-datebox"  editable="false" style="width:174px;" disabled="disabled" />
					</td>
				</tr>
				<tr>
					<th colspan="1" align="right">*审批意见</th>
					<td colspan="5"></td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="5"><textarea name="taskComment" id="taskComment" class="easyui-textbox" style="width: 95%; height: 90px;" data-options="required:true,validType:'length[0,200]'"></textarea></td>
				</tr>
				<tr>
					<td colspan="3"></td>
				   <td colspan="3" align="right">
						 <jsp:include page="/jsp/ad/taskHiddenJsp.jsp" flush="true" />
				   </td>
				</tr>	
				<tr>
					<td colspan="6">
						<jsp:include page="/jsp/ad/optionsList.jsp" flush="true" />
					</td>
				</tr>									
			</table>
    </form>
