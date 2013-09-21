$(document).ready(function(){
		$("#btnGenerate").bind("click", function(){
			validateAndSubmit();
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
		
		
		$("#emailStuff").css("display","none");
		$("#idEmailToCB").click(function(){
			if(this.checked){
				$("#emailStuff").show();
				$("#emailStuff").css("display","inline");
				$("#btnGenerate").hide();
				$("#btnEmail").show();
			} else {
				$("#emailStuff").hide();
				$("#btnGenerate").show();
				$("#btnEmail").hide();
			}
			
		});
		
		
		$("#btnEmail").bind("click", function(){
			validateAndEmail();
		});
		
		// Check the "Add" checkbox by default
		$("#idAdd").click();
		$("#idEmailToCB").prop("checked",false);
	});
	
	
	function validateAndSubmit(){
		$("#mainForm").validate({
			rules: {
				childName: {
					required: false,
					maxlength: 35
				},
				totalQuestions: {
					required: true,
					digits: true,
					minlength: 2,
					max: 500
				},
				maxSum: {
					required: true,
					digits: true,
					minlength: 2,
					max: 999
				},
				maxNum: {
					required: true,
					digits: true,
					minlength: 2,
					max: 999
				},
				emailTo: {
					required: true,
					email: true
				}
			},
			messages: {
				totalQuestions: {
					minlength: "Please enter alteast two digits.",
					max: "Max questions allowed is 500."
				},
				maxSum: {
					minlength: "Please enter alteast two digits.",
					max: "Please enter a number between 10 and 999."
				},
				maxNum: {
					minlength: "Please enter alteast two digits.",
					max: "Please enter a number between 10 and 999."
						
				},
				emailTo: {
					required: "e-mail address required.",
					email: "Invalid e-mail."
				}
			},
			submitHandler: function(event){
				var theUrl = ctxPath + "/main?action=genSheet&" + $("form").serialize();
				window.open(theUrl,"_blank");
				
				return false;
			}
			
		});
	} // validateAndSubmit
	
	
	function validateAndEmail(){
		$("#mainForm").validate({
			rules: {
				childName: {
					required: false,
					maxlength: 35
				},
				totalQuestions: {
					required: true,
					digits: true,
					minlength: 2,
					max: 500
				},
				maxSum: {
					required: true,
					digits: true,
					minlength: 2,
					max: 999
				},
				maxNum: {
					required: true,
					digits: true,
					minlength: 2,
					max: 999
				},
				emailTo: {
					required: true,
					email: true
				}
			},
			messages: {
				totalQuestions: {
					minlength: "Please enter alteast two digits.",
					max: "Max questions allowed is 500."
				},
				maxSum: {
					minlength: "Please enter alteast two digits.",
					max: "Please enter a number between 10 and 999."
				},
				maxNum: {
					minlength: "Please enter alteast two digits.",
					max: "Please enter a number between 10 and 999."
						
				},
				emailTo: {
					required: "e-mail address required.",
					email: "Invalid e-mail."
				}
			},
			submitHandler: function(event){
				var theUrl = ctxPath + "/main?action=genSheet&" + $("form").serialize();
				$.ajax({
					url: theUrl,
					async: false,
					beforeSend: function(){
						$( "#dialogProcessing" ).dialog({
							 height: 140,
							 width: 400,
							 modal: true
						});
					},
					success: function(){
						$("#dialogProcessing").dialog("close");
						$("#dialogDone").dialog({
							 height: 140,
							 width: 400,
							 modal: true
						});
					},
					error: function(){
						$("#dialogError").dialog({
							 height: 140,
							 width: 400,
							 modal: true
						});
					},
					complete: function(){
						$("#dialogProcessing").dialog("close");
					}
				});
				
				//window.open(theUrl,"_blank");
				
				return false;
			}
			
		});
	} // validateAndEmail