<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 高级查询 userSearchDlg.jsp-->
<div id="emergencyContacterSearchDlg">
	<form id="tbProcessStatusSearchFm" method="post">
		<table>
			<tr>
				<th>条件</th>
				<th>字段名</th>
				<th>条件</th>
				<th>值</th>
			</tr>
			<tr>
				<td>
					<div class="gradeSearchBox">
					<select name="searchAnds" class="gradeSelectSearchBox"> 
						<option value="and">并且</option>
						<option value="or">或者</option>
					</select>
					</div> 
				</td>
				<td>
				<div class="gradeSearchBox">
					<select name="searchColumnNames" class="gradeSelectSearchBox">
						<option value="id">ID</option>
						<option value="statusCode">状态码</option>
						<option value="statusName">状态名</option>
						<option value="description">描述</option>
						<option value="isUsed">是否启用</option>
						<!-- <option value="business">所属业务</option> -->
					</select>
					</div> 
				</td>
				<td>
				<div class="gradeSearchBox">
					<select name="searchConditions" class="gradeSelectSearchBox">
						<option value="=">等于</option>
						<option value="<>">大于小于</option>
						<option value="<">小于</option>
						<option value=">">大于</option>
						<option value="like">模糊</option>
					</select>
					</div> 
				</td>
					<td><input class="easyui-textbox easyui-validatebox" name="searchVals" size="18"> <a style="display: none;" href="javascript:void(0);" onclick="userSearchRemove(this);">删除</a>
				</td>
			</tr>
		</table>
	</form>
</div>
