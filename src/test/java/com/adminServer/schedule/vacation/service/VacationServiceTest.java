package com.adminServer.schedule.vacation.service;

import com.adminServer._core.errors.exception.EmptyPagingDataRequestException;
import com.adminServer._core.errors.exception.ValidStatusException;
import com.adminServer.schedule.Status;
import com.adminServer.schedule.vacation.dto.VacationResponse;
import com.adminServer.schedule.vacation.model.Reason;
import com.adminServer.schedule.vacation.model.Vacation;
import com.adminServer.schedule.vacation.repository.VacationRepository;
import com.adminServer.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VacationServiceTest {

    @Mock
    private VacationRepository vacationRepository;

    @InjectMocks
    private VacationService vacationService;

    @DisplayName("연차 목록 조회 성공")
    @Test
    void testGetVacationListSuccess() {
        //given
        String validStatus = "PENDING";
        Pageable pageable = mock(Pageable.class);

        List<User> testUsers = List.of(
                createUser(1L, "user1"),
                createUser(2L, "user2"),
                createUser(3L, "user3")
        );

        List<Vacation> vacationList = List.of(
                createVacation(1L, testUsers.get(0), "2023-07-01 00:00:00", "2023-07-05 00:00:00"),
                createVacation(2L, testUsers.get(1), "2023-07-02 00:00:00", "2023-07-03 00:00:00"),
                createVacation(3L, testUsers.get(2), "2023-07-03 00:00:00", "2023-07-04 00:00:00")
        );

        Page<Vacation> mockPage = new PageImpl<>(vacationList, pageable, vacationList.size());
        when(vacationRepository.findVacationsByStatus(pageable, Status.PENDING)).thenReturn(mockPage);

        //when
        Page<VacationResponse.ListDTO> result = vacationService.vacationListByStatus(pageable, validStatus);

        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.getTotalElements());
        verify(vacationRepository, times(1)).findVacationsByStatus(pageable, Status.PENDING);
    }

    @DisplayName("연차 목록 조회 실패 - 유효하지 않은 상태")
    @Test
    void testGetVacationListFailWithStatus() {
        //given
        String invalidStatus = "INVALID_STATUS";
        Pageable pageable = mock(Pageable.class);

        //when, then
        assertThrows(ValidStatusException.class,
                () -> vacationService.vacationListByStatus(pageable, invalidStatus));
        verify(vacationRepository, never()).findVacationsByStatus(any(), any());
    }

    @DisplayName("연차 목록 조회 실패 - 빈 페이지")
    @Test
    void testGetVacationListFailWithPage() {
        //given
        String validStatus = "APPROVED";
        Pageable pageable = null;

        //when, then
        assertThrows(EmptyPagingDataRequestException.class,
                () -> vacationService.vacationListByStatus(pageable, validStatus));
        verify(vacationRepository, never()).findVacationsByStatus(any(), any());
    }

    private User createUser(Long id, String username) {
        return User.builder()
                .id(id)
                .email("test" + id + "@email.com")
                .username(username)
                .password("password123")
                .role("user")
                .profileImage("profile" + id + ".jpg")
                .hireDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    private Vacation createVacation(Long id, User user, String startDate, String endDate) {
        return Vacation.builder()
                .id(id)
                .user(user)
                .reason(Reason.반차)
                .status(Status.PENDING)
                .approvalDate(null)
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .startDate(Timestamp.valueOf(startDate))
                .endDate(Timestamp.valueOf(endDate))
                .build();
    }
}