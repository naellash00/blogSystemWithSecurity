package com.example.blogsystemsecurity.Service;

import com.example.blogsystemsecurity.Api.ApiException;
import com.example.blogsystemsecurity.Model.MyUser;
import com.example.blogsystemsecurity.Repository.AuthRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;

    // have to override this method to pass my user username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // i will create myUser object by finind its username
        MyUser myUser = authRepository.findMyUserByUsername(username);
        if(myUser == null){
            throw new ApiException("wrong username or password");
        }
        return myUser;
    }
}
