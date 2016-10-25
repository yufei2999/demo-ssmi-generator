<%@page import="${basepackage}.model.*" %>
<#include "/macro.include"/>
<#include "/custom.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
	<meta charset="UTF-8">
	<title><%=${className}.TABLE_ALIAS%>编辑</title>
	<link type="text/css" rel="stylesheet" href="<@jspEl 'ctx'/>/static/css/themes/default/easyui.css">
	<link type="text/css" rel="stylesheet" href="<@jspEl 'ctx'/>/static/css/themes/icon.css">
	<link type="text/css" rel="stylesheet" href="<@jspEl 'ctx'/>/static/css/default.css">
	<script type="text/javascript" src="<@jspEl 'ctx'/>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<@jspEl 'ctx'/>/static/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<@jspEl 'ctx'/>/static/js/locale/easyui-lang-zh_CN.js"></script>
	<script>
		function submitForm(){
			$('#ff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},success:function(data){
					var result = $.parseJSON(data);
					if(result.code=="0000"){
						$.messager.alert('提示', "操作成功！", 'info',function(){
							window.location.href="<@jspEl 'ctx'/>${actionBasePath}/list.do";
						});
					}else{
						$.messager.alert('提示', result.message, 'info');
					}
				}
			});
		}
		function resetForm(){
			$('#ff').form('reset');
		}
		function goBack(){
			window.location='<@jspEl 'ctx'/>${actionBasePath}/list.${actionExtension}';
		}
	</script>
</head>

<body>
<h2><%=${className}.TABLE_ALIAS%>编辑</h2>
<p>请填写完整信息并保存.</p>
<div style="margin:20px 0;"></div>
<div class="easyui-panel" title="信息编辑" style="width:600px">
	<div style="padding:10px 60px 20px 60px">
		<form id="ff" action="<@jspEl 'ctx'/>${actionBasePath}/update.${actionExtension}" method="post" class="easyui-form" data-options="novalidate:true">
	<#list table.columns as column>
		<#if column.htmlHidden>
			<input type="hidden" id="${column.columnNameLower}" name="${column.columnNameLower}" value="<@jspEl 'model.'+column.columnNameLower/>"/>
		</#if>
	</#list>
		<table cellpadding="5" style="font-size: 12px">
	<!-- ONGL access static field: @package.class@field or @vs@field -->
	<#list table.columns as column>
		<#if !column.htmlHidden>
			<tr>
				<td>
					<%=${className}.ALIAS_${column.constantName}%>:
				</td>
				<td>
				<#if column.isDateTimeColumn>
					<input value="<@jspEl 'model.'+column.columnNameLower+'String'/>" id="${column.columnNameLower}String" name="${column.columnNameLower}String"
						   type="text" class="easyui-datetimebox"  editable="false"  data-options="prompt:'请输入<%=${className}.ALIAS_${column.constantName}%>'<#if !column.nullable>,required:true</#if>" />
				<#else>
					<#if column.isNumberColumn>
					<input id="${column.columnNameLower}" name="${column.columnNameLower}" value="<@jspEl 'model.'+column.columnNameLower/>"
						   type="text"  class="easyui-numberbox"  data-options="prompt:'请输入<%=${className}.ALIAS_${column.constantName}%>'<#if !column.nullable>,required:true</#if>,min:0,max:10000" />
					<#else>
					<input id="${column.columnNameLower}" name="${column.columnNameLower}" value="<@jspEl 'model.'+column.columnNameLower/>"
						   type="text"  class="easyui-textbox"  data-options="prompt:'请输入<%=${className}.ALIAS_${column.constantName}%>'<#if !column.nullable>,required:true</#if>,validType:'length[0,50]'" />
					</#if>
				</#if>
				</td>
			</tr>
		</#if>
	</#list>
		</table>
		</form>
		<div style="text-align:center;padding:20px">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="submitForm()">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="resetForm()">重置</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" onclick="goBack()">返回</a>
		</div>
	</div>
</div>
</body>
</html>
