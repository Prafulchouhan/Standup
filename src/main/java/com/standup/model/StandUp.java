package com.standup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandUp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "standup_id", nullable = false)
    private Long standUpId;

    private String title;

    @ManyToMany
    @JoinTable(name = "stand_up_users",
            joinColumns = @JoinColumn(name = "stand_up_standup_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<User> users = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_space_space_id")
    private TeamSpace teamSpace;

    @OneToMany(mappedBy = "standUp", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Update> updates = new LinkedHashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stand_up_standup_id")
    private List<Question> questions = new ArrayList<>();
}

