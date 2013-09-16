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
					minlength: 2
				},
				maxSum: {
					required: true,
					digits: true,
					minlength: 2
				},
				maxNum: {
					required: true,
					digits: true,
					minlength: 2
				},
			},
			messages: {
				totalQuestions: {
					minlength: "Please enter alteast two digits"
				},
				maxSum: {
					minlength: "Please enter alteast two digits"
				},
				maxNum: {
					minlength: "Please enter alteast two digits"
				}
			},
			submitHandler: function(event){
				var theUrl = ctxPath + "/main?action=genSheet&" + $("form").serialize();
				window.open(theUrl,"_blank");
				
				return false;
			}
			
		});
	} // validateAndSubmit