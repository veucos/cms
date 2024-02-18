package ru.veucos.cms.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Данные авторизации
 */
@Data
public class AuthUserDto {
    @NotBlank(message = "email обязателен")
    @Email(message = "email некорректный")
    private String email;
    @NotBlank(message = "Пароль обязателен")
    private String password;
}
