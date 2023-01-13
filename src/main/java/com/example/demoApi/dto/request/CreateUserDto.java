package com.example.demoApi.dto.request;

import com.example.demoApi.domain.SexType;

import java.util.Date;

public class CreateUserDto {
    String name;
    String email;
    Long phoneNumber;
    String nickname;
    SexType sexType;
    Date birthDate;

    public CreateUserDto() {
    }

    public CreateUserDto(String name, String email, Long phoneNumber,
                         String nickname, SexType sexType, Date birthDate) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.sexType = sexType;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickname() {
        return nickname;
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

    public void setBirthDate(Date date) {
        this.birthDate = date;
    }
}
