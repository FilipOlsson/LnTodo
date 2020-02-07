package se.logandtwig.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * The users username. Passwords and authentication is soo~ 2010.
	 */
	private String username;

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
}