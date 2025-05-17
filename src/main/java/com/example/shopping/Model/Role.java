package com.example.shopping.Model;

import com.example.shopping.util.Roles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private Roles role;

    @ManyToMany(mappedBy = "roles" , fetch = FetchType.LAZY
    , cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();
}
