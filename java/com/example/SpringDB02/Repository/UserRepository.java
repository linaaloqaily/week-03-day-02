package com.example.SpringDB02.Repository;

import com.example.SpringDB02.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByEmail(String email);

    List<User> findAllByAgeLessThan(Integer age);

//    List<User> findAllByRoleEqual(String role);

    List<User> countAllByRole(String role);

    User findByUsername(String username);

    User findByJoiningYear(Integer joiningYear);

    List<User> findAllByJoiningYearAndAge(Integer joiningYear, Integer age);


}
