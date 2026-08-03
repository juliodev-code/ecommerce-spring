package com.juliodev.ecommerce.controller;

import com.juliodev.ecommerce.model.AppRole;
import com.juliodev.ecommerce.model.Role;
import com.juliodev.ecommerce.model.User;
import com.juliodev.ecommerce.repositories.RoleRepository;
import com.juliodev.ecommerce.repositories.UserRepository;
import com.juliodev.ecommerce.security.jwt.JwtUtils;
import com.juliodev.ecommerce.security.request.LoginRequest;
import com.juliodev.ecommerce.security.request.SignupRequest;
import com.juliodev.ecommerce.security.response.MessageResponse;
import com.juliodev.ecommerce.security.response.UserInfoResponse;
import com.juliodev.ecommerce.security.services.UserDetailsImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        UserInfoResponse response = new UserInfoResponse(userDetails.getId(),
                userDetails.getUsername(), roles, userDetails.getEmail(), jwtCookie.toString());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?>registerUser(@RequestBody SignupRequest signupRequest){
        if(userRepository.existsByUserName(signupRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken"));
        }

        if(userRepository.existsByEmail(signupRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken"));
        }

        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> rolesInString = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(rolesInString == null){
            Optional<Role> userRole = roleRepository.findByRoleName(AppRole.ROLE_USER);
            if(userRole.isEmpty()) throw new RuntimeException("Error:Role is not found");
            roles.add(userRole.get());
        }
        else{
            rolesInString.forEach(role -> {
                switch(role){
                    case "admin":
                        Optional<Role> adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN);
                        if(adminRole.isEmpty()) throw new RuntimeException("Error:Admin Role is not found");
                        roles.add(adminRole.get());
                        break;
                    case "seller":
                        Optional<Role> sellerRole = roleRepository.findByRoleName(AppRole.ROLE_SELLER);
                        if(sellerRole.isEmpty()) throw new RuntimeException("Error:Seller Role is not found");
                        roles.add(sellerRole.get());
                        break;
                    default:
                        Optional<Role> userRole = roleRepository.findByRoleName(AppRole.ROLE_USER);
                        if(userRole.isEmpty()) throw new RuntimeException("Error:Role is not found");
                        roles.add(userRole.get());
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    @GetMapping("/username")
    public String currentUser(Authentication authentication){
        if(authentication != null) return authentication.getName();
        else return "";
    }

    @GetMapping("/user")
    public ResponseEntity<UserInfoResponse> getUserDetails(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        UserInfoResponse response = new UserInfoResponse(userDetails.getId(),
                userDetails.getUsername(), roles);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/signout")
    public ResponseEntity<?>signOutUser(){
        ResponseCookie jwtTokenDeletedCookie = this.jwtUtils.deleteJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtTokenDeletedCookie.toString())
                .body(new MessageResponse("You've been signOut."));
    }
}
