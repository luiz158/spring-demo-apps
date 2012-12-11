<%@include file="../taglib.jsp"%>
<html>
<head>
	<style>
		label, input { display:block; }
	</style>
	<script>
	$(function() {
		$( "input:submit" ).button();
		$( "input:button" ).button();
		
		$("#loginForm").submit(function(e){
			  var username = $.trim($("#j_username").val());
			  var pwd = $.trim($("#j_password").val());
			
			 if(username=="" || pwd=="")
			 {
				e.preventDefault();
				alert('Please enter UserName and Password');
			 }
			 
		});

		$("#registerBtn").click(function(e){
			//alert('Register');
			window.location = "registration.htm";
		});

		$("#forgotPwdBtn").click(function(e){
			//alert('forgotPwd');
			window.location = "forgotPwd.htm";
		});
		
	});
	</script>
</head>
<body>
	<br/><br/>
	<div style="width:400px; margin-right:auto; margin-left:auto;">
		<font color="red">${message}</font>
	</div>
	<c:if test='${not empty param.error}'>  
  	<font color='red'>  
	    Login error. 
	 
	    Reason : ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}  
	  </font>  
	</c:if>
	<div class="ui-widget ui-widget-content ui-corner-all" 
		style="outline: 0px none; width: 400px; margin-right:auto; margin-left:auto;">
		<div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"
		style="width: auto; min-height: 30px; height: auto;">
			<span id="ui-dialog-title-dialog" class="ui-dialog-title">Login Form</span>
		</div>
		<div class="ui-widget-content" style="width: auto; min-height: 113.4px; height: auto; border-top: none;">
			<form action="<c:url value='/j_spring_security_check' />" method="post" id="loginForm">
				<table>
					<tbody>							
						<tr>
							<td width="150" align="right">UserName* </td>
							<td><input type="text" name="j_username" id="j_username" size="25"/>
							</td>
						</tr>
						
						<tr>
							<td width="150" align="right">Password* </td>
							<td><input type="password" name="j_password" id="j_password" size="25"/>
							</td>
						</tr>				
						<tr>
							<td>&nbsp;</td>
							<td  width="150" align="right">
								<input value="Login" type="submit">						
							</td>
						</tr>
						<tr>
							
							<td  width="150" align="right">
							<!-- <input id="registerBtn" value="Register" type="button"> -->	
							New User? <a href="registration.htm">Register</a>						
							</td>
							<td>&nbsp;<a href="forgotPwd.htm">Forgot Password</a>	</td>
						</tr>
						<!-- 
						<tr>
							<td  width="150" align="right">
								<a href="forgotPwd.htm">Forgot Password</a>						
							</td>
							<td>&nbsp;</td>
						</tr>
						 -->
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
