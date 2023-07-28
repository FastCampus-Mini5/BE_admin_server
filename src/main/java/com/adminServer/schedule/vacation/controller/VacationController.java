package com.adminServer.schedule.vacation.controller;

import com.adminServer._core.util.ApiResponse;
import com.adminServer.schedule.duty.dto.DutyResponse;
import com.adminServer.schedule.vacation.dto.VacationResponse;
import com.adminServer.schedule.vacation.service.VacationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/vacation")
public class VacationController {

    private final VacationService vacationService;

    @GetMapping("/{status}")
    public ResponseEntity<ApiResponse.Result<Page<VacationResponse.VacationListDTO>>> VacationListByStatus(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String status) {
        Page<VacationResponse.VacationListDTO> vacationListPage = vacationService.vacationListByStatus(page, size, status);
        return ResponseEntity.ok(ApiResponse.success(vacationListPage));
    }
}
