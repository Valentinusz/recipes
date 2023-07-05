package com.valentinusz.recipes.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Class representing an ingredient with portions.
 * @see Ingredient
 */
@Entity
@Table(name = "portioned_ingredients")
@Getter
@Setter
public class PortionedIngredient {
    /** Id of the ingredient. **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Ingredient entity being portioned. */
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    /** Recipe the ingredient is used in with the given portions. */
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    /** Serving size of the portion. */
    @Enumerated(value = EnumType.STRING)
    private ServingSize servingSize;

    /** Amount of the portion. **/
    @Column(precision = 8)
    private Double amount;
}
