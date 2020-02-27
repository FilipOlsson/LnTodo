package se.logandtwig.todo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.logandtwig.todo.controller.response.TodoDto;
import se.logandtwig.todo.model.TodoEntity;
import se.logandtwig.todo.model.UserEntity;
import se.logandtwig.todo.repository.TodoRepository;
import se.logandtwig.todo.repository.UserRepository;
import se.logandtwig.todo.validations.TodoValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TodoApi {

	private TodoRepository todoRepository;
	private UserRepository userRepository;
	private TodoValidator  todoValidator;


	TodoApi(TodoRepository todoRepository, UserRepository userRepository){
		this.todoRepository = todoRepository;
		this.userRepository = userRepository;
		this.todoValidator  = new TodoValidator();
	}

	@GetMapping("/todo")
	public List<TodoDto> getAll(@RequestParam(value = "username") String username) {

		ArrayList<TodoEntity> todos = todoRepository.findAllByOwner(findOwner(username));

		return todos.stream().map(this::convertToDto).collect(Collectors.toList()); // Implement me
	}

	@GetMapping("/todo/{id}")
	public TodoDto getOne(@PathVariable(value = "id") Integer id,
	                      @RequestParam(value = "username") String username) {

		TodoEntity todoEntity = todoRepository.getById(id);
		if(todoEntity == null) throw new TaskNotFoundException();
		if(!todoEntity.getOwner().getUsername().equals(username)) throw new IncorrectUserException();

		return convertToDto(todoEntity);
	}

	@PostMapping("/todo")
	public TodoDto create(@RequestBody TodoDto todoDto,
	                      @RequestParam(value = "username") String username) {
		todoValidator.validate(todoDto);
 		TodoEntity todoEntity = new TodoEntity();
 		todoEntity.setOwner(findOwner(username));
 		todoEntity.setTask(todoDto.getTask());
		return convertToDto(todoRepository.save(todoEntity));
	}

	@DeleteMapping("/todo/{id}")
	public TodoDto delete(@PathVariable(value = "id") Integer id,
	                      @RequestParam(value = "username") String username) {
		TodoEntity todoEntity = todoRepository.getById(id);

		if(todoEntity == null) throw new TaskNotFoundException();
		if(!todoEntity.getOwner().getUsername().equals(username)) throw new IncorrectUserException();
		todoRepository.deleteById(id);
		return convertToDto(todoEntity);
	}

	// Optional bonus! //
	@PutMapping("/todo")
	public TodoDto edit(@RequestBody TodoDto todo,
	                    @RequestParam(value = "username") String username) {
		todoValidator.validate(todo);
		TodoEntity todoEntity = todoRepository.getById(todo.getId());

		if(todoEntity == null) throw new TaskNotFoundException();
		if(!todoEntity.getOwner().getUsername().equals(username)) throw new IncorrectUserException();
		todoEntity.setTask(todo.getTask());

		return convertToDto(todoRepository.save(todoEntity));
	}

	//Health endpoint @ url/actuator/health ¯\_(ツ)_/¯


	/// private methods
	private TodoDto convertToDto(TodoEntity todoEntity){
		return TodoDto.convertToDto(todoEntity);
	}

	private UserEntity findOwner(String username){
		UserEntity owner = userRepository.findByUsername(username);
		if(owner == null) throw new UserNotFoundException();
		return owner;
	}


	//Error responses
	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Task not found")
	public class TaskNotFoundException extends RuntimeException {
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
	public class UserNotFoundException extends RuntimeException {
	}

	@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "User not allowed to access task")
	public class IncorrectUserException extends RuntimeException {
	}
}
