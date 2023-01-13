package com.example.demoApi.service;

import com.example.demoApi.domain.SexType;
import com.example.demoApi.dto.Response.UserDto;
import com.example.demoApi.dto.request.CreateUserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(CreateUserDto createUserDto);

    void deleteUserById(Long id);

    UserDto getUserById(Long id);

    UserDto updateUserById(Long id, CreateUserDto createUserDto);

    List<UserDto> getAllUsers(int page, int size);

    List<UserDto> getAllAdultUsers(int page, int size);

    List<UserDto> getUsersByIdsAndSexType(List<Long> id, SexType sexType, int page, int size);

    List<UserDto> getUsersHasContentBySexType(SexType sexType, int page, int size);
    public List<UserDto> getUsersWhoHasContentAndGenreAndComment(int page, int size);


}
