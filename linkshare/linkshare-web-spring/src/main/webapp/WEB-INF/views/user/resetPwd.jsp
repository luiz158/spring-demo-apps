<%@include file="../taglib.jsp"%>
<html>
<head>
	<title>Reset Password</title>
	<style>
		label, input { display:block; }
	</style>
	<script>
	$(function() {
		$( "input:submit" ).button();
		$( "input:button" ).button();
		
		$("#resetPwdForm").submit(function(e)
		{	
			
			  var emailId = $.trim($("#emailId").val());
			  var newPwd = $.trim($("#newPwd").val());
			  var confPwd = $.trim($("#confPwd").val());
			
			 if(emailId=="" || newPwd=="" || confPwd == "")
			 {
				e.preventDefault();
				alert('Please enter all the field values.');
				return;
			 }
			 
			 if(newPwd!=confPwd)
			 {
				e.preventDefault();
				alert("New Password and Confirm Password doesn't match.");
				return;
			 }
			 
				
		});
		
	});
	</script>
</head>

<body>
	<br/><br/>
	<div style="width:400px; margin-right:auto; margin-left:auto;">
		<font color="red">${message}</font>
	</div>
	<c:if test="${pwdRestToken != null }">
	<div class="ui-widget ui-widget-content ui-corner-all" 
		style="outline: 0px none; width: 400px; margin-right:auto; margin-left:auto;">
		<div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"
		style="width: auto; min-height: 30px; height: auto;">
			<span id="ui-dialog-title-dialog" class="ui-dialog-title">Reset Password Form</span>
		</div>
		<div class="ui-widget-content" style="width: auto; min-height: 113.4px; height: auto; border-top: none;">
			
			
			
			
			
					<form action="#" method="post" id="resetPwdForm">
					<table>
					<tbody>							
						<tr>
							<td width="150" align="right">Email Id* </td>
							<td><input type="text" name="emailId" id="emailId" size="25"></td>
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
	</c:if>
	<br/><br/>
	
</body>
</html>
