package com.example.SpringDB02.controller;

import com.example.SpringDB02.model.User;
import com.example.SpringDB02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<String> addUser(@Valid @RequestBody User user , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(200).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(201).body("User added !");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserID(@PathVariable Integer id){
        return ResponseEntity.status(200).body(userService.getUserID(id));
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<User>> getUserEmail(@RequestBody String email){
        return ResponseEntity.status(200).body(userService.getUserEmail(email));
    }

    @GetMapping("/{age}")
    public ResponseEntity<List<User>> getUserAge(@PathVariable Integer age){
        return ResponseEntity.status(200).body(userService.getUserAge(age));
    }

    @GetMapping("/role")
    public ResponseEntity<List<User>> getUserRole(@RequestParam String role){
        return ResponseEntity.status(200).body(userService.getUserRole(role));
    }

    @GetMapping("/check")
    public ResponseEntity checkUsernamePassword(@RequestParam String username, @RequestParam String password){
        if(userService.checkUsernamePassword(username, password)){
            return ResponseEntity.status(200).body("Username and password matching !");
        }
        return ResponseEntity.status(400).body("password or username is incorrect");
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateOldUser(@RequestBody User user, @PathVariable Integer id){
        userService.updateUser(user,id);
        return ResponseEntity.status(200).body("User Updated !");
    }

    @PutMapping("/newPassword/{id}")
    public ResponseEntity updateOldPassword(@PathVariable Integer id, @RequestParam String newPassword){
        userService.updatePassword(id,newPassword);
        return ResponseEntity.status(200).body("Password Updated !");
    }

    @GetMapping("{id}/{year}")
    public ResponseEntity checkJoiningYear(@PathVariable Integer id, @PathVariable Integer joiningYear){
        Integer check = userService.checkJoiningYear(id,joiningYear);
        if(check == 0){
            return ResponseEntity.status(200).body("Correct year entered");
        } else if (check ==1) {
            return ResponseEntity.status(400).body("incorrect year entered");
        }
        return ResponseEntity.status(400).body("Invalid id");
    }

    @GetMapping("/joiningYearAfter")
    public ResponseEntity<User> userJoiningYear(@RequestParam Integer joiningYear) {
        return ResponseEntity.status(200).body(userService.userJoiningYear(joiningYear));
    }

    @GetMapping("/{year}/{age}")
    public ResponseEntity<Object> userJoiningYearAndAge(@PathVariable Integer joiningYear, @PathVariable Integer age){
        return ResponseEntity.status(200).body(userService.userJoiningYearAndAge(joiningYear,age));
    }

}
