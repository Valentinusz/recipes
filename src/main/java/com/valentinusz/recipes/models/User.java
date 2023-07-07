package com.valentinusz.recipes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Collection;

/** Entity representing a user. */
@Entity
@Table(name = "users")
@Data

public class User {
    /** Id of the user. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    /** Username of the user. */
    @Column(unique = true, nullable = false, length = 20)
    @Size(min = 4, max = 20)
    @NotNull
    private String userName;

    /** Email address of the user. */
    @Column(unique = true, nullable = false, length = 32)
    @Email
    @NotNull
    private String email;

    /** Time at which the users email was verified at. */
    @Column
    private Instant emailVerifiedAt;

    /** Password of the user. **/
    @Column(nullable = false, length = 60)
    @JsonIgnore
    @NotNull
    private String password;

    /** Holds whether the user is an admin or not. */
    @Column(nullable = false, columnDefinition = "bit")
    @ColumnDefault("0")
    private Boolean isAdmin = false;

    /** Time at which the model was inserted into the database at. */
    @Column(nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    /** Time at which the model was last updated at. */
    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Collection<Recipe> recipes;

    @ManyToMany
    @JoinTable(
            name = "favourites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private Collection<Recipe> favourites;

    @ManyToMany
    @JoinTable(
            name = "dietary_restrictions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private Collection<IngredientAttribute> dietaryRestrictions;
}
