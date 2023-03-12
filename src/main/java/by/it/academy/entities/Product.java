package by.it.academy.entities;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "PRODUCTS", schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROD_ID")
    private int id;
    @Column(name = "WEIGHT")
    private int weight;
    @Column(name = "LOADING_LOCATION")
    private String loadingLocation;
    @Column(name = "UNLOADING_LOCATION")
    private String unloadingLocation;
    @Column(name = "CARGO_COST")
    private int cargoCost;

    public Product() {
    }

    public Product(int weight, String loadingLocation, String unloadingLocation, int cargoCost) {
        this.weight = weight;
        this.loadingLocation = loadingLocation;
        this.unloadingLocation = unloadingLocation;
        this.cargoCost = cargoCost;
    }
}
