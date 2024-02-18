package ru.veucos.cms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Кредит
 */
@Entity
@Table(name = "credits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GenericGenerator(name = "kaugen", strategy = "increment")
    @GeneratedValue(generator = "kaugen")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Offer offer;
    private Long amount;
    private LocalDate date;
}
