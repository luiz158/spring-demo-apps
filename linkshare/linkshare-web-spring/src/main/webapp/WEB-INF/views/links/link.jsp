<!DOCTYPE html>
<%@include file="../taglib.jsp"%>
<html>
<head>
<title>Link</title>
	<style>
		label, input { display:block; }
		legend {font-weight: bold;}
	</style>
	
	<script>
	$(function() {
		$( "input:submit" ).button();
		$( "input:button" ).button();

		$( "#upVoteBtn").mouseover(function(){
			$(this).addClass('cursor');
		});

		$( "#downVoteBtn").mouseover(function(){
			$(this).addClass('cursor');
		});
		
		$("#newCommentBtn").click(function(e){
			
			var data = {};
			data['message']=$('#newComment').val();
			data['linkId']=$('#linkId').val();
			
			 $.ajax( {
			      type: "POST",
			      url: 'addComment.htm',
			      data: data,
			      success: function( response ) {
			        //alert( response );
			        window.location = "showLink.htm?linkId="+$('#linkId').val();
			      }
			 } );
			 
			
		});
		var voteLink = function(linkId, voteType){
			//alert(linkId+":"+ voteType);
			var data = {};
			data['voteType']=voteType;
			data['linkId']=linkId;
			
			 $.ajax( {
			      type: "POST",
			      url: 'voteLink.htm',
			      data: data,
			      dataType: "json",
			      success: function( response ) {
			      //alert( response );
			        if(response.status == 'success')
			        {
			        	//$('#upVotesCount').html(response.up);
				       // $('#downVotesCount').html(response.down);
			        	window.location = "showLink.htm?linkId="+$('#linkId').val();
				    }
			        else{
						alert(response.message);
				    }
			      }
			 } );
		};
		
		$("#upVoteBtn").click(function(e){
			voteLink($('#linkId').val(), 'U');
			
		});

		$("#downVoteBtn").click(function(e){
			voteLink($('#linkId').val(), 'D');
		});

		
	});
	</script>
</head>
<body>
	<form>
		<input type="hidden" name="linkId" id="linkId" value="${link.linkId}">
	</form>
	<fieldset  style="width: 800px;">
		<legend align="left">Link Details</legend>
	
	<table >
		
		<tr>
			<td  align="left">
				<font size="5"><a href="${link.url}" target="_blank">${link.title}</a></font>
			</td>
		</tr>
		<tr>
			<td  align="left">${link.description}</td>
		</tr>
	</table>
	<br/>
	<table>
	<tr>
		<td  align="left">
		<span id="upVotesCount">${link.upVotesCount}</span>
		 <img id="upVoteBtn" alt="Vote Up" src="resources/images/vote_up.png">
		&nbsp;&nbsp;&nbsp;
		<span id="downVotesCount">${link.downVotesCount}</span>
		 <img id="downVoteBtn" alt="Vote Down" src="resources/images/vote_down.png">
		</td>
		</tr>
	</table>
	</fieldset>
	
	<fieldset style="width: 800px;">
		<legend align="left">Up Votes for this Link</legend>
		<table>
		<c:forEach var="voter" items="${link.upVoters }">
			<tr>
			<td  align="left">
			${voter.userName}
			</td>
			</tr>
		</c:forEach>
		</table>
	</fieldset>	
	
	<fieldset style="width: 800px;">
	<legend align="left">Down Votes for this Link</legend>
	<table>
	<c:forEach var="voter" items="${link.downVoters }">
		<tr>
		<td  align="left">
		${voter.userName}
		</td>
		</tr>
	</c:forEach>
	</table>
	</fieldset>
	
	<fieldset style="width: 800px;">
	<legend align="left">Add Comment</legend>
	<table>
		<tr>
			<td  align="left"><textarea rows="5" cols="60" id="newComment" name="newComment"></textarea> </td>
		</tr>
		<tr>
			<td  align="left"><input id="newCommentBtn" type="button" value="Submit"> </td>
		</tr>
	</table>
	</fieldset>
	
	<fieldset style="width: 800px;">
	<legend align="left">Comments</legend>
	<table>
		<c:forEach var="comment" items="${link.comments}">
			
			<tr>
				<td align="left">
					<fieldset style="width:400;">
					<div>${comment.message}</div>
					<div>Commented By: ${comment.postedBy.userName} on ${comment.createdOn}</div>
					</fieldset>
				</td>
			</tr>			
		</c:forEach>
		
	</table>
	</fieldset>
	
</body>
</html>