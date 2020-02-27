package se.logandtwig.todo;

import org.junit.jupiter.api.Test;
import se.logandtwig.todo.controller.response.TodoDto;
import se.logandtwig.todo.validations.TodoValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoApplicationTests {

	@Test
	void test() {
		assertEquals("Hello World!","Hello".concat(" World") + "!");
	}

	@Test
	public void whenExceptionThrown_thenAssertionSucceeds() {
		TodoValidator todoValidator = new TodoValidator();
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			TodoDto todoDto = new TodoDto(1,null,"Pelle");
			todoValidator.validate(todoDto);
		});

		String expectedMessage = "Task should not be empty";
		String actualMessage = exception.getMessage();

		assertEquals(actualMessage,expectedMessage);
	}


}
