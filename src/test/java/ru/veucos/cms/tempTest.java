package ru.veucos.cms;

import org.junit.jupiter.api.Test;

public class tempTest {
    @Test
    void calcCreditSchedule() {
        Long amount = 100000L;
        Long rate = 12L;
        Long term = 60L;
        Double monthRate = rate / 12.0 / 100;
        Double monthPayment = Math.round(100 * amount * (monthRate / (1 - Math.pow(1 + monthRate, -term)))) / 100.0;
        System.out.println(monthPayment);
        for (int i = 1; i < term; i++) {
            Double body = Math.round(100 * monthPayment / Math.pow(1 + monthRate, term - i + 1)) / 100.0;
            Double percent = monthPayment - body;
            System.out.printf("%d %.2f %.2f%n", i, body, percent);
        }
    }
}
