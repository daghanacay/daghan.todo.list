<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Todo Login</title>

<!-- System CSS -->
<link href="/todo/public-resources/todo-v1.0/style.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">SimpleTodo</a>
			</div>
			<form class="navbar-collapse collapse"
				action="/todo/j_spring_security_check" method="post">
				<div class="navbar-form navbar-right">
					<div class="form-group">
						<input type="text" placeholder="Email" class="form-control"
							name='j_username'>
					</div>
					<div class="form-group">
						<input type="password" placeholder="Password" class="form-control"
							name='j_password'>
					</div>
					<button type="submit" class="btn btn-success">Sign in</button>
				</div>
			</form>
			<!--/.navbar-collapse -->
		</div>
	</div>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Simple Todo</h1>
			<p>A simple todo list to organize your life anywhere you go.</p>
		</div>
		<c:if test="${not empty error}">
					${error} Please register below or try again.
		</c:if>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h2>First time to the Simple Todo?</h2>
				If you have not registered yet. Please register using the form.
			</div>
			<div class="col-md-6">
				<h2>Register</h2>
				<div>
					<div class="form-group">
						<input id="firstNameRegistration" placeholder="First Name"
							class="form-control">
					</div>
					<div class="form-group">
						<input id="lastNameRegistration" placeholder="Last Name"
							class="form-control">
					</div>
					<div class="form-group">
						<input id="emailRegistration" placeholder="E-mail"
							class="form-control">
					</div>
					<div class="form-group">
						<input id="passwordRegistration" type="password"
							placeholder="Password" class="form-control">
					</div>
					<div>
						<button id="submitRegistration" type="submit"
							class="btn btn-success">Register</button>
					</div>
				</div>

			</div>

		</div>

		<hr>

		<footer>
			<p>&copy; Todo List 2014</p>
		</footer>
	</div>
	<!-- /container -->
	
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="/todo/public-resources/jQuery1.9.1/jquery-1.9.1.js"></script>
	<script
		src="/todo/public-resources/bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>
	<script
		src="/todo/public-resources/jQueryUI1.20.3/jquery-ui-1.10.3.custom.js"></script>
	<script src="/todo/public-resources/todo-v1.0/global.js"></script>
	<script src="/todo/public-resources/todo-v1.0/loginPage.js"></script>
</body>
</html>
