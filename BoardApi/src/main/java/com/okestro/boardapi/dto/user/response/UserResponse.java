package com.okestro.boardapi.dto.user.response;


import com.okestro.boardapi.model.UserEntity;
import lombok.Getter;

@Getter
public class UserResponse {

    private long id;
    private String userId;
    private String name;
    private int age;

    public UserResponse(UserEntity user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.name = user.getName();
        this.age = user.getAge();
    }
}
