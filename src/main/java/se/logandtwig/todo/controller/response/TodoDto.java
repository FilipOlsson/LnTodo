package se.logandtwig.todo.controller.response;

import se.logandtwig.todo.model.TodoEntity;


/**
 * This is the response representation of a single task.
 */
public class TodoDto {

	public TodoDto() {
	}

	public TodoDto(Integer id, String task, String username) {
		this.id = id;
		this.task = task;
		this.username = username;
	}

	private Integer id;

	private String task;

	private String username;

	/**
	 * @return The unique ID of the task
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return The actual task
	 */
	public String getTask() {
		return task;
	}

	/**
	 * @return The owner of the task
	 */
	public String username() {
		return username;
	}

	public static TodoDto convertToDto(TodoEntity todoEntity){
		return new TodoDto(todoEntity.getId(),todoEntity.getTask(),todoEntity.getOwner().getUsername());
	}
}
