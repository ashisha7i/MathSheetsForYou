<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1"/>
		<meta name="apple-mobile-web-capable" content="yes"/>
		<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
		<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
 		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
		<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
		<script src="js/jquery.form.js"></script>
		<script src="js/jquery.validate.min.js"></script>
		<script src="js/mathbuddy-m.js"></script>
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css"/>
		<link rel="stylesheet" href="css/m.css"/>
		<title>MathSheetsForYou Mobile</title>
		<script>var ctxPath = "<%= request.getContextPath() %>";</script>
	</head>
	<body>
		<div data-role="page" id="home" data-theme="c">
			<div data-role="header" data-theme="c">
				<div align="center" class="userGreet"><%= session.getAttribute("USER_GREETING") %></div>
				<div style="align: center; width:100%;""><img src="./img/logo.png" width="80%"><img src="./img/icon-beta.png" width="10%"></div>
			</div>
			<div data-role="content">
				<div style="padding: 2px; " class="filled">
					<h4>Please fill the details below:</h4>
					<form name="criteria" id="mainForm" onSubmit="javascript: validateAndSubmit();">
						<div data-role="fieldcontain">
							<label for="idChildName">Child Name:</label>
							<input name="childName" id="idChildName" placeholder="Enter child name" value="" data-clear-btn="true" type="text" data-mini="true">
						</div>
						<div data-role="fieldcontain">
							<label for="idQuesType">Sheet Type</label></td>
						    <select name="quesType" id="idQuesType"  data-mini="true">
						        <option value="Add">Addition</option>
						        <option value="Sub">Subtraction</option>
						    </select>
						</div>
						<div data-role="fieldcontain">
							<label for="idTotalQuestions">Number of Sums</label>
							<input name="totalQuestions" id="idTotalQuestions" placeholder="Question Count" value="" data-clear-btn="true" type="number"  data-mini="true">
						</div>
						<div data-role="fieldcontain" id="forAdd">
							<label for="idMaxSum">Maximum Total</label>
							<input name="maxSum" id="idMaxSum" placeholder="Max Sum" value="" data-clear-btn="true" type="number"  data-mini="true">
						</div>
						<div data-role="fieldcontain" id="forSub">
							<label for="idMaxNum">Maximum Number</label>
							<input name="maxNum" id="idMaxNum" placeholder="Greater Number" value="" data-clear-btn="true" type="number"  data-mini="true">
						</div>
						<div data-role="fieldcontain">
							<label for="idAllowZero">Allow zero</label>
							<select name="allowZero" id="idAllowZero" data-role="slider" data-mini="true">
							    <option value="N">No</option>
							    <option value="Y" selected="">Yes</option>
							</select>
						</div>
						<div data-role="fieldcontain">
							<label for="idEmailToCB">E-mail pdf?</label>
							<select name="emailToCB" id="idEmailToCB" data-role="slider" data-mini="true">
							    <option value="N" selected="">No</option>
							    <option value="Y">Yes</option>
							</select>
						</div>
						<div data-role="fieldcontain" id="emailStuff">
								<label for="idEmailTo">Enter e-mail:</label>													
								<input name="emailTo" id="idEmailTo" value="<%= session.getAttribute("USER_NAME") %>" data-clear-btn="true" type="text" size="25"  data-mini="true">
						</div>
						<div data-role="fieldcontain">
							<input type="button" value="Email" id="btnEmail" data-mini="true"/>
							<input type="submit" value="Generate" id="btnGenerate"  data-mini="true"/>
						</div>
						
					</form>
					
					</div>
			</div>
			<div data-role="footer"></div>
		</div>
		
		<div data-role="popup" id="loadingPopup">
			<p>Generating...</p>
		</div>
		

		<div data-role="popup" id="loadingError">
			<p>ERROR</p>
		</div>
	</body>
</html>