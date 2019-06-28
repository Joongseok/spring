<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.userTr:hover {
	cursor: pointer;
}
</style>

<script>
	$(document).ready(function() {
		//  사용자 tr태그 이벤트 등록
		$("#excelDown").on("click", function() {
			$("#frm").attr("action", "${cp}/user/userListExcel");
			$("#filename").val("userList")
			$("#frm").submit();
		})
		$(".userTr").on("click", function() {
			console.log("userTr click");

			//userId를 획득하는 방법
			// 			$(this).find(".userId").html();
			// 			$(this).data("userid");

			// 사용자 아이디를 #userId 값으로 설정해주고
			var userId = $(this).find(".userId").text();
			$("#userId").val(userId);

			//#frm 을 이용하여 submit();
			$("#frm").submit();
		});
	});
</script>


<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자(tiles)</h2>

		<!-- 사용자 상세 조회 : userId가 필요 -->
		<form id="frm" action="${cp}/user/user" method="get">
			<input type="hidden" id="userId" name="userId"> <input
				type="hidden" id="filename" name="filename">

		</form>

		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>사용자 아이디</th>
					<th>사용자 이름</th>
					<th>사용자 별명</th>
					<th>등록일시</th>
				</tr>

				<c:forEach items="${userList}" var="user">
					<tr class="userTr" data-userid="${user.userId}">
						<td class="userId">${user.userId}</td>
						<td>${user.name}</td>
						<td>${user.alias}</td>
						<td></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<a class="btn btn-default pull-right" href="${cp }/user/form">사용자
			등록</a>
		<div class="text-center">
			<ul class="pagination">
				<!--  내가 현재 몇번째 페이지에 있는가? -->
				<c:choose>
					<c:when test="${pageVo.page  == 1}">
						<li class="disabled"><span>«</span></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${cp}/user/pagingList?page=${pageVo.page - 1 }&pageSize=${pageVo.pageSize}">«</a></li>
					</c:otherwise>
				</c:choose>

				<c:forEach var="i" begin="1" end="${paginationSize}" step="1">
					<li><c:choose>
							<c:when test="${pageVo.page == i}">
								<li class="active"><span>${i }</span></li>
							</c:when>
							<c:when test="${pageVo.page != i}">
								<a
									href="${cp}/user/pagingList?page=${i}&pageSize=${pageVo.pageSize}">${i}</a>
							</c:when>
						</c:choose></li>
				</c:forEach>

				<c:choose>
					<c:when test="${pageVo.page  == paginationSize}">
						<li class="disabled"><span>»</span></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${cp}/user/pagingList?page=${pageVo.page + 1 }&pageSize=${pageVo.pageSize}">»</a></li>
					</c:otherwise>
				</c:choose>
			</ul>

			<input id="excelDown" type="button" value="엑셀다운" />
			<c:forEach items="${userList }" var="user">
								${user }<br>
			</c:forEach>
		</div>
	</div>
</div>

