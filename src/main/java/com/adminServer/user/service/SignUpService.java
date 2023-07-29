package com.adminServer.user.service;

import com.adminServer._core.errors.ErrorMessage;
import com.adminServer._core.errors.exception.EmptyDtoRequestException;
import com.adminServer._core.errors.exception.EmptyPagingDataRequestException;
import com.adminServer._core.errors.exception.SignUpServiceException;
import com.adminServer.user.dto.SignUpRequest;
import com.adminServer.user.dto.SignUpResponse;
import com.adminServer.user.model.SignUp;
import com.adminServer.user.model.User;
import com.adminServer.user.repository.SignUpRepository;
import com.adminServer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SignUpService {

    private final SignUpRepository signUpRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<SignUpResponse.ListDTO> getAllList(Pageable pageable) throws EmptyPagingDataRequestException {
        if (pageable == null) throw new EmptyPagingDataRequestException();

        Page<SignUp> signUps = signUpRepository.findAll(pageable);
        return signUps.map(SignUpResponse.ListDTO::from);
    }

    @Transactional
    public void approve(SignUpRequest.ApproveDTO approveDTO) {
        if (approveDTO == null) throw new EmptyDtoRequestException(ErrorMessage.EMPTY_DATA_TO_APPROVE_SIGNUP);

        Optional<SignUp> signUpOptional = signUpRepository.findByEmail(approveDTO.getEmail());
        SignUp signUp = signUpOptional.orElseThrow(() -> new SignUpServiceException(ErrorMessage.NOT_FOUND_SIGNUP));

        User user = signUp.toUser();
        userRepository.save(user);
        signUpRepository.delete(signUp);
    }
}
