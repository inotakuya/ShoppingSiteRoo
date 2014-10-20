<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${items }" var="item">
		<a href="items/${item.id }"> <IMG alt="${item.itemName }"
			src="items/${item.id }/image" width="200" />
			
				<c:out value="${item.itemName }" />
			 <span> <c:out value="${item.price }" />円
		</span>
		</a>
		<br />
		<br />
	</c:forEach>

</body>
</html>