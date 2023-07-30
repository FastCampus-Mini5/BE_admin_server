package com.adminServer.schedule.vacation.model;

import com.adminServer.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "vacation_info_tb")
@Entity
public class VacationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int remainVacation;

    @Column(nullable = false)
    private int usedVacation;

    @PrePersist
    public void onCreate() {
        remainVacation = 0;
        usedVacation = 0;
    }

    public void updateInfo() {
        this.remainVacation -= 1;
        this.usedVacation += 1;
    }
}
