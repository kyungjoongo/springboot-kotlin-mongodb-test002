package com.kyungjoon.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserService {


    @Autowired
    public UserRepository userRepository;

    public User createProduct(User user) {

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User updateOne(User updatedUser) throws Exception {

        userRepository.save(updatedUser);
        return updatedUser;
    }

    public List<User> getAll() {

        List<User> users = this.userRepository.findAll();

        return users;
    }

    public User getOneByID(String id) throws Exception {

        Optional<User> userDB = this.userRepository.findById(id);

        if (userDB.isPresent()) {
            return userDB.get();
        } else {
            throw new Exception("Record not found with id : " + id);
        }
    }

    public void deleteOne(String id) throws Exception {
        Optional<User> userDB = this.userRepository.findById(id);

        if (userDB.isPresent()) {
            this.userRepository.delete(userDB.get());
        } else {
            throw new Exception("Record not found with id : " + id);
        }

    }
}
