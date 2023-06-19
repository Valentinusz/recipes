package com.valentinusz.recipes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

/** Entity representing a recipe. */
@Entity
@Table(name = "recipes")
public class Recipe {
    /** Id of the recipe. */
    @Id
    private Integer id;

    /** Name of the recipe. */
    @Size(min = 8, max = 64)
    @NotNull
    private String name;

    /** Time it takes to prepare ingredients for the recipe. */
    @Min(0)
    @NotNull
    private Integer preparationTime;

    /** Time it takes to cook the given meal in minutes. */
    @Min(0)
    @NotNull
    private Integer cookingTime;

    // TODO: 2023. 06. 19. consider creating a dedicated object for this instead of using string.
    /** Instructions of the recipe. */
    @NotNull
    private String instructions;

    /** Author of the recipe. */
    @ManyToOne(optional = false, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    /** Thumbnail of the recipe. */
    @Size(max = 64)
    @NotNull
    public String thumbnail;

    /** Creation time of the entity. */
    @CreationTimestamp
    private Instant createdAt;

    /** Time at which the entity was last updated at. */
    @UpdateTimestamp
    private Instant updatedAt;
}
