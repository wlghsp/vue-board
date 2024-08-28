package com.okestro.boardapi.service.impl;

import com.okestro.boardapi.model.UserEntity;
import com.okestro.boardapi.dto.user.request.UserCreateRequest;
import com.okestro.boardapi.dto.user.request.UserUpdateRequest;
import com.okestro.boardapi.dto.user.response.UserResponse;
import com.okestro.boardapi.repo.UserRepository;
import com.okestro.boardapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Transactional
    public void saveUser(UserCreateRequest request) {
        userRepository.save(new UserEntity(request.getUserId(), request.getName(), request.getAge()));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        UserEntity user = userRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new);
        user.updateUser(request.getName(), request.getAge());
    }
    @Transactional
    public void deleteUser(String userId) {
        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);
    }

}
