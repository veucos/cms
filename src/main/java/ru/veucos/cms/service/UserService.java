package ru.veucos.cms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.exception.ItemNotFoundException;
import ru.veucos.cms.repository.UserRepository;
import ru.veucos.cms.security.Role;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException("User not found, id = " + id));
    }

    public User findByUsername(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new ItemNotFoundException("User not found, email = " + email));
    }

    public boolean isUserExist(String email) {
        return repository.findByEmail(email).isPresent();
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return repository.save(user);
    }

    public User update(User user) {
        findById(user.getId());
        return repository.save(user);
    }

    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
