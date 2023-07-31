package com.adminServer.user.service;

import com.adminServer._core.errors.exception.EmptyPagingDataRequestException;
import com.adminServer.schedule.vacation.model.VacationInfo;
import com.adminServer.user.dto.UserResponse;
import com.adminServer.schedule.vacation.repository.VacationInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final VacationInfoRepository vacationInfoRepository;

    public Page<UserResponse.ListDTO> getAllUsers(Pageable pageable) {
        if (pageable == null) throw new EmptyPagingDataRequestException();

        Page<VacationInfo> allUsersWithVacationInfo = vacationInfoRepository.findAll(pageable);
        return allUsersWithVacationInfo.map(UserResponse.ListDTO::from);
    }
}
