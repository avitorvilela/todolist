package br.com.absolomb.todoList.repository;

import br.com.absolomb.todoList.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByIdUser(UUID idUser);
    // List<TaskModel> findByIdAndIdUser(UUID id, UUID idUser);
}
