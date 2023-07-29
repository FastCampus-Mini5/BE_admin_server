package com.adminServer.user.service;

import com.adminServer._core.errors.exception.EmptyPagingDataRequestException;
import com.adminServer.user.dto.SignUpResponse;
import com.adminServer.user.model.SignUp;
import com.adminServer.user.repository.SignUpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignUpService {

    private final SignUpRepository signUpRepository;

    @Transactional(readOnly = true)
    public Page<SignUpResponse.ListDTO> getAllList(Pageable pageable) throws EmptyPagingDataRequestException {
        if (pageable == null) throw new EmptyPagingDataRequestException();

        Page<SignUp> signUps = signUpRepository.findAll(pageable);
        return signUps.map(SignUpResponse.ListDTO::from);
    }
}
