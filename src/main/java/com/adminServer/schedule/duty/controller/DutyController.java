package com.adminServer.schedule.duty.controller;

import com.adminServer._core.util.ApiResponse;
import com.adminServer.schedule.duty.dto.DutyRequest;
import com.adminServer.schedule.duty.dto.DutyResponse;
import com.adminServer.schedule.duty.service.DutyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/duty")
public class DutyController {

    private final DutyService dutyService;

    @GetMapping("/{status}/list")
    public ResponseEntity<ApiResponse.Result<Page<DutyResponse.ListDTO>>> dutyListByStatus(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC)Pageable pageable,
            @PathVariable(required = false) String status) {
        Page<DutyResponse.ListDTO> dutyListPage = dutyService.dutyListByStatus(pageable, status);
        return ResponseEntity.ok(ApiResponse.success(dutyListPage));
    }

    @PostMapping("/proceed")
    public ResponseEntity<ApiResponse.Result<String>> dutyApprove(
            @RequestBody @Valid DutyRequest.StatusDTO statusDTO, Error error) {
        dutyService.updateByStatus(statusDTO);
        return ResponseEntity.ok(ApiResponse.success());
    }
}
