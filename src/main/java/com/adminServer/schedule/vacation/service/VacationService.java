package com.adminServer.schedule.vacation.service;

import com.adminServer._core.errors.ErrorMessage;
import com.adminServer._core.errors.exception.EmptyDtoRequestException;
import com.adminServer._core.errors.exception.EmptyPagingDataRequestException;
import com.adminServer._core.errors.exception.ScheduleServiceException;
import com.adminServer._core.errors.exception.ValidStatusException;
import com.adminServer.schedule.Status;
import com.adminServer.schedule.vacation.dto.VacationRequest;
import com.adminServer.schedule.vacation.dto.VacationResponse;
import com.adminServer.schedule.vacation.model.Vacation;
import com.adminServer.schedule.vacation.model.VacationInfo;
import com.adminServer.schedule.vacation.repository.VacationRepository;
import com.adminServer.schedule.vacation.repository.VacationInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VacationService {

    private final VacationRepository vacationRepository;
    private final VacationInfoRepository vacationInfoRepository;

    @Transactional(readOnly = true)
    public Page<VacationResponse.ListDTO> vacationListByStatus(Pageable pageable, String status) {
        if (pageable == null) throw new EmptyPagingDataRequestException();

        Status requestStatus = isValidStatus(status);
        Page<Vacation> vacationPage = vacationRepository.findVacationsByStatus(pageable, requestStatus);
        return vacationPage.map(VacationResponse.ListDTO::form);
    }

    @Transactional
    public void updateStatus(VacationRequest.StatusDTO statusDTO) {
        if (statusDTO == null) throw new EmptyDtoRequestException(ErrorMessage.EMPTY_DATA_TO_VACATION);
        Status responseStatus = isValidStatus(statusDTO.getStatus());

        Long vacationId = statusDTO.getId();
        Optional<Vacation> optionalVacation = vacationRepository.findById(vacationId);
        Vacation vacation = optionalVacation.orElseThrow(
                () -> new ScheduleServiceException(ErrorMessage.NOT_FOUND_VACATION));

        vacation.updateStatus(responseStatus);
        vacationRepository.save(vacation);
        Long userId = vacation.getUser().getId();

        if (responseStatus == Status.APPROVE) {
            updateVacationInfoForApproval(userId);
        }
    }

    private void updateVacationInfoForApproval(Long userId) {
        VacationInfo vacationInfo = vacationInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new ScheduleServiceException(ErrorMessage.NOT_FOUND_VACATION));

        vacationInfo.updateInfo();
        vacationInfoRepository.save(vacationInfo);
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
