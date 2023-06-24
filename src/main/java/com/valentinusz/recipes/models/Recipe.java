package com.valentinusz.recipes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.Collection;

/** Entity representing a recipe. */
@Entity
@Table(name = "recipes")
@Getter
@Setter
public class Recipe {
    /** Id of the recipe. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Name of the recipe. */
    @Column(nullable = false)
    @Size(min = 2, max = 64)
    @NotNull
    private String name;

    /** Time it takes to prepare ingredients for the recipe. */
    @Column(nullable = false)
    @Min(0)
    @NotNull
    private Integer preparationTime;

    /** Time it takes to cook the given meal in minutes. */
    @Column(nullable = false)
    @Min(0)
    @NotNull
    private Integer cookingTime;

    // TODO: 2023. 06. 19. consider creating a dedicated object / table for this instead of using string.
    /** Instructions of the recipe. */
    @Column(nullable = false)
    @Length(min = 32)
    @NotNull
    private String instructions;

    /** Author of the recipe. */
    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "author_id")
    private User author;

    /** Thumbnail of the recipe. */
    @Column
    @Size(min = 3, max = 64)
    private String thumbnail;

    /** Ingredients of the recipe. */
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private Collection<PortionedIngredient> ingredients;

    /** Creation time of the entity. */
    @CreationTimestamp
    private Instant createdAt;

    /** Time at which the entity was last updated at. */
    @UpdateTimestamp
    private Instant updatedAt;
}
