package by.it.academy.entities;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "PRODUCTS", schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPROD")
    private int id;
    @Column(name = "WEIGHT")
    private int weight;
    @Column(name = "LOADINGLOC")
    private String loadingLocation;
    @Column(name = "UNLOADINGLOC")
    private String unloadingLocation;
    @Column(name = "CARGOCOST")
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
