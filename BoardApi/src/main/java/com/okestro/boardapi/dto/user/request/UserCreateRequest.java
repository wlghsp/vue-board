package com.okestro.boardapi.dto.user.request;

import lombok.Getter;

@Getter
public class UserCreateRequest {
    private String userId;
    private String name;
    private Integer age;

}
