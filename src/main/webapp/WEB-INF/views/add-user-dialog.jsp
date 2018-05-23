<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
	<form method="post" action="${ctx }/user/add.json" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">

			<div class="unit">
				<label>登录名：</label>
				<input type="text" name="loginName" size="30" minlength="3" maxlength="20" class="required" />
			</div>
			<div class="unit">
				<label>真实姓名：</label>
				<input type="text" name="name" size="30" class="required"/>
			</div>
			<div class="unit">
				<label>角色：</label>
				<select class="combox" name="roleCode">
					<c:forEach items="${roles}" var="role">
					<option value="${role.roleCode}">${role.dec}</option>
					</c:forEach>
				</select>
			</div>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

