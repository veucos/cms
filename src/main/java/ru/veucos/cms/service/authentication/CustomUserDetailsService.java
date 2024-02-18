package ru.veucos.cms.service.authentication;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.security.CustomUserPrincipal;
import ru.veucos.cms.security.SecurityUtil;
import ru.veucos.cms.service.UserService;

import java.util.Set;

/**
 * Сервис детализации пользователя
 */
@AllArgsConstructor
@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService service;

    /**
     * Выборка детальной информации по пользователю
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
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
