package ru.veucos.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.veucos.cms.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}
