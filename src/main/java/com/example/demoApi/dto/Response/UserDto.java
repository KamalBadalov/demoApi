package com.example.demoApi.dto.Response;

import com.example.demoApi.domain.SexType;

import java.util.Date;

public class UserDto {
    Long id;
    String name;
    String nickname;
    SexType sexType;
    Date birthDate;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(String name) {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNickname(String nickname) {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public SexType getSexType() {
        return sexType;
    }

    public void setSexType(SexType sexType) {
        this.sexType = sexType;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}
