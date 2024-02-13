package ru.veucos.cms.service.authentication;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.security.CustomUserPrincipal;
import ru.veucos.cms.security.SecurityUtil;
import ru.veucos.cms.service.impl.UserServiceImpl;

import java.util.Set;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserServiceImpl service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.getModelByEmail(username);
        Set<GrantedAuthority> authorities = Set.of(SecurityUtil.convertToAuthority(user.getRole().name()));

        return CustomUserPrincipal.builder()
                .user(user)
                .id(user.getId())
                .email(username)
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
