package com.barzhaa.fit.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "workout_bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingDate;

    @PrePersist
    protected void onCreate() {
        bookingDate = new Date();
        status = Status.BOOKED;
    }

    public enum Status {
        BOOKED, CANCELED, COMPLETED
    }
}
