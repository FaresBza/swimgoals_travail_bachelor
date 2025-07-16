package com.swimgoals.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "swims")
public class Swim {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    public Swim() {
        this(0, null);
    }

    public Swim(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public static final Swim Papillon = new Swim(1, "Papillon");
    public static final Swim Dos = new Swim(2, "Dos");
    public static final Swim Brasse = new Swim(3, "Brasse");
    public static final Swim Crawl = new Swim(4, "Crawl");
    public static final Swim QuatreNages = new Swim(5, "4 Nages");
    public static final Swim Jambes = new Swim(6, "4 Nages");
    public static final Swim NageLibre = new Swim(7, "Nage Libre");
}
