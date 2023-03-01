package JPA.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "MESSAGE")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "TEXT")
    private String text;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }
}
