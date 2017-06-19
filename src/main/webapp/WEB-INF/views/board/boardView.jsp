<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${ kind }View</h1>
	
	<h6>num : ${ view.num }</h6>
	<h6>writer : ${ view.writer }</h6>
	<h6>title : ${ view.title }</h6>	
	<h6>contents : ${ view.contents }</h6>
	<h6>hit : ${ view.hit }</h6>
	
	<a href="boardUpdate?num=${ view.num }">Update</a>
	<a href="boardDelete?num=${ view.num }">Delete</a>
</body>
</html>