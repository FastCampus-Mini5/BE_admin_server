package com.adminServer.schedule.duty.dto;

import com.adminServer.schedule.duty.model.Duty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

public class DutyResponse {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DutyListDTO {

        private String username;
        private String email;
        private Timestamp dutyDate;
        private Timestamp createdDate;

        public static DutyListDTO toListDTO(Duty duty) {
            return DutyListDTO.builder()
                    .username(duty.getUser().getUsername())
                    .email(duty.getUser().getEmail())
                    .dutyDate(duty.getDutyDate())
                    .createdDate(duty.getCreatedDate())
                    .build();
        }
    }
}
