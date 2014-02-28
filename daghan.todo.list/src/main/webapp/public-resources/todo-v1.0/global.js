function fetchAjaxData(url, successFunction, errorFunction) {
	$.ajax({
		type : "GET",
		url : url,
		dataType : "json",
		success : function(data) {
			successFunction(data);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			errorFunction(thrownError + xhr.responseText);
		}
	});
};

function insertAjaxData(url, data, successFunction, errorFunction) {
	$.ajax({
		type : "POST",
		url : url + ".json",
		data : JSON.stringify(data),
		dataType : "json",
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(data) {
			successFunction(data);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			errorFunction(thrownError + xhr.responseText);
		}
	});
}

function updateAjaxData(url, data, successFunction, errorFunction) {
	$.ajax({
		type : "PUT",
		url : url + ".json",
		data : JSON.stringify(data),
		dataType : "json",
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(data) {
			successFunction(data);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			errorFunction(thrownError + xhr.responseText);
		}
	});
}

function deleteAjaxData(url, elementId, successFunction, errorFunction) {
	$.ajax({
		url : url + elementId + ".json",
		type : "DELETE",
		success : function(data) {
			successFunction(data);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			errorFunction(thrownError + xhr.responseText);
		}
	});
}
// extension to jQuery to test if the text is exact in addition to :contains
$.expr[":"].containsExactText = function(obj, index, meta, stack) {
	return (obj.textContent || obj.innerText || $(obj).text() || "") == meta[3];
};

//Adds the error popup to the page. requires jqueryui.js to be loaded in the page
function addErrorPopUp() {
	// Error dialog
	$("body").append(
			"<div id='dialog-error' title='Error'>"
					+ "<span class='ui-icon ui-icon-alert'"
					+ "style='float: left; margin: 0 7px 20px 0;'></span>"
					+ "<div id='error_message'></div>" + "</div>");

	$("#dialog-error").dialog({
		autoOpen : false,
		resizable : false,
		width : 600,
		height : 220,
		modal : true,
		buttons : {
			OK : function() {
				$(this).dialog("close");
			}
		}
	});
};

function showError(errorStr) {
	$("#error_message").text(errorStr);
	$("#dialog-error").dialog("open");
};