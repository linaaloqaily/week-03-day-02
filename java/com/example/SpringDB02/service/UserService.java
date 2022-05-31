package com.example.SpringDB02.service;

import com.example.SpringDB02.Repository.UserRepository;
import com.example.SpringDB02.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User getUserID(Integer id) {
        return userRepository.findById(id).get();
    }

    public List<User> getUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUserAge(Integer age) {
        return userRepository.findAllByAgeLessThan(age);
    }

    public List<User> getUserRole(String role) {
       return userRepository.countAllByRole(role);
    }

    public boolean checkUsernamePassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user != null){
            if (user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public String updateUser(User user, Integer id) {
        User isAdmin = userRepository.findById(id).get();
        User oldUser = user;
        if(isAdmin != null){
            oldUser.setUsername(user.getUsername());
            oldUser.setEmail(user.getEmail());
            oldUser.setAge(user.getAge());
            oldUser.setJoiningYear(user.getJoiningYear());
            oldUser.setPassword(user.getPassword());
            return "User is update" ;
        }
        return "User is not admin" ;
    }

    public String updatePassword(Integer id, String newPassword) {
        User user = userRepository.findById(id).get();
        if (user == null){
            return "User id is wrong";
        }
        user.setPassword(newPassword);
        return "Password updated !";
    }

    public Integer checkJoiningYear(Integer id, Integer joiningYear) {
        User user = userRepository.findById(id).get();
        if(user != null){
            if(user.getJoiningYear().equals(joiningYear)){
                return 0; //Correct year
            }
            return 1; //incorrect year
        }
        return -1;  //Invalid id
    }

    public User userJoiningYear(Integer joiningYear) {
        return userRepository.findByJoiningYear(joiningYear);
    }

    public Object userJoiningYearAndAge(Integer joiningYear, Integer age) {
        return userRepository.findAllByJoiningYearAndAge(joiningYear,age);
    }
}
