<%@include file="../taglib.jsp" %>

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
	
	<style>
	.ui-autocomplete-loading1 { background: white url('images/ui-anim_basic_16x16.gif') right center no-repeat; }
	</style>
	
	<script>

	function validateURL(textval) {
		  var urlregex = new RegExp(
		        "^(http:\/\/www.|https:\/\/www.|ftp:\/\/www.|www.){1}([0-9A-Za-z]+\.)");
		  return urlregex.test(textval);
		}
	
	$(function() {

		function split( val ) {
			return val.split( /,\s*/ );
		}
		function extractLast( term ) {
			return split( term ).pop();
		}
		
		$( "#tagListAC" )
			// don't navigate away from the field on tab when selecting an item
			.bind( "keydown", function( event ) {
				if ( event.keyCode === $.ui.keyCode.TAB &&
						$( this ).data( "autocomplete" ).menu.active ) {
					event.preventDefault();
				}
			})
			/*
			.bind("blur",function() {
				//alert(11);
				var val = $(this).data("uiItem");
				if(typeof val === 'undefined') val = '';
				$(this).val(val);    
			})*/
			.autocomplete({
				source: function( request, response ) {
					$.getJSON( "searchTagsJson", {
						query: extractLast( request.term )
					}, response );
				},
				search: function() {
					// custom minLength
					var term = extractLast( this.value );
					if ( term.length < 1 ) {
						return false;
					}
				},
				focus: function() {
					// prevent value inserted on focus
					return false;
				},
				select: function( event, ui ) {
					var terms = split( this.value );
					// remove the current input
					terms.pop();
					// add the selected item
					terms.push( ui.item.value );
					var oldTagIdsList = $('#tagIdsList').val();
					$('#tagIdsList').val(oldTagIdsList+','+ui.item.tagId);
					//alert($('#tagIdsList').val());
					// add placeholder to get the comma-and-space at the end
					terms.push( "" );
					this.value = terms.join( ", " );
					return false;
				}
				/*
				,open: function () {
                    $("#tagListAC").bind("blur", function () {
                        if (typeof $(this).data("uiItem") === 'undefined') {
                            $(this).val("");
                        }
                    });
                }
                ,close: function () {
                    $("#tagListAC").unbind("blur");
                }
                */
			});

		$("#newLinkForm").submit(function(e)
		{
			 var title = $.trim($("#title").val());
			 var url = $.trim($("#url").val());
			 
			 if(title=="" || url=="")
			 {
				e.preventDefault();
				alert('Please enter Title and URL');
				return;
			 }
			
			 //e.preventDefault();
		});
		
		
	});
	</script>
	
</head>
<body>
<br/><br/>
	<div style="width:400px; margin-right:auto; margin-left:auto;">
		<font color="red">${message}</font>
	</div>
<div style="width:600px; margin-right:auto; margin-left:auto; border:1px solid #000; border-radius: 5px;">
	
	<div class="ui-widget">
		<form:form id="newLinkForm" action="createLink.htm" method="post" commandName="link">
		
		<table>
			<tbody>							
				
				<tr>
					<td width="100" align="right"><label for="title">Title*</label></td>
					<td><form:input path="title" id="title" size="60"/>
					<form:errors cssStyle="color:red" path="title"/></td>
				</tr>
				
				<tr>
					<td width="100" align="right"><label for="url">URL*</label> </td>
					<td><form:input path="url" id="url" size="60"/>
					<form:errors cssStyle="color:red" path="url"/></td>
				</tr>
				<tr>
					<td width="100" align="right"><label for="description">Description</label> </td>
					<td><form:textarea path="description" id="description" rows="5" cols="45"/>
					<form:errors cssStyle="color:red" path="description"/></td>
				</tr>
				<tr>
					<td width="100" align="right"><label for="tags">Tags</label> </td>
					<td>
						<input id="tagListAC" name="tagListAC" size="50" />
						<input type="hidden" id="tagIdsList" name="tagIdsList" size="50" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td  width="100" align="right">
						<input name="" value="Submit" type="submit">						
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<form:errors cssStyle="color:red"/>
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