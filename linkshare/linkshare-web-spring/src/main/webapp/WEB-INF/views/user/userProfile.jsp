<%@include file="../taglib.jsp"%>
<html>
<head>
	
<script type="text/javascript">

$(function() {
	$( "#tabs" ).tabs();

	$("#changePwdForm").submit(function(e){
		e.preventDefault();
		
		  var oldPwd = $.trim($("#oldPwd").val());
		  var newPwd = $.trim($("#newPwd").val());
		  var confPwd = $.trim($("#confPwd").val());
		  
		
		 if(oldPwd=="" || newPwd=="" || confPwd == "")
		 {
			alert('Please enter all the field values.');
			return;
		 }
		 if(newPwd!=confPwd){
			alert("New Password and Confirm Password doesn't match.");
			return;
		}
		 if(oldPwd == newPwd){
				alert("Old Password and New Password should not be same.");
				return;
		}
		  $.ajax( {
		      type: "POST",
		      url: 'changePwd.htm',
		      data: 'oldPwd='+oldPwd+'&newPwd='+newPwd,
		      success: function( response ) {
		        alert( response );
		        $("#oldPwd").val('');
		        $("#newPwd").val('');
		        $("#confPwd").val('');
		      }
		 } );
			
	});
	
});

</script>

	
	<style type="text/css">
			ul#icons {margin: 0; padding: 0;}
			ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
			ul#icons span.ui-icon {float: left; margin: 0 4px;}
		</style>
		
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
<div id="tabs">
	<ul>
		<li><a href="#tabs-1">User Details</a></li>
		<li><a href="#tabs-2">Change Password</a></li>
		<li><a href="#tabs-3">Posted Links</a></li>
	</ul>
	
	<div id="tabs-1">
		
	<div style="width:400px; margin-right:auto; margin-left:auto; border:1px solid #000; border-radius: 5px;">
	
	<form:form action="#" commandName="userProfile" method="post">
		<table>
			<tbody>							
				<tr>
					<td width="150" align="right">UserName* </td>
					<td><form:input path="userName" readonly="true" size="25"/>
					<form:errors cssStyle="color:red" path="userName"/>
					</td>
				</tr>
				<tr>
					<td width="150" align="right">EmailId </td>
					<td><form:input path="emailId" readonly="true" size="25"></form:input>
					<form:errors cssStyle="color:red" path="emailId"/></td>
				</tr>
				<!-- 
				<tr>
					<td width="150" align="right">Password* </td>
					<td><form:password path="password" size="25"></form:password>
					<form:errors cssStyle="color:red" path="password"/></td>
				</tr>
				 -->
				<tr>
					<td width="150" align="right"><form:checkbox path="userPreferences.subscribeToDailyLinkFeed" /></td>
					<td>Subscribe To Link Feed </td>
				</tr>
			</tbody>
		</table>
	</form:form>
	</div>
		
	</div>
	
	<div id="tabs-2">
		<div style="width:400px; margin-right:auto; margin-left:auto; border:1px solid #000; border-radius: 5px;">
	
	<form action="#" method="post" id="changePwdForm">
		<table>
			<tbody>							
				<tr>
					<td width="150" align="right">Current Password* </td>
					<td><input type="password" name="oldPwd" id="oldPwd" size="25"></td>
				</tr>
				<tr>
					<td width="150" align="right">New Password* </td>
					<td><input type="password" name="newPwd" id="newPwd" size="25"></td>
				</tr>
				<tr>
					<td width="150" align="right">Confirm Password* </td>
					<td><input type="password" name="confPwd" id="confPwd" size="25"></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="Submit">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
	</div>
	
	<div id="tabs-3">
	
	<div id="links-contain">
	<table class="ui-widget ui-widget-content">
		<thead>
		<tr class="ui-widget-header">
			<th>Links Posted By You</th>
		</tr>
		</thead>
		<c:if test="${empty USER_LINKS}">
		<tr>
			<td colspan="4">No Links found</td>
		</tr>
		</c:if>
		<c:if test="${! empty USER_LINKS}">
			<c:forEach var="link" items="${USER_LINKS}">		
		    <tr style="border: 1; border-collapse: collapse;">
				<td align="left">
					<font size="4.5px;"><a href="showLink.htm?linkId=${link.linkId}">${link.title}</a></font><br/>
					<font size="2px;">${link.description}</font><br/>
					<font size="1px;">Posted By: ${link.postedBy.userName} </font><br/> 
					<font size="1px;">Created On : <fmt:formatDate value="${link.createdOn}" pattern="dd/MM/yyyy hh:mm:ss"/></font>
				</td>				
							
			</tr>
			</c:forEach>
		</c:if>				
	</table>
	</div>
	
	</div>
	
</div>
	
		
</body>
</html>
