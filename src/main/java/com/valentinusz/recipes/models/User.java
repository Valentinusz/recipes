package com.valentinusz.recipes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Collection;

/** Entity representing a user. */
@Entity
@Table(name = "users")
public class User {
    /** Id of the user. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Username of the user. */
    @Column(unique = true, nullable = false, length = 20)
    @Size(min = 6, max = 20)
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
    @Column(unique = true, nullable = false, length = 60)
    @JsonIgnore
    @NotNull
    private String password;

    /** Holds whether the user is an admin or not. */
    @Column(nullable = false, columnDefinition = "bit")
    @ColumnDefault("0")
    private Boolean isAdmin;

    /** Time at which the model was inserted into the database at. */
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
            name = "user_to_dietary_restriction",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "restriction_id")
    )
    private Collection<DietaryRestriction> dietaryRestrictions;

    public User(@NotNull String userName, @NotNull String email, @NotNull String password, @NotNull Boolean isAdmin) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull String getUserName() {
        return userName;
    }

    public void setUserName(@NotNull String userName) {
        this.userName = userName;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public Instant getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Instant emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setRecipes(Collection<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Collection<Recipe> getRecipes() {
        return recipes;
    }

    public void setDietaryRestrictions(Collection<DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }


    public Collection<DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }
}
