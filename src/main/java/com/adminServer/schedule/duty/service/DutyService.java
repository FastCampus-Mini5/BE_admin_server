package com.adminServer.schedule.duty.service;

import com.adminServer._core.errors.exception.EmptyPagingDataRequestException;
import com.adminServer._core.errors.exception.ValidStatusException;
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
        if (pageable == null) throw new EmptyPagingDataRequestException();
        if (status == null || !isValidStatus(status)) throw new ValidStatusException();

        Status requestStatus = Status.valueOf(status.toUpperCase());
        Page<Duty> dutyPage = dutyRepository.findDutyByStatus(pageable, requestStatus);
        return dutyPage.map(DutyResponse.ListDTO::form);
    }

    private boolean isValidStatus(String status) {
        for (Status validStatus : Status.values()) {
            if (validStatus.name().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }
}
