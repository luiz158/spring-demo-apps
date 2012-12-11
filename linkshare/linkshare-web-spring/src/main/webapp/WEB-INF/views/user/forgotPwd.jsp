<%@include file="../taglib.jsp"%>
<html>
<head>
	<title>Forgot Password</title>
	<style>
		label, input { display:block; }
	</style>
	<script>
	$(function() {
		$( "input:submit" ).button();
		$( "input:button" ).button();
		
		$("#forgotPwdForm").submit(function(e)
		{
			  var emailId = $.trim($("#emailId").val());
			 
			 if(emailId=="")
			 {
				e.preventDefault();
				alert('Please enter Email Id');
			 }
			 
		});

		$("#registerBtn").click(function(e){
			//alert('Register');
			window.location = "registration.htm";
		});

		$("#loginBtn").click(function(e){
			//alert('forgotPwd');
			window.location = "login.htm";
		});
		
	});
	</script>
</head>

<body>
	<br/><br/>
	<div style="width:400px; margin-right:auto; margin-left:auto;">
		<font color="red">${message}</font>
	</div>
	<div class="ui-widget ui-widget-content ui-corner-all" 
		style="outline: 0px none; width: 400px; margin-right:auto; margin-left:auto;">
		<div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"
		style="width: auto; min-height: 30px; height: auto;">
			<span id="ui-dialog-title-dialog" class="ui-dialog-title">Forgot Password Form</span>
		</div>
		<div class="ui-widget-content" style="width: auto; min-height: 113.4px; height: auto; border-top: none;">
			<form action="forgotPwd.htm" method="post" id="forgotPwdForm">
				<table>
					<tbody>							
						<tr>
							<td width="150" align="right">Email* </td>
							<td><input name="emailId" id="emailId" title="Email" size="25"/>
							</td>
						</tr>
						
						
						<tr>
							<td>&nbsp;</td>
							<td  width="150" align="right">
								<input id="loginBtn" value="Cancel" type="button">	
								<input value="Submit" type="submit">						
							</td>
							
						</tr>
						<tr>
							
							<td  width="150" align="right">
							<!-- <input id="registerBtn" value="Register" type="button"> -->	
							New User? <a href="registration.htm">Register</a>						
							</td>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan="2">
							<form:errors cssStyle="color:red" />					
							</td>
						</tr>
					</tbody>
				</table>
	</form>
	
		</div>
	</div>
	
	<br/><br/>
	
</body>
</html>
