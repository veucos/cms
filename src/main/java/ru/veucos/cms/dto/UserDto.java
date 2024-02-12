package ru.veucos.cms.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private Integer age;
}
