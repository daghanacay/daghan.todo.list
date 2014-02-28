// Ajax for registration
// html for the form should be as follows
/*
 <div class="container">
 <div class="row">
 <div class="col-md-4">
 <h2>Register</h2>
 <div>
 <div id="firstNameRegistration" class="form-group">
 <input placeholder="First Name" class="form-control">
 </div>
 <div id="lastNameRegistration" class="form-group">
 <input placeholder="Last Name" class="form-control">
 </div>
 <div id="emailRegistration" class="form-group">
 <input placeholder="E-mail" class="form-control">
 </div>
 <div id="passwordRegistration" class="form-group">
 <input type="password" placeholder="Password" class="form-control">
 </div>
 <div>
 <button id="submitRegistration" type="submit"
 class="btn btn-success">Register</button>
 </div>
 </div>

 </div>
 */
$(document).ready(function() {
	//requires jqueryui.js
	addErrorPopUp();
	// respond to login
	$('#submitRegistration').click(function(event) {
		var firstName = $('#firstNameRegistration').val();
		var lastName = $('#lastNameRegistration').val();
		var email = $('#emailRegistration').val();
		var password = $('#passwordRegistration').val();
		var json = {
			"firstName" : firstName,
			"lastName" : lastName,
			"email" : email,
			"password" : password
		};

		insertAjaxData("/todo/rest/register", json, function(response) {
			window.location = "/todo/mvc/secure/home";
		}, function(errorString) {
			showError(errorString);
		});
		event.preventDefault();
	});
});