package com.adminServer.schedule.duty.dto;

import com.adminServer._core.util.AESEncryptionUtil;
import com.adminServer.schedule.duty.model.Duty;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DutyResponse {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ListDTO {

        private String username;
        private String email;
        private Timestamp dutyDate;
        private Timestamp createdDate;

        public static ListDTO form(Duty duty) {
            return ListDTO.builder()
                    .username(duty.getUser().getUsername())
                    .email(duty.getUser().getEmail())
                    .dutyDate(duty.getDutyDate())
                    .createdDate(duty.getCreatedDate())
                    .build();
        }
    }
}
