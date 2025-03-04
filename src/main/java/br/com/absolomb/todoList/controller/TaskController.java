package br.com.absolomb.todoList.controller;

import br.com.absolomb.todoList.model.TaskModel;
import br.com.absolomb.todoList.repository.ITaskRepository;
import br.com.absolomb.todoList.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskRepository iTaskRepository;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();

        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Data de início ou data de término deve ser maior do que a data atual.");
        }

        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tarefas não podem ter a data de início após a data de término.");
        }

        var task = this.iTaskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.iTaskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TaskModel taskModel,
    HttpServletRequest request,
                    @PathVariable UUID id) {
        var task = this.iTaskRepository.findById(id).orElse(null);

        if(task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa " +
                    "inexistente.");
        }

        var idUser = request.getAttribute("idUser");

        if(!task.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário sem " +
                    "permissão para alterar essa tarefa.");
        }

        Utils.copyNonNullProperties(taskModel, task);
        var taskUpdated = this.iTaskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(this.iTaskRepository.
                save(taskUpdated));
    }
}