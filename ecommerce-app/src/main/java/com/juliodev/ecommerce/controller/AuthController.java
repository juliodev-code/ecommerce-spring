package com.juliodev.ecommerce.controller;

import com.juliodev.ecommerce.config.AppConstants;
import com.juliodev.ecommerce.model.AppRole;
import com.juliodev.ecommerce.model.Role;
import com.juliodev.ecommerce.model.User;
import com.juliodev.ecommerce.payload.AuthenticationResult;
import com.juliodev.ecommerce.repositories.RoleRepository;
import com.juliodev.ecommerce.repositories.UserRepository;
import com.juliodev.ecommerce.security.jwt.JwtUtils;
import com.juliodev.ecommerce.security.request.LoginRequest;
import com.juliodev.ecommerce.security.request.SignupRequest;
import com.juliodev.ecommerce.security.response.MessageResponse;
import com.juliodev.ecommerce.security.response.UserInfoResponse;
import com.juliodev.ecommerce.security.services.UserDetailsImpl;
import com.juliodev.ecommerce.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name="Auth API", description = "Security endpoints for the e-commerce app")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        AuthenticationResult result = authService.login(loginRequest);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,result.getJwtCookie().toString())
                .body(result.getResponse());
    }

    @PostMapping("/signup")
    public ResponseEntity<?>registerUser(@RequestBody SignupRequest signUpRequest){
        return authService.register(signUpRequest);
    }

    @GetMapping("/username")
    public String currentUser(Authentication authentication){
        if(authentication != null) return authentication.getName();
        else return "";
    }

    @GetMapping("/user")
    public ResponseEntity<UserInfoResponse> getUserDetails(Authentication authentication){
        return ResponseEntity.ok().body(authService.getCurrentUserDetails(authentication));
    }

    @PostMapping("/signout")
    public ResponseEntity<?>signOutUser(){
        ResponseCookie cookie = authService.logoutUser();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signOut."));
    }

    @GetMapping("/sellers")
    public ResponseEntity<?> getAllSellers(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber) {

        Sort sortByAndOrder = Sort.by(AppConstants.SORT_USERS_BY).descending();
        Pageable pageDetails = PageRequest.of(pageNumber ,
                Integer.parseInt(AppConstants.PAGE_SIZE), sortByAndOrder);

        return ResponseEntity.ok(authService.getAllSellers(pageDetails));
    }
}
