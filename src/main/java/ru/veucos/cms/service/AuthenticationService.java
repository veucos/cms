package ru.veucos.cms.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.veucos.cms.dto.AuthUserDto;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.security.CustomUserPrincipal;
import ru.veucos.cms.security.jwt.JwtProvider;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public User signInAndReturnJwt(AuthUserDto signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(customUserPrincipal);

        User signInUser = customUserPrincipal.getUser();
        signInUser.setToken(jwt);

        return signInUser;
    }
}
