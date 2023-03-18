package by.it.academy.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CHARACTERISTICS", schema = "public")
public class TransportCharacteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHARACTERISTIC_ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "VOLUME_FACTOR")
    private String volumeFactor;


    public TransportCharacteristic() {
    }


    public TransportCharacteristic(String name, String volumeFactor) {
        this.name = name;
        this.volumeFactor = volumeFactor;
    }
}

