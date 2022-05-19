package com.example.restaurantvoting.util.time;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DateTimeUtil {

    private final DateTimeProvider dateTimeProvider;

    public static LocalDate getNowDate() {
        DateTimeUtil dateTimeUtil = new DateTimeUtil(DateTimeProvider.INSTANCE);
        return dateTimeUtil.getNow().toLocalDate();
    }

    public static LocalDate atStartOfDayOrMin(LocalDate localDate) {
        return localDate != null ? localDate : LocalDate.of(2000, 1, 1);
    }

    public static LocalDate endOfDayOrMax(LocalDate localDate) {
        return localDate != null ? localDate : LocalDate.of(3000, 12, 31);
    }

    public LocalDateTime getNow() {
        return dateTimeProvider.getNow();
    }
}
