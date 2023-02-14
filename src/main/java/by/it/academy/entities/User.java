package by.it.academy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String nameCompany;
    private String location;
    private String email;
    private String userName;
    private String password;
    private UserType userType;
}

