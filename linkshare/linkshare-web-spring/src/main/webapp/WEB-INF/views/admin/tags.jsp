<%@include file="../taglib.jsp" %>
<html>
<head>

</head>
<body>

<form action="<spring:url value="/admin/createTag"/>" method="post">		
			<table>
			<tr>
				<td>Tag Label</td> 
				<td><input type="text" name="label"/>
				</td>
			</tr>
			<tr>
				<td>Tag Value</td> 
				<td><input type="text" name="value"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Save"/>
				</td>
			</tr>
		</table>
	</form>
	
	<form action="<spring:url value="/admin/tags"/>" method="get">		
			<table>
			<tr>
				<td>Search By Tag Name</td> 
				<td><input type="text" name="query"/>
					&nbsp;&nbsp;<input type="submit" value="Search"/>
					
				</td>
			</tr>
		</table>
	</form>
	
	<table style="border-collapse: collapse;" border="1">
		<tr bgcolor="lightblue" style="border: 1; border-collapse: collapse;">
			<th>TagId</th>
			<th>Label</th>			
			<th>Value</th>	
			
		</tr>
		<c:if test="${empty TAGS_KEY}">
		<tr>
			<td colspan="4">No Tags found</td>
		</tr>
		</c:if>
		<c:if test="${! empty TAGS_KEY}">
			<c:forEach var="tag" items="${TAGS_KEY}">		
		    <tr style="border: 1; border-collapse: collapse;">
				<td><c:out value="${tag.tagId}"></c:out></td>
				<td><c:out value="${tag.label}"></c:out></td>
				<td><c:out value="${tag.value}"></c:out> </td>
			</tr>
			</c:forEach>
		</c:if>				
	</table>
	
</body>
</html>