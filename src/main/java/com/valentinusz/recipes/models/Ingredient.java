package com.valentinusz.recipes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;

/** Entity representing an ingredient. */
@Entity
@Table(name = "ingredients")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ingredient {
    /** Id of the ingredient. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    /** Name of the ingredient. */
    @Column(nullable = false, length = 64, unique = true)
    @Length(min = 2, max = 64)
    @NotNull
    private String name;

    /** Energy content of 1 gram of the ingredient. */
    @Column(nullable = false)
    @Min(0)
    @NotNull
    private Integer energy;

    /** Category of the ingredient. */
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private IngredientCategory category;

    /** Creation time of the entity. */
    @Column(nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    /** Time at which the entity was last updated at. */
    @Column(nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;
}
