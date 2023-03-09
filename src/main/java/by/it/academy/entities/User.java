package by.it.academy.entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "COMPNAME")
    private String nameCompany;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NAMEUSER")
    private String userName;
    @Column(name = "PASSUSER")
    private String password;
    @Column(name = "TYPEUSER")
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

