package com.adminServer.schedule.vacation.dto;

import com.adminServer.schedule.vacation.model.Reason;
import com.adminServer.schedule.vacation.model.Vacation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

public class VacationResponse {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class VacationListDTO {
        private String username;
        private String email;
        private Reason reason;
        private Timestamp createdAt;
        private Timestamp startDate;
        private Timestamp endDate;

        public static VacationListDTO toListDtO(Vacation vacation) {
            return VacationListDTO.builder()
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
