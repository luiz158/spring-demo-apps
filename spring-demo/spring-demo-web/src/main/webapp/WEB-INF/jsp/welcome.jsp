<%@include file="taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	
</head>
<body>
 	
	<h1>Welcome!!</h1>
	<c:forEach var="user" items="${USERS}">
		<p>${user.email}</p>
	</c:forEach>
	
</body>
</html>