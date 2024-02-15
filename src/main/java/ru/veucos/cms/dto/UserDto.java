package ru.veucos.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.veucos.cms.security.Role;

@Data
public class UserDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String email;
    private String name;
    private String phone;
    private String passport;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Role role;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String token;
}
