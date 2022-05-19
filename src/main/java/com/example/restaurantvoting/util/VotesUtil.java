package com.example.restaurantvoting.util;

import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.to.VoteTo;
import com.example.restaurantvoting.util.time.DateTimeProvider;
import com.example.restaurantvoting.util.time.DateTimeUtil;
import lombok.experimental.UtilityClass;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@UtilityClass
public class VotesUtil {

    public static final LocalTime THRESHOLD_TIME = LocalTime.of(11, 0);

    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getRestaurant().getId(), vote.getDate());
    }

    public static List<VoteTo> getTos(Collection<Vote> votes) {
        return votes.stream().map(VotesUtil::createTo).toList();
    }

    public static boolean isTimeBeforeThreshold() {
        DateTimeUtil dateTimeUtil = new DateTimeUtil(DateTimeProvider.INSTANCE);
        LocalTime nowTime = dateTimeUtil.getNow().toLocalTime();
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
}
