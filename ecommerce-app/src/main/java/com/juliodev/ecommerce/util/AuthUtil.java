package com.juliodev.ecommerce.util;

import com.juliodev.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.juliodev.ecommerce.model.User;

import java.util.Optional;

@Component
public class AuthUtil {

    @Autowired
    UserRepository userRepository;

    public String loggedInEmail(){
       return this.findUserNameInRepository().getEmail();
    }

    public Long loggedInUserId(){
        return this.findUserNameInRepository().getUserId();
    }

    public User loggedInUser(){
        return this.findUserNameInRepository();
    }

    private User findUserNameInRepository(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = this.userRepository.findByUserName(authentication.getName());
        if(user.isEmpty()) throw new UsernameNotFoundException("User Not Found with username: " + authentication.getName());
        return user.get();
    }


}
