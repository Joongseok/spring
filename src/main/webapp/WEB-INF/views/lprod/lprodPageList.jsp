<%@page import="kr.or.ddit.lprod.model.LprodVO"%>
<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>lprodList</title>
<style>
	.lprodTr:hover{
		cursor: pointer;
	}
</style>
<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp"%>

<script>
	$(document).ready(function() {
		$(".prodTr").on('click', function(){
			var prod_buyer = $(this).data("prod_buyer");
			alert(prod_buyer)
			$("#prod_buyer").val(prod_buyer);
			$("#frm").submit();
			
		});
		
		$("#select").val("${lprod_gu}")
		$("#select").change(function (){
			$("#lprod_gu").val($(this).val())
			$("#lprodfmt").submit();
		});
	});

</script>

</head>

<body>
	<!-- header -->
	<%@include file="/WEB-INF/views/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@include file="/WEB-INF/views/common/left.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">lprod</h2>
						<form id="lprodfmt" action="${cp}/lprod/pagingList" method="get">
							<input type="hidden" id="lprod_gu" name="lprod_gu" />
							<select id="select">
								<c:forEach items="${lprodList}" var="lprod">
									<option value="${lprod.lprod_gu }">${lprod.lprod_nm}</option>
								</c:forEach>
							</select>
						</form>
						<form id="frm" action="${cp}/lprod/prod" method="get">
							<input type="hidden" id="prod_buyer" name="prod_buyer">
						</form>
						
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>제품 이름</th>
									<th>가격</th>
									<th>등록일자</th>
								</tr>

								<c:forEach items="${prodList }" var="prod">
									<tr class="prodTr" data-prod_buyer="${prod.prod_buyer}">
										<td>${prod.prod_name}</td>
										<td>${prod.prod_sale }</td>
										<td><fmt:formatDate value="${prod.prod_insdate }" pattern="yy/MM/dd"/></td>
										<td></td>
									</tr>
								</c:forEach>
							</table>
						</div>

						<div class="text-center">
							<ul class="pagination">
								<c:choose>
									<c:when test="${pageVo.page == 1 }">
										<li class="disabled"><span>«</span></li>
									</c:when>
									<c:otherwise>
										<li><a href="${cp}/lprod/pagingList?page=${pageVo.page - 1 }&pageSize=${pageVo.pageSize}">«</a></li>
									</c:otherwise>
								</c:choose>
								
								<c:forEach var="i" begin="1" end="${paginationSize}" step="1">
									<li> 
									<c:choose>    
										<c:when test="${pageVo.page == i}">
											<li class="active" ><span>${i }</span> </li>
										</c:when>
										<c:when test="${pageVo.page != i}">
											<a href="${cp}/lprod/pagingList?page=${i}&pageSize=${pageVo.pageSize}">${i}</a>
										</c:when>
									</c:choose>
									</li>
								</c:forEach>	
								
								<c:choose>
									<c:when test="${pageVo.page == paginationSize }">
										<li class="disabled"><span>»</span></li>
									</c:when>
									<c:otherwise>
										<li><a href="${cp}/lprod/pagingList?page=${pageVo.page + 1 }&pageSize=${pageVo.pageSize}">»</a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
