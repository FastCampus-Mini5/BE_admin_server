package com.adminServer.schedule.vacation.model;

import com.adminServer.schedule.Status;
import com.adminServer.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Table(name = "vacation_tb")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Reason reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Timestamp startDate;

    @Column(nullable = false)
    private Timestamp endDate;

    private Timestamp approvalDate;

    @CreationTimestamp
    private Timestamp createdDate;

    @PrePersist
    protected void onCreate() {
        status = Status.PENDING;
        reason = Reason.휴가;
    }

    public void updateStatus(Status status) {
        this.status = status;
        this.approvalDate = Timestamp.valueOf(LocalDateTime.now());
    }
}
