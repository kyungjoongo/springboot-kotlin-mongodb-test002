package com.kyungjoon.login;


import com.kyungjoon.user.User;
import com.kyungjoon.user.UserRepository;
import com.kyungjoon.user.UserService;
import com.kyungjoon.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class LoginService {


    @Autowired
    public UserRepository userRepository;

    public UserService userService ;


    public User getUserOne(User paramUser) throws Exception {

        Optional<User> dbUser = this.userRepository.findById(paramUser.getId());

        if (dbUser.isPresent()) {
            System.out.println("getId===>" + dbUser.get().getId());
            System.out.println("getPwd===>" + dbUser.get().getPwd());
            String token = JwtUtil.makeJwtToken();
            System.out.println("token===>" + token);
            User _user =dbUser.get();
            _user.setAccessToken(token);
            return _user;

        } else {
            throw new Exception("Record not found with id : " + paramUser.getId());
        }
    }


}
