package com.adminServer.schedule.duty.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
public class DutyRequest {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatusDTO {

        private Long id;

        @NotBlank
        private String status;
    }
}
