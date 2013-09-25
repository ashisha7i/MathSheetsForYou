$(document).ready(function(){
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
		errorPlacement: function(error, element) {
		     var eleName = element.context.attributes.id.value;
		     var addToElement = $("label[for='"+eleName+"']"); 
		     if(typeof addToElement != 'undefined'){
		    	 error.appendTo(addToElement);
		     } else {
		    	 error.appendTo(element);
		     }
		},
		messages: {
			totalQuestions: {
				required: "Required",
				minlength: "Min 2 digits",
				max: "Max 500",
				digits: "Digits only",
				number: "Digits only"
			},
			maxSum: {
				required: "Required",
				minlength: "Min 2 digits",
				max: "Range 10 - 999",
				digits: "Digits only",
				number: "Digits only"
			},
			maxNum: {
				required: "Required",
				minlength: "Min 2 digits",
				max: "Range 10 - 999",
				digits: "Digits only",
				number: "Digits only"
			},
			emailTo: {
				required: "Required",
				email: "Invalid mail"
			}
		},
		submitHandler: function(event){
			var theUrl = ctxPath + "/main?action=genSheet&" + $("form").serialize();
			window.open(theUrl,"_blank");
			
			return false;
		}
		
	});
	
	
	
		$("#btnGenerate").bind("click", function(){
			//validateAndSubmit();
			$("#mainForm").valid();
		});
		
		$("#btnEmail").bind("click", function(){
			if($("#mainForm").valid()){
				emailTheSheet();
			}
		});
		
		$("#idQuesType").bind("change", function(){
			if(this.value == "Add"){
				$("#forSub").hide();
				$("#forAdd").show();
			} else {
				$("#forSub").show();
				$("#forAdd").hide();
			}
		});
		
		$("#idSub").click(function(){
			if(this.checked){
				$("#forAdd").hide();
				$("#forSub").show();
			}
		});
		
		
		$("#emailStuff").css("display","none");
		$( "#idEmailToCB").on('slidestop', function( event ) {
			  var slider_value=$("#idEmailToCB").slider().val();
			  if(slider_value == 'Y'){
				  $('#btnEmail').closest('.ui-btn').show();
				  $('#btnGenerate').closest('.ui-btn').hide();
				  $("#emailStuff").show();
			  } else {
				  $('#btnEmail').closest('.ui-btn').hide();
				  $('#btnGenerate').closest('.ui-btn').show();
				  $("#emailStuff").hide();
			  }
		});
		
		
		
		
		// Check the "Add" checkbox by default
		$("#idQuestionType").val("Add");
		$("#forSub").hide();
		$("#idEmailToCB").val("N");
		$("#idEmailToCB").slider("refresh");
		$('#btnEmail').closest('.ui-btn').hide();
		$('#btnGenerate').closest('.ui-btn').show();
		$("#idAllowZero").val("Y");
		$("#idAllowZero").slider("refresh");
		
		// Initializing popups
		$("#loadingPopup").popup();
		$("#loadedPopup").popup();
		$("#loadingError").popup();
		
		// Binding popup close buttons
		$("#btnPopCloseLoaded").bind("click", function(){
			$("#loadedPopup").popup("close");
		});
		
		$("#btnPopCloseErr").bind("click", function(){
			$("#loadingError").popup("close");
		});
		
	});
	
	
	
function emailTheSheet(){
	var theUrl = ctxPath + "/main?action=genSheet&" + $("#mainForm").serialize();
	$.ajax({
		url: theUrl,
		async: false,
		beforeSend: function(){
			//$.mobile.changePage("dlgDone.jsp", { role: "dialog" });
			//$("#dlgText").val("Processing...");
			//$("#dlgClose").hide();
			$("#loadingPopup").popup("open");
		},
		success: function(){
			//$("#dlgText").val("Sheet Email successfully !!");
			//$("#dlgClose").show();
			$("#loadingPopup").popup("close");
			$("#loadedPopup").popup("open");
		},
		error: function(){
			$("#loadingPopup").popup("close");
			$("#loadedPopup").popup("close");
			$("#loadingError").popup("open");
		},
		complete: function(){
			//$("#loadingPopup").popup("close");
		}
	});
}
	
	