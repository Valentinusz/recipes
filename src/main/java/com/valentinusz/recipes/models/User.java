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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

/** Entity representing a user. */
@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
