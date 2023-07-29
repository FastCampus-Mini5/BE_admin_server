package com.adminServer.schedule.vacation.dto;

import com.adminServer._core.util.AESEncryptionUtil;
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
    public static class ListDTO {
        private String username;
        private String email;
        private Reason reason;
        private Timestamp createdAt;
        private Timestamp startDate;
        private Timestamp endDate;

        public static ListDTO form(Vacation vacation) {
            String decryptedUsername = null;
            String decryptedEmail = null;
            try {
                decryptedUsername = AESEncryptionUtil.decrypt(vacation.getUser().getUsername());
                decryptedEmail = AESEncryptionUtil.decrypt(vacation.getUser().getEmail());

                return ListDTO.builder()
                        .username(decryptedUsername)
                        .email(decryptedEmail)
                        .reason(vacation.getReason())
                        .createdAt(vacation.getCreatedDate())
                        .startDate(vacation.getStartDate())
                        .endDate(vacation.getEndDate())
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
