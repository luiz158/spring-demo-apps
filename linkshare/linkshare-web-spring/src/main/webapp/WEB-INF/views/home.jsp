<%@include file="taglib.jsp" %>

<html>
<head>
	<style>
		label, input { display:block; }
		fieldset { padding:0; border:0; margin-top:2px; }
		div#links-contain { width: 695px; margin: 1px 0;}
		div#links-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#links-contain table td, div#links-contain table th 
		{ border: 1px solid #eee; padding: .6em 2px; text-align: left; }
		
	</style>	
</head>
<body>
 	<!-- 
 	<sec:authorize ifAllGranted="ROLE_USER,ROLE_ADMIN">
			(Admin)
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_USER , ROLE_ADMIN')">
		User
	</sec:authorize>
	<sec:authorize  ifAllGranted="ROLE_USER,ROLE_ADMIN">
			(User&Admin)
	</sec:authorize>
	<sec:authorize  ifAnyGranted="ROLE_USER,ROLE_ADMIN">
			(User/Admin)
	</sec:authorize>
	<sec:authorize  access="hasRole('ROLE_USER')">
			(User)
	</sec:authorize>
	<sec:authorize ifAllGranted="ROLE_ADMIN">
			(Admin)
	</sec:authorize>
	<sec:authorize ifNotGranted="ROLE_USER">
			Guest
	</sec:authorize>	
	
	 <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
			(User/Admin)
	</sec:authorize>
	-->
	<div>
		${message}
	</div>
	<c:set var="links" value="${ALL_LINKS.content}"></c:set>

<div id="links-contain" >
	<table class="ui-widget1 ui-widget-content1">
		<thead>
		<tr class="ui-widget-header">
			<th colspan="2">SivaLabs &gt;&gt; LinkShare</th>
		</tr>
		</thead>
		
		<c:if test="${empty links}">
		<tr>
			<td colspan="2">No Links found</td>
		</tr>
		</c:if>
		<c:if test="${! empty links}">
			<c:forEach var="link" items="${links}">
		    <tr style="border: 1; border-collapse: collapse;">
				<td valign="top" width="600px;">
					<font size="4.5px;"><a href="showLink.htm?linkId=${link.linkId}">${link.title}</a></font><br/>
					<font size="2px;">${link.description}</font><br/>
				</td>
				<td>
					<img alt="" src="resources/images/ananymous_user.jpg" width="60px" height="60px"><br/>
					<font size="1px;">${link.postedBy.userName} </font><br/> 
					<font size="1px;"><fmt:formatDate value="${link.createdOn}" pattern="dd/MM/yyyy hh:mm"/></font>
				
				</td>				
							
			</tr>
			</c:forEach>
		</c:if>	
		<tr>
			<td colspan="2" class="ui-widget-header"  style="text-align: center;">
				<font size="4">
					<a href="home.htm?page=0">First</a>
					
					
					<c:if test="${ALL_LINKS.hasPreviousPage()}">
						<a href="home.htm?page=${ALL_LINKS.number - 1}">Previous</a>
					</c:if>
					<c:if test="${not ALL_LINKS.hasPreviousPage()}">
						<a href="#">Previous</a>
					</c:if>					
					
					<c:if test="${ALL_LINKS.hasNextPage()}">
						<a href="home.htm?page=${ALL_LINKS.number + 1}">Next</a>
					</c:if>
					<c:if test="${not ALL_LINKS.hasNextPage()}">
						<a href="#">Next</a>
					</c:if>					
					 
					 <c:if test="${ALL_LINKS.totalPages > 0}">
						<a href="home.htm?page=${ALL_LINKS.totalPages -1}">Last</a>
					</c:if>
					
					<c:if test="${not ALL_LINKS.hasNextPage()}">
						<a href="#">Last</a>
					</c:if>
					
					</font>
			</td>
		</tr>			
	</table>
	</div>
</body>
</html>