package com.adminServer.schedule.vacation.service;

import com.adminServer.schedule.Status;
import com.adminServer.schedule.vacation.dto.VacationResponse;
import com.adminServer.schedule.vacation.model.Vacation;
import com.adminServer.schedule.vacation.repository.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class VacationService {

    private final VacationRepository vacationRepository;

    @Transactional(readOnly = true)
    public Page<VacationResponse.ListDTO> vacationListByStatus(Pageable pageable, String status) {
        // 예외처리
        Status requesStatus = Status.valueOf(status.toUpperCase());
        Page<Vacation> vacationPage = vacationRepository.findVacationsByStatus(pageable, requesStatus);
        return vacationPage.map(VacationResponse.ListDTO::form);
    }
}
