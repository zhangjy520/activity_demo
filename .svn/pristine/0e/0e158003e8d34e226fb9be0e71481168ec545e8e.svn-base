<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div>
<p>请假天数：${processInstance.processVariables.day}天</p>
<p>请假原因：${processInstance.processVariables.reason}</p>
<p>申请时间：<fmt:formatDate value="${processInstance.processVariables.askTime}" type="both" pattern="yyyy/MM/d HH:mm:ss EEEE"/></p>
<p><img src="${ctx }/bpmn/showBpmnImg?processId=${processInstance.id}"/></p>
</div>