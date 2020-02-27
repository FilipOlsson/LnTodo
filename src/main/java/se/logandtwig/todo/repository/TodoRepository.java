package se.logandtwig.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.logandtwig.todo.model.TodoEntity;
import se.logandtwig.todo.model.UserEntity;

import java.util.ArrayList;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

    public ArrayList<TodoEntity> findAllByOwner(UserEntity owner);

    public ArrayList<TodoEntity> findAll();

    public TodoEntity getById(Integer id);

}
