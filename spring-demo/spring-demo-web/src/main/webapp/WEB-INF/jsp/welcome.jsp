<%@include file="taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	
</head>
<body>
 	
	<h1>Welcome!!</h1>
	<h3>${Welcome}</h3><a href="../<c:url value="j_spring_security_logout" />" >Logout</a>
	
	<sec:authorize access="hasRole('ROLE_USER')">
		User Can See This
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		Admin Can See This
	</sec:authorize>
	
</body>
</html>