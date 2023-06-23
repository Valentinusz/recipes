package com.valentinusz.recipes.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "portioned_ingredients")
@Getter
@Setter
public class PortionedIngredient {
    /** Id of the entity. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Recipe the ingredient is used in with the given portions. */
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    /** Ingredient portioned. */
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    /** */
    @Enumerated(value = EnumType.STRING)
    private ServingSize servingSize;

    @Column(precision = 8, scale = 2)
    private Double amount;
}
