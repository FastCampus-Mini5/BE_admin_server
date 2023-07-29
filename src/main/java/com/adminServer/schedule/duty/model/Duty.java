package com.adminServer.schedule.duty.model;


import com.adminServer.schedule.Status;
import com.adminServer.user.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "on_duty_tb")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Duty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Timestamp dutyDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private Timestamp approvalDate;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;

    @PrePersist
    protected void onCreate() {
        status = Status.PENDING;
    }
}
