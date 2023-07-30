package com.adminServer.schedule.duty.service;

import com.adminServer._core.errors.ErrorMessage;
import com.adminServer._core.errors.exception.EmptyDtoRequestException;
import com.adminServer._core.errors.exception.EmptyPagingDataRequestException;
import com.adminServer._core.errors.exception.ScheduleServiceException;
import com.adminServer._core.errors.exception.ValidStatusException;
import com.adminServer.schedule.Status;
import com.adminServer.schedule.duty.dto.DutyRequest;
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
        Status requestStatus = isValidStatus(status);
        Page<Duty> dutyPage = dutyRepository.findDutyByStatus(pageable, requestStatus);
        return dutyPage.map(DutyResponse.ListDTO::form);
    }

    @Transactional
    public void updateByStatus(DutyRequest.StatusDTO statusDTO) {
        if (statusDTO == null) throw new EmptyDtoRequestException(ErrorMessage.EMPTY_DATA_TO_DUTY);

        Long id = statusDTO.getId();
        Duty duty = dutyRepository.findById(id).orElseThrow(
                () -> new ScheduleServiceException(ErrorMessage.NOT_FOUND_DUTY));

        String responseStatus = statusDTO.getStatus();
        Status updatedStatus = isValidStatus(responseStatus);

        duty.updateStatus(updatedStatus);
        dutyRepository.save(duty);
    }

    private Status isValidStatus(String status) {
        if (status == null) throw new ValidStatusException();

        try {
            Status validStatus = Status.valueOf(status.toUpperCase());
            return validStatus;
        } catch (IllegalArgumentException e) {
            throw new ValidStatusException();
        }
    }
}
