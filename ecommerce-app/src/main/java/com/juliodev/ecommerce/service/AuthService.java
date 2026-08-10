package com.juliodev.ecommerce.service;

import com.juliodev.ecommerce.payload.AuthenticationResult;
import com.juliodev.ecommerce.payload.UserResponse;
import com.juliodev.ecommerce.security.request.LoginRequest;
import com.juliodev.ecommerce.security.request.SignupRequest;
import com.juliodev.ecommerce.security.response.MessageResponse;
import com.juliodev.ecommerce.security.response.UserInfoResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface AuthService {
    AuthenticationResult login(LoginRequest loginRequest);
    ResponseEntity<MessageResponse> register(SignupRequest signUpRequest);
    UserInfoResponse getCurrentUserDetails(Authentication authentication);
    ResponseCookie logoutUser();
    UserResponse getAllSellers(Pageable pageable);
}
