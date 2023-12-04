package com.standup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Update {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "update_id", nullable = false)
    private Long updateId;

    private LocalDateTime reportAddDateAndTime;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stand_up_standup_id")
    private StandUp standUp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "update_update_id")
    private List<Answer> answers = new ArrayList<>();

}
