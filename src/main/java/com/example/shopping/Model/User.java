package com.example.shopping.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int userId;

    protected String firstName;

    protected String lastName;

    protected String password;

    @Column(name = "email", unique = true)
    protected String email;

    @Column(unique = true)
    protected String phone;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH, CascadeType.DETACH}
            , fetch = FetchType.EAGER, mappedBy = "user")
    protected Set<Review> review = new HashSet<>();

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REFRESH, CascadeType.DETACH}
            , fetch = FetchType.EAGER, mappedBy = "user")
    protected Set<Address> addresses = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

}