package com.adminServer.schedule.duty.service;

import com.adminServer.schedule.Status;
import com.adminServer.schedule.duty.dto.DutyResponse;
import com.adminServer.schedule.duty.model.Duty;
import com.adminServer.schedule.duty.repository.DutyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DutyService {

    private final DutyRepository dutyRepository;

    @Transactional(readOnly = true)
    public Page<DutyResponse.ListDTO> dutyListByStatus(Pageable pageable, String status) {
        // 예외처리
        Status requestStatus = Status.valueOf(status.toUpperCase());
        Page<Duty> dutyPage = dutyRepository.findDutyByStatus(pageable, requestStatus);
        return dutyPage.map(DutyResponse.ListDTO::form);
    }
}
