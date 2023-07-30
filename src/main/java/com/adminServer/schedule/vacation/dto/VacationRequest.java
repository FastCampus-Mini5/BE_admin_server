package com.adminServer.schedule.vacation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class VacationRequest {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StatusDTO {
        private Long id;

        @NotBlank
        private String status;
    }
}
