package com.adminServer._core.security;

import com.adminServer._core.errors.ErrorMessage;
import com.adminServer._core.errors.exception.Exception500;
import com.adminServer.user.UserRepository;
import com.adminServer.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PrincipalUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("스프링 시큐리티 로그인 서비스 호출 email: " + email);

        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.orElseThrow(() -> new Exception500(ErrorMessage.LOGIN_FAILED));

        return new PrincipalUserDetail(user);
    }
}
