package com.barzhaa.fit.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private User trainer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String timeStart;

    @Column(nullable = false)
    private String timeEnd;

    @Column(nullable = false)
    private int maxParticipants;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Type {
        GROUP, PERSONAL
    }

    public enum Status {
        PLANNED, COMPLETED, CANCELED
    }
}
