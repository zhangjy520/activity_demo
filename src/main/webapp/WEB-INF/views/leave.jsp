<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent" style="width: 566px;">
	<form method="post" action="${ctx }/account/askForLeave" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);" novalidate="novalidate">
		<div class="pageFormContent" layouth="56" style="height: 205px; overflow: auto;">
			<p>
				<label>请假天数：</label>
				<input name="day" type="text" size="30" value="" class="required textInput">
			</p>
			<p>
				<label>请假原因：</label>
				<input name="reason" class="required textInput" type="text" size="30" value="" alt="" id="reason">
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>