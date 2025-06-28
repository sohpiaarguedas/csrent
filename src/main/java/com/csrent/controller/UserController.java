package com.csrent.controller;

import com.csrent.dto.UserDto;
import com.csrent.model.Space;
import com.csrent.model.User;
import com.csrent.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping
    public ResponseEntity<?> getAll() {
        List<?> users = userService.getAllUsers();
        if (users == null || users.isEmpty()) {
            return ResponseEntity.ok("No hay users registrados todavia");
            // return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userService.getAllUsers());

    }



    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

//    @PostMapping
//    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
//        if(result.hasErrors()){
//            List<String> errores = new ArrayList<>();
//
//            for (ObjectError error : result.getAllErrors()) {
//                errores.add(error.getDefaultMessage());
//            }
//            return ResponseEntity.badRequest().body(errores);
//        }
//        //User createdUser = userService.createUser(new User());
//      //  return ResponseEntity.status(201).body(createdUser);
//        return ResponseEntity.ok("Se registro el usuario");
//    }
//



    @PutMapping
    public ResponseEntity<?> putSpace(@RequestBody User user) {
        if (user.getEmail() == null || !userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.notFound().build(); // 404 Not Found si no existe
        }

        User updated = userService.updateUser(user);
        return ResponseEntity.ok(updated); // 200 OK con el objeto actualizado
    }


    @PatchMapping
    public ResponseEntity<User> partialUpdateUser(@RequestBody User user) {
        User updatedUser = userService.edit(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user.getEmail()== email) {
            return ResponseEntity.noContent().build();
          }
        return ResponseEntity.notFound().build();
    }


}//fin de la clase
