package se.logandtwig.todo.controller;

import org.springframework.web.bind.annotation.*;
import se.logandtwig.todo.controller.response.TodoDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoApi {

	@GetMapping("/todo")
	public List<TodoDto> getAll(@RequestParam(value = "username") String username) {
		return new ArrayList<>(); // Implement me
	}

	@GetMapping("/todo/{id}")
	public TodoDto getOne(@PathVariable(value = "id") Long id,
	                      @RequestParam(value = "username") String username) {
		return new TodoDto(); // Implement me
	}

	@PostMapping("/todo")
	public TodoDto create(@RequestBody TodoDto todo,
	                      @RequestParam(value = "username") String username) {
		return new TodoDto(); // Implement me
	}

	@DeleteMapping("/todo/{id}")
	public TodoDto delete(@PathVariable(value = "id") Long id,
	                      @RequestParam(value = "username") String username) {
		return new TodoDto(); // Implement me
	}

	// Optional bonus! //
	@PutMapping("/todo")
	public TodoDto edit(@RequestBody TodoDto todo,
	                    @RequestParam(value = "username") String username) {
		return new TodoDto(); // Implement me
	}
}
