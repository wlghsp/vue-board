package com.okestro.boardapi.controller;

import com.okestro.boardapi.dto.user.request.UserCreateRequest;
import com.okestro.boardapi.dto.user.request.UserUpdateRequest;
import com.okestro.boardapi.dto.user.response.UserResponse;
import com.okestro.boardapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String userId) {
        userService.deleteUser(userId);
    }

}
