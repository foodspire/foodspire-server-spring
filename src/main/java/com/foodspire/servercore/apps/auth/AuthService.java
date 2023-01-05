package com.foodspire.servercore.apps.auth;

import com.foodspire.servercore.models.auth.User;
import com.foodspire.servercore.models.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User createUser(SignUpDTO signUpDTO) {
        User user = new User(null,
                signUpDTO.getEmail(),
                passwordEncoder.encode(signUpDTO.getPassword()),
                signUpDTO.getFirstName(),
                signUpDTO.getLastName()
        );
        userRepository.save(user);
        return user;
    }
}
