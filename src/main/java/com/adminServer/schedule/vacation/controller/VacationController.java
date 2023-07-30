package com.adminServer.schedule.vacation.controller;

import com.adminServer._core.util.ApiResponse;
import com.adminServer.schedule.vacation.dto.VacationResponse;
import com.adminServer.schedule.vacation.service.VacationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/vacation")
public class VacationController {

    private final VacationService vacationService;

    @GetMapping("/{status}/list")
    public ResponseEntity<ApiResponse.Result<Page<VacationResponse.ListDTO>>> vacationListByStatus(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC)Pageable pageable,
            @PathVariable(required = false) String status) {
        Page<VacationResponse.ListDTO> vacationListPage = vacationService.vacationListByStatus(pageable, status);
        return ResponseEntity.ok(ApiResponse.success(vacationListPage));
    }
}
