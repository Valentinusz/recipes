package com.valentinusz.recipes.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "portioned_ingredients")
@Getter
@Setter
public class PortionedIngredient extends Ingredient {
    /** Recipe the ingredient is used in with the given portions. */
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    /** */
    @Enumerated(value = EnumType.STRING)
    private ServingSize servingSize;

    @Column(precision = 8)
    private Double amount;
}
