package com.daghan.todo.domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.daghan.todo.util.JsonDateSerializer;

@Entity
@Table(name = "TODO_ITEMS", catalog = "todolistdb")
// Annotation for JSON marshaling in MVC
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idTODO_ITEMS")
	private Long todoItemId;

	@Size(min = 2, max = 45)
	@Column(name = "detail")
	private String detail;

	@Column(name = "priority")
	@Min(1)
	@Max(3)
	@NotNull
	private Long priority;

	@Column(name = "entered")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date dateEntered;

	@Column(name = "due")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date dueDate;

	@Column(name = "fk_USER_DATA_id")
	private Long userDataForeignKey;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public Date getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Long getUserDataForeignKey() {
		return userDataForeignKey;
	}

	public void setUserDataForeignKey(Long userDataForeignKey) {
		this.userDataForeignKey = userDataForeignKey;
	}

	public Long getTodoItemId() {
		return todoItemId;
	}

}
