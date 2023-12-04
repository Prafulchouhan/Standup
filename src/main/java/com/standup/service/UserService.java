package com.standup.service;


import com.standup.dto.LoginBody;
import com.standup.dto.RegistrationBody;
import com.standup.model.User;
import com.standup.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private JWTService jwtService;

    public User registerUser(RegistrationBody body) {
        User user=new User();
        user.setUserName(body.getUserName());
        user.setRole("NORMAL");
        user.setEmail(body.getEmail());
        //encrypted password
        user.setPassword(encryptionService.encryptPassword(body.getPassword()));
        return userRepository.save(user);
    }

    public String logIn(LoginBody body){
        Optional<User> user=userRepository.findById(body.getEmpId());
        if(user.isPresent()){
            User localUser=user.get();
            if(encryptionService.verifyPassword(body.getPassword(),localUser.getPassword())){
                System.out.println("localUser = " + localUser);
                return jwtService.generateJWT(localUser);
            }
        }
        return null;
    }

    public User getName(String name){
        return userRepository.findUserByUserName(name).get();
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).get();
    }
}
