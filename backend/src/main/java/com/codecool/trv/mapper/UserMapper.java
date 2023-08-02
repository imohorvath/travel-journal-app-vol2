package com.codecool.trv.mapper;

import com.codecool.trv.dto.user.NewUserRequest;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.model.User;

public class UserMapper {

    public static UserResponse mapToUserResponse(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    public static User mapToUser(NewUserRequest newUserRequest) {
        return User
                .builder()
                .username(newUserRequest.username())
                .firstName(newUserRequest.firstName())
                .lastName(newUserRequest.lastName())
                .email(newUserRequest.email())
                .password(newUserRequest.password())
                .build();
    }

}
