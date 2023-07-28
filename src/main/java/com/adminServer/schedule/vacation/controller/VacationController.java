package com.adminServer.schedule.vacation.controller;

import com.adminServer.schedule.vacation.dto.VacationResponse;
import com.adminServer.schedule.vacation.service.VacationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/vacation")
public class VacationController {

    private final VacationService vacationService;

    @GetMapping("/{status}")
    public Page<VacationResponse.VacationListDTO> VacationListByStatus(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String status) {
        return vacationService.vacationListByStatus(page, size, status);
    }
}
