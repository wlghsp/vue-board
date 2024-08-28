package com.okestro.boardapi.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Entity
public class UserEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 20)
    private String userId;
    @Column(nullable = false, length = 20)
    private String name;
    private Integer age;

    public UserEntity(String userId, String name, Integer age) {
        if (isUserIdEmpty(userId)) {
            throw new IllegalArgumentException(String.format("잘못된 userId(%s)이 들어왔습니다", userId));
        }
        this.userId = userId;
        this.name = name;
        this.age = age;
    }

    private boolean isUserIdEmpty(String userId) {
        return !StringUtils.hasText(userId);
    }

    public void updateUser(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

}
