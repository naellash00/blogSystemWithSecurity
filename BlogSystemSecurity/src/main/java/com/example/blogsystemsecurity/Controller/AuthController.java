package com.example.blogsystemsecurity.Controller;

import com.example.blogsystemsecurity.Api.ApiResponse;
import com.example.blogsystemsecurity.Model.MyUser;
import com.example.blogsystemsecurity.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid MyUser myUser){
        authService.register(myUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Registered Successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid MyUser myNewUser){
        authService.updateUser(myUser.getId(), myNewUser);
        return ResponseEntity.status(200).body(new ApiResponse("user updated"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal MyUser myUser){
        authService.deleteUser(myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("user deleted"));
    }

}
