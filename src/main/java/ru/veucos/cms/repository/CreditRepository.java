package ru.veucos.cms.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.veucos.cms.entity.Credit;
import ru.veucos.cms.entity.User;

import java.util.List;

/**
 * Репозиторий кредита
 */
@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    /**
     * Кредиты по пользователю
     *
     * @param user пользователь
     * @return
     */
    List<Credit> getByUser(User user);

    /**
     * Кредиты по пользователю (пагинация)
     *
     * @param user        пользователь
     * @param pageRequest
     * @return
     */
    List<Credit> getByUser(User user, PageRequest pageRequest);
}
