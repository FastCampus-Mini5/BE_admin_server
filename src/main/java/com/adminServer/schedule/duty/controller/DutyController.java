package com.adminServer.schedule.duty.controller;

import com.adminServer.schedule.duty.dto.DutyResponse;
import com.adminServer.schedule.duty.service.DutyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/duty")
public class DutyController {

    private final DutyService dutyService;

    @GetMapping("/{status}")
    public Page<DutyResponse.DutyListDTO> DutyListByStatus(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String status) {
        return dutyService.DutyListByStatus(page, size, status);
    }
}
