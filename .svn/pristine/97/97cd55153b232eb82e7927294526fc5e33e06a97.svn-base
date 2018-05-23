<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="pagerForm" method="post" action="${ctx }/account/myTask">
	<input type="hidden" name="pageNum" value="${tasks.current}" />
	<input type="hidden" name="numPerPage" value="10" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post" onreset="$(this).find('select.combox').comboxReset()">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					我的客户：<input type="text" name="keyword" />
				</td>
				<td>
					<select class="combox" name="province" ref="demo_combox_city" refUrl="demo/combox/city_{value}.html" reset-value="bj">
						<option value="all">所有省市</option>
						<option value="bj">北京</option>
						<option value="sh">上海</option>
						<option value="zj">浙江省</option>
					</select>
					<select class="combox" name="city" id="demo_combox_city" ref="demo_combox_region" refUrl="demo/combox/region_{value}.html">
						<option value="all">所有城市</option>
					</select>
					<select class="combox" name="region" id="demo_combox_region">
						<option value="all">所有区县</option>
					</select>
				</td>
				<td>
					<select class="combox" name="type">
						<option value="all">所有等级</option>
						<option value="1">1级</option>
						<option value="2">2级</option>
						<option value="3" selected>3级</option>
					</select>
				</td>
				<td class="dateRange">
					建档日期:
					<input name="startDate" class="date readonly" readonly="readonly" type="text" value="">
					<span class="limit">-</span>
					<input name="endDate" class="date readonly" readonly="readonly" type="text" value="">
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="reset">重置</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80">任务名</th>
				<th width="200">申请日期</th>
				<th>请假人</th>
				<th width="100">请假天数</th>
				<th width="150">请假原因</th>
				<th width="80" align="center">priority</th>
				<th width="80">category</th>
				<th width="80">formKey</th>
				<th width="200" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tasks.records}" var="task">
			<tr target="sid_user" rel="1">
				<td>${task.name}</td>
				<td><fmt:formatDate value="${task.processVariables.askTime}" type="both" pattern="yyyy/MM/d HH:mm:ss EEEE"/></td>
				<td>${task.processVariables.name}</td>
				<td>${task.processVariables.day}</td>
				<td>${task.processVariables.reason}</td>
				<td>${task.priority}</td>
				<td>${task.assignee}</td>
				<td>${task.formKey}</td>
				<td>
					<div>
						<a title="确认通过？" target="ajaxTodo" href="${ctx }/account/complete?taskId=${task.id}" class="">通过审批</a>
					</div>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="150">150</option>
				<option value="200">200</option>
				<option value="250">250</option>
			</select>
			<span>条，共${tasks.total}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${tasks.total}" numPerPage="20" pageNumShown="10" currentPage="${tasks.current}"></div>

	</div>
</div>