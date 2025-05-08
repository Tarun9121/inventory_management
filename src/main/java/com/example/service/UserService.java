package com.example.service;

import com.example.dao.UserRepository;
import com.example.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public int checkCrediantals(String email, String password) {
        Optional<User> userOptional = userRepository.getUserByEmail(email);

        if(userOptional.isPresent()) {
            if(password.equals(userOptional.get().getPassword())) {
                return 200;
            } else {
                return 401;
            }
        }
        return 400;
    }

}
