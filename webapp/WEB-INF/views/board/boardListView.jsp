<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <!-- jQuery 라이브러리 연결 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    
<style>
	#list-area {
		border : 1px solid white;
		text-align : center;
	}
	
	.outer a {
		color : white;
		text-decoration : none;
	}
</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
	
	<div class="outer" align="center">
	
		<br>
		<h1 align="center">게시판</h1>
		<br>
		<%-- 
			검색 기능 구현 시 동적 SQL을 사용하는 경우
			* MyBatis의 동적 쿼리(동적 SQL) => 상황에 따라 분기 처리를 통해 SQL문을 동적으로 구성
										   문법은 JSTL과 유사	
		 --%>
		<%--
		<div id="search-area">
			<form action="search.bo" method="get">
				<input type="hidden" name="currentPage" value="1"> <!-- 페이징 처리를 위해 넘기는 값 -->
				<select name="condition">
					<option value="writer">작성자</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select>
				<input type="text" name="keyword" value="${ keyword }">
				<!-- 검색 시 검색어 유지를 위해 keyword를 넣어 줌! 만약 keyword가 없는 상태라면 안 뽑고 말기 때문에 검색했을 때만 이용 가능! -->
				<button type="submit">검색</button>
			</form>
		</div>
		--%>
		
		<%--
			검색 기능 구현 시 정적 바인딩을 사용하는 경우
			* MyBatis의 동적 바인딩 => #{} : 문자열의 경우 양사이드에 홑따옴표가 붙어서 구멍이 메꿔짐
					     정적 바인딩 => ${} : 문자열의 경우 양사이드에 홑따움표가 붙지 않고 구멍이 메꿔짐
					     				(보안이 상당히 취약한 방식으로 권장되지 않음!)
		--%>
		
		<div id="search-area">
			<form action="search.bo" method="get">
				<input type="hidden" name="currentPage" value="1">
				<select name="condition">
					<option value="USER_ID">작성자</option>
					<option value="BOARD_TITLE">제목</option>
					<option value="BOARD_CONTENT">내용</option>
				</select>
				<input type="text" name="keyword" value="${ keyword }">
				<button type="submit">검색</button>
			</form>
		</div>
		
		<!-- 검색 시 검색어 유지를 위해 추가함 -->
		<c:if test="${ not empty condition }">
			<script>
				$(function() {
					$("#search-area option[value=${ condition }]").attr("selected", true);
				});
			</script>
		</c:if>

		
		<br>
		<table id="list-area">
			<thead>
				<tr>
					<th>글번호</th>
					<th width="400">제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="b" items="${ list }">
					<tr>
						<td>${ b.boardNo }</td>
						<td><a href="detail.bo?bno=${ b.boardNo }">${ b.boardTitle }</a></td>
						<td>${ b.boardWriter }</td>
						<td>${ b.count }</td>
						<td>${ b.createDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		
		<div id="paging-area">
			<c:if test="${ pi.currentPage ne 1 }">
				<c:choose>
					<c:when test="${ empty condition }">
					
						<a href="list.bo?currentPage=${ pi.currentPage - 1 }">[이전]</a>
						
					</c:when>
					<c:otherwise>
					
						<a href="search.bo?currentPage=${ pi.currentPage - 1 }&condition=${ condition }&keyword=${ keyword }">[이전]</a>
					
					</c:otherwise>
				</c:choose>
			</c:if>
			
			<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }" step="1">
			
				<c:choose>
					<c:when test="${ empty condition }"> <!-- 전체 조회일 경우: list.bo로 페이지 이동할 수 있게끔 -->
									
						<a href="list.bo?currentPage=${ p }">[${ p }]</a>
						
					</c:when>
					<c:otherwise> <!-- 검색 조회일 경우: search.bo로 페이지 이동할 수 있게끔 -->
						
						<a href="search.bo?currentPage=${ p }&condition=${ condition }&keyword=${ keyword }">[${ p }]</a>
						
					</c:otherwise>
				</c:choose>

			</c:forEach>
			
			<c:if test="${ pi.currentPage ne pi.maxPage }">
				<c:choose>
					<c:when test="${ empty condition }">
						<a href="list.bo?currentPage=${ pi.currentPage + 1 }">[다음]</a>
					</c:when>
					<c:otherwise>
						<a href="search.bo?currentPage=${ pi.currentPage + 1 }&condition=${ condition }&keyword=${ keyword }">[다음]</a>
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>
		
	</div>

</body>
</html>