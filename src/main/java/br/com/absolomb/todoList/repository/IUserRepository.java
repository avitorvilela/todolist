package br.com.absolomb.todoList.repository;

import br.com.absolomb.todoList.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
}
