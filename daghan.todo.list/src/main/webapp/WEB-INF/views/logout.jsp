<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Log out</title>

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

			<div class="navbar-collapse collapse">
				<div class="navbar-form navbar-right">
					<a href="/todo/j_spring_security_logout" class="btn btn-success">
						<img style="width: 30px;" alt="Logout"
						src="/todo/public-resources/images/logout.png" title="Log out" />
					</a>
				</div>
			</div>
		</div>
	</div>
	<!-- /container -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>Good Bye.</h1>
				Accidentally logged out? <a href="/todo/mvc/login">Sign in again.</a>
			</div>
		</div>
	</div>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="/todo/public-resources/bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>
</body>
</html>
