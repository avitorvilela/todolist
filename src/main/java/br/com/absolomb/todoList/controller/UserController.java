package br.com.absolomb.todoList.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.absolomb.todoList.model.UserModel;
import br.com.absolomb.todoList.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository iUserRepository;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody UserModel userModel) {
        var user = this.iUserRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe.");
        }

        var passwordHashed =
                BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashed);

        var userCreated = this.iUserRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
