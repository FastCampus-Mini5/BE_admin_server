package com.adminServer.schedule.vacation.dto;

import com.adminServer.schedule.vacation.model.Reason;
import com.adminServer.schedule.vacation.model.Vacation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class VacationResponse {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RequestListDTO {
        @NotBlank
        private String username;

        @NotBlank
        private String email;

        @NotBlank
        private Reason reason;

        @NotBlank
        private Timestamp createdAt;

        @NotBlank
        private Timestamp startDate;

        @NotBlank
        private Timestamp endDate;

        public static RequestListDTO toRequestListDtO(Vacation vacation) {
            return RequestListDTO.builder()
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
