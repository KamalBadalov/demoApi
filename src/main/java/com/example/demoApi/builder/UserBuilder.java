package com.example.demoApi.builder;

import com.example.demoApi.domain.User;
import com.example.demoApi.dto.Response.UserDto;
import com.example.demoApi.dto.request.CreateUserDto;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {

    public static UserDto userToUserDto(User user) {
        var userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setNickname(user.getNickname());
        userDto.setSexType(user.getSexType());
        userDto.setBirthDate(user.getBirthDate());
        return userDto;
    }

    public static User userDtoToUser(CreateUserDto createUserDto) {
        var user = new User();
        user.setNickname(createUserDto.getNickname());
        user.setEmail(createUserDto.getEmail());
        user.setBirthDate(createUserDto.getBirthDate());
        user.setSexType(createUserDto.getSexType());
        user.setPhoneNumber(createUserDto.getPhoneNumber());
        user.setName(createUserDto.getName());
        return user;
    }
}
