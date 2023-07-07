package com.valentinusz.recipes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class representing an ingredient with portions.
 * @see Ingredient
 */
@Entity
@Table(name = "portioned_ingredients")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PortionedIngredient {
    /** Id of the ingredient. **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    /** Ingredient entity being portioned. */
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    @NotNull
    private Ingredient ingredient;

    /** Recipe the ingredient is used in with the given portions. */
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @NotNull
    private Recipe recipe;

    /** Serving size of the portion. */
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private ServingSize servingSize;

    /** Amount of the portion. **/
    @Column(nullable = false, precision = 8)
    @NotNull
    private Double amount;
}
