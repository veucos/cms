package ru.veucos.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.veucos.cms.security.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Данные справочника пользователей
 */
@Data
@AllArgsConstructor
public class UserDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank(message = "email обязателен")
    @Email(message = "email некорректный")
    private String email;
    @NotBlank(message = "Имя обязательно")
    private String name;
    @NotBlank(message = "Телефон обязателен")
    @Pattern(regexp = "\\+?\\d+([\\(\\s\\-]?\\d+[\\)\\s\\-]?[\\d\\s\\-]+)?", message = "Телефон некорректный")
    private String phone;
    @NotBlank(message = "Паспорт обязателен")
    @Pattern(regexp = "\\d{4}\\s\\d{6}", message = "Введите серию и номер паспорта через пробел")
    private String passport;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Пароль обязателен")
    private String password;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Role role;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String token;
}
