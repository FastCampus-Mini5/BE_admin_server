package com.adminServer.schedule.vacation.dto;

import com.adminServer.schedule.Status;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class VacationRequest {

    @Getter
    public static class StatusDTO {
        private Long id;

        @NotBlank
        private String status;
    }
}

