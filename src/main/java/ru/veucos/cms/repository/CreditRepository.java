package ru.veucos.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.veucos.cms.entity.Credit;
import ru.veucos.cms.entity.Offer;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
}
