package com.example.SpringDB02.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@AllArgsConstructor @NoArgsConstructor @Data
@Entity
public class User {
    @Id
    @NotNull(message = "id is required")
    private Integer id;

    @NotEmpty(message = "username is required")
    private String username;

    @NotEmpty(message = "password is required")
    private String password;

    @NotEmpty(message = "email is required")
    private String email;

    @Pattern(regexp = "(Admin|Teacher|Student)", message = "role must be (Admin or Teacher orStudent)")
    private String role;

    @Positive
    @NotEmpty(message = "joining year is required")
    @Range(min = 200, max = 2022, message = "joiningYear must be between 2000 -2022")
    private String joiningYear;

    @NotEmpty(message = "age is required")
    @Positive
    @Min(7)
    private Integer age;

}
