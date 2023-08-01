package com.adminServer.schedule.vacation.dto;

import com.adminServer.schedule.vacation.model.Reason;
import com.adminServer.schedule.vacation.model.Vacation;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VacationResponse {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ListDTO {
        private String username;
        private String email;
        private Reason reason;
        private Timestamp createdAt;
        private Timestamp startDate;
        private Timestamp endDate;

        public static ListDTO form(Vacation vacation) {
            return ListDTO.builder()
                    .username(vacation.getUser().getUsername())
                    .email(vacation.getUser().getEmail())
                    .reason(vacation.getReason())
                    .createdAt(vacation.getCreatedDate())
                    .startDate(vacation.getStartDate())
                    .endDate(vacation.getEndDate())
                    .build();
        }
    }
}
