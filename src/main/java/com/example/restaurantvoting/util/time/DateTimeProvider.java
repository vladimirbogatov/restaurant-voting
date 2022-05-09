package com.example.restaurantvoting.util.time;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public enum DateTimeProvider {
    INSTANCE;

    public static DateTimeProvider getInstance() {
        return INSTANCE;
    }

    private final Clock defaultClock = Clock.systemDefaultZone();
    public Clock clock = defaultClock;

    public LocalDateTime getNow() {
        return LocalDateTime.now(clock);
    }

    public void setDateTime(LocalDateTime localDateTime) {
        this.clock = Clock.fixed(localDateTime.toInstant(ZoneId.systemDefault().getRules().getOffset(localDateTime)),
                ZoneId.systemDefault());
    }

    public void resetTimeToDefault() {
        this.clock = this.defaultClock;
    }
}
