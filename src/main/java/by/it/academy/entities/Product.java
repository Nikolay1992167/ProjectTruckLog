package by.it.academy.entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCTS", schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROD_ID", nullable = false)
    private int id;
    @Column(name = "PROD_NAME")
    private String name;
    @Column(name = "WEIGHT", nullable = false)
    private int weight;
    @Column(name = "LOADING_LOCATION")
    private String loadingLocation;
    @Column(name = "UNLOADING_LOCATION")
    private String unloadingLocation;
    @Column(name = "CARGO_COST")
    private int cargoCost;
    @Column(name = "CHARACTERISTIC_ID")
    private int transportCharacteristic;

    public Product() {
    }

    public Product(String name, int weight, String loadingLocation, String unloadingLocation, int cargoCost, int transportCharacteristic) {
        this.name = name;
        this.weight = weight;
        this.loadingLocation = loadingLocation;
        this.unloadingLocation = unloadingLocation;
        this.cargoCost = cargoCost;
        this.transportCharacteristic = transportCharacteristic;
    }
}
