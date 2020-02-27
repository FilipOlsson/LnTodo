package se.logandtwig.todo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "todos")
public class TodoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * The title of the task
	 */
	private String task;

	/**
	 * The title of the task
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserEntity owner;

}