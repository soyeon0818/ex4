<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$(".go").click(function() {
			document.frm.curPage.value = $(this).attr("id");
			document.frm.search.value = '${ listInfo.search }';
			document.frm.find.value = '${ listInfo.find }';
			document.frm.submit();
			
			/* var curPage = $(this).attr("id");
			location.href= "${board}List?curPage=" + curPage + "&search=${listInfo.search}&find=${listInfo.find}"; */
		})
	});
	
	var m = '${ message }';
	
	if(m != "") {
		alert(m);
	}
</script>
</head>
<body>
	<h1>${ board }List</h1>
	
	<div>
		<form action="${ board }List" name="frm">
			<input type="hidden" name="curPage">
			<select name="search">
				<option value="title">TITLE</option>
				<option value="writer">WRITER</option>
				<option value="contents">CONTENTS</option>
			</select>
			<input type="text" name="find">
			<input type="submit" value="SEARCH">
		</form>
	</div>
	
	
	<table class="table table-striped">
		<thead>
			<tr>
				<th>NUM</th>
		        <th>TITLE</th>
		        <th>WRITER</th>
		        <th>DATE</th>
		        <th>HIT</th>
     		</tr>
    	</thead>
    	<tbody>   	
		    <c:forEach items="${ list }" var="dto">
				<tr>
					<td> 
					<c:catch>
						<c:forEach begin="1" end="${ dto.depth }">
							-
						</c:forEach>
					</c:catch>
					${ dto.num }</td>
					<td><a href="${ board }View?num=${ dto.num }">${ dto.title }</a></td>
					<td>${ dto.writer }</td>
					<td>${ dto.reg_date }</td>
					<td>${ dto.hit }</td>
				</tr>
			</c:forEach>
		</tbody>
  	</table>
  	
  	<c:if test="${ listInfo.curBlock > 1 }">
  		<span class="go" id="${listInfo.startNum - 1}">이전</span>
  	</c:if>
	<c:forEach begin="${ listInfo.startNum }" end="${ listInfo.lastNum }" var="i">
		<span class="go" id="${ i }">${ i }</span>
	</c:forEach>  	
  	<c:if test="${ listInfo.curBlock < listInfo.totalBlock }">
  		<span class="go" id="${listInfo.lastNum + 1}">다음</span>
  	</c:if>
  	
  	<div>
		<a href="${ board }Write">Write</a>
	</div>
</body>
</html>