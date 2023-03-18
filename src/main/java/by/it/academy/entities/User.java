package by.it.academy.entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int id;
    @Column(name = "COMPANY_NAME")
    private String nameCompany;
    @Column(name = "LOC")
    private String location;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User() {
    }

    public User(String nameCompany, String location, String email, String userName, String password, UserType userType) {
        this.nameCompany = nameCompany;
        this.location = location;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }
}

