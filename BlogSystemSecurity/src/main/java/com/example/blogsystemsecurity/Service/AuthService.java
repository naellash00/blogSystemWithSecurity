package com.example.blogsystemsecurity.Service;

import com.example.blogsystemsecurity.Api.ApiException;
import com.example.blogsystemsecurity.Model.MyUser;
import com.example.blogsystemsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void register(MyUser myUser) {
        myUser.setRole("USER");
        String hashPassword = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashPassword);
        authRepository.save(myUser);
    }

    public void updateUser(Integer user_id, MyUser myUser) {
        MyUser oldUser = authRepository.findMyUserById(user_id);
        if(oldUser == null){
            throw new ApiException("user not found");
        }
        oldUser.setUsername(myUser.getUsername());
        oldUser.setPassword(myUser.getPassword());
        authRepository.save(oldUser);
    }

    public void deleteUser(Integer user_id){
        MyUser myUser = authRepository.findMyUserById(user_id);
        if(myUser == null){
            throw new ApiException("user not found");
        }
        authRepository.delete(myUser);
    }

}
