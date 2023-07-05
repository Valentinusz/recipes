package com.valentinusz.recipes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.Objects;

/** Entity representing an ingredient. */
@Entity
@Table(name = "ingredients")
@Inheritance(strategy = InheritanceType.JOINED)
public class Ingredient {
    /** Id of the ingredient. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    /** Name of the ingredient. */
    @Column(nullable = false, length = 64)
    @Length(min = 2, max = 64)
    @NotNull
    @Getter
    @Setter
    private String name;

    /** Energy content of 1 gram of the ingredient. */
    @Column(nullable = false)
    @Min(0)
    @NotNull
    @Getter
    @Setter
    private Integer energy;

    /** Category of the ingredient. */
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Getter
    @Setter
    private IngredientCategory category;

    /** Creation time of the entity. */
    @CreationTimestamp
    @Getter
    private Instant createdAt;

    /** Time at which the entity was last updated at. */
    @UpdateTimestamp
    @Getter
    private Instant updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, energy, category);
    }
}
