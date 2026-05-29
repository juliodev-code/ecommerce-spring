package com.juliodev.ecommerce.security.services;

import com.juliodev.ecommerce.repositories.UserRepository;
import com.juliodev.ecommerce.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUserName(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("User Not Found with username: " + username);
        return UserDetailsImpl.build(user.get());
    }
}
