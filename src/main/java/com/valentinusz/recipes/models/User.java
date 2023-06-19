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

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 20)
    @Size(min = 6, max = 20)
    private String userName;

    @Column(unique = true, nullable = false, length = 32)
    @Email
    private String email;

    private Instant emailVerifiedAt;

    @Column(unique = true, nullable = false, length = 20)
    @JsonIgnore
    private String password;

    @Column(nullable = false, columnDefinition = "bit")
    @ColumnDefault("0")
    private Boolean isAdmin;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany
    private Collection<Recipe> recipes;

    public User() {}

    public User(@NotNull String userName, @NotNull String email, @NotNull String password, @NotNull Boolean isAdmin) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
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
}
