package ru.veucos.cms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ru.veucos.cms.security.Role;

import javax.persistence.*;

/**
 * Пользователи (клиенты)
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GenericGenerator(name = "kaugen", strategy = "increment")
    @GeneratedValue(generator = "kaugen")
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String name;
    private String phone;
    private String passport;
    private String password;
    @Transient // не создаем поле в таблице
    private String token;

    private Role role;
}
