package com.adminServer.user.controller;

import com.adminServer._core.util.ApiResponse;
import com.adminServer.user.dto.SignUpRequest;
import com.adminServer.user.dto.SignUpResponse;
import com.adminServer.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/signup")
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse.Result<Page<SignUpResponse.ListDTO>>> list(
            @PageableDefault(size = 5) Pageable pageable) {
        log.info("GET /api/admin/signup/list " + pageable);

        Page<SignUpResponse.ListDTO> SignUpResponse = signUpService.getAllList(pageable);
        return ResponseEntity.ok(ApiResponse.success(SignUpResponse));
    }

    @PostMapping("/approve")
    public ResponseEntity<ApiResponse.Result<Object>> approve(
            @RequestBody @Valid SignUpRequest.ApproveDTO approveDTO,
            Errors errors) {
        log.info("POST /api/admin/signup/approve " + approveDTO);

        signUpService.approve(approveDTO);
        return ResponseEntity.ok(ApiResponse.success());
    }
}
