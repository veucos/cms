package ru.veucos.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.veucos.cms.entity.User;

import java.util.Optional;

/**
 * Репозиторий пользователей (клиентов)
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Поиск пользователя по почте
     *
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

    /**
     * Проверка пользователя на существование по email
     *
     * @param email
     * @return
     */
    Boolean existsByEmail(String email);
}
