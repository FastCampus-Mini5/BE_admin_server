package com.adminServer._core.errors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessage {

    public static final String UN_AUTHORIZED = "인증되지 않았습니다.";
    public static final String FORBIDDEN = "권한이 없습니다.";

    public static final String TOKEN_UN_AUTHORIZED = "검증되지 않은 토큰입니다.";
    public static final String TOKEN_EXPIRED = "만료된 토큰입니다.";
    public static final String TOKEN_VERIFICATION_FAILED = "토큰 검증에 실패했습니다.";

    public static final String LOGIN_FAILED = "회원 정보가 존재하지 않습니다.";
    public static final String EMPTY_DATA_TO_JOIN = "회원 가입을 위한 데이터가 존재하지 않습니다.";
    public static final String EMPTY_DATA_TO_LOGIN = "로그인을 위한 데이터가 존재하지 않습니다.";

    public static final String EMPTY_DATA_TO_PAGING = "페이징을 위한 데이터가 존재하지 않습니다.";
    public static final String EMPTY_DATA_TO_APPROVE_SIGNUP = "회원 가입 요청 승인을 위한 데이터가 존재하지 않습니다.";

    public static final String NOT_FOUND_SIGNUP = "회원 가입 요청 정보가 존재하지 않습니다.";

    public static final String INVALID_STATUS = "유효하지 않은 상태입니다.";
}
