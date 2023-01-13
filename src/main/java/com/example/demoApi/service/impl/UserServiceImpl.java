package com.example.demoApi.service.impl;

import com.example.demoApi.builder.UserBuilder;
import com.example.demoApi.domain.SexType;
import com.example.demoApi.dto.Response.UserDto;
import com.example.demoApi.dto.request.CreateUserDto;
import com.example.demoApi.repository.UserRepository;
import com.example.demoApi.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        var user = UserBuilder.userDtoToUser(createUserDto);
        var isEmailExists = userRepository.isEmailExists(user.getEmail());
        var isPhoneNumberExists = userRepository.islPhoneNumberExists(user.getPhoneNumber());
        if (isEmailExists || isPhoneNumberExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        var userOpt = userRepository.save(user);
        return UserBuilder.userToUserDto(userOpt);
    }

    @Override
    public void deleteUserById(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден");
        }
        userRepository.deleteById(id);

    }

    @Override
    public UserDto getUserById(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден");
        }
        return UserBuilder.userToUserDto(userRepository.getById(id));
    }

    @Override
    public UserDto updateUserById(Long id, CreateUserDto createUserDto) {
        var userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден");
        }
        var user = userOpt.get();
        if (createUserDto.getName() != null) {
            user.setName(createUserDto.getName());
        }
        if (createUserDto.getBirthDate() != null) {
            user.setBirthDate(createUserDto.getBirthDate());
        }
        if (createUserDto.getPhoneNumber() != null) {
            var isPhoneNumberExists = userRepository.islPhoneNumberExists(createUserDto.getPhoneNumber());
            if (isPhoneNumberExists) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {
                user.setPhoneNumber(createUserDto.getPhoneNumber());

            }
        }
        if (createUserDto.getEmail() != null) {
            var isEmailExists = userRepository.isEmailExists(createUserDto.getEmail());
            if (isEmailExists) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {
                user.setEmail(createUserDto.getEmail());
            }
        }
        if (createUserDto.getNickname() != null) {
            user.setNickname(createUserDto.getNickname());
        }
        if (createUserDto.getSexType() != null) {
            user.setSexType(createUserDto.getSexType());
        }
        return UserBuilder.userToUserDto(userRepository.save(user));
    }


    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        var pageable = PageRequest.of(page, size);
        var users = userRepository.findAll(pageable);
        return users
                .stream()
                .map(UserBuilder::userToUserDto).toList();
    }

    @Override
    public List<UserDto> getAllAdultUsers(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Pageable pageable = PageRequest.of(page, size);
        var users = userRepository.findAllAdultAuthors(pageable);
        return users
                .stream()
                .map(UserBuilder::userToUserDto)
                .toList();
    }




    @Override
    public List<UserDto> getUsersByIdsAndSexType(List<Long> ids, @NotNull SexType sexType, int page, int size) {
        if (page < 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        var pageable = PageRequest.of(page, size);
        var user = userRepository.findBySexTypeAndIdIn(sexType, ids, pageable);
        return user
                .stream()
                .map(UserBuilder::userToUserDto)
                .toList();
    }

    public List<UserDto> getUsersHasContentBySexType(SexType sexType, int page, int size) {
        if (page < 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        var users = userRepository.findUsersHasContentBySexType(sexType.name(), page, size);
        return users
                .stream()
                .map(UserBuilder::userToUserDto)
                .toList();
    }

    public List<UserDto> getUsersWhoHasContentAndGenreAndComment(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        var users = userRepository.findUsersWhoHasContentAndGenreAndComment(page, size);
        return users
                .stream()
                .map(UserBuilder::userToUserDto)
                .toList();
    }
}
