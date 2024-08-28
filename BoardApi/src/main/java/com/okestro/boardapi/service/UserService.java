package com.okestro.boardapi.service;


import com.okestro.boardapi.dto.user.request.UserCreateRequest;
import com.okestro.boardapi.dto.user.request.UserUpdateRequest;
import com.okestro.boardapi.dto.user.response.UserResponse;

import java.util.List;

public interface UserService {

    void saveUser(UserCreateRequest request);

    List<UserResponse> getUsers();

    void updateUser(UserUpdateRequest request);

    void deleteUser(String userId);
}
