$(document).ready(function() {
	// requires jqueryui.js
	addErrorPopUp();
	populateTodos("/todo/rest/secure/todos.json");
	configureDialogs();
	configureButtons();
});

// Utility functions
function populateTodos(urlToFetch) {
	fetchAjaxData(urlToFetch, function(data) {
		if (data.length <= 0) {
			addNoItem();
		} else {
			$.each(data, function(index, obj) {
				addTodo(obj);
			});
		}
	}, function(errorString) {
		showError(errorString);
	});
};

function addNoItem() {
	$('#todoListTable')
			.find('tbody:last')
			.append(
					'<td id="no_items" colspan = "12"><h3>Congratulations... You have no tasks Todo. Would you like to add more?</h3></td>');
};

function addTodo(todoObj) {
	var todoId = todoObj.todoItemId;
	htmlStr = "<tr>	<td style='display:none;' class='id_cell' colspan='1'>"
			+ todoId
			+ "</td>	<td id='detail_cell' colspan='7'>"
			+ todoObj.detail
			+ "<td colspan='1'>"
			+ $.datepicker
					.formatDate('dd/mm/yy', new Date(todoObj.dateEntered))
			+ "</td>	<td id='duedate_cell' colspan='1'>"
			+ $.datepicker.formatDate('dd/mm/yy', new Date(todoObj.dueDate))
			+ "</td>"
			+ "<td colspan='1'>"
			+ "<select name='priority_cell'	id='priority_cell' class='ui-widget-content ui-corner-all'>"
			+ "<option value='1'>High</option><option value='2'>Medium</option><option value='3'>Low</option>"
			+ "</select>"
			+ "</td> "
			+ "<td colspan='2'>"
			+ "<button type='button' class='update_button btn btn-warning'>Update</button>"
			+ " <button type='button' class='delete_button btn btn-danger'>Delete</button>"
			+ "</td>" + "</tr>";
	$('#todoListTable').find('tbody:last').append(htmlStr);
	// create the selection value and the table rendering
	var addedRow = $(".id_cell:containsExactText('" + todoId + "')").closest(
			'tr');
	addedRow.find("#priority_cell").val(todoObj.priority);
	setRowColor(todoObj.priority, addedRow);
	// attach handlers to select and the buttons
	attachHandlers(todoId);
};

function updateTodo(todoObj) {
	var todoId = todoObj.todoItemId;
	var selectedRow = $(".id_cell:containsExactText('" + todoId + "')")
			.closest('tr');
	selectedRow.find('#detail_cell').text(todoObj.detail);
	selectedRow.find('#duedate_cell').text(
			$.datepicker.formatDate('dd/mm/yy', new Date(todoObj.dueDate)));
	selectedRow.find("#priority_cell").val(todoObj.priority);
	setRowColor(todoObj.priority, selectedRow);
};

function setRowColor(todoPriority, selectedRow) {
	selectedRow.removeClass('danger warning info');
	switch (todoPriority) {
	case 1:
		selectedRow.addClass('danger');
		break;
	case 2:
		selectedRow.addClass('warning');
		break;
	case 3:
		selectedRow.addClass('info');
		break;
	}
};

function attachHandlers(todoId) {
	// Priority dropdown update
	var addedRow = $(".id_cell:containsExactText('" + todoId + "')").closest(
			'tr');
	addedRow.find('#priority_cell').change(
			function(event) {
				var detail = addedRow.find('#detail_cell').text();
				var priority = addedRow.find('#priority_cell').val();
				var dueDate = $.datepicker.parseDate('dd/mm/yy', addedRow.find(
						'#duedate_cell').text());
				json = {
					"detail" : detail,
					"priority" : priority,
					"dueDate" : dueDate
				};
				updateAjaxData("/todo/rest/secure/todos/" + todoId, json,
						function(result) {
							updateTodo(result);
						}, function(errorStr) {
							showError(errorStr);
						});
				event.preventDefault();
			});

	// update button handler
	addedRow.find('.update_button').click(
			function(event) {
				window.todoId = todoId;
				$('#detail_update').val(addedRow.find('#detail_cell').text());
				$('#dueDate_update').datepicker(
						"setDate",
						$.datepicker.parseDate('dd/mm/yy', addedRow.find(
								'#duedate_cell').text()));
				$("#dialog-update").dialog("open");
				event.preventDefault();
			});
	// Add button handler
	addedRow.find('.delete_button').click(function(event) {
		window.todoId = todoId;
		$("#dialog-delete").dialog("open");
		event.preventDefault();
	});

}

function configureDialogs() {
	// Delete dialog
	$("#dialog-delete")
			.dialog(
					{
						autoOpen : false,
						resizable : false,
						height : 220,
						modal : true,
						buttons : {
							"Delete Todo" : function() {
								deleteAjaxData(
										"/todo/rest/secure/todos/",
										window.todoId,
										function(data) {
											$(
													".id_cell:containsExactText('"
															+ todoId + "')")
													.closest('tr').remove();
											if ($('#todoListTable').find('td').length == 2) {
												addNoItem();
											}
										}, function(errorStr) {
											showError("error is " + errorStr);
										});
								$(this).dialog("close");
							},
							Cancel : function() {
								$(this).dialog("close");
							}
						}
					});
	// add dialog
	$("#dialog-add")
			.dialog(
					{
						autoOpen : false,
						height : 605,
						width : 300,
						modal : true,
						buttons : {
							"Create new todo" : function() {
								var detail = $('#detail').val();
								var priority = $('#priority').val();
								var dueDate = $.datepicker.formatDate(
										"yy-mm-dd", $('#dueDate').datepicker(
												"getDate"));

								json = {
									"detail" : detail,
									"priority" : priority,
									"dueDate" : dueDate
								};

								insertAjaxData("/todo/rest/secure/todos", json,
										function(result) {
											if ($('#todoListTable').find(
													'#no_items') != null) {
												$('#todoListTable').find(
														'#no_items').remove();
											}
											addTodo(result);
											$("#dialog-add").dialog("close");
										}, function(errorStr) {
											showError(errorStr);
										});
							},
							Cancel : function() {
								$(this).dialog("close");
							}
						},
						close : function() {
							detail = $("#detail");
							priority = $("#priority");
							dueDate = $("#dueDate");
							$([]).add(detail).add(priority).add(dueDate)
									.val("");
						}
					});
	$("#dueDate").datepicker({
		showButtonPanel : true,
		inline : true
	});

	// Update dialog
	$("#dialog-update").dialog(
			{
				autoOpen : false,
				height : 580,
				width : 300,
				modal : true,
				buttons : {
					"Update todo" : function() {
						var detail = $('#detail_update').val();
						var priority = $(
								".id_cell:containsExactText('" + todoId + "')")
								.closest('tr').find('#priority_cell').val();
						var dueDate = $.datepicker.formatDate("yy-mm-dd", $(
								'#dueDate_update').datepicker("getDate"));

						json = {
							"detail" : detail,
							"priority" : priority,
							"dueDate" : dueDate
						};

						updateAjaxData("/todo/rest/secure/todos/"
								+ window.todoId, json, function(result) {
							updateTodo(result);
							$("#dialog-update").dialog("close");
						}, function(errorStr) {
							showError(errorStr);
						});
					},
					Cancel : function() {
						$(this).dialog("close");
					}
				},
				close : function() {
					detail = $("#detail");
					priority = $("#priority");
					dueDate = $("#dueDate");
					$([]).add(detail).add(priority).add(dueDate).val("");
				}
			});
	$("#dueDate_update").datepicker({
		showButtonPanel : true,
		inline : true
	});

}

function configureButtons() {
	// respond to add new todo
	$("#add_todo").click(function(event) {
		$("#dialog-add").dialog("open");
		event.preventDefault();
	});

};