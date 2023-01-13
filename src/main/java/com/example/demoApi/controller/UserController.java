package com.example.demoApi.controller;

import com.example.demoApi.domain.SexType;
import com.example.demoApi.dto.Response.UserDto;
import com.example.demoApi.dto.request.CreateUserDto;
import com.example.demoApi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto create(@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

    @GetMapping("{id}")
    public UserDto getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PatchMapping("{id}")
    public UserDto updateById(@RequestParam Long id, @RequestBody CreateUserDto createUserDto) {
        return userService.updateUserById(id, createUserDto);
    }

    @GetMapping("adult-users")
    public List<UserDto> getAdultUsers(int page, int size) {
        return userService.getAllAdultUsers(page, size);
    }

    @GetMapping("by-ids-and-gender")
            public List<UserDto> getByIdsAndSexType(@RequestParam List<Long> ids, @RequestParam SexType sexType, int page, int size) {
        return userService.getUsersByIdsAndSexType(ids, sexType, page, size);
    }

    @GetMapping
    public List<UserDto> getAll(int page, int size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping("has-content-by-sex-type")
    public List<UserDto> getAllWhoHaveContentBySexType(@RequestParam SexType sexType, int page, int size) {
        return userService.getUsersHasContentBySexType(sexType, page, size);

    }

    @GetMapping("who-has-min-fri-content-with-the-available-genre-and-comment")
    public List<UserDto> getUsersWhoHasContentAndGenreAndComment(int page, int size) {
        return userService.getUsersWhoHasContentAndGenreAndComment(page, size);
    }
}

