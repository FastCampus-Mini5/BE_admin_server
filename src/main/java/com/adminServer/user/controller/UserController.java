package com.adminServer.user.controller;

import com.adminServer._core.util.ApiResponse;
import com.adminServer.user.dto.UserResponse;
import com.adminServer.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse.Result<?>> list(
            @PageableDefault(size = 10) Pageable pageable) {
        log.info("GET /api/admin/user/list " + pageable);

        Page<UserResponse.ListDTO> listResponse = userService.getAllUsers(pageable);
        return ResponseEntity.ok(ApiResponse.success(listResponse));
    }
}
