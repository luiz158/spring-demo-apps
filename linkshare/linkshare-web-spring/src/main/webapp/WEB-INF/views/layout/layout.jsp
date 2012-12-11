<!DOCTYPE html>

<%@page import="com.sivalabs.linkshare.entities.UserAccount"%>
<%@page import="com.sivalabs.linkshare.web.WebUtils"%>  
<%@ include file="../taglib.jsp" %>

<html lang="en">
<head>
	<title><decorator:title default="Welcome!" /></title>
	<link type="text/css" href='<spring:url value="/resources/css/style.css"/>' rel="stylesheet" />
	<link type="text/css" href='<spring:url value="/resources/jquery/css/redmond/jquery-ui-1.8.21.custom.css"/>' rel="stylesheet" />	
	<script type="text/javascript" src='<spring:url value="/resources/jquery/js/jquery-1.7.2.min.js"/>'></script>
	<script type="text/javascript" src='<spring:url value="/resources/jquery/js/jquery-ui-1.8.21.custom.min.js"/>'></script>
	<script>
	$(function() {
		$( "input:submit" ).button();
		$( "#siteLogo").mouseover(function(){
			$(this).addClass('cursor');
		});
		$( "#siteLogo").click(function(){
			window.location = '<spring:url value="/home.htm"/>';
		});
	});
	</script>
	
	<decorator:head/>
		
</head>
<body>

<div align="center">
<div class="site">
	<table class="tbl">
		<tbody>		  
		 <tr>
		 	<td colspan="2" class="header" >
		 		<span id="siteLogo">LinkShare</span>
		 	</td>
		 </tr>	
		 <tr>
		 	<td colspan="2" class="userBanner">
		 		<span class="textLabel"> 
		 		
				 <%
 		 						 	boolean userLoggedin = WebUtils.isUserLoggedIn(session);
 		 						 		 	if(userLoggedin)
 		 						 		 	{  		
 		 						 		 		UserAccount user = WebUtils.getLoggedInUser(session);
 		 						 %>
				 					<a class="link" style="text-decoration: underline;" href='<spring:url value="/home.htm"/>'>
				 					Home
				 					</a> |
				 					 <a class="link" style="text-decoration: underline;" href='<spring:url value="/showUserProfile.htm"/>'>
				 					<%=user.getUserName()%>
				 					</a> | 
				 				<a class="link" style="text-decoration: underline;" href='<spring:url value="/createLink.htm"/>'>Add Link</a>
				 				| <a class="link" style="text-decoration: underline;" href='<spring:url value="/admin/home"/>'>Admin</a>
				 				| <a class="link" style="text-decoration: underline;" href="<spring:url value='/j_spring_security_logout'/>">Logout</a>
				 	<%}
				 	else
				 	{ %> 	
				 	
					<a class="link" style="text-decoration: underline;" href='<spring:url value="/login.htm"/>'>Login</a> | 
					<a class="link" style="text-decoration: underline;" href='<spring:url value="/registration.htm"/>'>Register</a>
				
				 	<%}
				 %>
				 </span>
		 	</td>
		 </tr>
		 <tr>
		    <td valign="top" colspan="2">
		    	<decorator:body/>	
		    </td>
		 </tr>
		 <tr class="footer" height="25">
	    	<td colspan="2">
	    		<a class="link" href="http://www.sivalabs.blogspot.com" target="_blank">
					http://www.sivalabs.blogspot.com. Version 1.0.2.SNAPSHOT
				</a>
	   		 </td>
		 </tr>
		</tbody>
	</table>	
</div>
</div>
</body>
</html>