package com.adminServer.user.dto;

import com.adminServer.schedule.vacation.model.VacationInfo;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

public class UserResponse {

    @Getter
    @Builder
    public static class ListDTO {

        private String username;
        private String email;
        private Timestamp hireDate;
        private int remainVacation;

        public static ListDTO from(VacationInfo vacationInfo) {
            return ListDTO.builder()
                    .username(vacationInfo.getUser().getUsername())
                    .email(vacationInfo.getUser().getEmail())
                    .hireDate(vacationInfo.getUser().getHireDate())
                    .remainVacation(vacationInfo.getRemainVacation())
                    .build();
        }
    }
}
