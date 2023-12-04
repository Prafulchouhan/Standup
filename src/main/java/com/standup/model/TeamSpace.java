package com.standup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "space_id", nullable = false)
    private Long spaceId;

    private String title;

    @OneToMany(mappedBy = "teamSpace", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StandUp> standUps = new LinkedHashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @JoinTable(
            name = "team_space_users",
            joinColumns = @JoinColumn(name = "team_space_space_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    @JsonIgnore
    private Set<User> users = new LinkedHashSet<>();

}
