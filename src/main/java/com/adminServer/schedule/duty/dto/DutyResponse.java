package com.adminServer.schedule.duty.dto;

import com.adminServer._core.util.AESEncryptionUtil;
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
    public static class ListDTO {

        private String username;
        private String email;
        private Timestamp dutyDate;
        private Timestamp createdDate;

        public static ListDTO form(Duty duty) {
            String decryptedUsername = null;
            String decryptedEmail = null;
            try {
                decryptedUsername = AESEncryptionUtil.decrypt(duty.getUser().getUsername());
                decryptedEmail = AESEncryptionUtil.decrypt(duty.getUser().getEmail());

                return ListDTO.builder()
                        .username(decryptedUsername)
                        .email(decryptedEmail)
                        .dutyDate(duty.getDutyDate())
                        .createdDate(duty.getCreatedDate())
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
