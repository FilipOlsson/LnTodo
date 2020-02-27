package se.logandtwig.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.logandtwig.todo.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByUsername(String username);

    public ArrayList<UserEntity> findAll();

}
