<html>
<head>
<title>Welcome to Math Sheets For You</title>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="js/mathbuddy.js"></script>
<script>
	$(document).ready(function(){
		$("#btnGenerate").bind("click", function(){
			getSheetFromServer();
		});
		
		$("#idAdd").click(function(){
			if(this.checked){
				$("#forSub").hide();
				$("#forAdd").show();
			}
		});
		
		$("#idSub").click(function(){
			if(this.checked){
				$("#forAdd").hide();
				$("#forSub").show();
			}
		});
		
		$("#idAdd").click();
	});
	
	
	function getSheetFromServer(){
		var childName = $("#idChildName").val();
		var quesType  = $("input:checked").val();
		var totalQuestions = $("#idTotalQues").val();
		var maxSum = $("#idMaxSum");
		var maxNum = $("#idMaxNum");

		var theUrl = "<%= request.getContextPath() %>/main?action=genSheet&" + $("form").serialize();
		/*
		$.ajax({
		    url: theUrl,	    
		    type: 'GET', 
		    success: function(result) {
		    	alert("Returned okay");
		    },
		    error: function(xhr) {
		    	alert("Error calling the function");
		    }	    		    
		});	
		*/
		
		window.open(theUrl);
		
	}
	
</script>
<link rel="stylesheet" type="text/css" href="./css/main.css"/>
</head>
<body>
	<div id="wrap" class="filled">
	<p><img src="./img/logo.png"><img src="./img/icon-beta.png"><br></p>
	<p style="background-color: #FFFFCC; padding: 5px; border: 1px solid #ffcc00;">
	   This is a free service for all where you can generate math practice sheets for your child. (Currently only addition and subtraction sheets 
	   are supported.)<br> We will be adding more features real soon in the meantime have fun using the website. For any issues or suggestions <a href="https://twitter.com/intent/tweet?screen_name=vinicreates" class="twitter-mention-button" data-related="vinicreates">Tweet to @vinicreates</a>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script><br><br> 
	   Use the following form to specify the criteria for generating your sheet(s). Hover your mouse over <img src="./img/icon_info2.png"> icon for a brief description of each option.<br><br>Thanks,<br>- A	
	</p>
	<div style="padding: 5px; " class="filled">
	<h3>Please specify the criteria for generating the sheet : </h3>
	<form name="criteria" method="post" action="./main">
		<table>
		<tr>
			<td style="width: 450px;"><label for="childName">Generated for (child name)</label> <img src="./img/icon_info2.png" title="Child name to be printed on the generated sheet. Leave blank if you do not want any name to be printed on the sheet (e.g. Generating more than one sheets)"></td>
			<td><input type="text" name="childName"  id="idChildName"/></td>
		</tr>
		<tr>
			<td><label for="questionsType">Select sheet type </label></td>
			<td>
				<input type="radio" name="quesType" value="add" id="idAdd" checked/>Addition
				<input type="radio" name="quesType" value="sub" id="idSub"/>Subtraction
			</td>
		</tr>
		<tr>
			<td><label for="totalQuestions">Enter the number of questions to generate</label></td>
			<td><input type="text" name="totalQuestions" id="idTotalQues" value="50"/></td>
		</tr>
		<tr id="forAdd">
			<td><label for="maxSum">Maximum Total <img src="./img/icon_info2.png" title="Generated sheet will contain sums where the sum of the two numbers will be lower than or equal to the value you speficy here (A + B <= Value Specified)"></label></td>
			<td><input type="text" name="maxSum" id="idMaxSum" value="20"/></td>
		</tr>
		<tr id="forSub">
			<td><label for="mixNum">Greatest Number <img src="./img/icon_info2.png" title="Generated sheet will contain subtraction problems where the larger number (minuend) will be less than or equal to the number specified here"></label></td>
			<td><input type="text" name="maxNum" id="idMaxNum" value="20"/></td>
		</tr>
		<tr id="forSub">
			<td><label for="allowZero">Generate sums with zero as the second number</label> <img src="./img/icon_info2.png" title="Generated problems will have ZERO as one of the numbers (A + 0 = ? or A - 0 = ?)"></td>
			<td><input type="checkbox" name="allowZero" id="idAllowZero" value="Y" checked/></td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="button" value="Generate" id="btnGenerate"/></td>
		</tr>
		</table>
	</form>
	
	</div>
	
	</div>

</body>
</html>

