<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
	
	<div class="outer">
	
		<br>
		<h1 align="center">회원가입</h1>
		
		<!-- 
			현재 나의 url: http://localhost:8888/mybatis/enrollForm.me
			요청하고자 하는 url: http://localhost:8888/mybatis/insert.me
			
			절대 경로 방식: /mybatis/insert.me
			상대 경로 방식: insert.me
			
		-->
		<form action="insert.me" method="post">
		<!-- 회원가입form안에.txt -->
			<table align="center">
				<tr>
					<td>* ID</td>
					<td><input type="text" name="userId" required></td>
				</tr>
				<tr>
					<td>* PWD</td>
					<td><input type="password" name="userPwd" required></td>
				</tr>
				<tr>
					<td>* NAME</td>
					<td><input type="text" name="userName" required></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;EMAIL</td>
					<td><input type="email" name="email"></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;BIRTHDAY</td>
					<td><input type="text" name="birthday" placeholder="생년월일(6자리)"></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;GENDER</td>
					<td align="center">
						<input type="radio" name="gender" value="M" checked> 남
						<input type="radio" name="gender" value="F"> 여
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;PHONE</td>
					<td><input type="text" name="phone" placeholder="-포함"></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;ADDRESS</td>
					<td><input type="text" name="address"></td>
				</tr>
			</table>
			<br>
			<div align="center">
				<button type="reset">초기화</button>
				<button type="submit">회원가입</button>
			</div>
		</form>
			
	</div>

</body>
</html>