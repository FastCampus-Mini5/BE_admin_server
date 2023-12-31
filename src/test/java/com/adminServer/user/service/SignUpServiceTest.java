package com.adminServer.user.service;

import com.adminServer._core.errors.exception.EmptyDtoRequestException;
import com.adminServer._core.errors.exception.EmptyPagingDataRequestException;
import com.adminServer._core.errors.exception.SignUpServiceException;
import com.adminServer.user.dto.SignUpRequest;
import com.adminServer.user.dto.SignUpResponse;
import com.adminServer.user.model.SignUp;
import com.adminServer.user.model.User;
import com.adminServer.user.repository.SignUpRepository;
import com.adminServer.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SignUpServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SignUpRepository signUpRepository;

    @InjectMocks
    private SignUpService signUpService;

    @DisplayName("회원 가입 요청 리스트 조회 성공 테스트")
    @Test
    void getAllList_Success_Test() {
        // Given
        List<SignUp> mockList = List.of(
                SignUp.builder().id(1L).email("user1@email.com").username("user1")
                        .hireDate((Timestamp.valueOf(LocalDateTime.now()))).build(),

                SignUp.builder().id(2L).email("user2@email.com").username("user2")
                        .hireDate((Timestamp.valueOf(LocalDateTime.now()))).build(),

                SignUp.builder().id(3L).email("user3@email.com").username("user3")
                        .hireDate((Timestamp.valueOf(LocalDateTime.now()))).build()
        );

        Pageable pageable = PageRequest.of(0, 3);
        Page<SignUp> mockPage = new PageImpl<>(mockList, pageable, 1);

        Mockito.when(signUpRepository.findAll(pageable))
                .thenReturn(mockPage);

        // When
        Page<SignUpResponse.ListDTO> actual = signUpService.getAllList(pageable);

        // Then
        Assertions.assertEquals(3, actual.getTotalElements());
        Assertions.assertEquals(1, actual.getTotalPages());
    }

    @DisplayName("회원 가입 요청 리스트 조회 실패 테스트 - 빈 페이지 정보 요청")
    @Test
    void getAllList_Failure_Test() {
        // Given
        // When
        // Then
        Assertions.assertThrows(EmptyPagingDataRequestException.class, () -> {
            signUpService.getAllList(null);
        });
    }

    @DisplayName("회원 가입 요청 승인 성공 테스트")
    @Test
    void approve_Success_Test() {
        // Given
        SignUpRequest.ApproveDTO approveDTO = new SignUpRequest.ApproveDTO("user@example.com");
        SignUp signUp = SignUp.builder().email("user@example.com").build();

        Mockito.when(signUpRepository.findByEmail(approveDTO.getEmail()))
                .thenReturn(Optional.of(signUp));

        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class)))
                .thenReturn(new User());

        // When
        // Then
        signUpService.approve(approveDTO);
    }

    @DisplayName("회원 가입 요청 승인 실패 테스트 - 비어있는 DTO")
    @Test
    void approve_Failure_Test_EmptyDTO() {
        // Given
        // When
        // Then
        Assertions.assertThrows(EmptyDtoRequestException.class, () -> {
            signUpService.approve(null);
        });
    }

    @DisplayName("회원 가입 요청 승인 실패 테스트 - 유효하지 않은 Email")
    @Test
    void approve_Failure_Test_InvalidEmail() {
        // Given
        Mockito.when(signUpRepository.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        SignUpRequest.ApproveDTO approveDTO = new SignUpRequest.ApproveDTO("user@example.com");
        // When
        // Then
        Assertions.assertThrows(SignUpServiceException.class, () -> {
            signUpService.approve(approveDTO);
        });
    }
}