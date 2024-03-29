package ru.veucos.cms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Кредитное предложение
 */
@Entity
@Table(name = "offers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GenericGenerator(name = "kaugen", strategy = "increment")
    @GeneratedValue(generator = "kaugen")
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Bank bank;
    private Long lim;
    private Integer term;
    private Integer rate;
}
