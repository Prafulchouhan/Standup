package com.standup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String userName;

    private String Email;

    @JsonIgnore
    private String Password;

    private String Role;

    @ManyToMany(mappedBy = "users")
    private Set<StandUp> standUps = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "users", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,CascadeType.REMOVE})
    private Set<TeamSpace> teamSpaces = new LinkedHashSet<>();
}
