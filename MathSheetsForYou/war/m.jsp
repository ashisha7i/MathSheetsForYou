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
				<div style="padding: 5px; " class="filled">
					<h4>Please fill the details below:</h4>
					<form name="criteria" id="mainForm" onSubmit="javascript: validateAndSubmit();">
						<table>
						<tr>
							<td><label for="childName">Child Name</label></td>
							<!--<td><input type="text" name="childName"  size="31" id="idChildName"/></td>-->
							<td><input name="childName" id="idChildName" placeholder="Enter child name" value="" data-clear-btn="true" type="text" data-mini="true"></td>
						</tr>
						<tr>
							<td><label for="quesType">Sheet Type</label></td>
							<td>
							    <select name="quesType" id="idQuesType"  data-mini="true">
							        <option value="Add">Addition</option>
							        <option value="Sub">Subtraction</option>
							    </select>
							</td>
						</tr>
						<tr>
							<td><label for="totalQuestions">Number of Sums</label></td>
							<!-- <td><input type="text" name="totalQuestions" id="idTotalQues" value="50" required/></td> -->
							<td><input name="totalQuestions" id="idTotalQuestions" placeholder="Question Count" value="" data-clear-btn="true" type="number"  data-mini="true"></td>
						</tr>
						<tr id="forAdd">
							<td><label for="maxSum">Maximum Total</label></td>
							<!-- <td><input type="text" name="maxSum" id="idMaxSum" value="20" required/></td> -->
							<td><input name="maxSum" id="idMaxSum" placeholder="Max Sum" value="" data-clear-btn="true" type="number"  data-mini="true"></td>
						</tr>
						<tr id="forSub">
							<td><label for="maxNum">Greatest Number</label></td>
							<!-- <td><input type="text" name="maxNum" id="idMaxNum" value="20" required/></td> -->
							<td><input name="maxNum" id="idMaxNum" placeholder="Max. Number" value="" data-clear-btn="true" type="number"  data-mini="true"></td>
						</tr>
						<tr id="forSub">
							<td><label for="allowZero">Allow zero</label></td>
							<!-- <td><input type="checkbox" name="allowZero" id="idAllowZero" value="Y" checked/></td> -->
							<td><select name="allowZero" id="idAllowZero" data-role="slider" data-mini="true">
							    <option value="N">No</option>
							    <option value="Y" selected="">Yes</option>
							</select></td>
						</tr>
						<tr style="height:30px;">
							<td><label for="emailToCB">E-mail pdf?</label></td>
							<td>
								<!-- <input type="checkbox" name="emailToCB" id="idEmailToCB" value="Y"/> -->
								<select name="emailToCB" id="idEmailToCB" data-role="slider" data-mini="true">
								    <option value="N" selected="">No</option>
								    <option value="Y">Yes</option>
								</select>
							</td>
						</tr>
						<tr id="emailStuff">
							<td colspan="2">
									<!-- <input type="text" width="50" size="30" name="emailTo" id="idEmailTo" value="<%= session.getAttribute("USER_NAME") %>"/> -->
									<input name="emailTo" id="idEmailTo" value="<%= session.getAttribute("USER_NAME") %>" data-clear-btn="true" type="text" size="25"  data-mini="true">
									<center><label for="emailTo"></label></center>
							</td>
						<tr>
							<td>
								<input type="submit" value="Email" id="btnEmail" data-mini="true"/>
								<input type="submit" value="Generate" id="btnGenerate"  data-mini="true"/>
							</td>
						</tr>
						</table>
					</form>
					
					</div>
			</div>
			<div data-role="footer"></div>
		</div>
	</body>
</html>