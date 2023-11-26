package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity implements Comparable<ItemEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    private float price;

    private String description;

    @OneToOne
    private CategoryEntity category;

    @Override
    public int compareTo(ItemEntity o) {
        return Float.compare(this.getPrice(), o.getPrice());
    }
}
