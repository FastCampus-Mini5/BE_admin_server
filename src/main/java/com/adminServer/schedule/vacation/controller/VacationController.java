package com.adminServer.schedule.vacation.controller;

import com.adminServer.schedule.vacation.dto.VacationResponse;
import com.adminServer.schedule.vacation.service.VacationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class VacationController {

    private final VacationService vacationService;

    @GetMapping("/{pending}")
    public Page<VacationResponse.RequestListDTO> VacationRequestList(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String pending) {
        return vacationService.requestList(page, size, pending);
    }
}
