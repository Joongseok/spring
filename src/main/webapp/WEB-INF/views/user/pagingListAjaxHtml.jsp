<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:forEach items="${data.userList}" var="user">
	<tr class="userTr" data-userid="${user.userId}">
		<td class="userId">${user.userId}</td>
		<td>${user.name}</td>
		<td>${user.alias}</td>
		<td></td>
	</tr>
</c:forEach>

SEPERATORSEPERATOR
<!--  내가 현재 몇번째 페이지에 있는가? -->
<c:choose>
	<c:when test="${pageVO.page  == 1}">
		<li class="disabled"><span>«</span></li>
	</c:when>
	<c:otherwise>
		<li><a
			href="javascript:userPagingListAjaxHtml(${pageVO.page - 1 },${pageVO.pageSize});">«</a></li>
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="1" end="${data.paginationSize}" step="1">
	<li><c:choose>
			<c:when test="${pageVO.page == i}">
				<li class="active"><span>${i }</span></li>
			</c:when>
			<c:when test="${pageVO.page != i}">
				<a
					href="javascript:userPagingListAjaxHtml(${i},${pageVO.pageSize});">${i}</a>
			</c:when>
		</c:choose></li>
</c:forEach>

<c:choose>
	<c:when test="${pageVO.page  == data.paginationSize}">
		<li class="disabled"><span>»</span></li>
	</c:when>
	<c:otherwise>
		<li><a
			href="javascript:userPagingListAjaxHtml(${pageVO.page + 1 },${pageVO.pageSize});">»</a></li>
	</c:otherwise>
</c:choose>

