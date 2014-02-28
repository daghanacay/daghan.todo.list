<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Home</title>
<style>
label,input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}
</style>
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
				<h2>Your Todo list</h2>
				<table id="todoListTable" class="table table-hover">
					<thead>
						<tr>
							<th colspan="1" style="display: none;">#</th>
							<th colspan="7">Detail</th>
							<th colspan="1">Entered</th>
							<th colspan="1">Due Date</th>
							<th colspan="1">Priority</th>
							<th colspan="2">Actions</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
					<tfoot>
						<tr>
							<td colspan="11"></td>
							<td>
								<button id="add_todo" type="button" style="width: 145px;"
									class="btn btn-success">Add New Todo</button>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	<div id="dialog-delete" title="Confirm Delete">
		<p>
			<span class="ui-icon ui-icon-alert"
				style="float: left; margin: 0 7px 20px 0;"></span>Todo will be
			permanently deleted and cannot be recovered. Are you sure?
		</p>
	</div>

	<div id="dialog-add" title="Add new todo">
		<p>All form fields are required.</p>
		<label for="detail">Detail</label> <input type="text" name="detail"
			id="detail" class="text ui-widget-content ui-corner-all">
		<div>
			<label>Priority</label> <select name="priority" id="priority"
				class="ui-widget-content ui-corner-all">
				<option value="">Select one...</option>
				<option value="1">High</option>
				<option value="2">Medium</option>
				<option value="3">Low</option>
			</select>
		</div>
		<label for="dueDate">Due Date</label>
		<div id="dueDate"></div>
	</div>

	<div id="dialog-update" title="Update todo">
		<p>All form fields are required.</p>
		<label for="detail">Detail</label> <input type="text"
			name="detail_update" id="detail_update"
			class="text ui-widget-content ui-corner-all"> <label
			for="dueDate_update">Due Date</label>
		<div id="dueDate_update"></div>
	</div>



	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="/todo/public-resources/jQuery1.9.1/jquery-1.9.1.js"></script>
	<script
		src="/todo/public-resources/bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>
	<script
		src="/todo/public-resources/jQueryUI1.20.3/jquery-ui-1.10.3.custom.js"></script>
	<script src="/todo/public-resources/todo-v1.0/global.js"></script>
	<script src="/todo/public-resources/todo-v1.0/homePage.js"></script>
</body>
</html>
