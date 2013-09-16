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
		
		$("#idAdd").click();
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
						
				}
			},
			submitHandler: function(event){
				var theUrl = ctxPath + "/main?action=genSheet&" + $("form").serialize();
				window.open(theUrl,"_blank");
				
				return false;
			}
			
		});
	} // validateAndSubmit