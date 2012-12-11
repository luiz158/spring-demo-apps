<%@include file="../taglib.jsp"%>
<html>
<head>
	<style>
		label, input { display:block; }
	</style>
	<script>
	$(function() {
		//$( "#dob" ).datepicker({dateFormat: 'dd/mm/yy'});
		$( "input:submit" ).button();
		$( "input:button" ).button();

		$("#loginBtn").click(function(e){
			//alert('forgotPwd');
			window.location = "login.htm";
		});
		
	});
	</script>
</head>
<body>
<br/><br/>

	<div class="ui-widget ui-widget-content ui-corner-all" 
		style="outline: 0px none; width: 400px; margin-right:auto; margin-left:auto;">
		<div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"
		style="width: auto; min-height: 30px; height: auto;">
			<span id="ui-dialog-title-dialog" class="ui-dialog-title">Registration Form</span>
		</div>
		<div class="ui-widget-content" style="width: auto; min-height: 113.4px; height: auto; border-top: none;">
		
	<form:form action="registration.htm" commandName="user" method="post">
		<table>
			<tbody>
				
				<tr>
					<td width="150" align="right">UserName* </td>
					<td><form:input path="userName" title="UserName" size="25"/>
					<form:errors cssStyle="color:red" path="userName"/>
					</td>
				</tr>
				<tr>
					<td width="150" align="right">Password* </td>
					<td><form:password path="password" size="25"></form:password>
					<form:errors cssStyle="color:red" path="password"/></td>
				</tr>
				<tr>
					<td width="150" align="right">EmailId* </td>
					<td><form:input path="emailId" size="25"></form:input>
					<form:errors cssStyle="color:red" path="emailId"/></td>
				</tr>
				<tr>
					<td width="150" align="right"><form:checkbox path="userPreferences.subscribeToDailyLinkFeed" /></td>
					<td align="left">Subscribe To Link Feed </td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td  width="150" align="right">
						<input id="loginBtn" value="Cancel" type="button">
						<input name="" value="Register" type="submit">						
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<form:errors cssStyle="color:red" />
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
	</div>
	</div>	
	<br/><br/>
	
</body>
</html>
