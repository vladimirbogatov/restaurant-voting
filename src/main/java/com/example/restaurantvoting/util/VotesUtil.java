package com.example.restaurantvoting.util;

import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.to.VoteTo;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@UtilityClass
public class VotesUtil {

    private static final LocalTime THRESHOLD_TIME = LocalTime.of(11, 00);

    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getRestaurant().getId(), vote.getDate());
    }

    public static List<VoteTo> getTos(Collection<Vote> votes) {
        return votes.stream().map(VotesUtil::createTo).toList();
    }


    public static boolean isTimeBeforeThreshold() {
        LocalTime nowTime = getNowTime();
        //        LocalTime nowTime = LocalTime.now();
        return !nowTime.isAfter(THRESHOLD_TIME);
    }

    public static Vote candidatePrepare(Vote candidate, Vote actual) {
        if (actual != null) {
            if (isTimeBeforeThreshold()) {
                candidate.setId(actual.getId());
            } else {
                return actual;
            }
        }
        return candidate;
    }

    public static LocalTime getNowTime() {
//        return LocalTime.of(11, 01);
        return LocalTime.now();
    }

    public static LocalDate getNowDate() {
//        return LocalDate.of(2022, 05, 02);
        return LocalDate.now();
    }

    public static LocalDate atStartOfDayOrMin(LocalDate localDate) {
        return localDate != null ? localDate : LocalDate.of(2000, 01, 01);
    }

    public static LocalDate endOfDayOrMax(LocalDate localDate) {
        return localDate != null ? localDate : LocalDate.of(3000, 12, 31);
    }
}
