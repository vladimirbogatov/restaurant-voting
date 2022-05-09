package com.example.restaurantvoting.util.time;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DateTimeUtil {

    private final DateTimeProvider dateTimeProvider;

    public LocalDateTime getNow() {
        return dateTimeProvider.getNow();
    }
}
