package ru.veucos.cms.service.authentication;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.veucos.cms.dto.AuthUserDto;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.mapper.UserMapper;
import ru.veucos.cms.security.CustomUserPrincipal;
import ru.veucos.cms.security.jwt.JwtProvider;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private UserMapper mapper;

    public UserDto signInAndReturnJwt(AuthUserDto signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(customUserPrincipal);

        User signInUser = customUserPrincipal.getUser();
        signInUser.setToken(jwt);
        return mapper.toDto(signInUser);
    }
}
